package com.win.store.controller.requsetEx;

/**
 * 文件不能为空异常
 */
public class FileEmptyException extends FileUploadException {

    private static final long serialVersionUID = 2863250036409291355L;

    public FileEmptyException() {
    }

    public FileEmptyException(String message) {
        super(message);
    }

    public FileEmptyException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileEmptyException(Throwable cause) {
        super(cause);
    }

    public FileEmptyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
