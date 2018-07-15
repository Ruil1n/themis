package dangod.themis.model.po.club;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Entity
@Table(name = "club_application")
public class Application {
    @Id
    @GeneratedValue
    private long id;
    private String applyDate;
    private String refundDate;
    @OneToOne(fetch= FetchType.EAGER, cascade= CascadeType.DETACH)
    @JoinColumn(name="club_id",nullable=true)
    private Club club;
    private String activityName;
    private String activityPlace;
    private String activityStart;
    private String activityEnd;
    private String activitypeople;
    private Double selfMoney;
    private Double realSelfMoney;
    private Double reserveMoney;
    private Double realReserveMoney;
    private Integer isFine;
    private Integer isApplyRefund;//0未提交退款申请 1提交了退款申请 2核销通过 3核销失败
    @Column(length = 1500)
    private String introduce;
    private Integer hasFile;// 0无 1有
    private Integer status;//-1,<0:未通过    =0:审核通过       1:审核中
    private Integer lv;//2(刚刚提交等待财务审批),3(财务审批通过等主席审批),4(主席审批结束等老师审批),5(老师审核完毕),100(拒绝)

    public Integer getIsApplyRefund() {
        return isApplyRefund;
    }

    public void setIsApplyRefund(Integer isApplyRefund) {
        this.isApplyRefund = isApplyRefund;
    }

    public String getRefundDate() {
        return refundDate;
    }

    public void setRefundDate(String refundDate) {
        this.refundDate = refundDate;
    }

    public Double getRealSelfMoney() {
        return realSelfMoney;
    }

    public void setRealSelfMoney(Double realSelfMoney) {
        this.realSelfMoney = realSelfMoney;
    }

    public Double getRealReserveMoney() {
        return realReserveMoney;
    }

    public void setRealReserveMoney(Double realReserveMoney) {
        this.realReserveMoney = realReserveMoney;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
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

    public Double getSelfMoney() {
        return selfMoney;
    }

    public void setSelfMoney(Double selfMoney) {
        this.selfMoney = selfMoney;
    }

    public Double getReserveMoney() {
        return reserveMoney;
    }

    public void setReserveMoney(Double reserveMoney) {
        this.reserveMoney = reserveMoney;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getLv() {
        return lv;
    }

    public void setLv(Integer lv) {
        this.lv = lv;
    }

    public Integer getHasFile() {
        return hasFile;
    }

    public void setHasFile(Integer hasFile) {
        this.hasFile = hasFile;
    }

    public Application() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒 E ");
        this.applyDate = sdf.format(Calendar.getInstance().getTime());
        this.status = 1;
        this.lv=2;
        this.isApplyRefund = 0;
    }

    public Application(Club club, String activityName, String activityPlace, String activityStart, String activityEnd, String activitypeople, Double selfMoney, Double reserveMoney,Integer isFine, String introduce, Integer hasFile) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒 E ");
        this.applyDate = sdf.format(Calendar.getInstance().getTime());
        this.club = club;
        this.activityName = activityName;
        this.activityPlace = activityPlace;
        this.activityStart = activityStart;
        this.activityEnd = activityEnd;
        this.activitypeople = activitypeople;
        this.selfMoney = selfMoney;
        this.reserveMoney = reserveMoney;
        this.isFine = isFine;
        this.introduce = introduce;
        this.status = 1;
        this.hasFile = hasFile;
        this.lv=2;
        this.isApplyRefund= 0;
    }
}
