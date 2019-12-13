package com.heartsuit.showcase.service;

import com.heartsuit.showcase.domain.Tenant;

import java.util.List;

/**
 * Created by Administrator on 2019/8/25 0025 00:01
 */
public interface TenantService {
    /**
     * Insert.
     *
     * @param tenant the tenant
     */
    void insert(Tenant tenant);

    /**
     * Find all list.
     *
     * @return the list
     */
    List<Tenant> findAll();

    /**
     * Check code string.
     *
     * @param tenant the tenant
     * @return the string
     */
    String checkCode(Tenant tenant);

    /**
     * Update activation status.
     *
     * @param tenant the tenant
     */
    void updateActivationStatus(Tenant tenant);

    /**
     * Check email string.
     *
     * @param tenant the tenant
     * @return the string
     */
    String checkEmail(Tenant tenant);

    /**
     * Login string.
     *
     * @param tenant the tenant
     * @return the string
     */
    String login(Tenant tenant);
}
