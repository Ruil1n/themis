package dangod.themis.service.score;

import dangod.themis.model.po.score.Class;
import dangod.themis.model.po.score.Dormitory;
import dangod.themis.model.po.score.Major;
import dangod.themis.model.vo.score.ClassVo;
import dangod.themis.model.vo.score.MajorVo;

import java.util.List;
import java.util.Map;

public interface ClassService {
    /**
     * 添加专业
     * @param majorName
     * @param year
     * @return
     */
    Integer addMajor(String majorName, Integer year);

    /**
     * 添加班级
     * @param majorId
     * @param classNum
     * @return
     */
    Integer addClass(long majorId, Integer classNum);

    List<Integer> getYearList();

    List<MajorVo> getMajorList(Integer year);

    List<ClassVo> getClassList(long majorId);

    Major getMajorById(long majorId);

    Class getClassById(long classId);






}
