package com.heartsuit.showcase.service.impl;

import com.heartsuit.showcase.core.error.ErrorCodeException;
import com.heartsuit.showcase.dao.ExpertDao;
import com.heartsuit.showcase.dao.SubscriberDao;
import com.heartsuit.showcase.domain.SubscriberFollowedExpert;
import com.heartsuit.showcase.domain.user.Expert;
import com.heartsuit.showcase.domain.user.Subscriber;
import com.heartsuit.showcase.service.SubscriberService;
import com.heartsuit.showcase.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * The type Subscriber service.
 */
@Component
public class SubscriberServiceImpl implements SubscriberService {

    private SubscriberDao subscriberDao;
    private ExpertDao expertDao;
    private IMailServiceImpl iMailService;

    /**
     * Instantiates a new Subscriber service.
     *  @param subscriberDao the subscriber dao
     * @param expertDao the expert dao
     * @param iMailService iMailService
     */
    @Autowired
    public SubscriberServiceImpl(SubscriberDao subscriberDao,
                                 ExpertDao expertDao,
                                 IMailServiceImpl iMailService) {
        this.subscriberDao = subscriberDao;
        this.expertDao = expertDao;
        this.iMailService = iMailService;
    }

    /**
     * Login integer.
     *
     * @param subscriber the subscriber
     * @return the long
     */
    @Override
    public long loginPassword(Subscriber subscriber) throws Exception {
        long subscriberByEmail = subscriberDao.findSubscriberByEmailAndIsActivation(subscriber);
        long tenantByEmailAndPassWord = subscriberDao.findSubscriberByEmailAndPassWord(subscriber);
        return subscriberByEmail > 0 ? (tenantByEmailAndPassWord > 0 ? 0 : 1): 2;
    }

    /**
     * Login code long.
     *
     * @param subscriber the subscriber
     * @return the long
     */
    @Override
    public long loginCode(Subscriber subscriber)
    {
        long tenantByEmailAndCode = subscriberDao.findSubscriberByEmailAndCode(subscriber);
        return tenantByEmailAndCode > 0 ? 0 : 1;
    }

    /**
     * Sets code.
     *
     * @param subscriber the subscriber
     */
    @Override
    public void setCode(Subscriber subscriber)
    {
        subscriberDao.setCode(subscriber);
    }

    /**
     * Sets verification code.
     *
     * @param subscriber the subscriber
     */
    @Override
    public void setVerificationCode(Subscriber subscriber)
    {
        subscriberDao.setVerificationCode(subscriber);
    }

    /**
     * Sets password by subscriber id.
     *
     * @param subscriber the subscriber
     * @throws Exception the exception
     */
    @Override
    public void setPasswordBySubscriberId(Subscriber subscriber) throws Exception {
        subscriberDao.setPassWordBySubscriberId(subscriber);
    }

    /**
     * Sets subscriber by subscriber id.
     *
     * @param subscriber the subscriber
     */
    @Override
    public void setSubscriberBySubscriberId(Subscriber subscriber)
    {
        subscriberDao.setSubscriberBySubscriberId(subscriber);
    }

    /**
     * Sets password by email.
     *
     * @param subscriber the subscriber
     * @throws Exception the exception
     */
    @Override
    public void setPasswordByEmail(Subscriber subscriber) throws Exception {
        subscriberDao.setPassWordByEmail(subscriber);
    }

    /**
     * Check subscriber by subscriber id and old password long.
     *
     * @param subscriber the subscriber
     * @return the long
     * @throws Exception the exception
     */
    @Override
    public long checkSubscriberBySubscriberIdAndOldPassword(Subscriber subscriber) throws Exception {
        return subscriberDao.findSubscriberBySubscriberIdAndPassWord(subscriber);
    }

    /**
     * Insert long.
     *
     * @param subscriber the subscriber
     * @return the long
     */
    @Override
    public long insert(Subscriber subscriber) throws Exception {
        long findSubscriberByEmail = subscriberDao.findSubscriberByEmail(subscriber);
        if(findSubscriberByEmail == 0){
            subscriberDao.insert(subscriber);
        }
        return findSubscriberByEmail;
    }

    /**
     * Add followed expert.
     *
     * @param subscriberFollowedExpert the subscriber followed expert
     */
    @Override
    public void addFollowedExpert(SubscriberFollowedExpert subscriberFollowedExpert){
        subscriberDao.addFollowedExpert(subscriberFollowedExpert);
    }

    /**
     * Gets subscriber by subscriber id.
     *
     * @param subscriber the subscriber
     * @return the subscriber by subscriber id
     */
    @Override
    public Subscriber getSubscriberBySubscriberId(Subscriber subscriber)
    {
        return subscriberDao.getSubscriberBySubscriberId(subscriber);
    }

    /**
     * Gets subscriber by email.
     *
     * @param subscriber the subscriber
     * @return the subscriber by email
     */
    @Override
    public Subscriber getSubscriberByEmail(Subscriber subscriber)
    {
        return subscriberDao.getSubscriberByEmail(subscriber);
    }

    /**
     * Check subscriber by email and verification code long.
     *
     * @param subscriber the subscriber
     * @return the long
     */
    @Override
    public long checkSubscriberByEmailAndVerificationCode(Subscriber subscriber)
    {
        return subscriberDao.findSubscriberByEmailAndVerificationCode(subscriber);
    }

    /**
     * Check subscriber by email and is activation long.
     *
     * @param subscriber the subscriber
     * @return the long
     */
    @Override
    public long checkSubscriberByEmailAndIsActivation(Subscriber subscriber)
    {
        return subscriberDao.findSubscriberByEmailAndIsActivation(subscriber);
    }

    /**
     * Check subscriber by subscriber id long.
     *
     * @param subscriber the subscriber
     * @return the long
     */
    @Override
    public boolean checkSubscriberBySubscriberId(Subscriber subscriber) throws ErrorCodeException {
        Subscriber subscriberResult = subscriberDao.findSubscriberBySubscriberId(subscriber);
        return StringUtil.isNullOrEmpty(subscriberResult.getSubscriberId());
    }

    /**
     * Check subscriber by email long.
     *
     * @param subscriber the subscriber
     * @return the long
     */
    @Override
    public long checkSubscriberByEmail(Subscriber subscriber){
        return subscriberDao.findSubscriberByEmail(subscriber);
    }

    /**
     * Update activation status.
     *
     * @param subscriber the subscriber
     */
    @Override
    public void updateActivationStatus(Subscriber subscriber){
        subscriberDao.updateActivationStatus(subscriber);
    }

    /**
     * Reset email.
     *
     * @param subscriber the subscriber
     */
    @Override
    public void resetEmail(Subscriber subscriber){
        subscriberDao.resetEmail(subscriberDao.getSubscriberByModifyCode(subscriber));
    }

    /**
     * Check subscriber by modify code long.
     *
     * @param subscriber the subscriber
     * @return the long
     */
    @Override
    public long checkSubscriberByModifyCode(Subscriber subscriber){
        return subscriberDao.findSubscriberByModifyCode(subscriber);
    }

    /**
     * Set new email.
     *
     * @param subscriber the subscriber
     */
    @Override
    public void setNewEmail(Subscriber subscriber){
        subscriberDao.setNewEmail(subscriber);
    }

    /**
     * Set modify code.
     *
     * @param subscriber the subscriber
     */
    @Override
    public void setModifyCode(Subscriber subscriber){
        subscriberDao.setModifyCode(subscriber);
    }

    @Override
    public void invitedExpert(String expertId) throws Exception {
        Expert expert = new Expert();
        expert.setExpertId(expertId);
        Expert byId = expertDao.findById(expert);

        Subscriber user = new Subscriber();
        user.setEmail(byId.getEmail());
        Subscriber byEmail = subscriberDao.findByEmail(user);
        if (byId.getExpertId() != null && StringUtil.isNullOrEmpty(byEmail.getSubscriberId())) {
            Subscriber subscriber = new Subscriber();
            subscriber.setEmail(byId.getEmail());
            subscriber.setIsActivation("1");
            subscriber.setPassWord("6666"); // 初始密码6666
            subscriber.setNickName(byId.getName());
            subscriber.setExpertId(expertId);
            subscriberDao.insert(subscriber);
            // TODO: 2019/12/1 0001 提供官网主页
            iMailService.sendHtmlMail(byId.getEmail(), "专家资源平台",
                    "亲爱的专家，我们专家资源平台将为你提供最优质的的服务，请收下我们为您准备的账号密码，谢谢支持！"
                            + "登录邮箱为：" + byId.getEmail() + ", 初始密码为：6666");
        }
    }
}
