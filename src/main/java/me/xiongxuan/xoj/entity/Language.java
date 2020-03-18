package me.xiongxuan.xoj.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * 记录了oj中可使用的语言
 * 一个oj对于多种语言
 * @author XiongXuan
 */
@Entity
@Table(name = "oj_language")
public class Language {

    /**
     * 语言的id
     */
    @Id
    @TableGenerator( name = "AppSeqStore", allocationSize = 1 )
    @GeneratedValue( strategy = GenerationType.TABLE, generator = "AppSeqStore" )
    private Integer languageId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "problem_type_id")
    private ProblemType problemType;

    private String languageName;

    /**
     * 该语言代码保存的后缀
     */
    private String suffix;

    public Language() {
    }

    public Language(Integer languageId) {
        this.languageId = languageId;
    }

    public Language(ProblemType problemType, String languageName) {
        this.problemType = problemType;
        this.languageName = languageName;
    }

    public Language(Integer problemTypeId, String languageName) {
        this.problemType = new ProblemType(problemTypeId);
        this.languageName = languageName;
    }

    public Integer getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Integer languageId) {
        this.languageId = languageId;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    public ProblemType getProblemType() {
        return problemType;
    }

    public void setProblemType(ProblemType problemType) {
        this.problemType = problemType;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    @Override
    public String toString() {
        return "Language{" +
                "languageId=" + languageId +
                ", problemType=" + problemType +
                ", languageName='" + languageName + '\'' +
                ", suffix='" + suffix + '\'' +
                '}';
    }
}
