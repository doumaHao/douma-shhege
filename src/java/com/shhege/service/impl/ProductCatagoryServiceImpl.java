package com.shhege.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shhege.core.util.id.IdGen;
import com.shhege.madel.po.ProductCatagoryPo;
import com.shhege.service.ProductCatagoryService;
import com.shhege.service.dao.ProductCatagoryDao;

@Service("productCatagoryService")
public class ProductCatagoryServiceImpl implements ProductCatagoryService {
	
	@Autowired
	private ProductCatagoryDao productCatagoryDao;

	@Override
	public List<ProductCatagoryPo> topCatagory(int top) {
		return productCatagoryDao.topCatagory(top);
	}

	@Override
	public int updateCatagory(ProductCatagoryPo productCatagoryPo) {
		if(productCatagoryPo == null || StringUtils.isBlank(productCatagoryPo.getId())){
			return 0;
		} else {
			return productCatagoryDao.updateProductCatagoryById(productCatagoryPo);
		}
	}

	@Override
	public int updateCatagorys(List<ProductCatagoryPo> productCatagoryPoList) {
		if(productCatagoryPoList != null 
				&& productCatagoryPoList.size() > 0){
			int i = 0;
			for(ProductCatagoryPo po: productCatagoryPoList){
				i += updateCatagory(po);
			}
			if(i == productCatagoryPoList.size()){
				return i;
			} else {
				throw new IllegalArgumentException("更新异常，记录数目不一致");
			}
		} else {
			return 0;
		}
	}

	@Override
	public int addCatagory(ProductCatagoryPo productCatagoryPo) {
		productCatagoryPo.setId(IdGen.uuid());
		return productCatagoryDao.insertProductCatagory(productCatagoryPo);
	}

	@Override
	public String getCatagoryName(String catagoryId) {
		return productCatagoryDao.findNameById(catagoryId);
	}
	

}
