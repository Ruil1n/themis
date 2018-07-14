package dangod.themis.service.impl.club;

import dangod.themis.dao.club.ClubRepo;
import dangod.themis.model.vo.club.ClubVo;
import dangod.themis.service.club.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClubServiceImpl implements ClubService{
    @Autowired
    private ClubRepo clubRepo;
    @Override
    public ClubVo getClubVoById(Integer id) {
        return null;
    }

    @Override
    public ClubVo getClubVoByuserId(long userId) {
        return null;
    }

    @Override
    public List<ClubVo> getPageClub(Integer page, Integer size) {
        return null;
    }
}
