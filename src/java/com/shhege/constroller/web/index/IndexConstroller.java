package com.shhege.constroller.web.index;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.shhege.common.constant.CacheKeyConstant;
import com.shhege.common.mv.WebCommonModeAndView;
import com.shhege.madel.po.ProductInfoPo;
import com.shhege.service.ImageService;
import com.shhege.service.ProductInfoService;
import com.shhege.service.SystemService;
@Controller
public class IndexConstroller {
	
	@Autowired
	private	SystemService systemService;
	
	@Autowired
	private ProductInfoService productInfoService;
	
	@Autowired
	private ImageService imageService;
	
	final static public int INDEX_RANDOM = 4;
	
    /*public IndexConstroller() {}  
    @RequestMapping(value = "/login/{user}", method = RequestMethod.GET)  
    public ModelAndView myMethod(HttpServletRequest request,HttpServletResponse response,   
            @PathVariable("user") String user, ModelMap modelMap) throws Exception {  
    	System.out.println("dsadsadsadsa");
        modelMap.put("loginUser", user);  
        return new ModelAndView("/login/hello", modelMap);  
    }  
    @RequestMapping(value = "/welcome", method = RequestMethod.GET)  
    public String registPost() {
    	System.out.println("dsadsadsadsa");
        return "/welcome";  
    } */ 
	
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mv = new WebCommonModeAndView("/index", request);
		
		//首页随机四个产品
		List<ProductInfoPo> productInfoPoLisyt = productInfoService.queryProductByRandom(INDEX_RANDOM);
		if(productInfoPoLisyt != null && productInfoPoLisyt.size()>0){
			for(ProductInfoPo po : productInfoPoLisyt){
				po.setProductImg(imageService.getImgUrlById(po.getProductImg()));
			}
		}
		String img1 = systemService.findByKey(CacheKeyConstant.key_sysParam_indexImg1);
		String img2 = systemService.findByKey(CacheKeyConstant.key_sysParam_indexImg2);
		String img3 = systemService.findByKey(CacheKeyConstant.key_sysParam_indexImg3);
		String time = systemService.findByKey(CacheKeyConstant.key_sysParam_indexImgTime);
		
		mv.addObject("productInfoPoLisyt", productInfoPoLisyt);
		mv.addObject("img1", img1);
		mv.addObject("img2", img2);
		mv.addObject("img3", img3);
		mv.addObject("time", time);
		return mv;
	}
	
}  