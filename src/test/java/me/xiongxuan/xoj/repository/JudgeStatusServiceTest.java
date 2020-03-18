package me.xiongxuan.xoj.repository;

import me.xiongxuan.xoj.entity.request.JudgeStatusFilterRequest;
import me.xiongxuan.xoj.service.JudgeStatusService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(locations = {"classpath:application.yml"})
@SpringBootTest
public class JudgeStatusServiceTest {

    @Autowired
    JudgeStatusService judgeStatusService;

    @Test
    public void testGetByFilterRequest() {
        JudgeStatusFilterRequest request = new JudgeStatusFilterRequest();
        request.setUserName("xiongxuan");
        request.setPage(1);
        request.setRows(5);
        System.out.println(judgeStatusService.getJudgeStatusByFilterRequest(request));
    }
}