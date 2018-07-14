package dangod.themis.dao.score.record;

import dangod.themis.model.po.score.record.Reserve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface ReserveRepo extends JpaRepository<Reserve, Long>{
    List<Reserve> findByBaseInfo_BaseInfo_User_IdAndTerm(long userId, String term, Pageable pageable);

    List<Reserve> findByBaseInfo_StuIdAndTerm(String stuId, String term, Pageable pageable);
}
