package com.shhege.service;

import com.shhege.madel.filter.ContactInfoFilter;
import com.shhege.madel.page.Page;
import com.shhege.madel.po.ContactInfoPo;

public interface ContactService {
	
	/**
	 * 联系留言条数
	 * @return
	 */
	abstract public int countOfContact(ContactInfoFilter contactInfoFilter);
	
	/**
	 * 分页查询联系留言
	 * @param contactInfoPo
	 * @return
	 */
	abstract public Page<ContactInfoPo> queryContactByPage(ContactInfoFilter contactInfoFilter, int pageIndex, int count);
	
	/**
	 * 根据id查询联系留言详情
	 * @param id
	 * @return
	 */
	abstract public ContactInfoPo queryContactById(String id);
	
	/**
	 * 提交一个联系留言
	 * @param contactInfoPo
	 * @return
	 */
	abstract public int submitContact(ContactInfoPo contactInfoPo);
	
	/**
	 * 修改一个留言
	 * @param contactInfoPo
	 * @return
	 */
	abstract public int updateContact(ContactInfoPo contactInfoPo);
	
	/**
	 * 删除一个留言
	 * @param id
	 * @return
	 */
	abstract public int deleteContact(String id);
	
}
