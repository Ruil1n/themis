package dangod.themis.core.config.error;

import dangod.themis.core.result.Result;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static dangod.themis.controller.base.constant.Message.BASE_MESSAGE_BAD_REQUEST;
import static dangod.themis.controller.base.constant.Message.BASE_MESSAGE_NOT_FOUND;
import static dangod.themis.controller.base.constant.Message.BASE_MESSAGE_SERVER_ERROR;
import static dangod.themis.controller.base.constant.Status.FAIL;
import static dangod.themis.controller.base.constant.Status.NOT_FIND;
import static dangod.themis.controller.base.constant.Status.SERVER_ERROR;

@Controller
public class BaseError implements ErrorController {

    private static final String ERROR_PATH = "/error";

    @ResponseBody
    @RequestMapping(value=ERROR_PATH)
    public String handleError(HttpServletRequest request, HttpServletResponse response, Model model){
        switch (response.getStatus()){
            case 400:
                return Result.send(FAIL, null, BASE_MESSAGE_BAD_REQUEST);
            case 404:
                return Result.send(NOT_FIND, null, BASE_MESSAGE_NOT_FOUND);
            case 500:
                return Result.send(SERVER_ERROR, null, BASE_MESSAGE_SERVER_ERROR);
            default:
                return Result.send(SERVER_ERROR, null, BASE_MESSAGE_SERVER_ERROR);
        }
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

}
