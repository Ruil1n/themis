package dangod.themis.service.club;

import dangod.themis.model.vo.club.ClubVo;

import java.util.List;

public interface ClubService {
    ClubVo getClubVoById(Integer id);

    ClubVo getClubVoByuserId(long userId);

    List<ClubVo> getPageClub(Integer page, Integer size);
}
