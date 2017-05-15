package com.shhege.service;

import java.util.List;

import com.shhege.madel.filter.ProductInfoFilter;
import com.shhege.madel.page.Page;
import com.shhege.madel.po.ProductInfoPo;


public interface ProductInfoService {
	
	/**
	 * 分类前多少个
	 * @param top
	 * @param catagoryId
	 * @return
	 */
	abstract List<ProductInfoPo> topGoodsByCatagoty(int top, String catagoryId);
	
	/**
	 * 产品总个数
	 * @return
	 */
	abstract public int countOfProduct(ProductInfoFilter productInfoFilter);
	
	/**
	 * 分页查询产品
	 * @param productInfoFilter
	 * @return
	 */
	abstract public Page<ProductInfoPo> queryProductByPage(ProductInfoFilter productInfoFilter, int pageIndex, int count);
	
	
	/**
	 * 随机在首页显示几个产品
	 * @param random
	 * @return
	 */
	abstract public List<ProductInfoPo> queryProductByRandom(int random);
	
	/**
	 * 根据id查询产品详情
	 * @param id
	 * @return
	 */
	abstract public ProductInfoPo queryProductById(String id);
	
	/**
	 * 增加一个产品
	 * @param contactInfoPo
	 * @return
	 */
	abstract public int addProduct(ProductInfoPo productInfoPo);
	
	/**
	 * 修改产品
	 * @param contactInfoPo
	 * @return
	 */
	abstract public int updateProduct(ProductInfoPo productInfoPo);
	
	/**
	 * 删除产品
	 * @param id
	 * @return
	 */
	abstract public int deleteProduct(String id);
}
