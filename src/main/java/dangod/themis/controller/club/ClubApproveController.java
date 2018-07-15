package dangod.themis.controller.club;

import dangod.themis.controller.base.BaseController;
import dangod.themis.controller.base.annotation.Authorization;
import dangod.themis.controller.base.annotation.ContainAuthority;
import dangod.themis.core.result.Result;
import dangod.themis.model.vo.club.ApprovalVo;
import dangod.themis.service.club.ApproveService;
import dangod.themis.service.club.MailService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static dangod.themis.config.ClubConstant.*;
import static dangod.themis.controller.base.constant.AnnotationConstant.AUTHORIZATION;
import static dangod.themis.controller.base.constant.Message.*;
import static dangod.themis.controller.base.constant.Status.FAIL;
import static dangod.themis.controller.base.constant.Status.PERMISSIN_DENIED;
import static dangod.themis.controller.base.constant.Status.SUCCESS;
import static dangod.themis.model.po.authority.constant.TypeContant.CLUB_ACTIVITY_APPLY;
import static dangod.themis.model.po.authority.constant.TypeContant.CLUB_ACTIVITY_APPROVE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@CrossOrigin
@RestController
@RequestMapping(value = "/club/approval")
public class ClubApproveController extends BaseController{
    @Autowired
    private ApproveService approveService;
    @RequestMapping(method = POST)
    @ApiOperation(value = "审核表单")
    @Authorization
    @ContainAuthority(CLUB_ACTIVITY_APPROVE)
    public String approve(HttpServletRequest request, HttpServletResponse response,
                                  @RequestHeader(AUTHORIZATION)String token,
                                  @RequestParam("appId") long appId,
                                  @RequestParam("result") Integer result,
                                  @RequestParam("comment") String comment){
        ApprovalVo approvalVo = approveService.approve(appId, getUserId(request), result,comment);
        if(approvalVo == null)return Result.send(FAIL, approvalVo, CLUB_APPROVE_FAIL_MESSAGE);
        return Result.send(SUCCESS, approvalVo, CLUB_APPROVE_SUCCESS_MESSAGE);
    }

    @RequestMapping(value = "/lv", method = GET)
    @ApiOperation(value = "获取审批等级")
    @Authorization
    //@ContainAuthority(CLUB_ACTIVITY_APPROVE)
    public String getLv(HttpServletRequest request, HttpServletResponse response,
                                  @RequestHeader(AUTHORIZATION)String token){
        int lv = approveService.getApprovalLv(getUserId(request));
        System.out.println(lv);
        if(lv == -1)return Result.send(PERMISSIN_DENIED, lv, CLUB_APPROVE_NO_AUTHORITY_MESSAGE);
        return Result.send(SUCCESS, lv, CLUB_APPROVE_HAVE_AUTHORITY_MESSAGE);
    }

    @RequestMapping(value = "/refund",method = POST)
    @ApiOperation(value = "审核核销")
    @Authorization
    @ContainAuthority(CLUB_ACTIVITY_APPROVE)
    public String apprefund(HttpServletRequest request, HttpServletResponse response,
                            @RequestHeader(AUTHORIZATION)String token,
                            @RequestParam("appId") long appId,
                            @RequestParam("result") Integer result,
                            @RequestParam("comment") String comment){
        ApprovalVo approvalVo = approveService.apprefund(appId, getUserId(request), result,comment);
        if(approvalVo == null)return Result.send(FAIL, approvalVo, CLUB_APPROVE_FAIL_MESSAGE);
        return Result.send(SUCCESS, approvalVo, CLUB_APPROVE_SUCCESS_MESSAGE);
    }


    @RequestMapping(value = "/refund", method = GET)
    @ApiOperation(value = "获取是否核账")
    @Authorization
    @ContainAuthority(CLUB_ACTIVITY_APPROVE)
    public String getRefund(HttpServletRequest request, HttpServletResponse response,
                            @RequestHeader(AUTHORIZATION)String token,
                            @RequestParam("appId") long appId){
        int refund = approveService.getApprovalRefund(appId);
        if(refund != 1)return Result.send(PERMISSIN_DENIED, refund, CLUB_APPROVE_NO_AUTHORITY_MESSAGE);
        return Result.send(SUCCESS, refund, CLUB_APPROVE_HAVE_AUTHORITY_MESSAGE);
    }



}
