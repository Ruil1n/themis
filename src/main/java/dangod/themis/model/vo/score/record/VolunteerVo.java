package dangod.themis.model.vo.score.record;

import dangod.themis.model.po.score.record.Volunteer;

public class VolunteerVo extends BaseRecordVo {
    private String volunteerName;
    private String volunteerDate;
    private Double volunteerTime;

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

    public VolunteerVo() {
    }

    public VolunteerVo(Volunteer volunteer) {
        this.recordId = volunteer.getId();
        this.stuId = volunteer.getBaseInfo().getStuId();
        this.common = volunteer.getCommon();
        this.term = volunteer.getTerm();
        this.volunteerDate = volunteer.getVolunteerDate();
        this.volunteerName = volunteer.getVolunteerName();
        this.volunteerTime = volunteer.getVolunteerTime();
    }
}
