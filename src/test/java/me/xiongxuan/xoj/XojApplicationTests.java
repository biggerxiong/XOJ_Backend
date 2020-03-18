package me.xiongxuan.xoj;

import me.xiongxuan.xoj.entity.User;
import me.xiongxuan.xoj.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class XojApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private UserService userService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testRedis() {
        String key = "name";
        redisTemplate.opsForValue().set(key, "xiongxuan");
        // 获取数据
        String value = (String) redisTemplate.opsForValue().get(key);
        System.out.println("获取缓存中key为" + key + "的值为：" + value);

        User user = userService.getUserByUserName("xiongxuan");
        String userKey = "xiongxuan";
        redisTemplate.opsForValue().set(userKey, user);
        User newUser = (User) redisTemplate.opsForValue().get(userKey);
        System.out.println(newUser);
    }
}
