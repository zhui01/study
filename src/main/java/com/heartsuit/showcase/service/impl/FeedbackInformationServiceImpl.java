package com.heartsuit.showcase.service.impl;

import com.heartsuit.showcase.core.error.ErrorCodeException;
import com.heartsuit.showcase.dao.ExpertDao;
import com.heartsuit.showcase.dao.FeedbackInformationDao;
import com.heartsuit.showcase.dao.SubscriberDao;
import com.heartsuit.showcase.domain.FeedbackInformation;
import com.heartsuit.showcase.domain.user.Expert;
import com.heartsuit.showcase.domain.user.Subscriber;
import com.heartsuit.showcase.service.FeedbackInformationService;
import com.heartsuit.showcase.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FeedbackInformationServiceImpl implements FeedbackInformationService {
    private FeedbackInformationDao feedbackInformationDao;
    private SubscriberDao subscriberDao;
    private ExpertDao expertDao;

    @Autowired
    public FeedbackInformationServiceImpl(FeedbackInformationDao feedbackInformationDao,
                                          SubscriberDao subscriberDao,
                                          ExpertDao expertDao) {
        this.feedbackInformationDao = feedbackInformationDao;
        this.subscriberDao = subscriberDao;
        this.expertDao = expertDao;
    }

    @Override
    public void updateProcessedStatus(FeedbackInformation feedbackInformation) {
        feedbackInformationDao.updateProcessedStatus(feedbackInformation);
    }

    @Override
    public List<FeedbackInformation> queryAll() {
        return feedbackInformationDao.findAll();
    }

    @Override
    public List<FeedbackInformation> queryNotProcessed() {
        return feedbackInformationDao.findNotProcessed();
    }

    @Override
    public void insert(FeedbackInformation feedbackInformation) throws ErrorCodeException {
        if (!StringUtil.isNullOrEmpty(feedbackInformation.getExpertId())) {
            Expert expert = new Expert();
            expert.setExpertId(feedbackInformation.getExpertId());
            Expert result = expertDao.findById(expert);
            List<String> expertInformation = result.getFeedbackInformationId();
            expertInformation.add(feedbackInformation.getFeedbackInformationId());
            expertDao.update(expert);
        }
        feedbackInformationDao.insert(feedbackInformation);
        FeedbackInformation information = feedbackInformationDao.find(feedbackInformation);

        Subscriber subscriber = new Subscriber();
        subscriber.setSubscriberId(feedbackInformation.getSubscriberId());
        Subscriber subscriberBySubscriberId = subscriberDao.findSubscriberBySubscriberId(subscriber);
        List<String> subscriberFeedbackInformation = subscriberBySubscriberId.getFeedbackInformation();
        subscriberFeedbackInformation.add(information.getFeedbackInformationId());
        subscriberDao.setFeedbackInformation(subscriberBySubscriberId);
    }

    @Override
    public FeedbackInformation queryById(FeedbackInformation feedbackInformation) {
        return feedbackInformationDao.findById(feedbackInformation);
    }
}
