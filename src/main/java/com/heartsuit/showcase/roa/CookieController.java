package com.heartsuit.showcase.roa;

import com.heartsuit.showcase.core.error.ErrorCode;
import com.heartsuit.showcase.core.error.ErrorCodeException;
import com.heartsuit.showcase.domain.Cookie;
import com.heartsuit.showcase.service.CookieService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * The type Subscriber controller.
 */
@RestController //@Component 类的注入
@RequestMapping("/cookie")
@Api(tags = "缓存信息接口")

public class CookieController {

    private static final Logger LOGGER = LogManager.getLogger();
    private CookieService cookieService;


    /**
     * Instantiates a new Cookie controller.
     *
     * @param cookieService the cookie service
     */
    @Autowired
    public CookieController(CookieService cookieService) {
        this.cookieService = cookieService;
    }

    /**
     * 用户通过cookieId查询subscribeId
     * @param cookie
     * @return cookieId 对应的 subscribeId
     */
    @CrossOrigin
    @ApiOperation(value = "查询用户Id",notes = "参数为cookieId")
    @RequestMapping(value = "/get/cookie", method = RequestMethod.POST)
    @ResponseBody
    public Cookie getCookieByCookieId(@RequestBody Cookie cookie) throws Exception{
        if(cookieService.findCookieByCookieId(cookie)!=1) {
            throw new ErrorCodeException(ErrorCode.THIS_COOKIE_ID_ERROR_10012);
        }
        else {
            if(cookieService.compareOverdueTime(cookie)){
                return cookieService.getCookieByCookieId(cookie);
            }
            else
                throw new ErrorCodeException(ErrorCode.LOGIN_TIMEOUT_10013);
        }
    }
}
