package dangod.themis.model.vo.score.file;

import java.util.List;

public class ReserveImport {
    private String stuId;
    private String realName;
    private String course;
    private Double score;
    private String common;

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
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

    public String getCommon() {
        return common;
    }

    public void setCommon(String common) {
        this.common = common;
    }

    public ReserveImport() {
    }

    public ReserveImport(List<String> list) {
        this.stuId = list.get(0);
        this.realName = list.get(1);
        this.course = list.get(2);
        this.score = Double.parseDouble(list.get(3));
        this.common = list.get(4);
    }
}
