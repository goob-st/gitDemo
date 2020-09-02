package com.win.store.service.serviceEx;

/**
 * 业务异常，是当前项目中所有业务异常的基类
 */
public class ServiceException extends RuntimeException{

    private static final long serialVersionUID = -7346764288447609759L;

    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
