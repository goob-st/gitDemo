package com.win.store.service.serviceEx;

/**
 * 购物车数据不存在的异常
 * @Date 2020/7/27 13:39
 */
public class CartNoFoundException extends ServiceException {

    private static final long serialVersionUID = 1551728501581634253L;

    public CartNoFoundException() {
    }

    public CartNoFoundException(String message) {
        super(message);
    }

    public CartNoFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CartNoFoundException(Throwable cause) {
        super(cause);
    }

    public CartNoFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
