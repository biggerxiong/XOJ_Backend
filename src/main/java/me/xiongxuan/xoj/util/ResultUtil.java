package me.xiongxuan.xoj.util;


import me.xiongxuan.xoj.entity.Result;
import me.xiongxuan.xoj.enums.ResultEnum;

/**
 * Created by XiongXuan on 2018/1/2.
 */
public class ResultUtil {

    public static Result success(String msg, Object object) {
        return new Result(1, msg, object);
    }
    public static Result success(Object object) {
        return new Result(1, "", object);
    }

    public static Result success(String msg) {
        return new Result(1, msg, null);
    }

    public static Result error(int code, String msg) {
        return new Result(code, msg, null);
    }

    public static Result getNoObjectInstance(ResultEnum resultEnum) {
        return new Result(resultEnum.getCode(), resultEnum.getMsg(), null);
    }
}
