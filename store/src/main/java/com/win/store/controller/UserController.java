package com.win.store.controller;

import com.win.store.controller.requsetEx.FileEmptyException;
import com.win.store.controller.requsetEx.FileSizeOutOfLimitException;
import com.win.store.controller.requsetEx.FileTypeNotSupportException;
import com.win.store.controller.requsetEx.FileUploadException;
import com.win.store.entity.User;
import com.win.store.service.IUserService;
import com.win.store.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController{

    /**
     * 上传文件夹的名称
     */
    private static final String UPLOAD_DIR_NAME = "upload";
    /**
     *  上传文件的最大大小
     */
    private static final long FILE_MAX_SIZE = 5 * 1024 *1024;
    /**
     * 允许上传的文件类型
     */
    private static final List<String> FILE_CONTENT_TYPES = new ArrayList<>();

    /**
     * 初始化允许上传的文件类型的集合
     */
    static {
        FILE_CONTENT_TYPES.add("image/jpeg");
        FILE_CONTENT_TYPES.add("image/png");
    }

    @Autowired
    private IUserService userService;

    @PostMapping("reg.do")
    public ResponseResult<Void> handleReg(User user){
        userService.reg(user);

        return new ResponseResult<Void>(SUCCESS);
    }

    @PostMapping("/login.do")
    public ResponseResult<User> handleLogin
            (@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session){
        User user = userService.login(username,password);
        //将相关信息放入Session
        session.setAttribute("uid",user.getId());
        session.setAttribute("username",user.getUsername());
        return new ResponseResult<User>(SUCCESS,user);
    }

    @PostMapping("/password.do")
    public ResponseResult<Void> changePassword(@RequestParam("old_password") String oldPassword,@RequestParam("new_password") String newPassword, HttpSession session){
        Integer uid = getUidFromSession(session);
        userService.changePassword(uid,oldPassword,newPassword);
        return new ResponseResult<Void>(SUCCESS);
    }

    @RequestMapping("/info.do")
    public ResponseResult<User> getInfo(HttpSession session){
        Integer uid = getUidFromSession(session);
        User user = userService.getById(uid);
        return new ResponseResult<User>(SUCCESS,user);
    }

    @PostMapping("/change_info.do")
    public ResponseResult<Void> changeInfo(User user,HttpSession session){
        Integer id = getUidFromSession(session);
        user.setId(id);
        userService.changeInfo(user);
        return new ResponseResult<>(SUCCESS);
    }

    @PostMapping("/upload.do")
    public ResponseResult<String> handleUpload(HttpSession session,@RequestParam("file") MultipartFile file){
        //检查是否存在上传文件 > file.isEmpty()
        if (file.isEmpty()){
            //抛出异常：文件不允许为空
            throw new FileEmptyException("上传失败！没有选择上传文件，或者上传文件为空！");
        }
        //检查文件大小 > file.getSize()
        if (file.getSize() > FILE_MAX_SIZE){
            // 抛出异常：文件大小超出限制
            throw new FileSizeOutOfLimitException("上传失败！文件大小超出限制！");
        }
        //检查文件类型 > file.getContentType()
        if (! FILE_CONTENT_TYPES.contains(file.getContentType())){
            //抛出异常：文件类型限制
            throw new FileTypeNotSupportException("上传失败！文件类型不符合");
        }

        // 确定上传文件夹的路径 > session.getServletContext.getRealPath(UPLOAD_DIR_NAME) > exists() > mkdirs()
        String parentPath = session.getServletContext().getRealPath(UPLOAD_DIR_NAME);
        File parent = new File(parentPath);
        if (!parent.exists()){
            parent.mkdirs();
        }
        // 确定文件名 > getOriginalFileName()
        String originalFileName = file.getOriginalFilename();
        int beginIndex = originalFileName.lastIndexOf(".");
        String suffix = originalFileName.substring(beginIndex);
        String fileName = System.currentTimeMillis() + "" + (new Random().nextInt(90000000)+10000000) + suffix;
        // 执行保存文件
        File dest = new File(parent,fileName);
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            throw new FileUploadException("上传失败！611！");
        }
        // 获取当前用户的id
        Integer uid = getUidFromSession(session);
        // 更新头像数据
        String avatar = "/" + UPLOAD_DIR_NAME + "/" + fileName;
        userService.changeAvatar(uid,avatar);
        // 返回
        ResponseResult<String> rr = new ResponseResult<>();
        rr.setState(SUCCESS);
        rr.setData(avatar);
        return rr;
    }


}
