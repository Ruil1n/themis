package dangod.themis.controller.club;

import com.alibaba.fastjson.JSON;
import dangod.themis.controller.base.BaseController;
import dangod.themis.controller.base.annotation.Authorization;
import dangod.themis.controller.base.annotation.club.Club;
import dangod.themis.controller.base.annotation.club.ClubAdmin;
import dangod.themis.core.result.Result;
import dangod.themis.model.vo.club.StatusVo;
import dangod.themis.service.club.ApplicationService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

import static dangod.themis.controller.base.constant.AnnotationConstant.AUTHORIZATION;
import static dangod.themis.controller.base.constant.Message.CLUB_APPROVE_SUCCESS_MESSAGE;
import static dangod.themis.controller.base.constant.Message.CLUB_STATUS_SUCCESS_MESSAGE;
import static dangod.themis.controller.base.constant.Status.SUCCESS;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@CrossOrigin
@RestController
@RequestMapping(value = "/club/status")
public class ClubAppStatusController extends BaseController{
    @Autowired
    private ApplicationService applicationService;
    @RequestMapping(value = "/self", method = GET)
    @ApiOperation(value = "获取自己社团所有申请表情况(status -1 0 1)")
    @Authorization
    @Club
    public String getSelfStatuses(HttpServletRequest request, HttpServletResponse response,
                              @RequestHeader(AUTHORIZATION)String token,
                              @RequestParam("page") Integer page){
        int size = DEFAULT_SIZE;
        if(getParameter(request, "size") != null)
            size = Integer.parseInt(getParameter(request, "size"));
        List<StatusVo> statuses = new ArrayList<>();
        Integer status = null;
        try{
            status = Integer.parseInt(getParameter(request, "status"));
        }catch (Exception e){
            status = null;
        }
        if(status != null)
            statuses = applicationService.getPageStatus(getClub(request).getId(), status,  page, size);
        else
            statuses = applicationService.getPageStatus(getClub(request).getId(),  page, size);
        return Result.send(SUCCESS, statuses, CLUB_STATUS_SUCCESS_MESSAGE);
    }
    @RequestMapping(value = "/clubId", method = GET)
    @ApiOperation(value = "获取指定社团所有申请表情况(status -1 0 1)")
    @Authorization
    @ClubAdmin
    public String getStatusesByClubId(HttpServletRequest request, HttpServletResponse response,
                                      @RequestHeader(AUTHORIZATION)String token,
                                      @RequestParam("clubId")long clubId,
                                      @RequestParam("page") Integer page){
        int size = DEFAULT_SIZE;
        if(getParameter(request, "size") != null)
            size = Integer.parseInt(getParameter(request, "size"));
        List<StatusVo> statuses = null;
        Integer status = null;
        try{
            status = Integer.parseInt(getParameter(request, "status"));
        }catch (Exception e){
            status = null;
        }
        if(status != null)
            statuses = applicationService.getPageStatus(clubId, status,  page, size);
        else
            statuses = applicationService.getPageStatus(clubId,  page, size);
        return Result.send(SUCCESS, statuses, CLUB_STATUS_SUCCESS_MESSAGE);
    }
    @RequestMapping(value = "/all", method = GET)
    @ApiOperation(value = "获取审批员可见得所有申请表")
    @Authorization
    @ClubAdmin
    public String getAllCanSeeStatuses(HttpServletRequest request, HttpServletResponse response,
                                 @RequestHeader(AUTHORIZATION)String token,
                                 @RequestParam("page") Integer page){
        int size = DEFAULT_SIZE;
        if(getParameter(request, "size") != null)
            size = Integer.parseInt(getParameter(request, "size"));
        List<StatusVo> statuses = null;
        Integer status = null;
        try{
            status = Integer.parseInt(getParameter(request, "status"));
        }catch (Exception e){
            status = null;
        }
        if(status != null)
            statuses = applicationService.getAllCanSeePageStatus(getClubRole(request), status,  page, size);
        else
            statuses = applicationService.getAllCanSeePageStatus(getClubRole(request),  page, size);
        return Result.send(SUCCESS, statuses, CLUB_STATUS_SUCCESS_MESSAGE);
    }

    @RequestMapping(value = "/needApprove", method = GET)
    @ApiOperation(value = "获取需要用户审批申请")
    @Authorization
    @ClubAdmin
    public String getNeedApproveStatuses(HttpServletRequest request, HttpServletResponse response,
                                 @RequestHeader(AUTHORIZATION)String token,
                                 @RequestParam("page") Integer page){
        int size = DEFAULT_SIZE;
        if(getParameter(request, "size") != null)
            size = Integer.parseInt(getParameter(request, "size"));
        List<StatusVo> statuses = null;
        statuses = applicationService.getNeedApprovePage(getClubRole(request),  page, size);
        return Result.send(SUCCESS, statuses, CLUB_STATUS_SUCCESS_MESSAGE);
    }

}
