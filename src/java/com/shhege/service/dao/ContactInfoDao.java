package com.shhege.service.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.shhege.madel.filter.ContactInfoFilter;
import com.shhege.madel.po.ContactInfoPo;

public interface ContactInfoDao {
	
	/**
	 * 查询所有联系留言的总记录条数
	 * @param start
	 * @param count
	 * @return
	 */
	public int countOfContactInfo(@Param("contactInfoFilter") ContactInfoFilter contactInfoFilter);
	
	/**
	 * 根据分页联系留言
	 * @param start
	 * @param count
	 * @return
	 */
	public List<ContactInfoPo> findContactInfoByPage(@Param("contactInfoFilter") ContactInfoFilter contactInfoFilter, @Param("start") int start, @Param("count") int count);
	
	/**
	 * 根据id查询联系留言
	 * @param id
	 * @return
	 */
	public ContactInfoPo findContactInfo(@Param("id") String id);
	
	/**
	 * 根据id更新联系留言
	 * @param contactInfoPo
	 * @return
	 */
	public int updateContactInfo(@Param("contactInfoPo") ContactInfoPo contactInfoPo);
	
	/**
	 * 插入联系留言
	 * @param contactInfoPo
	 * @return
	 */
	public int insertContactInfo(@Param("contactInfoPo") ContactInfoPo contactInfoPo);

}
