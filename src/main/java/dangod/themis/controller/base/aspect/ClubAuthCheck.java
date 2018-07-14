package dangod.themis.controller.base.aspect;

import dangod.themis.core.result.Result;
import dangod.themis.model.po.club.Club;
import dangod.themis.model.po.club.ClubRole;
import dangod.themis.service.club.RoleService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static dangod.themis.controller.base.constant.Message.BASE_MESSAGE_SERVER_ERROR;
import static dangod.themis.controller.base.constant.Message.NOT_MANAGE_CLUB;
import static dangod.themis.controller.base.constant.Message.PERMISSIN_DENIED_MESSAGE;
import static dangod.themis.controller.base.constant.Status.PERMISSIN_DENIED;
import static dangod.themis.controller.base.constant.Status.SERVER_ERROR;

/**
 * 前置检查: TokenCheck, AuthorityCheck
 * 获取管理员所管理的集合
 */
@Component
@Aspect
@Order(-97)
public class ClubAuthCheck {
    @Autowired
    private RoleService roleService;
    @Pointcut("@annotation(dangod.themis.controller.base.annotation.club.Club)")
    public void clubSelf() {
    }
    @Pointcut("@annotation(dangod.themis.controller.base.annotation.club.ClubAdmin)")
    public void clubAdmin() {
    }
    @Around("clubSelf()")
    public Object clubCheck(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        try {
            HttpServletRequest request = ParamUtil.getRequest(proceedingJoinPoint.getArgs());
            HttpServletResponse response = ParamUtil.getResponse(proceedingJoinPoint.getArgs());
            long userId = (long) request.getAttribute("userId");
            ClubRole role = roleService.getRole(userId);
            Club club = roleService.getClubByUserId(userId);
            if(club == null)
                return Result.send(PERMISSIN_DENIED, null, NOT_MANAGE_CLUB);
            if(role == null || role.getLv() != 1)
                return Result.send(PERMISSIN_DENIED, null, PERMISSIN_DENIED_MESSAGE);
            request.setAttribute("club", club);
        }catch (Exception e){
            return Result.send(SERVER_ERROR, null, BASE_MESSAGE_SERVER_ERROR);

        }
        return proceedingJoinPoint.proceed();
    }
    @Around("clubAdmin()")
    public Object clubAdminCheck(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        try {
            HttpServletRequest request = ParamUtil.getRequest(proceedingJoinPoint.getArgs());
            HttpServletResponse response = ParamUtil.getResponse(proceedingJoinPoint.getArgs());
            long userId = (long) request.getAttribute("userId");
            ClubRole role = roleService.getRole(userId);
            if(role == null || role.getLv() <= 1)
                return Result.send(PERMISSIN_DENIED, null, PERMISSIN_DENIED_MESSAGE);
            request.setAttribute("clubRole", role);
        }catch (Exception e){
            return Result.send(SERVER_ERROR, null, BASE_MESSAGE_SERVER_ERROR);

        }
        return proceedingJoinPoint.proceed();
    }
}
