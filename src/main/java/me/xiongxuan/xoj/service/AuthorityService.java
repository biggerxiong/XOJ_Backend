package me.xiongxuan.xoj.service;

import me.xiongxuan.xoj.entity.Authority;
import me.xiongxuan.xoj.entity.Role;
import me.xiongxuan.xoj.entity.User;
import me.xiongxuan.xoj.repository.AuthorityRepository;
import me.xiongxuan.xoj.repository.RoleRepository;
import me.xiongxuan.xoj.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class AuthorityService {

    @Resource
    private AuthorityRepository authorityRepository;

    @Resource
    private UserRepository userRepository;

    @Resource
    private RoleRepository roleRepository;

    public Authority getAuthorityByid(Integer authorityId) {
        return authorityRepository.getOne(authorityId);
    }

    public Authority getAuthorityByName(String authorityName) {
        return authorityRepository.findByAuthorityName(authorityName);
    }

    public Authority addAuthority(Authority authority) {
        return authorityRepository.save(authority);
    }

    public Authority updateAuthority(Authority authority) {
        return authorityRepository.save(authority);
    }

    public Authority addAuthority(String authorityName, String description) {
        Authority authority = new Authority();
        authority.setAuthorityName(authorityName);
        authority.setDescription(description);
        return authorityRepository.save(authority);
    }

    public Authority updateAuthority(Integer id, String authorityName, String description) {
        Authority authority = new Authority();
        authority.setId(id);
        authority.setAuthorityName(authorityName);
        authority.setDescription(description);
        return authorityRepository.save(authority);
    }

    public User grantAuthority(String userName, String authorityName) {
        User user = userRepository.findUserByUserName(userName);
        Authority authority = authorityRepository.findByAuthorityName(authorityName);
        return grantAuthority(user, authority);
    }

    public User grantAuthority(User user, Authority authority) {
        user.getAuthorityList().add(authority);
        return userRepository.save(user);
    }

    public User grantAuthority(Integer userId, Integer authorityId) {
        User user = userRepository.getOne(userId);
        Authority authority = authorityRepository.getOne(authorityId);
        return grantAuthority(user, authority);
    }

    public User removeAuthority(User user, Authority authority) {
//        List<Authority> newAuthoritys = new ArrayList<>();
//        for (Authority authorityTemp: user.getAuthorityList()) {
//            if (!authorityTemp.getId().equals(authority.getId())) {
//                newAuthoritys.add(authorityTemp);
//            }
//        }
//
//        user.setAuthorityList(newAuthoritys);
        user.getAuthorityList().remove(authority);
        return userRepository.save(user);
    }

    public User removeAuthority(Integer userId, Integer authorityId) {
        User user = userRepository.getOne(userId);
        Authority authority = authorityRepository.getOne(authorityId);
        return removeAuthority(user, authority);
    }

    public Role addRole(String roleName, String description) {
        Role role = new Role();
        role.setRoleName(roleName);
        role.setDescription(description);
        return roleRepository.save(role);
    }

    public Role updateRole(Role role) {
        return roleRepository.save(role);
    }

    public Role addRole(String roleName, String description, Set<Authority> authorities) {
        Role role = new Role();
        role.setRoleName(roleName);
        role.setDescription(description);
        role.setAuthorities(authorities);
        return roleRepository.save(role);
    }

    public User grantRole(String userName, String roleName) {
        User user = userRepository.findUserByUserName(userName);
        Role role = roleRepository.findByRoleName(roleName);
        return grantRole(user, role);
    }

    public User grantRole(User user, Role role) {
        user.getRoleList().add(role);
        user.getAuthorityList().addAll(role.getAuthorities());
        return userRepository.save(user);
    }

    public User grantRole(Integer userId, Integer roleId) {
        User user = userRepository.getOne(userId);
        Role role = roleRepository.getOne(roleId);
        return grantRole(user, role);
    }

    public User removeRole(User user, Role role) {
        user.getRoleList().remove(role);
        user.getAuthorityList().removeAll(role.getAuthorities());
        return userRepository.save(user);
    }

    public User removeRole(Integer userId, Integer roleId) {
        User user = userRepository.getOne(userId);
        Role role = roleRepository.getOne(roleId);
        return removeRole(user, role);
    }

    public Role getRoleByid(Integer roleId) {
        return roleRepository.getOne(roleId);
    }

    public Role getRoleByName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }

    public Role bindRoleAndAuthority(Role role, Authority authority) {
        role.getAuthorities().add(authority);
        return roleRepository.save(role);
    }

    public Role bindRoleAndAuthority(String roleName, String authorityName) {
        Role role = roleRepository.findByRoleName(roleName);
        Authority authority = authorityRepository.findByAuthorityName(authorityName);
        return bindRoleAndAuthority(role, authority);
    }

    public Role bindRoleAndAuthority(Integer roleId, Integer authorityId) {
        Role role = roleRepository.getOne(roleId);
        Authority authority = authorityRepository.getOne(authorityId);
        return bindRoleAndAuthority(role, authority);
    }
}
