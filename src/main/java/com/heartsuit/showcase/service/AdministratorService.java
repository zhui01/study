package com.heartsuit.showcase.service;

import com.heartsuit.showcase.domain.Administrators;
import com.heartsuit.showcase.domain.application.Application;
import com.heartsuit.showcase.domain.user.Expert;

/**
 * The interface Administrator service.
 */
public interface AdministratorService {
    /**
     * 邀请专家
     * @param  expert expert
     */
    void inviteExpert(Expert expert) throws Exception;

    /**
     * Insert.
     *
     * @param administrators the administrators
     */
    void insert(Administrators administrators) throws Exception;

    /**
     * Login string.
     *
     * @param administrators the administrators
     * @return the string
     */
    String login(Administrators administrators) throws Exception;

    /**
     * Approval.
     *
     * @param application the application
     */
    void approval(Application application);

    /**
     * Approval.
     *
     * @param application the application
     */
    void disApproval(Application application);
}
