package dangod.themis.model.vo.club;

import dangod.themis.model.po.club.Application;
import dangod.themis.model.po.club.Approval;
import dangod.themis.model.po.common.UserBaseInfo;

import javax.persistence.*;

public class ApprovalVo {
    private long approvalId;
    private Integer approvalLV;
    private String result;
    private String comment;
    private String auditor;
    private long applicationId;

    public long getApprovalId() {
        return approvalId;
    }

    public void setApprovalId(long approvalId) {
        this.approvalId = approvalId;
    }

    public Integer getApprovalLV() {
        return approvalLV;
    }

    public void setApprovalLV(Integer approvalLV) {
        this.approvalLV = approvalLV;
    }

    public String getResult() {
        return result;
    }

    public void setResult(Integer result) {
        if(result == 1)this.result = "同意";
        else this.result = "不同意";
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(long applicationId) {
        this.applicationId = applicationId;
    }

    public ApprovalVo() {
    }

    public ApprovalVo(Approval approval) {
        this.approvalId = approval.getId();
        this.approvalLV = approval.getApprovalLV();
        this.setResult(approval.getResult());
        this.comment = approval.getComment();
        this.auditor = approval.getBaseInfo().getRealName();
        this.applicationId = approval.getApplication().getId();
    }

    public ApprovalVo(long approvalId, Integer approvalLV, Integer result, String comment, String auditor, long applicationId) {
        this.approvalId = approvalId;
        this.approvalLV = approvalLV;
        this.setResult(result);
        this.comment = comment;
        this.auditor = auditor;
        this.applicationId = applicationId;
    }
}
