package dangod.themis.dao.common;

import dangod.themis.model.po.common.UserBaseInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBaseInfoRepo extends JpaRepository<UserBaseInfo, Long> {
    UserBaseInfo findByUser_Id(long userId);
}
