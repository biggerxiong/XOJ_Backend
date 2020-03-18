package me.xiongxuan.xoj.entity.request;

public class JudgeStatusFilterRequest {

    private String userName;

    private Integer problemId;

    /**
     * 状态代码，0为队列中，1为正常，2为编译错误，3为系统错误，4为AC
     */
    private Integer error;

    private Integer page = 0;

    private Integer rows = 30;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getProblemId() {
        return problemId;
    }

    public void setProblemId(Integer problemId) {
        this.problemId = problemId;
    }

    public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
        this.error = error;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }
}
