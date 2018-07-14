package dangod.themis.dao.club;

import dangod.themis.model.po.club.ClubFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubFileRepo extends JpaRepository<ClubFile, Long>{
    ClubFile findByApplication_Id(long applicationId);
    void deleteByApplication_Id(long applicationId);
}
