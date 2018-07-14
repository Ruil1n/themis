package dangod.themis.dao.score;

import dangod.themis.model.po.score.DutyManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DutyManagerRepo extends JpaRepository<DutyManager, Long>{
    DutyManager findByUser_Id(long userId);
}
