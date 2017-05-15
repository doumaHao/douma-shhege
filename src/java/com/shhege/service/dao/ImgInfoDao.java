package com.shhege.service.dao;

import org.apache.ibatis.annotations.Param;

import com.shhege.madel.po.ImgInfoPo;



public interface ImgInfoDao {
	
	/**
	 * 根据imgid查询img
	 * @param imgId
	 * @return
	 */
	public ImgInfoPo findImg(@Param(value="imgId") String imgId);
	
	/**
	 * 根据imgid更新img
	 * @param imgId
	 * @param imgName
	 * @param imgUrl
	 * @return
	 */
	public int updateImg(@Param(value="imgId") String imgId, @Param(value="imgName") String imgName, @Param(value="imgUrl") String imgUrl);
	
	/**
	 * 插入一条新的img
	 * @param imgInfoPo
	 * @return
	 */
	public int insertImg(ImgInfoPo imgInfoPo);
}
