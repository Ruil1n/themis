package dangod.themis.service.impl.club;

import dangod.themis.dao.club.ApplicationRepo;
import dangod.themis.dao.club.ApprovalRepo;
import dangod.themis.dao.common.UserBaseInfoRepo;
import dangod.themis.model.po.club.Application;
import dangod.themis.model.po.club.Approval;
import dangod.themis.model.po.club.ClubRole;
import dangod.themis.model.vo.club.ApprovalVo;
import dangod.themis.service.club.ApplicationService;
import dangod.themis.service.club.ApproveService;
import dangod.themis.service.club.MailService;
import dangod.themis.service.club.RoleService;
import dangod.themis.service.common.UserInfoService;
import dangod.themis.service.common.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.List;

import static dangod.themis.config.ClubConstant.*;

@Service
public class ApproveServiceImpl implements ApproveService {
    @Autowired
    private ApprovalRepo approvalRepo;
    @Autowired
    private ApplicationRepo applicationRepo;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserBaseInfoRepo userBaseInfoRepo;
    @Autowired
    private MailService mailService;
    @Override
    public List<ApprovalVo> getApprovalVoListById(long applicationId) {
        List<ApprovalVo> approvalVoList = new ArrayList<>();
        List<Approval> approvalList = approvalRepo.findByApplication_Id(applicationId, new Sort(Sort.Direction.ASC, "approvalLV"));
        for(Approval a : approvalList){
            approvalVoList.add(new ApprovalVo(a));
        }
        return approvalVoList;
    }
    //审批处理成事务，防止出问题
    @Override
    @Transactional
    public ApprovalVo approve(long applicationId, long userId, Integer result, String comment) {
        ApprovalVo approvalVo;
        try {
            ClubRole role = roleService.getRole(userId);
            if (role.getLv() < 2) throw new Exception("没有权限");
            Application app = applicationRepo.findOne(applicationId);
            //如果条件 为 app.getLv() != role.getLv() 就是不允许跨级审批
            Approval approvalDO = approvalRepo.findByApplication_IdAndAndApprovalLV(app.getId(), role.getLv());
            if (app.getLv() > role.getLv() || app.getStatus() != 1 || approvalDO != null) throw new Exception("当前等级已经审批过或者审批流程结束");
            Approval approval = new Approval(role.getLv(), result, comment, userBaseInfoRepo.findByUser_Id(userId), app);
            approvalRepo.save(approval);
            app.setLv(role.getLv() + 1);
            if(result == 0) {//0：不同意
                app.setStatus(-1);
                app.setLv(100);
            }
            if(result == 1&&app.getLv() == 5) {//指导老师审批且同意 审批通过
                app.setStatus(0);
            }
            approvalVo = new ApprovalVo(approval);
            applicationRepo.save(app);
            //TODO 邮件发送 考虑邮件error 数据库回滚问题
            String toMail = app.getClub().getBaseInfo().getEmail();
            if(toMail != null) {
                try {
                    if (app.getStatus() == 0) {//审核通过
                        String subject = String.format(MAIL_SUCCESS_SUBJECT_FORMAT, app.getClub().getClubName(), app.getActivityName());
                        String content = String.format(MAIL_SUCCESS_CONTENT_FORMAT, app.getClub().getClubName(), app.getActivityName());
                        mailService.sendMessage(toMail, subject, content);
                    } else if (app.getStatus() == -1) {//审核不通过
                        String subject = String.format(MAIL_FAIL_SUBJECT_FORMAT, app.getClub().getClubName(), app.getActivityName());
                        String content = String.format(MAIL_FAIL_CONTENT_FORMAT, app.getClub().getClubName(), app.getActivityName());
                        mailService.sendMessage(toMail, subject, content);
                    } else {
                        String subject = String.format(MAIL_APPROVE_SUBJECT_FORMAT, app.getClub().getClubName(), app.getActivityName());
                        String content = String.format(MAIL_APPROVE_CONTENT_FORMAT, app.getClub().getClubName(), app.getActivityStart(), app.getActivityEnd(), app.getActivityPlace(),app.getActivityName(), app.getId());
                        switch (app.getLv()){
                            case 3:
                                toMail = ZX_EMAIL;
                                break;
                            case 4:
                                toMail = ZD_EMAIL;
                                break;
                        }
                        mailService.sendMessage(toMail, subject, content);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            e.printStackTrace();
            return null;
        }
        return approvalVo;
    }

    @Override
    public Integer getApprovalLv(long userId) {
        int lv = -1;
        try{
            lv = roleService.getRole(userId).getLv();
        }catch (Exception e){
            e.printStackTrace();
        }
        return lv;
    }

    @Override
    public Integer deleteApprovalByAppId(long appId) {
        int status = 0;
        try {
            approvalRepo.deleteApprovalByApplication_Id(appId);
            approvalRepo.flush();
        }catch (Exception e){
            status = -1;
            e.printStackTrace();
        }
        return status;

    }
}
