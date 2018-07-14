package dangod.themis.dao.club;

import dangod.themis.model.po.club.Application;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepo extends JpaRepository<Application, Long>{
//    @Query(value = "select new StatusVo(app.id, app.applyDate, app.club.clubName, app.club.baseInfo.realName, app.activityName, app.status) from Application app where club_id = :clubId limit :size*(:page-1), :size")
//    List<StatusVo> findStatusVoListByClubId(@Param("clubId") long clubId, Integer page, Integer size);
    List<Application> findAllByClub_Id(long clubId, Pageable pageable);

    List<Application> findAllByClub_IdAndStatus(long clubId, Integer status, Pageable pageable);

    List<Application> findByLvGreaterThanEqual(Integer lv, Pageable pageable);

    List<Application> findByLvGreaterThanEqualAndStatus(Integer lv, Integer status, Pageable pageable);

    List<Application> findByIsApplyRefundOrLvEquals(Integer isApplyRefund,Integer lv, Pageable pageable);

    List<Application> findAllByLvEquals(Integer lv, Pageable pageable);

    Page<Application> findAllByStatus(Integer status, Pageable pageable);
}
