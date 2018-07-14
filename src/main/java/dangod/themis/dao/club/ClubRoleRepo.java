package dangod.themis.dao.club;

import dangod.themis.model.po.club.ClubRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubRoleRepo extends JpaRepository<ClubRole, Long>{
    ClubRole findByBaseInfo_User_Id(long userId);
}
