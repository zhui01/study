package com.heartsuit.showcase.core.i18n;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.ResourceBundle;

@Component
public final class MessageUtils {

    public MessageUtils() {
    }

    /**
     * 获取单个国际化翻译值
     */
    public static String get(String msgKey) {
        ResourceBundle resourceBundle =
                ResourceBundle.getBundle("i18n/messages", Locale.US);
        return resourceBundle.getString(msgKey);
    }
}
