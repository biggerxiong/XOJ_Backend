package me.xiongxuan.xoj.service;

import me.xiongxuan.xoj.entity.Authority;
import me.xiongxuan.xoj.entity.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource(locations = {"classpath:application.yml"})
@SpringBootTest
public class AuthorityServiceTest {

    @Resource
    AuthorityService authorityService;

    @Resource
    UserService userService;

    @Test
    public void addAuthority() {
//        authorityService.addAuthority("AUTH_GET_PROBLEM_DETAIL", "获取题目内容的权限");
//        authorityService.addAuthority("AUTH_GET_PROBLEM_LIST", "获取题目列表的权限");
//        authorityService.addAuthority("AUTH_CREATE_PROBLEM", "创建题目的权限");
//        authorityService.addAuthority("AUTH_EDIT_PROBLEM", "编辑题目内容的权限");
//        authorityService.addAuthority("AUTH_UPLOAD_DATA", "上传题目测试数据的权限");
        authorityService.addAuthority("AUTH_JUDGE_CODE", "评测代码的权限");
        authorityService.addAuthority("AUTH_GET_JUDGE_STATUS", "查询评测记录的权限");
    }

    @Test
    public void grantAuthority() {
        authorityService.grantAuthority("tuchenming", "AUTH_GET_PROBLEM_DETAIL");
//        authorityService.grantAuthority("lucifer", "AUTH_GET_PROBLEM_LIST");
//        authorityService.grantAuthority("lucifer", "AUTH_JUDGE_CODE");
//        authorityService.grantAuthority("lucifer", "AUTH_GET_JUDGE_STATUS");
//        authorityService.grantAuthority("lucifer", "AUTH_EDIT_PROBLEM");
//        authorityService.grantAuthority("lucifer", "AUTH_CREATE_PROBLEM");
//        authorityService.grantAuthority("lucifer", "AUTH_UPLOAD_DATA");
    }

    @Test
    public void removeAuthority() {
        authorityService.removeAuthority(111, 1);
    }
    @Test
    public void addRole() {
        authorityService.addRole("ROLE_USER", "普通用户，有获取题目列表、获取题目详细信息、评测代码、查询评测结果的权限");
        authorityService.addRole("ROLE_CRAZY_USER", "高级用户，拥有普通用户拥有的权限，还有查看用户统计状态的权限");
        authorityService.addRole("ROLE_SUPER_USER", "超级用户，拥有普通用户、高级用户拥有的权限，还有创建题目、编辑题目、上传题目数据的权限");
        authorityService.addRole("ROLE_ADMIN", "管理员，拥有用户所有的权限，还有添加用户、授予用户角色的权限");
        authorityService.addRole("ROLE_ROOT", "超级管理员，拥有用户、管理员所有的权限，还有授予管理员角色的权限");
    }

    @Test
    public void grantRole() {
        authorityService.grantRole("lucifer", "ROLE_USER");
        authorityService.grantRole("lucifer", "ROLE_CRAZY_USER");
        authorityService.grantRole("lucifer", "ROLE_SUPER_USER");
        authorityService.grantRole("lucifer", "ROLE_ADMIN");
    }

    @Test
    public void bindRoleAndAuthority() {
        authorityService.bindRoleAndAuthority("ROLE_USER", "AUTH_GET_PROBLEM_DETAIL");
        authorityService.bindRoleAndAuthority("ROLE_USER", "AUTH_GET_PROBLEM_LIST");
        authorityService.bindRoleAndAuthority("ROLE_USER", "AUTH_JUDGE_CODE");
        authorityService.bindRoleAndAuthority("ROLE_USER", "AUTH_GET_JUDGE_STATUS");

        authorityService.bindRoleAndAuthority("ROLE_SUPER_USER", "AUTH_CREATE_PROBLEM");
        authorityService.bindRoleAndAuthority("ROLE_SUPER_USER", "AUTH_EDIT_PROBLEM");
        authorityService.bindRoleAndAuthority("ROLE_SUPER_USER", "AUTH_UPLOAD_DATA");
    }
}