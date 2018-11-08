package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.TeachesDao;
import com.dao.TeachesDaoImpl;

/**
 * Servlet implementation class TeacherSubjectVerifySrv
 */
@WebServlet("/TeacherSubjectVerifySrv")
public class TeacherSubjectVerifySrv extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeacherSubjectVerifySrv() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sesID=(request.getParameter("sesID"));
		String secName=request.getParameter("secName");
		int strID=(int)request.getSession().getAttribute("stream");
		
		TeachesDao tdao=new TeachesDaoImpl();
		if(tdao.verifyTeachesSubject(strID, sesID, secName)){
			request.getSession().setAttribute("msg", "Teacher has been Verified!!!");
			response.sendRedirect("verifysubjects.jsp");
		}
		else{
			request.getSession().setAttribute("msg", "Teacher not Verified!!!");
			response.sendRedirect("verifysubjects.jsp");
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
