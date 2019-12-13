package com.heartsuit.showcase.domain.hyperlink;

import javax.xml.crypto.Data;

/**
 * The type Verify hyperlink.链接验证
 */
public class VerifyHyperlink {
    private String value; // 表示验证链接的后缀
    private VerifyHyperlinkType verifyHyperlinkType; // 验证类型
    private String subscriber; // 对应用户
    private String expert; // 对应专家
    private Data overdueTime; // 过期时间

    /**
     * Gets value.
     *
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets value.
     *
     * @param value the value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Gets verify hyperlink type.
     *
     * @return the verify hyperlink type
     */
    public VerifyHyperlinkType getVerifyHyperlinkType() {
        return verifyHyperlinkType;
    }

    /**
     * Sets verify hyperlink type.
     *
     * @param verifyHyperlinkType the verify hyperlink type
     */
    public void setVerifyHyperlinkType(VerifyHyperlinkType verifyHyperlinkType) {
        this.verifyHyperlinkType = verifyHyperlinkType;
    }

    /**
     * Gets subscriber.
     *
     * @return the subscriber
     */
    public String getSubscriber() {
        return subscriber;
    }

    /**
     * Sets subscriber.
     *
     * @param subscriber the subscriber
     */
    public void setSubscriber(String subscriber) {
        this.subscriber = subscriber;
    }

    /**
     * Gets expert.
     *
     * @return the expert
     */
    public String getExpert() {
        return expert;
    }

    /**
     * Sets expert.
     *
     * @param expert the expert
     */
    public void setExpert(String expert) {
        this.expert = expert;
    }

    /**
     * Gets overdue time.
     *
     * @return the overdue time
     */
    public Data getOverdueTime() {
        return overdueTime;
    }

    /**
     * Sets overdue time.
     *
     * @param overdueTime the overdue time
     */
    public void setOverdueTime(Data overdueTime) {
        this.overdueTime = overdueTime;
    }
}
