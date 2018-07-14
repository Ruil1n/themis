package dangod.themis.model.po.score;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "score_major")
public class Major {
    @Id
    @GeneratedValue
    private long id;
    private String majorName;
    private Integer year;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Major() {
    }

    public Major(String majorName, Integer year) {
        this.majorName = majorName;
        this.year = year;
    }
}
