package dangod.themis.dao.authority;

import dangod.themis.model.po.authority.AuthorityType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AuthorityTypeRepo extends JpaRepository<AuthorityType, Long> {
    List<AuthorityType> findAllByIdIn(List<Long> list);
}
