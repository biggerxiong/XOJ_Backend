package me.xiongxuan.xoj.controller;

import me.xiongxuan.xoj.annotation.RequestLimit;
import me.xiongxuan.xoj.entity.*;
import me.xiongxuan.xoj.entity.request.ProblemEditRequest;
import me.xiongxuan.xoj.service.JudgeStatusService;
import me.xiongxuan.xoj.service.ProblemService;
import me.xiongxuan.xoj.service.UserService;
import me.xiongxuan.xoj.util.MarkDownUtil;
import me.xiongxuan.xoj.util.ResultUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
public class ProblemController {
    @Resource
    private ProblemService problemService;

    @Resource
    private JudgeStatusService judgeStatusService;

    @Resource
    private UserService userService;

    @PreAuthorize("hasAuthority('AUTH_GET_PROBLEM_LIST')")
    @GetMapping("/problems")
    @ResponseBody
    public Result getProblemList() {
        return ResultUtil.success(problemService.getVisiableProblemList());
    }

    @PreAuthorize("hasAuthority('AUTH_GET_PROBLEM_DETAIL')")
    @GetMapping("/problem/{problemId}")
    @ResponseBody
    public Result getProblem(@PathVariable Integer problemId) {
        return ResultUtil.success(problemService.getProblemById(problemId));
    }

    @PreAuthorize("hasAuthority('AUTH_CREATE_PROBLEM')")
    @PostMapping("/problem/new")
    @ResponseBody
    public Result createProblem(@RequestBody ProblemEditRequest problemEditRequest, Authentication authentication) {

        Problem problem = new Problem();
        problem.setTitle(problemEditRequest.getTitle());
        problem.setTimeLimit(problemEditRequest.getTimeLimit());
        problem.setMemoryLimit(problemEditRequest.getMemoryLimit());
        problem.setDescriptionMarkdown(problemEditRequest.getDescriptionMarkdown());
        problem.setDescription(MarkDownUtil.markdownToHtml(problem.getDescriptionMarkdown()));
        problem.setInputDescriptionMarkdown(problemEditRequest.getInputDescriptionMarkdown());
        problem.setInputDescription(MarkDownUtil.markdownToHtml(problem.getInputDescriptionMarkdown()));
        problem.setOutputDescriptionMarkdown(problemEditRequest.getOutputDescriptionMarkdown());
        problem.setOutputDescription(MarkDownUtil.markdownToHtml(problem.getOutputDescriptionMarkdown()));
        problem.setSamples(problemEditRequest.getSamples());
        problem.setHintMarkdown(problemEditRequest.getHintMarkdown());
        problem.setHint(MarkDownUtil.markdownToHtml(problem.getHintMarkdown()));

        User user = userService.getUserByUserName(authentication.getName());
        problem.setCreateBy(user);
        problem.setProblemType(new ProblemType(1));

        Problem savedProblem = problemService.createProblem(problem);
        return ResultUtil.success(savedProblem);
    }

    @PreAuthorize("hasAuthority('AUTH_EDIT_PROBLEM')")
    @PostMapping("/problem/edit/{problemId}")
    @ResponseBody
    public Result editProblem(@RequestBody ProblemEditRequest problemEditRequest, @PathVariable Integer problemId) {

        Problem savedProblem = problemService.getProblemById(problemId);
        savedProblem.setTitle(problemEditRequest.getTitle());
        savedProblem.setTimeLimit(problemEditRequest.getTimeLimit());
        savedProblem.setMemoryLimit(problemEditRequest.getMemoryLimit());
        savedProblem.setDescriptionMarkdown(problemEditRequest.getDescriptionMarkdown());
        savedProblem.setDescription(MarkDownUtil.markdownToHtml(savedProblem.getDescriptionMarkdown()));
        savedProblem.setInputDescriptionMarkdown(problemEditRequest.getInputDescriptionMarkdown());
        savedProblem.setInputDescription(MarkDownUtil.markdownToHtml(savedProblem.getInputDescriptionMarkdown()));
        savedProblem.setOutputDescriptionMarkdown(problemEditRequest.getOutputDescriptionMarkdown());
        savedProblem.setOutputDescription(MarkDownUtil.markdownToHtml(savedProblem.getOutputDescriptionMarkdown()));
        savedProblem.setSamples(problemEditRequest.getSamples());
        savedProblem.setHintMarkdown(problemEditRequest.getHintMarkdown());
        savedProblem.setHint(MarkDownUtil.markdownToHtml(savedProblem.getHintMarkdown()));

        Problem newProblem = problemService.updateProblem(savedProblem);
        return ResultUtil.success(newProblem);
    }

    @PreAuthorize("hasAuthority('AUTH_EDIT_PROBLEM')")
    @GetMapping("/problem/edit/{problemId}")
    @ResponseBody
    public Result getEditProblem(@PathVariable Integer problemId) {

        return ResultUtil.success(problemService.getEditProblemById(problemId));
    }

}
