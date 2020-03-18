package me.xiongxuan.xoj.entity.request;

public class ProblemEditRequest {

    private Integer problemId;
    private String title;
    private Integer timeLimit;
    private Integer memoryLimit;
    private String descriptionMarkdown;
    private String inputDescriptionMarkdown;
    private String outputDescriptionMarkdown;
    private String samples;
    private String hintMarkdown;

    public Integer getProblemId() {
        return problemId;
    }

    public void setProblemId(Integer problemId) {
        this.problemId = problemId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(Integer timeLimit) {
        this.timeLimit = timeLimit;
    }

    public Integer getMemoryLimit() {
        return memoryLimit;
    }

    public void setMemoryLimit(Integer memoryLimit) {
        this.memoryLimit = memoryLimit;
    }

    public String getDescriptionMarkdown() {
        return descriptionMarkdown;
    }

    public void setDescriptionMarkdown(String descriptionMarkdown) {
        this.descriptionMarkdown = descriptionMarkdown;
    }

    public String getInputDescriptionMarkdown() {
        return inputDescriptionMarkdown;
    }

    public void setInputDescriptionMarkdown(String inputDescriptionMarkdown) {
        this.inputDescriptionMarkdown = inputDescriptionMarkdown;
    }

    public String getOutputDescriptionMarkdown() {
        return outputDescriptionMarkdown;
    }

    public void setOutputDescriptionMarkdown(String outputDescriptionMarkdown) {
        this.outputDescriptionMarkdown = outputDescriptionMarkdown;
    }

    public String getSamples() {
        return samples;
    }

    public void setSamples(String samples) {
        this.samples = samples;
    }

    public String getHintMarkdown() {
        return hintMarkdown;
    }

    public void setHintMarkdown(String hintMarkdown) {
        this.hintMarkdown = hintMarkdown;
    }

    @Override
    public String toString() {
        return "ProblemEditRequest{" +
                "problemId=" + problemId +
                ", title='" + title + '\'' +
                ", timeLimit='" + timeLimit + '\'' +
                ", memoryLimit='" + memoryLimit + '\'' +
                ", descriptionMarkdown='" + descriptionMarkdown + '\'' +
                ", inputDescriptionMarkdown='" + inputDescriptionMarkdown + '\'' +
                ", outputDescriptionMarkdown='" + outputDescriptionMarkdown + '\'' +
                ", samples='" + samples + '\'' +
                ", hintMarkdown='" + hintMarkdown + '\'' +
                '}';
    }
}
