package com.shhege.constroller.admin.invite;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.shhege.common.constant.CacheKeyConstant;
import com.shhege.common.mv.AdminCommonModeAndView;
import com.shhege.core.util.id.IdGen;
import com.shhege.core.util.request.ReqUtil;
import com.shhege.madel.ajax.Result;
import com.shhege.madel.po.TextInfoPo;
import com.shhege.service.SystemService;
import com.shhege.service.TextInfoService;
@Controller
@RequestMapping(value = "/admin")
public class AdminInviteConstroller {
	
	@Autowired
	private	SystemService systemService;
	
	@Autowired
	private TextInfoService textInfoService;
	
	final static public String ADVERTISES_CHANE_FLG = "yes";
	
	@RequestMapping(value = "/invite.do", method = RequestMethod.GET)
	public ModelAndView invite(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mv = new AdminCommonModeAndView("/invite", request);
		String advertises = systemService.findByKey(CacheKeyConstant.key_sysParam_advertises);
		
		mv.addObject("advertises", advertises);
		mv.addObject("asideLi", "invite.do");
		return mv;
	}
	
	@ResponseBody
	@RequestMapping(value = "/setInvite.json", method = RequestMethod.POST)
	public Result setInvite(HttpServletRequest request,HttpServletResponse response){
		Result result = new Result();
		
		String advertisesChangeFlg = ReqUtil.getParam(request, "advertisesChangeFlg");
		String advertises = ReqUtil.getParam(request, "advertises");
		if(ADVERTISES_CHANE_FLG.equals(advertisesChangeFlg)){
			TextInfoPo textInfoPo = new TextInfoPo();
			textInfoPo.setTextContent(advertises);
			textInfoService.insertText(textInfoPo);
			systemService.update(CacheKeyConstant.key_sysParam_advertises, textInfoPo.getTextId());
		}
		
		result.setSuccess(true);
		return result;
	}
	
}  