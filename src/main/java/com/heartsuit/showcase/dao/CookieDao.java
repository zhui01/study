package com.heartsuit.showcase.dao;

import com.heartsuit.showcase.domain.Cookie;
import com.heartsuit.showcase.util.DateTimeUtil;
import com.heartsuit.showcase.util.StringUtil;
import com.mongodb.client.FindIterable;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

/**
 * The type Cookie dao.
 * @author Administrator
 */
@Component
public class CookieDao
{
    private MongoTemplate mongoTemplate;

    /**
     * Instantiates a new Cookie dao.
     *
     * @param mongoTemplate the mongo template
     */
    @Autowired
    public CookieDao(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

        private static final String COLLECTION_NAME = "Cookie";

    /**
     * Insert.
     *
     * @param cookie the cookie
     */
    public Cookie insert(Cookie cookie) {
        Document document = new Document();
        document.put("subscriberId", StringUtil.convertNullToEmpty(cookie.getSubscriberId()));
        document.put("overdueTime", DateTimeUtil.getCurrentTime());
        mongoTemplate.getCollection(COLLECTION_NAME).insertOne(document);
        Cookie findCookie = new Cookie();
        Document findDocument = mongoTemplate.getCollection(COLLECTION_NAME).find(document).first();
        convertCookie(findDocument,findCookie);
        return findCookie;
    }

    /**
     * Update overdue time.
     *
     * @param cookie the cookie
     */
    public void updateOverdueTime(Cookie cookie) {
        Document document = new Document();
        document.put("_id", new ObjectId(cookie.getCookieId()));
        FindIterable<Document> documents = mongoTemplate.getCollection(COLLECTION_NAME).find(document);
        Document first = documents.first();
        if (null != first) {
            first.put("overdueTime", DateTimeUtil.getCurrentTime());
            mongoTemplate.getCollection(COLLECTION_NAME).replaceOne(document, first);
        }
    }

    /**
     * Get cookie by cookie id cookie.
     *
     * @param cookie the cookie
     * @return the cookie
     */
    public Cookie getCookieByCookieId(Cookie cookie){
        Document document = new Document();
        document.put("_id", new ObjectId(cookie.getCookieId()));
        Document findDocument = mongoTemplate.getCollection(COLLECTION_NAME).find(document).first();
        Cookie findCookie = new Cookie();
        convertCookie(findDocument,findCookie);
        return findCookie;
    }

    /**
     * Find cookie by cookie id long.
     *
     * @param cookie the cookie
     * @return the long
     */
    public long findCookieByCookieId(Cookie cookie){
        Document document = new Document();
        document.put("_id", new ObjectId(cookie.getCookieId()));
        return mongoTemplate.getCollection(COLLECTION_NAME).countDocuments(document);
    }

    /**
     * Compare overdue time boolean.
     *
     * @param cookie the cookie
     * @return the boolean
     */
    public boolean compareOverdueTime(Cookie cookie) {
        Document document = new Document();
        document.put("_id", new ObjectId(cookie.getCookieId()));
        Document findDocument = mongoTemplate.getCollection(COLLECTION_NAME).find(document).first();
        convertCookie(findDocument,cookie);
        boolean flag = DateTimeUtil.isInvalid(cookie.getOverdueTime());
        if(flag){
            findDocument.put("overdueTime", DateTimeUtil.getCurrentTime());
            mongoTemplate.getCollection(COLLECTION_NAME).replaceOne(document, findDocument);
        }
        return flag;
    }

    /**
     * Convert cookie.
     *
     * @param document the document
     * @param cookie   the cookie
     */
    public void convertCookie(Document document, Cookie cookie) {
        if (document == null) {
            return;
        }
        cookie.setCookieId(document.get("_id").toString());
        cookie.setSubscriberId(document.getString("subscriberId"));
        cookie.setOverdueTime(document.getString("overdueTime"));
    }
}
