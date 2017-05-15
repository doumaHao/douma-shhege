package com.shhege.service.impl;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shhege.common.constant.SystemConstant;
import com.shhege.core.util.id.IdGen;
import com.shhege.madel.filter.CustomerInfoFilter;
import com.shhege.madel.page.Page;
import com.shhege.madel.po.CustomerInfoPo;
import com.shhege.service.CustomerInfoService;
import com.shhege.service.dao.CustomerInfoDao;

@Service("customerInfoService")
public class CustomerInfoServiceImpl implements CustomerInfoService {
	
	@Autowired
	private CustomerInfoDao customerInfoDao;

	@Override
	public CustomerInfoPo detailOfCustomer(String id) {
		return customerInfoDao.findCustomerInfoById(id);
	}

	@Override
	public Page<CustomerInfoPo> listOfCustomers(CustomerInfoFilter customerInfoFilter, int pageIndex, int count) {
		Page<CustomerInfoPo> page = new Page<CustomerInfoPo>();
		//总条数
		page.setPageCount(customerInfoDao.countOfCustomerInfo(customerInfoFilter));
		//总页数
		page.setPageCount((page.getPageCount()-1)/count+1);
		//每页最多多少条记录
		page.setCount(count);
		//当前页数
		page.setPageIndex(pageIndex);
		//上一页
		if(page.getPageIndex()>1){
			page.setPagePre(page.getPageIndex()-1);
		} else {
			page.setPagePre(1);
		}
		//下一页
		if(page.getPageIndex()<page.getPageCount()){
			page.setPageNext(page.getPageIndex()+1);
		} else {
			page.setPageNext(page.getPageCount());
		}
		//当前记录集合
		page.setPageData(customerInfoDao.findCustomerInfoByPage(customerInfoFilter, (pageIndex-1)*count, count));
		
		return page;
	}

	@Override
	public int addCustomer(CustomerInfoPo customerInfoPo) {
		customerInfoPo.setId(IdGen.uuid());
		customerInfoPo.setRegisterDate(new Date());
		customerInfoPo.setDeleteFlg(SystemConstant.DELETE_FLG_NO);
		return customerInfoDao.insertCustomerInfo(customerInfoPo);
	}

	@Override
	public int update(CustomerInfoPo customerInfoPo) {
		if(customerInfoPo == null
				||  StringUtils.isBlank(customerInfoPo.getId())) {
			return 0;
		} else {
			return customerInfoDao.updateCustomerInfoById(customerInfoPo);
		}
	}

	@Override
	public int delete(String id) {
		CustomerInfoPo customerInfoPo = new CustomerInfoPo();
		customerInfoPo.setId(id);
		customerInfoPo.setDeleteFlg(SystemConstant.DELETE_FLG_YES);
		return customerInfoDao.updateCustomerInfoById(customerInfoPo);
	}

}
