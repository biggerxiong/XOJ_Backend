package me.xiongxuan.xoj.util;

import com.google.gson.Gson;
import me.xiongxuan.xoj.entity.Result;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResponseUtil {

    public static void responseOkJson(HttpServletResponse response, Result result) {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        response.setCharacterEncoding("UTF-8");
        try {
            response.getOutputStream().write(new Gson().toJson(result).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void responseUnauthorizedJson(HttpServletResponse response, Result result) {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setCharacterEncoding("UTF-8");
        try {
            response.getOutputStream().write(new Gson().toJson(result).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
