package com.shhege.madel.po;

import java.io.Serializable;
import java.util.Date;

public class CustomerInfoPo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	
	private String customerName;

	private String customerTitle;

	private String customerTel;

	private String customerMob;

	private String customerMail;

	private String customerFox;

	private String customerCompany;

	private String companyLink;

	private String companyAddress;

	private String customerLv;

	private String customerState;
	
	private String customerNeed;
	
	private String costomerCost;
	
	private String costomerMark;
	
	private String costomerPlan;
	
	private Date planTime;
	
	private String costomerPrice;
	
	private Date priceTime;
	
	private String customerContract;
	
	private Date contractTime;

	private Date registerDate;

	private String registerId;

	private String deleteFlg;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public String getCustomerMail() {
		return customerMail;
	}

	public void setCustomerMail(String customerMail) {
		this.customerMail = customerMail;
	}

	public String getCustomerFox() {
		return customerFox;
	}

	public void setCustomerFox(String customerFox) {
		this.customerFox = customerFox;
	}

	public String getCustomerCompany() {
		return customerCompany;
	}

	public void setCustomerCompany(String customerCompany) {
		this.customerCompany = customerCompany;
	}

	public String getCompanyLink() {
		return companyLink;
	}

	public void setCompanyLink(String companyLink) {
		this.companyLink = companyLink;
	}

	public String getCompanyAddress() {
		return companyAddress;
	}

	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
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

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	public String getRegisterId() {
		return registerId;
	}

	public void setRegisterId(String registerId) {
		this.registerId = registerId;
	}

	public String getDeleteFlg() {
		return deleteFlg;
	}

	public void setDeleteFlg(String deleteFlg) {
		this.deleteFlg = deleteFlg;
	}

	public String getCustomerNeed() {
		return customerNeed;
	}

	public void setCustomerNeed(String customerNeed) {
		this.customerNeed = customerNeed;
	}

	public String getCostomerCost() {
		return costomerCost;
	}

	public void setCostomerCost(String costomerCost) {
		this.costomerCost = costomerCost;
	}

	public String getCostomerMark() {
		return costomerMark;
	}

	public void setCostomerMark(String costomerMark) {
		this.costomerMark = costomerMark;
	}

	public String getCostomerPlan() {
		return costomerPlan;
	}

	public void setCostomerPlan(String costomerPlan) {
		this.costomerPlan = costomerPlan;
	}

	public Date getPlanTime() {
		return planTime;
	}

	public void setPlanTime(Date planTime) {
		this.planTime = planTime;
	}

	public String getCostomerPrice() {
		return costomerPrice;
	}

	public void setCostomerPrice(String costomerPrice) {
		this.costomerPrice = costomerPrice;
	}

	public Date getPriceTime() {
		return priceTime;
	}

	public void setPriceTime(Date priceTime) {
		this.priceTime = priceTime;
	}

	public String getCustomerContract() {
		return customerContract;
	}

	public void setCustomerContract(String customerContract) {
		this.customerContract = customerContract;
	}

	public Date getContractTime() {
		return contractTime;
	}

	public void setContractTime(Date contractTime) {
		this.contractTime = contractTime;
	}
	
}
