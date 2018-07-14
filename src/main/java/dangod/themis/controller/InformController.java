package dangod.themis.controller;

import dangod.themis.controller.base.BaseController;
import dangod.themis.controller.base.annotation.Authorization;
import dangod.themis.controller.base.annotation.ContainAuthority;
import dangod.themis.core.result.Result;
import dangod.themis.model.vo.InformVo;
import dangod.themis.service.common.InformService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import static dangod.themis.controller.base.constant.Message.*;
import static dangod.themis.controller.base.constant.AnnotationConstant.AUTHORIZATION;
import static dangod.themis.controller.base.constant.Status.*;
import static dangod.themis.model.po.authority.constant.TypeContant.INFORM_MANAGE;
import static dangod.themis.model.po.authority.constant.TypeContant.INFORM_SELF;
import static dangod.themis.model.po.authority.constant.TypeContant.INFORM_SEND;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/inform")
public class InformController extends BaseController{
    @Autowired
    private InformService informService;

    @RequestMapping(value = "/id", method = GET)
    @ApiOperation(value = "通过id获取通知")
    public String getInformById(HttpServletRequest request, HttpServletResponse response,
                                    @RequestParam("id")Long id){
        InformVo informVo = informService.getInformById(id);
        if(informVo == null) return Result.send(NOT_FIND, null, INFORM_NOT_EXISTS_MESSAGE);
        return Result.send(SUCCESS, informVo, INFORM_SUCCESS_MESSAGE);
    }

    @RequestMapping(value = "/page", method = GET)
    @ApiOperation(value = "分页获取通知")
    public String getInformByPage(HttpServletRequest request, HttpServletResponse response,
                                    @RequestParam("page")Integer page){
        int size = DEFAULT_SIZE;
        if(getParameter(request, "size") != null)
            size = Integer.parseInt(getParameter(request, "size"));
        List<InformVo> list = informService.getPageInform(page, size);
        if(list == null) return Result.send(NOT_FIND, null, INFORM_NOT_EXISTS_MESSAGE);
        return Result.send(SUCCESS, list, INFORM_SUCCESS_MESSAGE);
    }

    @RequestMapping(value = "/user", method = GET)
    @ApiOperation(value = "获取自己已发通知")
    @Authorization
    @ContainAuthority(INFORM_SELF)
    public String getInformBySelf(HttpServletRequest request, HttpServletResponse response,
                                    @RequestHeader(AUTHORIZATION)String token,
                                    @RequestParam("page")Integer page){
        int size = DEFAULT_SIZE;
        if(getParameter(request, "size") != null)
            size = Integer.parseInt(getParameter(request, "size"));
        List<InformVo> list = informService.getListByUserId(getUserId(request), page, size);
        if(list == null) return Result.send(NOT_FIND, null, INFORM_NOT_EXISTS_MESSAGE);
        return Result.send(SUCCESS, list, INFORM_SUCCESS_MESSAGE);
    }

    @RequestMapping(value = "/admin", method = GET)
    @ApiOperation(value = "管理通过userId获取通知")
    @Authorization
    @ContainAuthority(INFORM_MANAGE)
    public String getInformByUserId(HttpServletRequest request, HttpServletResponse response,
                                    @RequestHeader(AUTHORIZATION)String token,
                                    @RequestParam("userId")Long userId,
                                    @RequestParam("page")Integer page){
        int size = DEFAULT_SIZE;
        if(getParameter(request, "size") != null)
            size = Integer.parseInt(getParameter(request, "size"));
        List<InformVo> list = informService.getListByUserId(userId, page, size);
        if(list == null) return Result.send(NOT_FIND, null, INFORM_NOT_EXISTS_MESSAGE);
        return Result.send(SUCCESS, list, INFORM_SUCCESS_MESSAGE);
    }

    @RequestMapping(method = POST)
    @ApiOperation(value = "发送通知")
    @Authorization
    @ContainAuthority(INFORM_SEND)
    public String getInformByUserId(HttpServletRequest request, HttpServletResponse response,
                                    @RequestHeader(AUTHORIZATION)String token,
                                    @RequestParam("title")String title,
                                    @RequestParam("content")String content){
        InformVo informVo = informService.addInform(title, content, getUserId(request));
        if(informVo == null) return Result.send(FAIL, null, INFORM_SEND_FAIL_MESSAGE);
        return Result.send(SUCCESS, informVo, INFORM_SEND_SUCCESS_MESSAGE);
    }

    @RequestMapping(method = PUT)
    @ApiOperation(value = "修改通知")
    @Authorization
    public String updateInformByUserId(HttpServletRequest request, HttpServletResponse response,
                                    @RequestHeader(AUTHORIZATION)String token,
                                    @RequestParam("informId")long id,
                                    @RequestParam("title")String title,
                                    @RequestParam("content")String content){
        if(informService.updateInformValid(id, getUserId(request))){
            InformVo informVo = informService.updateInform(id, title, content, getUserId(request));
            if(informVo == null) return Result.send(FAIL, null, INFORM_UPDATE_FAIL_MESSAGE);
            return Result.send(SUCCESS, informVo, INFORM_UPDATE_SUCCESS_MESSAGE);
        }else{
            return Result.send(PERMISSIN_DENIED, null, PERMISSIN_DENIED_MESSAGE);
        }
    }

    @RequestMapping(value = "/admin", method = PUT)
    @ApiOperation(value = "管理员修改通知")
    @ContainAuthority(INFORM_MANAGE)
    @Authorization
    public String updateInformByAdmin(HttpServletRequest request, HttpServletResponse response,
                                    @RequestHeader(AUTHORIZATION)String token,
                                    @RequestParam("informId")long id,
                                    @RequestParam("title")String title,
                                    @RequestParam("content")String content){
        InformVo informVo = informService.updateInform(id, title, content, getUserId(request));
        if(informVo == null) return Result.send(FAIL, null, INFORM_UPDATE_FAIL_MESSAGE);
        return Result.send(SUCCESS, informVo, INFORM_UPDATE_SUCCESS_MESSAGE);
    }

    @RequestMapping(method = DELETE)
    @ApiOperation(value = "删除通知")
    @Authorization
    public String deleteInformByUserId(HttpServletRequest request, HttpServletResponse response,
                                    @RequestHeader(AUTHORIZATION)String token,
                                    @RequestParam("informId")long id){
        if(informService.updateInformValid(id, getUserId(request))){
            Integer status = informService.deleteInformById(id);
            if(status != 0) return Result.send(FAIL, null, INFORM_UPDATE_FAIL_MESSAGE);
            return Result.send(SUCCESS, null, INFORM_UPDATE_SUCCESS_MESSAGE);
        }else{
            return Result.send(PERMISSIN_DENIED, null, PERMISSIN_DENIED_MESSAGE);
        }
    }

    @RequestMapping(value = "/admin", method = DELETE)
    @ApiOperation(value = "管理员删除通知")
    @ContainAuthority(INFORM_MANAGE)
    @Authorization
    public String deleteInformByAdmin(HttpServletRequest request, HttpServletResponse response,
                                    @RequestHeader(AUTHORIZATION)String token,
                                    @RequestParam("informId")long id){
        Integer status = informService.deleteInformById(id);
        if(status != 0) return Result.send(FAIL, null, INFORM_UPDATE_FAIL_MESSAGE);
        return Result.send(SUCCESS, null, INFORM_UPDATE_SUCCESS_MESSAGE);
    }
}
