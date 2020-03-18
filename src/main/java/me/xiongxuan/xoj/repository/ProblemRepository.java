package me.xiongxuan.xoj.repository;


import me.xiongxuan.xoj.entity.Problem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProblemRepository extends JpaRepository<Problem,Integer> {

    @Query("select new me.xiongxuan.xoj.entity.Problem(p.problemId, p.createBy, p.title, p.description, p.inputDescription, p.outputDescription, p.samples, p.submit, p.accept, p.timeLimit, p.memoryLimit, p.hint, p.problemType, p.createTime)" +
            "from Problem as p where p.problemId=?1")
    Problem findProblemByProblemId(Integer id);

    @Query("select new me.xiongxuan.xoj.entity.Problem(p.problemId, p.createBy, p.title, p.descriptionMarkdown, p.inputDescriptionMarkdown, p.outputDescriptionMarkdown, p.samples, p.timeLimit, p.memoryLimit, p.hintMarkdown, p.problemType, p.remoteProblemId, p.problemVisiable)" +
            "from Problem as p where p.problemId=?1")
    Problem findEditProblemByProblemId(Integer id);

    @Query("select new me.xiongxuan.xoj.entity.Problem(p.problemId, p.title, p.submit, p.accept, p.problemType)" +
            "from Problem as p where p.problemVisiable=?1")
    List<Problem> findProblemsByProblemVisiable(Integer visiable);

}
