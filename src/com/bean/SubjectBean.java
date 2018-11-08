package com.bean;

import java.io.Serializable;

public class SubjectBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String subCode;
	private String subName;
	private int subSem;
	private int subTaughtDuration;
	private int strID;
	
	public SubjectBean(String subCode, String subName, int subSem, int subTaughtDuration, int strID) {
		super();
		this.subCode = subCode;
		this.subName = subName;
		this.subSem = subSem;
		this.subTaughtDuration = subTaughtDuration;
		this.strID = strID;
	}

	public String getSubCode() {
		return subCode;
	}

	public void setSubCode(String subCode) {
		this.subCode = subCode;
	}

	public String getSubName() {
		return subName;
	}

	public void setSubName(String subName) {
		this.subName = subName;
	}

	public int getSubSem() {
		return subSem;
	}

	public void setSubSem(int subSem) {
		this.subSem = subSem;
	}

	public int getSubTaughtDuration() {
		return subTaughtDuration;
	}

	public void setSubTaughtDuration(int subTaughtDuration) {
		this.subTaughtDuration = subTaughtDuration;
	}

	public int getStrID() {
		return strID;
	}

	public void setStrID(int strID) {
		this.strID = strID;
	}
	
	@Override
	public String toString() {
		return "SubjectBean [subCode=" + subCode + ", subName=" + subName + ", subSem=" + subSem
				+ ", subTaughtDuration=" + subTaughtDuration + ", strID=" + strID + "]";
	}

}
