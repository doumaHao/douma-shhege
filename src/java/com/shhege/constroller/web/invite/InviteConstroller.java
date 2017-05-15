package com.shhege.constroller.web.invite;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.shhege.common.constant.CacheKeyConstant;
import com.shhege.common.mv.WebCommonModeAndView;
import com.shhege.service.SystemService;

/**
 * 人才招聘
 * @author Douma
 *
 */
@Controller
public class InviteConstroller {
	
	@Autowired SystemService systemService;
	

	@RequestMapping(value = "/invite", method = RequestMethod.GET)
	public ModelAndView invite(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mv = new WebCommonModeAndView("/zhaopin", request);
		String advertises = systemService.findByKey(CacheKeyConstant.key_sysParam_advertises);
		
		mv.addObject("advertises", advertises);
		return mv;
	}
}
