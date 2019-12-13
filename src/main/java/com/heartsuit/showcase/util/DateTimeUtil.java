package com.heartsuit.showcase.util;

import com.heartsuit.showcase.core.error.ErrorCode;
import com.heartsuit.showcase.core.error.ErrorCodeException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateTimeUtil {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static final long ONE_WEEK = 60 * 60 * 24 * 7;

    private DateTimeUtil() {
    }

    public static String getCurrentTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(PATTERN);
        Date date = new Date();
        return simpleDateFormat.format(date);
    }

    public static Date convertTime(String str) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(PATTERN);
        return simpleDateFormat.parse(str);
    }

    public static boolean isInvalid(String str) {
        try {
            Date date = convertTime(str);
            Date now = new Date();
            return now.getTime() - date.getTime() < ONE_WEEK*1000;
        } catch (ParseException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ErrorCodeException(ErrorCode.ILLEGAL_PARAMETER_10001);
        }
    }
}
