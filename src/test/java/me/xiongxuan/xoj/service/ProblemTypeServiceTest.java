package me.xiongxuan.xoj.service;

import me.xiongxuan.xoj.entity.ProblemType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(locations = {"classpath:application.yml"})
@SpringBootTest
public class ProblemTypeServiceTest {
    @Autowired
    private ProblemTypeService problemTypeService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getProblemTypeById() {
//        ProblemType problemType = new ProblemType();
//        problemType.setProblemTypeId(1);
//        problemType.setProblemTypeName("XOJ");
//        problemType.setProblemTypeDescription("本地OJ");
//
//        System.out.println(problemTypeService.getProblemTypeById(1));
    }

    @Test
    public void createProblemType() {
//        ProblemType problemType = new ProblemType();
////        problemType.setProblemTypeName("QDUOJ");
////        problemType.setProblemTypeDescription("青岛大学OJ");
//        problemType.setProblemTypeName("XOJ");
//        problemType.setProblemTypeDescription("本地OJ");
//
//        System.out.println(problemTypeService.createProblemType(problemType));
    }
}