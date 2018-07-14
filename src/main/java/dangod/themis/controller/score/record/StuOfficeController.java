package dangod.themis.controller.score.record;

import dangod.themis.controller.base.BaseController;
import dangod.themis.controller.base.annotation.Authorization;
import dangod.themis.controller.base.annotation.ContainAuthority;
import dangod.themis.controller.base.annotation.score.*;
import dangod.themis.controller.base.annotation.score.Class;
import dangod.themis.core.result.Result;
import dangod.themis.model.vo.score.record.*;
import dangod.themis.service.score.StudentBaseInfoService;
import dangod.themis.service.score.StudentRecordService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import static dangod.themis.controller.base.constant.AnnotationConstant.AUTHORIZATION;
import static dangod.themis.controller.base.constant.Message.PERMISSIN_DENIED_MESSAGE;
import static dangod.themis.controller.base.constant.Message.STU_FAIL_MESSAGE;
import static dangod.themis.controller.base.constant.Message.STU_SUCCESS_MESSAGE;
import static dangod.themis.controller.base.constant.Status.FAIL;
import static dangod.themis.controller.base.constant.Status.PERMISSIN_DENIED;
import static dangod.themis.controller.base.constant.Status.SUCCESS;
import static dangod.themis.model.po.authority.constant.TypeContant.*;
import static org.springframework.web.bind.annotation.RequestMethod.GET;


@CrossOrigin
@RestController
@RequestMapping(value = "/stu/office")
public class StuOfficeController extends BaseController{
    @Autowired
    private StudentRecordService recordService;
    @Autowired
    private StudentBaseInfoService studentBaseInfoService;

    @RequestMapping(method = GET)
    @ApiOperation(value = "用户获取学生Office信息(按学期)")
    @ContainAuthority(SELF_STUDENT)
    @Authorization
    public String getStudentOfficeByTerm(HttpServletRequest request, HttpServletResponse response,
                                    @RequestHeader(AUTHORIZATION)String token,
                                    @RequestParam("term")String term,
                                    @RequestParam("page")Integer page){
        int size = DEFAULT_SIZE;
        if(getParameter(request, "size") != null)
            size = Integer.parseInt(getParameter(request, "size"));
        List<OfficeVo> list = recordService.getOfficeByUserIdAndTerm(getUserId(request), term, page, size);
        if(list == null)
            return Result.send(FAIL, null, STU_FAIL_MESSAGE);
        return Result.send(SUCCESS, list, STU_SUCCESS_MESSAGE);
    }

    @RequestMapping(value = "/school", method = GET)
    @ApiOperation(value = "校级管理员获取学生Office信息(按学期)")
    @ContainAuthority(SCHOOL_STUDENT_MANAGE)
    @Authorization
    public String schoolAdminGetStudentOfficeByTerm(HttpServletRequest request, HttpServletResponse response,
                                    @RequestHeader(AUTHORIZATION)String token,
                                    @RequestParam("stuId")String stuId,
                                    @RequestParam("term")String term,
                                    @RequestParam("page")Integer page){
        int size = DEFAULT_SIZE;
        if(getParameter(request, "size") != null)
            size = Integer.parseInt(getParameter(request, "size"));
        List<OfficeVo> list = recordService.getOfficeByStuIdAndTerm(stuId, term, page, size);
        if(list == null)
            return Result.send(FAIL, null, STU_FAIL_MESSAGE);
        return Result.send(SUCCESS, list, STU_SUCCESS_MESSAGE);
    }

    @RequestMapping(value = "/class", method = GET)
    @ApiOperation(value = "班级管理员获取学生Office信息(按学期)")
    @ContainAuthority(CLASS_STUDENT_MANAGE)
    @Class
    @Authorization
    public String classAdminGetStudentOfficeByTerm(HttpServletRequest request, HttpServletResponse response,
                                    @RequestHeader(AUTHORIZATION)String token,
                                    @RequestParam("stuId")String stuId,
                                    @RequestParam("term")String term,
                                    @RequestParam("page")Integer page){
        if(!studentBaseInfoService.checkStuClass(stuId, getManageClass(request)))
            return Result.send(PERMISSIN_DENIED, null, PERMISSIN_DENIED_MESSAGE);
        int size = DEFAULT_SIZE;
        if(getParameter(request, "size") != null)
            size = Integer.parseInt(getParameter(request, "size"));
        List<OfficeVo> list = recordService.getOfficeByStuIdAndTerm(stuId, term, page, size);
        if(list == null)
            return Result.send(FAIL, null, STU_FAIL_MESSAGE);
        return Result.send(SUCCESS, list, STU_SUCCESS_MESSAGE);
    }
            
    @RequestMapping(value = "/major", method = GET)
    @ApiOperation(value = "专业管理员获取学生Office信息(按学期)")
    @ContainAuthority(MAJOR_STUDENT_MANAGE)
    @Major
    @Authorization
    public String majorAdminGetStudentOfficeByTerm(HttpServletRequest request, HttpServletResponse response,
                                    @RequestHeader(AUTHORIZATION)String token,
                                    @RequestParam("stuId")String stuId,
                                    @RequestParam("term")String term,
                                    @RequestParam("page")Integer page){
        if(!studentBaseInfoService.checkStuMajor(stuId, getManageMajor(request)))
            return Result.send(PERMISSIN_DENIED, null, PERMISSIN_DENIED_MESSAGE);
        int size = DEFAULT_SIZE;
        if(getParameter(request, "size") != null)
            size = Integer.parseInt(getParameter(request, "size"));
        List<OfficeVo> list = recordService.getOfficeByStuIdAndTerm(stuId, term, page, size);
        if(list == null)
            return Result.send(FAIL, null, STU_FAIL_MESSAGE);
        return Result.send(SUCCESS, list, STU_SUCCESS_MESSAGE);
    }
}
