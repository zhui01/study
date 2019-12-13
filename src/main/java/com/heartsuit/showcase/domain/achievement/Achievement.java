package com.heartsuit.showcase.domain.achievement;

import com.heartsuit.showcase.domain.author.Author;

import java.util.Date;
import java.util.List;

/**
 * The type Achievement.成果
 * @author Administrator
 */
public class Achievement {
    private String achievementId; // 成果id
    private String title; // 标题
    private List<Author> authors; // 作者
    private AchievementType achievementType; // 成果类型
    private String summary; // 摘要
    private String mainBody; // 正文
    private String hyperlink; // 链接
    private String downloadAddress; // 下载地址
    private Date updateTime; // 更新时间

    /**
     * Gets achievement type.
     *
     * @return the achievement type
     */
    public AchievementType getAchievementType() {
        return achievementType;
    }

    /**
     * Sets achievement type.
     *
     * @param achievementType the achievement type
     */
    public void setAchievementType(AchievementType achievementType) {
        this.achievementType = achievementType;
    }

    /**
     * Gets achievement id.
     *
     * @return the achievement id
     */
    public String getAchievementId() {
        return achievementId;
    }

    /**
     * Sets achievement id.
     *
     * @param achievementId the achievement id
     */
    public void setAchievementId(String achievementId) {
        this.achievementId = achievementId;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets authors.
     *
     * @return the authors
     */
    public List<Author> getAuthors() {
        return authors;
    }

    /**
     * Sets authors.
     *
     * @param authors the authors
     */
    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    /**
     * Gets summary.
     *
     * @return the summary
     */
    public String getSummary() {
        return summary;
    }

    /**
     * Sets summary.
     *
     * @param summary the summary
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * Gets main body.
     *
     * @return the main body
     */
    public String getMainBody() {
        return mainBody;
    }

    /**
     * Sets main body.
     *
     * @param mainBody the main body
     */
    public void setMainBody(String mainBody) {
        this.mainBody = mainBody;
    }

    /**
     * Gets hyperlink.
     *
     * @return the hyperlink
     */
    public String getHyperlink() {
        return hyperlink;
    }

    /**
     * Sets hyperlink.
     *
     * @param hyperlink the hyperlink
     */
    public void setHyperlink(String hyperlink) {
        this.hyperlink = hyperlink;
    }

    /**
     * Gets download address.
     *
     * @return the download address
     */
    public String getDownloadAddress() {
        return downloadAddress;
    }

    /**
     * Sets download address.
     *
     * @param downloadAddress the download address
     */
    public void setDownloadAddress(String downloadAddress) {
        this.downloadAddress = downloadAddress;
    }

    /**
     * Gets update time.
     *
     * @return the update time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * Sets update time.
     *
     * @param updateTime the update time
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}
