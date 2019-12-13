package com.heartsuit.showcase.dao;

import com.mongodb.client.FindIterable;
import com.heartsuit.showcase.domain.Tenant;
import com.heartsuit.showcase.util.StringUtil;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Administrator on 2019/8/24 0024 19:44
 */
@Component
public class TenantDao
{
    private MongoTemplate mongoTemplate;

    @Autowired
    public TenantDao(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

        private static final String COLLECTION_NAME = "Tenant";

    /**
     * Insert.
     *
     * @param tenant the tenant
     */
    public void insert(Tenant tenant) {
        Document document = new Document();
        String UserId=UUID.randomUUID().toString().replace("-","");
        document.put("userId", UserId);
        document.put("userName", StringUtil.convertNullToEmpty(tenant.getUserName()));
        document.put("age", StringUtil.convertNullToEmpty(tenant.getAge()));
        document.put("email", StringUtil.convertNullToEmpty(tenant.getEmail()));
        document.put("password", StringUtil.convertNullToEmpty(tenant.getPassword()));
        document.put("sex", StringUtil.convertNullToEmpty(tenant.getSex()));
        document.put("code", UserId+UserId);
        document.put("isActivation", StringUtil.convertNullToEmpty(tenant.getIsActivation()));
        document.put("telephone", StringUtil.convertNullToEmpty(tenant.getTelephone()));
        mongoTemplate.getCollection(COLLECTION_NAME).insertOne(document);
    }

    /**
     * Find all list.
     *
     * @return the list
     */
    public List<Tenant> findAll() {
        FindIterable<Document> documents = mongoTemplate.getCollection(COLLECTION_NAME).find();
        ArrayList<Tenant> tenants = new ArrayList<>();
        for (Document document : documents) {
            Tenant tenant = new Tenant();
            tenant.setUserName(document.getString("userName"));
            tenant.setAge(document.getString("age"));
            tenant.setEmail(document.getString("email"));
            tenant.setPassword(document.getString("password"));
            tenant.setSex(document.getString("sex"));
            tenant.setIsActivation(document.getString("isActivation"));
            tenant.setTelephone(document.getString("telephone"));
            tenants.add(tenant);
        }
        return tenants;
    }

    /**
     * Check code string.
     *
     * @param tenant the tenant
     * @return the string
     */
    public String checkCode(Tenant tenant){
        Document document = new Document();
        document.put("code", tenant.getCode());
        long documents = mongoTemplate.getCollection(COLLECTION_NAME).countDocuments(document);
        return documents > 0 ? "1":"0";
    }

    /**
     * Update activation status.
     *
     * @param tenant the tenant
     */
    public void updateActivationStatus(Tenant tenant) {
        Document document = new Document();
        document.put("code", tenant.getCode());
        FindIterable<Document> documents = mongoTemplate.getCollection(COLLECTION_NAME).find(document);
        Document first = documents.first();
        if (null != first) {
            first.put("isActivation", "1");
            first.put("code","");
            mongoTemplate.getCollection(COLLECTION_NAME).replaceOne(document, first);
        }
    }

    /**
     * Find repeat email string.
     *
     * @param tenant the tenant
     * @return the string
     */
    public String findRepeatEmail(Tenant tenant) {
        Document document = new Document();
        document.put("email", tenant.getEmail());
        long documents = mongoTemplate.getCollection(COLLECTION_NAME).countDocuments(document);
        return documents > 0 ? "1":"0";
    }

    /**
     * Find tenant by email long.
     *
     * @param tenant the tenant
     * @return the long
     */
    public long findTenantByEmail(Tenant tenant) {
        Document document = new Document();
        document.put("email", tenant.getEmail());
        return mongoTemplate.getCollection(COLLECTION_NAME).countDocuments(document);
    }

    /**
     * Find tenant by email and pass word long.
     *
     * @param tenant the tenant
     * @return the long
     */
    public long findTenantByEmailAndPassWord(Tenant tenant) {
        Document document = new Document();
        document.put("email", tenant.getEmail());
        document.put("password", tenant.getPassword());
        return mongoTemplate.getCollection(COLLECTION_NAME).countDocuments(document);
    }
}
