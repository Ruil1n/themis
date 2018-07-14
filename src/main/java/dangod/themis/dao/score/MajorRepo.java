package dangod.themis.dao.score;

import dangod.themis.model.po.score.Major;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;


@Repository
public interface MajorRepo extends JpaRepository<Major, Long> {
    @Query(value = "select m.year from Major m")
    Set<Integer> getYearList();

    List<Major> findAllByYearOrderById(Integer year);
}
