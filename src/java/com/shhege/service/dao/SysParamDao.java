package com.shhege.service.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.shhege.madel.po.SysParamPo;


public interface SysParamDao{
	
	/**
	 * 根据类型和key查询value的最终值
	 * @param type
	 * @param key
	 * @return
	 */
	public SysParamPo findValueByKey(@Param(value="key") String key);
	
	/**
	 * 根据keys查询出系统参数表的po
	 * @param keys
	 * @return
	 */
	public List<SysParamPo> findValuesByKeys(@Param(value="keys") List keys);
	
	/**
	 * 根据type和key更新系统参数的value值
	 * @param type
	 * @param key
	 * @return
	 */
	public int updateSysParamByKey(@Param(value="key") String key, @Param(value="value") String value);
	
}
