package com.dao;

import java.util.ArrayList;

import com.bean.AttendanceBean;

public interface AttendanceDao {
	
	public boolean createAttendanceTable(int strID,String sesID);
	public boolean findAttendanceTable(int strID,String sesID);
	public boolean insertAttendanceDetails(AttendanceBean abean,int strID,String sesID);
	public boolean insertSubjectAttendanceforDate(int strID,String sesID,AttendanceBean abean,String date,ArrayList<Boolean> attnList,int attnCount);
	public boolean updateSubjectAttendanceforDate(int strID,String sesID,AttendanceBean abean,String date,ArrayList<Boolean> attnList,int attnCount);
	public boolean deleteSubjectAttendanceforDate(int strID,String sesID,AttendanceBean abean,String date);
	public ArrayList<ArrayList<String>> fetchSubjectAttendance(int strID,String sesID,AttendanceBean abean);
	public ArrayList<ArrayList<String>> fetchStudentAttnPercentForSubjectsInSem(int strID,String sesID,String sesSecName,int semNo,long roll);
}
