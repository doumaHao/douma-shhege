package com.shhege.madel.vo;

public class ProductVo {
	
	private String id;
	
	private int sort;

	private String catagoryId;
	
	private String catagory;
	
	private String name;
	
	private String imgId;
	
	private String imgUrl;
	
	private String desId;
	
	private String desText;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public String getCatagory() {
		return catagory;
	}

	public void setCatagory(String catagory) {
		this.catagory = catagory;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImgId() {
		return imgId;
	}

	public void setImgId(String imgId) {
		this.imgId = imgId;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getDesId() {
		return desId;
	}

	public void setDesId(String desId) {
		this.desId = desId;
	}

	public String getDesText() {
		return desText;
	}

	public void setDesText(String desText) {
		this.desText = desText;
	}

	public String getCatagoryId() {
		return catagoryId;
	}

	public void setCatagoryId(String catagoryId) {
		this.catagoryId = catagoryId;
	}
}
