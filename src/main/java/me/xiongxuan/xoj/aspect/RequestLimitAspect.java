package me.xiongxuan.xoj.aspect;

import me.xiongxuan.xoj.annotation.RequestLimit;
import me.xiongxuan.xoj.enums.ResultEnum;
import me.xiongxuan.xoj.service.TokenAuthenticationService;
import me.xiongxuan.xoj.util.ResponseUtil;
import me.xiongxuan.xoj.util.ResultUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

@Component
@Aspect
public class RequestLimitAspect {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Around("execution(public * me.xiongxuan.xoj.controller.*.*(..)) && @annotation(limit)")
    public Object requestLimit(ProceedingJoinPoint joinPoint, RequestLimit limit) throws Throwable {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse response = attributes.getResponse();

        String userName = TokenAuthenticationService.getAuthenticationUserName(request);
        if (userName == null) {
            return null;
        }

        //加1后看看值
        long count = redisTemplate.opsForValue().increment(userName, 1);
        //刚创建
        if (count == 1) {
            //设置过期时间
            redisTemplate.expire(userName, limit.time(), TimeUnit.MILLISECONDS);
        }
        if (count > limit.count()) {
            ResponseUtil.responseOkJson(response, ResultUtil.getNoObjectInstance(ResultEnum.ERROR_REQUEST_LIMITED));
            return null;
        }
        return joinPoint.proceed();
    }

}
