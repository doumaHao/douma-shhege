package com.shhege.core.init;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.shhege.common.constant.CacheKeyConstant;
import com.shhege.core.cache.CacheData;
import com.shhege.service.ImageService;
import com.shhege.service.SystemService;
import com.shhege.service.TextInfoService;

public class SystemInit implements ApplicationListener<ContextRefreshedEvent> {
	
	@Autowired
	private	SystemService systemService;
	
	@Autowired
	private ImageService imageService;
	
	@Autowired
	private TextInfoService textInfoService;

	/**
	 * 服务启动加载
	 */
	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		
		//加载系统参数
		loadSysParam();
	}
	
	/**
	 * 再加载项目
	 */
	public void reLoadEvent(){
		//加载系统参数
		loadSysParam();
	}
	
	
	/**
	 * 加载系统参数
	 */
	private void loadSysParam() {
		
		List<String> keys = new ArrayList<String>();
		keys.add(CacheKeyConstant.key_sysParam_adminUsername);
		keys.add(CacheKeyConstant.key_sysParam_companyTel);
		keys.add(CacheKeyConstant.key_sysParam_companyFox);
		keys.add(CacheKeyConstant.key_sysParam_companyEmail);
		keys.add(CacheKeyConstant.key_sysParam_companyAddress);
		keys.add(CacheKeyConstant.key_sysParam_recordNo);
		keys.add(CacheKeyConstant.key_sysParam_copyRight);
		keys.add(CacheKeyConstant.key_sysParam_saleTel);
		keys.add(CacheKeyConstant.key_sysParam_technicalTel);
		keys.add(CacheKeyConstant.key_sysParam_userImg);
		
		if(CacheData.getInstance().getData(CacheKeyConstant.key_sysParam) == null) {
			systemService.findAll(keys);
		}
	}
}
