package com.heartsuit.showcase.domain.author;

/**
 * @author Administrator
 */

public enum AuthorType {
    /**
     * First author author type.
     * 第一作者
     */
    FIRST_AUTHOR(1),
    /**
     * Parallel first authors author type.
     * 并列第一作者
     */
    PARALLEL_FIRST_AUTHORS(2),
    /**
     * Second author author type.
     * 第二作者
     */
    SECOND_AUTHOR(3),
    /**
     * Other authors author type.
     * 其他作者
     */
    OTHER_AUTHORS(4),
    /**
     * Correspondence author author type.
     * 通讯作者
     */
    CORRESPONDENCE_AUTHOR(5),
    /**
     * Inventor author type.
     * 发明人
     */
    INVENTOR(6),
    /**
     * Applicant author type.
     * 申请人
     */
    APPLICANT(7);

    private int value;
    AuthorType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
