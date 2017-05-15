package com.shhege.madel.po;

import java.io.Serializable;

public class ProductInfoPo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private int productSort;

	private String catagoryId;
	
	private String productName;
	
	private String productImg;
	
	private String productDes;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public int getProductSort() {
		return productSort;
	}

	public void setProductSort(int productSort) {
		this.productSort = productSort;
	}

	public String getCatagoryId() {
		return catagoryId;
	}

	public void setCatagoryId(String catagoryId) {
		this.catagoryId = catagoryId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductImg() {
		return productImg;
	}

	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}

	public String getProductDes() {
		return productDes;
	}

	public void setProductDes(String productDes) {
		this.productDes = productDes;
	}
	
}
