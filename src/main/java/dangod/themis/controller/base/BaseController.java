package dangod.themis.controller.base;

import dangod.themis.model.po.club.Club;
import dangod.themis.model.po.club.ClubRole;
import dangod.themis.service.common.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

public class BaseController {
    @Autowired
    private UserInfoService userInfoService;

    protected static final int DEFAULT_SIZE = 6;

    protected long getUserId(HttpServletRequest request){
        return (long)request.getAttribute("userId");
    }
    //score
    protected long getManageClass(HttpServletRequest request){
        return (long)request.getAttribute("classId");
    }
    protected long getManageMajor(HttpServletRequest request){
        return (long)request.getAttribute("majorId");
    }
    //club
    protected Club getClub(HttpServletRequest request){
        return (Club) request.getAttribute("club");
    }
    protected ClubRole getClubRole(HttpServletRequest request){ return (ClubRole)request.getAttribute("clubRole");}

    protected String getAttribute(HttpServletRequest request, String key){
        return (String)request.getAttribute(key);
    }
    protected String getParameter(HttpServletRequest request, String key){
        return request.getParameter(key);
    }

    protected String getRealName(HttpServletRequest request){
        return userInfoService.getBaseInfoByUserId(getUserId(request)).getRealName();
    }
}
