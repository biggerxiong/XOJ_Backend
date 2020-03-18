package me.xiongxuan.xoj.entity.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import me.xiongxuan.xoj.entity.User;

public class BoardRespose {

    @JsonIgnoreProperties(value = { "inviter" })
    public User user;

    public Integer score;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "BoardRespose{" +
                "user=" + user +
                ", score=" + score +
                '}';
    }
}
