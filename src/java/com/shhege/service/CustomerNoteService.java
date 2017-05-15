package com.shhege.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.shhege.madel.po.CustomerNotePo;


public interface CustomerNoteService {
	
	/**
	 * 根据客户id查询改客户所有笔记跟进
	 * @param costomerId
	 * @return
	 */
	abstract public List<CustomerNotePo> getAllNoteByCostomerId(String costomerId);
	
	/**
	 * 插入客户笔记跟进
	 * @param customerNotePo
	 * @return
	 */
	@Transactional
	abstract public int insertNote(CustomerNotePo customerNotePo);

}
