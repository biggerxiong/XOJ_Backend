package me.xiongxuan.xoj.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(locations = {"classpath:application.yml"})
@SpringBootTest
public class ProblemRepositoryTest {

    @Autowired
    ProblemRepository problemRepository;

    @Test
    public void testGetEditProblemDetail() {
        System.out.println(problemRepository.getOne(1001));
        System.out.println();
        System.out.println();
        System.out.println(problemRepository.findProblemByProblemId(1001));
    }
}