package com.win.store.controller.requsetEx;

/**
 * 上传文件的类型不支持异常
 */
public class FileTypeNotSupportException extends FileUploadException {
    private static final long serialVersionUID = -6796522897592253682L;

    public FileTypeNotSupportException() {
    }

    public FileTypeNotSupportException(String message) {
        super(message);
    }

    public FileTypeNotSupportException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileTypeNotSupportException(Throwable cause) {
        super(cause);
    }

    public FileTypeNotSupportException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
