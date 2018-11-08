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

import com.bean.TeacherBean;
import com.dao.TeacherDao;
import com.dao.TeacherDaoImpl;
import com.dao.TeachesDao;
import com.dao.TeachesDaoImpl;
import com.util.DBSchema;

/**
 * Servlet implementation class GenerateTeacherJSONSrv
 */
@WebServlet("/GenerateTeacherJSONSrv")
public class GenerateTeacherJSONSrv extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	public static class Parameters{
		public static final String TeacherID="teacherID";
		public static final String SESSION="sesID";
		public static final String SECTION="sesSecName";
		public static final String CATEGORY="category";
		public static final String STRID="strID";
	}
	
	private JSONObject mapToJson(String secName){
    	JSONObject obj = new JSONObject();
		obj.put("secName", secName);
		return obj;
    }
	
	@SuppressWarnings("unchecked")
	private JSONObject mapToJSONAbstract(TeacherBean teachBean){
		JSONObject obj = new JSONObject();
		obj.put(DBSchema.Teacher.Columns.ID, teachBean.getTeacherID());
		obj.put(DBSchema.Teacher.Columns.NAME, teachBean.getTeacherName());
		obj.put(DBSchema.Teacher.Columns.DESIGNATION, teachBean.getTeacherDesignation());
		
		return obj;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String category=request.getParameter(Parameters.CATEGORY);
		//System.out.println(sesID+" "+teacherID);
		TeachesDao tdao=new TeachesDaoImpl();
		
		if(category.equalsIgnoreCase("section")){
			
			String sesID=(request.getParameter("sesID"));
			
			int teacherID=Integer.parseInt(request.getParameter("teacherID"));
				ArrayList<String> secList=tdao.fetchTeacherSectionsTaught(teacherID, sesID);
				JSONObject json_response=new JSONObject();
				JSONArray jarr=new JSONArray();
				for(String s:secList){
					jarr.add(mapToJson(s));
				}
				json_response.put("sections", jarr);
				
				System.out.println(json_response.toString());
				response.setContentType("application/Json");
		        
		        response.getWriter().write(json_response.toString());
		}
		
		if(category.equalsIgnoreCase("subject")){
			
			int teacherID=Integer.parseInt(request.getParameter("teacherID"));
			String sesID=(request.getParameter("sesID"));
			System.out.println("Subject block");
			String section=request.getParameter(Parameters.SECTION);
			ArrayList<String> secList=tdao.fetchTeacherSubjectTaught(teacherID, sesID, section);
			JSONObject json_response=new JSONObject();
			JSONArray jarr=new JSONArray();
			for(String s:secList){
				jarr.add(mapToJson(s));
			}
			json_response.put("sections", jarr);
			
			System.out.println(json_response.toString());
			response.setContentType("application/Json");
	        
	        response.getWriter().write(json_response.toString());
	}
		
		if(category.equalsIgnoreCase("verify")){
			TeacherDao teacherdao=new TeacherDaoImpl();
			
			int strID=Integer.parseInt(request.getParameter("strID"));
			ArrayList<TeacherBean> tlist=teacherdao.fetchNonVerifiedTeachers(strID);
			
			JSONObject json_response=new JSONObject();
			JSONArray subj_array_json=new JSONArray();
			for(TeacherBean tb: tlist){
				subj_array_json.add(mapToJSONAbstract(tb));
			}
			
			json_response.put(DBSchema.Teacher.TABLE_NAME, subj_array_json);
	
			System.out.println(json_response.toString());
			response.setContentType("application/Json");
	        
	        response.getWriter().write(json_response.toString());
		}
		
		if(category.equalsIgnoreCase("MakeAdmin")){
			
			int strID=Integer.parseInt(request.getParameter("strID"));
			
			TeacherDao teacherdao=new TeacherDaoImpl();
			ArrayList<TeacherBean> tlistAdmin=teacherdao.fetchNonAdminTeachers(strID);
			JSONObject json_response=new JSONObject();
			JSONArray subj_array_json=new JSONArray();
			for(TeacherBean tadmin: tlistAdmin){
				subj_array_json.add(mapToJSONAbstract(tadmin));
			}
			
			json_response.put(DBSchema.Teacher.TABLE_NAME, subj_array_json);
	
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
