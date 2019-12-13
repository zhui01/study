package com.heartsuit.showcase.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayUtil {

    public static String convertNullToEmpty(List list) {
        if (isNullOrEmpty(list)) {
            return "[]";
        }
        return list.toString();
    }

    public static boolean isNullOrEmpty(List list) {
        return list == null || list.size() == 0;
    }

    public static List<String> convertToList(String s) {
        if (StringUtil.isNullOrEmpty(s)) {
            return new ArrayList<>();
        }
        String[] split = s.replace("[", "").replace("]", "").split(", ");
        return new ArrayList<>(Arrays.asList(split));
    }
}
