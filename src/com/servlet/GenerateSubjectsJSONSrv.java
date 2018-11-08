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

import com.bean.SubjectBean;
import com.dao.SubjectDao;
import com.dao.SubjectDaoImpl;
import com.util.DBSchema;

/**
 * Servlet implementation class GenerateSubjectsJSONSrv
 */
@WebServlet("/GenerateSubjectsJSONSrv")
public class GenerateSubjectsJSONSrv extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public static class Parameters{
		public static final String STRID="strID";
		public static final String SEM="subSem";
	}
	
	@SuppressWarnings("unchecked")
	private JSONObject mapToJSON(SubjectBean subBean){
		JSONObject obj = new JSONObject();
		obj.put(DBSchema.Subject.Columns.CODE, subBean.getSubCode());
		obj.put(DBSchema.Subject.Columns.NAME, subBean.getSubName());
		obj.put(DBSchema.Subject.Columns.SEM, subBean.getSubSem());
		obj.put(DBSchema.Subject.Columns.TAUGHTDURATION, subBean.getSubTaughtDuration());
		obj.put(DBSchema.Subject.Columns.STRID, subBean.getStrID());
		
		return obj;
	}

    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//System.out.println(request.getParameter(Parameters.STRID));
		//System.out.println(request.getParameter(Parameters.SEM));
		int stream = Integer.parseInt(request.getParameter(Parameters.STRID));
		int sem = Integer.parseInt(request.getParameter(Parameters.SEM));
		
		SubjectDao subdao = new SubjectDaoImpl();
		ArrayList<SubjectBean> sublist;
		if(sem==0)
			sublist=subdao.fetchAllStreamSubjects(stream);
		else
			sublist = subdao.fetchAllStreamSubjects(stream, sem);
		
		JSONObject json_response=new JSONObject();
		JSONArray subj_array_json=new JSONArray();
		for(SubjectBean sb: sublist){
			subj_array_json.add(mapToJSON(sb));
		}
		
		json_response.put(DBSchema.Subject.TABLE_NAME, subj_array_json);

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
