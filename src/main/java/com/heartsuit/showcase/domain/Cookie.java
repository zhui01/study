package com.heartsuit.showcase.domain;

import javax.xml.crypto.Data;

/**
 * The type Cookie.
 */
public class Cookie {
    private String cookieId; // 值
    private String subscriberId; // 用户id
    private String overdueTime; // 过期时间

    /**
     * Gets cookie id.
     *
     * @return the cookie id
     */
    public String getCookieId() {
        return cookieId;
    }

    /**
     * Sets cookie id.
     *
     * @param cookieId the cookie id
     */
    public void setCookieId(String cookieId) {
        this.cookieId = cookieId;
    }

    /**
     * Gets subscriber id.
     *
     * @return the subscriber id
     */
    public String getSubscriberId() {
        return subscriberId;
    }

    /**
     * Sets subscriber id.
     *
     * @param subscriberId the subscriber id
     */
    public void setSubscriberId(String subscriberId) {
        this.subscriberId = subscriberId;
    }

    /**
     * Gets overdue time.
     *
     * @return the overdue time
     */
    public String getOverdueTime() {
        return overdueTime;
    }

    /**
     * Sets overdue time.
     *
     * @param overdueTime the overdue time
     */
    public void setOverdueTime(String overdueTime) {
        this.overdueTime = overdueTime;
    }
}
