package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.SessionBean;
import com.dao.SessionDao;
import com.dao.SessionDaoImpl;

/**
 * Servlet implementation class AddSessionSrv
 */
@WebServlet("/AddSessionSrv")
public class AddSessionSrv extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public static class Parameters{
    	public static final String SESSIONSTART="sessionStartDate";
    	public static final String SESSIONEND="sessionEndDate";
    	public static final String NOOFSECTIONS="sectionNo";
    	public static final String SECTIONNAME="secName";
    }
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddSessionSrv() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int strID=(int)request.getSession().getAttribute("stream");
		String sesStart=request.getParameter(Parameters.SESSIONSTART);
		String sesEnd=request.getParameter(Parameters.SESSIONEND);
		String secNames[]=request.getParameterValues(Parameters.SECTIONNAME);
		SessionDao sdao=new SessionDaoImpl();
		SessionBean sbean;
		for(int i=0;i<secNames.length;i++){
			sbean=new SessionBean(sesStart+"-"+sesEnd, strID, sesStart, secNames[i], sesEnd);
			sdao.addSessionDetails(sbean);
		}
		int type=Integer.parseInt(request.getParameter("type"));
		if(type==1){
			response.sendRedirect("initsetupsessions.jsp");
		}
		else{
			response.sendRedirect("initsetup.jsp");
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
