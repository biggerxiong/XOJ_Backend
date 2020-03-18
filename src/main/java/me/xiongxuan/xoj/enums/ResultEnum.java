package me.xiongxuan.xoj.enums;

/**
 * Created by XiongXuan on 2018/1/8.
 */
public enum  ResultEnum {
    ERROR_UNKNOW(-1, "位置错误"),
    SUCCESS(1, "成功"),

    // 注册错误
    ERROR_REGISTER_NO_SMS(2, "没有发送短信验证码"),
    ERROR_REGISTER_WRONG_SMS_CODE(3, "短信验证码错误"),
    ERROR_REGISTER_SMS_TIME_LIMIT(4, "没有在有效时间内输入验证码，请重新获取验证码"),
    ERROR_REGISTER_DUPLICATE_PHONE_NUMBER(5, "该手机号码已经注册过了，请直接登录"),

    // 登录错误
    ERROR_LOGIN_NO_PHONENUMBER(6, "该帐号没有注册，请先注册"),
    ERROR_LOGIN_WRONG_PASSWORD(7, "帐号或密码错误"),
    ERROR_LOGIN_FIRST(8, "请先登陆"),

    // 项目错误
    ERROR_REQUEST_LIMITED(9, "你的操作太频繁了，休息一会再试"),

    // 修改个人信息错误
    ERROR_USER_METHOD_ILLEGAL(13, "非法访问！请通过 个人中心-修改信息 来修改您的信息"),
    ERROR_USER_TYPE_MODIFYED(14, "您修改了您的账户类型，该类型需管理员审核后方可生效，请等待"),

    // 题目编辑错误
    ERROR_PROBLEM_DATA_NULL(15, "上传的题目为空，请重新上传"),
    ERROR_PROBLEM_DATA_NAME_LENGTH_LIMIT(16, "文件名过长"),
    ERROR_PROBLEM_DATA_DELETE_FAILED(17, "文件io失败"),

    // 更新权限错误
    ERROR_AUTH_UPDATE_NO_OPTION(20, "无对应option"),
    ERROR_AUTH_UPDATE_NO_NAME(21, "无对应权限名"),
    ;

    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    ResultEnum(int code, String msg) {

        this.code = code;
        this.msg = msg;
    }
}
