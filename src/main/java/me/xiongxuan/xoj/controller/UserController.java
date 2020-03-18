package me.xiongxuan.xoj.controller;

import me.xiongxuan.xoj.entity.Result;
import me.xiongxuan.xoj.service.UserService;
import me.xiongxuan.xoj.util.ResultUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author XiongXuan
 */
@Controller
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/user")
    @ResponseBody
    public Result getUserAuthority(Authentication authentication) {
        return ResultUtil.success(authentication);
    }

    @PreAuthorize("hasAuthority('AUTH_GET_JUDGE_STATUS')")
    @GetMapping("/user/rank")
    @ResponseBody
    public Result getRankList(Integer page, Integer rows) {
        if (page == null) {
            page = 0;
        }
        if (rows == null) {
            rows = 10;
        }

        return ResultUtil.success(userService.getRankList(page, rows));
    }
}
