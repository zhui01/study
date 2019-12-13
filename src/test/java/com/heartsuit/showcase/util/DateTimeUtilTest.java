package com.heartsuit.showcase.util;

import com.heartsuit.showcase.core.error.ErrorCode;
import com.heartsuit.showcase.core.error.ErrorCodeException;
import org.bson.types.ObjectId;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.Date;

public class DateTimeUtilTest {

    @Test
    public void test() throws ParseException {
        String currentTime = DateTimeUtil.getCurrentTime();
        Date date = DateTimeUtil.convertTime("2019-12-12 12:12:12");
        boolean invalid = DateTimeUtil.isInvalid("2019-12-01 12:12:12");
        boolean invalid1 = DateTimeUtil.isInvalid("2019-11-22 12:12:12");
        boolean invalid2 = DateTimeUtil.isInvalid("2019-12-20 12:12:12");
    }

    @Test
    public void test1() {
        try {
            ObjectId string = new ObjectId("String");
        }
        catch (IllegalArgumentException e) {
            boolean nullOrEmpty = StringUtil.isNullOrEmpty("");
        }
    }

}
