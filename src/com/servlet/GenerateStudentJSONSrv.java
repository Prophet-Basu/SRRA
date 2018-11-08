package com.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.bean.StudentAcademicDetailsBean;
import com.bean.StudentPersonalDetailsBean;
import com.bean.SubjectBean;
import com.dao.SessionDaoImpl;
import com.dao.StreamDaoImpl;
import com.dao.StudentAcademicDetailsDao;
import com.dao.StudentAcademicDetailsDaoImpl;
import com.dao.StudentPersonalDao;
import com.dao.StudentPersonalDaoImpl;
import com.servlet.GenerateSubjectsJSONSrv.Parameters;
import com.util.DBSchema;

/**
 * Servlet implementation class GenerateStudentJSONSrv
 */
@WebServlet("/GenerateStudentJSONSrv")
public class GenerateStudentJSONSrv extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	public static class Parameters{
		public static final String STRID="strID";
		public static final String SESSION="sesID";
		public static final String CATEGORY="category";
	}
	
	@SuppressWarnings("unchecked")
	private JSONObject mapToJSON(int strID,String sesID,StudentPersonalDetailsBean studentBean){
		JSONObject obj = new JSONObject();
		obj.put(DBSchema.StudentPersonalDetails.Columns.ROLL, studentBean.getStudRoll());
		obj.put(DBSchema.StudentPersonalDetails.Columns.NAME, studentBean.getStudName());
		obj.put(DBSchema.StudentPersonalDetails.Columns.STRID, new StreamDaoImpl().fetchStreamName(strID));
		obj.put(DBSchema.StudentPersonalDetails.Columns.SESID, new String(new SessionDaoImpl().fetchSingleSession(sesID).getSesStartDate())+"-"+new String(new SessionDaoImpl().fetchSingleSession(sesID).getSesEndDate()));
		return obj;
	}
   

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Entered SRV");
		System.out.println(request.getParameter(Parameters.STRID));
		System.out.println(request.getParameter(Parameters.SESSION));
		
		int stream = Integer.parseInt(request.getParameter(Parameters.STRID));
		String sesID= request.getParameter(Parameters.SESSION);
		String category=request.getParameter(Parameters.CATEGORY);
		
		if(category.equalsIgnoreCase("General")){
			

			StudentPersonalDao studentDao=new StudentPersonalDaoImpl();
			ArrayList<StudentPersonalDetailsBean> studentlist = studentDao.fetchAllStudent(stream, sesID);
			
			JSONObject json_response=new JSONObject();
			JSONArray subj_array_json=new JSONArray();
			for(StudentPersonalDetailsBean sb: studentlist){
				subj_array_json.add(mapToJSON(stream,sesID,sb));
			}
			
			json_response.put(DBSchema.StudentPersonalDetails.TABLE_NAME, subj_array_json);
	
			System.out.println(json_response.toString());
			response.setContentType("application/Json");
	        
	        response.getWriter().write(json_response.toString());
	        
		}
		
		if(category.equalsIgnoreCase("Diploma")){
			
			StudentAcademicDetailsDao academicdao=new StudentAcademicDetailsDaoImpl();
			ArrayList<StudentAcademicDetailsBean> studentlist=academicdao.fetchDiplomaStudents(stream, sesID);
			
			JSONObject json_response=new JSONObject();
			JSONArray subj_array_json=new JSONArray();
			
			for(StudentAcademicDetailsBean sa:studentlist){
				
				StudentPersonalDao studentDao=new StudentPersonalDaoImpl();
				StudentPersonalDetailsBean sb=studentDao.fetchStudentDetails(stream,sesID,sa.getStudRoll());
				subj_array_json.add(mapToJSON(stream,sesID,sb));
			}
			
			json_response.put(DBSchema.StudentPersonalDetails.TABLE_NAME, subj_array_json);
			
			System.out.println(json_response.toString());
			response.setContentType("application/Json");
	        
	        response.getWriter().write(json_response.toString());
		}
		
		
			//Non Diploma Students
			if(category.equalsIgnoreCase("NonDiploma")){
			
			boolean flag=true;
				
			StudentPersonalDao studentDao=new StudentPersonalDaoImpl();
			ArrayList<StudentPersonalDetailsBean> studentlist = studentDao.fetchAllStudent(stream, sesID);
				
			StudentAcademicDetailsDao academicdao=new StudentAcademicDetailsDaoImpl();
			ArrayList<StudentAcademicDetailsBean> studentacademiclist=academicdao.fetchDiplomaStudents(stream, sesID);
			
			JSONObject json_response=new JSONObject();
			JSONArray subj_array_json=new JSONArray();
			
			for(StudentPersonalDetailsBean sb: studentlist){
					flag=true;
				for(StudentAcademicDetailsBean sa:studentacademiclist){
					if(sb.getStudRoll()==sa.getStudRoll()){
						flag=false;
						break;
					}
					
				}
				if(flag)
					subj_array_json.add(mapToJSON(stream,sesID,sb));
			}
			
			
			
			json_response.put(DBSchema.StudentPersonalDetails.TABLE_NAME, subj_array_json);
			
			System.out.println(json_response.toString());
			response.setContentType("application/Json");
	        
	        response.getWriter().write(json_response.toString());
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
