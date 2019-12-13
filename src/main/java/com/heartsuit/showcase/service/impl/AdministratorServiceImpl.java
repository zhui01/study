package com.heartsuit.showcase.service.impl;

import com.heartsuit.showcase.core.error.ErrorCode;
import com.heartsuit.showcase.core.error.ErrorCodeException;
import com.heartsuit.showcase.dao.AdministratorDao;
import com.heartsuit.showcase.dao.ApplicationDao;
import com.heartsuit.showcase.dao.ExpertDao;
import com.heartsuit.showcase.dao.SubscriberDao;
import com.heartsuit.showcase.domain.Administrators;
import com.heartsuit.showcase.domain.application.Application;
import com.heartsuit.showcase.domain.user.Expert;
import com.heartsuit.showcase.domain.user.Subscriber;
import com.heartsuit.showcase.service.AdministratorService;
import com.heartsuit.showcase.service.IMailService;
import com.heartsuit.showcase.util.AesEncryptUtils;
import com.heartsuit.showcase.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdministratorServiceImpl implements AdministratorService {
    private IMailService iMailService;
    private AdministratorDao administratorDao;
    private ApplicationDao applicationDao;
    private SubscriberDao subscriberDao;
    private ExpertDao expertDao;

    @Autowired
    public AdministratorServiceImpl(IMailService iMailService,
                                    AdministratorDao administratorDao,
                                    ApplicationDao applicationDao,
                                    SubscriberDao subscriberDao,
                                    ExpertDao expertDao) {
        this.iMailService = iMailService;
        this.administratorDao = administratorDao;
        this.applicationDao = applicationDao;
        this.subscriberDao = subscriberDao;
        this.expertDao = expertDao;
    }

    @Override
    public void inviteExpert(Expert expert) throws Exception {
        Expert invitedExpert = expertDao.findByEmail(expert);
        // TODO: 2019/12/1 0001 需要改为服务器的ip地址
        iMailService.sendHtmlMail(expert.getEmail(),"邀请开通门户页",
                "<a href=\"http://114.116.9.214:8000/subscriber/invited?expertId="+ invitedExpert.getExpertId() +"\">专家资源平台官网受邀链接</a>");
    }

    @Override
    public void insert(Administrators administrators) throws Exception {
        administrators.setPassWord(AesEncryptUtils.encrypt(administrators.getPassWord()));
        administratorDao.insert(administrators);
    }

    @Override
    public String login(Administrators administrators) throws Exception {
        administrators.setPassWord(AesEncryptUtils.encrypt(administrators.getPassWord()));
        Administrators admin = administratorDao.find(administrators);
        if (admin == null) {
            throw new ErrorCodeException(ErrorCode.EMAIL_PASSWORD_IS_INCORRECT_10004);
        }
        else {
            return admin.getAdministratorsId();
        }
    }

    @Override
    public void approval(Application application) {
        Application applicationResult = applicationDao.findById(application);
        if (StringUtil.isNullOrEmpty(applicationResult.getApplicationId())) {
            throw new ErrorCodeException(ErrorCode.APPLICATION_FORM_DOES_NOT_EXIST_10016);
        }
        switch (applicationResult.getApplicationType()) {
            case BECOME_EXPERT:
                // 成为专家 用户成为专家，并拥有专家主页
                becomeExpert(application, applicationResult);
                updateApplicationStatus(application, "true");
                // TODO: 2019/12/2 0002 前台提供漂漂亮亮的页面
                iMailService.sendHtmlMail(applicationResult.getEmail(),"亲爱的专家，恭喜成为我们资源平台重要成员",
                        "感谢" + applicationResult.getName() + "的支持，您已成为我们资源平台中认证专家，并将拥有自己的专家主页");
                return;
            case MODIFY_INFORMATION:
                // 修改申请
                modifyInformation(application);
                updateApplicationStatus(application, "true");
                // TODO: 2019/12/2 0002 前台提供漂漂亮亮的页面
                iMailService.sendHtmlMail(applicationResult.getEmail(),"亲爱的用户，恭喜您的修改申请已经审批通过",
                        "感谢" + applicationResult.getName() + "的支持，您的修改已经审批通过");
                return;
            case EXPERT_CONFIRMATION:
                // 同意专家认领
                expertConfirmation(applicationResult);
                updateApplicationStatus(application, "true");
                // TODO: 2019/12/2 0002 前台提供漂漂亮亮的页面
                iMailService.sendHtmlMail(applicationResult.getEmail(),"亲爱的专家，恭喜您成功认领我们专家的门户网页",
                        "感谢" + applicationResult.getName() + "的支持，恭喜您成功认领我们专家的门户网页");
                return;
            default:
                throw new ErrorCodeException(ErrorCode.ILLEGAL_PARAMETER_10002, "applicationType");
        }
    }

    private void updateApplicationStatus(Application application, String str) {
        Application applicationResult = applicationDao.findById(application);
        applicationResult.setApplicationStatus(str);
        applicationDao.update(applicationResult);
    }

    private void becomeExpert(Application application, Application applicationResult) {
        String subscriberId = applicationResult.getSubscriberId();

        Expert expert = new Expert();
        expert.setSubscriberId(subscriberId);
        expert.setEmail(application.getEmail());
        expert.setName(application.getName());
        expert.setPersonalHomepage(application.getPersonalHomepage());
        expert.setPhotographUrl(application.getPhotographUrl());
        expert.setOrganization(application.getOrganization());
        expert.setUpdateDate(application.getCreateDate());
        expert.setResearchArea(application.getResearchArea());
        expertDao.insert(expert);
        Expert expertId = expertDao.findExpertId(expert);

        Subscriber subscriber = new Subscriber();
        subscriber.setSubscriberId(subscriberId);
        Subscriber subscriberResult = subscriberDao.findSubscriberBySubscriberId(subscriber);
        subscriberResult.setExpertId(expertId.getExpertId());
        subscriberDao.setExpertId(subscriberResult);
    }

    private void modifyInformation(Application application) {
        String expertId = application.getExpertId();
        Expert expert = new Expert();
        expert.setExpertId(expertId);
        Expert expertResult = expertDao.findById(expert);
        expertResult.setEmail(application.getEmail());
        expertResult.setName(application.getName());
        expertResult.setPersonalHomepage(application.getPersonalHomepage());
        expertResult.setPhotographUrl(application.getPhotographUrl());
        expertResult.setOrganization(application.getOrganization());
        expertResult.setUpdateDate(application.getCreateDate());
        expertResult.setResearchArea(application.getResearchArea());
        expertDao.update(expertResult);
    }

    private void expertConfirmation(Application applicationResult) {
        String subscriberId = applicationResult.getSubscriberId();
        Subscriber subscriber = new Subscriber();
        subscriber.setSubscriberId(subscriberId);
        Subscriber subscriberResult = subscriberDao.findSubscriberBySubscriberId(subscriber);
        subscriberResult.setExpertId(applicationResult.getExpertId());
        subscriberDao.setExpertId(subscriber);
    }

    @Override
    public void disApproval(Application application) {
        updateApplicationStatus(application, "false");
        // TODO: 2019/12/2 0002 前台提供漂漂亮亮的页面
        iMailService.sendHtmlMail(application.getEmail(),"亲爱的用户，对不起您的审批没有通过",
                "感谢" + application.getName() + "的支持，我们是专业的，您的申请，我们是严格对待的");
    }

}

