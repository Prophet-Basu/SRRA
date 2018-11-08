package com.bean;

import java.io.Serializable;

public class TeachesBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int strID;
	private String subCode;
	private String sesID;
	private String sesSecName;
	private int teacherID;
	private int verified;
	
	public TeachesBean(int strID, String subCode,String sesID, String sesSecName, int teacherID, int verified) {
		super();
		this.strID = strID;
		this.subCode = subCode;
		this.sesID = sesID;
		this.sesSecName=sesSecName;
		this.teacherID = teacherID;
		this.verified=verified;
	}

	public int getVerified() {
		return verified;
	}

	public void setVerified(int verified) {
		this.verified = verified;
	}

	public String getSesSecName() {
		return sesSecName;
	}

	public void setSesSecName(String secName) {
		this.sesSecName = secName;
	}

	public String getSesID() {
		return sesID;
	}

	public void setSesID(String sesID) {
		this.sesID = sesID;
	}

	public int getStrID() {
		return strID;
	}

	public void setStrID(int strID) {
		this.strID = strID;
	}

	public String getSubCode() {
		return subCode;
	}

	public void setSubCode(String subCode) {
		this.subCode = subCode;
	}

	public int getTeacherID() {
		return teacherID;
	}

	public void setTeacherID(int teacherID) {
		this.teacherID = teacherID;
	}
	
}
