package com.heartsuit.showcase.service.impl;

import com.heartsuit.showcase.dao.CookieDao;
import com.heartsuit.showcase.domain.Cookie;
import com.heartsuit.showcase.service.CookieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * The type Cookie service.
 */
@Component
public class CookieServiceImpl implements CookieService {

    private CookieDao cookieDao;

    /**
     * Instantiates a new Cookie service.
     *
     * @param cookieDao the cookie dao
     */
    @Autowired
    public CookieServiceImpl(CookieDao cookieDao) {
        this.cookieDao = cookieDao;
    }


    /**
     * Insert.
     *
     * @param subscriberId the subscriber id
     */
    @Override
    public Cookie insert(String subscriberId){
        Cookie newCookie = new Cookie();
        newCookie.setSubscriberId(subscriberId);
        return cookieDao.insert(newCookie);
    }

    /**
     * Get cookie by cookie id cookie.
     *
     * @param cookie the cookie
     * @return the cookie
     */
    @Override
    public Cookie getCookieByCookieId(Cookie cookie){
        return cookieDao.getCookieByCookieId(cookie);
    }

    /**
     * Compare overdue time boolean.
     *
     * @param cookie the cookie
     * @return the boolean
     */
    @Override
    public boolean compareOverdueTime(Cookie cookie){
        return cookieDao.compareOverdueTime(cookie);
    }

    /**
     * Find cookie by cookie id long.
     *
     * @param cookie the cookie
     * @return the long
     */
    @Override
    public long findCookieByCookieId(Cookie cookie){
        return cookieDao.findCookieByCookieId(cookie);
    }
}
