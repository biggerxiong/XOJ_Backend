package me.xiongxuan.xoj.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 *
 * @author XiongXuan
 * @date 2018/4/26
 */

@Entity
@Table(name = "oj_judgeStatus")
public class JudgeStatus {
    /**
     * 评测id（主键），只能为数字，自增，从100000开始
     */
    @Id
    @TableGenerator( name = "AppSeqStore", initialValue = 1000000, allocationSize = 1 )
    @GeneratedValue( strategy = GenerationType.TABLE, generator = "AppSeqStore" )
    private Integer judgeStatusId;

    /**
     * 题目id，外键
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "problem_id")
    private Problem problem;

    /**
     * 用户id
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties(value = { "inviter" })
    private User user;

    /**
     * 远程评测的id
     */
    private String remoteId;

    /**
     * 待评测代码的语言id，整型，为Language的外键
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "language_id")
    private Language language;

    /**
     * 存放代码的文件路径，最大60字符
     */
    @Column(length = 60)
    private String codeFilePath;

    /**
     * 评测结果代码，整型，0为接受。默认6，队列中
     */
    private Integer score = 0;

    /**
     * 状态代码，0为队列中，1为正常，2为编译错误，3为系统错误
     */
    private Integer error = 0;
    /**
     * 评测结果，如果code为0，则此项为Accept，默认In Queue，队列中。最大为20
     */
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition = "Text")
    private String result = "In Queue";

    /**
     * 存放编译错误的信息
     */
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(columnDefinition = "Text")
    private String errorMsg = null;

    /**
     * 运行时间，整型，ms为单位
     */
    private Integer timeCost;

    /**
     * 内存消耗，整型，kb为单位
     */
    private Integer memoryCost;

    /**
     * 创建时间
     */
    private LocalDateTime createTime = LocalDateTime.now();

    public JudgeStatus() {
    }

    public JudgeStatus(Integer problemId, Integer userId, String codeFilePath, Integer error, String result, Integer languageId) {
        this.problem = new Problem(problemId);
        this.user = new User(userId);
        this.language = new Language(languageId);
        this.codeFilePath = codeFilePath;
        this.error = error;
        this.result = result;
    }

    public JudgeStatus(Integer problemId, User user, String codeFilePath, Integer error, String result, Integer languageId) {
        this.problem = new Problem(problemId);
        this.user = user;
        this.language = new Language(languageId);
        this.codeFilePath = codeFilePath;
        this.error = error;
        this.result = result;
    }

    public Integer getJudgeStatusId() {
        return judgeStatusId;
    }

    public void setJudgeStatusId(Integer judgeStatusId) {
        this.judgeStatusId = judgeStatusId;
    }

    public Problem getProblem() {
        return problem;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRemoteId() {
        return remoteId;
    }

    public void setRemoteId(String remoteId) {
        this.remoteId = remoteId;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public String getCodeFilePath() {
        return codeFilePath;
    }

    public void setCodeFilePath(String codeFilePath) {
        this.codeFilePath = codeFilePath;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
        this.error = error;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public Integer getTimeCost() {
        return timeCost;
    }

    public void setTimeCost(Integer timeCost) {
        this.timeCost = timeCost;
    }

    public Integer getMemoryCost() {
        return memoryCost;
    }

    public void setMemoryCost(Integer memoryCost) {
        this.memoryCost = memoryCost;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "JudgeStatus{" +
                "judgeStatusId=" + judgeStatusId +
                ", problem=" + problem.getProblemId() +
                ", user=" + user.getUserName() +
                ", remoteId='" + remoteId + '\'' +
                ", language=" + language.getLanguageName() +
                ", codeFilePath='" + codeFilePath + '\'' +
                ", score=" + score +
                ", error=" + error +
                ", result='" + result + '\'' +
                ", errorMsg='" + errorMsg + '\'' +
                ", timeCost=" + timeCost +
                ", memoryCost=" + memoryCost +
                ", createTime=" + createTime +
                '}';
    }
}
