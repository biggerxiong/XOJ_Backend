package me.xiongxuan.xoj.entity.request;

public class AuthUpdateRequest {

    /**
     * 目标用户的id
     */
    private Integer userId;

    /**
     * 操作类型，1是添加，0是删除
     */
    private Integer option;

    /**
     * 权限名或角色名
     */
    private String name;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getOption() {
        return option;
    }

    public void setOption(Integer option) {
        this.option = option;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "AuthUpdateRequest{" +
                "userId=" + userId +
                ", option=" + option +
                ", name='" + name + '\'' +
                '}';
    }
}
