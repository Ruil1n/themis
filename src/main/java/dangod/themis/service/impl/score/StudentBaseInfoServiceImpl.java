package dangod.themis.service.impl.score;

import dangod.themis.core.util.BaseFile;
import dangod.themis.core.util.HSSF;
import dangod.themis.dao.authority.AuthorityUserRepo;
import dangod.themis.dao.score.StudentBaseInfoRepo;
import dangod.themis.model.po.authority.AuthorityUser;
import dangod.themis.model.po.common.UserBaseInfo;
import dangod.themis.model.po.score.Class;
import dangod.themis.model.po.score.Dormitory;
import dangod.themis.model.po.score.StudentBaseInfo;
import dangod.themis.model.vo.score.StudentBaseInfoVo;
import dangod.themis.model.vo.score.file.BaseImport;
import dangod.themis.model.vo.score.file.result.ImportResult;
import dangod.themis.service.score.ClassService;
import dangod.themis.service.score.StudentBaseInfoService;
import dangod.themis.service.score.DormitoryService;
import dangod.themis.service.common.UserService;
import dangod.themis.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
@Service
public class StudentBaseInfoServiceImpl implements StudentBaseInfoService {
    @Autowired
    private StudentBaseInfoRepo studentBaseInfoRepo;
    @Autowired
    private ClassService classService;
    @Autowired
    private DormitoryService dormitoryService;
    @Autowired
    private UserService userService;
    @Autowired
    private AuthorityUserRepo authorityUserRepo;

    private String STU_BASE_AUTH = "[6]";//6:个人学生信息查看

    private static final String STU_BASE_IMPORT_PATH = BaseFile.FOLDER + "score" + File.separator + "import" + File.separator + "base";

    @Override
    public StudentBaseInfoVo getStudentBaseById(long id) {
        StudentBaseInfo baseInfo = studentBaseInfoRepo.findOne(id);
        if(baseInfo == null)return null;
        return new StudentBaseInfoVo(baseInfo);
    }

    @Override
    public StudentBaseInfoVo getStudentBaseByUserId(long userId) {
        StudentBaseInfo baseInfo = studentBaseInfoRepo.findByBaseInfo_User_Id(userId);
        if(baseInfo == null)return null;
        return new StudentBaseInfoVo(baseInfo);
    }

    @Override
    public StudentBaseInfoVo getStudentBaseByStuId(String stuId) {
        StudentBaseInfo baseInfo = studentBaseInfoRepo.findByStuId(stuId);
        if(baseInfo == null)return null;
        return new StudentBaseInfoVo(baseInfo);
    }

    @Override
    public List<StudentBaseInfoVo> getStudentListBaseAll(Integer page, Integer size) {
        Pageable pageable = new PageRequest(page, size, new Sort("id"));
        Page<StudentBaseInfo> poList= studentBaseInfoRepo.findAll(pageable);
        List<StudentBaseInfoVo> voList = new ArrayList<>();
        for(StudentBaseInfo baseInfo : poList){
            voList.add(new StudentBaseInfoVo(baseInfo));
        }
        return voList;
    }

    @Override
    public List<StudentBaseInfoVo> getStudentListBaseByDormitory(long dormitoryId, Integer page, Integer size) {
        List<StudentBaseInfo> poList= studentBaseInfoRepo.findByDormitory_Id(dormitoryId, new PageRequest(page, size, new Sort("id")));
        List<StudentBaseInfoVo> voList = new ArrayList<>();
        for(StudentBaseInfo baseInfo : poList){
            voList.add(new StudentBaseInfoVo(baseInfo));
        }
        return voList;
    }

    @Override
    public List<StudentBaseInfoVo> getStudentListBaseByClass(long classId, Integer page, Integer size) {
        List<StudentBaseInfo> poList= studentBaseInfoRepo.findByAClass_Id(classId, new PageRequest(page, size, new Sort("id")));
        List<StudentBaseInfoVo> voList = new ArrayList<>();
        for(StudentBaseInfo baseInfo : poList){
            voList.add(new StudentBaseInfoVo(baseInfo));
        }
        return voList;
    }

    @Override
    public List<StudentBaseInfoVo> getStudentListBaseByMajor(long majorId, Integer page, Integer size) {
        List<StudentBaseInfo> poList= studentBaseInfoRepo.findByAClass_Major_Id(majorId, new PageRequest(page, size, new Sort("id")));
        List<StudentBaseInfoVo> voList = new ArrayList<>();
        for(StudentBaseInfo baseInfo : poList){
            voList.add(new StudentBaseInfoVo(baseInfo));
        }
        return voList;
    }

    @Override
    public boolean checkStuClass(String stuId, long classId) {
        try {
            if(studentBaseInfoRepo.findByStuId(stuId).getaClass().getId() == classId)
                return true;
            else
                return false;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean checkStuMajor(String stuId, long majorId) {
        try {
            if(studentBaseInfoRepo.findByStuId(stuId).getaClass().getMajor().getId() == majorId)
                return true;
            else
                return false;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean checkClassMajor(long classId, long majorId) {
        try {
            if(classService.getClassById(classId).getMajor().getId() == majorId)
                return true;
            else
                return false;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public StudentBaseInfoVo updateBaseInfo(String stuId, String realName, String sex, long classId, String photo, String entranceTime, long dormitoryId, String political) {
        Dormitory dormitory = null;
        Class aClass = null;
        if(dormitoryId != -1) {
            dormitory = dormitoryService.getDormitoryById(dormitoryId);
            if(dormitory == null)return null;
        }
        if(classId != -1){
            aClass = classService.getClassById(classId);
            if(aClass == null)return null;
        }
        StudentBaseInfo baseInfo = studentBaseInfoRepo.findByStuId(stuId);
        if(baseInfo == null)return null;
        baseInfo.setaClass(aClass);
        baseInfo.setDormitory(dormitory);
        baseInfo.setEntranceTime(entranceTime);
        baseInfo.setPhoto(photo);
        baseInfo.setPolitical(political);
        UserBaseInfo base = baseInfo.getBaseInfo();
        base.setRealName(realName);
        base.setSex(sex);
        try {
            studentBaseInfoRepo.saveAndFlush(baseInfo);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return new StudentBaseInfoVo(baseInfo);
    }

    @Override
    public StudentBaseInfoVo updateBaseInfo(String stuId, String political) {
        StudentBaseInfo baseInfo = studentBaseInfoRepo.findByStuId(stuId);
        if(baseInfo == null)return null;
        baseInfo.setPolitical(political);
        try {
            studentBaseInfoRepo.saveAndFlush(baseInfo);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return new StudentBaseInfoVo(baseInfo);
    }

    @Override
    public StudentBaseInfoVo updateBaseInfo(String stuId, long dormitoryId, String political) {
        Dormitory dormitory = null;
        if(dormitoryId != -1) {
            dormitory = dormitoryService.getDormitoryById(dormitoryId);
            if(dormitory == null)return null;
        }
        StudentBaseInfo baseInfo = studentBaseInfoRepo.findByStuId(stuId);
        if(baseInfo == null)return null;
        baseInfo.setPolitical(political);
        baseInfo.setDormitory(dormitory);
        try {
            studentBaseInfoRepo.saveAndFlush(baseInfo);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return new StudentBaseInfoVo(baseInfo);
    }

    @Override
    public ImportResult addStudentBaseByFile(MultipartFile file, String opName) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日-HH时mm分ss秒");
            long now = Calendar.getInstance().getTime().getTime();
            String fileName = String.format("[%s](%s)import", sdf.format(now), opName);
            if (BaseFile.upload(file, STU_BASE_IMPORT_PATH, fileName) == 0) {
                String[] arr = file.getOriginalFilename().split("[.]");
                String suffix = "." + arr[arr.length - 1];
                HSSF hssf = new HSSF(STU_BASE_IMPORT_PATH, fileName + suffix);
                int row = hssf.getSheetRowSize(0);
                List<StudentBaseInfo> stuBasePoList = new ArrayList<>();
                List<AuthorityUser> stuBaseAuthList = new ArrayList<>();
                List<String> failAdd = new ArrayList<>();
                for (int i = 1; i <= row; i++) {
                    BaseImport importVo = new BaseImport(hssf.getRowValue(0, i, 8));
                    UserBaseInfo baseInfo = userService.addAndCheckUser(importVo.getStuId(), MD5Util.MD5(importVo.getStuId().substring(importVo.getStuId().length() - 4)), importVo.getRealName(), importVo.getEmail(), importVo.getSex());
                    if (baseInfo != null) {
                        stuBasePoList.add(new StudentBaseInfo(importVo.getStuId(), null, importVo.getEntranceTime(), importVo.getPolitical(), baseInfo, classService.getClassById(importVo.getClassId()), dormitoryService.getDormitoryById(importVo.getDormitoryId())));
                        stuBaseAuthList.add(new AuthorityUser(baseInfo.getUser(), STU_BASE_AUTH));
                    }else {
                        failAdd.add(importVo.getStuId()+":"+importVo.getRealName());
                    }
                }
                studentBaseInfoRepo.save(stuBasePoList);
                authorityUserRepo.save(stuBaseAuthList);
                return new ImportResult(stuBasePoList.size(), failAdd.size(), failAdd);
            } else {
                return null;
            }
        }catch (Exception e){
            return null;
        }
    }
}
