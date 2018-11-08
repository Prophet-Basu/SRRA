package com.bean;

import java.io.Serializable;

public class StudentAcademicDetailsBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long studRoll;
	private String studCls10SchoolName;
	private String studCls10SchoolMedium;
	private String studCls10BoardName;
	private String studCls10ExamName;
	private float studCls10AvgMarks;
	private float studCls10BestMarks;
	private int studCls10YOP;
	private String studCls12SchoolName;
	private String studCls12SchoolMedium;
	private String studCls12BoardName;
	private String studCls12ExamName;
	private float studCls12AvgMarks;
	private float studCls12BestMarks;
	private int studCls12YOP;
	private String studDiplomaBoardName;
	private String studDiplomaStream;
	private float studDiplomaMarks;
	private int studDiplomaYOP;
	private int studBTechSelectionRank;
	private String studBTechSelectionExam;
	private String studCollege;
	private String studUniversity;
	private int studPresentSem;
	private float studSem1SGPA;
	private float studSem2SGPA;
	private float studSem3SGPA;
	private float studSem4SGPA;
	private float studSem5SGPA;
	private float studSem6SGPA;
	private float studSem7SGPA;
	private float studSem8SGPA;
	private float studYGPA1;
	private float studYGPA2;
	private float studYGPA3;
	private float studYGPA4;
	private float studDGPA;
	private String studHasBacklog;
	private String studBacklog1;
	private String studBacklog2;
	private String studBacklog3;
	private String studHasYearGap;
	private String studYearGapDuration;
	private String studYearGapPeriod;
	private String studYearGapCause;
	private String studAchievement;
	private String sesSecName;
	public StudentAcademicDetailsBean(long studRoll, String studCls10SchoolName, String studCls10SchoolMedium,
			String studCls10BoardName, String studCls10ExamName, float studCls10AvgMarks, float studCls10BestMarks,
			int studCls10YOP, String studCls12SchoolName, String studCls12SchoolMedium, String studCls12BoardName,
			String studCls12ExamName, float studCls12AvgMarks, float studCls12BestMarks, int studCls12YOP,
			String studDiplomaBoardName, String studDiplomaStream, float studDiplomaMarks, int studDiplomaYOP,
			int studBTechSelectionRank, String studBTechSelectionExam, String studCollege, String studUniversity,
			int studPresentSem, float studSem1SGPA, float studSem2SGPA, float studSem3SGPA, float studSem4SGPA,
			float studSem5SGPA, float studSem6SGPA, float studSem7SGPA, float studSem8SGPA, float studYGPA1,
			float studYGPA2, float studYGPA3, float studYGPA4, float studDGPA, String studHasBacklog,
			String studBacklog1, String studBacklog2, String studBacklog3, String studHasYearGap,
			String studYearGapDuration, String studYearGapPeriod, String studYearGapCause, String studAchievement,
			String sesSecName) {
		super();
		this.studRoll = studRoll;
		this.studCls10SchoolName = studCls10SchoolName;
		this.studCls10SchoolMedium = studCls10SchoolMedium;
		this.studCls10BoardName = studCls10BoardName;
		this.studCls10ExamName = studCls10ExamName;
		this.studCls10AvgMarks = studCls10AvgMarks;
		this.studCls10BestMarks = studCls10BestMarks;
		this.studCls10YOP = studCls10YOP;
		this.studCls12SchoolName = studCls12SchoolName;
		this.studCls12SchoolMedium = studCls12SchoolMedium;
		this.studCls12BoardName = studCls12BoardName;
		this.studCls12ExamName = studCls12ExamName;
		this.studCls12AvgMarks = studCls12AvgMarks;
		this.studCls12BestMarks = studCls12BestMarks;
		this.studCls12YOP = studCls12YOP;
		this.studDiplomaBoardName = studDiplomaBoardName;
		this.studDiplomaStream = studDiplomaStream;
		this.studDiplomaMarks = studDiplomaMarks;
		this.studDiplomaYOP = studDiplomaYOP;
		this.studBTechSelectionRank = studBTechSelectionRank;
		this.studBTechSelectionExam = studBTechSelectionExam;
		this.studCollege = studCollege;
		this.studUniversity = studUniversity;
		this.studPresentSem = studPresentSem;
		this.studSem1SGPA = studSem1SGPA;
		this.studSem2SGPA = studSem2SGPA;
		this.studSem3SGPA = studSem3SGPA;
		this.studSem4SGPA = studSem4SGPA;
		this.studSem5SGPA = studSem5SGPA;
		this.studSem6SGPA = studSem6SGPA;
		this.studSem7SGPA = studSem7SGPA;
		this.studSem8SGPA = studSem8SGPA;
		this.studYGPA1 = studYGPA1;
		this.studYGPA2 = studYGPA2;
		this.studYGPA3 = studYGPA3;
		this.studYGPA4 = studYGPA4;
		this.studDGPA = studDGPA;
		this.studHasBacklog = studHasBacklog;
		this.studBacklog1 = studBacklog1;
		this.studBacklog2 = studBacklog2;
		this.studBacklog3 = studBacklog3;
		this.studHasYearGap = studHasYearGap;
		this.studYearGapDuration = studYearGapDuration;
		this.studYearGapPeriod = studYearGapPeriod;
		this.studYearGapCause = studYearGapCause;
		this.studAchievement = studAchievement;
		this.sesSecName = sesSecName;
	}
	public long getStudRoll() {
		return studRoll;
	}
	public void setStudRoll(long studRoll) {
		this.studRoll = studRoll;
	}
	public String getStudCls10SchoolName() {
		return studCls10SchoolName;
	}
	public void setStudCls10SchoolName(String studCls10SchoolName) {
		this.studCls10SchoolName = studCls10SchoolName;
	}
	public String getStudCls10SchoolMedium() {
		return studCls10SchoolMedium;
	}
	public void setStudCls10SchoolMedium(String studCls10SchoolMedium) {
		this.studCls10SchoolMedium = studCls10SchoolMedium;
	}
	public String getStudCls10BoardName() {
		return studCls10BoardName;
	}
	public void setStudCls10BoardName(String studCls10BoardName) {
		this.studCls10BoardName = studCls10BoardName;
	}
	public String getStudCls10ExamName() {
		return studCls10ExamName;
	}
	public void setStudCls10ExamName(String studCls10ExamName) {
		this.studCls10ExamName = studCls10ExamName;
	}
	public float getStudCls10AvgMarks() {
		return studCls10AvgMarks;
	}
	public void setStudCls10AvgMarks(float studCls10AvgMarks) {
		this.studCls10AvgMarks = studCls10AvgMarks;
	}
	public float getStudCls10BestMarks() {
		return studCls10BestMarks;
	}
	public void setStudCls10BestMarks(float studCls10BestMarks) {
		this.studCls10BestMarks = studCls10BestMarks;
	}
	public int getStudCls10YOP() {
		return studCls10YOP;
	}
	public void setStudCls10YOP(int studCls10YOP) {
		this.studCls10YOP = studCls10YOP;
	}
	public String getStudCls12SchoolName() {
		return studCls12SchoolName;
	}
	public void setStudCls12SchoolName(String studCls12SchoolName) {
		this.studCls12SchoolName = studCls12SchoolName;
	}
	public String getStudCls12SchoolMedium() {
		return studCls12SchoolMedium;
	}
	public void setStudCls12SchoolMedium(String studCls12SchoolMedium) {
		this.studCls12SchoolMedium = studCls12SchoolMedium;
	}
	public String getStudCls12BoardName() {
		return studCls12BoardName;
	}
	public void setStudCls12BoardName(String studCls12BoardName) {
		this.studCls12BoardName = studCls12BoardName;
	}
	public String getStudCls12ExamName() {
		return studCls12ExamName;
	}
	public void setStudCls12ExamName(String studCls12ExamName) {
		this.studCls12ExamName = studCls12ExamName;
	}
	public float getStudCls12AvgMarks() {
		return studCls12AvgMarks;
	}
	public void setStudCls12AvgMarks(float studCls12AvgMarks) {
		this.studCls12AvgMarks = studCls12AvgMarks;
	}
	public float getStudCls12BestMarks() {
		return studCls12BestMarks;
	}
	public void setStudCls12BestMarks(float studCls12BestMarks) {
		this.studCls12BestMarks = studCls12BestMarks;
	}
	public int getStudCls12YOP() {
		return studCls12YOP;
	}
	public void setStudCls12YOP(int studCls12YOP) {
		this.studCls12YOP = studCls12YOP;
	}
	public String getStudDiplomaBoardName() {
		return studDiplomaBoardName;
	}
	public void setStudDiplomaBoardName(String studDiplomaBoardName) {
		this.studDiplomaBoardName = studDiplomaBoardName;
	}
	public String getStudDiplomaStream() {
		return studDiplomaStream;
	}
	public void setStudDiplomaStream(String studDiplomaStream) {
		this.studDiplomaStream = studDiplomaStream;
	}
	public float getStudDiplomaMarks() {
		return studDiplomaMarks;
	}
	public void setStudDiplomaMarks(float studDiplomaMarks) {
		this.studDiplomaMarks = studDiplomaMarks;
	}
	public int getStudDiplomaYOP() {
		return studDiplomaYOP;
	}
	public void setStudDiplomaYOP(int studDiplomaYOP) {
		this.studDiplomaYOP = studDiplomaYOP;
	}
	public int getStudBTechSelectionRank() {
		return studBTechSelectionRank;
	}
	public void setStudBTechSelectionRank(int studBTechSelectionRank) {
		this.studBTechSelectionRank = studBTechSelectionRank;
	}
	public String getStudBTechSelectionExam() {
		return studBTechSelectionExam;
	}
	public void setStudBTechSelectionExam(String studBTechSelectionExam) {
		this.studBTechSelectionExam = studBTechSelectionExam;
	}
	public String getStudCollege() {
		return studCollege;
	}
	public void setStudCollege(String studCollege) {
		this.studCollege = studCollege;
	}
	public String getStudUniversity() {
		return studUniversity;
	}
	public void setStudUniversity(String studUniversity) {
		this.studUniversity = studUniversity;
	}
	public int getStudPresentSem() {
		return studPresentSem;
	}
	public void setStudPresentSem(int studPresentSem) {
		this.studPresentSem = studPresentSem;
	}
	public float getStudSem1SGPA() {
		return studSem1SGPA;
	}
	public void setStudSem1SGPA(float studSem1SGPA) {
		this.studSem1SGPA = studSem1SGPA;
	}
	public float getStudSem2SGPA() {
		return studSem2SGPA;
	}
	public void setStudSem2SGPA(float studSem2SGPA) {
		this.studSem2SGPA = studSem2SGPA;
	}
	public float getStudSem3SGPA() {
		return studSem3SGPA;
	}
	public void setStudSem3SGPA(float studSem3SGPA) {
		this.studSem3SGPA = studSem3SGPA;
	}
	public float getStudSem4SGPA() {
		return studSem4SGPA;
	}
	public void setStudSem4SGPA(float studSem4SGPA) {
		this.studSem4SGPA = studSem4SGPA;
	}
	public float getStudSem5SGPA() {
		return studSem5SGPA;
	}
	public void setStudSem5SGPA(float studSem5SGPA) {
		this.studSem5SGPA = studSem5SGPA;
	}
	public float getStudSem6SGPA() {
		return studSem6SGPA;
	}
	public void setStudSem6SGPA(float studSem6SGPA) {
		this.studSem6SGPA = studSem6SGPA;
	}
	public float getStudSem7SGPA() {
		return studSem7SGPA;
	}
	public void setStudSem7SGPA(float studSem7SGPA) {
		this.studSem7SGPA = studSem7SGPA;
	}
	public float getStudSem8SGPA() {
		return studSem8SGPA;
	}
	public void setStudSem8SGPA(float studSem8SGPA) {
		this.studSem8SGPA = studSem8SGPA;
	}
	public float getStudYGPA1() {
		return studYGPA1;
	}
	public void setStudYGPA1(float studYGPA1) {
		this.studYGPA1 = studYGPA1;
	}
	public float getStudYGPA2() {
		return studYGPA2;
	}
	public void setStudYGPA2(float studYGPA2) {
		this.studYGPA2 = studYGPA2;
	}
	public float getStudYGPA3() {
		return studYGPA3;
	}
	public void setStudYGPA3(float studYGPA3) {
		this.studYGPA3 = studYGPA3;
	}
	public float getStudYGPA4() {
		return studYGPA4;
	}
	public void setStudYGPA4(float studYGPA4) {
		this.studYGPA4 = studYGPA4;
	}
	public float getStudDGPA() {
		return studDGPA;
	}
	public void setStudDGPA(float studDGPA) {
		this.studDGPA = studDGPA;
	}
	public String getStudHasBacklog() {
		return studHasBacklog;
	}
	public void setStudHasBacklog(String studHasBacklog) {
		this.studHasBacklog = studHasBacklog;
	}
	public String getStudBacklog1() {
		return studBacklog1;
	}
	public void setStudBacklog1(String studBacklog1) {
		this.studBacklog1 = studBacklog1;
	}
	public String getStudBacklog2() {
		return studBacklog2;
	}
	public void setStudBacklog2(String studBacklog2) {
		this.studBacklog2 = studBacklog2;
	}
	public String getStudBacklog3() {
		return studBacklog3;
	}
	public void setStudBacklog3(String studBacklog3) {
		this.studBacklog3 = studBacklog3;
	}
	public String getStudHasYearGap() {
		return studHasYearGap;
	}
	public void setStudHasYearGap(String studHasYearGap) {
		this.studHasYearGap = studHasYearGap;
	}
	public String getStudYearGapDuration() {
		return studYearGapDuration;
	}
	public void setStudYearGapDuration(String studYearGapDuration) {
		this.studYearGapDuration = studYearGapDuration;
	}
	public String getStudYearGapPeriod() {
		return studYearGapPeriod;
	}
	public void setStudYearGapPeriod(String studYearGapPeriod) {
		this.studYearGapPeriod = studYearGapPeriod;
	}
	public String getStudYearGapCause() {
		return studYearGapCause;
	}
	public void setStudYearGapCause(String studYearGapCause) {
		this.studYearGapCause = studYearGapCause;
	}
	public String getStudAchievement() {
		return studAchievement;
	}
	public void setStudAchievement(String studAchievement) {
		this.studAchievement = studAchievement;
	}
	public String getSesSecName() {
		return sesSecName;
	}
	public void setSesSecName(String sesSecName) {
		this.sesSecName = sesSecName;
	}
	
	
		
	
	
}
