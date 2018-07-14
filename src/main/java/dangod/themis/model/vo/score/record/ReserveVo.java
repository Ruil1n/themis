package dangod.themis.model.vo.score.record;

import dangod.themis.model.po.score.record.Reserve;

public class ReserveVo extends BaseRecordVo {
    private String course;
    private Double score;

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

    public ReserveVo() {
    }

    public ReserveVo(Reserve reserve) {
        this.recordId = reserve.getId();
        this.stuId = reserve.getBaseInfo().getStuId();
        this.common = reserve.getCommon();
        this.term = reserve.getTerm();
        this.course = reserve.getCourse();
        this.score = reserve.getScore();
    }
}
