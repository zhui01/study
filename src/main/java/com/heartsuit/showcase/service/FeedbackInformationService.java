package com.heartsuit.showcase.service;

import com.heartsuit.showcase.core.error.ErrorCodeException;
import com.heartsuit.showcase.domain.FeedbackInformation;

import java.util.List;

/**
 * The interface Feedback information service.
 */
public interface FeedbackInformationService {
    /**
     * Update processed status.
     *
     * @param feedbackInformation the feedback information
     */
    void updateProcessedStatus(FeedbackInformation feedbackInformation);

    /**
     * Query all list.
     *
     * @return the list
     */
    List<FeedbackInformation> queryAll();

    /**
     * Query not processed list.
     *
     * @return the list
     */
    List<FeedbackInformation> queryNotProcessed();

    /**
     * Insert.
     *
     * @param feedbackInformation the feedback information
     */
    void insert(FeedbackInformation feedbackInformation) throws ErrorCodeException;

    /**
     * Query by id feedback information.
     *
     * @param feedbackInformation the feedback information
     * @return the feedback information
     */
    FeedbackInformation queryById(FeedbackInformation feedbackInformation);
}
