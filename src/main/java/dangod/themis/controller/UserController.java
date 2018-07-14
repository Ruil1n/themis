package dangod.themis.controller;

import dangod.themis.controller.base.BaseController;
import dangod.themis.controller.base.annotation.Authorization;
import dangod.themis.controller.base.annotation.ContainAuthority;
import dangod.themis.core.result.Result;
import dangod.themis.service.common.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static dangod.themis.controller.base.constant.Message.*;
import static dangod.themis.controller.base.constant.Status.FAIL;
import static dangod.themis.controller.base.constant.Status.SUCCESS;
import static dangod.themis.controller.base.constant.AnnotationConstant.AUTHORIZATION;
import static dangod.themis.model.po.authority.constant.TypeContant.ACCOUNT_MANAGE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@CrossOrigin
@RestController
@RequestMapping(value = "/user")
public class UserController extends BaseController{

    @Autowired
    private UserService userService;

    @RequestMapping(method = POST)
    @ApiOperation(value = "注册")
    public String register(HttpServletRequest request, HttpServletResponse response,
                           @RequestParam("username")String username,
                           @RequestParam("password")String password,
                           @RequestParam("realName")String realName,
                           @RequestParam("email")String email,
                           @RequestParam("sex")String sex){
        Integer status = userService.addUser(username, password, realName, email, sex);
        if(status != null)
            return Result.send(FAIL, null, REGISTER_FAIL_MESSAGE);
        return Result.send(SUCCESS, null, REGISTER_SUCCESS_MESSAGE);
    }

    @RequestMapping(method = PUT)
    @ApiOperation(value = "用户修改密码")
    @Authorization
    public String modifyPassword(HttpServletRequest request, HttpServletResponse response,
                                 @RequestHeader(AUTHORIZATION)String token,
                                 @RequestParam("password")String password){
        long userId = getUserId(request);
        Integer status = userService.updatePassword(userId, password);
        if(status != 0)
            return Result.send(FAIL, null, ACCOUNT_UPDATE_FAIL_MESSAGE);
        return Result.send(SUCCESS, null, ACCOUNT_UPDATE_SUCCESS_MESSAGE);
    }

    @RequestMapping(value = "/admin", method = PUT)
    @ApiOperation(value = "管理员修改账户")
    @ContainAuthority(ACCOUNT_MANAGE)
    @Authorization
    public String modifyPassword(HttpServletRequest request, HttpServletResponse response,
                                @RequestHeader(AUTHORIZATION)String token,
                                @RequestParam("userId")Long userId,
                                @RequestParam("password")String password){
        Integer status = userService.updatePassword(userId, password);
        if(status != 0)
            return Result.send(FAIL, null, ACCOUNT_UPDATE_FAIL_MESSAGE);
        return Result.send(SUCCESS, null, ACCOUNT_UPDATE_SUCCESS_MESSAGE);
    }
}
