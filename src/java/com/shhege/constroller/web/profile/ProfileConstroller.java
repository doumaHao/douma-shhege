package com.shhege.constroller.web.profile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.shhege.common.mv.WebCommonModeAndView;

/**
 * 关于我们
 * @author Douma
 *
 */
@Controller
public class ProfileConstroller {
	
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public ModelAndView profile(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mv = new WebCommonModeAndView("/about", request);
		
		return mv;
	}

}
