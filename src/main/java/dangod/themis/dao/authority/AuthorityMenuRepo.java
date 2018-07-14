package dangod.themis.dao.authority;

import dangod.themis.model.po.authority.AuthorityMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AuthorityMenuRepo extends JpaRepository<AuthorityMenu, Long> {
    List<AuthorityMenu> findAllByIdIn(List<Long> list);
}
