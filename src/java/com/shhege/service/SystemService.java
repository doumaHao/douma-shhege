package com.shhege.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.shhege.madel.po.SysParamPo;

public interface SystemService {
	
	public String findByKeyTrue(String key);
	
	/**
	 * 根据参数类型和参数key，获取系统参数值
	 * @param type
	 * @param key
	 * @return
	 */
	public String findByKey(String key);
	
	/**
	 * 根据参数列表，批量查询系统参数值
	 * @param keys
	 * @return
	 */
	public List<SysParamPo> findAll(List<String> keys);
	
	
	/**
	 *  更新系统参数信息
	 * @param sysParamPo
	 * @return
	 */
	@Transactional
	public int update(String key, String value);
	
	/**
	 *  更新系统参数信息
	 *  必须字段：id，param_type，param_key
	 * @param sysParamPo
	 * @return
	 */
	@Transactional
	public int updateSome(List<SysParamPo> sysParamPoList);
	
	/**
	 * 获取管理员密码
	 * @param type
	 * @param key
	 * @return
	 */
	public String findAdminPasswrod(String type, String key);

}
