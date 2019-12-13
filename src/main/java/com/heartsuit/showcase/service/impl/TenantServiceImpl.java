package com.heartsuit.showcase.service.impl;

import com.heartsuit.showcase.dao.TenantDao;
import com.heartsuit.showcase.domain.Tenant;
import com.heartsuit.showcase.service.IMailService;
import com.heartsuit.showcase.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Administrator on 2019/8/25 0025 00:03
 */
@Component
public class TenantServiceImpl implements TenantService{
    private TenantDao tenantDao;

    @Autowired
    public TenantServiceImpl(TenantDao tenantDao) {
        this.tenantDao = tenantDao;
    }

    @Override
    public void insert(Tenant tenant) {
        tenantDao.insert(tenant);
    }

    @Override
    public String checkCode(Tenant tenant){
        return tenantDao.checkCode(tenant);
    }

    @Override
    public List<Tenant> findAll() {
        return tenantDao.findAll();
    }

    @Override
    public void updateActivationStatus(Tenant tenant) {
        tenantDao.updateActivationStatus(tenant);
    }

    @Override
    public String checkEmail(Tenant tenant)
    {
        return tenantDao.findRepeatEmail(tenant);
    }

    @Override
    public String login(Tenant tenant)
    {
        long tenantByEmail = tenantDao.findTenantByEmail(tenant);
        long tenantByEmailAndPassWord = tenantDao.findTenantByEmailAndPassWord(tenant);
        return tenantByEmail > 0 ? (tenantByEmailAndPassWord > 0 ? "0" : "1"): "2";
    }
}
