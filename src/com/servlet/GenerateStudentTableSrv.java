package com.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.AttendanceBean;
import com.bean.MarksBean;
import com.bean.StudentAcademicDetailsBean;
import com.bean.StudentPersonalDetailsBean;
import com.bean.SubjectBean;
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
 * Servlet implementation class GenerateStudentTableSrv
 */
@WebServlet("/GenerateStudentTableSrv")
public class GenerateStudentTableSrv extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GenerateStudentTableSrv() {
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
		
			MarksDao marksdao=new MarksDaoImpl();
			SubjectDao subdao=new SubjectDaoImpl();
			StudentPersonalDao studentdao=new StudentPersonalDaoImpl();
			StudentAcademicDetailsDao studentAcademicDao=new StudentAcademicDetailsDaoImpl();
			
			ArrayList<ArrayList<String>> aalist=new ArrayList<ArrayList<String>>();
			ArrayList<String> row;
		
			if(param.equals("IndividualStudentSubjects")){
			
				row=new ArrayList<String>();
				row.add("Sl. No.");
				row.add("Subject Name");
				row.add("Marks");
				aalist.add(row);
				int count=0;
			String sesID=request.getParameter("sesSelect");
			long studRoll=Long.parseLong(request.getParameter("studSelect"));
			int semNo=Integer.parseInt(request.getParameter("semester"));
			//System.out.println(studRoll);		
			
			
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
				row=new ArrayList<String>();
				row.add(String.valueOf(++count));
				row.add(subDetails.get(index).getSubName());
				row.add(String.valueOf(mb.getMarksSem() ));
				aalist.add(row);
				
				
				
				
			}
			
		}
			
			
			if(param.equals("MultipleStudentsSubject")){
				
				row=new ArrayList<String>();
				row.add("Sl. No.");
				row.add("Names of Student");
				row.add("Marks");
				aalist.add(row);
				int count=0;
				int semNo=Integer.parseInt(request.getParameter("semester"));
				String subCode=request.getParameter("subject");
				for(int i=1;i<=5;i++){
					String sesID=request.getParameter("sesSelect"+i);
					if(!(request.getParameter("studSelect"+i).equalsIgnoreCase("defaultvalue"))){
					long studRoll=Long.parseLong(request.getParameter("studSelect"+i));
					
					
					int marks=marksdao.findStudentParticularSubjectMarks(strID, sesID, studRoll, semNo, subCode);
					StudentPersonalDetailsBean studentBean=studentdao.fetchStudentDetails(strID,sesID,studRoll);
					
					
					row=new ArrayList<String>();
					row.add(String.valueOf(++count));
					row.add(studentBean.getStudName());
					row.add(String.valueOf(marks));
					aalist.add(row);
					}
				}
			
		}
			
				if(param.equals("Class10BoardBestMarks")){
				
				row=new ArrayList<String>();
				row.add("Sl. No.");
				row.add("Names of Student");
				row.add("Marks");
				aalist.add(row);
				int count=0;
				for(int i=1;i<=5;i++){
					String sesID=request.getParameter("sesSelect"+i);
					if(!(request.getParameter("studSelect"+i).equalsIgnoreCase("defaultvalue"))){
					long studRoll=Long.parseLong(request.getParameter("studSelect"+i));
					
					
					StudentPersonalDetailsBean studentBean=studentdao.fetchStudentDetails(strID,sesID,studRoll);
					StudentAcademicDetailsBean academicBean=studentAcademicDao.fetchStudent(strID,sesID,studRoll);
					
					row=new ArrayList<String>();
					row.add(String.valueOf(++count));
					row.add(studentBean.getStudName());
					row.add(String.valueOf(academicBean.getStudCls10BestMarks() ));
					aalist.add(row);
					
					}
				}
			
		}
				
				if(param.equals("Class10BoardAvgMarks")){
					
					row=new ArrayList<String>();
					row.add("Sl. No.");
					row.add("Names of Student");
					row.add("Marks");
					aalist.add(row);
					int count=0;
					for(int i=1;i<=5;i++){
						String sesID=request.getParameter("sesSelect"+i);
						if(!(request.getParameter("studSelect"+i).equalsIgnoreCase("defaultvalue"))){
						long studRoll=Long.parseLong(request.getParameter("studSelect"+i));
						
						
						StudentPersonalDetailsBean studentBean=studentdao.fetchStudentDetails(strID,sesID,studRoll);
						StudentAcademicDetailsBean academicBean=studentAcademicDao.fetchStudent(strID,sesID,studRoll);
						
						row=new ArrayList<String>();
						row.add(String.valueOf(++count));
						row.add(studentBean.getStudName());
						row.add(String.valueOf(academicBean.getStudCls10AvgMarks() ));
						aalist.add(row);
						
						}
					}
				
			}
				
				
					if(param.equals("Class12BoardBestMarks")){
					
					row=new ArrayList<String>();
					row.add("Sl. No.");
					row.add("Names of Student");
					row.add("Marks");
					aalist.add(row);
					int count=0;
					for(int i=1;i<=5;i++){
						String sesID=request.getParameter("nonDiplomaSesSelect"+i);
						if(!(request.getParameter("nonDiplomaStudSelect"+i).equalsIgnoreCase("defaultvalue"))){
						long studRoll=Long.parseLong(request.getParameter("nonDiplomaStudSelect"+i));
						
						
						StudentPersonalDetailsBean studentBean=studentdao.fetchStudentDetails(strID,sesID,studRoll);
						StudentAcademicDetailsBean academicBean=studentAcademicDao.fetchStudent(strID,sesID,studRoll);
						
						row=new ArrayList<String>();
						row.add(String.valueOf(++count));
						row.add(studentBean.getStudName());
						row.add(String.valueOf(academicBean.getStudCls12BestMarks() ));
						aalist.add(row);
						
						
						}
					}
				
			}
					
					if(param.equals("Class12BoardAvgMarks")){
						
						row=new ArrayList<String>();
						row.add("Sl. No.");
						row.add("Names of Student");
						row.add("Marks");
						aalist.add(row);
						int count=0;
						for(int i=1;i<=5;i++){
							String sesID=request.getParameter("nonDiplomaSesSelect"+i);
							if(!(request.getParameter("nonDiplomaStudSelect"+i).equalsIgnoreCase("defaultvalue"))){
							long studRoll=Long.parseLong(request.getParameter("nonDiplomaStudSelect"+i));
							
							
							StudentPersonalDetailsBean studentBean=studentdao.fetchStudentDetails(strID,sesID,studRoll);
							StudentAcademicDetailsBean academicBean=studentAcademicDao.fetchStudent(strID,sesID,studRoll);
							
							row=new ArrayList<String>();
							row.add(String.valueOf(++count));
							row.add(studentBean.getStudName());
							row.add(String.valueOf(academicBean.getStudCls12AvgMarks() ));
							aalist.add(row);
							
							
							}
						}
					
				}
					
						if(param.equals("DiplomaMarks")){
						
						row=new ArrayList<String>();
						row.add("Sl. No.");
						row.add("Names of Student");
						row.add("Marks");
						aalist.add(row);
						int count=0;
						for(int i=1;i<=5;i++){
							String sesID=request.getParameter("diplomasesSelect"+i);
							if(!(request.getParameter("diplomastudSelect"+i).equalsIgnoreCase("defaultvalue"))){
							long studRoll=Long.parseLong(request.getParameter("diplomastudSelect"+i));
							
							
							StudentPersonalDetailsBean studentBean=studentdao.fetchStudentDetails(strID,sesID,studRoll);
							StudentAcademicDetailsBean academicBean=studentAcademicDao.fetchStudent(strID,sesID,studRoll);
							
							row=new ArrayList<String>();
							row.add(String.valueOf(++count));
							row.add(studentBean.getStudName());
							row.add(String.valueOf(academicBean.getStudDiplomaMarks() ));
							aalist.add(row);
							
							
							}
						}
					
				}
						
						if(param.equals("JELETSelectionRank")){
							
							row=new ArrayList<String>();
							row.add("Sl. No.");
							row.add("Names of Student");
							row.add("Rank");
							aalist.add(row);
							int count=0;
							for(int i=1;i<=5;i++){
								String sesID=request.getParameter("diplomasesSelect"+i);
								if(!(request.getParameter("diplomastudSelect"+i).equalsIgnoreCase("defaultvalue"))){
								long studRoll=Long.parseLong(request.getParameter("diplomastudSelect"+i));
								
								
								StudentPersonalDetailsBean studentBean=studentdao.fetchStudentDetails(strID,sesID,studRoll);
								StudentAcademicDetailsBean academicBean=studentAcademicDao.fetchStudent(strID,sesID,studRoll);
								
								row=new ArrayList<String>();
								row.add(String.valueOf(++count));
								row.add(studentBean.getStudName());
								row.add(String.valueOf(academicBean.getStudBTechSelectionRank() ));
								aalist.add(row);
								
								
								}
							}
						
					}
						
						
							if(param.equals("WBJEESelectionRank")){
							
							row=new ArrayList<String>();
							row.add("Sl. No.");
							row.add("Names of Student");
							row.add("Rank");
							aalist.add(row);
							int count=0;
							for(int i=1;i<=5;i++){
								String sesID=request.getParameter("nonDiplomaSesSelect"+i);
								if(!(request.getParameter("nonDiplomaStudSelect"+i).equalsIgnoreCase("defaultvalue"))){
								long studRoll=Long.parseLong(request.getParameter("nonDiplomaStudSelect"+i));
								
								
								StudentPersonalDetailsBean studentBean=studentdao.fetchStudentDetails(strID,sesID,studRoll);
								StudentAcademicDetailsBean academicBean=studentAcademicDao.fetchStudent(strID,sesID,studRoll);
								
								row=new ArrayList<String>();
								row.add(String.valueOf(++count));
								row.add(studentBean.getStudName());
								row.add(String.valueOf(academicBean.getStudBTechSelectionRank() ));
								aalist.add(row);
								
								
								}
							}
						
					}
							
							if(param.equals("IndividualStudentSemesters")){
								
								row=new ArrayList<String>();
								row.add("Sl. No.");
								row.add("Semesters");
								row.add("SGPA");
								aalist.add(row);
								int count=0;
								String sesID=request.getParameter("sesSelect");
								long studRoll=Long.parseLong(request.getParameter("studSelect"));
								System.out.println(studRoll);		
								
								
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
										
										row=new ArrayList<String>();
										row.add(String.valueOf(++count));
										row.add("Semester"+i);
										row.add(String.valueOf(sgpa));
										aalist.add(row);
										
										
									
									}
								}
							
							
							if(param.equals("MultipleStudentsSemester")){
								
								row=new ArrayList<String>();
								row.add("Sl. No.");
								row.add("Names of Student");
								row.add("SGPA");
								aalist.add(row);
								int count=0;
								int semNo=Integer.parseInt(request.getParameter("semester"));
								
								
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
										
										row=new ArrayList<String>();
										row.add(String.valueOf(++count));
										row.add(studentBean.getStudName());
										row.add(String.valueOf(sgpa));
										aalist.add(row);
										
										
									}
								}
							}
						
							
							if(param.equals("MultipleStudentsAttendance")){
								
								int streamID=(int)request.getSession().getAttribute(LoginSrv.Parameters.STREAM);
								String sesID=request.getParameter("multiattendancesession");
								//long studRoll=Long.parseLong(request.getParameter("studSelect"));
								//int semNo=Integer.parseInt(request.getParameter("semester"));
								String sesSecName=request.getParameter("multiattendancesection");
								String subCode=request.getParameter("multiattendancesubject");
								SubjectDao sbdao=new SubjectDaoImpl();
								SubjectBean sb=sbdao.fetchSubject(strID, subCode);
								//System.out.println(studRoll);		
								AttendanceBean abean=new AttendanceBean(subCode, sesSecName, sb.getSubSem(), "", 0);
								
								ArrayList<ArrayList<String>> aalist2=new AttendanceDaoImpl().fetchSubjectAttendance(streamID, sesID, abean);
								ArrayList<String> paramList=aalist2.get(0);
								int arr[]=new int[paramList.size()-2];
								
								for(int i=0;i<paramList.size()-2;i++){
									for(int j=1;j<aalist2.size();j++){
										if(aalist2.get(j).get(i+1).equals("1")){
											arr[i]++;
										}
									}
								}
								
								row=new ArrayList<String>();
								row.add("Student Roll");
								row.add("Attendance");
								aalist.add(row);
								
								for(int i=0;i<paramList.size()-2;i++){
									row=new ArrayList<>();
									row.add(paramList.get(i+1).substring(1));
									row.add(String.valueOf(arr[i]));
									aalist.add(row);
								}
								
								
							}
							
							if(param.equals("GradeDistribution")){
								
								row=new ArrayList<String>();
								row.add("Grade");
								row.add("Count");
								aalist.add(row);
								
								int streamID=(int)request.getSession().getAttribute(LoginSrv.Parameters.STREAM);
								String sesID=request.getParameter("multiattendancesession");
								//long studRoll=Long.parseLong(request.getParameter("studSelect"));
								//int semNo=Integer.parseInt(request.getParameter("semester"));
								String sesSecName=request.getParameter("multiattendancesection");
								String subCode=request.getParameter("multiattendancesubject");		
							
								MarksDao marks=new MarksDaoImpl();
								//yaxis="Grades";
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
								
								row=new ArrayList<>();
								row.add(GradeO);
								row.add(String.valueOf(countO));
								aalist.add(row);
								
								row=new ArrayList<>();
								row.add(GradeE);
								row.add(String.valueOf(countE));
								aalist.add(row);
								
								row=new ArrayList<>();
								row.add(GradeA);
								row.add(String.valueOf(countA));
								aalist.add(row);
								
								row=new ArrayList<>();
								row.add(GradeB);
								row.add(String.valueOf(countB));
								aalist.add(row);
								
								row=new ArrayList<>();
								row.add(GradeC);
								row.add(String.valueOf(countC));
								aalist.add(row);
								
								row=new ArrayList<>();
								row.add(GradeD);
								row.add(String.valueOf(countD));
								aalist.add(row);
								
								row=new ArrayList<>();
								row.add(GradeF);
								row.add(String.valueOf(countF));
								aalist.add(row);
								
								
							
							}
						
					
					
			
			ses.setAttribute("table", true);
			ses.setAttribute("aalist",aalist);
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
