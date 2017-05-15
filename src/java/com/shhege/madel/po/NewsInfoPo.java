package com.shhege.madel.po;

import java.io.Serializable;
import java.util.Date;

public class NewsInfoPo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	private String newsTitle;
	
	private String newsImg;
	
	private String newsContent;
	
	private Date publishTime;
	
	private String newsState;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNewsTitle() {
		return newsTitle;
	}

	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}

	public String getNewsContent() {
		return newsContent;
	}

	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public String getNewsState() {
		return newsState;
	}

	public void setNewsState(String newsState) {
		this.newsState = newsState;
	}

	public String getNewsImg() {
		return newsImg;
	}

	public void setNewsImg(String newsImg) {
		this.newsImg = newsImg;
	}
}
