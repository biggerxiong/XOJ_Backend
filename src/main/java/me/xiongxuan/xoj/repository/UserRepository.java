package me.xiongxuan.xoj.repository;

import me.xiongxuan.xoj.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author XiongXuan
 */
public interface UserRepository extends JpaRepository<User,Integer> {

    User findUserByUserId(Integer id);

    User findUserByUserName(String userName);
}
