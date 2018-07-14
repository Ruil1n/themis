package dangod.themis.dao.common;

import dangod.themis.model.po.common.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
    Integer countByUsername(String username);
    @Modifying
    @Query(value = "update User set password = :password where id =:id", nativeQuery = true)
    void updatePasswordById(@Param("id")long id, @Param("password")String password);
}
