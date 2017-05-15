package com.shhege.service.dao;

import org.apache.ibatis.annotations.Param;

import com.shhege.madel.po.TextInfoPo;

public interface TextInfoDao {
	
	/**
	 * 根据textid查询text内容
	 * @param type
	 * @param key
	 * @return
	 */
	public String findContent(@Param("textId") String textId);
	
	/**
	 * 根据textid更新text内容
	 * @param textId
	 * @param textContent
	 * @return
	 */
	public String updateContent(@Param("textId") String textId, @Param("textContent") String textContent);
	
	/**
	 * 插入一条新的text内容
	 * @param textId
	 * @param textContent
	 * @return
	 */
	public int insert(@Param("textInfoPo") TextInfoPo textInfoPo);
}
