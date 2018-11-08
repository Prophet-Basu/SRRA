package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.StudentAcademicDetailsBean;
import com.bean.StudentPersonalDetailsBean;
import com.dao.StudentAcademicDetailsDao;
import com.dao.StudentAcademicDetailsDaoImpl;
import com.dao.StudentPersonalDao;
import com.dao.StudentPersonalDaoImpl;

/**
 * Servlet implementation class EditStudentSrv
 */
@WebServlet("/EditStudentSrv")
public class EditStudentSrv extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditStudentSrv() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long roll=Long.parseLong(request.getParameter("Roll"));
		
		HttpSession session=request.getSession();
		session.setAttribute("UpdateRoll", roll);
		
		StudentPersonalDao studentPersonal=new StudentPersonalDaoImpl();
		//StudentPersonalDetailsBean studentBean=studentPersonal.fetchStudentDetails(roll);
		//session.setAttribute("UpdateStudentPersonal", studentBean);
		
		StudentAcademicDetailsDao studentAcademic=new StudentAcademicDetailsDaoImpl();
		//StudentAcademicDetailsBean studentAcadBean=studentAcademic.fetchStudentDetails(roll);
		//session.setAttribute("UpdateStudentAcademic", studentAcadBean);
		
		response.sendRedirect("studentprofileupdatecriteria.jsp");
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
