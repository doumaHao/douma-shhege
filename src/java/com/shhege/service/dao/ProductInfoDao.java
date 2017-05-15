package com.shhege.service.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.shhege.madel.filter.ProductInfoFilter;
import com.shhege.madel.po.ProductInfoPo;

public interface ProductInfoDao {
	
	/**
	 * 根据查看种类下前多少个商品
	 * @param top
	 * @param catagoryId
	 * @return
	 */
	public List<ProductInfoPo> topGoodsByCatagoty(@Param("top") int top, @Param("catagoryId") String catagoryId);
	
	/**
	 * 查询所有产品信息的总记录条数
	 * @param start
	 * @param count
	 * @return
	 */
	public int countOfProductInfo(@Param("productInfoFilter") ProductInfoFilter productInfoFilter);
	
	/**
	 * 根据分页信息查询所有产品
	 * @param start
	 * @param count
	 * @return
	 */
	public List<ProductInfoPo> findProductInfoByPage(@Param("productInfoFilter") ProductInfoFilter productInfoFilter, @Param("start") int start, @Param("count") int count);
	
	/**
	 * 根据id查询产品信息
	 * @param id
	 * @return
	 */
	public ProductInfoPo findProductInfoById(@Param("id") String id);
	
	/**
	 * 根据id更新产品信息
	 * @param productInfoPo
	 * @return
	 */
	public int updateProductInfoById(@Param("productInfoPo") ProductInfoPo productInfoPo);
	
	/**
	 * 插入产品信息
	 * @param productInfoPo
	 * @return
	 */
	public int insertProductInfo(@Param("productInfoPo") ProductInfoPo productInfoPo);
	
	/**
	 * 删除产品
	 * @param id
	 * @return
	 */
	public int deleteProductInfo(@Param("id") String id);

}
