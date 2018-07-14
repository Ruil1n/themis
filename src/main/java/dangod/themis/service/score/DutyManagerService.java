package dangod.themis.service.score;

import dangod.themis.model.po.score.DutyManager;

public interface DutyManagerService {
    DutyManager getManagerByUserId(long userId);
}
