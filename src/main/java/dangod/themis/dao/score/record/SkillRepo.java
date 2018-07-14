package dangod.themis.dao.score.record;

import dangod.themis.model.po.score.record.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface SkillRepo extends JpaRepository<Skill, Long>{
    List<Skill> findByBaseInfo_BaseInfo_User_IdAndTerm(long userId, String term, Pageable pageable);

    List<Skill> findByBaseInfo_StuIdAndTerm(String stuId, String term, Pageable pageable);
}
