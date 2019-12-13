package com.heartsuit.showcase.service;

import com.heartsuit.showcase.domain.Cookie;

/**
 * The interface Subscriber service.
 */
public interface CookieService {

    /**
     * Insert.
     *
     * @param subscriberId the subscriber id
     */
    Cookie insert(String subscriberId);

    /**
     * Gets cookie by cookie id.
     *
     * @param cookie the cookie
     * @return the cookie by cookie id
     */
    Cookie getCookieByCookieId(Cookie cookie);

    /**
     * Compare overdue time boolean.
     *
     * @param cookie the cookie
     * @return the boolean
     */
    boolean compareOverdueTime(Cookie cookie);

    /**
     * Find cookie by cookie id long.
     *
     * @param cookie the cookie
     * @return the long
     */
    long findCookieByCookieId(Cookie cookie);
}
