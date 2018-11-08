package com.bean;

import java.io.Serializable;

public class MarksBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String subCode;
	private long studRoll;
	private String sesSecName;
	private int semNo;
	private int marksSem;
	private String marksGrade;
	private int marksInternal1;
	private int marksInternal2;
	private int marksClsPerf;
	
	public MarksBean(String subCode, long studRoll, String sesSecName, int semNo, int marksSem, String marksGrade,
			int marksInternal1, int marksInternal2, int marksClsPerf) {
		super();
		this.subCode = subCode;
		this.studRoll = studRoll;
		this.sesSecName = sesSecName;
		this.semNo = semNo;
		this.marksSem = marksSem;
		this.marksGrade = marksGrade;
		this.marksInternal1 = marksInternal1;
		this.marksInternal2 = marksInternal2;
		this.marksClsPerf = marksClsPerf;
	}

	public String getSubCode() {
		return subCode;
	}

	public void setSubCode(String subCode) {
		this.subCode = subCode;
	}

	public long getStudRoll() {
		return studRoll;
	}

	public void setStudRoll(long studRoll) {
		this.studRoll = studRoll;
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

	public int getMarksSem() {
		return marksSem;
	}

	public void setMarksSem(int marksSem) {
		this.marksSem = marksSem;
	}

	public String getMarksGrade() {
		return marksGrade;
	}

	public void setMarksGrade(String marksGrade) {
		this.marksGrade = marksGrade;
	}

	public int getMarksInternal1() {
		return marksInternal1;
	}

	public void setMarksInternal1(int marksInternal1) {
		this.marksInternal1 = marksInternal1;
	}

	public int getMarksInternal2() {
		return marksInternal2;
	}

	public void setMarksInternal2(int marksInternal2) {
		this.marksInternal2 = marksInternal2;
	}

	public int getMarksClsPerf() {
		return marksClsPerf;
	}

	public void setMarksClsPerf(int marksClsPerf) {
		this.marksClsPerf = marksClsPerf;
	}
	
	
		
	
}
