package dangod.themis.service.impl.score;

import dangod.themis.dao.score.ClassRepo;
import dangod.themis.dao.score.MajorRepo;
import dangod.themis.model.po.score.Class;
import dangod.themis.model.po.score.Major;
import dangod.themis.model.vo.score.ClassVo;
import dangod.themis.model.vo.score.MajorVo;
import dangod.themis.service.score.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ClassServiceImpl implements ClassService {
    @Autowired
    private MajorRepo majorRepo;
    @Autowired
    private ClassRepo classRepo;
    @Override
    public Integer addMajor(String majorName, Integer year) {
        try {
            majorRepo.save(new Major(majorName, year));
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 0;
    }

    @Override
    public Integer addClass(long majorId, Integer classNum) {
        Major major = getMajorById(majorId);
        if(major == null)return -1;
        try{
            classRepo.save(new Class(classNum, major));
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 0;
    }

    @Override
    public List<Integer> getYearList() {
        List<Integer> list = new ArrayList<>(majorRepo.getYearList());
        Collections.sort(list);
        return list;
    }

    @Override
    public List<MajorVo> getMajorList(Integer year) {
        List<Major> poList = majorRepo.findAllByYearOrderById(year);
        List<MajorVo> result = new ArrayList<>();
        for(Major major : poList){
            result.add(new MajorVo(major));
        }
        return result;
    }

    @Override
    public List<ClassVo> getClassList(long majorId) {
        List<Class> poList = classRepo.findAllByMajor_IdOrderById(majorId);
        List<ClassVo> result = new ArrayList<>();
        for(Class aClass : poList){
            result.add(new ClassVo(aClass));
        }
        return result;
    }

    @Cacheable(value = "30s")
    @Override
    public Major getMajorById(long majorId){
        return majorRepo.findOne(majorId);
    }

    @Cacheable(value = "30s")
    @Override
    public Class getClassById(long classId) {
        return classRepo.findOne(classId);
    }
}
