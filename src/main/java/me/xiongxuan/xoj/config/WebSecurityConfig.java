package me.xiongxuan.xoj.config;

import me.xiongxuan.xoj.filter.JWTAuthenticationFilter;
import me.xiongxuan.xoj.filter.JWTLoginFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author XiongXuan
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)  //  启用方法级别的权限认证
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
            // 授权配置
        http.authorizeRequests()
            // 允许登陆界面
            .antMatchers("/login").permitAll()
            .antMatchers("/wq/**").permitAll()
            .antMatchers("/config/**").permitAll()
            // 允许判题结果更新界面，for judge server
            .antMatchers(HttpMethod.POST, "/status").permitAll()
            .antMatchers(HttpMethod.POST, "/heartBeat").permitAll()
            .antMatchers(HttpMethod.GET, "/md5/*").permitAll()
            .anyRequest()  // 所有请求
            .authenticated() // 都需要认证
            //前后端分离采用JWT 不需要session
            .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and().csrf().disable()
            .cors()
            // 保证跨域的过滤器首先触发
//                .and().addFilterBefore(new WebSecurityCorsFilter(), ChannelProcessingFilter.class)
            // 添加一个过滤器 所有访问 /login 的请求交给 JWTLoginFilter 来处理 这个类处理所有的JWT相关内容
            .and().addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
                    UsernamePasswordAuthenticationFilter.class)
            // 添加一个过滤器验证其他请求的Token是否合法
            .addFilterBefore(new JWTAuthenticationFilter(),
                    UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
