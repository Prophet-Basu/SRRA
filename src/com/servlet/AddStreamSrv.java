package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.StreamBean;
import com.dao.StreamDao;
import com.dao.StreamDaoImpl;
import com.dao.TeacherDao;
import com.dao.TeacherDaoImpl;

/**
 * Servlet implementation class AddStreamSrv
 */
@WebServlet("/AddStreamSrv")
public class AddStreamSrv extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static class Parameters{
		public static final String STREAM_ID = "strID";
		public static final String STREAM_NAME = "strName";
		public static final String STREAM_NO_OF_SEM = "strNoOfSem";
		public static final String MESSAGE = "deladdsuccess";
		public static final String DEL_ADD_FAILURE = "deladdfailure";
	}
	
    

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		
		String[] names = request.getParameterValues(Parameters.STREAM_NAME);
		String[] noOfSems = request.getParameterValues(Parameters.STREAM_NO_OF_SEM);
		StringBuilder sb = new StringBuilder();
		
		
		StreamDao sdao=new StreamDaoImpl();
		TeacherDao trdao=new TeacherDaoImpl();
		
		try{
			for(int i=0;i<names.length;i++){
				StreamBean strbean=new StreamBean(0,names[i],
						  Integer.parseInt(noOfSems[i]));
				
				int id=sdao.addStream(strbean);
				if(id==0){
					sb.append("<br/>Could not Add Stream: "+names[i]);
				}else{
					sb.append("<br/>Added Stream: "+names[i]+"with id: "+id);
					trdao.createTeacherTable(id);
				}
			}
		}catch(Exception e){
			sb.append("<br/>Error: "+e.getMessage());
		}
		
		request.getSession().setAttribute(Parameters.MESSAGE, sb.toString());
		response.sendRedirect("initsetupstream.jsp");
		
	}

}
