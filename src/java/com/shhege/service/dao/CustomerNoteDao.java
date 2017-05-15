package com.shhege.service.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.shhege.madel.po.CustomerNotePo;

public interface CustomerNoteDao {
	
	/**
	 * 根据客户id查询该客户的笔记跟进
	 * @param costomerId
	 * @return
	 */
	public List<CustomerNotePo> findCustomerNotesByConstomerId(@Param("costomerId") String costomerId);
	
	/**
	 * 插入客户笔记跟进
	 * @param contactInfoPo
	 * @return
	 */
	public int insertContactInfo(@Param("customerNotePo") CustomerNotePo customerNotePo);

}
