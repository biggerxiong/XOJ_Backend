package me.xiongxuan.xoj.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author XiongXuan
 */
@Entity
@Table(name = "oj_problem_type")
public class ProblemType {

    /**
     * 题目类型id（主键），只能为数字，自增。（如0代表本oj，1代表杭电oj，2代表北大oj）
     */
    @Id
    @TableGenerator( name = "AppSeqStore", allocationSize = 1 )
    @GeneratedValue( strategy = GenerationType.TABLE, generator = "AppSeqStore" )
    private Integer problemTypeId;

    /**
     * 题目类型名，最大长度20。（若id为0，则本项为XOJ，若id为1，则本项为HDU_OJ）
     */
    @Column(length = 20)
    private String problemTypeName;

    /**
     * 该类型的描述，默认为空，最大为50。
     */
    @Column(length = 50)
    private String problemTypeDescription;

    public ProblemType() {
    }

    public ProblemType(Integer problemTypeId) {
        this.problemTypeId = problemTypeId;
    }

    public Integer getProblemTypeId() {
        return problemTypeId;
    }

    public void setProblemTypeId(Integer problemTypeId) {
        this.problemTypeId = problemTypeId;
    }

    public String getProblemTypeName() {
        return problemTypeName;
    }

    public void setProblemTypeName(String problemTypeName) {
        this.problemTypeName = problemTypeName;
    }

    public String getProblemTypeDescription() {
        return problemTypeDescription;
    }

    public void setProblemTypeDescription(String problemTypeDescription) {
        this.problemTypeDescription = problemTypeDescription;
    }

    @Override
    public String toString() {
        return "ProblemType{" +
                "problemTypeId=" + problemTypeId +
                ", problemTypeName='" + problemTypeName + '\'' +
                ", problemTypeDescription='" + problemTypeDescription + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProblemType)) {
            return false;
        }
        ProblemType that = (ProblemType) o;
        return Objects.equals(problemTypeId, that.problemTypeId) &&
                Objects.equals(problemTypeName, that.problemTypeName) &&
                Objects.equals(problemTypeDescription, that.problemTypeDescription);
    }

}
