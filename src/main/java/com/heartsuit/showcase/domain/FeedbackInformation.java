package com.heartsuit.showcase.domain;

import java.util.Date;

/**
 * The type Feedback information.反馈信息
 */
public class FeedbackInformation {
    private String feedbackInformationId; // 反馈信息id
    private String feedbackContent; // 反馈内容
    private String feedbackTime; // 反馈时间
    private String subscriberId; // 用户id
    private String expertId; // 专家id
    private String isProcessed; // 是否被处理

    /**
     * Gets is processed.
     *
     * @return the is processed
     */
    public String getIsProcessed() {
        return isProcessed;
    }

    /**
     * Sets is processed.
     *
     * @param isProcessed the is processed
     */
    public void setIsProcessed(String isProcessed) {
        this.isProcessed = isProcessed;
    }

    /**
     * Gets feedback information id.
     *
     * @return the feedback information id
     */
    public String getFeedbackInformationId() {
        return feedbackInformationId;
    }

    /**
     * Sets feedback information id.
     *
     * @param feedbackInformationId the feedback information id
     */
    public void setFeedbackInformationId(String feedbackInformationId) {
        this.feedbackInformationId = feedbackInformationId;
    }

    /**
     * Gets feedback content.
     *
     * @return the feedback content
     */
    public String getFeedbackContent() {
        return feedbackContent;
    }

    /**
     * Sets feedback content.
     *
     * @param feedbackContent the feedback content
     */
    public void setFeedbackContent(String feedbackContent) {
        this.feedbackContent = feedbackContent;
    }

    /**
     * Gets feedback time.
     *
     * @return the feedback time
     */
    public String getFeedbackTime() {
        return feedbackTime;
    }

    /**
     * Sets feedback time.
     *
     * @param feedbackTime the feedback time
     */
    public void setFeedbackTime(String feedbackTime) {
        this.feedbackTime = feedbackTime;
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
     * Gets expert id.
     *
     * @return the expert id
     */
    public String getExpertId() {
        return expertId;
    }

    /**
     * Sets expert id.
     *
     * @param expertId the expert id
     */
    public void setExpertId(String expertId) {
        this.expertId = expertId;
    }
}
