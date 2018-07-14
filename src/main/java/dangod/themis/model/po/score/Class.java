package dangod.themis.model.po.score;

import javax.persistence.*;

@Entity
@Table(name = "score_class")
public class Class {
    @Id
    @GeneratedValue
    private long id;
    private Integer classNum;
    @ManyToOne(fetch=FetchType.EAGER, cascade= CascadeType.DETACH)
    @JoinColumn(name="major_id",nullable=true)
    private Major major;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getClassNum() {
        return classNum;
    }

    public void setClassNum(Integer classNum) {
        this.classNum = classNum;
    }

    public Major getMajor() {
        return major;
    }

    public void setMajor(Major major) {
        this.major = major;
    }

    public Class(Integer classNum, Major major) {
        this.classNum = classNum;
        this.major = major;
    }

    public Class() {
    }
}
