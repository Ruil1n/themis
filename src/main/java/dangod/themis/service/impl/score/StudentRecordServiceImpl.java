package dangod.themis.service.impl.score;

import dangod.themis.core.util.BaseFile;
import dangod.themis.core.util.HSSF;
import dangod.themis.dao.score.StudentBaseInfoRepo;
import dangod.themis.dao.score.record.*;
import dangod.themis.model.po.score.StudentBaseInfo;
import dangod.themis.model.po.score.record.*;
import dangod.themis.model.vo.score.file.*;
import dangod.themis.model.vo.score.file.result.ImportResult;
import dangod.themis.model.vo.score.record.*;
import dangod.themis.service.score.StudentRecordService;
import dangod.themis.util.TermUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static dangod.themis.config.ScoreConstant.ACTIVITY_SCORE;

@Service
public class StudentRecordServiceImpl implements StudentRecordService{
    @Autowired
    private ActivityRepo activityRepo;
    @Autowired
    private OfficeRepo officeRepo;
    @Autowired
    private HonorRepo honorRepo;
    @Autowired
    private VolunteerRepo volunteerRepo;
    @Autowired
    private SkillRepo skillRepo;
    @Autowired
    private ReserveRepo reserveRepo;
    @Autowired
    private PracticeRepo practiceRepo;
    @Autowired
    private StudentBaseInfoRepo studentBaseInfoRepo;
    @Override
    public List<ActivityVo> getActivityByUserIdAndTerm(long userId, String term, Integer page, Integer size) {
        List<Activity> poList = activityRepo.findByBaseInfo_BaseInfo_User_IdAndTerm(userId, term, new PageRequest(page, size, new Sort("id")));
        if(poList == null)return null;
        List<ActivityVo> voList = new ArrayList<>();
        for(Activity entity : poList){
            voList.add(new ActivityVo(entity, ACTIVITY_SCORE));
        }
        return voList;
    }

    @Override
    public List<ActivityVo> getActivityByStuIdAndTerm(String stuId, String term, Integer page, Integer size) {
        List<Activity> poList = activityRepo.findByBaseInfo_StuIdAndTerm(stuId, term, new PageRequest(page, size, new Sort("id")));
        if(poList == null)return null;
        List<ActivityVo> voList = new ArrayList<>();
        for(Activity entity : poList){
            voList.add(new ActivityVo(entity,  ACTIVITY_SCORE));
        }
        return voList;
    }

    @Override
    public List<HonorVo> getHonorByUserIdAndTerm(long userId, String term, Integer page, Integer size) {
        List<Honor> poList = honorRepo.findByBaseInfo_BaseInfo_User_IdAndTerm(userId, term, new PageRequest(page, size, new Sort("id")));
        if(poList == null)return null;
        List<HonorVo> voList = new ArrayList<>();
        for(Honor entity : poList){
            voList.add(new HonorVo(entity));
        }
        return voList;
    }

    @Override
    public List<HonorVo> getHonorByStuIdAndTerm(String stuId, String term, Integer page, Integer size) {
        List<Honor> poList = honorRepo.findByBaseInfo_StuIdAndTerm(stuId, term, new PageRequest(page, size, new Sort("id")));
        if(poList == null)return null;
        List<HonorVo> voList = new ArrayList<>();
        for(Honor entity : poList){
            voList.add(new HonorVo(entity));
        }
        return voList;
    }

    @Override
    public List<OfficeVo> getOfficeByUserIdAndTerm(long userId, String term, Integer page, Integer size) {
        List<Office> poList = officeRepo.findByBaseInfo_BaseInfo_User_IdAndTerm(userId, term, new PageRequest(page, size, new Sort("id")));
        if(poList == null)return null;
        List<OfficeVo> voList = new ArrayList<>();
        for(Office entity : poList){
            voList.add(new OfficeVo(entity));
        }
        return voList;
    }

    @Override
    public List<OfficeVo> getOfficeByStuIdAndTerm(String stuId, String term, Integer page, Integer size) {
        List<Office> poList = officeRepo.findByBaseInfo_StuIdAndTerm(stuId, term, new PageRequest(page, size, new Sort("id")));
        if(poList == null)return null;
        List<OfficeVo> voList = new ArrayList<>();
        for(Office entity : poList){
            voList.add(new OfficeVo(entity));
        }
        return voList;
    }

    @Override
    public List<PracticeVo> getPracticeByUserIdAndTerm(long userId, String term, Integer page, Integer size) {
        List<Practice> poList = practiceRepo.findByBaseInfo_BaseInfo_User_IdAndTerm(userId, term, new PageRequest(page, size, new Sort("id")));
        if(poList == null)return null;
        List<PracticeVo> voList = new ArrayList<>();
        for(Practice entity : poList){
            voList.add(new PracticeVo(entity));
        }
        return voList;
    }

    @Override
    public List<PracticeVo> getPracticeByStuIdAndTerm(String stuId, String term, Integer page, Integer size) {
        List<Practice> poList = practiceRepo.findByBaseInfo_StuIdAndTerm(stuId, term, new PageRequest(page, size, new Sort("id")));
        if(poList == null)return null;
        List<PracticeVo> voList = new ArrayList<>();
        for(Practice entity : poList){
            voList.add(new PracticeVo(entity));
        }
        return voList;
    }

    @Override
    public List<ReserveVo> getReserveByUserIdAndTerm(long userId, String term, Integer page, Integer size) {
        List<Reserve> poList = reserveRepo.findByBaseInfo_BaseInfo_User_IdAndTerm(userId, term, new PageRequest(page, size, new Sort("id")));
        if(poList == null)return null;
        List<ReserveVo> voList = new ArrayList<>();
        for(Reserve entity : poList){
            voList.add(new ReserveVo(entity));
        }
        return voList;
    }

    @Override
    public List<ReserveVo> getReserveByStuIdAndTerm(String stuId, String term, Integer page, Integer size) {
        List<Reserve> poList = reserveRepo.findByBaseInfo_StuIdAndTerm(stuId, term, new PageRequest(page, size, new Sort("id")));
        if(poList == null)return null;
        List<ReserveVo> voList = new ArrayList<>();
        for(Reserve entity : poList){
            voList.add(new ReserveVo(entity));
        }
        return voList;
    }

    @Override
    public List<SkillVo> getSkillByUserIdAndTerm(long userId, String term, Integer page, Integer size) {
        List<Skill> poList = skillRepo.findByBaseInfo_BaseInfo_User_IdAndTerm(userId, term, new PageRequest(page, size, new Sort("id")));
        if(poList == null)return null;
        List<SkillVo> voList = new ArrayList<>();
        for(Skill entity : poList){
            voList.add(new SkillVo(entity));
        }
        return voList;
    }

    @Override
    public List<SkillVo> getSkillByStuIdAndTerm(String stuId, String term, Integer page, Integer size) {
        List<Skill> poList = skillRepo.findByBaseInfo_StuIdAndTerm(stuId, term, new PageRequest(page, size, new Sort("id")));
        if(poList == null)return null;
        List<SkillVo> voList = new ArrayList<>();
        for(Skill entity : poList){
            voList.add(new SkillVo(entity));
        }
        return voList;
    }

    @Override
    public List<VolunteerVo> getVolunteerByUserIdAndTerm(long userId, String term, Integer page, Integer size) {
        List<Volunteer> poList = volunteerRepo.findByBaseInfo_BaseInfo_User_IdAndTerm(userId, term, new PageRequest(page, size, new Sort("id")));
        if(poList == null)return null;
        List<VolunteerVo> voList = new ArrayList<>();
        for(Volunteer entity : poList){
            voList.add(new VolunteerVo(entity));
        }
        return voList;
    }

    @Override
    public List<VolunteerVo> getVolunteerByStuIdAndTerm(String stuId, String term, Integer page, Integer size) {
        List<Volunteer> poList = volunteerRepo.findByBaseInfo_StuIdAndTerm(stuId, term, new PageRequest(page, size, new Sort("id")));
        if(poList == null)return null;
        List<VolunteerVo> voList = new ArrayList<>();
        for(Volunteer entity : poList){
            voList.add(new VolunteerVo(entity));
        }
        return voList;
    }

    private static final String STU_ACTIVITY_IMPORT_PATH = BaseFile.FOLDER + "score" + File.separator + "import" + File.separator + "activity";

    private static final String STU_HONOR_IMPORT_PATH = BaseFile.FOLDER + "score" + File.separator + "import" + File.separator + "honor";

    private static final String STU_OFFICE_IMPORT_PATH = BaseFile.FOLDER + "score" + File.separator + "import" + File.separator + "office";

    private static final String STU_PRACTICE_IMPORT_PATH = BaseFile.FOLDER + "score" + File.separator + "import" + File.separator + "practice";

    private static final String STU_RESERVE_IMPORT_PATH = BaseFile.FOLDER + "score" + File.separator + "import" + File.separator + "reserve";

    private static final String STU_SKILL_IMPORT_PATH = BaseFile.FOLDER + "score" + File.separator + "import" + File.separator + "skill";

    private static final String STU_VOLUNTEER_IMPORT_PATH = BaseFile.FOLDER + "score" + File.separator + "import" + File.separator + "volunteer";



    @Override
    public ImportResult addActivityByFile(MultipartFile file, String opName) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日-HH时mm分ss秒");
            long now = Calendar.getInstance().getTime().getTime();
            String fileName = String.format("[%s](%s)import", sdf.format(now), opName);
            if (BaseFile.upload(file, STU_ACTIVITY_IMPORT_PATH, fileName) == 0) {
                String[] arr = file.getOriginalFilename().split("[.]");
                String suffix = "." + arr[arr.length - 1];
                HSSF hssf = new HSSF(STU_ACTIVITY_IMPORT_PATH, fileName + suffix);
                int row = hssf.getSheetRowSize(0);
                List<Activity> addList = new ArrayList<>();
                List<String> failList = new ArrayList<>();
                for (int i = 1; i <= row; i++) {
                    ActivityImport importVo = new ActivityImport(hssf.getRowValue(0, i, 5));
                    StudentBaseInfo baseInfo = studentBaseInfoRepo.findByStuId(importVo.getStuId());
                    if (baseInfo != null) {
                        addList.add(new Activity(baseInfo, importVo.getCommon(), TermUtil.get(), importVo.getActivityDate(), importVo.getActivityName()));
                    }else {
                        failList.add(importVo.getStuId()+":"+importVo.getRealName()+"-"+importVo.getActivityName());
                    }
                }
                activityRepo.save(addList);
                return new ImportResult(addList.size(), failList.size(), failList);
            } else {
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ImportResult addHonorByFile(MultipartFile file, String opName) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日-HH时mm分ss秒");
            long now = Calendar.getInstance().getTime().getTime();
            String fileName = String.format("[%s](%s)import", sdf.format(now), opName);
            if (BaseFile.upload(file, STU_HONOR_IMPORT_PATH, fileName) == 0) {
                String[] arr = file.getOriginalFilename().split("[.]");
                String suffix = "." + arr[arr.length - 1];
                HSSF hssf = new HSSF(STU_HONOR_IMPORT_PATH, fileName + suffix);
                int row = hssf.getSheetRowSize(0);
                List<Honor> addList = new ArrayList<>();
                List<String> failList = new ArrayList<>();
                for (int i = 1; i <= row; i++) {
                    HonorImport importVo = new HonorImport(hssf.getRowValue(0, i, 6));
                    StudentBaseInfo baseInfo = studentBaseInfoRepo.findByStuId(importVo.getStuId());
                    if (baseInfo != null) {
                        addList.add(new Honor(baseInfo, importVo.getCommon(), TermUtil.get(), importVo.getHonorName(), importVo.getHonorLv(), importVo.getHonorScore()));
                    }else {
                        failList.add(importVo.getStuId()+":"+importVo.getRealName()+"-"+importVo.getHonorName());
                    }
                }
                honorRepo.save(addList);
                return new ImportResult(addList.size(), failList.size(), failList);
            } else {
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ImportResult addOfficeByFile(MultipartFile file, String opName) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日-HH时mm分ss秒");
            long now = Calendar.getInstance().getTime().getTime();
            String fileName = String.format("[%s](%s)import", sdf.format(now), opName);
            if (BaseFile.upload(file, STU_OFFICE_IMPORT_PATH, fileName) == 0) {
                String[] arr = file.getOriginalFilename().split("[.]");
                String suffix = "." + arr[arr.length - 1];
                HSSF hssf = new HSSF(STU_OFFICE_IMPORT_PATH, fileName + suffix);
                int row = hssf.getSheetRowSize(0);
                List<Office> addList = new ArrayList<>();
                List<String> failList = new ArrayList<>();
                for (int i = 1; i <= row; i++) {
                    OfficeImport importVo = new OfficeImport(hssf.getRowValue(0, i, 9));
                    StudentBaseInfo baseInfo = studentBaseInfoRepo.findByStuId(importVo.getStuId());
                    if (baseInfo != null) {
                        addList.add(new Office(baseInfo, importVo.getCommon(), TermUtil.get(), importVo.getOfficeName(), importVo.getOfficeLv(), importVo.getStartDate(), importVo.getEndDate(), importVo.getResult()));
                    }else {
                        failList.add(importVo.getStuId()+":"+importVo.getRealName()+"-"+importVo.getOfficeName());
                    }
                }
                officeRepo.save(addList);
                return new ImportResult(addList.size(), failList.size(), failList);
            } else {
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ImportResult addPracticeByFile(MultipartFile file, String opName) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日-HH时mm分ss秒");
            long now = Calendar.getInstance().getTime().getTime();
            String fileName = String.format("[%s](%s)import", sdf.format(now), opName);
            if (BaseFile.upload(file, STU_PRACTICE_IMPORT_PATH, fileName) == 0) {
                String[] arr = file.getOriginalFilename().split("[.]");
                String suffix = "." + arr[arr.length - 1];
                HSSF hssf = new HSSF(STU_PRACTICE_IMPORT_PATH, fileName + suffix);
                int row = hssf.getSheetRowSize(0);
                List<Practice> addList = new ArrayList<>();
                List<String> failList = new ArrayList<>();
                for (int i = 1; i <= row; i++) {
                    PracticeImport importVo = new PracticeImport(hssf.getRowValue(0, i, 6));
                    StudentBaseInfo baseInfo = studentBaseInfoRepo.findByStuId(importVo.getStuId());
                    if (baseInfo != null) {
                        addList.add(new Practice(baseInfo, importVo.getCommon(), TermUtil.get(), importVo.getPracticeName(), importVo.getPracticeDate(), importVo.getResult()));
                    }else {
                        failList.add(importVo.getStuId()+":"+importVo.getRealName()+"-"+importVo.getPracticeName());
                    }
                }
                practiceRepo.save(addList);
                return new ImportResult(addList.size(), failList.size(), failList);
            } else {
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ImportResult addReserveByFile(MultipartFile file, String opName) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日-HH时mm分ss秒");
            long now = Calendar.getInstance().getTime().getTime();
            String fileName = String.format("[%s](%s)import", sdf.format(now), opName);
            if (BaseFile.upload(file, STU_RESERVE_IMPORT_PATH, fileName) == 0) {
                String[] arr = file.getOriginalFilename().split("[.]");
                String suffix = "." + arr[arr.length - 1];
                HSSF hssf = new HSSF(STU_RESERVE_IMPORT_PATH, fileName + suffix);
                int row = hssf.getSheetRowSize(0);
                List<Reserve> addList = new ArrayList<>();
                List<String> failList = new ArrayList<>();
                for (int i = 1; i <= row; i++) {
                    ReserveImport importVo = new ReserveImport(hssf.getRowValue(0, i, 5));
                    StudentBaseInfo baseInfo = studentBaseInfoRepo.findByStuId(importVo.getStuId());
                    if (baseInfo != null) {
                        addList.add(new Reserve(baseInfo, importVo.getCommon(), TermUtil.get(), importVo.getCourse(), importVo.getScore()));
                    }else {
                        failList.add(importVo.getStuId()+":"+importVo.getRealName()+"-"+importVo.getCourse());
                    }
                }
                reserveRepo.save(addList);
                return new ImportResult(addList.size(), failList.size(), failList);
            } else {
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ImportResult addSkillByFile(MultipartFile file, String opName) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日-HH时mm分ss秒");
            long now = Calendar.getInstance().getTime().getTime();
            String fileName = String.format("[%s](%s)import", sdf.format(now), opName);
            if (BaseFile.upload(file, STU_SKILL_IMPORT_PATH, fileName) == 0) {
                String[] arr = file.getOriginalFilename().split("[.]");
                String suffix = "." + arr[arr.length - 1];
                HSSF hssf = new HSSF(STU_SKILL_IMPORT_PATH, fileName + suffix);
                int row = hssf.getSheetRowSize(0);
                List<Skill> addList = new ArrayList<>();
                List<String> failList = new ArrayList<>();
                for (int i = 1; i <= row; i++) {
                    SkillImport importVo = new SkillImport(hssf.getRowValue(0, i, 6));
                    StudentBaseInfo baseInfo = studentBaseInfoRepo.findByStuId(importVo.getStuId());
                    if (baseInfo != null) {
                        addList.add(new Skill(baseInfo, importVo.getCommon(), TermUtil.get(), importVo.getSkillLv(), importVo.getSkillName(), importVo.getSkillScore()));
                    }else {
                        failList.add(importVo.getStuId()+":"+importVo.getRealName()+"-"+importVo.getSkillName());
                    }
                }
                skillRepo.save(addList);
                return new ImportResult(addList.size(), failList.size(), failList);
            } else {
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ImportResult addVolunteerByFile(MultipartFile file, String opName) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日-HH时mm分ss秒");
            long now = Calendar.getInstance().getTime().getTime();
            String fileName = String.format("[%s](%s)import", sdf.format(now), opName);
            if (BaseFile.upload(file, STU_VOLUNTEER_IMPORT_PATH, fileName) == 0) {
                String[] arr = file.getOriginalFilename().split("[.]");
                String suffix = "." + arr[arr.length - 1];
                HSSF hssf = new HSSF(STU_VOLUNTEER_IMPORT_PATH, fileName + suffix);
                int row = hssf.getSheetRowSize(0);
                List<Volunteer> addList = new ArrayList<>();
                List<String> failList = new ArrayList<>();
                for (int i = 1; i <= row; i++) {
                    VolunteerImport importVo = new VolunteerImport(hssf.getRowValue(0, i, 6));
                    StudentBaseInfo baseInfo = studentBaseInfoRepo.findByStuId(importVo.getStuId());
                    if (baseInfo != null) {
                        addList.add(new Volunteer(baseInfo, importVo.getCommon(), TermUtil.get(), importVo.getVolunteerName(), importVo.getVolunteerDate(), importVo.getVolunteerTime()));
                    }else {
                        failList.add(importVo.getStuId()+":"+importVo.getRealName()+"-"+importVo.getVolunteerName());
                    }
                }
                volunteerRepo.save(addList);
                return new ImportResult(addList.size(), failList.size(), failList);
            } else {
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Integer addActivity(String stuId, String activityName, String activityDate, String common) {
        try {
            StudentBaseInfo baseInfo = studentBaseInfoRepo.findByStuId(stuId);
            activityRepo.save(new Activity(baseInfo, common, TermUtil.get(), activityDate, activityName));
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 0;
    }

    @Override
    public Integer deleteActivity(long recordId) {
        try {
            activityRepo.delete(recordId);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 0;
    }

    @Override
    public Integer updateActivity(long recordId, String activityName, String activityDate, String common) {
        try {
            Activity activity = activityRepo.findOne(recordId);
            activity.setActivityName(activityName);
            activity.setActivityDate(activityDate);
            activity.setCommon(common);
            activityRepo.save(activity);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 0;
    }

    @Override
    public Integer deleteHonor(long recordId) {
        try {
            honorRepo.delete(recordId);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 0;
    }

    @Override
    public Integer deleteOffice(long recordId) {
        try {
            officeRepo.delete(recordId);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 0;
    }

    @Override
    public Integer deletePractice(long recordId) {
        try {
            practiceRepo.delete(recordId);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 0;
    }

    @Override
    public Integer deleteReserve(long recordId) {
        try {
            reserveRepo.delete(recordId);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 0;
    }

    @Override
    public Integer deleteSkill(long recordId) {
        try {
            skillRepo.delete(recordId);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 0;
    }

    @Override
    public Integer deleteVolunteer(long recordId) {
        try {
            volunteerRepo.delete(recordId);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 0;
    }

    @Override
    public Integer addHonor(String stuId, String honorName, int honorLv, double honorScore, String common) {
        try {
            StudentBaseInfo baseInfo = studentBaseInfoRepo.findByStuId(stuId);
            honorRepo.save(new Honor(baseInfo, common, TermUtil.get(), honorName, honorLv, honorScore));
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 0;
    }

    @Override
    public Integer updateHonor(long recordId, String honorName, int honorLv, double honorScore, String common) {
        try {
            Honor honor = honorRepo.findOne(recordId);
            honor.setHonorName(honorName);
            honor.setHonorLv(honorLv);
            honor.setHonorScore(honorScore);
            honor.setCommon(common);
            honorRepo.save(honor);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 0;
    }

}
