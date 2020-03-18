package me.xiongxuan.xoj.filter;

import cn.hutool.json.JSONUtil;
import com.google.gson.Gson;
import me.xiongxuan.xoj.entity.User;
import me.xiongxuan.xoj.enums.ResultEnum;
import me.xiongxuan.xoj.service.TokenAuthenticationService;
import me.xiongxuan.xoj.service.UserService;
import me.xiongxuan.xoj.util.ResultUtil;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author XiongXuan
 */
public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

    private UserService userService;

    public JWTLoginFilter(String url, AuthenticationManager authManager) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
    }

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest req, HttpServletResponse res)
            throws AuthenticationException, IOException, ServletException {

        // JSON反序列化成 AccountCredentials
//        User creds = new ObjectMapper().readValue(req.getInputStream(), User.class);
        User creds = JSONUtil.toBean(charReader(req), User.class);

        // 返回一个验证令牌
        return getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(
                        creds.getUserName(),
                        creds.getPassword()
                )
        );
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest req,
            HttpServletResponse res, FilterChain chain,
            Authentication auth) throws IOException, ServletException {

        if (userService == null) {
            AbstractApplicationContext cxt = (AbstractApplicationContext) WebApplicationContextUtils.getWebApplicationContext(req.getSession().getServletContext());
            assert cxt != null;
            userService = (UserService) cxt.getBean("userService");
        }
        TokenAuthenticationService.addAuthentication(res, userService.getUserByUserName(auth.getName()), auth);
    }


    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {

        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        PrintWriter out = response.getWriter();
        out.println(new Gson().toJson(ResultUtil.getNoObjectInstance(ResultEnum.ERROR_LOGIN_WRONG_PASSWORD)));
    }

    private String charReader(HttpServletRequest request) {
        BufferedReader br = null;
        try {
            br = request.getReader();
            String str;
            StringBuilder wholeStr = new StringBuilder();
            while((str = br.readLine()) != null){
                wholeStr.append(str);
            }
            return wholeStr.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
