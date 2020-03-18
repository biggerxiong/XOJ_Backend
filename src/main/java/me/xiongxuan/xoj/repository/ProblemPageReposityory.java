package me.xiongxuan.xoj.repository;


import me.xiongxuan.xoj.entity.Problem;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProblemPageReposityory extends PagingAndSortingRepository<Problem,Integer> {
}
