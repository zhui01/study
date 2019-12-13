package com.heartsuit.showcase.service.impl;

import com.heartsuit.showcase.core.error.ErrorCode;
import com.heartsuit.showcase.core.error.ErrorCodeException;
import com.heartsuit.showcase.dao.ApplicationDao;
import com.heartsuit.showcase.dao.SubscriberDao;
import com.heartsuit.showcase.domain.application.Application;
import com.heartsuit.showcase.domain.application.ApplicationType;
import com.heartsuit.showcase.domain.user.Subscriber;
import com.heartsuit.showcase.service.ApplicationService;
import com.heartsuit.showcase.util.StringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ApplicationServiceImpl implements ApplicationService {
    private static final Logger LOGGER = LogManager.getLogger();
    private ApplicationDao applicationDao;
    private SubscriberDao subscriberDao;

    @Autowired
    public ApplicationServiceImpl(ApplicationDao applicationDao,
                                  SubscriberDao subscriberDao) {
        this.applicationDao = applicationDao;
        this.subscriberDao = subscriberDao;
    }

    @Override
    public void update(Application application) {
        if (ApplicationType.MODIFY_INFORMATION.equals(application.getApplicationType())) {
            applicationDao.update(application);
            LOGGER.info("application update successfully");
        }
        else {
            throw new ErrorCodeException(ErrorCode.ILLEGAL_PARAMETER_10002, "applicationType");
        }
    }

    @Override
    public List<Application> queryAll() {
        return applicationDao.findAll();
    }

    @Override
    public void insert(Application application) throws ErrorCodeException{
        String subscriberId = application.getSubscriberId();
        Subscriber subscriber = new Subscriber();
        subscriber.setSubscriberId(subscriberId);
        Subscriber subscriberBySubscriberId = subscriberDao.findSubscriberBySubscriberId(subscriber);
        if (StringUtil.isNullOrEmpty(subscriberBySubscriberId.getSubscriberId())) {
            throw new ErrorCodeException(ErrorCode.THIS_SUBSCRIBER_ID_IS_NOT_EXIST_10011);
        }
        applicationDao.insert(application);
    }

    @Override
    public Application queryById(Application application) {
        return applicationDao.findById(application);
    }
}
