package me.xiongxuan.xoj.service;

import me.xiongxuan.xoj.entity.ProblemType;
import me.xiongxuan.xoj.repository.ProblemTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author XiongXuan
 */
@Service
public class ProblemTypeService {

    @Autowired
    private ProblemTypeRepository problemTypeRepository;

    @Transactional
    public ProblemType getProblemTypeById(Integer id) {
        return problemTypeRepository.getOne(id);
    }

    @Transactional
    public ProblemType createProblemType(ProblemType problemType) {
        return problemTypeRepository.save(problemType);
    }
}
