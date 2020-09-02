package com.win.store.controller;

import com.win.store.entity.Address;
import com.win.store.service.IAddressService;
import com.win.store.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 处理收货地址相关请求的控制器
 */
@RestController
@RequestMapping("/address")
public class AddressController extends BaseController{
    @Autowired
    private IAddressService addressService;

    @PostMapping("/create")
    public ResponseResult<Void> handleCreate(Address address, HttpSession session){
        // 根据session获取username
        String username = session.getAttribute("username").toString();
        // 根据session获取uid
        Integer uid = getUidFromSession(session);
        // 将uid封装到address中
        address.setUid(uid);
        // 调用业务层对象执行创建收货地址
        addressService.create(username,address);
        //返回
        return new ResponseResult<>(SUCCESS);
    }

    @RequestMapping("/list")
    public ResponseResult<List<Address>> handleList(HttpSession session){
        // 获取ID
        Integer uid = getUidFromSession(session);
        //查询用户数据
        List<Address> list = addressService.getListByUid(uid);
        //返回用户数据
        return new ResponseResult<>(SUCCESS,list);
    }

    @GetMapping("/default/{id}")
    public ResponseResult<Void> setDefault(HttpSession session , @PathVariable("id") Integer id){
        // 根据session获取修改人name
        String modifiedUser = session.getAttribute("username").toString();
        //获取用户id
        Integer uid = getUidFromSession(session);
        addressService.setDefault(uid,id,modifiedUser);
        return new ResponseResult<Void>(SUCCESS);
    }


    @GetMapping("/delete/{id}")
    public ResponseResult<Void> setDelete(HttpSession session,@PathVariable("id") Integer id){
        // 根据session获取修改人name
        String modifiedUser = session.getAttribute("username").toString();
        //获取用户id
        Integer uid = getUidFromSession(session);
        addressService.delete(uid,id,modifiedUser);
        return new ResponseResult<>(SUCCESS);
    }

    @RequestMapping("/add")
    public ResponseResult<Address> changeAdd(HttpSession session,Address address,Integer id){
        // 根据session获取修改人name
        String modifiedUser = session.getAttribute("username").toString();
        addressService.changeAdd(address,id,modifiedUser);
        System.out.println(address);
        return new ResponseResult<>(SUCCESS);
    }

    @RequestMapping("/addInfo")
    public ResponseResult<Address> addGetById(Integer id){
        Address address = addressService.addGetById(id);
        return new ResponseResult<Address>(SUCCESS,address);
    }
}
