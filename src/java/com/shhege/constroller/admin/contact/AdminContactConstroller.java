package com.shhege.constroller.admin.contact;

import java.io.UnsupportedEncodingException;
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

import com.shhege.common.mv.AdminCommonModeAndView;
import com.shhege.core.util.date.DateUtil;
import com.shhege.core.util.request.ReqUtil;
import com.shhege.madel.ajax.Result;
import com.shhege.madel.filter.ContactInfoFilter;
import com.shhege.madel.page.Page;
import com.shhege.madel.po.ContactInfoPo;
import com.shhege.service.ContactService;

@Controller
@RequestMapping(value = "/admin")
public class AdminContactConstroller {
	
	final static public int COUNT = 10;
	
	@Autowired
	private	ContactService contactService;
	
	@RequestMapping(value = "/contact.do", method = RequestMethod.GET)
	public ModelAndView contact(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException{
		ModelAndView mv = new AdminCommonModeAndView("/contact", request);
		
		String contactName = ReqUtil.getParam(request, "contactName");
		String contactTel = ReqUtil.getParam(request, "contactTel");
		String contactTitle = ReqUtil.getParam(request, "contactTitle");
		String contactTimeForm = ReqUtil.getParam(request, "contactTimeForm");
		Date contactTimeFormDate = DateUtil.parse(contactTimeForm);
		String contactTimeTo = ReqUtil.getParam(request, "contactTimeTo");
		Date contactTimeToDate = DateUtil.parse(contactTimeTo);;
		String contactState = ReqUtil.getParam(request, "contactState");
		String pageIndex = ReqUtil.getParam(request, "pageIndex");
		int pageIndexInt = 1;
		if(StringUtils.isNotBlank(pageIndex)){
			pageIndexInt = Integer.valueOf(pageIndex);
		}
		
		ContactInfoFilter contactInfoFilter = new ContactInfoFilter();
		contactInfoFilter.setContactName(contactName);
		contactInfoFilter.setContactTel(contactTel);
		contactInfoFilter.setContactTitle(contactTitle);
		contactInfoFilter.setContactTimeForm(contactTimeFormDate);
		contactInfoFilter.setContactTimeTo(contactTimeToDate);
		contactInfoFilter.setContactState(contactState);
		
		
		Page<ContactInfoPo> page = contactService.queryContactByPage(contactInfoFilter, pageIndexInt, COUNT);
		
		mv.addObject("page", page);
		mv.addObject("contactInfoFilter", contactInfoFilter);
		mv.addObject("asideLi", "contact.do");
		
		return mv;
	}
	
	@RequestMapping(value = "/contact/detail.do", method = RequestMethod.GET)
	public ModelAndView contactDetail(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mv = new AdminCommonModeAndView("/contact-detail", request);
		String contactInfoId = ReqUtil.getParam(request, "contactInfoId");
		ContactInfoPo contactInfoPo = contactService.queryContactById(contactInfoId);
		
		mv.addObject("contactInfoPo", contactInfoPo);
		mv.addObject("asideLi", "contact.do");
		return mv;
	}
	
	@ResponseBody
	@RequestMapping(value = "/contact/update.do", method = RequestMethod.POST)
	public Result updateContact(HttpServletRequest request,HttpServletResponse response){
		Result result = new Result();
		String id = ReqUtil.getParam(request, "id");
		String contactState = ReqUtil.getParam(request, "contactState");
		String contactName = ReqUtil.getParam(request, "contactName");
		String contactTel = ReqUtil.getParam(request, "contactTel");
		String contactTitle = ReqUtil.getParam(request, "contactTitle");
		String contactContent = ReqUtil.getParam(request, "contactContent");
		
		if(StringUtils.isBlank(id) 
				|| StringUtils.isBlank(contactState)
				|| StringUtils.isBlank(contactName)
				|| StringUtils.isBlank(contactTel)){
			result.setSuccess(false);
			return result;
		}
		ContactInfoPo contactInfoPo = new ContactInfoPo();
		contactInfoPo.setId(id);
		contactInfoPo.setContactState(contactState);
		contactInfoPo.setContactName(contactName);
		contactInfoPo.setContactTel(contactTel);
		contactInfoPo.setContactTitle(contactTitle);
		contactInfoPo.setContactContent(contactContent);
		contactService.updateContact(contactInfoPo);
		
		result.setSuccess(true);
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/contact/delete.do", method = RequestMethod.POST)
	public Result deleteContact(HttpServletRequest request,HttpServletResponse response){
		Result result = new Result();
		String id = ReqUtil.getParam(request, "id");
		if(StringUtils.isBlank(id)){
			result.setSuccess(false);
			return result;
		}
		contactService.deleteContact(id);
		
		result.setSuccess(true);
		return result;
	}
	
}  