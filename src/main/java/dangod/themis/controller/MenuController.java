package dangod.themis.controller;

import dangod.themis.controller.base.BaseController;
import dangod.themis.controller.base.annotation.Authorization;
import dangod.themis.core.result.Result;
import dangod.themis.model.vo.MenuVo;
import dangod.themis.service.core.AuthorityService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import static dangod.themis.controller.base.constant.Message.MENU_SUCCESS_MESSAGE;
import static dangod.themis.controller.base.constant.Message.TOKEN_INVAILD_MESSAGE;
import static dangod.themis.controller.base.constant.Status.SUCCESS;
import static dangod.themis.controller.base.constant.Status.UNAUTHORIZED;
import static dangod.themis.controller.base.constant.AnnotationConstant.AUTHORIZATION;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@CrossOrigin
@RestController
@RequestMapping(value = "/menu")
public class MenuController extends BaseController {
    @Autowired
    private AuthorityService authorityService;

    @RequestMapping(method = GET)
    @ApiOperation(value = "获取菜单")
    @Authorization
    public String listMenu(HttpServletRequest request, HttpServletResponse response,
                           @RequestHeader(AUTHORIZATION)String token){

        long userId=getUserId(request);
        List<MenuVo> list =  authorityService.getMenuByUserId(userId);

        if(list != null)
            return Result.send(SUCCESS, list, MENU_SUCCESS_MESSAGE);
        else
            return Result.send(UNAUTHORIZED, null, TOKEN_INVAILD_MESSAGE);
    }
}
