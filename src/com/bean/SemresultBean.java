package com.bean;

import java.io.Serializable;

public class SemresultBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long studRoll;
	private int semNo;
	private float semScore;
	
	public SemresultBean(long studRoll, int semNo, float semScore) {
		super();
		this.studRoll = studRoll;
		this.semNo = semNo;
		this.semScore = semScore;
	}

	public long getStudRoll() {
		return studRoll;
	}

	public void setStudRoll(long studRoll) {
		this.studRoll = studRoll;
	}

	public int getSemNo() {
		return semNo;
	}

	public void setSemNo(int semNo) {
		this.semNo = semNo;
	}

	public float getSemScore() {
		return semScore;
	}

	public void setSemScore(float semScore) {
		this.semScore = semScore;
	}
	
}
