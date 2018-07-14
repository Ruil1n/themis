package dangod.themis.model.vo.score.file;

import java.util.List;

public class VolunteerImport {
    private String stuId;
    private String realName;
    private String volunteerName;
    private String volunteerDate;
    private Double volunteerTime;
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

    public String getVolunteerName() {
        return volunteerName;
    }

    public void setVolunteerName(String volunteerName) {
        this.volunteerName = volunteerName;
    }

    public String getVolunteerDate() {
        return volunteerDate;
    }

    public void setVolunteerDate(String volunteerDate) {
        this.volunteerDate = volunteerDate;
    }

    public Double getVolunteerTime() {
        return volunteerTime;
    }

    public void setVolunteerTime(Double volunteerTime) {
        this.volunteerTime = volunteerTime;
    }

    public String getCommon() {
        return common;
    }

    public void setCommon(String common) {
        this.common = common;
    }

    public VolunteerImport() {
    }

    public VolunteerImport(List<String> list) {
        this.stuId = list.get(0);
        this.realName = list.get(0);
        this.volunteerName = list.get(0);
        this.volunteerDate = list.get(0);
        this.volunteerTime = Double.parseDouble(list.get(0));
        this.common = list.get(0);
    }
}
