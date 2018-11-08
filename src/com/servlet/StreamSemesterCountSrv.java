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

/**
 * Servlet implementation class StreamSubjectCountSrv
 */
@WebServlet("/StreamSemesterCountSrv")
public class StreamSemesterCountSrv extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public static class Parameters{
		public static final String STRID = "strID";
	}
	
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter(Parameters.STRID));
		
		StreamDao streamDao = new StreamDaoImpl();
		StreamBean sb = streamDao.fetchStreamDetails(id);
		//System.out.println("Request here for : ");
		
		response.getWriter().write(sb.getStrNoOfSem()+"");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
	}

}
