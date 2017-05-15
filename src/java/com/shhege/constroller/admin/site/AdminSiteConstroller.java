package com.shhege.constroller.admin.site;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import com.shhege.core.cache.CacheData;
import com.shhege.core.util.request.ReqUtil;
import com.shhege.madel.ajax.Result;
import com.shhege.madel.po.SysParamPo;
import com.shhege.madel.vo.SysParamVo;
import com.shhege.service.SystemService;
@Controller
@RequestMapping(value = "/admin")
public class AdminSiteConstroller {
	
	@Autowired
	private	SystemService systemService;
	
	@RequestMapping(value = "/site.do", method = RequestMethod.GET)
	public ModelAndView site(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mv = new AdminCommonModeAndView("/site", request);
		
		Map<String, String> sysParamMap = (Map<String, String>) CacheData.getInstance().getData(CacheKeyConstant.key_sysParam);
		
		SysParamVo sysParamVo = new SysParamVo();
		sysParamVo.setAdminUsername(sysParamMap.get(CacheKeyConstant.key_sysParam_adminUsername));
		sysParamVo.setCompanyTel(sysParamMap.get(CacheKeyConstant.key_sysParam_companyTel));
		sysParamVo.setCompanyFox(sysParamMap.get(CacheKeyConstant.key_sysParam_companyFox));
		sysParamVo.setCompanyEmail(sysParamMap.get(CacheKeyConstant.key_sysParam_companyEmail));
		sysParamVo.setCompanyAddress(sysParamMap.get(CacheKeyConstant.key_sysParam_companyAddress));
		sysParamVo.setRecordNo(sysParamMap.get(CacheKeyConstant.key_sysParam_recordNo));
		sysParamVo.setCopyRight(sysParamMap.get(CacheKeyConstant.key_sysParam_copyRight));
		sysParamVo.setSaleTel(sysParamMap.get(CacheKeyConstant.key_sysParam_saleTel));
		sysParamVo.setTechnicalTel(sysParamMap.get(CacheKeyConstant.key_sysParam_technicalTel));
		
		mv.addObject("sysParamVo", sysParamVo);
		mv.addObject("readOnly", true);
		mv.addObject("asideLi", "site.do");
		
		return mv;
	}
	
	@ResponseBody
	@RequestMapping(value = "/setSiteParam.json", method = RequestMethod.POST)
	public Result setSiteParam(HttpServletRequest request,HttpServletResponse response){
		Result result = new Result();
		String adminUsername = ReqUtil.getParam(request, "adminUsername");
		if(StringUtils.isBlank(adminUsername)) adminUsername = "";
		String adminPassword = ReqUtil.getParam(request, "adminPassword");
		String adminPassword2 = ReqUtil.getParam(request, "adminPassword2");
		String companyTel = ReqUtil.getParam(request, "companyTel");
		if(StringUtils.isBlank(companyTel)) companyTel = "";
		String companyFox = ReqUtil.getParam(request, "companyFox");
		if(StringUtils.isBlank(companyFox)) companyFox = "";
		String companyEmail = ReqUtil.getParam(request, "companyEmail");
		if(StringUtils.isBlank(companyEmail)) companyEmail = "";
		String companyAddress = ReqUtil.getParam(request, "companyAddress");
		if(StringUtils.isBlank(companyAddress)) companyAddress = "";
		String recordNo = ReqUtil.getParam(request, "recordNo");
		if(StringUtils.isBlank(recordNo)) recordNo = "";
		String copyRight = ReqUtil.getParam(request, "copyRight");
		if(StringUtils.isBlank(copyRight)) copyRight = "";
		String saleTel = ReqUtil.getParam(request, "saleTel");
		if(StringUtils.isBlank(saleTel)) saleTel = "";
		String technicalTel = ReqUtil.getParam(request, "technicalTel");
		if(StringUtils.isBlank(technicalTel)) technicalTel = "";
		
		List<SysParamPo> sysParamPoList = new ArrayList<SysParamPo>();
		SysParamPo adminUsernamePo = new SysParamPo();
		adminUsernamePo.setParamKey(CacheKeyConstant.key_sysParam_adminUsername);
		adminUsernamePo.setParamValue(adminUsername);
		sysParamPoList.add(adminUsernamePo);
		if(StringUtils.isNotBlank(adminPassword)
				&& StringUtils.isNotBlank(adminPassword2)
				&& adminPassword.equals(adminPassword2)){
			SysParamPo adminPasswordPo = new SysParamPo();
			adminPasswordPo.setParamKey(CacheKeyConstant.key_sysParam_adminPassword);
			adminPasswordPo.setParamValue(adminPassword);
			sysParamPoList.add(adminPasswordPo);
		}
		SysParamPo companyTelPo = new SysParamPo();
		companyTelPo.setParamKey(CacheKeyConstant.key_sysParam_companyTel);
		companyTelPo.setParamValue(companyTel);
		sysParamPoList.add(companyTelPo);
		SysParamPo companyFoxPo = new SysParamPo();
		companyFoxPo.setParamKey(CacheKeyConstant.key_sysParam_companyFox);
		companyFoxPo.setParamValue(companyFox);
		sysParamPoList.add(companyFoxPo);
		SysParamPo companyEmailPo = new SysParamPo();
		companyEmailPo.setParamKey(CacheKeyConstant.key_sysParam_companyEmail);
		companyEmailPo.setParamValue(companyEmail);
		sysParamPoList.add(companyEmailPo);
		SysParamPo companyAddressPo = new SysParamPo();
		companyAddressPo.setParamKey(CacheKeyConstant.key_sysParam_companyAddress);
		companyAddressPo.setParamValue(companyAddress);
		sysParamPoList.add(companyAddressPo);
		SysParamPo recordNoPo = new SysParamPo();
		recordNoPo.setParamKey(CacheKeyConstant.key_sysParam_recordNo);
		recordNoPo.setParamValue(recordNo);
		sysParamPoList.add(recordNoPo);
		SysParamPo copyRightPo = new SysParamPo();
		copyRightPo.setParamKey(CacheKeyConstant.key_sysParam_copyRight);
		copyRightPo.setParamValue(copyRight);
		sysParamPoList.add(copyRightPo);
		SysParamPo saleTelPo = new SysParamPo();
		saleTelPo.setParamKey(CacheKeyConstant.key_sysParam_saleTel);
		saleTelPo.setParamValue(saleTel);
		sysParamPoList.add(saleTelPo);
		SysParamPo technicalTelPo = new SysParamPo();
		technicalTelPo.setParamKey(CacheKeyConstant.key_sysParam_technicalTel);
		technicalTelPo.setParamValue(technicalTel);
		sysParamPoList.add(technicalTelPo);
		
		systemService.updateSome(sysParamPoList);
		
		result.setSuccess(true);
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/setSiteParamOfUserImg.json", method = RequestMethod.POST)
	public Result setSiteParamOfUserImg(HttpServletRequest request,HttpServletResponse response){
		Result result = new Result();
		String userImg = ReqUtil.getParam(request, "userImg");
		if(StringUtils.isBlank(userImg)) userImg = "";
		systemService.update(CacheKeyConstant.key_sysParam_userImg, userImg);
		
		result.setSuccess(true);
		return result;
	}
}  