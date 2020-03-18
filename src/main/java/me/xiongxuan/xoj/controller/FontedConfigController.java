package me.xiongxuan.xoj.controller;

import me.xiongxuan.xoj.entity.Result;
import me.xiongxuan.xoj.entity.Role;
import me.xiongxuan.xoj.entity.User;
import me.xiongxuan.xoj.entity.response.FontedRouterInfo;
import me.xiongxuan.xoj.service.AuthorityService;
import me.xiongxuan.xoj.service.UserService;
import me.xiongxuan.xoj.util.ResultUtil;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author XiongXuan
 */
@Controller
@RequestMapping("/config")
public class FontedConfigController {

    @Resource
    AuthorityService authorityService;

    @Resource
    UserService userService;

    @GetMapping("/header/list")
    @ResponseBody
    public Result getHeaderList(Authentication authentication) {
        User user = userService.getUserByUserName(authentication.getName());
        Set<Role> roleList = user.getRoleList();

        List<FontedRouterInfo> routerInfoList = new ArrayList<>();
        for (Role role: roleList) {
            switch (role.getRoleName()) {
                case "ROLE_USER":
                    routerInfoList.add(new FontedRouterInfo("/home", "主页", "home", "fill"));
                    routerInfoList.add(new FontedRouterInfo("/problem", "题目", "book", "fill"));
                    routerInfoList.add(new FontedRouterInfo("/status", "记录", "pie-chart", "fill"));
                    routerInfoList.add(new FontedRouterInfo("/user/rank", "排行榜", "pie-chart", "fill"));
                    break;
                case "ROLE_CRAZY_USER":
                    routerInfoList.add(new FontedRouterInfo("/statistics", "统计", "filter", "fill"));
                    break;
                case "ROLE_SUPER_USER":
                    routerInfoList.add(new FontedRouterInfo("/problem/new", "创建", "folder-add", "fill"));
                    break;
                case "ROLE_ADMIN":
                    routerInfoList.add(new FontedRouterInfo("/authority", "授权", "crown", "fill"));
                    break;
                default:
            }
        }

        return ResultUtil.success(routerInfoList);
    }

}
