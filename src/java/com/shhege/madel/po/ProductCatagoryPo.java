package com.shhege.madel.po;

import java.io.Serializable;

public class ProductCatagoryPo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private int catagorySort;
	
	private String parentId;
	
	private String catagoryLv;
	
	private String catagoryName;
	
	private String catagoryImg;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public int getCatagorySort() {
		return catagorySort;
	}

	public void setCatagorySort(int catagorySort) {
		this.catagorySort = catagorySort;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getCatagoryLv() {
		return catagoryLv;
	}

	public void setCatagoryLv(String catagoryLv) {
		this.catagoryLv = catagoryLv;
	}

	public String getCatagoryName() {
		return catagoryName;
	}

	public void setCatagoryName(String catagoryName) {
		this.catagoryName = catagoryName;
	}

	public String getCatagoryImg() {
		return catagoryImg;
	}

	public void setCatagoryImg(String catagoryImg) {
		this.catagoryImg = catagoryImg;
	}
}
