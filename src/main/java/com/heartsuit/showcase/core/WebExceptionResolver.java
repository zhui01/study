package com.heartsuit.showcase.core;

import com.heartsuit.showcase.core.error.ErrorCode;
import com.heartsuit.showcase.core.error.ErrorCodeException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Administrator
 */
@Controller
public class WebExceptionResolver implements HandlerExceptionResolver {
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * 如果Controller层抛出异常，则返回内部包装出来的异常信息提示；
     * 如果不是ErrorCodeException，则返回未知异常，请联系管理员；
     * 如果是ErrorCodeException,则返回对应的异常信息；
     * @param httpServletRequest 请求
     * @param httpServletResponse 响应
     * @param o controller对象
     * @param e 异常
     * @return 空的ModelAndView
     */
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest,
                                         HttpServletResponse httpServletResponse,
                                         Object o,
                                         Exception e) {
        LOGGER.error(e.getMessage(), e);
        ModelAndView modelAndView = new ModelAndView();
        if (e instanceof ErrorCodeException) {
            ErrorCodeException errorCodeException = (ErrorCodeException) e;
            outputException(httpServletResponse, errorCodeException);
        }
        else {
            ErrorCodeException errorCodeException = new ErrorCodeException(ErrorCode.UNKNOWN_EXCEPTION);
            outputException(httpServletResponse, errorCodeException);
        }
        return modelAndView;
    }

    private void outputException(HttpServletResponse httpServletResponse, ErrorCodeException errorCodeException) {
        httpServletResponse.setStatus(500);
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.setCharacterEncoding("UTF-8");
        String result = String.format("{\"success\":\"false\",\"msg\":\"%s\"}", errorCodeException.getMessage());
        try {
            httpServletResponse.getWriter().write(result);
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
    }
}
