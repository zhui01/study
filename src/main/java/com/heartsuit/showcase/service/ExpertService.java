package com.heartsuit.showcase.service;

import com.heartsuit.showcase.domain.user.Expert;

import java.util.List;

public interface ExpertService {
    void update(Expert expert);

    void insert(Expert expert);

    List<Expert> queryAll();

    Expert queryById(Expert expert);

    void addAchievementByEmail(Expert expert);
}
