package com.heartsuit.showcase.domain.logger;

/**
 * The type Logger.日志
 */
public class Logger {
    private String filePath; // 日志文件路径
    private LoggerType loggerType; // 日志类型
    private String startTime; // 开始时间
    private String filingTime; // 归档时间

    /**
     * Gets file path.
     *
     * @return the file path
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * Sets file path.
     *
     * @param filePath the file path
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Gets logger type.
     *
     * @return the logger type
     */
    public LoggerType getLoggerType() {
        return loggerType;
    }

    /**
     * Sets logger type.
     *
     * @param loggerType the logger type
     */
    public void setLoggerType(LoggerType loggerType) {
        this.loggerType = loggerType;
    }

    /**
     * Gets start time.
     *
     * @return the start time
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * Sets start time.
     *
     * @param startTime the start time
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * Gets filing time.
     *
     * @return the filing time
     */
    public String getFilingTime() {
        return filingTime;
    }

    /**
     * Sets filing time.
     *
     * @param filingTime the filing time
     */
    public void setFilingTime(String filingTime) {
        this.filingTime = filingTime;
    }
}
