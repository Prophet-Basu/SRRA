package com.bean;

import java.io.Serializable;

public class TeacherBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int teacherID;
	private String teacherName;
	private String teacherAddress;
	private String teacherDOB;
	private long teacherMob;
	private String teacherEmail;
	private String teacherRole;
	private String teacherDesignation;
	private String teacherPassword;
	private int approve;
	
	public TeacherBean(int teacherID, String teacherName, String teacherAddress, String teacherDOB, long teacherMob,
			String teacherEmail, String teacherRole, String teacherDesignation, String teacherPassword, int approve) {
		super();
		this.teacherID = teacherID;
		this.teacherName = teacherName;
		this.teacherAddress = teacherAddress;
		this.teacherDOB = teacherDOB;
		this.teacherMob = teacherMob;
		this.teacherEmail = teacherEmail;
		this.teacherRole = teacherRole;
		this.teacherDesignation = teacherDesignation;
		this.teacherPassword = teacherPassword;
		this.approve = approve;
	}
	
	public int getTeacherID() {
		return teacherID;
	}

	public void setTeacherID(int teacherID) {
		this.teacherID = teacherID;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getTeacherAddress() {
		return teacherAddress;
	}

	public void setTeacherAddress(String teacherAddress) {
		this.teacherAddress = teacherAddress;
	}

	public String getTeacherDOB() {
		return teacherDOB;
	}

	public void setTeacherDOB(String teacherDOB) {
		this.teacherDOB = teacherDOB;
	}

	public long getTeacherMob() {
		return teacherMob;
	}

	public void setTeacherMob(long teacherMob) {
		this.teacherMob = teacherMob;
	}

	public String getTeacherEmail() {
		return teacherEmail;
	}

	public void setTeacherEmail(String teacherEmail) {
		this.teacherEmail = teacherEmail;
	}

	public String getTeacherRole() {
		return teacherRole;
	}

	public void setTeacherRole(String teacherRole) {
		this.teacherRole = teacherRole;
	}

	public String getTeacherDesignation() {
		return teacherDesignation;
	}

	public void setTeacherDesignation(String teacherDesignation) {
		this.teacherDesignation = teacherDesignation;
	}

	public String getTeacherPassword() {
		return teacherPassword;
	}

	public void setTeacherPassword(String teacherPassword) {
		this.teacherPassword = teacherPassword;
	}

	public int getApprove() {
		return approve;
	}

	public void setApprove(int approve) {
		this.approve = approve;
	}

	
	
	
}
