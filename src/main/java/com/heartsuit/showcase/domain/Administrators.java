package com.heartsuit.showcase.domain;

/**
 * The type Administrators.管理员
 */
public class Administrators {
    private String administratorsId; // 管理员id
    private String email; // 邮箱
    private String passWord; // 密码md5的哈希值

    /**
     * Gets administrators id.
     *
     * @return the administrators id
     */
    public String getAdministratorsId() {
        return administratorsId;
    }

    /**
     * Sets administrators id.
     *
     * @param administratorsId the administrators id
     */
    public void setAdministratorsId(String administratorsId) {
        this.administratorsId = administratorsId;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets pass word.
     *
     * @return the pass word
     */
    public String getPassWord() {
        return passWord;
    }

    /**
     * Sets pass word.
     *
     * @param passWord the pass word
     */
    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
