package dangod.themis.model.po.club;

import dangod.themis.model.po.common.User;
import dangod.themis.model.po.common.UserBaseInfo;

import javax.persistence.*;
@Entity
@Table(name = "club_approval")
public class Approval {
    @Id
    @GeneratedValue
    private long id;
    @Column(name = "approval_lv")
    private Integer approvalLV;//1 2 3 4
    private Integer result;// 同意1 不同意0
    @Column(length = 1000)
    private String comment;
    @OneToOne(fetch= FetchType.EAGER, cascade= CascadeType.DETACH)
    @JoinColumn(name="auditor_id",nullable=true)
    private UserBaseInfo baseInfo;
    @OneToOne(fetch= FetchType.EAGER, cascade= CascadeType.DETACH)
    @JoinColumn(name="application_id",nullable=true)
    private Application application;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getApprovalLV() {
        return approvalLV;
    }

    public void setApprovalLV(Integer approvalLV) {
        this.approvalLV = approvalLV;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public UserBaseInfo getBaseInfo() {
        return baseInfo;
    }

    public void setBaseInfo(UserBaseInfo baseInfo) {
        this.baseInfo = baseInfo;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public Approval() {
    }

    public Approval(Integer approvalLV, Integer result, String comment, UserBaseInfo baseInfo, Application application) {
        this.approvalLV = approvalLV;
        this.result = result;
        this.comment = comment;
        this.baseInfo = baseInfo;
        this.application = application;
    }
}
