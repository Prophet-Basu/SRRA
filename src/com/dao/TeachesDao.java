package com.dao;

import java.util.ArrayList;

import com.bean.TeachesBean;

public interface TeachesDao {

	public boolean addTeachesDetails(TeachesBean tbean);
	public ArrayList<String> fetchTeacherSubjects(int teacherID);
	public ArrayList<TeachesBean> fetchNonVerifiedSubjects(int strID);
	public ArrayList<String> fetchTeacherSessionsTaught(int teacherID);
	public ArrayList<String> fetchTeacherSectionsTaught(int teacherID,String sesID);
	public ArrayList<String> fetchTeacherSubjectTaught(int teacherID,String sesID,String section);
	public ArrayList<TeachesBean> fetchParticularSubjectTeacher(String subCode,int strID);
	public boolean verifyTeachesSubject(int strID,String sesID,String secName);
	public boolean findTeachesTable();
	public boolean createTeachesTable();
	
}
