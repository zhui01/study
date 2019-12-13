package com.heartsuit.showcase.domain.achievement;

/**
 * The type Dissertation achievement.论文
 */
public class DissertationAchievement extends Achievement {
    private String periodical; // 刊物
    private String conference; // 会议
    private String pagination; // 页码

    /**
     * Gets periodical.
     *
     * @return the periodical
     */
    public String getPeriodical() {
        return periodical;
    }

    /**
     * Sets periodical.
     *
     * @param periodical the periodical
     */
    public void setPeriodical(String periodical) {
        this.periodical = periodical;
    }

    /**
     * Gets pagination.
     *
     * @return the pagination
     */
    public String getPagination() {
        return pagination;
    }

    /**
     * Sets pagination.
     *
     * @param pagination the pagination
     */
    public void setPagination(String pagination) {
        this.pagination = pagination;
    }

    /**
     * Gets conference.
     *
     * @return the conference
     */
    public String getConference() {
        return conference;
    }

    /**
     * Sets conference.
     *
     * @param conference the conference
     */
    public void setConference(String conference) {
        this.conference = conference;
    }
}
