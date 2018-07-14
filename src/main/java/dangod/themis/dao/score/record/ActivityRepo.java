package dangod.themis.dao.score.record;

import dangod.themis.model.po.score.record.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface ActivityRepo extends JpaRepository<Activity, Long>{
    List<Activity> findByBaseInfo_BaseInfo_User_IdAndTerm(long userId, String term, Pageable pageable);

    List<Activity> findByBaseInfo_StuIdAndTerm(String stuId, String term, Pageable pageable);
}
