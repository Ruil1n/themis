package dangod.themis.dao.common;

import dangod.themis.model.po.common.Inform;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface InformRepo extends JpaRepository<Inform, Long> {
    List<Inform>findByUser_Id(Long userId, Pageable pageable);

}