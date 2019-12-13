package com.heartsuit.showcase.service;

import com.heartsuit.showcase.domain.application.Application;

import java.util.List;

public interface ApplicationService {
    void update(Application application);

    List<Application> queryAll();

    void insert(Application application);

    Application queryById(Application application);
}
