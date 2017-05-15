package com.shhege.madel.filter;

import java.io.Serializable;
import java.util.Date;

public class ContactInfoFilter implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String contactName;
	
	private String contactTel;
	
	private String contactTitle;
	
	private Date contactTimeForm;
	
	private Date contactTimeTo;
	
	private String contactState;

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactTel() {
		return contactTel;
	}

	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}

	public String getContactTitle() {
		return contactTitle;
	}

	public void setContactTitle(String contactTitle) {
		this.contactTitle = contactTitle;
	}

	public Date getContactTimeForm() {
		return contactTimeForm;
	}

	public void setContactTimeForm(Date contactTimeForm) {
		this.contactTimeForm = contactTimeForm;
	}

	public Date getContactTimeTo() {
		return contactTimeTo;
	}

	public void setContactTimeTo(Date contactTimeTo) {
		this.contactTimeTo = contactTimeTo;
	}

	public String getContactState() {
		return contactState;
	}

	public void setContactState(String contactState) {
		this.contactState = contactState;
	}
}
