package dangod.themis.controller.score;

import dangod.themis.controller.base.BaseController;
import dangod.themis.controller.base.annotation.Authorization;
import dangod.themis.controller.base.annotation.ContainAuthority;
import dangod.themis.controller.base.annotation.score.Class;
import dangod.themis.controller.base.annotation.score.Major;
import dangod.themis.core.result.Result;
import dangod.themis.model.vo.score.StudentBaseInfoVo;
import dangod.themis.service.score.StudentBaseInfoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import static dangod.themis.controller.base.constant.AnnotationConstant.AUTHORIZATION;
import static dangod.themis.controller.base.constant.Message.*;
import static dangod.themis.controller.base.constant.Status.FAIL;
import static dangod.themis.controller.base.constant.Status.PERMISSIN_DENIED;
import static dangod.themis.controller.base.constant.Status.SUCCESS;
import static dangod.themis.model.po.authority.constant.TypeContant.*;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;
@CrossOrigin
@RestController
@RequestMapping(value = "/stu/base")
public class StuBaseController extends BaseController{
    @Autowired
    private StudentBaseInfoService studentBaseInfoService;

    @RequestMapping(method = GET)
    @ApiOperation(value = "用户获取学生信息")
    @ContainAuthority(SELF_STUDENT)
    @Authorization
    public String getStudentBaseInfo(HttpServletRequest request, HttpServletResponse response,
                                     @RequestHeader(AUTHORIZATION)String token){
        StudentBaseInfoVo baseInfoVo = studentBaseInfoService.getStudentBaseByUserId(getUserId(request));
        if(baseInfoVo == null)
            return Result.send(FAIL, null, STU_BASEINFO_FAIL_MESSAGE);
        return Result.send(SUCCESS, baseInfoVo, STU_BASEINFO_SUCCESS_MESSAGE);
    }

//    @RequestMapping(value = "school/user",method = GET)
//    @ApiOperation(value = "校级管理员获取学生信息(userId)")
//    @ContainAuthority(SCHOOL_STUDENT_MANAGE)
//    @Authorization
//    public String getStudentBaseInfoSchoolAdminByUser(HttpServletRequest request, HttpServletResponse response,
//                                                      @RequestParam("id")long id){
//        StudentBaseInfoVo baseInfoVo = studentBaseInfoService.getStudentBaseByUserId(id);
//        if(baseInfoVo == null) return Result.send(FAIL, null, STU_BASEINFO_FAIL_MESSAGE);
//        return Result.send(SUCCESS, baseInfoVo, STU_BASEINFO_SUCCESS_MESSAGE);
//    }

    @RequestMapping(value = "school/stuid",method = GET)
    @ApiOperation(value = "校级管理员获取学生信息(stuid)")
    @ContainAuthority(SCHOOL_STUDENT_MANAGE)
    @Authorization
    public String getStudentBaseInfoSchoolAdminBystuId(HttpServletRequest request, HttpServletResponse response,
                                                       @RequestHeader(AUTHORIZATION)String token,
                                                       @RequestParam("stuId")String stuId){
        StudentBaseInfoVo baseInfoVo = studentBaseInfoService.getStudentBaseByStuId(stuId);
        if(baseInfoVo == null) return Result.send(FAIL, null, STU_BASEINFO_FAIL_MESSAGE);
        return Result.send(SUCCESS, baseInfoVo, STU_BASEINFO_SUCCESS_MESSAGE);
    }

    @RequestMapping(value = "school/class",method = GET)
    @ApiOperation(value = "校级管理员获取学生信息(class)")
    @ContainAuthority(SCHOOL_STUDENT_MANAGE)
    @Authorization
    public String getStudentBaseInfoSchoolAdminByClass(HttpServletRequest request, HttpServletResponse response,
                                                       @RequestHeader(AUTHORIZATION)String token,
                                                       @RequestParam("id")long id,
                                                       @RequestParam("page")Integer page){
        int size = DEFAULT_SIZE;
        if(getParameter(request, "size") != null)
            size = Integer.parseInt(getParameter(request, "size"));
        List<StudentBaseInfoVo> list = studentBaseInfoService.getStudentListBaseByClass(id, page, size);
        if(list == null) return Result.send(FAIL, null, STU_BASEINFO_FAIL_MESSAGE);
        return Result.send(SUCCESS, list, STU_BASEINFO_SUCCESS_MESSAGE);
    }

    @RequestMapping(value = "school/major",method = GET)
    @ApiOperation(value = "校级管理员获取学生信息(major)")
    @ContainAuthority(SCHOOL_STUDENT_MANAGE)
    @Authorization
    public String getStudentBaseInfoSchoolAdminByMajor(HttpServletRequest request, HttpServletResponse response,
                                                       @RequestHeader(AUTHORIZATION)String token,
                                                       @RequestParam("id")long id,
                                                       @RequestParam("page")Integer page){
        int size = DEFAULT_SIZE;
        if(getParameter(request, "size") != null)
            size = Integer.parseInt(getParameter(request, "size"));
        List<StudentBaseInfoVo> list = studentBaseInfoService.getStudentListBaseByMajor(id, page, size);
        if(list == null) return Result.send(FAIL, null, STU_BASEINFO_FAIL_MESSAGE);
        return Result.send(SUCCESS, list, STU_BASEINFO_SUCCESS_MESSAGE);
    }

    @RequestMapping(value = "school/dormitory",method = GET)
    @ApiOperation(value = "校级管理员获取学生信息(dormitory)")
    @ContainAuthority(SCHOOL_STUDENT_MANAGE)
    @Authorization
    public String getStudentBaseInfoSchoolAdminByDormitory(HttpServletRequest request, HttpServletResponse response,
                                                           @RequestHeader(AUTHORIZATION)String token,
                                                           @RequestParam("id")long id,
                                                           @RequestParam("page")Integer page){
        int size = DEFAULT_SIZE;
        if(getParameter(request, "size") != null)
            size = Integer.parseInt(getParameter(request, "size"));
        List<StudentBaseInfoVo> list = studentBaseInfoService.getStudentListBaseByDormitory(id, page, size);
        if(list == null) return Result.send(FAIL, null, STU_BASEINFO_FAIL_MESSAGE);
        return Result.send(SUCCESS, list, STU_BASEINFO_SUCCESS_MESSAGE);
    }

    @RequestMapping(value = "school",method = GET)
    @ApiOperation(value = "校级管理员获取学生信息列表")
    @ContainAuthority(SCHOOL_STUDENT_MANAGE)
    @Authorization
    public String getStudentBaseInfoSchoolAdmin(HttpServletRequest request, HttpServletResponse response,
                                                @RequestHeader(AUTHORIZATION)String token,
                                                @RequestParam("page")Integer page){
        int size = DEFAULT_SIZE;
        if(getParameter(request, "size") != null)
            size = Integer.parseInt(getParameter(request, "size"));
        List<StudentBaseInfoVo> list = studentBaseInfoService.getStudentListBaseAll(page, size);
        if(list == null) return Result.send(FAIL, null, STU_BASEINFO_FAIL_MESSAGE);
        return Result.send(SUCCESS, list, STU_BASEINFO_SUCCESS_MESSAGE);
    }

    @RequestMapping(value = "class/stuid",method = GET)
    @ApiOperation(value = "班级管理员获取学生信息(stuid)")
    @Authorization
    @ContainAuthority(CLASS_STUDENT_MANAGE)
    @Class
    public String getStudentBaseInfoClassAdminBystuId(HttpServletRequest request, HttpServletResponse response,
                                                      @RequestHeader(AUTHORIZATION)String token,
                                                      @RequestParam("stuId")String stuId){
        if(!studentBaseInfoService.checkStuClass(stuId, getManageClass(request)))
            return Result.send(PERMISSIN_DENIED, null, PERMISSIN_DENIED_MESSAGE);
        StudentBaseInfoVo baseInfoVo = studentBaseInfoService.getStudentBaseByStuId(stuId);
        if(baseInfoVo == null) return Result.send(FAIL, null, STU_BASEINFO_FAIL_MESSAGE);
        return Result.send(SUCCESS, baseInfoVo, STU_BASEINFO_SUCCESS_MESSAGE);
    }

    @RequestMapping(value = "class",method = GET)
    @ApiOperation(value = "班级管理员获取学生信息列表")
    @Authorization
    @ContainAuthority(CLASS_STUDENT_MANAGE)
    @Class
    public String getStudentBaseInfoListClassAdmin(HttpServletRequest request, HttpServletResponse response,
                                                   @RequestHeader(AUTHORIZATION)String token,
                                                   @RequestParam("page")Integer page){
        int size = DEFAULT_SIZE;
        if(getParameter(request, "size") != null)
            size = Integer.parseInt(getParameter(request, "size"));
        List<StudentBaseInfoVo> list = studentBaseInfoService.getStudentListBaseByClass(getManageClass(request), page, size);
        if(list == null) return Result.send(FAIL, null, STU_BASEINFO_FAIL_MESSAGE);
        return Result.send(SUCCESS, list, STU_BASEINFO_SUCCESS_MESSAGE);
    }

    @RequestMapping(value = "major/stuid",method = GET)
    @ApiOperation(value = "专业管理员获取学生信息(stuid)")
    @Authorization
    @ContainAuthority(MAJOR_STUDENT_MANAGE)
    @Major
    public String getStudentBaseInfoMajorAdminBystuId(HttpServletRequest request, HttpServletResponse response,
                                                      @RequestHeader(AUTHORIZATION)String token,
                                                      @RequestParam("stuId")String stuId){
        if(!studentBaseInfoService.checkStuMajor(stuId, getManageMajor(request)))
            return Result.send(PERMISSIN_DENIED, null, PERMISSIN_DENIED_MESSAGE);
        StudentBaseInfoVo baseInfoVo = studentBaseInfoService.getStudentBaseByStuId(stuId);
        if(baseInfoVo == null) return Result.send(FAIL, null, STU_BASEINFO_FAIL_MESSAGE);
        return Result.send(SUCCESS, baseInfoVo, STU_BASEINFO_SUCCESS_MESSAGE);
    }

    @RequestMapping(value = "major/class",method = GET)
    @ApiOperation(value = "专业管理员获取学生信息(class)")
    @Authorization
    @ContainAuthority(MAJOR_STUDENT_MANAGE)
    @Major
    public String getStudentBaseInfoMajorAdminByClass(HttpServletRequest request, HttpServletResponse response,
                                                      @RequestHeader(AUTHORIZATION)String token,
                                                      @RequestParam("id")long id,
                                                      @RequestParam("page")Integer page){
        if(!studentBaseInfoService.checkClassMajor(id, getManageMajor(request)))
            return Result.send(PERMISSIN_DENIED, null, PERMISSIN_DENIED_MESSAGE);
        int size = DEFAULT_SIZE;
        if(getParameter(request, "size") != null)
            size = Integer.parseInt(getParameter(request, "size"));
        List<StudentBaseInfoVo> list = studentBaseInfoService.getStudentListBaseByClass(id, page, size);
        if(list == null) return Result.send(FAIL, null, STU_BASEINFO_FAIL_MESSAGE);
        return Result.send(SUCCESS, list, STU_BASEINFO_SUCCESS_MESSAGE);

    }

    @RequestMapping(value = "major",method = GET)
    @ApiOperation(value = "专业管理员获取学生信息列表")
    @Authorization
    @ContainAuthority(MAJOR_STUDENT_MANAGE)
    @Major
    public String getStudentBaseInfoListMajorAdmin(HttpServletRequest request, HttpServletResponse response,
                                                   @RequestHeader(AUTHORIZATION)String token,
                                                   @RequestParam("page")Integer page){
        int size = DEFAULT_SIZE;
        if(getParameter(request, "size") != null)
            size = Integer.parseInt(getParameter(request, "size"));
        List<StudentBaseInfoVo> list = studentBaseInfoService.getStudentListBaseByMajor(getManageMajor(request), page, size);
        if(list == null) return Result.send(FAIL, null, STU_BASEINFO_FAIL_MESSAGE);
        return Result.send(SUCCESS, list, STU_BASEINFO_SUCCESS_MESSAGE);

    }

    @RequestMapping(value = "school/stuid",method = PUT)
    @ApiOperation(value = "校级管理员修改学生信息(stuid),班级和寝室缺省传-1")
    @ContainAuthority(SCHOOL_STUDENT_MANAGE)
    @Authorization
    public String updateStudentBaseInfoSchoolAdminBystuId(HttpServletRequest request, HttpServletResponse response,
                                                          @RequestHeader(AUTHORIZATION)String token,
                                                          @RequestParam("stuId")String stuId,
                                                          @RequestParam("realName")String realName,
                                                          @RequestParam("sex")String sex,
                                                          @RequestParam("classId")long classId,
                                                          @RequestParam("photo")String photo,
                                                          @RequestParam("entranceTime")String entranceTime,
                                                          @RequestParam("dormitoryId")long dormitoryId,
                                                          @RequestParam("political")String political){

        StudentBaseInfoVo baseInfoVo = studentBaseInfoService.updateBaseInfo(stuId, realName, sex, classId, photo, entranceTime,
                dormitoryId, political);
        if(baseInfoVo == null) return Result.send(FAIL, null, STU_BASEINFO_UPDATE_FAIL_MESSAGE);
        return Result.send(SUCCESS, baseInfoVo, STU_BASEINFO_UPDATE_SUCCESS_MESSAGE);
    }

    @RequestMapping(value = "class/stuid",method = PUT)
    @ApiOperation(value = "班级管理员修改学生信息(stuid)")
    @Authorization
    @ContainAuthority(CLASS_STUDENT_MANAGE)
    @Class
    public String updateStudentBaseInfoClassAdminBystuId(HttpServletRequest request, HttpServletResponse response,
                                                         @RequestHeader(AUTHORIZATION)String token,
                                                         @RequestParam("stuId")String stuId,
                                                         @RequestParam("political")String political){
        if(!studentBaseInfoService.checkStuClass(stuId, getManageClass(request)))
            return Result.send(PERMISSIN_DENIED, null, PERMISSIN_DENIED_MESSAGE);
        StudentBaseInfoVo baseInfoVo = studentBaseInfoService.updateBaseInfo(stuId, political);
        if(baseInfoVo == null) return Result.send(FAIL, null, STU_BASEINFO_UPDATE_FAIL_MESSAGE);
        return Result.send(SUCCESS, baseInfoVo, STU_BASEINFO_UPDATE_SUCCESS_MESSAGE);
    }

    @RequestMapping(value = "major/stuid",method = PUT)
    @ApiOperation(value = "专业管理员修改学生信息(stuid)")
    @Authorization
    @ContainAuthority(MAJOR_STUDENT_MANAGE)
    @Major
    public String updateStudentBaseInfoMajorAdminBystuId(HttpServletRequest request, HttpServletResponse response,
                                                         @RequestHeader(AUTHORIZATION)String token,
                                                         @RequestParam("stuId")String stuId,
                                                         @RequestParam("dormitoryId")long dormitoryId,
                                                         @RequestParam("political")String political){
        if(!studentBaseInfoService.checkStuMajor(stuId, getManageMajor(request)))
            return Result.send(PERMISSIN_DENIED, null, PERMISSIN_DENIED_MESSAGE);
        StudentBaseInfoVo baseInfoVo = studentBaseInfoService.updateBaseInfo(stuId, dormitoryId, political);
        if(baseInfoVo == null) return Result.send(FAIL, null, STU_BASEINFO_UPDATE_FAIL_MESSAGE);
        return Result.send(SUCCESS, baseInfoVo, STU_BASEINFO_UPDATE_SUCCESS_MESSAGE);
    }
}
