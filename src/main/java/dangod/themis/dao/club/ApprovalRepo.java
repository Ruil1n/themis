package dangod.themis.dao.club;

import dangod.themis.model.po.club.Approval;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApprovalRepo extends JpaRepository<Approval, Long>{
    List<Approval> findByApplication_Id(long id, Sort sort);
    Approval findByApplication_IdAndAndApprovalLV(long appId, int lv);
    void deleteApprovalByApplication_Id(long appId);
}
