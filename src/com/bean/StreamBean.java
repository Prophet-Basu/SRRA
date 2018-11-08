package com.bean;

import java.io.Serializable;

public class StreamBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int strID;
	private String strName;
	private int strNoOfSem;
	
	public StreamBean(int strID, String strName, int strNoOfSem) {
		super();
		this.strID = strID;
		this.strName = strName;
		this.strNoOfSem = strNoOfSem;
	}

	public int getStrID() {
		return strID;
	}

	public void setStrID(int strID) {
		this.strID = strID;
	}

	public String getStrName() {
		return strName;
	}

	public void setStrName(String strName) {
		this.strName = strName;
	}

	public int getStrNoOfSem() {
		return strNoOfSem;
	}

	public void setStrNoOfSem(int strNoOfSem) {
		this.strNoOfSem = strNoOfSem;
	}
}
