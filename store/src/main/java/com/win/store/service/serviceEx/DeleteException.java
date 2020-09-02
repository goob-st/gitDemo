package com.win.store.service.serviceEx;

/**
 * 数据删除异常
 */
public class DeleteException extends ServiceException {

    private static final long serialVersionUID = -6542754666157785289L;

    public DeleteException() {
    }

    public DeleteException(String message) {
        super(message);
    }

    public DeleteException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeleteException(Throwable cause) {
        super(cause);
    }

    public DeleteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
