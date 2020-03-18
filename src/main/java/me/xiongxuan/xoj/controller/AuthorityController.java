package me.xiongxuan.xoj.controller;

import me.xiongxuan.xoj.entity.Result;
import me.xiongxuan.xoj.entity.User;
import me.xiongxuan.xoj.entity.request.AuthUpdateRequest;
import me.xiongxuan.xoj.enums.ResultEnum;
import me.xiongxuan.xoj.service.AuthorityService;
import me.xiongxuan.xoj.service.UserService;
import me.xiongxuan.xoj.util.ResultUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("/auth")
public class AuthorityController {

    @Resource
    UserService userService;

    @Resource
    AuthorityService authorityService;

    @PreAuthorize("hasAnyRole('ADMIN', 'ROOT')")
    @PostMapping("/update")
    @ResponseBody
    public Result authManage(@RequestBody AuthUpdateRequest authUpdateRequest, Authentication authentication) {

        User user = userService.getUserById(authUpdateRequest.getUserId());
        if (user == null) {
            return ResultUtil.getNoObjectInstance(ResultEnum.ERROR_LOGIN_NO_PHONENUMBER);
        }

        // 删除权限
        if (authUpdateRequest.getOption() == 0) {
            // 增加角色
            if (authUpdateRequest.getName().startsWith("ROLE_")) {
                user.getRoleList().remove(authorityService.getRoleByName(authUpdateRequest.getName()));
                userService.updateUser(user);
            }
            // 增加权限
            else if (authUpdateRequest.getName().startsWith("AUTH_")) {
                user.getAuthorityList().remove(authorityService.getAuthorityByName(authUpdateRequest.getName()));
                userService.updateUser(user);
            }
            else {
                return ResultUtil.getNoObjectInstance(ResultEnum.ERROR_AUTH_UPDATE_NO_NAME);
            }
        }
        // 增加权限
        else if (authUpdateRequest.getOption() == 1){
            // 增加角色
            if (authUpdateRequest.getName().startsWith("ROLE_")) {
                user.getRoleList().add(authorityService.getRoleByName(authUpdateRequest.getName()));
                userService.updateUser(user);
            }
            // 增加权限
            else if (authUpdateRequest.getName().startsWith("AUTH_")) {
                user.getAuthorityList().add(authorityService.getAuthorityByName(authUpdateRequest.getName()));
                userService.updateUser(user);
            }
            else {
                return ResultUtil.getNoObjectInstance(ResultEnum.ERROR_AUTH_UPDATE_NO_NAME);
            }
        }
        else {
            return ResultUtil.getNoObjectInstance(ResultEnum.ERROR_AUTH_UPDATE_NO_OPTION);
        }
        return ResultUtil.success("修改成功");
    }
}
