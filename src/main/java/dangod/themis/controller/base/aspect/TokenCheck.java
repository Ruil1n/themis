package dangod.themis.controller.base.aspect;

import dangod.themis.core.result.Result;
import dangod.themis.service.common.TokenService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Base64;

import static dangod.themis.controller.base.constant.Message.TOKEN_INVAILD_MESSAGE;
import static dangod.themis.controller.base.constant.Status.UNAUTHORIZED;
import static dangod.themis.controller.base.constant.AnnotationConstant.AUTHORIZATION;
/**
 * 前置检查:
 * 检测是否登录并解析出userId
 */
@Component
@Aspect
@Order(-99)
public class TokenCheck {

    @Pointcut("@annotation(dangod.themis.controller.base.annotation.Authorization)")
    public void RequestCheck() {
    }

    @Autowired
    private TokenService tokenService;

    @Around("RequestCheck()")
    public Object tokenValid(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        HttpServletRequest request = ParamUtil.getRequest(proceedingJoinPoint.getArgs());
        HttpServletResponse response = ParamUtil.getResponse(proceedingJoinPoint.getArgs());
        String token = request.getHeader(AUTHORIZATION);

        if (token == null || !tokenService.checkToken(token)) {
            return Result.send(UNAUTHORIZED, null, TOKEN_INVAILD_MESSAGE);
        }

        byte[] bytes = Base64.getDecoder().decode(token);
        String[] arr = new String(bytes, "utf-8").split("-");
        request.setAttribute("userId", Long.parseLong(arr[0]));
        return proceedingJoinPoint.proceed();
    }


}
