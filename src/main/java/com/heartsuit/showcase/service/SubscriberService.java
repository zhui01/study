package com.heartsuit.showcase.service;

import com.heartsuit.showcase.core.error.ErrorCodeException;
import com.heartsuit.showcase.domain.SubscriberFollowedExpert;
import com.heartsuit.showcase.domain.user.Subscriber;

/**
 * The interface Subscriber service.
 */
public interface SubscriberService {

    /**
     * Login string.
     *
     * @param subscriber the subscriber
     * @return the string
     */
    long loginPassword(Subscriber subscriber) throws Exception;

    /**
     * Login code long.
     *
     * @param subscriber the subscriber
     * @return the long
     */
    long loginCode(Subscriber subscriber);

    /**
     * Sets code.
     *
     * @param subscriber the subscriber
     */
    void setCode(Subscriber subscriber);

    /**
     * Sets verification code.
     *
     * @param subscriber the subscriber
     */
    void setVerificationCode(Subscriber subscriber);

    /**
     * Sets password by subscriber id.
     *
     * @param subscriber the subscriber
     * @throws Exception the exception
     */
    void setPasswordBySubscriberId(Subscriber subscriber) throws Exception;

    /**
     * Sets password by email.
     *
     * @param subscriber the subscriber
     * @throws Exception the exception
     */
    void setPasswordByEmail(Subscriber subscriber) throws Exception;

    /**
     * Check subscriber by subscriber id and old password long.
     *
     * @param subscriber the subscriber
     * @return the long
     * @throws Exception the exception
     */
    long checkSubscriberBySubscriberIdAndOldPassword(Subscriber subscriber) throws Exception;

    /**
     * Insert long.
     *
     * @param subscriber the subscriber
     * @return the long
     */
    long insert(Subscriber subscriber) throws Exception;
    /**
     * Check subscriber.
     *
     * @param subscriber the subscriber
     * @return the subscriber
     */
    Subscriber getSubscriberBySubscriberId(Subscriber subscriber);

    /**
     * Sets subscriber by subscriber id.
     *
     * @param subscriber the subscriber
     */
    void setSubscriberBySubscriberId(Subscriber subscriber);

    /**
     * Add followed expert.
     *
     * @param subscriberFollowedExpert the subscriber followed expert
     */
    void addFollowedExpert(SubscriberFollowedExpert subscriberFollowedExpert);

    /**
     * Gets subscriber by email.
     *
     * @param subscriber the subscriber
     * @return the subscriber by email
     */
    Subscriber getSubscriberByEmail(Subscriber subscriber);

    /**
     * Check subscriber by email and verification code long.
     *
     * @param subscriber the subscriber
     * @return the long
     */
    long checkSubscriberByEmailAndVerificationCode(Subscriber subscriber);

    /**
     * Check subscriber by email and is activation long.
     *
     * @param subscriber the subscriber
     * @return the long
     */
    long checkSubscriberByEmailAndIsActivation(Subscriber subscriber);
    /**
     * Check subscriber by subscriber id long.
     *
     * @param subscriber the subscriber
     * @return the long
     */
    boolean checkSubscriberBySubscriberId(Subscriber subscriber) throws ErrorCodeException;

    /**
     * Check subscriber by email long.
     *
     * @param subscriber the subscriber
     * @return the long
     */
    long checkSubscriberByEmail(Subscriber subscriber);

    /**
     * Update activation status.
     *
     * @param subscriber the subscriber
     */
    void updateActivationStatus(Subscriber subscriber);

    /**
     * Reset email.
     *
     * @param subscriber the subscriber
     */
    void resetEmail(Subscriber subscriber);

    /**
     * Check subscriber by modify code long.
     *
     * @param subscriber the subscriber
     * @return the long
     */
    long checkSubscriberByModifyCode(Subscriber subscriber);

    /**
     * Sets new email.
     *
     * @param subscriber the subscriber
     */
    void setNewEmail(Subscriber subscriber);

    /**
     * Sets modify code.
     *
     * @param subscriber the subscriber
     */
    void setModifyCode(Subscriber subscriber);

    void invitedExpert(String expertId) throws Exception;
}
