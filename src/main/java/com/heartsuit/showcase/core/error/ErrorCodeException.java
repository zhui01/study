package com.heartsuit.showcase.core.error;

import com.heartsuit.showcase.core.i18n.MessageUtils;

import java.text.MessageFormat;

/**
 * @author Administrator
 */
public class ErrorCodeException extends RuntimeException {

    /**
     * Instantiates a new Error code exception.
     *
     * @param message the message
     */
    public ErrorCodeException(int message) {
        super(MessageUtils.get("messages.error."+message));
    }

    /**
     * Instantiates a new Error code exception.
     *
     * @param message the message
     * @param param   the param
     */
    public ErrorCodeException(int message, String ... param) {
        super(MessageFormat.format(MessageUtils.get("messages.error."+message), param));
    }
}
