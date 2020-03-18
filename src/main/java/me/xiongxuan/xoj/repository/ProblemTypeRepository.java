package me.xiongxuan.xoj.repository;

import me.xiongxuan.xoj.entity.ProblemType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProblemTypeRepository extends JpaRepository<ProblemType,Integer> {
}
