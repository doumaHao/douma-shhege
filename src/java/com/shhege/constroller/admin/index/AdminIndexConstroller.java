package com.shhege.constroller.admin.index;

import java.util.ArrayList;
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

import com.shhege.common.constant.CacheKeyConstant;
import com.shhege.common.mv.AdminCommonModeAndView;
import com.shhege.core.util.request.ReqUtil;
import com.shhege.madel.ajax.Result;
import com.shhege.madel.po.SysParamPo;
import com.shhege.madel.vo.IndexVo;
import com.shhege.service.SystemService;

@Controller
@RequestMapping(value = "/admin")
public class AdminIndexConstroller {
	
	@Autowired
	private	SystemService systemService;
	
	@RequestMapping(value = "/index.do", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mv = new AdminCommonModeAndView("/index", request);
		
		String indexImgId1 = systemService.findByKeyTrue(CacheKeyConstant.key_sysParam_indexImg1);
		String indexImgUrl1 = systemService.findByKey(CacheKeyConstant.key_sysParam_indexImg1);
		String indexImgId2 = systemService.findByKeyTrue(CacheKeyConstant.key_sysParam_indexImg2);
		String indexImgUrl2 = systemService.findByKey(CacheKeyConstant.key_sysParam_indexImg2);
		String indexImgId3 = systemService.findByKeyTrue(CacheKeyConstant.key_sysParam_indexImg3);
		String indexImgUrl3 = systemService.findByKey(CacheKeyConstant.key_sysParam_indexImg3);
		String indexImgTime = systemService.findByKey(CacheKeyConstant.key_sysParam_indexImgTime);
		IndexVo indexVo = new IndexVo();
		indexVo.setIndexImgId1(indexImgId1);
		indexVo.setIndexImgUrl1(indexImgUrl1);
		indexVo.setIndexImgId2(indexImgId2);
		indexVo.setIndexImgUrl2(indexImgUrl2);
		indexVo.setIndexImgId3(indexImgId3);
		indexVo.setIndexImgUrl3(indexImgUrl3);
		indexVo.setIndexImgTime(Integer.valueOf(StringUtils.isBlank(indexImgTime)?"0":indexImgTime));
		
		mv.addObject("indexVo", indexVo);
		mv.addObject("readOnly", true);
		mv.addObject("asideLi", "index.do");
		
		return mv;
	}
	
	@ResponseBody
	@RequestMapping(value = "/setIndexParam.json", method = RequestMethod.POST)
	public Result setIndexParam(HttpServletRequest request,HttpServletResponse response){
		Result result = new Result();
		
		String indexImgId1 = ReqUtil.getParam(request, "indexImgId1");
		if(StringUtils.isBlank(indexImgId1)) indexImgId1 = "";
		String indexImgId2 = ReqUtil.getParam(request, "indexImgId2");
		if(StringUtils.isBlank(indexImgId2)) indexImgId2 = "";
		String indexImgId3 = ReqUtil.getParam(request, "indexImgId3");
		if(StringUtils.isBlank(indexImgId3)) indexImgId3 = "";
		String indexImgTime = ReqUtil.getParam(request, "indexImgTime");
		if(StringUtils.isBlank(indexImgTime)) indexImgTime = "";
		
		List<SysParamPo> sysParamPoList = new ArrayList<SysParamPo>();
		SysParamPo indexImgId1Po = new SysParamPo();
		indexImgId1Po.setParamKey(CacheKeyConstant.key_sysParam_indexImg1);
		indexImgId1Po.setParamValue(indexImgId1);
		sysParamPoList.add(indexImgId1Po);
		SysParamPo indexImgId2Po = new SysParamPo();
		indexImgId2Po.setParamKey(CacheKeyConstant.key_sysParam_indexImg2);
		indexImgId2Po.setParamValue(indexImgId2);
		sysParamPoList.add(indexImgId2Po);
		SysParamPo indexImgId3Po = new SysParamPo();
		indexImgId3Po.setParamKey(CacheKeyConstant.key_sysParam_indexImg3);
		indexImgId3Po.setParamValue(indexImgId3);
		sysParamPoList.add(indexImgId3Po);
		SysParamPo indexImgTimePo = new SysParamPo();
		indexImgTimePo.setParamKey(CacheKeyConstant.key_sysParam_indexImgTime);
		indexImgTimePo.setParamValue(indexImgTime);
		sysParamPoList.add(indexImgTimePo);
		systemService.updateSome(sysParamPoList);
		
		result.setSuccess(true);
		return result;
	}
	
}  