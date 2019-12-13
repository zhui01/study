package com.heartsuit.showcase.domain.achievement;

/**
 * The type Patent achievement.专利
 */
public class PatentAchievement extends Achievement {
    private String filingDate; // 申请日
    private String publicationDate; // 公开日
    private String address; // 地址
    private String countryCode; // 国省代码
    private String applicationNumber; // 申请号
    private String publicationNumber; // 公开号

    /**
     * Gets filing date.
     *
     * @return the filing date
     */
    public String getFilingDate() {
        return filingDate;
    }

    /**
     * Sets filing date.
     *
     * @param filingDate the filing date
     */
    public void setFilingDate(String filingDate) {
        this.filingDate = filingDate;
    }

    /**
     * Gets publication date.
     *
     * @return the publication date
     */
    public String getPublicationDate() {
        return publicationDate;
    }

    /**
     * Sets publication date.
     *
     * @param publicationDate the publication date
     */
    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    /**
     * Gets address.
     *
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets address.
     *
     * @param address the address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets country code.
     *
     * @return the country code
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * Sets country code.
     *
     * @param countryCode the country code
     */
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    /**
     * Gets application number.
     *
     * @return the application number
     */
    public String getApplicationNumber() {
        return applicationNumber;
    }

    /**
     * Sets application number.
     *
     * @param applicationNumber the application number
     */
    public void setApplicationNumber(String applicationNumber) {
        this.applicationNumber = applicationNumber;
    }

    /**
     * Gets publication number.
     *
     * @return the publication number
     */
    public String getPublicationNumber() {
        return publicationNumber;
    }

    /**
     * Sets publication number.
     *
     * @param publicationNumber the publication number
     */
    public void setPublicationNumber(String publicationNumber) {
        this.publicationNumber = publicationNumber;
    }
}
