package dangod.themis.controller.score;

import dangod.themis.controller.base.BaseController;

import dangod.themis.controller.base.annotation.Authorization;
import dangod.themis.controller.base.annotation.ContainAuthority;
import dangod.themis.core.result.Result;
import dangod.themis.model.vo.score.ClassVo;
import dangod.themis.model.vo.score.MajorVo;
import dangod.themis.service.score.ClassService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import static dangod.themis.controller.base.constant.AnnotationConstant.AUTHORIZATION;
import static dangod.themis.controller.base.constant.Message.STU_BASEINFO_FAIL_MESSAGE;
import static dangod.themis.controller.base.constant.Message.STU_BASEINFO_SUCCESS_MESSAGE;
import static dangod.themis.controller.base.constant.Status.FAIL;
import static dangod.themis.controller.base.constant.Status.SUCCESS;
import static dangod.themis.model.po.authority.constant.TypeContant.SCHOOL_STUDENT_MANAGE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@CrossOrigin
@RestController
@RequestMapping(value = "/stu")
public class ClassController extends BaseController{
    @Autowired
    private ClassService classService;

    @RequestMapping(value = "/year",method = GET)
    @ApiOperation(value = "获取当前所有年级")
    @ContainAuthority(SCHOOL_STUDENT_MANAGE)
    @Authorization
    public String getAllYear(HttpServletRequest request, HttpServletResponse response,
                                     @RequestHeader(AUTHORIZATION)String token){
        List<Integer> list = classService.getYearList();
        if(list == null)
            return Result.send(FAIL, null, STU_BASEINFO_FAIL_MESSAGE);
        return Result.send(SUCCESS, list, STU_BASEINFO_SUCCESS_MESSAGE);
    }

    @RequestMapping(value = "/major",method = GET)
    @ApiOperation(value = "获取指定年级所有专业")
    @ContainAuthority(SCHOOL_STUDENT_MANAGE)
    @Authorization
    public String getAllMajorByYear(HttpServletRequest request, HttpServletResponse response,
                                     @RequestHeader(AUTHORIZATION)String token,
                                     @RequestParam("year")Integer year){
        List<MajorVo> majorVoList = classService.getMajorList(year);
        if(majorVoList == null)
            return Result.send(FAIL, null, STU_BASEINFO_FAIL_MESSAGE);
        return Result.send(SUCCESS, majorVoList, STU_BASEINFO_SUCCESS_MESSAGE);
    }

    @RequestMapping(value = "/class",method = GET)
    @ApiOperation(value = "获取指定专业所有班级")
    @ContainAuthority(SCHOOL_STUDENT_MANAGE)
    @Authorization
    public String getAllClassByMajor(HttpServletRequest request, HttpServletResponse response,
                                     @RequestHeader(AUTHORIZATION)String token,
                                     @RequestParam("majorId")long majorId){
        List<ClassVo> classVoList = classService.getClassList(majorId);
        if(classVoList == null)
            return Result.send(FAIL, null, STU_BASEINFO_FAIL_MESSAGE);
        return Result.send(SUCCESS, classVoList, STU_BASEINFO_SUCCESS_MESSAGE);
    }
}
