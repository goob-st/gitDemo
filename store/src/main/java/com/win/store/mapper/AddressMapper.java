package com.win.store.mapper;


import com.win.store.entity.Address;


import java.util.Date;
import java.util.List;

/**
 * 收货地址的持久层接口
 */
public interface AddressMapper {
	
	/**
	 * 增加新的收货地址数据
	 * @param address 收货地址数据
	 * @return 受影响的行数
	 */
	Integer addnew(Address address);

	/**
	 * 根据用户id获取该用户的收货地址数据的数量
	 * @param uid 用户id
	 * @return 该用户的收货地址数据的数量，如果没有数据，则返回0
	 */
	Integer getCountByUid(Integer uid);

	/**
	 * 获取某用户的收货地址
	 * @param uid 用户ID
	 * @return 该用户的收货地址数据
	 */
	List<Address> findByUid(Integer uid);

	/**
	 * 设置用户的收货地址全部设置为非默认
	 * @param uid 用户ID
	 * @return 受影响的行数
	 */
	Integer updateNonDefault(Integer uid);

	/**
	 * 	将指定id的收货地址设置为默认
	 * @param id 收货地址id
	 * @return 受影响的行数
	 */
	Integer updateDefault(Integer id,String modifiedUser, Date modifiedTime);

	/**
	 * 根据ID查询收货地址数据
	 * @param id 收获地址的ID
	 * @return 匹配的收货的数据,如果没有则返回null
	 */
	Address findById(Integer id);

	/**
	 * 删除收货地址数据
	 * @param id 收货地址id
	 * @return 返回受影响的行数
	 */
	Integer deleteById(Integer id);

	/**
	 * 查询用户最后修改时间的收货地址数据数据
	 * @param uid 收货地址数据
	 * @return 匹配的数据
	 */
	Address findLastModified(Integer uid);

	/**
	 * 修改用户收货地址
	 * @param address 收货地址数据
	 * @return 受影响的行数
	 */
	Integer updateAddnew(Address address);

}





