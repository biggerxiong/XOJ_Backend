package me.xiongxuan.xoj.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

/**
 * @author XiongXuan
 */
@Entity
@Table(name = "oj_user")
//@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class)
public class User implements UserDetails {

    @Id
    @TableGenerator( name = "AppSeqStore", allocationSize = 1 )
    @GeneratedValue( strategy = GenerationType.TABLE, generator = "AppSeqStore" )
    private Integer userId;

    @Column(length = 25, nullable = false)
    private String userName;

    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private Integer accept = 0;

    private Integer submit = 0;

    private String nickName;

//    @ManyToOne(cascade = {CascadeType.REFRESH},fetch = FetchType.LAZY)
//    private User inviter;

    /**
     * 多对多映射，用户权限
     */
    @ManyToMany(cascade = {CascadeType.REFRESH},fetch = FetchType.LAZY)
    private Set<Authority> authorityList;

    /**
     * 多对多映射，用户角色
     */
    @ManyToMany(cascade = {CascadeType.REFRESH},fetch = FetchType.LAZY)
    private Set<Role> roleList;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        Set<Authority> authorityList = this.getAuthorityList();
        Set<Role> roleList = this.getRoleList();
        // 加入权限
        for (Authority authority: authorityList) {
            authorities.add(new SimpleGrantedAuthority(authority.getAuthorityName()));
        }

        // 加入角色
        for (Role role: roleList) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        return authorities;
    }

    public User() {
    }

    public User(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAccept() {
        return accept;
    }

    public void setAccept(Integer accept) {
        this.accept = accept;
    }

    public Integer getSubmit() {
        return submit;
    }

    public void setSubmit(Integer submit) {
        this.submit = submit;
    }

    public Set<Authority> getAuthorityList() {
        return authorityList;
    }

    public void setAuthorityList(Set<Authority> authorityList) {
        this.authorityList = authorityList;
    }

    public Set<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(Set<Role> roleList) {
        this.roleList = roleList;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", accept=" + accept +
                ", submit=" + submit +
                ", nickName='" + nickName + '\'' +
                ", authorityList=" + authorityList +
                ", roleList=" + roleList +
                '}';
    }


    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

//    public User getInviter() {
//        return inviter;
//    }
//
//    public void setInviter(User inviter) {
//        this.inviter = inviter;
//    }
}
