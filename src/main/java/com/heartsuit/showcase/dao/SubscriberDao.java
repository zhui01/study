package com.heartsuit.showcase.dao;


import com.heartsuit.showcase.core.error.ErrorCode;
import com.heartsuit.showcase.core.error.ErrorCodeException;
import com.heartsuit.showcase.domain.FeedbackInformation;
import com.heartsuit.showcase.domain.SubscriberFollowedExpert;
import com.heartsuit.showcase.domain.application.Application;
import com.heartsuit.showcase.domain.user.Subscriber;
import com.heartsuit.showcase.util.AesEncryptUtils;
import com.heartsuit.showcase.util.ArrayUtil;
import com.heartsuit.showcase.util.StringUtil;
import com.mongodb.client.FindIterable;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;


/**
 * The type Subscriber dao.
 */
@Component
public class SubscriberDao{
    private MongoTemplate mongoTemplate;

    /**
     * Instantiates a new Subscriber dao.
     *
     * @param mongoTemplate the mongo template
     */
    @Autowired
    public SubscriberDao(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    private static final String COLLECTION_NAME = "Subscriber";

    /**
     * Insert subscriber
     *
     * @param subscriber
     * @throws Exception
     */
    public void insert(Subscriber subscriber) throws Exception {
        Document document = new Document();
        String activationCode=UUID.randomUUID().toString().replace("-","");
        document.put("email", StringUtil.convertNullToEmpty(subscriber.getEmail()));
        document.put("passWord", AesEncryptUtils.encrypt(StringUtil.convertNullToEmpty(subscriber.getPassWord())));
        document.put("nickName", StringUtil.convertNullToEmpty(subscriber.getNickName()));
        document.put("applicationId", StringUtil.convertNullToEmpty(subscriber.getApplicationId()));
        document.put("expertId", StringUtil.convertNullToEmpty(subscriber.getExpertId()));
        document.put("isActivation", StringUtil.convertNullToEmpty(subscriber.getIsActivation()));
        document.put("verificationCode", StringUtil.convertNullToEmpty(subscriber.getVerificationCode()));
        document.put("activationCode", StringUtil.convertNullToEmpty(activationCode));
        document.put("code",StringUtil.convertNullToEmpty(subscriber.getCode()));
        document.put("modifyCode",StringUtil.convertNullToEmpty(subscriber.getModifyCode()));
        document.put("newEmail",StringUtil.convertNullToEmpty(subscriber.getNewEmail()));
        document.put("followedExpert",ArrayUtil.convertNullToEmpty(subscriber.getFollowedExpert()));
        document.put("feedbackInformation", ArrayUtil.convertNullToEmpty(subscriber.getFeedbackInformation()));
        mongoTemplate.getCollection(COLLECTION_NAME).insertOne(document);
    }

    /**
     * Gets subscriber by subscriber id.
     *
     * @param subscriber the subscriber
     * @return the subscriber by subscriber id
     */
    public Subscriber getSubscriberBySubscriberId(Subscriber subscriber) {
        Document document = new Document();
        document.put("_id", new ObjectId(subscriber.getSubscriberId()));
        Document findDocument = mongoTemplate.getCollection(COLLECTION_NAME).find(document).first();
        Subscriber findSubscriber = new Subscriber();
        convertSubscriber(findDocument, findSubscriber);
        return findSubscriber;
    }

    /**
     * Gets subscriber by email.
     *
     * @param subscriber the subscriber
     * @return the subscriber by email
     */
    public Subscriber getSubscriberByEmail(Subscriber subscriber) {
        Document document = new Document();
        document.put("email", subscriber.getEmail());
        Document findDocument = mongoTemplate.getCollection(COLLECTION_NAME).find(document).first();
        Subscriber findSubscriber = new Subscriber();
        convertSubscriber(findDocument, findSubscriber);
        return findSubscriber;
    }

    /**
     * Gets subscriber by modify code.
     *
     * @param subscriber the subscriber
     * @return the subscriber by modify code
     */
    public Subscriber getSubscriberByModifyCode(Subscriber subscriber) {
        Document document = new Document();
        document.put("modifyCode", subscriber.getModifyCode());
        Document findDocument = mongoTemplate.getCollection(COLLECTION_NAME).find(document).first();
        Subscriber findSubscriber = new Subscriber();
        convertSubscriber(findDocument, findSubscriber);
        return findSubscriber;
    }

    /**
     * Get subscriber by activation code subscriber.
     *
     * @param subscriber the subscriber
     * @return the subscriber
     */
    public Subscriber getSubscriberByActivationCode(Subscriber subscriber){
        Document document = new Document();
        document.put("activationCode", subscriber.getActivationCode());
        Document findDocument = mongoTemplate.getCollection(COLLECTION_NAME).find(document).first();
        Subscriber findSubscriber = new Subscriber();
        convertSubscriber(findDocument, findSubscriber);
        return findSubscriber;
    }

    /**
     * Update activation status.
     *
     * @param subscriber the subscriber
     */
    public void updateActivationStatus(Subscriber subscriber){
        Document document = new Document();
        document.put("activationCode", subscriber.getActivationCode());
        FindIterable<Document> documents = mongoTemplate.getCollection(COLLECTION_NAME).find(document);
        Document first = documents.first();
        if (null != first) {
            first.put("isActivation", "1");
            first.put("activationCode","");
            mongoTemplate.getCollection(COLLECTION_NAME).replaceOne(document, first);
        }
    }

    /**
     * Add followed expert.
     *
     * @param subscriberFollowedExpert the subscriber followed expert
     */
    public void addFollowedExpert(SubscriberFollowedExpert subscriberFollowedExpert)
    {
        Document document = new Document();
        document.put("_id", new ObjectId(subscriberFollowedExpert.getSubscriberId()));
        FindIterable<Document> documents = mongoTemplate.getCollection(COLLECTION_NAME).find(document);
        Document first = documents.first();
        if (null != first) {
            List<String> followedExperts = ArrayUtil.convertToList(first.get("followedExpert").toString());
            followedExperts.add(subscriberFollowedExpert.getExpertId());
            first.put("followedExpert",ArrayUtil.convertNullToEmpty(followedExperts));
            mongoTemplate.getCollection(COLLECTION_NAME).replaceOne(document, first);
        }
    }

    /**
     * Add feedback information.
     *
     * @param feedbackInformation the feedback information
     */
    public void addFeedbackInformation(FeedbackInformation feedbackInformation)
    {
        Document document = new Document();
        document.put("_id", new ObjectId(feedbackInformation.getSubscriberId()));
        FindIterable<Document> documents = mongoTemplate.getCollection(COLLECTION_NAME).find(document);
        Document first = documents.first();
        if (null != first) {
            List<String> feedbackInformations = ArrayUtil.convertToList(first.get("feedbackInformation").toString());
            feedbackInformations.add(feedbackInformation.getFeedbackInformationId());
            first.put("feedbackInformation",ArrayUtil.convertNullToEmpty(feedbackInformations) );
            mongoTemplate.getCollection(COLLECTION_NAME).replaceOne(document, first);
        }
    }

    /**
     * Reset application id.
     *
     * @param application the application
     */
    public void resetApplicationId(Application application)
    {
        Document document = new Document();
        document.put("_id", new ObjectId(application.getSubscriberId()));
        FindIterable<Document> documents = mongoTemplate.getCollection(COLLECTION_NAME).find(document);
        Document first = documents.first();
        if (null != first) {
            first.put("applicationId",application.getApplicationId());
            mongoTemplate.getCollection(COLLECTION_NAME).replaceOne(document, first);
        }
    }

    /**
     * Sets pass word by subscriber id.
     *
     * @param subscriber the subscriber
     * @throws Exception the exception
     */
    public void setPassWordBySubscriberId(Subscriber subscriber) throws Exception {
        Document document = new Document();
        document.put("_id", new ObjectId(subscriber.getSubscriberId()));
        FindIterable<Document> documents = mongoTemplate.getCollection(COLLECTION_NAME).find(document);
        Document first = documents.first();
        if (null != first) {
            first.put("passWord", AesEncryptUtils.encrypt(subscriber.getPassWord()));
            mongoTemplate.getCollection(COLLECTION_NAME).replaceOne(document, first);
        }
    }

    /**
     * Sets pass word by email.
     *
     * @param subscriber the subscriber
     * @throws Exception the exception
     */
    public void setPassWordByEmail(Subscriber subscriber) throws Exception {
        Document document = new Document();
        document.put("email", subscriber.getEmail());
        FindIterable<Document> documents = mongoTemplate.getCollection(COLLECTION_NAME).find(document);
        Document first = documents.first();
        if (null != first) {
            first.put("passWord", AesEncryptUtils.encrypt(subscriber.getPassWord()));
            first.put("verificationCode","");
            mongoTemplate.getCollection(COLLECTION_NAME).replaceOne(document, first);
        }
    }

    /**
     * Set subscriber by subscriber id.
     *
     * @param subscriber the subscriber
     */
    public void setSubscriberBySubscriberId(Subscriber subscriber){
        Document document = new Document();
        document.put("_id", new ObjectId(subscriber.getSubscriberId()));
        FindIterable<Document> documents = mongoTemplate.getCollection(COLLECTION_NAME).find(document);
        Document first = documents.first();
        if (null != first) {
            first.put("nickName", subscriber.getNickName());
            mongoTemplate.getCollection(COLLECTION_NAME).replaceOne(document, first);
        }
    }

    /**
     * Gets subscriber list by subscriber id.
     *
     * @param subscriber the subscriber
     * @return the subscriber list by subscriber id
     */
    public List<Subscriber> getSubscriberListBySubscriberId(Subscriber subscriber) {
        Document document = new Document();
        document.put("_id", new ObjectId(subscriber.getSubscriberId()));
        FindIterable<Document> documents = mongoTemplate.getCollection(COLLECTION_NAME).find(document);
        List<Subscriber> targetSubscribers = new ArrayList<>();
        for (Document document1 : documents) {
            Subscriber resultSubscriber = new Subscriber();
            convertSubscriber(document1,resultSubscriber); //将查询出来的结果转换为java建模
            targetSubscribers.add(resultSubscriber);
        }
        return targetSubscribers;
    }

    /**
     * Find all subscriber.
     *
     * @return subscriber list
     */
    public List<Subscriber> findAll() {
        FindIterable<Document> documents = mongoTemplate.getCollection(COLLECTION_NAME).find();
        ArrayList<Subscriber> subscribers = new ArrayList<>();
        for (Document document : documents) {
            Subscriber subscriber = new Subscriber();
            convertSubscriber(document, subscriber);
            subscribers.add(subscriber);
        }
        return subscribers;
    }

    /**
     * Find subscriber by email long.
     *
     * @param subscriber the subscriber
     * @return the long
     */
    public long findSubscriberByEmail(Subscriber subscriber) {
        Document document = new Document();
        document.put("email", subscriber.getEmail());
        return mongoTemplate.getCollection(COLLECTION_NAME).countDocuments(document);
    }

    /**
     * Find subscriber by email and is activation long.
     *
     * @param subscriber the subscriber
     * @return the long
     */
    public long findSubscriberByEmailAndIsActivation(Subscriber subscriber) {
        Document document = new Document();
        document.put("email", subscriber.getEmail());
        document.put("isActivation","1");
        return mongoTemplate.getCollection(COLLECTION_NAME).countDocuments(document);
    }

    /**
     * Find subscriber by subscriber id and pass word long.
     *
     * @param subscriber the subscriber
     * @return the long
     * @throws Exception the exception
     */
    public long findSubscriberBySubscriberIdAndPassWord(Subscriber subscriber) throws Exception {
        Document document = new Document();
        document.put("_id", new ObjectId(subscriber.getSubscriberId()));
        document.put("passWord",AesEncryptUtils.encrypt(subscriber.getPassWord()));
        return mongoTemplate.getCollection(COLLECTION_NAME).countDocuments(document);
    }

    /**
     * Find subscriber by subscriber id long.
     *
     * @param subscriber the subscriber
     * @return the long
     */
    public Subscriber findSubscriberBySubscriberId(Subscriber subscriber) throws ErrorCodeException {
        Document document = new Document();
        try {
            document.put("_id", new ObjectId(subscriber.getSubscriberId()));
        }
        catch (IllegalArgumentException e) {
            throw new ErrorCodeException(ErrorCode.THIS_SUBSCRIBER_ID_IS_NOT_EXIST_10011);
        }
        Document first = mongoTemplate.getCollection(COLLECTION_NAME).find(document).first();
        Subscriber result = new Subscriber();
        convertSubscriber(first, result);
        return result;
    }

    /**
     * Set code.
     *
     * @param subscriber the subscriber
     */
    public void setCode(Subscriber subscriber){
        Document document = new Document();
        document.put("email", subscriber.getEmail());
        FindIterable<Document> documents = mongoTemplate.getCollection(COLLECTION_NAME).find(document);
        Document first = documents.first();
        StringBuilder str = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            str.append(random.nextInt(10));
        }
        if (null != first) {
            first.put("code", str.toString());
            mongoTemplate.getCollection(COLLECTION_NAME).replaceOne(document, first);
        }
    }

    /**
     * Set verification code.
     *
     * @param subscriber the subscriber
     */
    public void setVerificationCode(Subscriber subscriber){
        Document document = new Document();
        document.put("email", subscriber.getEmail());
        FindIterable<Document> documents = mongoTemplate.getCollection(COLLECTION_NAME).find(document);
        Document first = documents.first();
        StringBuilder str = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            str.append(random.nextInt(10));
        }
        if (null != first) {
            first.put("verificationCode", str.toString());
            mongoTemplate.getCollection(COLLECTION_NAME).replaceOne(document, first);
        }
    }

    /**
     * Find subscriber by email and code long.
     *
     * @param subscriber the subscriber
     * @return the long
     */
    public long findSubscriberByEmailAndCode(Subscriber subscriber) {
        Document document = new Document();
        document.put("email", subscriber.getEmail());
        document.put("code", subscriber.getCode());
        return mongoTemplate.getCollection(COLLECTION_NAME).countDocuments(document);
    }

    /**
     * Find subscriber by email and pass word long.
     *
     * @param subscriber the subscriber
     * @return the long
     */
    public long findSubscriberByEmailAndPassWord(Subscriber subscriber) throws Exception {
        Document document = new Document();
        document.put("email", subscriber.getEmail());
        document.put("passWord", AesEncryptUtils.encrypt(subscriber.getPassWord()));
        return mongoTemplate.getCollection(COLLECTION_NAME).countDocuments(document);
    }

    /**
     * Find subscriber by email and verification code
     *
     * @param subscriber
     * @return the long
     */
    public long findSubscriberByEmailAndVerificationCode(Subscriber subscriber) {
        Document document = new Document();
        document.put("email", subscriber.getEmail());
        document.put("verificationCode", subscriber.getVerificationCode());
        return mongoTemplate.getCollection(COLLECTION_NAME).countDocuments(document);
    }

    /**
     * Find subscriber by modify code long.
     *
     * @param subscriber the subscriber
     * @return the long
     */
    public long findSubscriberByModifyCode(Subscriber subscriber) {
        Document document = new Document();
        document.put("modifyCode", subscriber.getModifyCode());
        return mongoTemplate.getCollection(COLLECTION_NAME).countDocuments(document);
    }

    /**
     * Sets modify code.
     *
     * @param subscriber the subscriber
     */
    public void setModifyCode(Subscriber subscriber) {
        Document document = new Document();
        document.put("_id", new ObjectId(subscriber.getSubscriberId()));
        FindIterable<Document> documents = mongoTemplate.getCollection(COLLECTION_NAME).find(document);
        Document first = documents.first();
        if (null != first) {
            String modifyCode = UUID.randomUUID().toString().replace("-","");
            first.put("modifyCode",modifyCode);
            mongoTemplate.getCollection(COLLECTION_NAME).replaceOne(document, first);
        }
    }

    /**
     * Sets new email.
     *
     * @param subscriber the subscriber
     */
    public void setNewEmail(Subscriber subscriber) {
        Document document = new Document();
        document.put("modifyCode", subscriber.getModifyCode());
        FindIterable<Document> documents = mongoTemplate.getCollection(COLLECTION_NAME).find(document);
        Document first = documents.first();
        if (null != first) {
            first.put("newEmail",subscriber.getNewEmail());
            mongoTemplate.getCollection(COLLECTION_NAME).replaceOne(document, first);
        }
    }

    /**
     * Reset email.
     *
     * @param subscriber the subscriber
     */
    public void resetEmail(Subscriber subscriber) {
        Document document = new Document();
        document.put("modifyCode", subscriber.getModifyCode());
        FindIterable<Document> documents = mongoTemplate.getCollection(COLLECTION_NAME).find(document);
        Document first = documents.first();
        if (null != first) {
            first.put("email",first.getString("newEmail"));
            first.put("modifyCode","");
            first.put("newEmail","");
            mongoTemplate.getCollection(COLLECTION_NAME).replaceOne(document, first);
        }
    }

    /**
     *Convert document to subscriber
     *
     * @param document
     * @param subscriber
     */
    public void convertSubscriber(Document document, Subscriber subscriber) {
        if (document == null) {
            return;
        }
        subscriber.setSubscriberId(document.get("_id").toString());
        subscriber.setEmail(document.getString("email"));
        subscriber.setPassWord(document.getString("passWord"));
        subscriber.setNickName(document.getString("nickName"));
        subscriber.setApplicationId(document.getString("applicationId"));
        subscriber.setExpertId(document.getString("expertId"));
        subscriber.setIsActivation(document.getString("isActivation"));
        subscriber.setVerificationCode(document.getString("verificationCode"));
        subscriber.setActivationCode(document.getString("activationCode"));
        subscriber.setCode(document.getString("code"));
        subscriber.setNewEmail(document.getString("newEmail"));
        subscriber.setModifyCode(document.getString("modifyCode"));
        subscriber.setFollowedExpert(ArrayUtil.convertToList((String)document.get("followedExpert")));
        subscriber.setFeedbackInformation(ArrayUtil.convertToList((String)document.get("feedbackInformation")));
    }


    public void setExpertId(Subscriber subscriber) {
        Document document = new Document();
        document.put("_id", new ObjectId(subscriber.getSubscriberId()));
        FindIterable<Document> documents = mongoTemplate.getCollection(COLLECTION_NAME).find(document);
        Document first = documents.first();
        if (null != first) {
            first.put("expertId", subscriber.getExpertId());
            mongoTemplate.getCollection(COLLECTION_NAME).replaceOne(document, first);
        }
    }

    public void setFeedbackInformation(Subscriber subscriber) {
        Document document = new Document();
        document.put("_id", new ObjectId(subscriber.getSubscriberId()));
        FindIterable<Document> documents = mongoTemplate.getCollection(COLLECTION_NAME).find(document);
        Document first = documents.first();
        if (null != first) {
            first.put("feedbackInformation", ArrayUtil.convertNullToEmpty(subscriber.getFeedbackInformation()));
            mongoTemplate.getCollection(COLLECTION_NAME).replaceOne(document, first);
        }
    }

    public Subscriber findByEmail(Subscriber user) {
        Document document = new Document();
        document.put("email", StringUtil.convertNullToEmpty(user.getEmail()));
        Document first = mongoTemplate.getCollection(COLLECTION_NAME).find(document).first();
        Subscriber subscriberResult = new Subscriber();
        convertSubscriber(first, subscriberResult);
        return subscriberResult;
    }
}
