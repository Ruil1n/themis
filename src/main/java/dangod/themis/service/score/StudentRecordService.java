package dangod.themis.service.score;

import dangod.themis.model.vo.score.file.result.ImportResult;
import dangod.themis.model.vo.score.record.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StudentRecordService {
    List<ActivityVo> getActivityByUserIdAndTerm(long userId, String term, Integer page, Integer size);

    List<ActivityVo> getActivityByStuIdAndTerm(String stuId, String term, Integer page, Integer size);

    List<HonorVo> getHonorByUserIdAndTerm(long userId, String term, Integer page, Integer size);

    List<HonorVo> getHonorByStuIdAndTerm(String stuId, String term, Integer page, Integer size);

    List<OfficeVo> getOfficeByUserIdAndTerm(long userId, String term, Integer page, Integer size);

    List<OfficeVo> getOfficeByStuIdAndTerm(String stuId, String term, Integer page, Integer size);

    List<PracticeVo> getPracticeByUserIdAndTerm(long userId, String term, Integer page, Integer size);

    List<PracticeVo> getPracticeByStuIdAndTerm(String stuId, String term, Integer page, Integer size);

    List<ReserveVo> getReserveByUserIdAndTerm(long userId, String term, Integer page, Integer size);

    List<ReserveVo> getReserveByStuIdAndTerm(String stuId, String term, Integer page, Integer size);

    List<SkillVo> getSkillByUserIdAndTerm(long userId, String term, Integer page, Integer size);

    List<SkillVo> getSkillByStuIdAndTerm(String stuId, String term, Integer page, Integer size);

    List<VolunteerVo> getVolunteerByUserIdAndTerm(long userId, String term, Integer page, Integer size);

    List<VolunteerVo> getVolunteerByStuIdAndTerm(String stuId, String term, Integer page, Integer size);

    ImportResult addActivityByFile(MultipartFile file, String opName);

    ImportResult addHonorByFile(MultipartFile file, String opName);

    ImportResult addOfficeByFile(MultipartFile file, String opName);

    ImportResult addPracticeByFile(MultipartFile file, String opName);

    ImportResult addReserveByFile(MultipartFile file, String opName);

    ImportResult addSkillByFile(MultipartFile file, String opName);

    ImportResult addVolunteerByFile(MultipartFile file, String opName);

    Integer deleteActivity(long recordId);

    Integer deleteHonor (long recordId);

    Integer deleteOffice (long recordId);

    Integer deletePractice (long recordId);

    Integer deleteReserve (long recordId);

    Integer deleteSkill (long recordId);

    Integer deleteVolunteer (long recordId);

    //单体修改
    //activity
    Integer addActivity(String stuId, String activityName, String activityDate, String common);

    Integer updateActivity(long recordId, String activityName, String activityDate, String common);

    //honor
    Integer addHonor(String stuId, String honorName, int honorLv, double honorScore, String common);

    Integer updateHonor(long recordId, String honorName, int honorLv, double honorScore, String common);

}
