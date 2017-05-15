package com.shhege.service.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.shhege.madel.po.ProductCatagoryPo;

public interface ProductCatagoryDao {
	
	/**
	 * 查询前几条
	 * @return
	 */
	public List<ProductCatagoryPo> topCatagory(@Param("top") int top);
	
	/**
	 * 根据id更新产品分类
	 * @param productCatagoryPo
	 * @return
	 */
	public int updateProductCatagoryById(@Param("productCatagoryPo") ProductCatagoryPo productCatagoryPo);
	
	/**
	 * 插入产品分类
	 * @param productCatagoryPo
	 * @return
	 */
	public int insertProductCatagory(@Param("productCatagoryPo") ProductCatagoryPo productCatagoryPo);
	
	/**
	 * 根据分类id取得分类名称
	 * @param id
	 * @return
	 */
	public String findNameById(@Param("id") String id);
	
}
