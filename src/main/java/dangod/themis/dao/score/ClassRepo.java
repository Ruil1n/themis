package dangod.themis.dao.score;

import dangod.themis.model.po.score.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassRepo extends JpaRepository<Class, Long> {
    List<Class> findAllByMajor_IdOrderById(Long majorId);
}
