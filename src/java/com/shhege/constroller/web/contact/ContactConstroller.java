package com.shhege.constroller.web.contact;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.shhege.common.mv.WebCommonModeAndView;
import com.shhege.madel.ajax.Result;
import com.shhege.madel.po.ContactInfoPo;
import com.shhege.service.ContactService;

/**
 * 联系方式
 * @author Douma
 *
 */
@Controller
public class ContactConstroller {
	
	@Autowired
	private ContactService contactService;
	
	
	@RequestMapping(value = "/contact.do", method = RequestMethod.GET)
	public ModelAndView contact(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mv = new WebCommonModeAndView("/contact", request);
		
		return mv;
	}
	
	@ResponseBody
	@RequestMapping(value = "/submitContact.json", method = RequestMethod.POST)
	public Result submitContact(HttpServletRequest request,HttpServletResponse response){
		Result result = new Result();
		
		String contactName = request.getParameter("contactName");
		String contactTel = request.getParameter("contactTel");
		String contactContent = request.getParameter("contactContent");
		String contactTitle = request.getParameter("contactTitle");
		
		ContactInfoPo contactInfoPo = new ContactInfoPo();
		contactInfoPo.setContactName(contactName);
		contactInfoPo.setContactTel(contactTel);
		contactInfoPo.setContactContent(contactContent);
		contactInfoPo.setContactTitle(contactTitle);
		
		contactService.submitContact(contactInfoPo);
		
		result.setSuccess(true);
		return result;
	}

}
