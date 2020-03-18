package me.xiongxuan.xoj.entity.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HeartBeatRequest {

    @JsonProperty("judger_version")
    private String judgerVersion;

    private String hostname;

    @JsonProperty("running_task_number")
    private Integer runningTaskNumber;

    @JsonProperty("cpu_core")
    private Integer cpuCore;
    private Double memory;
    private String action;
    private Integer cpu;

    @JsonProperty("service_url")
    private String serviceUrl;

    @Override
    public String toString() {
        return "HeartBeatRequest{" +
                "judgerVersion='" + judgerVersion + '\'' +
                ", hostname='" + hostname + '\'' +
                ", runningTaskNumber=" + runningTaskNumber +
                ", cpuCore=" + cpuCore +
                ", memory=" + memory +
                ", action='" + action + '\'' +
                ", cpu=" + cpu +
                ", serviceUrl='" + serviceUrl + '\'' +
                '}';
    }

    public String getJudgerVersion() {
        return judgerVersion;
    }

    public void setJudgerVersion(String judgerVersion) {
        this.judgerVersion = judgerVersion;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public Integer getRunningTaskNumber() {
        return runningTaskNumber;
    }

    public void setRunningTaskNumber(Integer runningTaskNumber) {
        this.runningTaskNumber = runningTaskNumber;
    }

    public Integer getCpuCore() {
        return cpuCore;
    }

    public void setCpuCore(Integer cpuCore) {
        this.cpuCore = cpuCore;
    }

    public Double getMemory() {
        return memory;
    }

    public void setMemory(Double memory) {
        this.memory = memory;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Integer getCpu() {
        return cpu;
    }

    public void setCpu(Integer cpu) {
        this.cpu = cpu;
    }

    public String getServiceUrl() {
        return serviceUrl;
    }

    public void setServiceUrl(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }

    public HeartBeatRequest() {
    }

    public HeartBeatRequest(String judgerVersion, String hostname, Integer runningTaskNumber, Integer cpuCore, Double memory, String action, Integer cpu, String serviceUrl) {
        this.judgerVersion = judgerVersion;
        this.hostname = hostname;
        this.runningTaskNumber = runningTaskNumber;
        this.cpuCore = cpuCore;
        this.memory = memory;
        this.action = action;
        this.cpu = cpu;
        this.serviceUrl = serviceUrl;
    }
}
