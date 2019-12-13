package com.heartsuit.showcase.dao;

import com.heartsuit.showcase.core.error.ErrorCode;
import com.heartsuit.showcase.core.error.ErrorCodeException;
import com.heartsuit.showcase.domain.application.Application;
import com.heartsuit.showcase.domain.application.ApplicationType;
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

@Component
public class ApplicationDao {
    private MongoTemplate mongoTemplate;
    private static final String COLLECTION_NAME = "Application";
    private static final String APPLICATION_ID = "_id"; // 申请id
    private static final String SUBSCRIBER_ID = "subscriberId"; // 用户id
    private static final String APPLICATION_TYPE = "applicationType"; // 申请类型
    private static final String EMAIL = "email"; // 邮箱
    private static final String NAME =  "name"; // 姓名
    private static final String PHOTOGRAPH_URL = "photographUrl"; // 照片url
    private static final String ORGANIZATION = "organization"; // 机构
    private static final String RESEARCH_AREA = "researchArea"; // 研究领域
    private static final String PERSONAL_HOMEPAGE = "personal_Homepage"; // 个人主页
    private static final String CREATE_DATE = "createDate"; // 提交时间
    private static final String REMARKS = "remarks"; // 备注
    private static final String EXPERT_ID = "expertId"; // 专家id
    private static final String APPLICATION_STATUS = "applicationStatus"; // 申请状态

    /**
     * Instantiates a new Expert dao.
     *
     * @param mongoTemplate the mongo template
     */
    @Autowired
    public ApplicationDao(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public void insert(Application application) {
        Document document = new Document();
        convertApplication(application, document);
        mongoTemplate.getCollection(COLLECTION_NAME).insertOne(document);
    }

    public Application findById(Application application) {
        Document document = new Document();
        try {
            document.put(APPLICATION_ID,
                    new ObjectId(StringUtil.convertNullToEmpty(application.getApplicationId())));
        }
        catch (IllegalArgumentException e) {
            throw new ErrorCodeException(ErrorCode.APPLICATION_FORM_DOES_NOT_EXIST_10016);
        }
        Document first = mongoTemplate.getCollection(COLLECTION_NAME).find(document).first();
        return convertDocument(first);
    }

    public List<Application> findAll() {
        Document document = new Document();
        FindIterable<Document> documents = mongoTemplate.getCollection(COLLECTION_NAME).find(document);
        List<Application> list = new ArrayList<>();
        for (Document doc : documents) {
            list.add(convertDocument(doc));
        }
        return list;
    }

    public void update(Application application) {
        Document document = new Document();
        document.put(APPLICATION_ID,
                new ObjectId(StringUtil.convertNullToEmpty(application.getApplicationId())));
        FindIterable<Document> documents = mongoTemplate.getCollection(COLLECTION_NAME).find(document);
        Document first = documents.first();
        if (null != first) {
            convertApplication(application, first);
            mongoTemplate.getCollection(COLLECTION_NAME).replaceOne(document, first);
        }
    }

    private void convertApplication(Application application, Document first) {
        first.put(SUBSCRIBER_ID, StringUtil.convertNullToEmpty(application.getSubscriberId()));
        first.put(EMAIL, StringUtil.convertNullToEmpty(application.getEmail()));
        first.put(NAME, StringUtil.convertNullToEmpty(application.getName()));
        first.put(PHOTOGRAPH_URL, StringUtil.convertNullToEmpty(application.getPhotographUrl()));
        first.put(ORGANIZATION, StringUtil.convertNullToEmpty(application.getOrganization()));
        first.put(RESEARCH_AREA, ArrayUtil.convertNullToEmpty(application.getResearchArea()));
        first.put(PERSONAL_HOMEPAGE, StringUtil.convertNullToEmpty(application.getPersonalHomepage()));
        first.put(APPLICATION_TYPE, StringUtil.convertNullToEmpty(application.getApplicationType().toString()));
        first.put(CREATE_DATE, DateTimeUtil.getCurrentTime());
        first.put(REMARKS, StringUtil.convertNullToEmpty(application.getRemarks()));
        first.put(EXPERT_ID, StringUtil.convertNullToEmpty(application.getExpertId()));
        first.put(APPLICATION_STATUS, StringUtil.convertNullToEmpty(application.getApplicationStatus()));
    }

    private Application convertDocument(Document first) {
        if (first != null) {
            Application application = new Application();
            application.setApplicationId(first.get(APPLICATION_ID).toString());
            application.setApplicationType(ApplicationType.valueOf(first.getString(APPLICATION_TYPE)));
            application.setEmail(first.getString(EMAIL));
            application.setRemarks(first.getString(REMARKS));
            application.setName(first.getString(NAME));
            application.setOrganization(first.getString(ORGANIZATION));
            application.setPersonalHomepage(first.getString(PERSONAL_HOMEPAGE));
            application.setPhotographUrl(first.getString(PHOTOGRAPH_URL));
            application.setResearchArea(ArrayUtil.convertToList(first.getString(RESEARCH_AREA)));
            application.setSubscriberId(first.getString(SUBSCRIBER_ID));
            application.setCreateDate(first.getString(CREATE_DATE));
            application.setExpertId(first.getString(EXPERT_ID));
            application.setApplicationStatus(first.getString(APPLICATION_STATUS));
            return application;
        }
        return null;
    }
}
