package com.heartsuit.showcase.service.impl;

import com.heartsuit.showcase.dao.ExpertDao;
import com.heartsuit.showcase.domain.user.Expert;
import com.heartsuit.showcase.service.ExpertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExpertServiceImpl implements ExpertService {
    private ExpertDao expertDao;

    @Autowired
    public ExpertServiceImpl(ExpertDao expertDao) {
        this.expertDao = expertDao;
    }

    @Override
    public void update(Expert expert) {
        expertDao.update(expert);
    }

    @Override
    public void insert(Expert expert) {
        expertDao.insert(expert);
    }

    @Override
    public List<Expert> queryAll() {
        return expertDao.findAll();
    }

    @Override
    public Expert queryById(Expert expert) {
        return expertDao.findById(expert);
    }

    @Override
    public void addAchievementByEmail(Expert expert) {
        Expert byEmail = expertDao.findByEmail(expert);
        if (byEmail != null) {
            List<String> achievement = byEmail.getAchievement();
            achievement.addAll(expert.getAchievement());
        }
    }
}
