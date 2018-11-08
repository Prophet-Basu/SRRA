package com.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.TeacherBean;
import com.dao.TeacherDao;
import com.dao.TeacherDaoImpl;

/**
 * Servlet implementation class GenerateNonVerifiedTeachersSrv
 */
@WebServlet("/GenerateNonVerifiedTeachersSrv")
public class GenerateNonVerifiedTeachersSrv extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	public static class Parameters{
		public static final String TEACHERLIST="tlist";
		public static final String STREAM="stream";
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GenerateNonVerifiedTeachersSrv() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int strID=(int)request.getSession().getAttribute(Parameters.STREAM);
		TeacherDao tdao=new TeacherDaoImpl();
		ArrayList<TeacherBean> tlist=tdao.fetchNonVerifiedTeachers(strID);
		request.getSession().setAttribute(Parameters.TEACHERLIST, tlist);
		response.sendRedirect("verifyteacher.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
