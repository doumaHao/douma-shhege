package com.shhege.common.mv;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.shhege.common.constant.CacheKeyConstant;
import com.shhege.core.cache.CacheData;
import com.shhege.core.init.SystemInit;
import com.shhege.service.ImageService;

public class AdminCommonModeAndView extends ModelAndView{
	
	@Autowired
	private SystemInit systemInit;
	
	@Autowired
	private ImageService imageService;
	
	static final public String CONTEXT_PATH = "contextPath";
	
	static final public String COMMON_PATH = "commnPath";
	
	static final public String VIEW_FILE_PATH = "viewFile";
	
	static final public String CUR_USER_IMG = "curUserImg";
	
	public AdminCommonModeAndView(String viewFile, HttpServletRequest request){
		
		//后端在admin目录
		super("admin/"+viewFile);
		
		//添加contextpath路劲
		this.addObject(CONTEXT_PATH, request.getContextPath()+"/admin");
		
		//共通js文件路径
		this.addObject(COMMON_PATH, request.getContextPath()+"/viewFile/common");
		
		//添加viewFile路径
		this.addObject(VIEW_FILE_PATH, request.getContextPath()+"/viewFile/admin/");
		
		//添加缓存中的系统配置数据
		this.addObject(CacheKeyConstant.key_sysParam, CacheData.getInstance().getData(CacheKeyConstant.key_sysParam));
		
		//当前操作用户图片
		this.addObject(CUR_USER_IMG, (String)((Map)CacheData.getInstance().getData(CacheKeyConstant.key_sysParam)).get(CacheKeyConstant.key_sysParam_userImg));
	}

}
