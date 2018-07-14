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
import static dangod.themis.controller.base.constant.Message.*;
import static dangod.themis.controller.base.constant.Message.STU_DELETE_SUCCESS_MESSAGE;
import static dangod.themis.controller.base.constant.Status.FAIL;
import static dangod.themis.controller.base.constant.Status.PERMISSIN_DENIED;
import static dangod.themis.controller.base.constant.Status.SUCCESS;
import static dangod.themis.model.po.authority.constant.TypeContant.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;


@CrossOrigin
@RestController
@RequestMapping(value = "/stu/honor")
public class StuHonorController extends BaseController{
    @Autowired
    private StudentRecordService recordService;
    @Autowired
    private StudentBaseInfoService studentBaseInfoService;

    @RequestMapping(method = GET)
    @ApiOperation(value = "用户获取学生Honor信息(按学期)")
    @ContainAuthority(SELF_STUDENT)
    @Authorization
    public String getStudentHonorByTerm(HttpServletRequest request, HttpServletResponse response,
                                    @RequestHeader(AUTHORIZATION)String token,
                                    @RequestParam("term")String term,
                                    @RequestParam("page")Integer page){
        int size = DEFAULT_SIZE;
        if(getParameter(request, "size") != null)
            size = Integer.parseInt(getParameter(request, "size"));
        List<HonorVo> list = recordService.getHonorByUserIdAndTerm(getUserId(request), term, page, size);
        if(list == null)
            return Result.send(FAIL, null, STU_FAIL_MESSAGE);
        return Result.send(SUCCESS, list, STU_SUCCESS_MESSAGE);
    }

    @RequestMapping(value = "/school", method = GET)
    @ApiOperation(value = "校级管理员获取学生Honor信息(按学期)")
    @ContainAuthority(SCHOOL_STUDENT_MANAGE)
    @Authorization
    public String schoolAdminGetStudentHonorByTerm(HttpServletRequest request, HttpServletResponse response,
                                    @RequestHeader(AUTHORIZATION)String token,
                                    @RequestParam("stuId")String stuId,
                                    @RequestParam("term")String term,
                                    @RequestParam("page")Integer page){
        int size = DEFAULT_SIZE;
        if(getParameter(request, "size") != null)
            size = Integer.parseInt(getParameter(request, "size"));
        List<HonorVo> list = recordService.getHonorByStuIdAndTerm(stuId, term, page, size);
        if(list == null)
            return Result.send(FAIL, null, STU_FAIL_MESSAGE);
        return Result.send(SUCCESS, list, STU_SUCCESS_MESSAGE);
    }

    @RequestMapping(value = "/school", method = DELETE)
    @ApiOperation(value = "校级管理员删除学生Honor信息")
    @ContainAuthority(SCHOOL_STUDENT_MANAGE)
    @Authorization
    public String schoolAdminDeleteStudentHonorByTerm(HttpServletRequest request, HttpServletResponse response,
                                                      @RequestHeader(AUTHORIZATION)String token,
                                                      @RequestParam("record")Long recordId) {
        Integer status = recordService.deleteHonor(recordId);
        if(status == -1)
            return Result.send(FAIL, null, STU_DELETE_FAIL_MESSAGE);
        return Result.send(SUCCESS, null, STU_DELETE_SUCCESS_MESSAGE);
    }
    @RequestMapping(value = "/school", method = POST)
    @ApiOperation(value = "校级管理员添加学生Honor信息")
    @ContainAuthority(SCHOOL_STUDENT_MANAGE)
    @Authorization
    public String schoolAdminAddStudentHonorByTerm(HttpServletRequest request, HttpServletResponse response,
                                                      @RequestHeader(AUTHORIZATION)String token,
                                                      @RequestParam("stuId")String stuId,
                                                      @RequestParam("honorName")String activityName,
                                                      @RequestParam("honorLv")Integer honorLv,
                                                      @RequestParam("honorScore")Double honorScore,
                                                      @RequestParam("common")String common){
        Integer status = recordService.addHonor(stuId,activityName, honorLv, honorScore, common);
        if(status == -1)
            return Result.send(FAIL, null, STU_UPDATE_FAIL_MESSAGE);
        return Result.send(SUCCESS, null, STU_UPDATE_SUCCESS_MESSAGE);
    }

    @RequestMapping(value = "/school", method = PUT)
    @ApiOperation(value = "校级管理员修改学生Honor信息")
    @ContainAuthority(SCHOOL_STUDENT_MANAGE)
    @Authorization
    public String schoolAdminUpdateStudentHonorByTerm(HttpServletRequest request, HttpServletResponse response,
                                                         @RequestHeader(AUTHORIZATION)String token,
                                                         @RequestParam("recordId")Long recordId,
                                                         @RequestParam("honorName")String activityName,
                                                         @RequestParam("honorLv")Integer honorLv,
                                                         @RequestParam("honorScore")Double honorScore,
                                                         @RequestParam("common")String common){
        Integer status = recordService.updateHonor(recordId,activityName, honorLv, honorScore, common);
        if(status == -1)
            return Result.send(FAIL, null, STU_UPDATE_FAIL_MESSAGE);
        return Result.send(SUCCESS, null, STU_UPDATE_SUCCESS_MESSAGE);
    }

    @RequestMapping(value = "/class", method = GET)
    @ApiOperation(value = "班级管理员获取学生Honor信息(按学期)")
    @ContainAuthority(CLASS_STUDENT_MANAGE)
    @Class
    @Authorization
    public String classAdminGetStudentHonorByTerm(HttpServletRequest request, HttpServletResponse response,
                                    @RequestHeader(AUTHORIZATION)String token,
                                    @RequestParam("stuId")String stuId,
                                    @RequestParam("term")String term,
                                    @RequestParam("page")Integer page){
        if(!studentBaseInfoService.checkStuClass(stuId, getManageClass(request)))
            return Result.send(PERMISSIN_DENIED, null, PERMISSIN_DENIED_MESSAGE);
        int size = DEFAULT_SIZE;
        if(getParameter(request, "size") != null)
            size = Integer.parseInt(getParameter(request, "size"));
        List<HonorVo> list = recordService.getHonorByStuIdAndTerm(stuId, term, page, size);
        if(list == null)
            return Result.send(FAIL, null, STU_FAIL_MESSAGE);
        return Result.send(SUCCESS, list, STU_SUCCESS_MESSAGE);
    }

    @RequestMapping(value = "/class", method = DELETE)
    @ApiOperation(value = "班级管理员删除学生Honor信息")
    @ContainAuthority(CLASS_STUDENT_MANAGE)
    @Class
    @Authorization
    public String classAdminDeleteStudentHonorByTerm(HttpServletRequest request, HttpServletResponse response,
                                                     @RequestHeader(AUTHORIZATION)String token,
                                                     @RequestParam("stuId")String stuId,
                                                     @RequestParam("record")Long recordId) {
        if(!studentBaseInfoService.checkStuClass(stuId, getManageClass(request)))
            return Result.send(PERMISSIN_DENIED, null, PERMISSIN_DENIED_MESSAGE);
        Integer status = recordService.deleteHonor(recordId);
        if(status == -1)
            return Result.send(FAIL, null, STU_DELETE_FAIL_MESSAGE);
        return Result.send(SUCCESS, null, STU_DELETE_SUCCESS_MESSAGE);
    }
            
    @RequestMapping(value = "/major", method = GET)
    @ApiOperation(value = "专业管理员获取学生Honor信息(按学期)")
    @ContainAuthority(MAJOR_STUDENT_MANAGE)
    @Major
    @Authorization
    public String majorAdminGetStudentHonorByTerm(HttpServletRequest request, HttpServletResponse response,
                                    @RequestHeader(AUTHORIZATION)String token,
                                    @RequestParam("stuId")String stuId,
                                    @RequestParam("term")String term,
                                    @RequestParam("page")Integer page){
        if(!studentBaseInfoService.checkStuMajor(stuId, getManageMajor(request)))
            return Result.send(PERMISSIN_DENIED, null, PERMISSIN_DENIED_MESSAGE);
        int size = DEFAULT_SIZE;
        if(getParameter(request, "size") != null)
            size = Integer.parseInt(getParameter(request, "size"));
        List<HonorVo> list = recordService.getHonorByStuIdAndTerm(stuId, term, page, size);
        if(list == null)
            return Result.send(FAIL, null, STU_FAIL_MESSAGE);
        return Result.send(SUCCESS, list, STU_SUCCESS_MESSAGE);
    }

    @RequestMapping(value = "/major", method = DELETE)
    @ApiOperation(value = "专业管理员删除学生Honor信息(按学期)")
    @ContainAuthority(MAJOR_STUDENT_MANAGE)
    @Major
    @Authorization
    public String majorAdminGetStudentHonorByTerm(HttpServletRequest request, HttpServletResponse response,
                                                  @RequestHeader(AUTHORIZATION)String token,
                                                  @RequestParam("record")Long recordId) {
        Integer status = recordService.deleteHonor(recordId);
        if(status == -1)
            return Result.send(FAIL, null, STU_DELETE_FAIL_MESSAGE);
        return Result.send(SUCCESS, null, STU_DELETE_SUCCESS_MESSAGE);
    }

    @RequestMapping(value = "/major", method = POST)
    @ApiOperation(value = "专业管理员添加学生Honor信息")
    @ContainAuthority(MAJOR_STUDENT_MANAGE)
    @Major
    @Authorization
    public String majorAdminAddStudentHonorByTerm(HttpServletRequest request, HttpServletResponse response,
                                                   @RequestHeader(AUTHORIZATION)String token,
                                                   @RequestParam("stuId")String stuId,
                                                   @RequestParam("honorName")String activityName,
                                                   @RequestParam("honorLv")Integer honorLv,
                                                   @RequestParam("honorScore")Double honorScore,
                                                   @RequestParam("common")String common){
        Integer status = recordService.addHonor(stuId,activityName, honorLv, honorScore, common);
        if(status == -1)
            return Result.send(FAIL, null, STU_UPDATE_FAIL_MESSAGE);
        return Result.send(SUCCESS, null, STU_UPDATE_SUCCESS_MESSAGE);
    }

    @RequestMapping(value = "/major", method = PUT)
    @ApiOperation(value = "专业管理员修改学生Honor信息")
    @ContainAuthority(MAJOR_STUDENT_MANAGE)
    @Major
    @Authorization
    public String majorAdminUpdateStudentHonorByTerm(HttpServletRequest request, HttpServletResponse response,
                                                      @RequestHeader(AUTHORIZATION)String token,
                                                      @RequestParam("recordId")Long recordId,
                                                      @RequestParam("honorName")String activityName,
                                                      @RequestParam("honorLv")Integer honorLv,
                                                      @RequestParam("honorScore")Double honorScore,
                                                      @RequestParam("common")String common){
        Integer status = recordService.updateHonor(recordId,activityName, honorLv, honorScore, common);
        if(status == -1)
            return Result.send(FAIL, null, STU_UPDATE_FAIL_MESSAGE);
        return Result.send(SUCCESS, null, STU_UPDATE_SUCCESS_MESSAGE);
    }
    @RequestMapping(value = "/class", method = POST)
    @ApiOperation(value = "班级管理员添加学生Honor信息")
    @ContainAuthority(CLASS_STUDENT_MANAGE)
    @Class
    @Authorization
    public String classAdminAddStudentHonorByTerm(HttpServletRequest request, HttpServletResponse response,
                                                  @RequestHeader(AUTHORIZATION)String token,
                                                  @RequestParam("stuId")String stuId,
                                                  @RequestParam("honorName")String activityName,
                                                  @RequestParam("honorLv")Integer honorLv,
                                                  @RequestParam("honorScore")Double honorScore,
                                                  @RequestParam("common")String common){
        Integer status = recordService.addHonor(stuId,activityName, honorLv, honorScore, common);
        if(status == -1)
            return Result.send(FAIL, null, STU_UPDATE_FAIL_MESSAGE);
        return Result.send(SUCCESS, null, STU_UPDATE_SUCCESS_MESSAGE);
    }

    @RequestMapping(value = "/class", method = PUT)
    @ApiOperation(value = "班级管理员修改学生Honor信息")
    @ContainAuthority(CLASS_STUDENT_MANAGE)
    @Class
    @Authorization
    public String classAdminUpdateStudentHonorByTerm(HttpServletRequest request, HttpServletResponse response,
                                                     @RequestHeader(AUTHORIZATION)String token,
                                                     @RequestParam("recordId")Long recordId,
                                                     @RequestParam("honorName")String activityName,
                                                     @RequestParam("honorLv")Integer honorLv,
                                                     @RequestParam("honorScore")Double honorScore,
                                                     @RequestParam("common")String common){
        Integer status = recordService.updateHonor(recordId,activityName, honorLv, honorScore, common);
        if(status == -1)
            return Result.send(FAIL, null, STU_UPDATE_FAIL_MESSAGE);
        return Result.send(SUCCESS, null, STU_UPDATE_SUCCESS_MESSAGE);
    }
}
