package me.xiongxuan.xoj.service;

import cn.hutool.json.JSONUtil;
import com.google.gson.Gson;
import io.jsonwebtoken.*;
import me.xiongxuan.xoj.entity.User;
import me.xiongxuan.xoj.util.ResponseUtil;
import me.xiongxuan.xoj.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class TokenAuthenticationService {

    /**
     * 5天
     */
    private static final long EXPIRATIONTIME = 432_000_000;
//    private static final long EXPIRATIONTIME = 10_000;

    /**
     * JWT密码
     */
    private static final String SECRET = "P@ssw02dXOJ";

    /**
     * Token前缀
     */
    private static final String TOKEN_PREFIX = "Bearer";

    /**
     * 存放Token的Header Key
     */
    private static final String HEADER_STRING = "Authorization";

    /**
     * JWT生成方法
     * @param response
     * @param user
     */
    public static void addAuthentication(HttpServletResponse response, User user, Authentication authentication) {
        //获取用户的权限字符串，如 USER,ADMIN
//        String authorities = authentication.getAuthorities().stream()
//                .map(GrantedAuthority::getAuthority)
//                .collect(Collectors.joining(","));

        // 生成JWT
        String JWT = Jwts.builder()
                // 保存权限（角色）
//                .claim("authorities", "ROLE_ADMIN,AUTH_WRITE")
                // 用户名写入标题
                .claim("userId", user.getUserId())
                .claim("expiredTime", new Date(System.currentTimeMillis() + EXPIRATIONTIME).getTime())
//                .claim("authorities", authorities)
                .setSubject(user.getUserName())
                // 有效期设置
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATIONTIME))
                // 签名设置
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();

        // 将 JWT 写入 body

        ResponseUtil.responseOkJson(response, ResultUtil.success(JWT));

    }

    /**
     * JWT验证方法
     * @param request
     * @return
     */
    public static Authentication getAuthentication(HttpServletRequest request, UserService userService) throws AccountExpiredException{
        // 从Header中拿到token
        String token = request.getHeader(HEADER_STRING);

        if (token != null) {
            // 解析 Token
            Claims claims;
            try {
                claims = Jwts.parser()
                        // 验签
                        .setSigningKey(SECRET)
                        // 去掉 Bearer
                        .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                        .getBody();
            } catch (SignatureException | MalformedJwtException | IllegalArgumentException | ExpiredJwtException e ) {
                return null;
            }

            // 拿用户名
            String user = claims.getSubject();

            // 得到 权限（角色）
            User userObject = userService.getUserByUserName(user);
            Collection<? extends GrantedAuthority> authorities = userObject.getAuthorities();

            // 返回验证令牌
            return user != null ?
                    new UsernamePasswordAuthenticationToken(user, null, authorities) :
                    null;
        }
        return null;
    }

    public static String getAuthenticationUserName(HttpServletRequest request) throws AccountExpiredException{
        // 从Header中拿到token
        String token = request.getHeader(HEADER_STRING);

        if (token != null) {
            // 解析 Token
            Claims claims;
            try {
                claims = Jwts.parser()
                        // 验签
                        .setSigningKey(SECRET)
                        // 去掉 Bearer
                        .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                        .getBody();
            } catch (SignatureException | MalformedJwtException | IllegalArgumentException | ExpiredJwtException e ) {
                return null;
            }

            // 拿用户名
            String user = claims.getSubject();

            // 返回验证令牌
            return user;
        }
        return null;
    }
}