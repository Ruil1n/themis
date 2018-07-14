package dangod.themis.dao.authority;

import dangod.themis.model.po.authority.AuthorityGourp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityGourpRepo extends JpaRepository<AuthorityGourp, Long>{
}
