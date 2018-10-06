package dangod.themis.service.club;

import dangod.themis.model.po.club.Club;
import dangod.themis.model.vo.club.ClubVo;
import dangod.themis.model.vo.score.file.result.ImportResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ClubService {
    ClubVo getClubVoById(Integer id);

    ClubVo getClubVoByuserId(long userId);

    Club getClubByuserId(long userId);

    List<ClubVo> getPageClub(Integer page, Integer size);

    Integer updateMoney(long id,Double selfMoney,Double reserveMoney);

    /**
     * 添加社团 及其负责人 通过文件
     * @param file
     * @param opName
     * @return
     */
    ImportResult addClubManagerByFile(MultipartFile file, String opName);
}
