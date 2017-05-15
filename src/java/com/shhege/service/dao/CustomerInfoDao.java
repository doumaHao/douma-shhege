package com.shhege.service.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.shhege.madel.filter.CustomerInfoFilter;
import com.shhege.madel.po.CustomerInfoPo;
import com.sun.org.glassfish.gmbal.ParameterNames;

public interface CustomerInfoDao {
	
	/**
	 * 查询所有客户信息的总记录条数
	 * @param start
	 * @param count
	 * @return
	 */
	public int countOfCustomerInfo(@Param("customerInfoFilter") CustomerInfoFilter customerInfoFilter);
	
	/**
	 * 根据分页查询所有客户信息
	 * @param start
	 * @param count
	 * @return
	 */
	public List<CustomerInfoPo> findCustomerInfoByPage(@Param("customerInfoFilter") CustomerInfoFilter customerInfoFilter, @Param("start") int start, @Param("count") int count);
	
	/**
	 * 根据id查询客户信息
	 * @param id
	 * @return
	 */
	public CustomerInfoPo findCustomerInfoById(@Param("id") String id);
	
	/**
	 * 根据id更新客户信息
	 * @param customerInfoPo
	 * @return
	 */
	public int updateCustomerInfoById(@Param("customerInfoPo") CustomerInfoPo customerInfoPo);
	
	/**
	 * 插入客户信息
	 * @param customerInfoPo
	 * @return
	 */
	public int insertCustomerInfo(@Param("customerInfoPo") CustomerInfoPo customerInfoPo);
	
}
