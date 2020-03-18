package me.xiongxuan.xoj.controller;

import cn.hutool.core.io.file.FileWriter;
import cn.hutool.core.util.URLUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import me.xiongxuan.xoj.annotation.RequestLimit;
import me.xiongxuan.xoj.entity.*;
import me.xiongxuan.xoj.entity.request.JudgeStatusFilterRequest;
import me.xiongxuan.xoj.entity.request.JudgeStatusUpdateRequest;
import me.xiongxuan.xoj.entity.request.ProblemSubmitRequest;
import me.xiongxuan.xoj.entity.request.TestcaseResult;
import me.xiongxuan.xoj.entity.response.BoardRespose;
import me.xiongxuan.xoj.service.*;
import me.xiongxuan.xoj.util.ResultUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author XiongXuan
 */
@Controller
public class JudgeController {
    /**
     * 存放代码文件的位置
     */
    @Value("${file.code.path}")
    private String codeSavePath;

    @Resource
    private JudgeStatusService judgeStatusService;

    @Resource
    private ProblemService problemService;

    @Resource
    private ActiveMqProducerService activeMqProducerService;

    @Resource
    private UserService userService;

    @Resource
    private LanguageService languageService;

    private final String qduojCookie = "csrftoken=YfkB581US4wC1WqkEf38qcJc7c4gpsHzcmsi0mZDtzF7lUH8TWgRkIgWFH00cRTK; sessionid=jaqhjiukxw1f4mla0ti830ug1e9esgwy";

    /**
     * 评测代码
     * @param problemSubmitRequest
     * @param authentication
     * @return
     */
    @PreAuthorize("hasAuthority('AUTH_JUDGE_CODE')")
    @PostMapping("/submit")
    @RequestLimit(count = 1, time = 10000)
    @ResponseBody
    public Result judgeCode(@RequestBody ProblemSubmitRequest problemSubmitRequest, Authentication authentication) {

        User user = userService.getUserByUserName(authentication.getName());

        String source = problemSubmitRequest.getSource();
        Integer problemId = problemSubmitRequest.getProblemId();
        Integer languageId = problemSubmitRequest.getLanguageId();
        Language language = languageService.getLanguageById(languageId);
        source = URLUtil.decode(source);

        Problem problem = problemService.getTotalProblemById(problemId);
        problemService.submitIncrease(problem);
        userService.submitIncrease(user);

        // 保存该代码
        String codeSaveName = DigestUtil.md5Hex(source) + language.getSuffix();
        FileWriter fileWriter = new FileWriter(codeSavePath + File.separator + codeSaveName, Charset.forName("UTF-8"));
        fileWriter.write(source);

        JudgeStatus judgeStatus = judgeStatusService.createJudgeStatus(new JudgeStatus(problemId, user, codeSaveName, 0, "In Queue", languageId));

        if (problem.getProblemType().getProblemTypeId() == 1) {
            MqMessage mqMessage = new MqMessage();
            mqMessage.setJudgeStatusId(judgeStatus.getJudgeStatusId());
            mqMessage.setProblemId(problemId);
            mqMessage.setCode(source);
            mqMessage.setLanguageId(languageId);
            mqMessage.setMaxCpuTime(problem.getTimeLimit());
            mqMessage.setMaxMemory(problem.getMemoryLimit() * 1024 * 1024);
            activeMqProducerService.sendToJudgeQueue(mqMessage);
            judgeStatus.setRemoteId(judgeStatus.getJudgeStatusId().toString());
            judgeStatusService.updateJudgeStatus(judgeStatus);
        }
        // qduoj
        else if (problem.getProblemType().getProblemTypeId() == 2) {
            String url = "https://qduoj.com/api/submission";
            JSONObject json1 = JSONUtil.createObj();
            json1.put("problem_id", Integer.parseInt(problem.getRemoteProblemId()) + 3);
            json1.put("language", "C++");
            json1.put("code", source);


            String resultJson = HttpRequest.post(url).header("x-csrftoken", "YfkB581US4wC1WqkEf38qcJc7c4gpsHzcmsi0mZDtzF7lUH8TWgRkIgWFH00cRTK")
                    .body(json1).cookie(qduojCookie).execute().body();
            JSONObject json = JSONUtil.parseObj(resultJson);
            System.out.println(resultJson);
            json = json.getJSONObject("data");
            judgeStatus.setRemoteId(json.getStr("submission_id"));
            judgeStatusService.updateJudgeStatus(judgeStatus);
        }
        return ResultUtil.success("", judgeStatus);
    }

    /**
     * 更新评测状态(用于评测机)
     * @param judgeStatusUpdateRequest
     * @return
     */
    @PostMapping("/status")
    @ResponseBody
    public Result updateJudgeStatus(@RequestBody JudgeStatusUpdateRequest judgeStatusUpdateRequest) {
        JudgeStatus judgeStatus = judgeStatusService.getJudgeStatusById(judgeStatusUpdateRequest.getId());
        System.out.println(judgeStatusUpdateRequest);

        int errorCode = 1;
        int score = 0;
        int time = 0;
        int memory = 0;
        String result = "";
        if (judgeStatusUpdateRequest.getError() == null) {
            for (TestcaseResult testcaseResult: judgeStatusUpdateRequest.getData()) {
                if (testcaseResult.getResult() == 0) {
                    score += 100 / judgeStatusUpdateRequest.getData().size();
                    time = Math.max(time, testcaseResult.getCpuTime());
                    memory = Math.max(memory, testcaseResult.getMemory());
                }
            }
            judgeStatus.setResult(JSONUtil.toJsonStr(judgeStatusUpdateRequest.getData()));
        } else if ("CompileError".equals(judgeStatusUpdateRequest.getError())) {
            errorCode = 2;
            judgeStatus.setErrorMsg(judgeStatusUpdateRequest.getErrorMsg());
        } else if ("JudgeClientError".equals(judgeStatusUpdateRequest.getError())) {
            errorCode = 3;
            judgeStatus.setErrorMsg(judgeStatusUpdateRequest.getErrorMsg());
        }

        if (score == 100) {
            problemService.acceptIncrease(judgeStatus.getProblem());
            // 如果之前没有通过这道题，就更新通过题数
            if (!judgeStatusService.isAccept(judgeStatus.getUser(), judgeStatus.getProblem())) {
                userService.acceptIncrease(judgeStatus.getUser());
            }
        }

        judgeStatus.setError(errorCode);
        judgeStatus.setScore(score);
        judgeStatus.setTimeCost(time);
        judgeStatus.setMemoryCost(memory);
        judgeStatusService.updateJudgeStatus(judgeStatus);
        return ResultUtil.success(judgeStatus);

//        JSONObject jsonObject = getJSONParam(request);
//
//        Integer judgeStatusId = jsonObject.getInt("id");
//        JudgeStatus judgeStatus = judgeStatusService.getJudgeStatusById(judgeStatusId);
//        String err = jsonObject.getStr("err");
//
//        int error = 1;
//        int score = 0;
//        int time = 0;
//        int memory = 0;
//        String result = "";
//        if (err == null) {
//            result = JSONUtil.toJsonStr(jsonObject.getJSONArray("data"));
//            List<TestcaseResult> testcaseResults = JSONUtil.toList(jsonObject.getJSONArray("data"), TestcaseResult.class);
//            for (TestcaseResult testcaseResult: testcaseResults) {
//                if (testcaseResult.getResult() == 0) {
//                    score += 100 / testcaseResults.size();
//                    time = Math.max(time, testcaseResult.getCpuTime());
//                    memory = Math.max(memory, testcaseResult.getMemory());
//                }
//            }
//        } else if ("CompileError".equals(err)) {
//            result = jsonObject.getStr("data");
//            error = 2;
//        }

//        judgeStatus.setError(error);
//        judgeStatus.setResult(result);
//        judgeStatus.setScore(score);
//        judgeStatus.setTimeCost(time);
//        judgeStatus.setMemoryCost(memory);
//        judgeStatusService.updateJudgeStatus(judgeStatus);
//        return ResultUtil.success(judgeStatus);
    }

    /**
     * 获取特定id的评测结果
     * @param judgeId
     * @return
     */
    @PreAuthorize("hasAuthority('AUTH_GET_JUDGE_STATUS')")
    @GetMapping("/status/{judgeId}")
    @ResponseBody
    public Result getJudgeStatus(@PathVariable Integer judgeId) {
        JudgeStatus judgeStatus = judgeStatusService.getJudgeStatusById(judgeId);

        Problem problem = judgeStatus.getProblem();

        if (problem.getProblemType().getProblemTypeId() == 1) {
            return ResultUtil.success(judgeStatus);
        } else if (problem.getProblemType().getProblemTypeId() == 2) {
            if (judgeStatus.getScore() == 0) {
                judgeStatus = grabQduojStatus(judgeStatus);
            }
        }

        return ResultUtil.success(judgeStatus);
    }

    /**
     * 判断用户是否ac该题
     * @return
     */
    @PreAuthorize("hasAuthority('AUTH_GET_JUDGE_STATUS')")
    @GetMapping("/status/is_accept/{problemId}")
    @ResponseBody
    public Result isAccept(@PathVariable Integer problemId, Authentication authentication) {
        User user = userService.getUserByUserName(authentication.getName());
        Problem problem = problemService.getProblemById(problemId);

        boolean ans = judgeStatusService.isAccept(user, problem);
        return ResultUtil.success(ans);
    }

    /**
     * 获取自己通过的题目列表
     * @return
     */
    @PreAuthorize("hasAuthority('AUTH_GET_JUDGE_STATUS')")
    @GetMapping("/problem/accept/list")
    @ResponseBody
    public Result getAcceptList(Authentication authentication) {
        User user = userService.getUserByUserName(authentication.getName());
        return ResultUtil.success(judgeStatusService.getAcceptedProblemIdByUser(user));
    }

    /**
     * 获取评测列表
     * @return
     */
    @PreAuthorize("hasAuthority('AUTH_GET_JUDGE_STATUS')")
    @GetMapping("/status")
    @ResponseBody
    public Result getJudgeStatusList(JudgeStatusFilterRequest request) {
        return ResultUtil.success(judgeStatusService.getJudgeStatusByFilterRequest(request));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/status/board")
    @ResponseBody
    public Result getBoard() {
        List<BoardRespose> boardResposeList = new ArrayList<>();

        for (int i = 110; i <= 114; i++) {
            User user = userService.getUserById(i);
            Integer count = judgeStatusService.getAcceptedCountByUserIdBeforeDays(user.getUserId(), 15);
            BoardRespose boardRespose = new BoardRespose();
            boardRespose.setScore(count * 100);
            boardRespose.setUser(user);
            boardResposeList.add(boardRespose);
        }

        boardResposeList.sort(new Comparator<BoardRespose>() {
            @Override
            public int compare(BoardRespose o1, BoardRespose o2) {
                return o1.getScore() > o2.getScore() ? 1 : 0;
            }
        });

        System.out.println(boardResposeList);
        return ResultUtil.success(boardResposeList);
        }

private JudgeStatus grabQduojStatus(JudgeStatus judgeStatus) {
        String url = "https://qduoj.com/api/submission?id=";
        String resultJson = HttpRequest.get(url + judgeStatus.getRemoteId()).header("x-csrftoken", "YfkB581US4wC1WqkEf38qcJc7c4gpsHzcmsi0mZDtzF7lUH8TWgRkIgWFH00cRTK")
        .cookie(qduojCookie).execute().body();
        System.out.println("request " + url + judgeStatus.getRemoteId());
        JSONObject json = JSONUtil.parseObj(resultJson);
        System.out.println(resultJson);
        json = json.getJSONObject("data");

        // TODO: 适配QDOJ的languageId
        judgeStatus.setLanguage(new Language(0));
        Integer resultCode = json.getInt("result");
        JSONObject statisticInfoJson = json.getJSONObject("statistic_info");
        switch (resultCode) {
        case 0:
        judgeStatus.setScore(7);
        judgeStatus.setResult("Accept");
        judgeStatus.setTimeCost(statisticInfoJson.getInt("time_cost"));
        judgeStatus.setMemoryCost(statisticInfoJson.getInt("memory_cost"));
        break;
        case -1:
        judgeStatus.setScore(6);
        judgeStatus.setResult("Wrong Answer");
        judgeStatus.setTimeCost(statisticInfoJson.getInt("time_cost"));
        judgeStatus.setMemoryCost(statisticInfoJson.getInt("memory_cost"));
        break;
        case 1:
        judgeStatus.setScore(2);
        judgeStatus.setResult("Time Limit Exceeded");
        judgeStatus.setTimeCost(statisticInfoJson.getInt("time_cost"));
        judgeStatus.setMemoryCost(statisticInfoJson.getInt("memory_cost"));
        break;
        case -2:
        judgeStatus.setScore(1);
        judgeStatus.setResult("Compile Error");
        judgeStatus.setResult(statisticInfoJson.getStr("err_info"));
        break;
        case 3:
        judgeStatus.setScore(3);
        judgeStatus.setResult("Memory Limit Exceeded");
        judgeStatus.setTimeCost(statisticInfoJson.getInt("time_cost"));
        judgeStatus.setMemoryCost(statisticInfoJson.getInt("memory_cost"));
        break;
        case 4:
        judgeStatus.setScore(5);
        judgeStatus.setResult("Runtime Error");
        judgeStatus.setTimeCost(statisticInfoJson.getInt("time_cost"));
        judgeStatus.setMemoryCost(statisticInfoJson.getInt("memory_cost"));
        break;
        case 5:
        judgeStatus.setScore(9);
        judgeStatus.setResult("System Error");
        judgeStatus.setTimeCost(statisticInfoJson.getInt("time_cost"));
        judgeStatus.setMemoryCost(statisticInfoJson.getInt("memory_cost"));
        break;
default:
        }
        judgeStatusService.updateJudgeStatus(judgeStatus);
        return judgeStatus;
        }


        }
