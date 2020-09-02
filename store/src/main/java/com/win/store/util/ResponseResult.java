package com.win.store.util;

import java.io.Serializable;

/**
 * 服务器端向客户端响应结果
 * @param <T> 服务端向客户端响应的类型
 */
public class ResponseResult<T> implements Serializable {

    private static final long serialVersionUID = -5549556231186726669L;

    private Integer state;
    private String message;
    private T data;

    public ResponseResult() {
        super();
    }

    public ResponseResult(Integer state) {
        setState(state);
    }

    public Integer getState() {
        return state;
    }

    public ResponseResult(Integer state, String message) {
        this(state);
        setMessage(message);
    }

    public ResponseResult(Integer state,Exception e) {
        this(state,e.getMessage());
    }

    public ResponseResult(Integer state, T data) {
        this(state);
        setData(data);
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseResult{" +
                "state=" + state +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
