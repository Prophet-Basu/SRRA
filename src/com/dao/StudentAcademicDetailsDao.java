package com.dao;

import java.util.ArrayList;

import com.bean.StudentAcademicDetailsBean;


public interface StudentAcademicDetailsDao {
	public boolean addStudentAcademicDetails(int strID,String sesID,long roll);
	public boolean findStudentByRoll(int strID,String sesID,long roll);
	public boolean insertStudentAcademicDetails(int strID,String sesID,StudentAcademicDetailsBean sabean);
	public boolean updateStudentAcademicDetails(int strID,String sesID,StudentAcademicDetailsBean sabean);
	public String fetchStudentSection(int strID,String sesID,long roll);
	public boolean findStudentAcademicTable(int strID,String sesID);
	public boolean updateEducationDetails(int strID,String sesID,long roll,String cls10SchoolName,String cls10SchoolMedium,String cls10BoardName,String cls10Examname,float cls10AvgMarks,
									float cls10Bestmarks,int cls10YOP,String cls12SchoolName,String cls12SchoolMedium,String cls12BoardName,String cls12Examname,float cls12AvgMarks,
									float cls12Bestmarks,int cls12YOP,String diplBoardName,String diplStream,float diplMarks,int diplYOP);

	public boolean updateBTechDetails(long roll, int selectionRank, String selectionExam, String college,
			String university, int presentSem, float sem1, float sem2, float sem3, float sem4, float sem5, float sem6,
			float sem7, float sem8, String hasBackLog, String backLog1, String backLog2, String backLog3,
			String hasYearGap, String yearGapDuration, String yearGapPeriod, String yearGapCause, String achievement,
			int strID, String sesID);
	
	public boolean updateSemDetails(long roll,int strID,String sesID,String sesSecName,String attrName,float attrValue);
	
	public StudentAcademicDetailsBean fetchStudent(int strID,String sesID,long roll);
	
	public ArrayList<StudentAcademicDetailsBean> fetchDiplomaStudents(int strID,String sesID);
	public ArrayList<Long> fetchStudentRoll(int strID,String sesID,String sesSecName);
	public ArrayList<String> getAllParameterList(int strID, String sesID);
}
