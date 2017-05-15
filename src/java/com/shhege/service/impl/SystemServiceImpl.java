package com.shhege.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shhege.common.constant.CacheKeyConstant;
import com.shhege.common.constant.SystemConstant;
import com.shhege.core.cache.CacheData;
import com.shhege.core.cache.annotation.Cache;
import com.shhege.madel.po.SysParamPo;
import com.shhege.service.ImageService;
import com.shhege.service.SystemService;
import com.shhege.service.TextInfoService;
import com.shhege.service.dao.SysParamDao;

@Service("systemService")
public class SystemServiceImpl implements SystemService {
	
	@Autowired
	private SysParamDao sysParamDao;
	
	@Autowired
	private ImageService imageService;
	
	@Autowired
	private TextInfoService textInfoService;

	@Override
	public String findByKey(String key) {
		
		String value = null;
		
		SysParamPo sysParamPo = sysParamDao.findValueByKey(key);
		if(sysParamPo == null || StringUtils.isBlank(sysParamPo.getParamValue())) {
			value = "";
		} else {
			if(SystemConstant.SYS_PARAM_TYPE_CHAR.equals(sysParamPo.getParamType())) {
				value = sysParamPo.getParamValue();
			} else if(SystemConstant.SYS_PARAM_TYPE_TEXT.equals(sysParamPo.getParamType())){
				value = textInfoService.getText(sysParamPo.getParamValue());
			} else if(SystemConstant.SYS_PARAM_TYPE_PIC.equals(sysParamPo.getParamType())){
				value = imageService.getImgUrlById(sysParamPo.getParamValue());
			} else {
				throw new IllegalArgumentException("查询系统参数异常,参数key:"+key);
			}
		}
		
		//重新查询的系统参数更新单例缓存
		if(CacheKeyConstant.key_sysParam_adminPassword.equals(key)) {
			//do nothing  安全起见，密码不可缓存
		} else {
			Map<String, String> systemParamMap = (Map<String, String>) CacheData.getInstance().getData(CacheKeyConstant.key_sysParam);
			systemParamMap.put(key, value);
		}
		
		return value==null?"":value;
	}

	@Cache(life = 1*3600)
	@Override
	public List<SysParamPo> findAll(List<String> keys) {
		
		List<SysParamPo> sysParamPoList = null;
		sysParamPoList = sysParamDao.findValuesByKeys(keys);
		if(sysParamPoList != null && sysParamPoList.size()>0) {
			for(SysParamPo sysParamPo: sysParamPoList) {
				if(SystemConstant.SYS_PARAM_TYPE_CHAR.equals(sysParamPo.getParamType())) {
					// do nothing
				} else if(SystemConstant.SYS_PARAM_TYPE_TEXT.equals(sysParamPo.getParamType())) {
					sysParamPo.setParamValue(textInfoService.getText(sysParamPo.getParamValue()));
				} else if(SystemConstant.SYS_PARAM_TYPE_PIC.equals(sysParamPo.getParamType())){
					sysParamPo.setParamValue(imageService.getImgUrlById(sysParamPo.getParamValue()));
				}
			}
		}
		
		//查询的系统参数更新单例缓存
		Map<String, String> systemParamMap = new HashMap<String, String>();
		for(SysParamPo sysParamPo: sysParamPoList){
			systemParamMap.put(sysParamPo.getParamKey(), sysParamPo.getParamValue());
		}
		CacheData.getInstance().putData(CacheKeyConstant.key_sysParam, systemParamMap);
		
		return sysParamPoList==null?new ArrayList():sysParamPoList;
	}

	@Override
	public int update(String key, String value) {
		
		if(StringUtils.isBlank(key)){
			return 0;
		}
		
		int i = sysParamDao.updateSysParamByKey(key, value);
		if(i == 1) {
			//更新成功的系统熟悉保存至缓存中
			if(CacheData.getInstance().getData(CacheKeyConstant.key_sysParam) != null) {
				Map<String, String> systemParamMap = (Map<String, String>) CacheData.getInstance().getData(CacheKeyConstant.key_sysParam);
				systemParamMap.put(key, value);
			} else {
				Map<String, String> systemParamMap = new HashMap<String, String>();
				systemParamMap.put(key, value);
				CacheData.getInstance().putData(CacheKeyConstant.key_sysParam, systemParamMap);
			}
		}
		return i;
	}

	@Override
	public String findAdminPasswrod(String type, String key) {
		SysParamPo sysParamPo = 
				sysParamDao.findValueByKey(CacheKeyConstant.key_sysParam_adminPassword);
		return sysParamPo.getParamValue();
	}

	@Override
	public int updateSome(List<SysParamPo> sysParamPoList) {
		if(sysParamPoList == null 
				|| sysParamPoList.size() == 0){
			return 0;
		}
		for(SysParamPo s: sysParamPoList){
			update(s.getParamKey(), s.getParamValue());
		}
		return sysParamPoList.size();
	}

	@Override
	public String findByKeyTrue(String key) {
		SysParamPo sysParamPo = sysParamDao.findValueByKey(key);
		if(sysParamPo != null) {
			return sysParamPo.getParamValue();
		} else {
			return "";
		}
	}
}
