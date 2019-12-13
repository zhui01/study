package com.heartsuit.showcase.core.error;

public class ErrorCode {

    /**
     * 未知异常，请联系管理员
     */
    public static final int UNKNOWN_EXCEPTION = 10000;

    /**
     * 参数不合法
     */
    public static final int ILLEGAL_PARAMETER_10001 = 10001;

    /**
     * 参数不合法，{0}
     */
    public static final int ILLEGAL_PARAMETER_10002 = 10002;

    /**
     * 参数不合法，{0},{1}
     */
    public static final int ILLEGAL_PARAMETER_10003 = 10003;

    /**
     * 邮箱密码不正确
     */
    public static final int EMAIL_PASSWORD_IS_INCORRECT_10004 = 10004;

    /**
     * 邮箱不存在
     */
    public static final int EMAIL_DOES_NOT_EXIST_10005 = 10005;

    /**
     * 验证码不正确
     */
    public static final int CODE_DOES_NOT_EXIST_10006 = 10006;

    /**
     * 新邮箱已被注册
     */
    public static final int NEW_MAILBOX_HAS_BEEN_REGISTERED_10007 = 10007;

    /**
     * 激活链接已过期
     */
    public static final int ACTIVATION_LINK_HAS_EXPIRED_10008 = 10008;
    /**
     * 论文不可下载
     */
    public static final int PAPERS_ARE_NOT_DOWNLOADABLE_10009 = 10009;

    /**
     * 门户页信息不合法
     */
    public static final int PORTAL_PAGE_INFORMATION_IS_ILLEGAL_10010 = 10010;

    /**
     * 该用户ID不存在
     */
    public static final int THIS_SUBSCRIBER_ID_IS_NOT_EXIST_10011 = 10011;

    /**
     * 该缓存ID错误
     */
    public static final int THIS_COOKIE_ID_ERROR_10012 = 10012;

    /**
     * 该登录超时
     */
    public static final int LOGIN_TIMEOUT_10013 = 10013;

    /**
     * 该激活码失效
     */
    public static final int THIS_MODIFY_CODE_TIMEOUT_10014 = 10014;

    /**
     * 旧密码错误
     */
    public static final int OLD_PASSWORD_IS_WRONG_10015 = 10015;

    /**
     * 申请表不存在
     */
    public static final int APPLICATION_FORM_DOES_NOT_EXIST_10016 = 10016;
}
