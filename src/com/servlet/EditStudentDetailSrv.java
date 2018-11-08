package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EditStudentDetailSrv
 */
@WebServlet("/EditStudentDetailSrv")
public class EditStudentDetailSrv extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public static class StudentParameters{
		public static final String REG="studReg";
		public static final String NAME="studName";
		public static final String FNAME="studFName";
		public static final String LNAME="studLName";
		public static final String PRESENTADDRESS="studPresentAddress";
		public static final String PRESENTADDRESSCITY="studPresentAddressCity";
		public static final String PRESENTADDRESSPIN="studPresentAddressPin";
		public static final String PRESENTADDRESSPOSTOFFICE="studPresentAddressPostOffice";
		public static final String PRESENTADDRESSDISTRICT="studPresentAddressDistrict";
		public static final String PRESENTADDRESSSTATE="studPresentAddressState";
		public static final String PERMANENTADDRESS="studPermanentAddress";
		public static final String PERMANENTADDRESSCITY="studPermanentAddressCity";
		public static final String PERMANENTADDRESSPIN="studPermanentAddressPin";
		public static final String PERMANENTADDRESSPOSTOFFICE="studPermanentAddressPostOffice";
		public static final String PERMANENTADDRESSDISTRICT="studPermanentAddressDistrict";
		public static final String PERMANENTADDRESSSTATE="studPermanentAddressState";
		public static final String DOB="studDOB";
		public static final String GENDER="studGender";
		public static final String BLOODGROUP="studBloodGroup";
		public static final String LANDLINECODE="studLandlineCode";
		public static final String LANDLINE="studLandline";
		public static final String MOB1="studMob1";
		public static final String MOB2="studMob2";
		public static final String EMAIL="studEmail";
		public static final String PRESENTSEM="studPresentSem";
		public static final String FATHERNAME="studFatherName";
		public static final String FATHERSERVICE="studFatherService";
		public static final String MOTHERNAME="studMotherName";
		public static final String MOTHERSERVICE="studMotherService";
		public static final String PARENTMOB1="studParentMob1";
		public static final String PARENTMOB2="studParentMob2";
		public static final String LOCALGUARDIANNAME="studLocalGuardianName";
		public static final String LOCALGUARDIANRELATION="studLocalGuardianRelation";
		public static final String LOCALGUARDIANSERVICE="studLocalGuardianService";
		public static final String LOCALGUARDIANMOB="studLocalGuardianMob";
		
		public static final String ROLL="studRoll";
		public static final String CLS10SCHOOLNAME="studCls10SchoolName";
		public static final String CLS10SCHOOLMEDIUM="studCls10SchoolMedium";
		public static final String CLS10BOARDNAME="studCls10BoardName";
		public static final String CLS10EXAMNAME="studCls10ExamName";
		public static final String CLS10AVGMARKS="studCls10AvgMarks";
		public static final String CLS10BESTMARKS="studCls10BestMarks";
		public static final String CLS10YOP="studCls10YOP";
		public static final String CLS12SCHOOLNAME="studCls12SchoolName";
		public static final String CLS12SCHOOLMEDIUM="studCls12SchoolMedium";
		public static final String CLS12BOARDNAME="studCls12BoardName";
		public static final String CLS12EXAMNAME="studCls12ExamName";
		public static final String CLS12AVGMARKS="studCls12AvgMarks";
		public static final String CLS12BESTMARKS="studCls12BestMarks";
		public static final String CLS12YOP="studCls12YOP";
		public static final String DIPLOMABOARDNAME="studDiplomaBoardName";
		public static final String DIPLOMASTREAM="studDiplomaStream";
		public static final String DIPLOMAMARKS="studDiplomaMarks";
		public static final String DIPLOMAYOP="studDiplomaYOP";
		public static final String BTECHSELECTIONRANK="studBTechSelectionRank";
		public static final String BTECHSELECTIONEXAM="studBTechSelectionExam";
		public static final String COLLEGE="studCollege";
		public static final String UNIVERSITY="studUniversity";
		public static final String HASBACKLOG="studHasBacklog";
		public static final String BACKLOG1="studBacklog1";
		public static final String BACKLOG2="studBacklog2";
		public static final String BACKLOG3="studBacklog3";
		public static final String HASYEARGAP="studHasYearGap";
		public static final String YEARGAPDURATION="studYearGapDuration";
		public static final String YEARGAPPERIOD="studYearGapPeriod";
		public static final String YEARGAPCAUSE="studYearGapCause";
		public static final String ACHIEVEMENT="studAchievement";
		public static final String STRID="strID";
		public static final String SESID="sesID";
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
