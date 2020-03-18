package me.xiongxuan.xoj.controller.wq;

import cn.hutool.core.util.ReUtil;
import me.xiongxuan.xoj.entity.Result;
import me.xiongxuan.xoj.entity.User;
import me.xiongxuan.xoj.enums.ResultEnum;
import me.xiongxuan.xoj.service.UserService;
import me.xiongxuan.xoj.util.ResultUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author XiongXuan
 */
@Controller
@RequestMapping("/wq")
public class WqUserController {

    @Resource
    private UserService userService;

    @ResponseBody
    @PostMapping("/register")
    private Result userRegister(@RequestBody String openid) {
        System.out.println("openid" + openid);
        return ResultUtil.success(openid);
    }

    @ResponseBody
    @PostMapping("/bind")
    public Result userBind(@RequestBody WqUserQueryRequest request) {
        System.out.println(request);
        User user = userService.getUserByUserName(request.getUserName());
        if (user != null && user.getPassword().equals(request.getPassword())) {
            return ResultUtil.success(user);
        } else {
            return ResultUtil.getNoObjectInstance(ResultEnum.ERROR_LOGIN_WRONG_PASSWORD);
        }
    }
}

class WqUserQueryRequest {
    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "WqUserQueryRequest{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}