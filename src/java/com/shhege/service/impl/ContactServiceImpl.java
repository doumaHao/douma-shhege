package com.shhege.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shhege.common.constant.SystemConstant;
import com.shhege.core.util.id.IdGen;
import com.shhege.madel.filter.ContactInfoFilter;
import com.shhege.madel.page.Page;
import com.shhege.madel.po.ContactInfoPo;
import com.shhege.service.ContactService;
import com.shhege.service.dao.ContactInfoDao;

@Service("contactService")
public class ContactServiceImpl implements ContactService {
	
	@Autowired
	private ContactInfoDao contactInfoDao;

	@Override
	public int countOfContact(ContactInfoFilter contactInfoFilter) {
		return contactInfoDao.countOfContactInfo(contactInfoFilter);
	}
	
	@Override
	public Page<ContactInfoPo> queryContactByPage(ContactInfoFilter contactInfoFilter, int pageIndex, int count) {
		Page<ContactInfoPo> page = new Page<ContactInfoPo>();
		//总条数
		page.setPageCount(contactInfoDao.countOfContactInfo(contactInfoFilter));
		//总页数
		page.setPageCount((page.getPageCount()-1)/count+1);
		//每页最多多少条记录
		page.setCount(count);
		//当前页数
		page.setPageIndex(pageIndex);
		//当前记录集合
		page.setPageData(contactInfoDao.findContactInfoByPage(contactInfoFilter, (pageIndex-1)*count, count));
		
		return page;
	}

	@Override
	public ContactInfoPo queryContactById(String id) {
		return contactInfoDao.findContactInfo(id);
	}

	@Override
	public int submitContact(ContactInfoPo contactInfoPo) {
		contactInfoPo.setId(IdGen.uuid());
		contactInfoPo.setDeleteFlg(SystemConstant.DELETE_FLG_NO);
		contactInfoPo.setContactTime(new Date());
		contactInfoPo.setContactState(SystemConstant.CONTACT_STATE_10);
		return contactInfoDao.insertContactInfo(contactInfoPo);
	}

	@Override
	public int updateContact(ContactInfoPo contactInfoPo) {
		return contactInfoDao.updateContactInfo(contactInfoPo);
	}

	@Override
	public int deleteContact(String id) {
		ContactInfoPo contactInfoPo = new ContactInfoPo();
		contactInfoPo.setId(id);
		contactInfoPo.setDeleteFlg(SystemConstant.DELETE_FLG_YES);
		return contactInfoDao.updateContactInfo(contactInfoPo);
	}

}
