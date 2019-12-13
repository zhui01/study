package com.heartsuit.showcase.dao;

import com.heartsuit.showcase.domain.FeedbackInformation;
import com.heartsuit.showcase.util.DateTimeUtil;
import com.heartsuit.showcase.util.StringUtil;
import com.mongodb.client.FindIterable;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FeedbackInformationDao {
    private MongoTemplate mongoTemplate;
    private static final String COLLECTION_NAME = "FeedbackInformation";
    private static final String FEEDBACK_INFORMATION_ID = "_id"; // 反馈信息id
    private static final String FEEDBACK_CONTENT = "feedbackContent"; // 反馈内容
    private static final String FEEDBACK_TIME = "feedbackTime"; // 反馈时间
    private static final String SUBSCRIBER_ID = "subscriberId"; // 用户id
    private static final String EXPERT_ID = "expertId"; // 专家id
    private static final String IS_PROCESSED = "isProcessed"; // 是否处理

    @Autowired
    public FeedbackInformationDao(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public void insert(FeedbackInformation feedbackInformation) {
        Document document = new Document();
        convertDocument(feedbackInformation, document);
        document.put(FEEDBACK_TIME, DateTimeUtil.getCurrentTime());
        document.put(IS_PROCESSED, StringUtil.convertNullToEmpty("false"));
        mongoTemplate.getCollection(COLLECTION_NAME).insertOne(document);
    }

    public FeedbackInformation findById(FeedbackInformation feedbackInformation) {
        Document document = new Document();
        document.put(FEEDBACK_INFORMATION_ID,
                new ObjectId(StringUtil.convertNullToEmpty(feedbackInformation.getFeedbackInformationId())));
        Document first = mongoTemplate.getCollection(COLLECTION_NAME).find(document).first();
        return convertAdministrators(first);
    }

    public List<FeedbackInformation> findAll() {
        Document document = new Document();
        FindIterable<Document> documents = mongoTemplate.getCollection(COLLECTION_NAME).find(document);
        List<FeedbackInformation> list = new ArrayList<>();
        for (Document doc : documents) {
            list.add(convertAdministrators(doc));
        }
        return list;
    }

    public List<FeedbackInformation> findNotProcessed() {
        Document document = new Document();
        document.put(IS_PROCESSED, StringUtil.convertNullToEmpty("false"));
        FindIterable<Document> documents = mongoTemplate.getCollection(COLLECTION_NAME).find(document);
        List<FeedbackInformation> list = new ArrayList<>();
        for (Document doc : documents) {
            list.add(convertAdministrators(doc));
        }
        return list;
    }

    public void updateProcessedStatus(FeedbackInformation feedbackInformation) {
        Document document = new Document();
        document.put(FEEDBACK_INFORMATION_ID,
                new ObjectId(StringUtil.convertNullToEmpty(feedbackInformation.getFeedbackInformationId())));
        FindIterable<Document> documents = mongoTemplate.getCollection(COLLECTION_NAME).find(document);
        Document first = documents.first();
        if (null != first) {
            first.put(IS_PROCESSED, "true");
            mongoTemplate.getCollection(COLLECTION_NAME).replaceOne(document, first);
        }
    }

    private FeedbackInformation convertAdministrators(Document first) {
        if (first != null) {
            FeedbackInformation feedbackInformation = new FeedbackInformation();
            feedbackInformation.setExpertId(first.getString(EXPERT_ID));
            feedbackInformation.setFeedbackContent(first.getString(FEEDBACK_CONTENT));
            feedbackInformation.setFeedbackInformationId((first.get(FEEDBACK_INFORMATION_ID)).toString());
            feedbackInformation.setSubscriberId(first.getString(SUBSCRIBER_ID));
            feedbackInformation.setIsProcessed(first.getString(IS_PROCESSED));
            feedbackInformation.setFeedbackTime(first.getString(FEEDBACK_TIME));
            return feedbackInformation;
        }
        return null;
    }

    public FeedbackInformation find(FeedbackInformation feedbackInformation) {
        Document document = new Document();
        convertDocument(feedbackInformation, document);
        Document first = mongoTemplate.getCollection(COLLECTION_NAME).find(document).first();
        return convertAdministrators(first);
    }

    private void convertDocument(FeedbackInformation feedbackInformation, Document document) {
        document.put(FEEDBACK_CONTENT, StringUtil.convertNullToEmpty(feedbackInformation.getFeedbackContent()));
        document.put(SUBSCRIBER_ID, StringUtil.convertNullToEmpty(feedbackInformation.getSubscriberId()));
        document.put(EXPERT_ID, StringUtil.convertNullToEmpty(feedbackInformation.getExpertId()));
    }
}
