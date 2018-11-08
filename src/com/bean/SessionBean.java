package com.bean;

import java.io.Serializable;

public class SessionBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sesID;
	private int strID;
	private String sesSecName;
	private String sesStartDate;
	private String sesEndDate;

	public SessionBean(String sesID, int strID, String sesStartDate, String sesSecName, String sesEndDate) {
		super();
		this.sesID = sesID;
		this.strID = strID;
		this.sesSecName = sesSecName;
		this.sesStartDate = sesStartDate;
		this.sesEndDate = sesEndDate;
	}

	public int getStrID() {
		return strID;
	}

	public void setStrID(int strID) {
		this.strID = strID;
	}

	public String getSesSecName() {
		return sesSecName;
	}

	public void setSesSecName(String sesSecName) {
		this.sesSecName = sesSecName;
	}

	public String getSesID() {
		return sesID;
	}

	public void setSesID(String sesID) {
		this.sesID = sesID;
	}

	public String getSesStartDate() {
		return sesStartDate;
	}

	public void setSesStartDate(String sesStartDate) {
		this.sesStartDate = sesStartDate;
	}

	public String getSesEndDate() {
		return sesEndDate;
	}

	public void setSesEndDate(String sesEndDate) {
		this.sesEndDate = sesEndDate;
	}
}
