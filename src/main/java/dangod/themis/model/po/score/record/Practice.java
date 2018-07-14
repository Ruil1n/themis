package dangod.themis.model.po.score.record;

import dangod.themis.model.po.score.StudentBaseInfo;

import javax.persistence.*;

@Entity
@Table(name = "score_student_practice")
public class Practice {
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne(fetch=FetchType.EAGER, cascade= CascadeType.DETACH)
    @JoinColumn(name="base_id",nullable=true)
    private StudentBaseInfo baseInfo;
    private String common;
    private String term;

    private String practiceName;
    private String practiceDate;
    private Integer result;

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

    public String getPracticeName() {
        return practiceName;
    }

    public void setPracticeName(String practiceName) {
        this.practiceName = practiceName;
    }

    public String getPracticeDate() {
        return practiceDate;
    }

    public void setPracticeDate(String practiceDate) {
        this.practiceDate = practiceDate;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public Practice() {
    }

    public Practice(StudentBaseInfo baseInfo, String common, String term, String practiceName, String practiceDate, Integer result) {
        this.baseInfo = baseInfo;
        this.common = common;
        this.term = term;
        this.practiceName = practiceName;
        this.practiceDate = practiceDate;
        this.result = result;
    }
}
