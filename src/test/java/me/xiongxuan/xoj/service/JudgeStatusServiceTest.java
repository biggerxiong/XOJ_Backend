package me.xiongxuan.xoj.service;

import me.xiongxuan.xoj.entity.JudgeStatus;
import me.xiongxuan.xoj.entity.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(locations = {"classpath:application.yml"})
@SpringBootTest
public class JudgeStatusServiceTest {
    @Autowired
    private JudgeStatusService judgeStatusService;

    @Autowired
    private UserService userService;
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getJudgeStatusByUserIdBeforeDays() {
        List<JudgeStatus> judgeStatusList = judgeStatusService.getJudgeStatusByUserIdBeforeDays(2, 1);
        for(JudgeStatus judgeStatus: judgeStatusList) {

            System.out.println(judgeStatus);
        }
    }

    @Test
    public void getAcceptedJudgeStatusByUserIdBeforeDays() {
        List<JudgeStatus> judgeStatusList = judgeStatusService.getAcceptedJudgeStatusByUserIdBeforeDays(2, 1);
        for(JudgeStatus judgeStatus: judgeStatusList) {

            System.out.println(judgeStatus);
        }
    }

    @Test
    public void updateUserAcceptedCount() {
        for (int userid = 105; userid <= 119; userid++) {
            System.out.println("current: " + userid);
            User user = userService.getUserById(userid);
            user.setAccept(judgeStatusService.getAcceptedCountByUserIdBeforeDays(userid, 100));
            userService.updateUser(user);
        }
//        System.out.println(judgeStatusService.getAcceptedCountByUserIdBeforeDays(2, 100));
    }


    @Test
    public void isAccept() {
        System.out.println(judgeStatusService.isAccept(2, 1001));
        System.out.println(judgeStatusService.isAccept(1, 1001));
    }

    @Test
    public void getAcceptedCountByUserIdBeforeDays() {
        System.out.println(judgeStatusService.getAcceptedCountByUserIdBeforeDays(2, 100));
        System.out.println(judgeStatusService.getCountByUserIdBeforeDays(2, 100));
    }

    @Test
    public void getAcceptedProblemIdListByUserId() {
        System.out.println(judgeStatusService.getAcceptedProblemIdByUser(2));
    }
}