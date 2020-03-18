package me.xiongxuan.xoj.repository;

import me.xiongxuan.xoj.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority,Integer> {

    Authority findByAuthorityName(String authorityName);
}
