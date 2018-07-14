package dangod.themis.dao.score;

import dangod.themis.model.po.score.StudentBaseInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StudentBaseInfoRepo extends JpaRepository<StudentBaseInfo, Long> {
    StudentBaseInfo findByBaseInfo_User_Id(long userId);

    StudentBaseInfo findByStuId(String stuId);

    List<StudentBaseInfo> findByDormitory_Id(long dormitoryId, Pageable pageable);

    List<StudentBaseInfo> findByAClass_Id(long classId, Pageable pageable);

    List<StudentBaseInfo> findByAClass_Major_Id(long majorId, Pageable pageable);
}
