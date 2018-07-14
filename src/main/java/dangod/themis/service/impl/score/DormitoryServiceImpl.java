package dangod.themis.service.impl.score;

import dangod.themis.dao.score.DormitoryRepo;
import dangod.themis.model.po.score.Dormitory;
import dangod.themis.service.score.DormitoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class DormitoryServiceImpl implements DormitoryService {
    @Autowired
    private DormitoryRepo dormitoryRepo;


    @Cacheable(value = "30s")
    @Override
    public Dormitory getDormitoryById(long dormitoryId) {
        return dormitoryRepo.findOne(dormitoryId);
    }
}
