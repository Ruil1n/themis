package dangod.themis.model.po.score.record;


import dangod.themis.model.po.score.StudentBaseInfo;

import javax.persistence.*;

@Entity
@Table(name = "score_student_reserve")
public class Reserve {
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne(fetch=FetchType.EAGER, cascade= CascadeType.DETACH)
    @JoinColumn(name="base_id",nullable=true)
    private StudentBaseInfo baseInfo;
    private String common;
    private String term;

    private String course;
    private Double score;

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

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Reserve() {
    }

    public Reserve(StudentBaseInfo baseInfo, String common, String term, String course, Double score) {
        this.baseInfo = baseInfo;
        this.common = common;
        this.term = term;
        this.course = course;
        this.score = score;
    }
}
