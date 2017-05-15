package com.shhege.constroller.admin.product;

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

import com.shhege.common.mv.AdminCommonModeAndView;
import com.shhege.core.util.request.ReqUtil;
import com.shhege.madel.ajax.Result;
import com.shhege.madel.filter.ProductInfoFilter;
import com.shhege.madel.page.Page;
import com.shhege.madel.po.ProductCatagoryPo;
import com.shhege.madel.po.ProductInfoPo;
import com.shhege.madel.vo.ProductCatagoryVo;
import com.shhege.madel.vo.ProductVo;
import com.shhege.service.ImageService;
import com.shhege.service.ProductCatagoryService;
import com.shhege.service.ProductInfoService;
import com.shhege.service.TextInfoService;

@Controller
@RequestMapping(value = "/admin")
public class AdminProductConstroller {
	
	@Autowired
	private	ProductCatagoryService productCatagoryService;
	
	@Autowired
	private ProductInfoService productInfoService;
	
	@Autowired
	private ImageService imageService;
	
	@Autowired
	private TextInfoService textInfoService;
	
	final static public int TOP_NUM = 6;
	final static public int CATAGOTY_SORT_1 = 1;
	final static public int CATAGOTY_SORT_2 = 2;
	final static public int CATAGOTY_SORT_3 = 3;
	final static public int CATAGOTY_SORT_4 = 4;
	final static public int CATAGOTY_SORT_5 = 5;
	final static public int CATAGOTY_SORT_6 = 6;
	
	final static public int COUNT = 10;
	
	final static public String PRODUCTDES_CHANE_FLG = "yes";
	
	final static public String PRDUCT_DETAIL_OPER_DETAIL = "detail";
	final static public String PRDUCT_DETAIL_OPER_ADD = "add";
	final static public String PRDUCT_DETAIL_OPER_EDIT = "edit";
	
	@RequestMapping(value = "/catagory.do", method = RequestMethod.GET)
	public ModelAndView catagory(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mv = new AdminCommonModeAndView("/catagory", request);
		
		List<ProductCatagoryPo> productCatagoryPoList = productCatagoryService.topCatagory(TOP_NUM);
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
		
		mv.addObject("productCatagoryVo", productCatagoryVo);
		mv.addObject("asideLi", "catagory.do");
		return mv;
	}
	
	@ResponseBody
	@RequestMapping(value = "/updateCatagory.json", method = RequestMethod.POST)
	public Result updateCatagory(HttpServletRequest request,HttpServletResponse response){
		Result result = new Result();
		String id1 = ReqUtil.getParam(request, "id1");
		String name1 = ReqUtil.getParam(request, "name1");
		String imgId1 = ReqUtil.getParam(request, "imgId1");
		String id2 = ReqUtil.getParam(request, "id2");
		String name2 = ReqUtil.getParam(request, "name2");
		String imgId2 = ReqUtil.getParam(request, "imgId2");
		String id3 = ReqUtil.getParam(request, "id3");
		String name3 = ReqUtil.getParam(request, "name3");
		String imgId3 = ReqUtil.getParam(request, "imgId3");
		String id4 = ReqUtil.getParam(request, "id4");
		String name4 = ReqUtil.getParam(request, "name4");
		String imgId4 = ReqUtil.getParam(request, "imgId4");
		String id5 = ReqUtil.getParam(request, "id5");
		String name5 = ReqUtil.getParam(request, "name5");
		String imgId5 = ReqUtil.getParam(request, "imgId5");
		String id6 = ReqUtil.getParam(request, "id6");
		String name6 = ReqUtil.getParam(request, "name6");
		String imgId6 = ReqUtil.getParam(request, "imgId6");
		
		ProductCatagoryPo productCatagoryPo = new ProductCatagoryPo();
		if(StringUtils.isNotBlank(id1)){
			productCatagoryPo = new ProductCatagoryPo();
			productCatagoryPo.setId(id1);
			productCatagoryPo.setCatagoryName(name1);
			productCatagoryPo.setCatagoryImg(imgId1);
			productCatagoryService.updateCatagory(productCatagoryPo);
		} else {
			productCatagoryPo = new ProductCatagoryPo();
			productCatagoryPo.setCatagorySort(CATAGOTY_SORT_1);
			productCatagoryPo.setCatagoryName(name1);
			productCatagoryPo.setCatagoryImg(imgId1);
			productCatagoryService.addCatagory(productCatagoryPo);
		}
		if(StringUtils.isNotBlank(id2)){
			productCatagoryPo = new ProductCatagoryPo();
			productCatagoryPo.setId(id2);
			productCatagoryPo.setCatagoryName(name2);
			productCatagoryPo.setCatagoryImg(imgId2);
			productCatagoryService.updateCatagory(productCatagoryPo);
		} else {
			productCatagoryPo = new ProductCatagoryPo();
			productCatagoryPo.setCatagorySort(CATAGOTY_SORT_2);
			productCatagoryPo.setCatagoryName(name2);
			productCatagoryPo.setCatagoryImg(imgId2);
			productCatagoryService.addCatagory(productCatagoryPo);
		}
		if(StringUtils.isNotBlank(id3)){
			productCatagoryPo = new ProductCatagoryPo();
			productCatagoryPo.setId(id3);
			productCatagoryPo.setCatagoryName(name3);
			productCatagoryPo.setCatagoryImg(imgId3);
			productCatagoryService.updateCatagory(productCatagoryPo);
		} else {
			productCatagoryPo = new ProductCatagoryPo();
			productCatagoryPo.setCatagorySort(CATAGOTY_SORT_3);
			productCatagoryPo.setCatagoryName(name3);
			productCatagoryPo.setCatagoryImg(imgId3);
			productCatagoryService.addCatagory(productCatagoryPo);
		}
		if(StringUtils.isNotBlank(id4)){
			productCatagoryPo = new ProductCatagoryPo();
			productCatagoryPo.setId(id4);
			productCatagoryPo.setCatagoryName(name4);
			productCatagoryPo.setCatagoryImg(imgId4);
			productCatagoryService.updateCatagory(productCatagoryPo);
		} else {
			productCatagoryPo = new ProductCatagoryPo();
			productCatagoryPo.setCatagorySort(CATAGOTY_SORT_4);
			productCatagoryPo.setCatagoryName(name4);
			productCatagoryPo.setCatagoryImg(imgId4);
			productCatagoryService.addCatagory(productCatagoryPo);
		}
		if(StringUtils.isNotBlank(id5)){
			productCatagoryPo = new ProductCatagoryPo();
			productCatagoryPo.setId(id5);
			productCatagoryPo.setCatagoryName(name5);
			productCatagoryPo.setCatagoryImg(imgId5);
			productCatagoryService.updateCatagory(productCatagoryPo);
		} else {
			productCatagoryPo = new ProductCatagoryPo();
			productCatagoryPo.setCatagorySort(CATAGOTY_SORT_5);
			productCatagoryPo.setCatagoryName(name5);
			productCatagoryPo.setCatagoryImg(imgId5);
			productCatagoryService.addCatagory(productCatagoryPo);
		}
		if(StringUtils.isNotBlank(id6)){
			productCatagoryPo = new ProductCatagoryPo();
			productCatagoryPo.setId(id6);
			productCatagoryPo.setCatagoryName(name6);
			productCatagoryPo.setCatagoryImg(imgId6);
			productCatagoryService.updateCatagory(productCatagoryPo);
		} else {
			productCatagoryPo = new ProductCatagoryPo();
			productCatagoryPo.setCatagorySort(CATAGOTY_SORT_6);
			productCatagoryPo.setCatagoryName(name6);
			productCatagoryPo.setCatagoryImg(imgId6);
			productCatagoryService.addCatagory(productCatagoryPo);
		}
		
		result.setSuccess(true);
		return result;
	}
	
	@RequestMapping(value = "/product.do", method = RequestMethod.GET)
	public ModelAndView product(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mv = new AdminCommonModeAndView("/product", request);
		
		String pageIndex = ReqUtil.getParam(request, "pageIndex");
		int pageIndexInt = 1;
		if(StringUtils.isNotBlank(pageIndex)){
			pageIndexInt = Integer.valueOf(pageIndex);
		}
		
		ProductInfoFilter productInfoFilter = new ProductInfoFilter();
		Page<ProductInfoPo> page = productInfoService.queryProductByPage(productInfoFilter, pageIndexInt, COUNT);
		List<ProductVo> productVoList = new ArrayList<ProductVo>();
		if(page !=null 
				&& page.getPageData() !=  null
				&& page.getPageData().size() > 0){
			for(ProductInfoPo po: page.getPageData()){
				ProductVo vo = new ProductVo();
				vo.setId(po.getId());
				vo.setCatagory(productCatagoryService.getCatagoryName(po.getCatagoryId()));
				vo.setName(po.getProductName());
				vo.setImgUrl(imageService.getImgUrlById(po.getProductImg()));
				vo.setSort(po.getProductSort());
				productVoList.add(vo);
			}
		}

		mv.addObject("page", page);
		mv.addObject("productVoList", productVoList);
		mv.addObject("productInfoFilter", productInfoFilter);
		mv.addObject("asideLi", "product.do");
		return mv;
	}
	
	@RequestMapping(value = "/product/detail.do", method = RequestMethod.GET)
	public ModelAndView productDetail(HttpServletRequest request,HttpServletResponse response){
		ModelAndView mv = new AdminCommonModeAndView("/product-detail", request);
		String oper = ReqUtil.getParam(request, "oper");
		String id = ReqUtil.getParam(request, "id");
		
		ProductVo productVo = new ProductVo();
		List<ProductCatagoryPo> productCatagoryPoList = productCatagoryService.topCatagory(TOP_NUM);
		if(PRDUCT_DETAIL_OPER_ADD.equals(oper)){
			//do nothing
		} else if(PRDUCT_DETAIL_OPER_DETAIL.equals(oper)
				|| PRDUCT_DETAIL_OPER_EDIT.equals(oper)){
			ProductInfoPo productInfoPo = productInfoService.queryProductById(id);
			if(productInfoPo != null){
				productVo.setId(productInfoPo.getId());
				productVo.setName(productInfoPo.getProductName());
				productVo.setCatagoryId(productInfoPo.getCatagoryId());
				productVo.setCatagory(productCatagoryService.getCatagoryName(productInfoPo.getCatagoryId()));
				productVo.setImgId(productInfoPo.getProductImg());
				productVo.setImgUrl(imageService.getImgUrlById(productInfoPo.getProductImg()));
				productVo.setSort(productInfoPo.getProductSort());
				productVo.setDesId(productInfoPo.getProductDes());
				productVo.setDesText(textInfoService.getText(productInfoPo.getProductDes()));
			}
		}
		
		mv.addObject("oper", oper);
		mv.addObject("productVo", productVo);
		mv.addObject("productCatagoryPoList", productCatagoryPoList);
		mv.addObject("asideLi", "product.do");
		return mv;
	}
	
	@ResponseBody
	@RequestMapping(value = "/product/add.json", method = RequestMethod.POST)
	public Result addProduct(HttpServletRequest request,HttpServletResponse response){
		Result result = new Result();
		String productSort = ReqUtil.getParam(request, "productSort");
		int productSortInt = 1;
		if(StringUtils.isNotBlank(productSort)) productSortInt = Integer.valueOf(productSort);
		String catagoryId = ReqUtil.getParam(request, "catagoryId");
		String productName = ReqUtil.getParam(request, "productName");
		String productImg = ReqUtil.getParam(request, "productImg");
		String productDes = ReqUtil.getParam(request, "productDes");
		String productDesChaneFlg = ReqUtil.getParam(request, "productDesChaneFlg");

		ProductInfoPo productInfoPo = new ProductInfoPo();
		productInfoPo.setProductSort(productSortInt);
		productInfoPo.setCatagoryId(catagoryId);
		productInfoPo.setProductName(productName);
		productInfoPo.setProductImg(productImg);
		if(PRODUCTDES_CHANE_FLG.equals(productDesChaneFlg)){
			productInfoPo.setProductDes(productDes);
		} else {
			productInfoPo.setProductDes(null);
		}
		productInfoService.addProduct(productInfoPo);
		
		result.setSuccess(true);
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/product/update.json", method = RequestMethod.POST)
	public Result updateProduct(HttpServletRequest request,HttpServletResponse response){
		Result result = new Result();
		String id = ReqUtil.getParam(request, "id");
		String productSort = ReqUtil.getParam(request, "productSort");
		int productSortInt = 1;
		if(StringUtils.isNotBlank(productSort)) productSortInt = Integer.valueOf(productSort);
		String catagoryId = ReqUtil.getParam(request, "catagoryId");
		String productName = ReqUtil.getParam(request, "productName");
		String productImg = ReqUtil.getParam(request, "productImg");
		String productDes = ReqUtil.getParam(request, "productDes");
		String productDesChaneFlg = ReqUtil.getParam(request, "productDesChaneFlg");

		ProductInfoPo productInfoPo = new ProductInfoPo();
		productInfoPo.setId(id);
		productInfoPo.setProductSort(productSortInt);
		productInfoPo.setCatagoryId(catagoryId);
		productInfoPo.setProductName(productName);
		productInfoPo.setProductImg(productImg);
		if(PRODUCTDES_CHANE_FLG.equals(productDesChaneFlg)){
			productInfoPo.setProductDes(productDes);
		} else {
			productInfoPo.setProductDes(null);
		}
		productInfoService.updateProduct(productInfoPo);
		
		result.setSuccess(true);
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value = "/product/delete.json", method = RequestMethod.POST)
	public Result deleteProduct(HttpServletRequest request,HttpServletResponse response){
		Result result = new Result();
		String id = ReqUtil.getParam(request, "id");
		productInfoService.deleteProduct(id);
		result.setSuccess(true);
		return result;
	}
	
}  