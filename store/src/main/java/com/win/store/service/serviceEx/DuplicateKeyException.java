package com.win.store.service.serviceEx;

/**
 * 违反了Unique约束的异常
 */

public class DuplicateKeyException extends ServiceException{

    private static final long serialVersionUID = -5966852050663900908L;

    public DuplicateKeyException() {
    }

    public DuplicateKeyException(String message) {
        super(message);
    }

    public DuplicateKeyException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateKeyException(Throwable cause) {
        super(cause);
    }

    public DuplicateKeyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
