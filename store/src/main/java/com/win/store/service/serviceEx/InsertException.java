package com.win.store.service.serviceEx;

/**
 * 插入数据异常
 */

public class InsertException extends ServiceException{

    private static final long serialVersionUID = 1553625286012676361L;

    public InsertException() {
    }

    public InsertException(String message) {
        super(message);
    }

    public InsertException(String message, Throwable cause) {
        super(message, cause);
    }

    public InsertException(Throwable cause) {
        super(cause);
    }

    public InsertException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
