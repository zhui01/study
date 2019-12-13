package com.heartsuit.showcase.roa;

import com.heartsuit.showcase.core.error.ErrorCode;
import com.heartsuit.showcase.core.error.ErrorCodeException;
import com.heartsuit.showcase.domain.SubscriberFollowedExpert;
import com.heartsuit.showcase.domain.user.Subscriber;
import com.heartsuit.showcase.service.CookieService;
import com.heartsuit.showcase.service.IMailService;
import com.heartsuit.showcase.service.SubscriberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * The type Subscriber controller.
 */
@RestController //@Component 类的注入
@RequestMapping("/subscriber")
@Api(tags = "用户信息接口")
public class SubscriberController {

    private static final Logger LOGGER = LogManager.getLogger();
    private SubscriberService subscriberService;
    private IMailService iMailService;
    private CookieService cookieService;

    /**
     * Instantiates a new Subscriber controller.
     *
     * @param subscriberService the subscriber service
     * @param iMailService      the mail service
     */
    @Autowired
    public SubscriberController(SubscriberService subscriberService, IMailService iMailService,CookieService cookieService) {
        this.subscriberService = subscriberService;
        this.iMailService = iMailService;
        this.cookieService = cookieService;
    }


    /**
     * 用户注册
     *
     * @param subscriber
     * @return True 或者 错误原因
     * @throws Exception the exception
     */
    @CrossOrigin
    @ApiOperation(value = "注册",notes = "参数为email,passWord,nickName")
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    public String insert(@RequestBody Subscriber subscriber) throws Exception {
        long code = subscriberService.insert(subscriber);
        if(code == 0){
            String ActivationCode=subscriberService.getSubscriberByEmail(subscriber).getActivationCode();
            // TODO: 2019/12/2 0002 修改ip地址 
            iMailService.sendHtmlMail(subscriber.getEmail(),"专家资源平台","<a href=\"http://localhost:8000/subscriber/checkCode?code="+ActivationCode+"\">激活请点击:"+ActivationCode+"</a>");
            return "true";
        }
        else
            throw new ErrorCodeException(ErrorCode.NEW_MAILBOX_HAS_BEEN_REGISTERED_10007);
    }

    /**
     * 密码登录
     * @param subscriber
     * @return cookieId 或者 错误原因
     */
    @CrossOrigin
    @ApiOperation(value = "密码登录",notes = "参数为email,passWord")
    @RequestMapping(value = "/login/password", method = RequestMethod.POST)
    @ResponseBody
    public String loginPassword(@RequestBody Subscriber subscriber) throws Exception {
        long returnCode=subscriberService.loginPassword(subscriber);
        if(returnCode == 0) {
            LOGGER.info("Login success");
            return cookieService.insert(subscriberService.getSubscriberByEmail(subscriber).getSubscriberId()).getCookieId();
        }
        else {
            LOGGER.error("Login failed");
            if(returnCode == 1){
                throw new ErrorCodeException(ErrorCode.EMAIL_PASSWORD_IS_INCORRECT_10004);
            }
            else if(returnCode == 2){
                throw new ErrorCodeException(ErrorCode.EMAIL_DOES_NOT_EXIST_10005);
            }
        }
        return "false";
    }

    /**
     * 发送登录验证码
     * @param subscriber
     * @return True 或者 错误原因
     */
    @CrossOrigin
    @ApiOperation(value = "发送登录验证码",notes = "参数为email")
    @RequestMapping(value = "/send/code", method = RequestMethod.POST)
    @ResponseBody
    public String sendCode(@RequestBody Subscriber subscriber) throws Exception {
        if(subscriberService.checkSubscriberByEmailAndIsActivation(subscriber) > 0) {
            subscriberService.setCode(subscriber);
            // TODO: 2019/12/2 0002 请前台提供漂漂亮亮的页面
            iMailService.sendHtmlMail(subscriber.getEmail(),"专家资源平台","您的验证码是:"+subscriberService.getSubscriberByEmail(subscriber).getCode());
            LOGGER.info("Send success");
            return "true";
        }
        else {
            LOGGER.error("Send failed");
            throw new ErrorCodeException(ErrorCode.EMAIL_DOES_NOT_EXIST_10005);
        }
    }

    /**
     * 发送忘记密码验证码
     * @param subscriber
     * @return True 或者 错误原因
     */
    @CrossOrigin
    @ApiOperation(value = "发送忘记密码验证码",notes = "参数为email")
    @RequestMapping(value = "/send/verificationcode", method = RequestMethod.POST)
    @ResponseBody
    public String sendVerificationCode(@RequestBody Subscriber subscriber) throws Exception {
        if(subscriberService.checkSubscriberByEmailAndIsActivation(subscriber) > 0) {
            subscriberService.setVerificationCode(subscriber);
            // TODO: 2019/12/2 0002 请前台提供漂漂亮亮的页面
            iMailService.sendHtmlMail(subscriber.getEmail(),"专家资源平台","您的验证码是:"+subscriberService.getSubscriberByEmail(subscriber).getVerificationCode());
            LOGGER.info("Send success");
            return "true";
        }
        else {
            LOGGER.error("Send failed");
            throw new ErrorCodeException(ErrorCode.EMAIL_DOES_NOT_EXIST_10005);
        }
    }

    /**
     * 验证码登录
     * @param subscriber
     * @return cookieId 或者 错误原因
     */
    @CrossOrigin
    @ApiOperation(value = "验证码登录",notes = "参数为email,code")
    @RequestMapping(value = "/login/code", method = RequestMethod.POST)
    @ResponseBody
    public String loginCode(@RequestBody Subscriber subscriber) throws Exception {
        long returnCode=subscriberService.loginCode(subscriber);
        if(subscriberService.checkSubscriberByEmailAndIsActivation(subscriber) > 0) {
            if(returnCode == 0) {
                LOGGER.info("Login success");
                return cookieService.insert(subscriberService.getSubscriberByEmail(subscriber).getSubscriberId()).getCookieId();
            }
            else {
                LOGGER.error("Login failed");
                throw new ErrorCodeException(ErrorCode.CODE_DOES_NOT_EXIST_10006);
            }
        }
        else {
            LOGGER.error("Login failed");
            throw new ErrorCodeException(ErrorCode.EMAIL_DOES_NOT_EXIST_10005);
        }
    }

    /**
     * 更新邮箱激活状态
     * @param subscriber 用户
     * @return 更新是否成功
     */
    @CrossOrigin
    @ApiOperation(value = "邮箱激活",notes = "参数为activationCode")
    @RequestMapping(value = "/checkcode",method = RequestMethod.POST)
    @ResponseBody
    public String updateActivationStatus(@RequestBody Subscriber subscriber) {
        subscriberService.updateActivationStatus(subscriber);
        LOGGER.info("Activation success");
        return "true";
    }

    /**
     * 忘记密码修改密码
     * @param subscriber
     * @return True 或者 错误原因
     */
    @CrossOrigin
    @ApiOperation(value = "忘记密码修改密码",notes = "参数为email,verificationCode, passWord")
    @RequestMapping(value = "/forget/password", method = RequestMethod.POST)
    @ResponseBody
    public String setPasswordByVerificationCode(@RequestBody Subscriber subscriber) throws Exception {
        if(subscriberService.checkSubscriberByEmailAndIsActivation(subscriber) > 0) {
            if(subscriberService.checkSubscriberByEmailAndVerificationCode(subscriber)>0) {
                subscriberService.setPasswordByEmail(subscriber);
                LOGGER.info("Reset success");
                return "true";
            }
            else {
                LOGGER.error("Reset failed");
                throw new ErrorCodeException(ErrorCode.CODE_DOES_NOT_EXIST_10006);
            }
        }
        else {
            LOGGER.error("Reset failed");
            throw new ErrorCodeException(ErrorCode.EMAIL_DOES_NOT_EXIST_10005);
        }
    }

    /**
     * 修改邮箱向旧邮箱发送邮件
     *
     * @param subscriber
     * @return True 或者 错误原因
     * @throws Exception the exception
     */
    @CrossOrigin
    @ApiOperation(value = "修改邮箱向旧邮箱发送邮件",notes = "参数为subscriberId")
    @RequestMapping(value = "/sendoldemail", method = RequestMethod.POST)
    @ResponseBody
    public String sendOldEmail(@RequestBody Subscriber subscriber) throws Exception {
        subscriberService.setModifyCode(subscriber);
        String modifyCode = subscriberService.getSubscriberBySubscriberId(subscriber).getModifyCode();
        // TODO: 2019/12/2 0002 修改ip地址 
        iMailService.sendHtmlMail(subscriberService.getSubscriberBySubscriberId(subscriber).getEmail(),"专家资源平台","<a href=\"http://localhost:8000/subscriber/checkCode?code="+modifyCode+"\">填写新邮箱:"+modifyCode+"</a>");
        LOGGER.info("Send success");
        return "true";
    }

    /**
     * 添加修改新邮箱
     *
     * @param subscriber
     * @return True 或者 错误原因
     * @throws Exception the exception
     */
    @CrossOrigin
    @ApiOperation(value = "填写新邮箱并发送链接",notes = "参数为modifyCode和newEmail")
    @RequestMapping(value = "/insert/newemail", method = RequestMethod.POST)
    @ResponseBody
    public String InsertNewEmail(@RequestBody Subscriber subscriber) throws Exception {
        if(subscriberService.checkSubscriberByModifyCode(subscriber)==1){
            Subscriber newSubscriber = new Subscriber();
            newSubscriber.setEmail(subscriber.getNewEmail());
            if(subscriberService.checkSubscriberByEmail(newSubscriber)>0){
                LOGGER.error("Insert failed");
                throw new ErrorCodeException(ErrorCode.NEW_MAILBOX_HAS_BEEN_REGISTERED_10007);
            }
            else {
                String modifyCode = subscriber.getModifyCode();
                subscriberService.setNewEmail(subscriber);
                // TODO: 2019/12/2 0002 修改ip地址
                iMailService.sendHtmlMail(subscriber.getNewEmail(),"专家资源平台","<a href=\"http://localhost:8000/subscriber/checkCode?code="+modifyCode+"\">激活新邮箱:"+modifyCode+"</a>");
                LOGGER.info("Send success");
                return "true";
            }
        }
        else
            throw new ErrorCodeException(ErrorCode.THIS_MODIFY_CODE_TIMEOUT_10014);
    }

    /**
     * 更新旧邮箱为新邮箱
     * @param subscriber 用户
     * @return 更新是否成功
     */
    @CrossOrigin
    @ApiOperation(value = "更新旧邮箱为新邮箱",notes = "参数为modifyCode")
    @RequestMapping(value = "/newemail/old",method = RequestMethod.POST)
    @ResponseBody
    public String updateNewEmail(@RequestBody Subscriber subscriber) {
        subscriberService.resetEmail(subscriber);
        LOGGER.info("Activation success");
        return "true";
    }

    /**
     * 查询旧密码是否正确
     * @param subscriber
     * @return 旧密码是否正确
     */
    @CrossOrigin
    @ApiOperation(value = "查询旧密码是否正确",notes = "参数为subscriberId,passWord(Old passWord)")
    @RequestMapping(value = "/check/password", method = RequestMethod.POST)
    @ResponseBody
    public String checkPasswordBySubscriberId(@RequestBody Subscriber subscriber) throws Exception {
        if(subscriberService.checkSubscriberBySubscriberIdAndOldPassword(subscriber)==1) {
            LOGGER.info("Check success");
            return "true";
        }
        else
            throw new ErrorCodeException(ErrorCode.OLD_PASSWORD_IS_WRONG_10015);
    }

    /**
     * 直接修改密码
     * @param subscriber
     * @return True 或者 错误原因
     */
    @CrossOrigin
    @ApiOperation(value = "直接修改密码",notes = "参数为subscriberId,passWord(New passWord)")
    @RequestMapping(value = "/reset/password", method = RequestMethod.POST)
    @ResponseBody
    public String resetPassword(@RequestBody Subscriber subscriber) throws Exception {
        subscriberService.setPasswordBySubscriberId(subscriber);
        LOGGER.info("Reset success");
        return "true";
    }


    /**
     * Insert followed expert string.
     *
     * @param subscriberFollowedExpert the subscriber followed expert
     * @return the string
     * @throws Exception the exception
     */
    @CrossOrigin
    @ApiOperation(value = "关注专家",notes = "参数为subscriberId,expertId")
    @RequestMapping(value = "/insert/followedexpert", method = RequestMethod.POST)
    @ResponseBody
    public String insertFollowedExpert(@RequestBody SubscriberFollowedExpert subscriberFollowedExpert) throws Exception {
        subscriberService.addFollowedExpert(subscriberFollowedExpert);
        LOGGER.info("Insert success");
        return "true";
    }

    /**
     * 用户查看个人信息
     * @param subscriber
     * @return 用户信息列表
     */
    @CrossOrigin
    @ApiOperation(value = "查看个人信息",notes = "参数为subscriberId")
    @RequestMapping(value = "/get/subscriber", method = RequestMethod.POST)
    @ResponseBody
    public Subscriber getSubscriberBySubscriberId(@RequestBody Subscriber subscriber) throws Exception{
        if(subscriberService.checkSubscriberBySubscriberId(subscriber)) {
            LOGGER.error("Get failed");
            throw new ErrorCodeException(ErrorCode.THIS_SUBSCRIBER_ID_IS_NOT_EXIST_10011);
        }
        else {
            LOGGER.info("Get success");
            return subscriberService.getSubscriberBySubscriberId(subscriber);
        }
    }

    /**
     * 修改个人信息
     * @param subscriber
     * @return True
     */
    @CrossOrigin
    @ApiOperation(value = "修改个人信息",notes = "参数为subscriberId,nickName")
    @RequestMapping(value = "/reset/subscriber", method = RequestMethod.POST)
    @ResponseBody
    public String resetSubscriberBySubscriberId(@RequestBody Subscriber subscriber) throws Exception {
        subscriberService.setSubscriberBySubscriberId(subscriber);
        LOGGER.info("Reset success");
        return "true";
    }

    @ApiOperation(value = "受邀专家激活接口",notes = "与前端无关的接口")
    @RequestMapping(value = "/invited", method = RequestMethod.GET)
    @ResponseBody
    public String invitedExpert(String expertId) throws Exception {
        subscriberService.invitedExpert(expertId);
        return "激活成功";
    }
}
