package dangod.themis.model.vo.score.file;

import java.util.List;

public class ActivityImport {
    private String activityName;
    private String activityDate;
    private String stuId;
    private String realName;
    private String common;

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(String activityDate) {
        this.activityDate = activityDate;
    }

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

    public String getCommon() {
        return common;
    }

    public void setCommon(String common) {
        this.common = common;
    }

    public ActivityImport() {
    }

    public ActivityImport(List<String> list) {
        this.activityName = list.get(0);
        this.activityDate = list.get(1);
        this.stuId = list.get(2);
        this.realName = list.get(3);
        this.common = list.get(4);
    }
}
