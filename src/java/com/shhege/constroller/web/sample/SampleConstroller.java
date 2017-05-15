package com.shhege.constroller.web.sample;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.shhege.common.mv.WebCommonModeAndView;

/**
 * 应用客户焊接样品
 * @author Douma
 *
 */
@Controller
public class SampleConstroller {
	
	@RequestMapping(value = "/sample", method = RequestMethod.GET)
	public ModelAndView sample(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mv = new WebCommonModeAndView("/gallery", request);
		
		return mv;
	}

}
