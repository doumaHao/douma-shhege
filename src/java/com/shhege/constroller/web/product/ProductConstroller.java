package com.shhege.constroller.web.product;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.shhege.common.mv.WebCommonModeAndView;
import com.shhege.core.util.request.ReqUtil;
import com.shhege.madel.po.ProductCatagoryPo;
import com.shhege.madel.po.ProductInfoPo;
import com.shhege.madel.vo.ProductCatagoryVo;
import com.shhege.service.ImageService;
import com.shhege.service.ProductCatagoryService;
import com.shhege.service.ProductInfoService;
import com.shhege.service.TextInfoService;

/**
 * 产品中心
 * @author Douma
 *
 */
@Controller
public class ProductConstroller {
	
	@Autowired
	private	ProductCatagoryService productCatagoryService;
	
	@Autowired
	private ProductInfoService productInfoService;
	
	@Autowired
	private ImageService imageService;
	
	@Autowired
	private TextInfoService textInfoService;
	
	final static public int TOP_NUM_CATAGOTY = 6;
	
	final static public int TOP_NUM_GOODS = 8;
	
	@RequestMapping(value = "/product", method = RequestMethod.GET)
	public ModelAndView product(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mv = new WebCommonModeAndView("/typo", request);
		
		List<ProductCatagoryPo> productCatagoryPoList = productCatagoryService.topCatagory(TOP_NUM_GOODS);
		ProductCatagoryVo productCatagoryVo = getProductCatagoryVo(productCatagoryPoList);
		mv.addObject("productCatagoryVo", productCatagoryVo);
		
		return mv;
	}
	
	@RequestMapping(value = "/product/classify", method = RequestMethod.GET)
	public ModelAndView productClassify(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mv = new WebCommonModeAndView("/type-detail", request);
		String catagotyId = ReqUtil.getParam(request, "catagotyId");
		
		List<ProductCatagoryPo> productCatagoryPoList = productCatagoryService.topCatagory(TOP_NUM_GOODS);
		ProductCatagoryVo productCatagoryVo = getProductCatagoryVo(productCatagoryPoList);
		String productCatagoryName = productCatagoryService.getCatagoryName(catagotyId);
		List<ProductInfoPo> productInfoPoList = productInfoService.topGoodsByCatagoty(TOP_NUM_GOODS, catagotyId);
		for(ProductInfoPo po : productInfoPoList){
			po.setProductImg(imageService.getImgUrlById(po.getProductImg()));
		}
		
		mv.addObject("productCatagoryVo", productCatagoryVo);
		mv.addObject("catagotyId", catagotyId);
		mv.addObject("productCatagoryName", productCatagoryName);
		mv.addObject("productInfoPoList", productInfoPoList);
		return mv;
	}
	
	@RequestMapping(value = "/product/details", method = RequestMethod.GET)
	public ModelAndView productDetails(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mv = new WebCommonModeAndView("/single", request);
		String productId = ReqUtil.getParam(request, "productId");
		String catagoryId = ReqUtil.getParam(request, "catagoryId");
		
		if(StringUtils.isNotBlank(productId)){
			List<ProductCatagoryPo> productCatagoryPoList = productCatagoryService.topCatagory(TOP_NUM_GOODS);
			ProductCatagoryVo productCatagoryVo = getProductCatagoryVo(productCatagoryPoList);
			ProductInfoPo productInfoPo = productInfoService.queryProductById(productId);
			productInfoPo.setProductImg(imageService.getImgUrlById(productInfoPo.getProductImg()));
			productInfoPo.setProductDes(textInfoService.getText(productInfoPo.getProductDes()));
			
			mv.addObject("productCatagoryVo", productCatagoryVo);
			mv.addObject("productInfoPo", productInfoPo);
		}
		
		mv.addObject("catagoryId", catagoryId);
		return mv;
	}
	
	private ProductCatagoryVo getProductCatagoryVo(List<ProductCatagoryPo> productCatagoryPoList){
		ProductCatagoryVo productCatagoryVo = new ProductCatagoryVo();
		if(productCatagoryPoList != null && productCatagoryPoList.size()>0){
			for(int i=0; i<productCatagoryPoList.size(); i++){
				if(i == 0 && productCatagoryPoList.get(i) != null){
					productCatagoryVo.setId1(productCatagoryPoList.get(i).getId());
					productCatagoryVo.setName1(productCatagoryPoList.get(i).getCatagoryName());
					productCatagoryVo.setImgId1(productCatagoryPoList.get(i).getCatagoryImg());
					productCatagoryVo.setImgUrl1(imageService.getImgUrlById(productCatagoryVo.getImgId1()));
				} else if(i == 1 && productCatagoryPoList.get(i) != null){
					productCatagoryVo.setId2(productCatagoryPoList.get(i).getId());
					productCatagoryVo.setName2(productCatagoryPoList.get(i).getCatagoryName());
					productCatagoryVo.setImgId2(productCatagoryPoList.get(i).getCatagoryImg());
					productCatagoryVo.setImgUrl2(imageService.getImgUrlById(productCatagoryVo.getImgId2()));
				} else if(i == 2 && productCatagoryPoList.get(i) != null){
					productCatagoryVo.setId3(productCatagoryPoList.get(i).getId());
					productCatagoryVo.setName3(productCatagoryPoList.get(i).getCatagoryName());
					productCatagoryVo.setImgId3(productCatagoryPoList.get(i).getCatagoryImg());
					productCatagoryVo.setImgUrl3(imageService.getImgUrlById(productCatagoryVo.getImgId3()));
				} else if(i == 3 && productCatagoryPoList.get(i) != null){
					productCatagoryVo.setId4(productCatagoryPoList.get(i).getId());
					productCatagoryVo.setName4(productCatagoryPoList.get(i).getCatagoryName());
					productCatagoryVo.setImgId4(productCatagoryPoList.get(i).getCatagoryImg());
					productCatagoryVo.setImgUrl4(imageService.getImgUrlById(productCatagoryVo.getImgId4()));
				} else if(i == 4 && productCatagoryPoList.get(i) != null){
					productCatagoryVo.setId5(productCatagoryPoList.get(i).getId());
					productCatagoryVo.setName5(productCatagoryPoList.get(i).getCatagoryName());
					productCatagoryVo.setImgId5(productCatagoryPoList.get(i).getCatagoryImg());
					productCatagoryVo.setImgUrl5(imageService.getImgUrlById(productCatagoryVo.getImgId5()));
				} else if(i == 5 && productCatagoryPoList.get(i) != null){
					productCatagoryVo.setId6(productCatagoryPoList.get(i).getId());
					productCatagoryVo.setName6(productCatagoryPoList.get(i).getCatagoryName());
					productCatagoryVo.setImgId6(productCatagoryPoList.get(i).getCatagoryImg());
					productCatagoryVo.setImgUrl6(imageService.getImgUrlById(productCatagoryVo.getImgId6()));
				}
			}
		}
		return productCatagoryVo;
	}

}
