package com.shhege.constroller.web.login;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.shhege.common.constant.CacheKeyConstant;
import com.shhege.common.mv.AdminCommonModeAndView;
import com.shhege.core.util.md5.MD5Util;
import com.shhege.core.util.request.ReqUtil;
import com.shhege.madel.ajax.Result;
import com.shhege.service.SystemService;

@Controller
public class LoginConstroller {
	
	@Autowired
	private SystemService systemService;
	
	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public ModelAndView login(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mv = new AdminCommonModeAndView("/login", request);
		mv.addObject("contextPath", request.getContextPath());
		return mv;
	}
	
	@ResponseBody
	@RequestMapping(value = "/login.json", method = RequestMethod.POST)
	public Result loginDo(HttpServletRequest request,HttpServletResponse response){
		Result result = new Result();
		String login = ReqUtil.getParam(request, "login");
		String password = ReqUtil.getParam(request, "password");
		if(StringUtils.equals(login, systemService.findByKey(CacheKeyConstant.key_sysParam_adminUsername))
				&& StringUtils.equals(MD5Util.md5(password), systemService.findByKey(CacheKeyConstant.key_sysParam_adminPassword))){
			LoginVo loginVo = new LoginVo();
			loginVo.setLogin(login);
			request.getSession().setAttribute(CacheKeyConstant.login_user, loginVo);
			result.setSuccess(true);
		} else {
			result.setSuccess(false);
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/logout.json", method = RequestMethod.POST)
	public Result logoutDo(HttpServletRequest request,HttpServletResponse response){
		Result result = new Result();
		request.getSession().removeAttribute("CacheKeyConstant.login_user");
		result.setSuccess(true);
		return result;
	}
	
	
	public class LoginVo{
		private String login;
		private Date loginTime;
		public String getLogin() {
			return login;
		}
		public void setLogin(String login) {
			this.login = login;
		}
		public Date getLoginTime() {
			return loginTime;
		}
		public void setLoginTime(Date loginTime) {
			this.loginTime = loginTime;
		}
	}
} 
