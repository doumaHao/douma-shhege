package com.shhege.madel.filter;

import java.io.Serializable;

public class ProductInfoFilter implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String catagoryId;
		
	private String productName;

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
	
}
