package dangod.themis.model.vo.club;

import dangod.themis.model.po.club.Application;

public class StatusVo {
    //审批中 xxx社/xxx xxxx活动  时间
    private long applicationId;
    private String applyDate;
    private String clubName;
    private String chiefName;
    private String activityName;
    private String status;

    public long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(long applicationId) {
        this.applicationId = applicationId;
    }

    public String getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getChiefName() {
        return chiefName;
    }

    public void setChiefName(String chiefName) {
        this.chiefName = chiefName;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        switch (status){
            case -1:
                this.status = "审核未通过";break;
            case 0:
                this.status = "审核通过";break;
            case 1:
                this.status = "审核中";break;
            case 2:
                this.status = "核账中";break;
            default:
                this.status = "审核中";
        }
    }

    public StatusVo() {
    }

    public StatusVo(Application application) {
        this.applicationId = application.getId();
        this.applyDate = application.getApplyDate();
        this.clubName = application.getClub().getClubName();
        this.chiefName = application.getClub().getBaseInfo().getRealName();
        this.activityName = application.getActivityName();
        switch (application.getStatus()){
            case -1:
                this.status = "审核未通过";break;
            case 0:
                this.status = "审核通过";break;
            case 1:
                this.status = "审核中";break;
            case 2:
                this.status = "核账中";break;
            default:
                this.status = "审核中";
        }
    }

    public StatusVo(long applicationId, String applyDate, String clubName, String chiefName, String activityName, Integer status) {
        this.applicationId = applicationId;
        this.applyDate = applyDate;
        this.clubName = clubName;
        this.chiefName = chiefName;
        this.activityName = activityName;
        switch (status){
            case -1:
                this.status = "审核未通过";break;
            case 0:
                this.status = "审核通过";break;
            case 1:
                this.status = "审核中";break;
            case 2:
                this.status = "核账中";break;
            default:
                this.status = "审核中";
        }
    }
}
