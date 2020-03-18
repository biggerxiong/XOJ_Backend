package me.xiongxuan.xoj.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.google.gson.Gson;
import me.xiongxuan.xoj.entity.Result;
import me.xiongxuan.xoj.entity.request.HeartBeatRequest;
import me.xiongxuan.xoj.service.ProblemService;
import me.xiongxuan.xoj.util.ResultUtil;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

/**
 * @author XiongXuan
 */
@Controller
public class HomeController {

    @Resource
    private ProblemService problemService;

    @ResponseBody
    @GetMapping("/ping")
    public Result postPing() {
        return ResultUtil.success("success");
    }

    @ResponseBody
    @PostMapping("/heartBeat")
    public String heartBeat(@RequestBody HeartBeatRequest heartBeatRequest, HttpServletRequest request) {
        String token = request.getHeader("X-JUDGE-SERVER-TOKEN");
//        System.out.println(token);
//        System.out.println(heartBeatRequest);
        JSONObject json = new JSONObject(false, false);
        json.put("data", "success");
        json.put("error", null);

        return json.toString();
    }

    @ResponseBody
    @GetMapping("/md5/{problemId}")
    public Result getProblemDataMd5(@PathVariable Integer problemId) {
        return ResultUtil.success(problemService.getProblemById(problemId).getTestCaseMd5());
    }
}
