package com.shhege.service;

import java.util.List;

import com.shhege.madel.po.ProductCatagoryPo;

public interface ProductCatagoryService {
	
	/**
	 * 取得排序最靠前的几个分类
	 * @return
	 */
	abstract List<ProductCatagoryPo> topCatagory(int top);
	
	/**
	 * 修改单个分类
	 * @return
	 */
	abstract int updateCatagory(ProductCatagoryPo productCatagoryPo);
	
	/**
	 * 批量修改分类
	 * @param productCatagoryPo
	 * @return
	 */
	abstract int updateCatagorys(List<ProductCatagoryPo> productCatagoryPoList);
	
	/**
	 * 增加产品分类
	 * @param productCatagoryPo
	 * @return
	 */
	abstract int addCatagory(ProductCatagoryPo productCatagoryPo);
	
	/**
	 * 取得分类名称
	 * @param catagoryId
	 * @return
	 */
	abstract String getCatagoryName(String catagoryId);
	
}
