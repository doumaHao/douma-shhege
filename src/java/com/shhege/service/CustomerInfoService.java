package com.shhege.service;

import org.springframework.transaction.annotation.Transactional;

import com.shhege.madel.filter.CustomerInfoFilter;
import com.shhege.madel.page.Page;
import com.shhege.madel.po.CustomerInfoPo;

public interface CustomerInfoService {
	
	/**
	 * 根据客户id，获取客户详情
	 * @param id
	 * @return
	 */
	abstract public CustomerInfoPo detailOfCustomer(String id);
	
	/**
	 * 获取客户列表的分页信息
	 * pageIndex 为当前页的页数， count 为每页最多显示的条数
	 * @param pageIndex
	 * @param count
	 * @return
	 */
	abstract public Page<CustomerInfoPo> listOfCustomers(CustomerInfoFilter customerInfoFilter, int pageIndex, int count);
	
	/**
	 * 增加客户信息
	 * @param customerInfoPo
	 * @return
	 */
	@Transactional
	abstract public int addCustomer(CustomerInfoPo customerInfoPo);
	
	/**
	 * 修改客户信息
	 * 不可为空项目： id
	 * @param customerInfoPo
	 * @return
	 */
	@Transactional
	abstract public int update(CustomerInfoPo customerInfoPo);
	
	/**
	 * 删除客户
	 * @param id
	 * @return
	 */
	abstract public int delete(String id);
	
}
