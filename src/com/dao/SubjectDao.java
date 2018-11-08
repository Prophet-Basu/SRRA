package com.dao;

import java.util.ArrayList;
import com.bean.SubjectBean;

public interface SubjectDao {
	public boolean addSubject(SubjectBean subBean);
	public boolean deleteSubject(String subCode);
	public boolean updateSubject(SubjectBean sb);
	public ArrayList<SubjectBean> fetchAllStreamSubjects(int strID,int subsem);
	public ArrayList<SubjectBean> fetchAllStreamSubjects(int strID);
	public ArrayList<SubjectBean> fetchAllSubjects();
	public boolean checkSubjectCode(String subCode);
	public SubjectBean fetchSubject(int strID,String subCode);
}
