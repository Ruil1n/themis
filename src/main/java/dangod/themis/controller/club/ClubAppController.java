package dangod.themis.controller.club;

import dangod.themis.controller.base.BaseController;
import dangod.themis.controller.base.annotation.Authorization;
import dangod.themis.controller.base.annotation.ContainAuthority;
import dangod.themis.controller.base.annotation.club.Club;
import dangod.themis.core.result.Result;
import dangod.themis.core.util.BaseFile;
import dangod.themis.model.vo.club.ApplicationVo;
import dangod.themis.model.vo.club.ClubFileVo;
import dangod.themis.service.club.ApplicationService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static dangod.themis.controller.base.constant.AnnotationConstant.AUTHORIZATION;
import static dangod.themis.controller.base.constant.Message.*;
import static dangod.themis.controller.base.constant.Status.FAIL;
import static dangod.themis.controller.base.constant.Status.NOT_FIND;
import static dangod.themis.controller.base.constant.Status.SUCCESS;
import static dangod.themis.core.util.BaseFile.FOLDER;
import static dangod.themis.model.po.authority.constant.TypeContant.CLUB_ACTIVITY_APPLY;
import static dangod.themis.model.po.authority.constant.TypeContant.CLUB_ACTIVITY_APPROVE;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;


@CrossOrigin
@RestController
@RequestMapping(value = "/club/app")
public class ClubAppController extends BaseController {
    @Autowired
    private ApplicationService applicationService;

    @RequestMapping(method = GET)
    @ApiOperation(value = "通过id获取申请表内容")
    public String getApplication(HttpServletRequest request, HttpServletResponse response,
                                 @RequestParam("id") Long id) {
        ApplicationVo applicationVo = applicationService.getApplicationVo(id);
        if (applicationVo == null) return Result.send(NOT_FIND, null, CLUB_APP_NOT_FOUND);
        return Result.send(SUCCESS, applicationVo, CLUB_APP_MESSAGE);
    }

    @RequestMapping(value = "/refund",method = POST)
    @ApiOperation(value = "社团申请核账")
    @Authorization
    @ContainAuthority(CLUB_ACTIVITY_APPLY)
    @Club
    public String applyRefund(HttpServletRequest request, HttpServletResponse response,
                              @RequestHeader(AUTHORIZATION) String token,
                              @RequestParam("id") Long id,
                              @RequestParam("realSelfMoney") Double realSelfMoney,
                              @RequestParam("realReserveMoney") Double realReserveMoney
    ) {
        int status = applicationService.applyRefund(getClub(request), id, realSelfMoney, realReserveMoney);
        if (status != 0) return Result.send(FAIL, null, CLUB_APP_FAIL_MESSAGE);
        return Result.send(SUCCESS, null, CLUB_APP_SUCCESS_MESSAGE);
    }

    @RequestMapping(value = "/file", method = GET)
    @ApiOperation(value = "获取申请表附件")
    public String getApplicationFile(HttpServletRequest request, HttpServletResponse response,
                                     @RequestParam("id") Long id) {
        ClubFileVo fileVo = applicationService.getFile(id);
        if (fileVo == null) Result.send(NOT_FIND, null, CLUB_FILE_NOT_EXIST_MESSAGE);
        int status = BaseFile.download(response, FOLDER + fileVo.getPath(), fileVo.getFileName(), fileVo.getOriginName());
        if (status == -1)
            return Result.send(FAIL, null, CLUB_FILE_FAIL_MESSAGE);
        return Result.send(SUCCESS, null, CLUB_FILE_SUCCESS_MESSAGE);
    }

    @RequestMapping(method = POST)
    @ApiOperation(value = "社团申请活动")
    @Authorization
    @ContainAuthority(CLUB_ACTIVITY_APPLY)
    @Club
    public String apply(HttpServletRequest request, HttpServletResponse response,
                        @RequestHeader(AUTHORIZATION) String token,
                        @RequestParam("name") String name,
                        @RequestParam("place") String place,
                        @RequestParam("start") String start,
                        @RequestParam("end") String end,
                        @RequestParam("people") String people,
                        @RequestParam("selfMoney") Double selfMoney,
                        @RequestParam("reserveMoney") Double reserveMoney,
                        @RequestParam("isFine") Integer isFine,
                        @RequestParam("introduce") String introduce,
                        @RequestParam(value = "file", required = false) MultipartFile file) {
        int status = applicationService.apply(getClub(request), name, place, start, end, people, selfMoney, reserveMoney, isFine, introduce, file);
        if (status != 0) return Result.send(FAIL, null, CLUB_APP_FAIL_MESSAGE);
        return Result.send(SUCCESS, null, CLUB_APP_SUCCESS_MESSAGE);
    }

    @RequestMapping(value = "/self", method = DELETE)
    @ApiOperation(value = "社团自己删除表单")
    @Authorization
    @ContainAuthority(CLUB_ACTIVITY_APPLY)
    @Club
    public String deleteApplication(HttpServletRequest request, HttpServletResponse response,
                                    @RequestParam("id") Long id) {
        int status = applicationService.deleteApplication(getClub(request), id);
        if (status != 0) return Result.send(FAIL, null, CLUB_APP_DELETE_FAIL);
        return Result.send(SUCCESS, null, CLUB_APP_DELETE_SUCCESS);
    }

    @RequestMapping(value = "/admin", method = DELETE)
    @ApiOperation(value = "管理员删除表单")
    @Authorization
    @ContainAuthority(CLUB_ACTIVITY_APPROVE)
    public String deleteApplicationByAdmin(HttpServletRequest request, HttpServletResponse response,
                                           @RequestParam("id") Long id) {
        int status = applicationService.deleteApplication(id);
        if (status != 0) return Result.send(FAIL, null, CLUB_APP_DELETE_FAIL);
        return Result.send(SUCCESS, null, CLUB_APP_DELETE_SUCCESS);
    }
}
