package dangod.themis.dao.score;

import dangod.themis.model.po.score.Dormitory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DormitoryRepo extends JpaRepository<Dormitory, Long> {
}
