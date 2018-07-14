package dangod.themis.controller;

import dangod.themis.controller.base.BaseController;
import dangod.themis.controller.base.annotation.Authorization;
import dangod.themis.controller.base.annotation.ContainAuthority;
import dangod.themis.core.result.Result;
import dangod.themis.model.vo.UserBaseInfoVo;
import dangod.themis.service.common.UserInfoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import static dangod.themis.controller.base.constant.Message.*;
import static dangod.themis.controller.base.constant.Status.FAIL;
import static dangod.themis.controller.base.constant.Status.SUCCESS;
import static dangod.themis.controller.base.constant.AnnotationConstant.AUTHORIZATION;
import static dangod.themis.model.po.authority.constant.TypeContant.ACCOUNT_MANAGE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@CrossOrigin
@RestController
@RequestMapping(value = "/baseinfo")
public class UserBaseInfoController extends BaseController {

    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping(method = GET)
    @ApiOperation(value = "获取基本账户信息")
    @Authorization
    public String getBaseInfo(HttpServletRequest request, HttpServletResponse response,
                           @RequestHeader(AUTHORIZATION)String token){
        UserBaseInfoVo baseInfoVo = userInfoService.getBaseInfoByUserId(getUserId(request));
        if(baseInfoVo == null)
            return Result.send(FAIL, null, BASEINFO_FAIL_MESSAGE);
        return Result.send(SUCCESS, baseInfoVo, BASEINFO_SUCCESS_MESSAGE);
    }

    @RequestMapping(method = PUT)
    @ApiOperation(value = "用户修改基础信息")
    @Authorization
    public String updateBaseInfo(HttpServletRequest request, HttpServletResponse response,
                                 @RequestHeader(AUTHORIZATION)String token,
                                 @RequestParam("email")String email){

        UserBaseInfoVo baseInfoVo = userInfoService.updateUserBaseInfo(getUserId(request), null, email, null);
        if(baseInfoVo == null)
            return Result.send(FAIL, null, BASEINFO_FAIL_MESSAGE);
        return Result.send(SUCCESS, baseInfoVo, BASEINFO_SUCCESS_MESSAGE);
    }

    @RequestMapping(value = "/admin", method = PUT)
    @ApiOperation(value = "管理员修改基础信息")
    @ContainAuthority(ACCOUNT_MANAGE)
    @Authorization
    public String updateBaseInfo(HttpServletRequest request, HttpServletResponse response,
                                 @RequestHeader(AUTHORIZATION)String token,
                                 @RequestParam("userId")Long userId,
                                 @RequestParam("realName")String realName,
                                 @RequestParam("email")String email,
                                 @RequestParam("sex")String sex){

        UserBaseInfoVo baseInfoVo = userInfoService.updateUserBaseInfo(userId, realName, email, sex);
        if(baseInfoVo == null)
            return Result.send(FAIL, null, BASEINFO_UPDATE_FAIL_MESSAGE);
        return Result.send(SUCCESS, baseInfoVo, BASEINFO_UPDATE_SUCCESS_MESSAGE);
    }

    @RequestMapping(value = "/admin", method = GET)
    @ApiOperation(value = "管理员获取所有用户账号信息")
    @ContainAuthority(ACCOUNT_MANAGE)
    @Authorization
    public String getBaseInfoList(HttpServletRequest request, HttpServletResponse response,
                                  @RequestHeader(AUTHORIZATION)String token,
                                  @RequestParam("page")Integer page){
        int size = DEFAULT_SIZE;
        if(getParameter(request, "size") != null)
            size = Integer.parseInt(getParameter(request, "size"));
        List<UserBaseInfoVo> list = userInfoService.getAllUserBaseInfo(page, size);
        if(list == null)
            return Result.send(FAIL, null, BASEINFO_FAIL_MESSAGE);
        return Result.send(SUCCESS, list, BASEINFO_SUCCESS_MESSAGE);
    }
}
