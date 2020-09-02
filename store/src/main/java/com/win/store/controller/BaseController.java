package com.win.store.controller;

import com.win.store.controller.requsetEx.*;
import com.win.store.service.serviceEx.*;
import com.win.store.util.ResponseResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * 当前项目中所有控制器类的基类
 */
public class BaseController {
    /**
     * 正确响应时的代号
     */
    public static final Integer SUCCESS = 200;

    @ExceptionHandler({ServiceException.class,
            RequestException.class})
    @ResponseBody
    public ResponseResult<Void> handleException(Exception e){
        Integer state = null;
        if (e instanceof DuplicateKeyException){
            // 400-违反了Unique约束的异常
            state = 400;
        }else if (e instanceof InsertException){
            //500-插入数据异常
            state = 500;
        }else if (e instanceof UserNotFoundException){
            //100- 登录数据错误（用户名）
            state = 100;
        }else if (e instanceof PasswordNotMatchException){
            //100- 登录数据错误（密码）
            state = 101;
        }else if (e instanceof UpdateException){
            //300- 修改密码错误
            state = 301;
        }else if (e instanceof FileEmptyException){
            //600-上传文件为空
            state = 600;
        }if (e instanceof DeleteException){
            //302 删除数据异常
            state = 302;
        }else if (e instanceof FileSizeOutOfLimitException) {
            // 601-上传的文件超出了限制的异常
            state = 601;
        } else if (e instanceof FileTypeNotSupportException) {
            // 602-上传的文件类型不支持的异常
            state = 602;
        } else if (e instanceof FileUploadException) {
            // 610-文件上传异常
            state = 610;
        } else if (e instanceof AccessDeniedException){
            //非法访问异常
            state = 101;
        }else if (e instanceof AddressNotFoundException){
            //设置默认收货地址失败!尝试访问的收货地址不存在
            state = 102;
        }
        return new ResponseResult<>(state,e);
    }

    /**
     * 从Session中获取Uid
     * @param session HttpSession 对象
     * @return 返回当前登录用户的ID值
     */
    protected Integer getUidFromSession(HttpSession session){
        return Integer.valueOf(session.getAttribute("uid").toString());
    }
}
