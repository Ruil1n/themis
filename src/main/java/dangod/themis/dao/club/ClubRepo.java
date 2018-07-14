package dangod.themis.dao.club;

import dangod.themis.model.po.club.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubRepo extends JpaRepository<Club, Long>{
    Club findByBaseInfo_User_Id(long userId);
}
