package dangod.themis.model.vo.club;

import com.sun.javafx.binding.StringFormatter;
import dangod.themis.model.po.club.Application;
import dangod.themis.model.po.club.Approval;
import dangod.themis.model.po.club.Club;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class ApplicationVo {
    private long applicationId;
    private String applyDate;
    private String clubName;
    private long chiefId;
    private String chiefName;
    private String chiefPhone;
    private String activityName;
    private String activityPlace;
    private String activityStart;
    private String activityEnd;
    private String activitypeople;
    private String selfMoney;
    private String reserveMoney;
    private Integer isFine;
    private String introduce;
    private Integer hasFile;
    private String status;
    private Integer lv;
    private List<ApprovalVo> results;

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

    public long getChiefId() {
        return chiefId;
    }

    public void setChiefId(long chiefId) {
        this.chiefId = chiefId;
    }

    public String getChiefName() {
        return chiefName;
    }

    public void setChiefName(String chiefName) {
        this.chiefName = chiefName;
    }

    public String getChiefPhone() {
        return chiefPhone;
    }

    public void setChiefPhone(String chiefPhone) {
        this.chiefPhone = chiefPhone;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityPlace() {
        return activityPlace;
    }

    public void setActivityPlace(String activityPlace) {
        this.activityPlace = activityPlace;
    }

    public String getActivityStart() {
        return activityStart;
    }

    public void setActivityStart(String activityStart) {
        this.activityStart = activityStart;
    }

    public String getActivityEnd() {
        return activityEnd;
    }

    public void setActivityEnd(String activityEnd) {
        this.activityEnd = activityEnd;
    }

    public String getActivitypeople() {
        return activitypeople;
    }

    public void setActivitypeople(String activitypeople) {
        this.activitypeople = activitypeople;
    }

    public String getSelfMoney() {
        return selfMoney;
    }

    public void setSelfMoney(Double selfMoney) {
        this.selfMoney = String.format("%.2f", selfMoney);
    }

    public String getReserveMoney() {
        return reserveMoney;
    }

    public void setReserveMoney(Double reserveMoney) {
        this.reserveMoney = String.format("%.2f", reserveMoney);;
    }

    public Integer getLv() {
        return lv;
    }

    public void setLv(Integer lv) {
        this.lv = lv;
    }

    public Integer getIsFine() {
        return isFine;
    }

    public void setIsFine(Integer isFine) {
        this.isFine = isFine;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public Integer getHasFile() {
        return hasFile;
    }

    public void setHasFile(Integer hasFile) {
        this.hasFile = hasFile;
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
            default:
                this.status = "审核中";
        }
    }

    public List<ApprovalVo> getResults() {
        return results;
    }

    public void setResults(List<ApprovalVo> results) {
        this.results = results;
    }

    public ApplicationVo() {
    }

    public ApplicationVo(Application application) {
        this.applicationId = application.getId();
        this.applyDate = application.getApplyDate();
        this.clubName = application.getClub().getClubName();
        this.chiefId = application.getClub().getBaseInfo().getUser().getId();
        this.chiefName = application.getClub().getBaseInfo().getRealName();
        this.chiefPhone = application.getClub().getBaseInfo().getPhone();
        this.activityName = application.getActivityName();
        this.activityPlace = application.getActivityPlace();
        this.activityStart = application.getActivityStart();
        this.activityEnd = application.getActivityEnd();
        this.activitypeople = application.getActivitypeople();
        this.selfMoney = String.format("%.2f", application.getSelfMoney());
        this.reserveMoney = String.format("%.2f", application.getReserveMoney());
        this.isFine = application.getIsFine();
        this.introduce = application.getIntroduce();
        this.hasFile = application.getHasFile();
        this.lv = application.getLv();
        switch (application.getStatus()){
            case -1:
                this.status = "审核未通过";break;
            case 0:
                this.status = "审核通过";break;
            default:
                this.status = "审核中";
        }
        this.results = new ArrayList<>();
    }
    public ApplicationVo(Application application, List<ApprovalVo> results){
        this.applicationId = application.getId();
        this.applyDate = application.getApplyDate();
        this.clubName = application.getClub().getClubName();
        this.chiefId = application.getClub().getBaseInfo().getUser().getId();
        this.chiefName = application.getClub().getBaseInfo().getRealName();
        this.chiefPhone = application.getClub().getBaseInfo().getPhone();
        this.activityName = application.getActivityName();
        this.activityPlace = application.getActivityPlace();
        this.activityStart = application.getActivityStart();
        this.activityEnd = application.getActivityEnd();
        this.activitypeople = application.getActivitypeople();
        this.selfMoney = String.format("%.2f", application.getSelfMoney());
        this.reserveMoney = String.format("%.2f", application.getReserveMoney());
        this.isFine = application.getIsFine();
        this.introduce = application.getIntroduce();
        this.hasFile = application.getHasFile();
        this.lv = application.getLv();
        switch (application.getStatus()){
            case -1:
                this.status = "审核未通过";break;
            case 0:
                this.status = "审核通过";break;
            default:
                this.status = "审核中";
        }
        this.results = results;
    }

    public ApplicationVo(long applicationId, String applyDate, String clubName, long chiefId, String chiefName, String chiefPhone, String activityName, String activityPlace, String activityStart, String activityEnd, String activitypeople, Double selfMoney, Double reserveMoney, Integer isFine, String introduce, Integer hasFile, String status) {
        this.applicationId = applicationId;
        this.applyDate = applyDate;
        this.clubName = clubName;
        this.chiefId = chiefId;
        this.chiefName = chiefName;
        this.chiefPhone = chiefPhone;
        this.activityName = activityName;
        this.activityPlace = activityPlace;
        this.activityStart = activityStart;
        this.activityEnd = activityEnd;
        this.activitypeople = activitypeople;
        this.selfMoney = String.format("%.2f", selfMoney);
        this.reserveMoney = String.format("%.2f", reserveMoney);
        this.isFine = isFine;
        this.introduce = introduce;
        this.hasFile = hasFile;
        this.status = status;
        this.results = new ArrayList<>();
        this.lv = 1;
    }
}
