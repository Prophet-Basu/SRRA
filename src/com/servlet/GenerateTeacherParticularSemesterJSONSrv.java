package com.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.bean.SubjectBean;
import com.bean.TeacherBean;
import com.bean.TeachesBean;
import com.dao.SubjectDao;
import com.dao.SubjectDaoImpl;
import com.dao.TeacherDao;
import com.dao.TeacherDaoImpl;
import com.dao.TeachesDao;
import com.dao.TeachesDaoImpl;
import com.util.DBSchema;

/**
 * Servlet implementation class GenerateTeacherParticularSemesterJSONSrv
 */
@WebServlet("/GenerateTeacherParticularSemesterJSONSrv")
public class GenerateTeacherParticularSemesterJSONSrv extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public static class Parameters{
		public static final String STRID="strID";
		public static final String SEM="semID";
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		JSONObject json_response=new JSONObject();
		JSONArray subj_array_json=new JSONArray();
		
		SubjectDao subdao=new SubjectDaoImpl();
		ArrayList<SubjectBean> subBean=subdao.fetchAllStreamSubjects(Integer.parseInt(request.getParameter(Parameters.STRID)),Integer.parseInt(request.getParameter(Parameters.SEM)));
		
		for(SubjectBean sb:subBean){
		
			TeachesDao teachesDao=new TeachesDaoImpl();
			ArrayList<TeachesBean> teachList=teachesDao.fetchParticularSubjectTeacher(sb.getSubCode(), Integer.parseInt(request.getParameter(Parameters.STRID)));
			
			for(TeachesBean teach:teachList){
				
				TeacherDao teacher=new TeacherDaoImpl();
				TeacherBean subTeacher=teacher.fetchTeacher(teach.getTeacherID(), Integer.parseInt(request.getParameter(Parameters.STRID)));
				
				
						JSONObject obj = new JSONObject();
						obj.put(DBSchema.Teacher.Columns.ID, subTeacher.getTeacherID());
						obj.put(DBSchema.Teacher.Columns.NAME, subTeacher.getTeacherName());
						obj.put(DBSchema.Teaches.Columns.SESID, teach.getSesID());
						obj.put(DBSchema.Subject.Columns.NAME,sb.getSubName());
						subj_array_json.add(obj);
			}
		}
		
				json_response.put(DBSchema.Teacher.TABLE_NAME, subj_array_json);
		
				System.out.println(json_response.toString());
				response.setContentType("application/Json");
        
				response.getWriter().write(json_response.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
