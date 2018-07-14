package dangod.themis.model.po.score.record;

import dangod.themis.model.po.score.StudentBaseInfo;

import javax.persistence.*;

@Entity
@Table(name = "score_student_office")
public class Office {
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne(fetch=FetchType.EAGER, cascade= CascadeType.DETACH)
    @JoinColumn(name="base_id",nullable=true)
    private StudentBaseInfo baseInfo;
    private String common;
    private String term;

    private String officeName;
    private Integer officeLv;
    private String startDate;
    private String endDate;
    private Integer result = 0;

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

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public Integer getOfficeLv() {
        return officeLv;
    }

    public void setOfficeLv(Integer officeLv) {
        this.officeLv = officeLv;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
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

    public Office() {
    }

    public Office(StudentBaseInfo baseInfo, String common, String term, String officeName, Integer officeLv, String startDate, String endDate, Integer result) {
        this.baseInfo = baseInfo;
        this.common = common;
        this.term = term;
        this.officeName = officeName;
        this.officeLv = officeLv;
        this.startDate = startDate;
        this.endDate = endDate;
        this.result = result;
    }
}
