package com.heartsuit.showcase.domain.user;

import java.util.Date;
import java.util.List;

/**
 * The type Expert.专家
 */
public class Expert {
    private String expertId; // 专家id
    private String subscriberId; // 用户id
    private String email; // 邮箱
    private String name; // 姓名
    private String photographUrl; // 照片url
    private String organization; // 机构
    private List<String> researchArea; // 研究领域
    private String personalHomepage; // 个人主页
    private String updateDate; // 更新时间
    private List<String> achievement; // 成果id
    private List<String> feedbackInformationId; // 反馈id

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
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets photograph url.
     *
     * @return the photograph url
     */
    public String getPhotographUrl() {
        return photographUrl;
    }

    /**
     * Sets photograph url.
     *
     * @param photographUrl the photograph url
     */
    public void setPhotographUrl(String photographUrl) {
        this.photographUrl = photographUrl;
    }

    /**
     * Gets organization.
     *
     * @return the organization
     */
    public String getOrganization() {
        return organization;
    }

    /**
     * Sets organization.
     *
     * @param organization the organization
     */
    public void setOrganization(String organization) {
        this.organization = organization;
    }

    /**
     * Gets research area.
     *
     * @return the research area
     */
    public List<String> getResearchArea() {
        return researchArea;
    }

    /**
     * Sets research area.
     *
     * @param researchArea the research area
     */
    public void setResearchArea(List<String> researchArea) {
        this.researchArea = researchArea;
    }

    /**
     * Gets personal homepage.
     *
     * @return the personal homepage
     */
    public String getPersonalHomepage() {
        return personalHomepage;
    }

    /**
     * Sets personal homepage.
     *
     * @param personalHomepage the personal homepage
     */
    public void setPersonalHomepage(String personalHomepage) {
        this.personalHomepage = personalHomepage;
    }

    /**
     * Gets update date.
     *
     * @return the update date
     */
    public String getUpdateDate() {
        return updateDate;
    }

    /**
     * Sets update date.
     *
     * @param updateDate the update date
     */
    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * Gets achievement.
     *
     * @return the achievement
     */
    public List<String> getAchievement() {
        return achievement;
    }

    /**
     * Sets achievement.
     *
     * @param achievement the achievement
     */
    public void setAchievement(List<String> achievement) {
        this.achievement = achievement;
    }

    /**
     * Gets feedback information id.
     *
     * @return the feedback information id
     */
    public List<String> getFeedbackInformationId() {
        return feedbackInformationId;
    }

    /**
     * Sets feedback information id.
     *
     * @param feedbackInformationId the feedback information id
     */
    public void setFeedbackInformationId(List<String> feedbackInformationId) {
        this.feedbackInformationId = feedbackInformationId;
    }
}
