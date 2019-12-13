package com.heartsuit.showcase.domain.user;

import java.util.List;

/**
 * The type Subscriber.用户
 */
public class Subscriber {
    private String subscriberId; // 用户id
    private String email; // 邮箱
    private String passWord; // 密码，md5哈希值
    private String nickName; // 昵称
    private String applicationId; // 申请id
    private String expertId; // 所属专家id
    private String verificationCode; //修改密码验证码
    private String activationCode; // 激活码
    private String isActivation; //是否激活
    private String code; //登录验证码
    private String newEmail; //修改的新邮箱
    private String modifyCode; //修改邮箱的激活码
    private List<String> followedExpert; //该用户所关注的专家
    private List<String> feedbackInformation; // 该用户所有提交反馈的信息id


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
     * Gets pass word.
     *
     * @return the pass word
     */
    public String getPassWord() {
        return passWord;
    }

    /**
     * Sets pass word.
     *
     * @param passWord the pass word
     */
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    /**
     * Gets nick name.
     *
     * @return the nick name
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * Sets nick name.
     *
     * @param nickName the nick name
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

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
     * Gets feedback information.
     *
     * @return the feedback information
     */
    public List<String> getFeedbackInformation() {
        return feedbackInformation;
    }

    /**
     * Gets followed expert.
     *
     * @return the followed expert
     */
    public List<String> getFollowedExpert() {
        return followedExpert;
    }

    /**
     * Sets followed expert.
     *
     * @param followedExpert the followed expert
     */
    public void setFollowedExpert(List<String> followedExpert) {
        this.followedExpert = followedExpert;
    }

    /**
     * Sets feedback information.
     *
     * @param feedbackInformation the feedback information
     */
    public void setFeedbackInformation(List<String> feedbackInformation) {
        this.feedbackInformation = feedbackInformation;
    }

    /**
     * Gets activation code.
     *
     * @return the activation code
     */
    public String getActivationCode() {
        return activationCode;
    }

    /**
     * Sets activation code.
     *
     * @param activationCode the activation code
     */
    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    /**
     * Gets code.
     *
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets code.
     *
     * @param code the code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Gets is activation.
     *
     * @return the is activation
     */

    public String getIsActivation() {
        return isActivation;
    }

    /**
     * Sets is activation.
     *
     * @param isActivation the is activation
     */
    public void setIsActivation(String isActivation) {
        this.isActivation = isActivation;
    }

    /**
     * Gets verification code.
     *
     * @return the verification code
     */
    public String getVerificationCode() {
        return verificationCode;
    }

    /**
     * Sets verification code.
     *
     * @param verificationCode the verification code
     */
    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    /**
     * Gets new email.
     *
     * @return the new email
     */
    public String getNewEmail() {
        return newEmail;
    }

    /**
     * Sets new email.
     *
     * @param newEmail the new email
     */
    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }

    /**
     * Gets modify code.
     *
     * @return the modify code
     */
    public String getModifyCode() {
        return modifyCode;
    }

    /**
     * Sets modify code.
     *
     * @param modifyCode the modify code
     */
    public void setModifyCode(String modifyCode) {
        this.modifyCode = modifyCode;
    }
}
