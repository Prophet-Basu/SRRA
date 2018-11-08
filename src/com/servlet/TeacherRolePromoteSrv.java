package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.TeacherDao;
import com.dao.TeacherDaoImpl;

/**
 * Servlet implementation class TeacherRolePromoteSrv
 */
@WebServlet("/TeacherRolePromoteSrv")
public class TeacherRolePromoteSrv extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeacherRolePromoteSrv() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int teacherID=Integer.parseInt(request.getParameter("teacherID"));
		int strID=(int)request.getSession().getAttribute("stream");
		TeacherDao tdao=new TeacherDaoImpl();
		if(tdao.promoteTeacherRole(teacherID, strID)){
			request.getSession().setAttribute("msg", "Teacher has been promoted to Admin!!!");
			response.sendRedirect("promoteteacherrole.jsp");
		}
		else{
			request.getSession().setAttribute("msg", "Teacher not promoted!!!");
			response.sendRedirect("promoteteacherrole.jsp");
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
