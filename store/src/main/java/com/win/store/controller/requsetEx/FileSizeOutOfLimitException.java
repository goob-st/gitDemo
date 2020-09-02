package com.win.store.controller.requsetEx;

/**
 * 上传文件超出限制异常
 */
public class FileSizeOutOfLimitException extends FileUploadException {
    private static final long serialVersionUID = 1448590029946660653L;

    public FileSizeOutOfLimitException() {
    }

    public FileSizeOutOfLimitException(String message) {
        super(message);
    }

    public FileSizeOutOfLimitException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileSizeOutOfLimitException(Throwable cause) {
        super(cause);
    }

    public FileSizeOutOfLimitException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
