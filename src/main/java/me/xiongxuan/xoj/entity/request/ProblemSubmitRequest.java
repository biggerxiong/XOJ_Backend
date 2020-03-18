package me.xiongxuan.xoj.entity.request;

public class ProblemSubmitRequest {

    private String source;
    private Integer problemId;
    private Integer languageId;

    public ProblemSubmitRequest() {
    }

    public ProblemSubmitRequest(String source, Integer problemId, Integer languageId) {
        this.source = source;
        this.problemId = problemId;
        this.languageId = languageId;
    }

    @Override
    public String toString() {
        return "ProblemSubmitRequest{" +
                "source='" + source + '\'' +
                ", problemId=" + problemId +
                ", languageId=" + languageId +
                '}';
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
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
}
