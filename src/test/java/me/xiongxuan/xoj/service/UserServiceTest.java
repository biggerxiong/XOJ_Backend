package me.xiongxuan.xoj.service;

import me.xiongxuan.xoj.entity.Role;
import me.xiongxuan.xoj.entity.User;
import me.xiongxuan.xoj.repository.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(locations = {"classpath:application.yml"})
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthorityService authorityService;
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testUser() {
        User user = new User();
        user.setUserName("suhanqi");
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode("123456"));
        user.setNickName("苏汉祺");
        userService.createUser(user);

        authorityService.grantRole("suhanqi", "ROLE_USER");
//        authorityService.grantAuthority("huangwenjie", "AUTH_GET_PROBLEM_DETAIL");
//        authorityService.grantAuthority("huangwenjie", "AUTH_GET_PROBLEM_LIST");
//        authorityService.grantAuthority("huangwenjie", "AUTH_JUDGE_CODE");
//        authorityService.grantAuthority("huangwenjie", "AUTH_GET_JUDGE_STATUS");

//        user = new User();
//        user.setUserName("test");
//        user.setPassword("123456");
//
//
//        User userDb = userService.createUser(user);
//        System.out.println(userDb);
//        System.out.println(userService.getUserById(userDb.getUserId()));
//        System.out.println(userService.getUserByUserName(userDb.getUserName()));
    }

    @Test
    public void testUpdatePassword() {
        User user = userService.getUserByUserName("yangzhenghao");
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode("123456");
        user.setPassword(encodedPassword);
        userService.updateUser(user);
    }
}