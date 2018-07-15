package dangod.themis.service.impl.club;

import dangod.themis.dao.club.ClubRepo;
import dangod.themis.model.po.club.Club;
import dangod.themis.model.vo.club.ClubVo;
import dangod.themis.service.club.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.util.resources.cldr.de.CalendarData_de_LU;

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
    public Club getClubByuserId(long userId) {
        return null;
    }

    @Override
    public List<ClubVo> getPageClub(Integer page, Integer size) {
        return null;
    }

    @Override
    public Integer updateMoney(long id, Double selfMoney, Double reserveMoney) {
        Club club=clubRepo.findById(id);
        club.setSelfMoney(club.getSelfMoney()-selfMoney);
        club.setReserveMoney(club.getReserveMoney()-reserveMoney);
        clubRepo.save(club);
        return 0;
    }

}
