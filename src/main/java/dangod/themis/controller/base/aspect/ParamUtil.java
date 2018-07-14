package dangod.themis.controller.base.aspect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

public class ParamUtil {
    protected static HttpServletRequest getRequest(Object[] args) throws UnsupportedEncodingException {
        HttpServletRequest request = null;
        for (Object arg : args) {
            if (arg instanceof HttpServletRequest) {
                request = (HttpServletRequest) arg;
                request.setCharacterEncoding("UTF-8");
                break;
            }
        }
        return request;
    }

    protected static HttpServletResponse getResponse(Object[] args) throws UnsupportedEncodingException {
        HttpServletResponse response = null;
        for (Object arg : args) {
            if (arg instanceof HttpServletResponse) {
                response = (HttpServletResponse) arg;
                response.setCharacterEncoding("UTF-8");
                break;
            }
        }
        return response;
    }
}
