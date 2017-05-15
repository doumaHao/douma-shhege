package com.shhege.madel.filter;

import java.io.Serializable;

public class CustomerInfoFilter implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String customerName;
	
	private String customerTitle;
	
	private String customerTel;
	
	private String customerMob;
	
	private String customerLv;
	
	private String customerState;

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerTitle() {
		return customerTitle;
	}

	public void setCustomerTitle(String customerTitle) {
		this.customerTitle = customerTitle;
	}

	public String getCustomerTel() {
		return customerTel;
	}

	public void setCustomerTel(String customerTel) {
		this.customerTel = customerTel;
	}

	public String getCustomerMob() {
		return customerMob;
	}

	public void setCustomerMob(String customerMob) {
		this.customerMob = customerMob;
	}

	public String getCustomerLv() {
		return customerLv;
	}

	public void setCustomerLv(String customerLv) {
		this.customerLv = customerLv;
	}

	public String getCustomerState() {
		return customerState;
	}

	public void setCustomerState(String customerState) {
		this.customerState = customerState;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
