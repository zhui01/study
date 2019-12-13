package com.heartsuit.showcase.dao;

import com.heartsuit.showcase.domain.user.Expert;
import com.heartsuit.showcase.util.ArrayUtil;
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

/**
 * The type Expert dao.
 */
@Component
public class ExpertDao {
    private MongoTemplate mongoTemplate;
    private static final String COLLECTION_NAME = "Expert";
    private static final String EXPERT_ID = "_id"; // 专家id
    private static final String SUBSCRIBER_ID = "subscriberId"; // 用户id
    private static final String EMAIL = "email"; // 邮箱
    private static final String NAME = "name"; // 姓名
    private static final String PHOTOGRAPH_URL = "photographUrl"; //照片url
    private static final String ORGANIZATION = "organization"; // 机构
    private static final String RESEARCH_AREA = "researchArea"; // 研究领域
    private static final String PERSONAL_HOMEPAGE = "personalHomepage"; // 个人主页
    private static final String UPDATE_DATE = "updateDate"; // 更新时间
    private static final String ACHIEVEMENT = "achievement"; // 成果id
    private static final String FEEDBACK_INFORMATION_ID = "feedbackInformationId"; // 反馈id

    /**
     * Instantiates a new Expert dao.
     *
     * @param mongoTemplate the mongo template
     */
    @Autowired
    public ExpertDao(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public void insert(Expert expert) {
        Document document = new Document();
        convertExpert(expert, document);
        document.put(UPDATE_DATE, DateTimeUtil.getCurrentTime());
        mongoTemplate.getCollection(COLLECTION_NAME).insertOne(document);
    }

    public Expert findById(Expert expert) {
        Document document = new Document();
        document.put(EXPERT_ID,
                new ObjectId(StringUtil.convertNullToEmpty(expert.getExpertId())));
        Document first = mongoTemplate.getCollection(COLLECTION_NAME).find(document).first();
        return convertDocument(first);
    }

    public List<Expert> findAll() {
        Document document = new Document();
        FindIterable<Document> documents = mongoTemplate.getCollection(COLLECTION_NAME).find(document);
        List<Expert> list = new ArrayList<>();
        for (Document doc : documents) {
            list.add(convertDocument(doc));
        }
        return list;
    }

    public void update(Expert expert) {
        Document document = new Document();
        document.put(EXPERT_ID,
                new ObjectId(StringUtil.convertNullToEmpty(expert.getExpertId())));
        FindIterable<Document> documents = mongoTemplate.getCollection(COLLECTION_NAME).find(document);
        Document first = documents.first();
        if (null != first) {
            convertExpert(expert, first);
            first.put(UPDATE_DATE, DateTimeUtil.getCurrentTime());
            mongoTemplate.getCollection(COLLECTION_NAME).replaceOne(document, first);
        }
    }

    private void convertExpert(Expert expert, Document first) {
        first.put(SUBSCRIBER_ID, StringUtil.convertNullToEmpty(expert.getSubscriberId()));
        first.put(EMAIL, StringUtil.convertNullToEmpty(expert.getEmail()));
        first.put(NAME, StringUtil.convertNullToEmpty(expert.getName()));
        first.put(PHOTOGRAPH_URL, StringUtil.convertNullToEmpty(expert.getPhotographUrl()));
        first.put(ORGANIZATION, StringUtil.convertNullToEmpty(expert.getOrganization()));
        first.put(RESEARCH_AREA, ArrayUtil.convertNullToEmpty(expert.getResearchArea()));
        first.put(PERSONAL_HOMEPAGE, StringUtil.convertNullToEmpty(expert.getPersonalHomepage()));
        first.put(ACHIEVEMENT, ArrayUtil.convertNullToEmpty(expert.getAchievement()));
        first.put(FEEDBACK_INFORMATION_ID, ArrayUtil.convertNullToEmpty(expert.getFeedbackInformationId()));
    }

    private Expert convertDocument(Document first) {
        if (first != null) {
            Expert expert = new Expert();
            expert.setExpertId(first.get(EXPERT_ID).toString());
            expert.setAchievement(ArrayUtil.convertToList(first.getString(ACHIEVEMENT)));
            expert.setEmail(first.getString(EMAIL));
            expert.setFeedbackInformationId(ArrayUtil.convertToList(first.getString(FEEDBACK_INFORMATION_ID)));
            expert.setName(first.getString(NAME));
            expert.setOrganization(first.getString(ORGANIZATION));
            expert.setPersonalHomepage(first.getString(PERSONAL_HOMEPAGE));
            expert.setPhotographUrl(first.getString(PHOTOGRAPH_URL));
            expert.setResearchArea(ArrayUtil.convertToList(first.getString(RESEARCH_AREA)));
            expert.setSubscriberId(first.getString(SUBSCRIBER_ID));
            expert.setUpdateDate(first.getString(UPDATE_DATE));
            return expert;
        }
        return null;
    }

    public Expert findExpertId(Expert expert) {
        Document document = new Document();
        convertExpert(expert, document);
        Document first = mongoTemplate.getCollection(COLLECTION_NAME).find(document).first();
        return convertDocument(first);
    }

    public Expert findByEmail(Expert expert) {
        Document document = new Document();
        document.put(EMAIL, StringUtil.convertNullToEmpty(expert.getEmail()));
        Document first = mongoTemplate.getCollection(COLLECTION_NAME).find(document).first();
        return convertDocument(first);
    }
}
