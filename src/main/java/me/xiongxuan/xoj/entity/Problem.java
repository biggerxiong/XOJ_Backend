package me.xiongxuan.xoj.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author XiongXuan
 * @date 2018/4/25
 */

@Entity
@Table(name = "oj_problem")
@JsonIgnoreProperties(value={"hibernateLazyInitializer"})
public class Problem {

    @Id
    @TableGenerator( name = "AppSeqStore", initialValue = 1000, allocationSize = 1 )
    @GeneratedValue( strategy = GenerationType.TABLE, generator = "AppSeqStore" )
    private Integer problemId;

    /**
     * 由谁创建的
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "create_by")
    @JsonIgnoreProperties(value = { "inviter" })
    private User createBy;


    @Column(nullable = false, length = 50)
    private String title;

    /**
     * 题目描述，存放的是题目描述的html文本
     */
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition = "Text")
    private String description;

    /**
     * 题目描述，存放的是题目描述的markdown文本
     */
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition = "Text")
    private String descriptionMarkdown;

    /**
     * 存放的是输入描述的html文本
     */
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition = "Text")
    private String inputDescription;

    /**
     * 存放的是输入描述的markdown文本
     */
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition = "Text")
    private String inputDescriptionMarkdown;

    /**
     * 存放的是输出描述的html文本
     */
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition = "Text")
    private String outputDescription;

    /**
     * 存放的是输出描述的markdown文本
     */
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition = "Text")
    private String outputDescriptionMarkdown;

    /**
     * 样例，以json格式存放
     * 例如：[{"input": "1 1", "output": "2"},{"input": "2 1", "output": "3"},{"input": "2 2", "output": "4"}]
     */
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition = "Text")
    private String samples;

    /**
     * 提交数
     */
    private Integer submit = 0;

    /**
     * 通过数
     */
    private Integer accept = 0;

    /**
     * 时间限制
     */
    private Integer timeLimit;

    /**
     * 内存限制
     */
    private Integer memoryLimit;

    /**
     * 测试数据md5
     */
    private String testCaseMd5;

    /**
     * 提示，存放的是markdown文本
     */
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition = "Text")
    private String hintMarkdown;

    /**
     * 提示，存放的是html文本
     */
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition = "Text")
    private String hint;

    /**
     * 题目类型，整型，默认为0，代表本oj原创题，problem_type_id的外键。
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "problem_type_id")
    private ProblemType problemType = new ProblemType(0);

    /**
     * 该题目所在OJ对应的提交题号，字符型，最大为10。本oj题目对应的是problem_id，杭电对应的是它的题号
     */
    @Column(length = 15)
    private String remoteProblemId;

    /**
     * 题目是否在题目列表中可见，默认为1，代表可见
     */
    private Integer problemVisiable = 1;

    private LocalDateTime createTime = LocalDateTime.now();

    public Problem() {
    }

    public Problem(Integer problemId, String title, Integer submit, Integer accept, ProblemType problemType) {
        this.problemId = problemId;
        this.title = title;
        this.submit = submit;
        this.accept = accept;
        this.problemType = problemType;
    }

    public Problem(Integer problemId, User createBy, String title, String description, String inputDescription, String outputDescription, String samples, Integer submit, Integer accept, Integer timeLimit, Integer memoryLimit, String hint, ProblemType problemType, LocalDateTime createTime) {
        this.problemId = problemId;
        this.createBy = createBy;
        this.title = title;
        this.description = description;
        this.inputDescription = inputDescription;
        this.outputDescription = outputDescription;
        this.samples = samples;
        this.submit = submit;
        this.accept = accept;
        this.timeLimit = timeLimit;
        this.memoryLimit = memoryLimit;
        this.hint = hint;
        this.problemType = problemType;
        this.createTime = createTime;
    }

    public Problem(Integer problemId, User createBy, String title, String descriptionMarkdown, String inputDescriptionMarkdown, String outputDescriptionMarkdown, String samples, Integer timeLimit, Integer memoryLimit, String hintMarkdown, ProblemType problemType, String remoteProblemId, Integer problemVisiable) {
        this.problemId = problemId;
        this.createBy = createBy;
        this.title = title;
        this.descriptionMarkdown = descriptionMarkdown;
        this.inputDescriptionMarkdown = inputDescriptionMarkdown;
        this.outputDescriptionMarkdown = outputDescriptionMarkdown;
        this.samples = samples;
        this.timeLimit = timeLimit;
        this.memoryLimit = memoryLimit;
        this.hintMarkdown = hintMarkdown;
        this.problemType = problemType;
        this.remoteProblemId = remoteProblemId;
        this.problemVisiable = problemVisiable;
    }

    public Problem(Integer problemId) {
        this.problemId = problemId;
    }

    public String getTestCaseMd5() {
        return testCaseMd5;
    }

    public void setTestCaseMd5(String testCaseMd5) {
        this.testCaseMd5 = testCaseMd5;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInputDescription() {
        return inputDescription;
    }

    public void setInputDescription(String inputDescription) {
        this.inputDescription = inputDescription;
    }

    public String getOutputDescription() {
        return outputDescription;
    }

    public void setOutputDescription(String outputDescription) {
        this.outputDescription = outputDescription;
    }

    public String getSamples() {
        return samples;
    }

    public void setSamples(String samples) {
        this.samples = samples;
    }

    public Integer getSubmit() {
        return submit;
    }

    public void setSubmit(Integer submit) {
        this.submit = submit;
    }

    public Integer getAccept() {
        return accept;
    }

    public void setAccept(Integer accept) {
        this.accept = accept;
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

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getRemoteProblemId() {
        return remoteProblemId;
    }

    public void setRemoteProblemId(String remoteProblemId) {
        this.remoteProblemId = remoteProblemId;
    }

    public Integer getProblemVisiable() {
        return problemVisiable;
    }

    public void setProblemVisiable(Integer problemVisiable) {
        this.problemVisiable = problemVisiable;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public User getCreateBy() {
        return createBy;
    }

    public void setCreateBy(User createBy) {
        this.createBy = createBy;
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

    public String getHintMarkdown() {
        return hintMarkdown;
    }

    public void setHintMarkdown(String hintMarkdown) {
        this.hintMarkdown = hintMarkdown;
    }

    public ProblemType getProblemType() {
        return problemType;
    }

    public void setProblemType(ProblemType problemType) {
        this.problemType = problemType;
    }

    @Override
    public String toString() {
        return "Problem{" +
                "problemId=" + problemId +
                ", createBy=" + createBy +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", descriptionMarkdown='" + descriptionMarkdown + '\'' +
                ", inputDescription='" + inputDescription + '\'' +
                ", inputDescriptionMarkdown='" + inputDescriptionMarkdown + '\'' +
                ", outputDescription='" + outputDescription + '\'' +
                ", outputDescriptionMarkdown='" + outputDescriptionMarkdown + '\'' +
                ", samples='" + samples + '\'' +
                ", submit=" + submit +
                ", accept=" + accept +
                ", timeLimit=" + timeLimit +
                ", memoryLimit=" + memoryLimit +
                ", hintMarkdown='" + hintMarkdown + '\'' +
                ", hint='" + hint + '\'' +
                ", problemType=" + problemType +
                ", remoteProblemId='" + remoteProblemId + '\'' +
                ", problemVisiable=" + problemVisiable +
                ", createTime=" + createTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Problem)) return false;
        Problem problem = (Problem) o;
        return problemId.equals(problem.problemId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(problemId);
    }
}
