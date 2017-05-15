package com.shhege.constroller.admin.customer;

import java.util.Date;
import java.util.List;

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
import com.shhege.core.util.request.ReqUtil;
import com.shhege.madel.ajax.Result;
import com.shhege.madel.filter.CustomerInfoFilter;
import com.shhege.madel.page.Page;
import com.shhege.madel.po.CustomerInfoPo;
import com.shhege.madel.po.CustomerNotePo;
import com.shhege.service.CustomerInfoService;
import com.shhege.service.CustomerNoteService;
import com.shhege.service.ImageService;
@Controller
@RequestMapping(value = "/admin")
public class AdminCustomerConstroller {
	
	@Autowired
	private	CustomerInfoService customerInfoService;
	
	@Autowired
	private CustomerNoteService customerNoteService;
	
	@Autowired
	private ImageService imageService;
	
	final static public int COUNT = 15;
	
	final static public String CUSTOMER_DETAIL_OPER_DETAIL = "detail";
	final static public String CUSTOMER_DETAIL_OPER_ADD = "add";
	final static public String CUSTOMER_DETAIL_OPER_EDIT = "edit";
	
	@RequestMapping(value = "/customer.do", method = RequestMethod.GET)
	public ModelAndView customer(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mv = new AdminCommonModeAndView("/customer", request);
		String customerName = ReqUtil.getParam(request, "customerName");
		String customerTitle = ReqUtil.getParam(request, "customerTitle");
		String customerTel = ReqUtil.getParam(request, "customerTel");
		String customerMob = ReqUtil.getParam(request, "customerMob");
		String customerLv = ReqUtil.getParam(request, "customerLv");
		String customerState = ReqUtil.getParam(request, "customerState");
		String pageIndex = ReqUtil.getParam(request, "pageIndex");
		int pageIndexInt = 1;
		if(StringUtils.isNotBlank(pageIndex)){
			pageIndexInt = Integer.valueOf(pageIndex);
		}
		
		CustomerInfoFilter customerInfoFilter = new CustomerInfoFilter();
		customerInfoFilter.setCustomerName(customerName);
		customerInfoFilter.setCustomerTitle(customerTitle);
		customerInfoFilter.setCustomerTel(customerTel);
		customerInfoFilter.setCustomerMob(customerMob);
		customerInfoFilter.setCustomerLv(customerLv);
		customerInfoFilter.setCustomerState(customerState);
		
		Page<CustomerInfoPo> page = customerInfoService.listOfCustomers(customerInfoFilter, pageIndexInt, COUNT);
		
		mv.addObject("page", page);
		mv.addObject("customerInfoFilter", customerInfoFilter);
		mv.addObject("asideLi", "customer.do");
		return mv;
	}
	
	@RequestMapping(value = "/customer/detail.do", method = RequestMethod.GET)
	public ModelAndView customerDetail(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mv = new AdminCommonModeAndView("/customer-detail", request);
		String oper = ReqUtil.getParam(request, "oper");
		String id = ReqUtil.getParam(request, "id");

		//客户信息
		CustomerInfoPo customerInfoPo = new CustomerInfoPo();
		if(CUSTOMER_DETAIL_OPER_ADD.equals(oper)){
			//do nothing
		} else if(CUSTOMER_DETAIL_OPER_DETAIL.equals(oper)
				|| CUSTOMER_DETAIL_OPER_EDIT.equals(oper)){
			customerInfoPo = customerInfoService.detailOfCustomer(id);
		}
		customerInfoPo.setCostomerPlan(imageService.getImgUrlById(customerInfoPo.getCostomerPlan()));
		customerInfoPo.setCostomerPrice(imageService.getImgUrlById(customerInfoPo.getCostomerPrice()));
		customerInfoPo.setCustomerContract(imageService.getImgUrlById(customerInfoPo.getCustomerContract()));
		
		//跟进信息
		List<CustomerNotePo> customerNotePoList = null;
		if(StringUtils.isNotBlank(id)){
			customerNotePoList = customerNoteService.getAllNoteByCostomerId(id);
		}
		if(customerNotePoList != null && customerNotePoList.size()>0){
			for(CustomerNotePo customerNotePo: customerNotePoList){
				customerNotePo.setNoteFile(imageService.getImgUrlById(customerNotePo.getNoteFile()));
			}
		}
		
		mv.addObject("customerInfoPo", customerInfoPo);
		mv.addObject("customerNotePoList", customerNotePoList);
		mv.addObject("oper", oper);
		mv.addObject("nowDate", new Date());
		mv.addObject("asideLi", "customer.do");
		return mv;
	}
	
	@ResponseBody
	@RequestMapping(value = "/customer/add.json", method = RequestMethod.POST)
	public Result addCustomer(HttpServletRequest request,HttpServletResponse response){
		Result result = new Result();
		String customerName = ReqUtil.getParam(request, "customerName");
		String customerTitle = ReqUtil.getParam(request, "customerTitle");
		String customerCompany = ReqUtil.getParam(request, "customerCompany");
		String customerTel = ReqUtil.getParam(request, "customerTel");
		String customerMob = ReqUtil.getParam(request, "customerMob");
		String customerMail = ReqUtil.getParam(request, "customerMail");
		String customerFox = ReqUtil.getParam(request, "customerFox");
		String companyAddress = ReqUtil.getParam(request, "companyAddress");
		String companyLink = ReqUtil.getParam(request, "companyLink");
		String customerLv = ReqUtil.getParam(request, "customerLv");
		String customerState = ReqUtil.getParam(request, "customerState");
		String customerNeed = ReqUtil.getParam(request, "customerNeed");
		
		CustomerInfoPo customerInfoPo = new CustomerInfoPo();
		customerInfoPo.setCustomerName(customerName);
		customerInfoPo.setCustomerTitle(customerTitle);
		customerInfoPo.setCustomerCompany(customerCompany);
		customerInfoPo.setCustomerTel(customerTel);
		customerInfoPo.setCustomerMob(customerMob);
		customerInfoPo.setCustomerMail(customerMail);
		customerInfoPo.setCustomerFox(customerFox);
		customerInfoPo.setCompanyAddress(companyAddress);
		customerInfoPo.setCompanyLink(companyLink);
		customerInfoPo.setCustomerLv(customerLv);
		customerInfoPo.setCustomerState(customerState);
		customerInfoPo.setCustomerNeed(customerNeed);
		customerInfoPo.setRegisterId("admn");
		
		customerInfoService.addCustomer(customerInfoPo);
		
		result.setSuccess(true);
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/customer/update.json", method = RequestMethod.POST)
	public Result updateCustomer(HttpServletRequest request,HttpServletResponse response){
		Result result = new Result();
		String id = ReqUtil.getParam(request, "id");
		String customerName = ReqUtil.getParam(request, "customerName");
		String customerTitle = ReqUtil.getParam(request, "customerTitle");
		String customerCompany = ReqUtil.getParam(request, "customerCompany");
		String customerTel = ReqUtil.getParam(request, "customerTel");
		String customerMob = ReqUtil.getParam(request, "customerMob");
		String customerMail = ReqUtil.getParam(request, "customerMail");
		String customerFox = ReqUtil.getParam(request, "customerFox");
		String companyAddress = ReqUtil.getParam(request, "companyAddress");
		String companyLink = ReqUtil.getParam(request, "companyLink");
		String customerLv = ReqUtil.getParam(request, "customerLv");
		String customerState = ReqUtil.getParam(request, "customerState");
		String customerNeed = ReqUtil.getParam(request, "customerNeed");
		
		CustomerInfoPo customerInfoPo = new CustomerInfoPo();
		customerInfoPo.setId(id);
		customerInfoPo.setCustomerName(customerName);
		customerInfoPo.setCustomerTitle(customerTitle);
		customerInfoPo.setCustomerCompany(customerCompany);
		customerInfoPo.setCustomerTel(customerTel);
		customerInfoPo.setCustomerMob(customerMob);
		customerInfoPo.setCustomerMail(customerMail);
		customerInfoPo.setCustomerFox(customerFox);
		customerInfoPo.setCompanyAddress(companyAddress);
		customerInfoPo.setCompanyLink(companyLink);
		customerInfoPo.setCustomerLv(customerLv);
		customerInfoPo.setCustomerState(customerState);
		customerInfoPo.setCustomerNeed(customerNeed);
		customerInfoPo.setRegisterId("admn");
		
		customerInfoService.update(customerInfoPo);
		
		result.setSuccess(true);
		return result;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/customer/updateOther.json", method = RequestMethod.POST)
	public Result updatePrice(HttpServletRequest request,HttpServletResponse response){
		Result result = new Result();
		String id = ReqUtil.getParam(request, "id");
		String costomerPrice = ReqUtil.getParam(request, "costomerPrice");
		String customerContract = ReqUtil.getParam(request, "customerContract");
		String costomerPlan = ReqUtil.getParam(request, "costomerPlan");
		String costomerCost = ReqUtil.getParam(request, "costomerCost");
		String costomerMark = ReqUtil.getParam(request, "costomerMark");
		CustomerInfoPo customerInfoPo = new CustomerInfoPo();
		customerInfoPo.setId(id);
		customerInfoPo.setCostomerPrice(costomerPrice);
		if(StringUtils.isNotBlank(costomerPrice)){
			customerInfoPo.setPriceTime(new Date());
		}
		customerInfoPo.setCustomerContract(customerContract);
		if(StringUtils.isNotBlank(customerContract)){
			customerInfoPo.setContractTime(new Date());
		}
		
		customerInfoPo.setCostomerPlan(costomerPlan);
		if(StringUtils.isNotBlank(costomerPlan)){
			customerInfoPo.setPlanTime(new Date());
		}
		customerInfoPo.setCostomerCost(costomerCost);
		customerInfoPo.setCostomerMark(costomerMark);
		customerInfoService.update(customerInfoPo);
		result.setSuccess(true);
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/customer/delete.json", method = RequestMethod.POST)
	public Result deleteCustomer(HttpServletRequest request,HttpServletResponse response){
		Result result = new Result();
		String id = ReqUtil.getParam(request, "id");
		customerInfoService.delete(id);
		result.setSuccess(true);
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/customerNote/add.json", method = RequestMethod.POST)
	public Result addCustomerNote(HttpServletRequest request,HttpServletResponse response){
		Result result = new Result();
		String customerId = ReqUtil.getParam(request, "customerId");
		String noteContent = ReqUtil.getParam(request, "noteContent");
		String noteMethod = ReqUtil.getParam(request, "noteMethod");
		String noteFile = ReqUtil.getParam(request, "noteFile");
		
		CustomerNotePo customerNotePo = new CustomerNotePo();
		customerNotePo.setCustomerId(customerId);
		customerNotePo.setNoteContent(noteContent);
		customerNotePo.setNoteMethod(noteMethod);
		customerNotePo.setNoteFile(noteFile);
		customerNotePo.setNoteType("");
		customerNoteService.insertNote(customerNotePo);
		
		result.setSuccess(true);
		return result;
	}
	
	
	
}  