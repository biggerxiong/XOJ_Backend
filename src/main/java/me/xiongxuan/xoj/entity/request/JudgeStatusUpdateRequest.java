package me.xiongxuan.xoj.entity.request;

import java.util.List;

public class JudgeStatusUpdateRequest {
    private List<TestcaseResult> data;
    private String error;
    private Integer id;
    private String errorMsg;

    public List<TestcaseResult> getData() {
        return data;
    }

    public void setData(List<TestcaseResult> data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "JudgeStatusUpdateRequest{" +
                "data=" + data +
                ", error='" + error + '\'' +
                ", id=" + id +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }
}
