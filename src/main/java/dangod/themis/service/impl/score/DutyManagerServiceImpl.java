package dangod.themis.service.impl.score;

import dangod.themis.dao.score.DutyManagerRepo;
import dangod.themis.model.po.score.DutyManager;
import dangod.themis.service.score.DutyManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DutyManagerServiceImpl implements DutyManagerService {
    @Autowired
    private DutyManagerRepo dutyManagerRepo;
    @Override
    public DutyManager getManagerByUserId(long userId) {
        return dutyManagerRepo.findByUser_Id(userId);
    }
}
