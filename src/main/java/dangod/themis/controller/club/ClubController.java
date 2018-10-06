package dangod.themis.controller.club;

import com.alibaba.fastjson.JSON;
import dangod.themis.controller.base.BaseController;
import dangod.themis.controller.base.annotation.Authorization;
import dangod.themis.controller.base.annotation.ContainAuthority;
import dangod.themis.controller.base.annotation.club.Club;
import dangod.themis.core.result.Result;
import dangod.themis.model.vo.club.ClubVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static dangod.themis.controller.base.constant.Message.CLUB_STATUS_SUCCESS_MESSAGE;
import static dangod.themis.controller.base.constant.Status.SUCCESS;
import static dangod.themis.model.po.authority.constant.TypeContant.CLUB_ACTIVITY_APPLY;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@CrossOrigin
@RestController
@RequestMapping(value = "/club")
public class ClubController extends BaseController{
    @RequestMapping(method = GET)
    @ApiOperation(value = "获取自己社团信息")
    @Authorization
    @ContainAuthority(CLUB_ACTIVITY_APPLY)
    @Club
    public String getClubVo(HttpServletRequest request, HttpServletResponse response){
        return Result.send(SUCCESS, new ClubVo(getClub(request)), CLUB_STATUS_SUCCESS_MESSAGE);
    }
}
