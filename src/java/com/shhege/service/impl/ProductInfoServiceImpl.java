package com.shhege.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shhege.core.util.id.IdGen;
import com.shhege.madel.filter.ProductInfoFilter;
import com.shhege.madel.page.Page;
import com.shhege.madel.po.ProductInfoPo;
import com.shhege.madel.po.TextInfoPo;
import com.shhege.service.ImageService;
import com.shhege.service.ProductInfoService;
import com.shhege.service.TextInfoService;
import com.shhege.service.dao.ProductInfoDao;

@Service("productInfoService")
public class ProductInfoServiceImpl implements ProductInfoService {
	
	@Autowired
	private ProductInfoDao productInfoDao;
	
	@Autowired
	private TextInfoService textInfoService;
	
	@Autowired
	private ImageService imageService;

	@Override
	public int countOfProduct(ProductInfoFilter productInfoFilter) {
		return productInfoDao.countOfProductInfo(productInfoFilter);
	}

	@Override
	public Page<ProductInfoPo> queryProductByPage(
			ProductInfoFilter productInfoFilter, int pageIndex, int count) {
		Page<ProductInfoPo> page = new Page<ProductInfoPo>();
		//总条数
		page.setPageCount(productInfoDao.countOfProductInfo(productInfoFilter));
		//总页数
		page.setPageCount((page.getPageCount()-1)/count+1);
		//每页最多多少条记录
		page.setCount(count);
		//当前页数
		page.setPageIndex(pageIndex);
		//上一页
		if(page.getPageIndex()>1){
			page.setPagePre(page.getPageIndex()-1);
		} else {
			page.setPagePre(1);
		}
		//下一页
		if(page.getPageIndex()<page.getPageCount()){
			page.setPageNext(page.getPageIndex()+1);
		} else {
			page.setPageNext(page.getPageCount());
		}
		//当前记录集合
		page.setPageData(productInfoDao.findProductInfoByPage(productInfoFilter, (pageIndex-1)*count, count));
		
		return page;
	}

	@Override
	public ProductInfoPo queryProductById(String id) {
		return productInfoDao.findProductInfoById(id);
	}

	@Override
	public int addProduct(ProductInfoPo productInfoPo) {
		productInfoPo.setId(IdGen.uuid());
		if(productInfoPo.getProductDes() != null){
			TextInfoPo textInfoPo = new TextInfoPo();
			textInfoPo.setTextContent(productInfoPo.getProductDes());
			textInfoService.insertText(textInfoPo);
			productInfoPo.setProductDes(textInfoPo.getTextId());
		}
		return productInfoDao.insertProductInfo(productInfoPo);
	}

	@Override
	public int updateProduct(ProductInfoPo productInfoPo) {
		if(productInfoPo == null || StringUtils.isBlank(productInfoPo.getId())){
			throw new IllegalArgumentException("更新失败");
		} else {
			if(productInfoPo.getProductDes() != null){
				TextInfoPo textInfoPo = new TextInfoPo();
				textInfoPo.setTextContent(productInfoPo.getProductDes());
				textInfoService.insertText(textInfoPo);
				productInfoPo.setProductDes(textInfoPo.getTextId());
			}
			return productInfoDao.updateProductInfoById(productInfoPo);
		}
	}

	@Override
	public int deleteProduct(String id) {
		if(StringUtils.isBlank(id)){
			throw new IllegalArgumentException("删除失败");
		} else {
			return productInfoDao.deleteProductInfo(id);
		}
	}

	@Override
	public List<ProductInfoPo> topGoodsByCatagoty(int top, String catagoryId) {
		List<ProductInfoPo> productInfoPoList = productInfoDao.topGoodsByCatagoty(top, catagoryId);
		return productInfoPoList;
	}

	@Override
	public List<ProductInfoPo> queryProductByRandom(int random) {
		List<ProductInfoPo> productInfoPoList = new ArrayList<ProductInfoPo>();
		ProductInfoFilter productInfoFilter = new ProductInfoFilter();
		int allSize = countOfProduct(productInfoFilter);
		if(allSize <= random){
			productInfoPoList = productInfoDao.findProductInfoByPage(productInfoFilter, 0, allSize);
		} else if(allSize > random){
			int randomStart = new Random().nextInt(allSize - random);
			productInfoPoList = productInfoDao.findProductInfoByPage(productInfoFilter, randomStart, random);
		}
		
		return productInfoPoList;
	}
	
	public static void main(String[] args) {
		System.out.println(new Random().nextInt((5 - 4)+1));
	}
	
	
}
