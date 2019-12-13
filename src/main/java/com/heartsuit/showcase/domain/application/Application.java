package com.heartsuit.showcase.domain.application;

import java.util.List;

/**
 * The type Application.申请表
 */
public class Application {
    private String applicationId; // 申请id
    private String subscriberId; // 用户id
    private String expertId; // 专家门户页id
    private ApplicationType applicationType; // 申请类型
    private String email; // 邮箱
    private String name; // 姓名
    private String photographUrl; // 照片url
    private String organization; // 机构
    private List<String> researchArea; // 研究领域
    private String personalHomepage; // 个人主页
    private String createDate; // 提交时间
    private String remarks; // 备注
    private String applicationStatus; // 申请状态

    /**
     * Gets application id.
     *
     * @return the application id
     */
    public String getApplicationId() {
        return applicationId;
    }

    /**
     * Sets application id.
     *
     * @param applicationId the application id
     */
    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
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
     * Gets application type.
     *
     * @return the application type
     */
    public ApplicationType getApplicationType() {
        return applicationType;
    }

    /**
     * Sets application type.
     *
     * @param applicationType the application type
     */
    public void setApplicationType(ApplicationType applicationType) {
        this.applicationType = applicationType;
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
     * Gets create date.
     *
     * @return the create date
     */
    public String getCreateDate() {
        return createDate;
    }

    /**
     * Sets create date.
     *
     * @param createDate the create date
     */
    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    /**
     * Gets remarks.
     *
     * @return the remarks
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * Sets remarks.
     *
     * @param remarks the remarks
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
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

    /**
     * Gets application status.
     *
     * @return the application status
     */
    public String getApplicationStatus() {
        return applicationStatus;
    }

    /**
     * Sets application status.
     *
     * @param applicationStatus the application status
     */
    public void setApplicationStatus(String applicationStatus) {
        this.applicationStatus = applicationStatus;
    }
}
