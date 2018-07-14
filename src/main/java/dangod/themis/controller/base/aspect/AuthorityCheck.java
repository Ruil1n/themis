package dangod.themis.controller.base.aspect;

import dangod.themis.controller.base.annotation.ContainAuthority;
import dangod.themis.core.result.Result;
import dangod.themis.service.core.AuthorityService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static dangod.themis.controller.base.constant.Message.PERMISSIN_DENIED_MESSAGE;
import static dangod.themis.controller.base.constant.Status.PERMISSIN_DENIED;
/**
 * 前置检查: TokenCheck
 * 检测该用户是否拥有权限
 */
@Component
@Aspect
@Order(-98)
public class AuthorityCheck {
    @Pointcut("@annotation(dangod.themis.controller.base.annotation.ContainAuthority)")
    public void RequestCheck() {
    }

    @Autowired
    private AuthorityService authorityService;

    @Around("RequestCheck() && @annotation(authority)")
    public Object containAuthority(ProceedingJoinPoint proceedingJoinPoint, ContainAuthority authority) throws Throwable {
        HttpServletRequest request = ParamUtil.getRequest(proceedingJoinPoint.getArgs());
        HttpServletResponse response = ParamUtil.getResponse(proceedingJoinPoint.getArgs());
        List<Long> authorityList = authorityService.getAuthoritiesByUserId((long)request.getAttribute("userId"));
        if(authorityList == null||!authorityList.contains(authority.value())){
            return Result.send(PERMISSIN_DENIED, null, PERMISSIN_DENIED_MESSAGE);
        }
        return proceedingJoinPoint.proceed();
    }
}
