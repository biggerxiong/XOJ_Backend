package me.xiongxuan.xoj.repository;

import me.xiongxuan.xoj.entity.JudgeStatus;
import me.xiongxuan.xoj.entity.Problem;
import me.xiongxuan.xoj.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author XiongXuan
 */
public interface JudgeStatusRepository extends JpaRepository<JudgeStatus,Integer>, JpaSpecificationExecutor<JudgeStatus> {

    /**
     * 找该UserId所有的评测记录
     * @param user 用户id
     * @return 该用户所有的评测记录
     */
    List<JudgeStatus> findAllByUser(User user);

    /**
     * 通过judgeStatusId找
     * @param id 评测id
     * @return 对应的JudgeStatus
     */
    JudgeStatus findByJudgeStatusId(Integer id);

    /**
     * 通过题目id找所有评测记录
     * @param problem 题目id
     * @return 该题目所有的评测id
     */
    List<JudgeStatus> findAllByProblem(Problem problem);

    /**
     * 通过题目id和用户id找
     * @param problem 题目id
     * @param user 用户id
     * @return 该用户在该题目上的所有评测id
     */
    List<JudgeStatus> findAllByProblemAndUser(Problem problem, User user);

    /**
     * 通过用户id找日期内的评测记录
     * @param user 用户id
     * @param localDateTime 时间
     * @return 该时间内此用户的所有提交
     */
    List<JudgeStatus> findAllByUserAndCreateTimeAfter(User user, LocalDateTime localDateTime);

    /**
     * 查找该用户所有通过的题目编号
     * @param userId
     * @return
     */
    @Query(value = "select distinct(problem_id) from oj_judge_status where user_id=:userId and score=100", nativeQuery = true)
    List<Integer> findAcceptedProblemIdByUser(Integer userId);

    /**
     * 通过用户id和分数找日期内的评测记录
     * @param user 用户id
     * @param score 分数
     * @param localDateTime 时间
     * @return 该时间内此用户特定得分的所有提交
     */
    List<JudgeStatus> findAllByUserAndScoreEqualsAndCreateTimeAfter(User user, Integer score, LocalDateTime localDateTime);

    Integer countAllByUserAndProblemAndScoreEquals(User user, Problem problem, Integer score);

    List<JudgeStatus> getAllByUserAndScoreEqualsAndCreateTimeAfter(User user, Integer score, LocalDateTime localDateTime);

    Integer countAllByUserAndCreateTimeAfter(User user, LocalDateTime localDateTime);

}
