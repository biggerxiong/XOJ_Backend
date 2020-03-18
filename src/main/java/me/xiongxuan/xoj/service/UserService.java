package me.xiongxuan.xoj.service;

import me.xiongxuan.xoj.entity.User;
import me.xiongxuan.xoj.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @author XiongXuan
 */
@Service
@Transactional
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(Integer userId) {
        return userRepository.findUserByUserId(userId);
    }

    public User getUserByUserName(String userName) {
        return userRepository.findUserByUserName(userName);
    }

    public boolean login(User user) {
        User userDb = userRepository.findUserByUserName(user.getUserName());
        return userDb.getPassword().equals(user.getPassword());
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findUserByUserName(s);
        if (user == null) {
            throw new UsernameNotFoundException("User Not Found");
        }
        return user;
    }

    @Transactional
    public void acceptIncrease(User user) {
        user.setAccept(user.getAccept() + 1);
        userRepository.save(user);
    }

    @Transactional
    public void acceptIncrease(Integer userId) {
        User user = userRepository.findUserByUserId(userId);
        acceptIncrease(user);
    }

    @Transactional
    public void submitIncrease(User user) {
        System.out.println("submitIncrease: " + user);
        user.setSubmit(user.getSubmit() + 1);
        userRepository.save(user);
    }

    @Transactional
    public void submitIncrease(Integer userId) {
        User user = userRepository.findUserByUserId(userId);
        submitIncrease(user);
    }

    @Transactional
    public Page<User> getRankList(int page, int rows) {
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.DESC, "accept"));
        orders.add(new Sort.Order(Sort.Direction.ASC, "submit"));
        Pageable pageable = PageRequest.of(page, rows, Sort.by(orders));
        return userRepository.findAll(pageable);
    }
}
