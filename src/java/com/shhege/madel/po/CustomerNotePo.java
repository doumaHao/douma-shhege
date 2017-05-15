package com.shhege.madel.po;

import java.io.Serializable;
import java.util.Date;

public class CustomerNotePo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	
	private String customerId;
	
	private String noteContent;
	
	private String noteMethod;
	
	private String noteType;
	
	private Date noteTime;
	
	private String registerId;
	
	private String noteFile;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getNoteContent() {
		return noteContent;
	}

	public void setNoteContent(String noteContent) {
		this.noteContent = noteContent;
	}

	public String getNoteMethod() {
		return noteMethod;
	}

	public void setNoteMethod(String noteMethod) {
		this.noteMethod = noteMethod;
	}

	public String getNoteType() {
		return noteType;
	}

	public void setNoteType(String noteType) {
		this.noteType = noteType;
	}

	public String getRegisterId() {
		return registerId;
	}

	public void setRegisterId(String registerId) {
		this.registerId = registerId;
	}

	public String getNoteFile() {
		return noteFile;
	}

	public void setNoteFile(String noteFile) {
		this.noteFile = noteFile;
	}

	public Date getNoteTime() {
		return noteTime;
	}

	public void setNoteTime(Date noteTime) {
		this.noteTime = noteTime;
	}
}
