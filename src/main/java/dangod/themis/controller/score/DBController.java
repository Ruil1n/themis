package dangod.themis.controller.score;

import dangod.themis.controller.base.BaseController;
import dangod.themis.controller.base.annotation.Authorization;
import dangod.themis.controller.base.annotation.ContainAuthority;
import dangod.themis.core.result.Result;
import dangod.themis.core.util.BaseFile;
import dangod.themis.model.po.club.Club;
import dangod.themis.model.vo.score.file.result.ImportResult;
import dangod.themis.service.club.ClubService;
import dangod.themis.service.score.StudentBaseInfoService;
import dangod.themis.service.score.StudentRecordService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import static dangod.themis.controller.base.constant.AnnotationConstant.AUTHORIZATION;
import static dangod.themis.controller.base.constant.Message.*;
import static dangod.themis.controller.base.constant.Status.FAIL;
import static dangod.themis.controller.base.constant.Status.SUCCESS;
import static dangod.themis.core.util.BaseFile.FOLDER;
import static dangod.themis.model.po.authority.constant.TypeContant.*;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@CrossOrigin
@RestController
@RequestMapping(value = "/stu/db")
public class DBController extends BaseController{
    @Autowired
    private StudentBaseInfoService studentBaseInfoService;
    @Autowired
    private StudentRecordService studentRecordService;
    @Autowired
    private ClubService clubService;

    @RequestMapping(value = "/base/file",method = POST)
    @ApiOperation(value = "数据库管理员添加学生信息")
    @Authorization
    @ContainAuthority(DB_STU_BASE)
    public String addStudentByFile(HttpServletRequest request, HttpServletResponse response,
                                   @RequestHeader(AUTHORIZATION)String token,
                                   @RequestParam("file") MultipartFile file){
        //TEST:暂时改成社团负责人上传接口
        //ImportResult result = studentBaseInfoService.addStudentBaseByFile(file, getRealName(request));
        ImportResult result = clubService.addClubManagerByFile(file, getRealName(request));
        if(result == null)
            return Result.send(FAIL, null, STU_DB_FAIL_MESSAGE);
        return Result.send(SUCCESS, result.getFailMessage(), String.format(STU_DB_SUCCESS_MESSAGE, result.getSuccessNum(), result.getFailNum()));

    }

    @RequestMapping(value = "/base/file",method = GET)
    @ApiOperation(value = "数据库管理员下载学生模板文件")
    public String downloadBaseTemplate(HttpServletRequest request, HttpServletResponse response){
        int status = BaseFile.download(response, FOLDER + "templates", "stu_base_import.xls", "学生导入模板.xls");
        if(status == -1)
            return Result.send(FAIL, null, STU_DB_DOWNLOAD_FAIL_MESSAGE);
        return Result.send(SUCCESS, null, STU_DB_DOWNLOAD_SUCCESS_MESSAGE);
    }

    @RequestMapping(value = "/activity/file",method = POST)
    @ApiOperation(value = "数据库管理员添加活动信息")
    @Authorization
    @ContainAuthority(DB_STU_ACTIVITY)
    public String addActivityByFile(HttpServletRequest request, HttpServletResponse response,
                                   @RequestHeader(AUTHORIZATION)String token,
                                   @RequestParam("file") MultipartFile file){
        ImportResult result = studentRecordService.addActivityByFile(file, getRealName(request));
        if(result == null)
            return Result.send(FAIL, null, STU_DB_FAIL_MESSAGE);
        return Result.send(SUCCESS, result.getFailMessage(), String.format(STU_DB_SUCCESS_MESSAGE, result.getSuccessNum(), result.getFailNum()));

    }

    @RequestMapping(value = "/activity/file",method = GET)
    @ApiOperation(value = "数据库管理员下载活动模板文件")
    public String downloadActivityTemplate(HttpServletRequest request, HttpServletResponse response){
        int status = BaseFile.download(response, FOLDER + "templates", "stu_activity_import.xls", "活动导入模板.xls");
        if(status == -1)
            return Result.send(FAIL, null, STU_DB_DOWNLOAD_FAIL_MESSAGE);
        return Result.send(SUCCESS, null, STU_DB_DOWNLOAD_SUCCESS_MESSAGE);
    }

    @RequestMapping(value = "/honor/file",method = POST)
    @ApiOperation(value = "数据库管理员添加荣誉信息")
    @Authorization
    @ContainAuthority(DB_STU_HONOR)
    public String addHonorByFile(HttpServletRequest request, HttpServletResponse response,
                                 @RequestHeader(AUTHORIZATION)String token,
                                 @RequestParam("file") MultipartFile file){
        ImportResult result = studentRecordService.addHonorByFile(file, getRealName(request));
        if(result == null)
            return Result.send(FAIL, null, STU_DB_FAIL_MESSAGE);
        return Result.send(SUCCESS, result.getFailMessage(), String.format(STU_DB_SUCCESS_MESSAGE, result.getSuccessNum(), result.getFailNum()));

    }

    @RequestMapping(value = "/honor/file",method = GET)
    @ApiOperation(value = "数据库管理员下载荣誉模板文件")
    public String downloadHonorTemplate(HttpServletRequest request, HttpServletResponse response){
        int status = BaseFile.download(response, FOLDER + "templates", "stu_honor_import.xls", "荣誉导入模板.xls");
        if(status == -1)
            return Result.send(FAIL, null, STU_DB_DOWNLOAD_FAIL_MESSAGE);
        return Result.send(SUCCESS, null, STU_DB_DOWNLOAD_SUCCESS_MESSAGE);
    }

    @RequestMapping(value = "/office/file",method = POST)
    @ApiOperation(value = "数据库管理员添加任职信息")
    @Authorization
    @ContainAuthority(DB_STU_OFFICE)
    public String addOfficeByFile(HttpServletRequest request, HttpServletResponse response,
                                  @RequestHeader(AUTHORIZATION)String token,
                                  @RequestParam("file") MultipartFile file){
        ImportResult result = studentRecordService.addOfficeByFile(file, getRealName(request));
        if(result == null)
            return Result.send(FAIL, null, STU_DB_FAIL_MESSAGE);
        return Result.send(SUCCESS, result.getFailMessage(), String.format(STU_DB_SUCCESS_MESSAGE, result.getSuccessNum(), result.getFailNum()));

    }

    @RequestMapping(value = "/office/file",method = GET)
    @ApiOperation(value = "数据库管理员下载任职模板文件")
    public String downloadOfficeTemplate(HttpServletRequest request, HttpServletResponse response){
        int status = BaseFile.download(response, FOLDER + "templates", "stu_office_import.xls", "任职导入模板.xls");
        if(status == -1)
            return Result.send(FAIL, null, STU_DB_DOWNLOAD_FAIL_MESSAGE);
        return Result.send(SUCCESS, null, STU_DB_DOWNLOAD_SUCCESS_MESSAGE);
    }

    @RequestMapping(value = "/practice/file",method = POST)
    @ApiOperation(value = "数据库管理员添加实践信息")
    @Authorization
    @ContainAuthority(DB_STU_PRACTICE)
    public String addPracticeByFile(HttpServletRequest request, HttpServletResponse response,
                                    @RequestHeader(AUTHORIZATION)String token,
                                    @RequestParam("file") MultipartFile file){
        ImportResult result = studentRecordService.addPracticeByFile(file, getRealName(request));
        if(result == null)
            return Result.send(FAIL, null, STU_DB_FAIL_MESSAGE);
        return Result.send(SUCCESS, result.getFailMessage(), String.format(STU_DB_SUCCESS_MESSAGE, result.getSuccessNum(), result.getFailNum()));

    }

    @RequestMapping(value = "/practice/file",method = GET)
    @ApiOperation(value = "数据库管理员下载实践模板文件")
    public String downloadPracticeTemplate(HttpServletRequest request, HttpServletResponse response){
        int status = BaseFile.download(response, FOLDER + "templates", "stu_practice_import.xls", "实践导入模板.xls");
        if(status == -1)
            return Result.send(FAIL, null, STU_DB_DOWNLOAD_FAIL_MESSAGE);
        return Result.send(SUCCESS, null, STU_DB_DOWNLOAD_SUCCESS_MESSAGE);
    }

    @RequestMapping(value = "/reserve/file",method = POST)
    @ApiOperation(value = "数据库管理员添加其他加分信息")
    @Authorization
    @ContainAuthority(DB_STU_RESERVE)
    public String addReserveByFile(HttpServletRequest request, HttpServletResponse response,
                                   @RequestHeader(AUTHORIZATION)String token,
                                   @RequestParam("file") MultipartFile file){
        ImportResult result = studentRecordService.addReserveByFile(file, getRealName(request));
        if(result == null)
            return Result.send(FAIL, null, STU_DB_FAIL_MESSAGE);
        return Result.send(SUCCESS, result.getFailMessage(), String.format(STU_DB_SUCCESS_MESSAGE, result.getSuccessNum(), result.getFailNum()));

    }

    @RequestMapping(value = "/reserve/file",method = GET)
    @ApiOperation(value = "数据库管理员下载其他加分模板文件")
    public String downloadReserveTemplate(HttpServletRequest request, HttpServletResponse response){
        int status = BaseFile.download(response, FOLDER + "templates", "stu_reserve_import.xls", "其他加分导入模板.xls");
        if(status == -1)
            return Result.send(FAIL, null, STU_DB_DOWNLOAD_FAIL_MESSAGE);
        return Result.send(SUCCESS, null, STU_DB_DOWNLOAD_SUCCESS_MESSAGE);
    }

    @RequestMapping(value = "/skill/file",method = POST)
    @ApiOperation(value = "数据库管理员添加技能信息")
    @Authorization
    @ContainAuthority(DB_STU_SKILL)
    public String addSkillByFile(HttpServletRequest request, HttpServletResponse response,
                                 @RequestHeader(AUTHORIZATION)String token,
                                 @RequestParam("file") MultipartFile file){
        ImportResult result = studentRecordService.addSkillByFile(file, getRealName(request));
        if(result == null)
            return Result.send(FAIL, null, STU_DB_FAIL_MESSAGE);
        return Result.send(SUCCESS, result.getFailMessage(), String.format(STU_DB_SUCCESS_MESSAGE, result.getSuccessNum(), result.getFailNum()));

    }

    @RequestMapping(value = "/skill/file",method = GET)
    @ApiOperation(value = "数据库管理员下载技能模板文件")
    public String downloadSkillTemplate(HttpServletRequest request, HttpServletResponse response){
        int status = BaseFile.download(response, FOLDER + "templates", "stu_skill_import.xls", "技能导入模板.xls");
        if(status == -1)
            return Result.send(FAIL, null, STU_DB_DOWNLOAD_FAIL_MESSAGE);
        return Result.send(SUCCESS, null, STU_DB_DOWNLOAD_SUCCESS_MESSAGE);
    }

    @RequestMapping(value = "/volunteer/file",method = POST)
    @ApiOperation(value = "数据库管理员添加志愿信息")
    @Authorization
    @ContainAuthority(DB_STU_VOLUNTEER)
    public String addVolunteerByFile(HttpServletRequest request, HttpServletResponse response,
                                     @RequestHeader(AUTHORIZATION)String token,
                                     @RequestParam("file") MultipartFile file){
        ImportResult result = studentRecordService.addVolunteerByFile(file, getRealName(request));
        if(result == null)
            return Result.send(FAIL, null, STU_DB_FAIL_MESSAGE);
        return Result.send(SUCCESS, result.getFailMessage(), String.format(STU_DB_SUCCESS_MESSAGE, result.getSuccessNum(), result.getFailNum()));

    }

    @RequestMapping(value = "/volunteer/file",method = GET)
    @ApiOperation(value = "数据库管理员下载志愿模板文件")
    public String downloadVolunteerTemplate(HttpServletRequest request, HttpServletResponse response){
        int status = BaseFile.download(response, FOLDER + "templates", "stu_volunteer_import.xls", "志愿导入模板.xls");
        if(status == -1)
            return Result.send(FAIL, null, STU_DB_DOWNLOAD_FAIL_MESSAGE);
        return Result.send(SUCCESS, null, STU_DB_DOWNLOAD_SUCCESS_MESSAGE);
    }
}
