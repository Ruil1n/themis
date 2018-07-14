package dangod.themis.service.club;

import dangod.themis.model.po.club.Club;
import dangod.themis.model.po.club.ClubRole;

public interface RoleService {
    ClubRole getRole(long userId);

    Club getClubByUserId(long userId);
}
