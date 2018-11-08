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
 * Servlet implementation class StreamUpdateSrv
 */
@WebServlet("/StreamUpdateSrv")
public class StreamUpdateSrv extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StreamUpdateSrv() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int strID=Integer.parseInt(request.getParameter("strID"));
		String strName=request.getParameter("strName");
		int strNoOfSem=Integer.parseInt(request.getParameter("strNoOfSem"));
		StreamDao sdao=new StreamDaoImpl();
		sdao.updateStream(new StreamBean(strID, strName, strNoOfSem));
		response.sendRedirect("initsetupstream.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
