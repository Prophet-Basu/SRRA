package com.dao;

import java.util.ArrayList;

import com.bean.StudentPersonalDetailsBean;

public interface StudentPersonalDao {

	public boolean insertStudentPersonalDetails(int strID,String sesID,StudentPersonalDetailsBean spbean);
	public boolean updateStudentPersonalDetails(int strID,String sesID,StudentPersonalDetailsBean spbean);
	public boolean findStudentByRoll(int strID,String sesID,long roll);
	public long addStudent(int strID,String sesID);
	public StudentPersonalDetailsBean fetchStudentDetails(int strID,String sesID,long roll);
	public boolean findStudentTable(int strID,String sesID);
	public long updateGeneralInfo(int strID,String sesID,String name,String fname,String lname,String gender,String dob,
									String landLineCode,String landLine,long mob1,long mob2,String email,String bloodgrp);
	
	public boolean updatePersonalInfo(int strID,String sesID,long roll,String fatherName,String fatherService,String motherName,String motherService,long mob1,
			long mob2,String guardianName,String guardianRelation,String guardianService,long guardianMob);

	public boolean updateAddressInfo(int strID,String sesID,long roll,String presentAddress,String presentCity,String presentPinCode,String presentPostOffice,String presentDistrict,
			String presentState,String permanentAddress,String permanentCity,String permanentPinCode,String permanentPostOffice,String permanentDistrict,
			String permanentState);
	
	public boolean updateStreamAndSession(long roll,int strID,String sesID); 
	
	public ArrayList<StudentPersonalDetailsBean> fetchAllStudent(int strID,String sesID);

	public ArrayList<StudentPersonalDetailsBean> fetchAllStudent(int strID,String sesID,String sesSecName);
	public ArrayList<Long> fetchStudentRoll(int strID,String sesID,String sesSecName);
	public ArrayList<String> getAllParameterList(int strID, String sesID);
}
