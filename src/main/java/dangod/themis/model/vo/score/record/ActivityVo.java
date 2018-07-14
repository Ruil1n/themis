package dangod.themis.model.vo.score.record;

import dangod.themis.model.po.score.record.Activity;

import java.text.DecimalFormat;

public class ActivityVo extends BaseRecordVo{
    private String activityDate;
    private String activityName;
    private Double score;

    public String getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(String activityDate) {
        this.activityDate = activityDate;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Double getScore() {
        return score;
    }

    public ActivityVo() {
    }

    public ActivityVo(Activity activity, double score) {
        this.recordId = activity.getId();
        this.stuId = activity.getBaseInfo().getStuId();
        this.activityDate = activity.getActivityDate();
        this.activityName = activity.getActivityName();
        this.term = activity.getTerm();
        this.common = activity.getCommon();
        this.score = score;
    }
}
