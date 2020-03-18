package me.xiongxuan.xoj.entity;

import java.io.Serializable;

/**
 * @author XiongXuan
 * 与消息队列通信的实体类
 */
public class MqMessage implements Serializable{

    private static final long serialVersionUID = 1L;

    private Integer judgeStatusId;
    private Integer problemId;
    private Integer languageId;
    private Integer maxCpuTime;
    private Integer maxMemory;
    private String code;

    public MqMessage() {
    }

    @Override
    public String toString() {
        return "MqMessage{" +
                "judgeStatusId=" + judgeStatusId +
                ", problemId=" + problemId +
                ", languageId=" + languageId +
                ", maxCpuTime=" + maxCpuTime +
                ", maxMemory=" + maxMemory +
                ", code='" + code + '\'' +
                '}';
    }

    public Integer getMaxCpuTime() {
        return maxCpuTime;
    }

    public void setMaxCpuTime(Integer maxCpuTime) {
        this.maxCpuTime = maxCpuTime;
    }

    public Integer getMaxMemory() {
        return maxMemory;
    }

    public void setMaxMemory(Integer maxMemory) {
        this.maxMemory = maxMemory;
    }

    public MqMessage(Integer judgeStatusId, Integer problemId, Integer languageId, Integer maxCpuTime, Integer maxMemory, String code) {
        this.judgeStatusId = judgeStatusId;
        this.problemId = problemId;
        this.languageId = languageId;
        this.maxCpuTime = maxCpuTime;
        this.maxMemory = maxMemory;
        this.code = code;
    }

    public MqMessage(Integer judgeStatusId, Integer problemId, Integer languageId, String code) {
        this.judgeStatusId = judgeStatusId;
        this.problemId = problemId;
        this.languageId = languageId;
        this.code = code;
    }

    public Integer getJudgeStatusId() {
        return judgeStatusId;
    }

    public void setJudgeStatusId(Integer judgeStatusId) {
        this.judgeStatusId = judgeStatusId;
    }

    public Integer getProblemId() {
        return problemId;
    }

    public void setProblemId(Integer problemId) {
        this.problemId = problemId;
    }

    public Integer getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Integer languageId) {
        this.languageId = languageId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
