package me.xiongxuan.xoj.repository;

import me.xiongxuan.xoj.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer> {

    Role findByRoleName(String roleName);
}
