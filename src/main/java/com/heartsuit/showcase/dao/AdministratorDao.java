package com.heartsuit.showcase.dao;

import com.heartsuit.showcase.domain.Administrators;
import com.heartsuit.showcase.util.StringUtil;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
public class AdministratorDao {
    private MongoTemplate mongoTemplate;
    private static final String COLLECTION_NAME = "Administrator";

    @Autowired
    public AdministratorDao(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public void insert(Administrators administrators) {
        Document document = new Document();
        document.put("email", StringUtil.convertNullToEmpty(administrators.getEmail()));
        document.put("passWord", StringUtil.convertNullToEmpty(administrators.getPassWord()));
        mongoTemplate.getCollection(COLLECTION_NAME).insertOne(document);
    }

    public Administrators find(Administrators administrators) {
        Document document = new Document();
        document.put("email", administrators.getEmail());
        document.put("passWord", administrators.getPassWord());
        Document first = mongoTemplate.getCollection(COLLECTION_NAME).find(document).first();
        return convertAdministrators(first);
    }

    private Administrators convertAdministrators(Document first) {
        if (first != null) {
            Administrators admin = new Administrators();
            admin.setAdministratorsId(first.get("_id").toString());
            admin.setEmail(first.getString("email"));
            admin.setPassWord(first.getString("passWord"));
            return admin;
        }
        return null;
    }
}
