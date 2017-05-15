package com.shhege.common.mv;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.shhege.common.constant.CacheKeyConstant;
import com.shhege.core.cache.CacheData;
import com.shhege.core.init.SystemInit;

public class WebCommonModeAndView extends ModelAndView{
	
	@Autowired
	private SystemInit systemInit;
	
	static final public String CONTEXT_PATH = "contextPath";
	
	static final public String COMMON_PATH = "commnPath";
	
	static final public String VIEW_FILE_PATH = "viewFile"; 
	
	public WebCommonModeAndView(String viewFile, HttpServletRequest request){
		
		//网站在web目录下
		super("web/"+viewFile);
		
		//添加contextpath路径
		this.addObject(CONTEXT_PATH, request.getContextPath());
		
		//共通js文件路径
		this.addObject(COMMON_PATH, request.getContextPath()+"/viewFile/common");
		
		//添加viewFile路径
		this.addObject(VIEW_FILE_PATH, request.getContextPath()+"/viewFile/web");
		
		//添加缓存中的系统配置数据
		this.addObject(CacheKeyConstant.key_sysParam, CacheData.getInstance().getData(CacheKeyConstant.key_sysParam));
	}

}
