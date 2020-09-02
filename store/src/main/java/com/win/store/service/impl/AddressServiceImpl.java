package com.win.store.service.impl;

import com.win.store.entity.Address;
import com.win.store.entity.District;
import com.win.store.mapper.AddressMapper;
import com.win.store.service.IAddressService;
import com.win.store.service.IDistrictService;
import com.win.store.service.serviceEx.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class AddressServiceImpl implements IAddressService {
    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private IDistrictService districtService;

    @Override
    public Address create(String username,Address address) throws InsertException {
        // 通过address.getUid()得到用户id，并以此查询该用户的收货地址数量
        Integer count = getCountByUid(address.getUid());
        System.out.println(address.getName());
        if (count == 10){
            throw new InsertException("收货地址上限");
        }
        if (address.getName() == ""){
            throw new InsertException("收货人不能为空！");
        }
        if (address.getAddress() == ""){
            throw new InsertException("详细地址不能为空！");
        }
        if (address.getPhone() == ""){
            throw new InsertException("手机号不能为空！");
        }
        // 判断数量是否为0
        // 是：当前用户首次创建地址，则该地址默认：address.setIsDefault(1);
        // 否：当前用户非首次创建地址，则该地址非默认：address.setIsDefault(0);
        address.setIsDefault(count == 0? 1 : 0);
        //处理district
        String district = getDistrict(address.getProvince(),address.getCity(),address.getArea());
        address.setDistrict(district);
        // 封装日志
        Date now = new Date();
        address.setCreatedUser(username);
        address.setCreatedTime(now);
        // 执行创建新地址
        addnew(address);
        return address;
    }

    @Override
    public List<Address> getListByUid(Integer uid) {
        return findByUid(uid);
    }

    @Override
    public Address getById(Integer id) {
        return findById(id);
    }

    @Override
    @Transactional
    public void setDefault(Integer uid, Integer id,String modifiedUser) {
        //根据ID查询收货地址数据
        Address data = findById(id);
        //判断数据是否为null
        if (data == null){
            throw new AddressNotFoundException("设置默认收货地址失败!尝试访问的收货地址不存在!");
        }
        //判断查询到的数据中的uid与参数uid是否一致
        if (data.getUid() != uid){
            throw new AccessDeniedException("非法访问异常!");
        }
        //最后修改时间
        Date modifiedTime = new Date();
        //将该用户所有收货地址设置为非默认
        updateNonDefault(uid);
        //将指定ID值的收货数据改为默认
        updateDefault(id,modifiedUser,modifiedTime);
    }

    @Override
    @Transactional
    public void delete(Integer uid, Integer id,String modifiedUser) throws DeleteException {
        // 根据id查询收货地址数据：findById(id)
        Address data = findById(id);
        // 检查数据是否为null
        if (data == null){
            // 是：抛出AddressNotFoundException
            throw new AddressNotFoundException("删除收货地址失败！尝试删除的数据不存在！");
        }
        // 检查数据归属是否有误
        if (data.getUid() != uid){
            // 是：抛出AccessDeniedException
            throw new AccessDeniedException("删除收货地址失败！访问权限认证不通过");
        }
        // 执行删除
        deleteById(id);
        // 检查还有没有收货地址数据：getCountByUid(uid)
        if (getCountByUid(uid) > 0){
            // 是：判断刚才判断的是否是默认收货地址
            if (data.getIsDefault() == 1){
                // -- 是：获取最后修改的收货地址：findLastModified(uid)
               Integer lastModifiedId = findLastModified(uid).getId();
                // -- 将最后修改的收货地址设置为默认收货地址
               setDefault(uid,lastModifiedId,modifiedUser);
            }
        }


    }

    @Override
    public void changeAdd(Address address,Integer id,String modifiedUser) throws UserNotFoundException, UpdateException {
        //根据收货地址id查询收货地址
        Address data = findById(id);
        System.out.println(data);
        //判断地址是否存在
        if (data == null){
            throw new UserNotFoundException("无法查询此数据!");
        }
        //处理district
        String district = getDistrict(address.getProvince(),address.getCity(),address.getArea());
        address.setDistrict(district);
        //封装日志
        Date now = new Date();
        address.setModifiedUser(modifiedUser);
        address.setModifiedTime(now);
        //修改数据
        updateAddnew(address);
    }

    @Override
    public Address addGetById(Integer id) {
        Address data = findById(id);
        return data;
    }


    /**
     * 获取某用户的收货地址
     * @param uid 用户ID
     * @return 该用户的收货地址数据
     */
    private List<Address> findByUid(Integer uid){
        return addressMapper.findByUid(uid);
    }

    /**
     * 根据ID查询收货地址数据
     * @param id 收获地址的ID
     * @return 匹配的收货的数据,如果没有则返回null
     */
    private Address findById(Integer id){
        return addressMapper.findById(id);
    }

    /**
     * 用户添加收货地址
     * @param address 用户添加收货地址数据
     */
    private void addnew(Address address){
        Integer rows = addressMapper.addnew(address);
        if (rows != 1){
            throw new InsertException("数据错误!");
        }
    }

    /**
     * 查询用户收货地址的条数
     * @param uid 用户ID
     * @return  返回用户收货地址条数
     */
    private Integer getCountByUid(Integer uid){
        return addressMapper.getCountByUid(uid);
    }

    /**
     * 根据省、市、区的代号获取名称
     * @param province 省的代号
     * @param city 市的代号
     * @param area 区的代号
     * @return 省市区的名称
     */
    private String getDistrict(String province,String city,String area){
        String provinceName = null;
        String cityName = null;
        String areaName = null;
        //省
        District p = districtService.getByCode(province);
        //市
        District c = districtService.getByCode(city);
        //区
        District a = districtService.getByCode(area);
        if (p !=null){
            provinceName = p.getName();
        }
        if (c !=null){
            cityName = c.getName();
        }
        if (a !=null){
            areaName = a.getName();
        }
        return provinceName + "/" + cityName+ "/" + areaName;
    }

    /**
     * 根据uid 查询用户收货地址
     * @param uid 用户ID
     */
    private void updateNonDefault(Integer uid){
        Integer rows = addressMapper.updateNonDefault(uid);
        if (rows < 1){
            throw new UpdateException("修改默认收获地址时出现未知错误!");
        }
    }

    /**
     * 根据ID修改收货地址
     * @param id 收货地址id
     * @param modifiedUser 修改人
     * @param modifiedTime 修改时间
     */
    private void updateDefault(Integer id,String modifiedUser, Date modifiedTime){
        Integer rows = addressMapper.updateDefault(id,modifiedUser,modifiedTime);
        if (rows != 1){
            throw new UpdateException("修改默认收获地址时出现未知错误!");
        }
    }

    /**
     * 查询用户最后修改时间的收货地址数据数据
     * @param uid 收货地址数据
     * @return 匹配的数据
     */
    private Address findLastModified(Integer uid){
        return addressMapper.findLastModified(uid);
    }

    /**
     * 删除收货地址数据
     * @param id 收货地址id
     */
    private void deleteById(Integer id){
       Integer rows = addressMapper.deleteById(id);
       if (rows != 1){
           throw new DeleteException("删除收获地址时,出现错误!");
       }
    }

    private void updateAddnew(Address address){
        Integer rows = addressMapper.updateAddnew(address);
        if (rows != 1){
            throw new UpdateException("更新时出现错误");
        }
    }

}
