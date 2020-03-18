package me.xiongxuan.xoj.service;


import me.xiongxuan.xoj.entity.JudgeStatus;
import me.xiongxuan.xoj.entity.Problem;
import me.xiongxuan.xoj.entity.User;
import me.xiongxuan.xoj.entity.request.JudgeStatusFilterRequest;
import me.xiongxuan.xoj.repository.JudgeStatusRepository;
import me.xiongxuan.xoj.repository.UserRepository;
import org.hibernate.internal.util.StringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Created by XiongXuan on 2018/4/26.
 */
@Service
public class JudgeStatusService {
    @Autowired
    private JudgeStatusRepository judgeStatusRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public List<JudgeStatus> getAllJudgeStatus(int page, int size) {
        Sort.Order order=new Sort.Order(Sort.Direction.DESC,"id");
        Sort sort=new Sort(order);

        Pageable pageable=new PageRequest(page,size,sort);
        Page<JudgeStatus> all = judgeStatusRepository.findAll(pageable);

        List<JudgeStatus> list=new ArrayList<JudgeStatus>();
        for(JudgeStatus judgeStatus:all){
            list.add(judgeStatus);
        }
        return list;
    }

    @Transactional
    public List<JudgeStatus> getAllJudgeStatus() {
        return judgeStatusRepository.findAll();
    }

    @Transactional
    public List<JudgeStatus> getJudgeStatusByUserId(Integer userId) {
        List<JudgeStatus> allByUserName = judgeStatusRepository.findAllByUser(new User(userId));
        return allByUserName;
    }

    @Transactional
    public List<JudgeStatus> getJudgeStatusByProblemId(Integer problemId) {
        List<JudgeStatus> allByProblemId = judgeStatusRepository.findAllByProblem(new Problem(problemId));
        return allByProblemId;
    }

    @Transactional
    public JudgeStatus createJudgeStatus(JudgeStatus judgeStatus) {
        return judgeStatusRepository.save(judgeStatus);
    }

    @Transactional
    public JudgeStatus updateJudgeStatus(JudgeStatus judgeStatus) {
        return judgeStatusRepository.save(judgeStatus);
    }

    @Transactional
    public JudgeStatus getJudgeStatusById(Integer id) {
        return judgeStatusRepository.findByJudgeStatusId(id);
    }

    @Transactional
    public List<JudgeStatus> getJudgeStatusByUserIdBeforeDays(Integer userId, Integer days) {
        return judgeStatusRepository.findAllByUserAndCreateTimeAfter(new User(userId), LocalDateTime.now().minusDays(days));
    }

    @Transactional
    public List<JudgeStatus> getAcceptedJudgeStatusByUserIdBeforeDays(Integer userId, Integer days) {
        return judgeStatusRepository.findAllByUserAndScoreEqualsAndCreateTimeAfter(new User(userId), 100,  LocalDateTime.now().minusDays(days));
    }

    @Transactional
    public Integer getCountByUserIdBeforeDays(Integer userId, Integer days) {
        return judgeStatusRepository.countAllByUserAndCreateTimeAfter(new User(userId), LocalDateTime.now().minusDays(days));
    }

    @Transactional
    public Integer getAcceptedCountByUserIdBeforeDays(Integer userId, Integer days) {
        List<JudgeStatus> list = judgeStatusRepository.getAllByUserAndScoreEqualsAndCreateTimeAfter(new User(userId), 100, LocalDateTime.now().minusDays(days));
        Set<Integer> distinctList = new HashSet<>();
        for (JudgeStatus judgeStatus: list) {
            distinctList.add(judgeStatus.getProblem().getProblemId());
        }
        return distinctList.size();
    }

    @Transactional
    public List<Integer> getAcceptedProblemIdByUser(User user) {
        return judgeStatusRepository.findAcceptedProblemIdByUser(user.getUserId());
    }

    @Transactional
    public List<Integer> getAcceptedProblemIdByUser(Integer userId) {
        return judgeStatusRepository.findAcceptedProblemIdByUser(userId);
    }

    @Transactional
    public boolean isAccept(User user, Problem problem) {
        return judgeStatusRepository.countAllByUserAndProblemAndScoreEquals(user, problem, 100) > 0;
    }

    @Transactional
    public boolean isAccept(Integer userId, Integer problemId) {
        return isAccept(new User(userId), new Problem(problemId));
    }

    @Transactional
    public Page<JudgeStatus> getJudgeStatusByFilterRequest(JudgeStatusFilterRequest request) {
        Specification<JudgeStatus> specification= (root, query, cb) -> {
            Predicate predicate = cb.conjunction();
            if (request.getUserName() != null && StringHelper.isNotEmpty(request.getUserName())) {
                User user = userRepository.findUserByUserName(request.getUserName());
                predicate.getExpressions().add(cb.equal(root.get("user"), user));
            }

            if (request.getProblemId() != null) {
                predicate.getExpressions().add(cb.equal(root.get("problem"), new Problem(request.getProblemId())));
            }

            if (request.getError() != null) {
                if (request.getError() == 4) {
                    predicate.getExpressions().add(cb.equal(root.get("score"), 100));
                }
                else {
                    predicate.getExpressions().add(cb.equal(root.get("error"), request.getError()));
                }
            }

            return predicate;
        };

        Sort sort = new Sort(Sort.Direction.DESC, "judgeStatusId");
        Pageable pageable = PageRequest.of(request.getPage(), request.getRows(), sort);
        return judgeStatusRepository.findAll(specification, pageable);
    }
}
