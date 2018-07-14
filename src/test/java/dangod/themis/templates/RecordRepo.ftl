package dangod.themis.dao.score.record;

import dangod.themis.model.po.score.record.${name};
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface ${name}Repo extends JpaRepository<${name}, Long>{
    List<${name}> findByBaseInfo_BaseInfo_User_IdAndTerm(long userId, String term, Pageable pageable);

    List<${name}> findByBaseInfo_StuIdAndTerm(String stuId, String term, Pageable pageable);
}
