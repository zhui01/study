package com.heartsuit.showcase.service.impl;

import com.heartsuit.showcase.service.IMailService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class IMailServiceImpl implements IMailService {
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Spring Boot 提供了一个发送邮件的简单抽象，使用的是下面这个接口，这里直接注入即可使用
     */
    private JavaMailSender mailSender;

    @Autowired
    public IMailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
     /**
     * 配置文件中我的qq邮箱
     */
    @Value("${spring.mail.username}")
    private String from;

    /**
     * html邮件
     * @param to 收件人
     * @param subject 主题
     * @param content 内容
     */
    @Override
    public void sendHtmlMail(String to, String subject, String content) {
        //获取MimeMessage对象
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper;
        try {
            messageHelper = new MimeMessageHelper(message, true);
            //邮件发送人
            messageHelper.setFrom(from);
            //邮件接收人
            messageHelper.setTo(to);
            //邮件主题
            messageHelper.setSubject(subject);
            //邮件内容，html格式
            messageHelper.setText(content, true);
            //发送
            mailSender.send(message);
            //日志信息
            LOGGER.info("邮件已经发送。");
        } catch (MessagingException e) {
            LOGGER.error("发送邮件时发生异常！", e);
        }
    }
}
