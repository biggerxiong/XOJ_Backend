package me.xiongxuan.xoj.service;

import me.xiongxuan.xoj.entity.Problem;
import me.xiongxuan.xoj.repository.ProblemPageReposityory;
import me.xiongxuan.xoj.repository.ProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by XiongXuan on 2018/4/26.
 */


@Service
public class ProblemService {


    @Autowired
    private ProblemPageReposityory problemPageReposityory;

    @Autowired
    private ProblemRepository problemRepository;


    @Transactional
    public List<Problem> getVisiableProblemList() {
        return problemRepository.findProblemsByProblemVisiable(1);
    }


    @Transactional
    public List<Problem> getProblemList() {
        return problemRepository.findAll();
    }

    @Transactional
    public Problem getProblemById(Integer id) {
        return problemRepository.findProblemByProblemId(id);
    }

    @Transactional
    public Problem getTotalProblemById(Integer id) {
        return problemRepository.getOne(id);
    }


    @Transactional
    public Problem getEditProblemById(Integer id) {
        return problemRepository.findEditProblemByProblemId(id);
    }

    @Transactional
    public Problem createProblem(Problem problem) {
        return problemRepository.save(problem);
    }

    @Transactional
    public Problem submitIncrease(Integer id) {
        Problem problem = problemRepository.findProblemByProblemId(id);
        return submitIncrease(problem);
    }

    @Transactional
    public Problem submitIncrease(Problem problem) {
        problem.setSubmit(problem.getSubmit() + 1);
        return problemRepository.save(problem);
    }

    @Transactional
    public Problem acceptIncrease(Integer id) {
        Problem problem = problemRepository.findProblemByProblemId(id);
        return acceptIncrease(problem);
    }

    @Transactional
    public Problem acceptIncrease(Problem problem) {
        problem.setAccept(problem.getAccept() + 1);
        return problemRepository.save(problem);
    }

    @Transactional
    public Problem updateProblem(Problem problem) {
        return problemRepository.save(problem);
    }
}
