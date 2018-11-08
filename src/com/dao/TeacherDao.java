package com.dao;

import java.util.ArrayList;

import com.bean.TeacherBean;

public interface TeacherDao {

	public int addTeacher(TeacherBean trBean,int strID);
	public boolean validateTeacherLogin(String teacherEmail,String teacherPassword,int strID);
	public boolean validateAdminLogin(String teacherEmail,String teacherPassword,int strID);
	public boolean findTeacherByEmail(String email,int strID);
	public ArrayList<TeacherBean> fetchAllTeacher(int strID);
	public ArrayList<TeacherBean> fetchAllTeacher(int strID,int teacherID);
	public TeacherBean fetchTeacher(String teacherEmail, String teacherPassword,int strID);
	public TeacherBean fetchTeacher(int teacherID,int strID);
	public ArrayList<TeacherBean> fetchNonVerifiedTeachers(int strID);
	public ArrayList<TeacherBean> fetchNonAdminTeachers(int strID);
	public boolean delTeacher(int teacherEmail,int strID);
	public boolean updateTeacher(TeacherBean trBean,int strID);
	public boolean updateTeacherPassword(int teacherID,int strID,String pass);
	public boolean createTeacherTable(int strID);
	public boolean approveTeacher(int teacherID,int strID);
	public boolean promoteTeacherRole(int teacherID,int strID);
	public ArrayList<String> getAllParameterList(int strID);
}
