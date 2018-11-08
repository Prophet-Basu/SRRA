package com.bean;

import java.io.Serializable;

public class AttendanceBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String subCode;
	//private long studRoll;
	private String sesSecName;
	private int semNo;
	private String attnTableName;
	private int attnTotal;
	
	public AttendanceBean(String subCode, String sesSecName, int semNo, String attnTableName, int attnTotal) {
		super();
		this.subCode = subCode;
		this.sesSecName = sesSecName;
		this.semNo = semNo;
		this.attnTableName = attnTableName;
		this.attnTotal = attnTotal;
	}

	public String getSubCode() {
		return subCode;
	}

	public void setSubCode(String subCode) {
		this.subCode = subCode;
	}

	public String getSesSecName() {
		return sesSecName;
	}

	public void setSesSecName(String sesSecName) {
		this.sesSecName = sesSecName;
	}

	public int getSemNo() {
		return semNo;
	}

	public void setSemNo(int semNo) {
		this.semNo = semNo;
	}

	public String getAttnTableName() {
		return attnTableName;
	}

	public void setAttnTableName(String attnTableName) {
		this.attnTableName = attnTableName;
	}

	public int getAttnTotal() {
		return attnTotal;
	}

	public void setAttnTotal(int attnTotal) {
		this.attnTotal = attnTotal;
	}
	
}
