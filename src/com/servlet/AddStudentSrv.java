package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.StudentPersonalDetailsBean;
import com.dao.StudentAcademicDetailsDao;
import com.dao.StudentAcademicDetailsDaoImpl;
import com.dao.StudentPersonalDao;
import com.dao.StudentPersonalDaoImpl;

/**
 * Servlet implementation class AddStudentSrv
 */
@WebServlet("/AddStudentSrv")
public class AddStudentSrv extends HttpServlet {
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
		
		HttpSession ses=request.getSession();
		int pageNo=Integer.parseInt(ses.getAttribute("PageNo").toString());
		
		if(pageNo==1){
			String name=request.getParameter(StudentParameters.NAME);
			String fName=request.getParameter(StudentParameters.FNAME);
			String lName=request.getParameter(StudentParameters.LNAME);
			String gender=request.getParameter(StudentParameters.GENDER);
			String dob=request.getParameter(StudentParameters.DOB);
			String bloodgrp=request.getParameter(StudentParameters.BLOODGROUP);
			String landlineCode=request.getParameter(StudentParameters.LANDLINECODE);
			String landline=request.getParameter(StudentParameters.LANDLINE);
			long mob1=Long.parseLong(request.getParameter(StudentParameters.MOB1));
			long mob2=Long.parseLong(request.getParameter(StudentParameters.MOB2));
			String email=request.getParameter(StudentParameters.EMAIL);
			int strID=Integer.parseInt(request.getParameter(StudentParameters.STRID));
			String sesID=request.getParameter(StudentParameters.SESID);
			
			StudentPersonalDao studDao=new StudentPersonalDaoImpl();
			long roll=studDao.updateGeneralInfo(strID,sesID,name, fName, lName, gender, dob, landlineCode, landline, mob1, mob2, email,bloodgrp);
			
			if(roll>0){
				ses.setAttribute("Roll", roll);
				response.sendRedirect("studenteducationinfo.jsp");
				
		}
		}
		else if(pageNo==2){
			System.out.println(request.getParameter(StudentParameters.CLS10BOARDNAME));
			boolean flag=false;
			String cls10SchoolName=request.getParameter(StudentParameters.CLS10SCHOOLNAME);
			String cls10SchoolMedium=request.getParameter(StudentParameters.CLS10SCHOOLMEDIUM);
			String cls10BoardName=request.getParameter(StudentParameters.CLS10BOARDNAME);
			String cls10ExamName=request.getParameter(StudentParameters.CLS10EXAMNAME);
			float cls10AvgMarks=Float.parseFloat(request.getParameter(StudentParameters.CLS10AVGMARKS));
			
			float cls10BestMarks=Float.parseFloat(request.getParameter(StudentParameters.CLS10BESTMARKS));
			int cls10YOP=Integer.parseInt(request.getParameter(StudentParameters.CLS10YOP));
			String cls12SchoolName=request.getParameter(StudentParameters.CLS12SCHOOLNAME);
			String cls12SchoolMedium=request.getParameter(StudentParameters.CLS12SCHOOLMEDIUM);
			String cls12BoardName=request.getParameter(StudentParameters.CLS12BOARDNAME);
			String cls12ExamName=request.getParameter(StudentParameters.CLS12EXAMNAME);
			float cls12AvgMarks=Float.parseFloat(request.getParameter(StudentParameters.CLS12AVGMARKS));
			float cls12BestMarks=Float.parseFloat(request.getParameter(StudentParameters.CLS12BESTMARKS));
			int cls12YOP=Integer.parseInt(request.getParameter(StudentParameters.CLS12YOP));
			
			
			String diplBoardName=request.getParameter(StudentParameters.DIPLOMABOARDNAME);
			String diplStream=request.getParameter(StudentParameters.DIPLOMASTREAM);
			float diplMarks=Float.parseFloat(request.getParameter(StudentParameters.DIPLOMAMARKS));
			int diplYOP=Integer.parseInt(request.getParameter(StudentParameters.DIPLOMAYOP));
			int strID=Integer.parseInt(request.getParameter(StudentParameters.STRID));
			String sesID=request.getParameter(StudentParameters.SESID);
			
			
			long roll=Long.parseLong(ses.getAttribute("Roll").toString());
			
			StudentAcademicDetailsDao studAcadDao=new StudentAcademicDetailsDaoImpl();
			flag=studAcadDao.updateEducationDetails(strID,sesID,roll, cls10SchoolName, cls10SchoolMedium, cls10BoardName, cls10ExamName, cls10AvgMarks, cls10BestMarks, cls10YOP, cls12SchoolName, cls12SchoolMedium, cls12BoardName, cls12ExamName, cls12AvgMarks, cls12BestMarks, cls12YOP, diplBoardName, diplStream, diplMarks, diplYOP);
		
			if(flag){
				response.sendRedirect("studentbtechdetails.jsp");
			}
		
		}
		
		
		else if(pageNo==4){
			String fatherName=request.getParameter(StudentParameters.FATHERNAME);
			String fatherService=request.getParameter(StudentParameters.FATHERSERVICE);
			String motherName=request.getParameter(StudentParameters.MOTHERNAME);
			String motherService=request.getParameter(StudentParameters.MOTHERSERVICE);
			long mob1=Long.parseLong(request.getParameter(StudentParameters.PARENTMOB1));
			long mob2=Long.parseLong(request.getParameter(StudentParameters.PARENTMOB2));
			String guardianName=request.getParameter(StudentParameters.LOCALGUARDIANNAME);
			String guardianRelation=request.getParameter(StudentParameters.LOCALGUARDIANRELATION);
			String guardianService=request.getParameter(StudentParameters.LOCALGUARDIANSERVICE);
			long guardianMob=Long.parseLong(request.getParameter(StudentParameters.LOCALGUARDIANMOB));
			
			long roll=Long.parseLong(ses.getAttribute("Roll").toString());
			int strID=Integer.parseInt(request.getParameter(StudentParameters.STRID));
			String sesID=request.getParameter(StudentParameters.SESID);
			
			StudentPersonalDao studDao=new StudentPersonalDaoImpl();
			boolean flag=studDao.updatePersonalInfo(strID,sesID,roll, fatherName, fatherService, motherName, motherService, mob1, mob2, guardianName, guardianRelation, guardianService, guardianMob);
			
			if(flag){
				response.sendRedirect("studentaddressdetails.jsp");
			}
		}
		
		
		else if(pageNo==5){
			
			String presentAddress=request.getParameter(StudentParameters.PRESENTADDRESS);
			String presentCity=request.getParameter(StudentParameters.PRESENTADDRESSCITY);
			String presentPinCode=request.getParameter(StudentParameters.PRESENTADDRESSPIN);
			String presentPostOffice=request.getParameter(StudentParameters.PRESENTADDRESSPOSTOFFICE);
			String presentDistrict=request.getParameter(StudentParameters.PRESENTADDRESSDISTRICT);
			String presentState=request.getParameter(StudentParameters.PRESENTADDRESSSTATE);
			String permanentAddress=request.getParameter(StudentParameters.PERMANENTADDRESS);
			String permanentCity=request.getParameter(StudentParameters.PERMANENTADDRESSCITY); 
			String permanentPinCode=request.getParameter(StudentParameters.PERMANENTADDRESSPIN); 
			String permanentPostOffice=request.getParameter(StudentParameters.PERMANENTADDRESSPOSTOFFICE);
			String permanentDistrict=request.getParameter(StudentParameters.PERMANENTADDRESSDISTRICT);
			String permanentState=request.getParameter(StudentParameters.PERMANENTADDRESSSTATE);
			int strID=Integer.parseInt(request.getParameter(StudentParameters.STRID));
			String sesID=request.getParameter(StudentParameters.SESID);
			
			
			long roll=Long.parseLong(ses.getAttribute("Roll").toString());
			
			StudentPersonalDao studDao=new StudentPersonalDaoImpl();
			boolean flag=studDao.updateAddressInfo(strID,sesID,roll, presentAddress, presentCity, presentPinCode, presentPostOffice, presentDistrict, presentState, permanentAddress, permanentCity, permanentPinCode, permanentPostOffice, permanentDistrict, permanentState);
			
			if(flag){
				System.out.print("SuccessAddress");
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
