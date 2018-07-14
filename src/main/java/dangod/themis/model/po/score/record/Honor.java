package dangod.themis.model.po.score.record;

import dangod.themis.model.po.score.StudentBaseInfo;

import javax.persistence.*;

@Entity
@Table(name = "score_student_honor")
public class Honor {
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne(fetch=FetchType.EAGER, cascade= CascadeType.DETACH)
    @JoinColumn(name="base_id",nullable=true)
    private StudentBaseInfo baseInfo;
    private String common;
    private String term;

    private String honorName;
    private Integer honorLv;
    private Double honorScore;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public StudentBaseInfo getBaseInfo() {
        return baseInfo;
    }

    public void setBaseInfo(StudentBaseInfo baseInfo) {
        this.baseInfo = baseInfo;
    }

    public String getCommon() {
        return common;
    }

    public void setCommon(String common) {
        this.common = common;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getHonorName() {
        return honorName;
    }

    public void setHonorName(String honorName) {
        this.honorName = honorName;
    }

    public Integer getHonorLv() {
        return honorLv;
    }

    public void setHonorLv(Integer honorLv) {
        this.honorLv = honorLv;
    }

    public Double getHonorScore() {
        return honorScore;
    }

    public void setHonorScore(Double honorScore) {
        this.honorScore = honorScore;
    }

    public Honor() {
    }

    public Honor(StudentBaseInfo baseInfo, String common, String term, String honorName, Integer honorLv, Double honorScore) {
        this.baseInfo = baseInfo;
        this.common = common;
        this.term = term;
        this.honorName = honorName;
        this.honorLv = honorLv;
        this.honorScore = honorScore;
    }
}
