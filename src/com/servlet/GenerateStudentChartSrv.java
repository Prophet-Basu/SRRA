package com.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.MarksBean;
import com.bean.StudentAcademicDetailsBean;
import com.bean.StudentPersonalDetailsBean;
import com.bean.SubjectBean;
import com.dao.AttendanceDao;
import com.dao.AttendanceDaoImpl;
import com.dao.MarksDao;
import com.dao.MarksDaoImpl;
import com.dao.StudentAcademicDetailsDao;
import com.dao.StudentAcademicDetailsDaoImpl;
import com.dao.StudentPersonalDao;
import com.dao.StudentPersonalDaoImpl;
import com.dao.SubjectDao;
import com.dao.SubjectDaoImpl;

/**
 * Servlet implementation class GenerateStudentChartSrv
 */
@WebServlet("/GenerateStudentChartSrv")
public class GenerateStudentChartSrv extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GenerateStudentChartSrv() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession ses=request.getSession();
		
		String param=request.getParameter("compareParameter");
		
		int strID=Integer.parseInt(ses.getAttribute("stream").toString());
		//String sesID=request.getParameter("sesSelect");
		
		
		String str="";
		String yaxis="";
		
		
		MarksDao marksdao=new MarksDaoImpl();
		SubjectDao subdao=new SubjectDaoImpl();
		StudentPersonalDao studentdao=new StudentPersonalDaoImpl();
		StudentAcademicDetailsDao studentAcademicDao=new StudentAcademicDetailsDaoImpl();
		AttendanceDao attendance=new AttendanceDaoImpl();
		
		if(param.equals("IndividualStudentSubjects")){
			
			String sesID=request.getParameter("sesSelect");
			long studRoll=Long.parseLong(request.getParameter("studSelect"));
			int semNo=Integer.parseInt(request.getParameter("semester"));
			System.out.println(studRoll);		
			
			yaxis="Subjects of Semester"+request.getParameter("semester");
			ArrayList<MarksBean> marks=marksdao.findStudentParticularSemesterMarks(strID, sesID, studRoll, semNo);
			
			for(MarksBean mb:marks){
				ArrayList<SubjectBean> subDetails=new ArrayList<SubjectBean>(); 
				subDetails=subdao.fetchAllStreamSubjects(strID, semNo);
				int index=-1;
				for(int i=0;i<subDetails.size();i++){
					if(subDetails.get(i).getSubCode().equals(mb.getSubCode())){
						index=i;
						break;
					}
				}
				str+="['"+subDetails.get(index).getSubName()+"',"+mb.getMarksSem() +"]"+",";
				
				
			}
			
		}
		
		else if(param.equals("MultipleStudentsSubject")){
			int semNo=Integer.parseInt(request.getParameter("semester"));
			String subCode=request.getParameter("subject");
			for(int i=1;i<=5;i++){
				String sesID=request.getParameter("sesSelect"+i);
				if(!(request.getParameter("studSelect"+i).equalsIgnoreCase("defaultvalue"))){
				long studRoll=Long.parseLong(request.getParameter("studSelect"+i));
				
				yaxis="Names of Student";
				int marks=marksdao.findStudentParticularSubjectMarks(strID, sesID, studRoll, semNo, subCode);
				StudentPersonalDetailsBean studentBean=studentdao.fetchStudentDetails(strID,sesID,studRoll);
				str+="['"+studentBean.getStudName()+"',"+marks +"]"+",";
				}
			}
		}
		
		else if(param.equals("Class10BoardBestMarks")){
			for(int i=1;i<=5;i++){
				String sesID=request.getParameter("sesSelect"+i);
				if(!(request.getParameter("studSelect"+i).equalsIgnoreCase("defaultvalue"))){
				long studRoll=Long.parseLong(request.getParameter("studSelect"+i));
				
				yaxis="Names of Student";
				StudentPersonalDetailsBean studentBean=studentdao.fetchStudentDetails(strID,sesID,studRoll);
				StudentAcademicDetailsBean academicBean=studentAcademicDao.fetchStudent(strID,sesID,studRoll);
				str+="['"+studentBean.getStudName()+"',"+academicBean.getStudCls10BestMarks() +"]"+",";
				}
			}
		}
		
		else if(param.equals("Class10BoardAvgMarks")){
			for(int i=1;i<=5;i++){
				String sesID=request.getParameter("sesSelect"+i);
				if(!(request.getParameter("studSelect"+i).equalsIgnoreCase("defaultvalue"))){
				long studRoll=Long.parseLong(request.getParameter("studSelect"+i));
				
				yaxis="Names of Student";
				StudentPersonalDetailsBean studentBean=studentdao.fetchStudentDetails(strID,sesID,studRoll);
				StudentAcademicDetailsBean academicBean=studentAcademicDao.fetchStudent(strID,sesID,studRoll);
				str+="['"+studentBean.getStudName()+"',"+academicBean.getStudCls10AvgMarks() +"]"+",";
				}
			}
		}
		
		else if(param.equals("Class12BoardBestMarks")){
			for(int i=1;i<=5;i++){
				String sesID=request.getParameter("nonDiplomaSesSelect"+i);
				if(!(request.getParameter("nonDiplomaStudSelect"+i).equalsIgnoreCase("defaultvalue"))){
				long studRoll=Long.parseLong(request.getParameter("nonDiplomaStudSelect"+i));
				
				yaxis="Names of Student";
				StudentPersonalDetailsBean studentBean=studentdao.fetchStudentDetails(strID,sesID,studRoll);
				StudentAcademicDetailsBean academicBean=studentAcademicDao.fetchStudent(strID,sesID,studRoll);
				str+="['"+studentBean.getStudName()+"',"+academicBean.getStudCls12BestMarks() +"]"+",";
				}
			}
		}
		
		else if(param.equals("Class12BoardAvgMarks")){
			for(int i=1;i<=5;i++){
				String sesID=request.getParameter("nonDiplomaSesSelect"+i);
				if(!(request.getParameter("nonDiplomaStudSelect"+i).equalsIgnoreCase("defaultvalue"))){
				long studRoll=Long.parseLong(request.getParameter("nonDiplomaStudSelect"+i));
				
				yaxis="Names of Student";
				StudentPersonalDetailsBean studentBean=studentdao.fetchStudentDetails(strID,sesID,studRoll);
				StudentAcademicDetailsBean academicBean=studentAcademicDao.fetchStudent(strID,sesID,studRoll);
				str+="['"+studentBean.getStudName()+"',"+academicBean.getStudCls12AvgMarks() +"]"+",";
				}
			}
		}
		
		else if(param.equals("DiplomaMarks")){
			for(int i=1;i<=5;i++){
				String sesID=request.getParameter("diplomasesSelect"+i);
				if(!(request.getParameter("diplomastudSelect"+i).equalsIgnoreCase("defaultvalue"))){
				long studRoll=Long.parseLong(request.getParameter("diplomastudSelect"+i));
				
				yaxis="Names of Student";
				StudentPersonalDetailsBean studentBean=studentdao.fetchStudentDetails(strID,sesID,studRoll);
				StudentAcademicDetailsBean academicBean=studentAcademicDao.fetchStudent(strID,sesID,studRoll);
				str+="['"+studentBean.getStudName()+"',"+academicBean.getStudDiplomaMarks()+"]"+",";
				}
			}
		}
		
		else if(param.equals("JELETSelectionRank")){
			for(int i=1;i<=5;i++){
				String sesID=request.getParameter("diplomasesSelect"+i);
				if(!(request.getParameter("diplomastudSelect"+i).equalsIgnoreCase("defaultvalue"))){
				long studRoll=Long.parseLong(request.getParameter("diplomastudSelect"+i));
				
				yaxis="Names of Student";
				StudentPersonalDetailsBean studentBean=studentdao.fetchStudentDetails(strID,sesID,studRoll);
				StudentAcademicDetailsBean academicBean=studentAcademicDao.fetchStudent(strID,sesID,studRoll);
				str+="['"+studentBean.getStudName()+"',"+academicBean.getStudBTechSelectionRank()+"]"+",";
				}
			}
		}
		
		else if(param.equals("WBJEESelectionRank")){
			for(int i=1;i<=5;i++){
				String sesID=request.getParameter("nonDiplomaSesSelect"+i);
				if(!(request.getParameter("nonDiplomaStudSelect"+i).equalsIgnoreCase("defaultvalue"))){
				long studRoll=Long.parseLong(request.getParameter("nonDiplomaStudSelect"+i));
				
				yaxis="Names of Student";
				StudentPersonalDetailsBean studentBean=studentdao.fetchStudentDetails(strID,sesID,studRoll);
				StudentAcademicDetailsBean academicBean=studentAcademicDao.fetchStudent(strID,sesID,studRoll);
				str+="['"+studentBean.getStudName()+"',"+academicBean.getStudBTechSelectionRank() +"]"+",";
				}
			}
		}
		
			if(param.equals("IndividualStudentSemesters")){
			
			String sesID=request.getParameter("sesSelect");
			long studRoll=Long.parseLong(request.getParameter("studSelect"));
			System.out.println(studRoll);		
			
			yaxis="SGPA of Semesters";
			StudentAcademicDetailsBean studentAcademic=studentAcademicDao.fetchStudent(strID, sesID, studRoll);
			int presentSem=studentAcademic.getStudPresentSem();
			
				for(int i=1;i<=presentSem-1;i++){
					float sgpa=0;
					if(i==1)
						sgpa=studentAcademic.getStudSem1SGPA();
					if(i==2)
						sgpa=studentAcademic.getStudSem2SGPA();
					if(i==3)
						sgpa=studentAcademic.getStudSem3SGPA();
					if(i==4)
						sgpa=studentAcademic.getStudSem4SGPA();
					if(i==5)
						sgpa=studentAcademic.getStudSem5SGPA();
					if(i==6)
						sgpa=studentAcademic.getStudSem6SGPA();
					if(i==7)
						sgpa=studentAcademic.getStudSem7SGPA();
					if(i==8)
						sgpa=studentAcademic.getStudSem8SGPA();
					str+="['"+"Semester"+i+"',"+sgpa+"]"+",";
				
				}
			}
			
			else if(param.equals("MultipleStudentsMultipleSemester")){
				
				yaxis="Names of Student";
				
				for(int sem=1;sem<=8;sem++){
				for(int i=1;i<=5;i++){
					String sesID=request.getParameter("sesSelect"+i);
					if(!(request.getParameter("studSelect"+i).equalsIgnoreCase("defaultvalue"))){
						long studRoll=Long.parseLong(request.getParameter("studSelect"+i));
						StudentAcademicDetailsBean studentAcademic=studentAcademicDao.fetchStudent(strID, sesID, studRoll);
						
							if(i!=1)
								str+=",";
								
							float sgpa=0;
							if(sem==1)
								sgpa=studentAcademic.getStudSem1SGPA();
							if(sem==2)
								sgpa=studentAcademic.getStudSem2SGPA();
							if(sem==3)
								sgpa=studentAcademic.getStudSem3SGPA();
							if(sem==4)
								sgpa=studentAcademic.getStudSem4SGPA();
							if(sem==5)
								sgpa=studentAcademic.getStudSem5SGPA();
							if(sem==6)
								sgpa=studentAcademic.getStudSem6SGPA();
							if(sem==7)
								sgpa=studentAcademic.getStudSem7SGPA();
							if(sem==8)
								sgpa=studentAcademic.getStudSem8SGPA();
						
						
						StudentPersonalDetailsBean studentBean=studentdao.fetchStudentDetails(strID,sesID,studRoll);
						str+="['"+"Semester"+sem+"',"+sgpa;
					}
					}
					str+="]"+",";
				}
			}
			
			else if(param.equals("MultipleStudentsSemester")){
				int semNo=Integer.parseInt(request.getParameter("semester"));
				yaxis="Names of Student";
				
				for(int i=1;i<=5;i++){
					String sesID=request.getParameter("sesSelect"+i);
					if(!(request.getParameter("studSelect"+i).equalsIgnoreCase("defaultvalue"))){
						long studRoll=Long.parseLong(request.getParameter("studSelect"+i));
						StudentAcademicDetailsBean studentAcademic=studentAcademicDao.fetchStudent(strID, sesID, studRoll);
						
						float sgpa=0;
						if(semNo==1)
							sgpa=studentAcademic.getStudSem1SGPA();
						if(semNo==2)
							sgpa=studentAcademic.getStudSem2SGPA();
						if(semNo==3)
							sgpa=studentAcademic.getStudSem3SGPA();
						if(semNo==4)
							sgpa=studentAcademic.getStudSem4SGPA();
						if(semNo==5)
							sgpa=studentAcademic.getStudSem5SGPA();
						if(semNo==6)
							sgpa=studentAcademic.getStudSem6SGPA();
						if(semNo==7)
							sgpa=studentAcademic.getStudSem7SGPA();
						if(semNo==8)
							sgpa=studentAcademic.getStudSem8SGPA();
						
						
						StudentPersonalDetailsBean studentBean=studentdao.fetchStudentDetails(strID,sesID,studRoll);
						str+="['"+studentBean.getStudName()+"',"+sgpa +"]"+",";
					}
				}
			}
			
			else if(param.equals("IndividualStudentAttendance")){
				
				String sesID=request.getParameter("sesSelect");
				long studRoll=Long.parseLong(request.getParameter("studSelect"));
				int semNo=Integer.parseInt(request.getParameter("semester"));
				String sesSecName=request.getParameter("sectionSelect");
				System.out.println(studRoll);		
				
				yaxis="Subjects of Semester"+request.getParameter("semester");
				ArrayList<ArrayList<String>> aalist=attendance.fetchStudentAttnPercentForSubjectsInSem(strID, sesID, sesSecName, semNo, studRoll);
				
				for(ArrayList<String> alist:aalist){
					float value=Float.parseFloat(alist.get(1));
					System.out.println("Attendance value="+value);
					str+="['"+alist.get(0)+"',"+value +"]"+",";
				}
					
			}
			
				else if(param.equals("GradeDistribution")){
				
					int streamID=(int)request.getSession().getAttribute(LoginSrv.Parameters.STREAM);
					String sesID=request.getParameter("multiattendancesession");
					//long studRoll=Long.parseLong(request.getParameter("studSelect"));
					//int semNo=Integer.parseInt(request.getParameter("semester"));
					String sesSecName=request.getParameter("multiattendancesection");
					String subCode=request.getParameter("multiattendancesubject");		
				
					MarksDao marks=new MarksDaoImpl();
					yaxis="Grades";
					String GradeO="O";
					String GradeE="E";
					String GradeA="A";
					String GradeB="B";
					String GradeC="C";
					String GradeD="D";
					String GradeF="F";
					
					int countO=marks.findCountOfStudent(streamID, sesID, sesSecName, subCode, "O");
					int countE=marks.findCountOfStudent(streamID, sesID, sesSecName, subCode, "E");
					int countA=marks.findCountOfStudent(streamID, sesID, sesSecName, subCode, "A");
					int countB=marks.findCountOfStudent(streamID, sesID, sesSecName, subCode, "B");
					int countC=marks.findCountOfStudent(streamID, sesID, sesSecName, subCode, "C");
					int countD=marks.findCountOfStudent(streamID, sesID, sesSecName, subCode, "D");
					int countF=marks.findCountOfStudent(streamID, sesID, sesSecName, subCode, "F");
					
					str+="['"+GradeO+"',"+countO +"]"+",";
					str+="['"+GradeE+"',"+countE +"]"+",";
					str+="['"+GradeA+"',"+countA +"]"+",";
					str+="['"+GradeB+"',"+countB +"]"+",";
					str+="['"+GradeC+"',"+countC +"]"+",";
					str+="['"+GradeD+"',"+countD +"]"+",";
					str+="['"+GradeF+"',"+countF +"]"+",";
				
				}
				else if(param.equals("MultipleStudentsAttendance"))
				{
						request.getSession().setAttribute("MSG", "Chart can't be generated for this data");
						
				}
				
			
		
		
		System.out.println(str);
		ses.setAttribute("ja", str);
		ses.setAttribute("yaxis", yaxis);
		ses.setAttribute("param", param);
		response.sendRedirect("studentcomparison.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
