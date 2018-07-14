package dangod.themis.controller;

import dangod.themis.controller.base.annotation.Authorization;
import dangod.themis.core.result.Result;
import dangod.themis.service.common.TokenService;
import dangod.themis.service.common.UserService;
import dangod.themis.util.ReplayDefender;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static dangod.themis.controller.base.constant.Message.*;
import static dangod.themis.controller.base.constant.Status.*;
import static dangod.themis.controller.base.constant.AnnotationConstant.AUTHORIZATION;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@CrossOrigin
@RestController
@RequestMapping(value = "/token")
public class TokenController {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserService userService;
    @Autowired
    private ReplayDefender replayDefender;

    @RequestMapping(method = POST)
    @ApiOperation(value = "登录")
    public String login(HttpServletRequest request, HttpServletResponse response,
                        @RequestParam("username")String username,
                        @RequestParam("password")String password,
                        @RequestParam("timestamp")long timestamp,
                        @RequestParam("nonce")String nonce){
        if(replayDefender.checkReplay(timestamp, nonce))
            return Result.send(REPLAY_ATTACK, null, REPLAY_ATTACK_MESSAGE);
        long userId = userService.checkUser(username, password);
        if(userId == -1){
            return Result.send(FAIL, null, LOGIN_FAIL_MESSAGE);
        }
        String token = tokenService.createToken(userId);
        return Result.send(SUCCESS, token, LOGIN_SUCCESS_MESSAGE);
    }

    @RequestMapping(method = DELETE)
    @ApiOperation(value = "登出")
    @Authorization
    public String logout(HttpServletRequest request, HttpServletResponse response,
                        @RequestHeader(AUTHORIZATION)String token){
        tokenService.deleteToken(token);
        return Result.send(SUCCESS, null, LOGOUT_SUCCESS_MESSAGE);
    }

    @RequestMapping(method = GET)
    @ApiOperation(value = "验证token")
    public String tokenVaild(HttpServletRequest request, HttpServletResponse response,
                         @RequestHeader(AUTHORIZATION)String token){
        if(tokenService.checkToken(token))
            return Result.send(SUCCESS, null, TOKEN_VAILD_MESSAGE);
        return Result.send(UNAUTHORIZED, null, TOKEN_INVAILD_MESSAGE);
    }
}
