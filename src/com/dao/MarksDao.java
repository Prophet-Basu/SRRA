package com.dao;

import java.util.ArrayList;

import com.bean.MarksBean;

public interface MarksDao {

	public boolean createMarksTable(int strID,String sesID);
	public boolean findMarksTable(int strID,String sesID);
	public boolean insertMarks(MarksBean mb,int strID,String sesID);
	public boolean updateSemMarks(MarksBean mb,int strID,String sesID);
	public boolean updateInternalMarks(MarksBean mb,int strID,String sesID);
	public ArrayList<MarksBean> findStudentParticularSemesterMarks(int strID,String sesID,long studRoll,int semNo);
	public int findStudentParticularSubjectMarks(int strID,String sesID,long studRoll,int semNo,String subCode);
	public int findCountOfStudent(int strID,String sesID,String sesSecname,String subCode,String semGrade);
}
