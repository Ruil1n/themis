package dangod.themis.service.impl.club;

import dangod.themis.dao.club.ClubRepo;
import dangod.themis.dao.club.ClubRoleRepo;
import dangod.themis.model.po.club.Club;
import dangod.themis.model.po.club.ClubRole;
import dangod.themis.service.club.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
    private ClubRoleRepo clubRoleRepo;
    @Autowired
    private ClubRepo clubRepo;
    @Override
    public ClubRole getRole(long userId) {
        ClubRole clubRole = clubRoleRepo.findByBaseInfo_User_Id(userId);
        return clubRole;
    }

    @Override
    public Club getClubByUserId(long userId) {
        return clubRepo.findByBaseInfo_User_Id(userId);
    }
}
