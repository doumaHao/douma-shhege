package com.shhege.service;

import org.springframework.transaction.annotation.Transactional;

import com.shhege.madel.po.TextInfoPo;


public interface TextInfoService {
	
	/**
	 * 根据textId获取text内容
	 * @param textId
	 * @return
	 */
	abstract public String getText(String textId);
	
	/**
	 * 插入text内容
	 * @param textId
	 * @param textContent
	 * @return
	 */
	@Transactional
	abstract public String insertText(TextInfoPo textInfoPo);

}
