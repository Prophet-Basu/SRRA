package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.TeacherBean;
import com.bean.TeachesBean;
import com.dao.TeacherDao;
import com.dao.TeacherDaoImpl;

/**
 * Servlet implementation class TeacherRegisterSrv
 */
@WebServlet("/TeacherRegisterSrv")
public class TeacherRegisterSrv extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	public static class TeacherParameters{
		public static final String ID="teacherID";
		public static final String NAME="teacherName";
		public static final String ADDRESS="teacherAddress";
		public static final String DOB="teacherDOB";
		public static final String MOB="teacherMob";
		public static final String EMAIL="teacherEmail";
		public static final String ROLE="teacherRole";
		public static final String DESIGNATION="teacherDesignation";
		public static final String PASSWORD="teacherPassword";
		public static final String APPROVE="teacherApprove";
		public static final String DEL_ADD_SUCCESS = "deladdsuccess";
		public static final String DEL_ADD_FAILURE = "deladdfailure";
		public static final String STRID="strID";
		public static final String SESID="sesID";
		public static final String SUBCODE="subCode";
	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeacherRegisterSrv() {
        super();
        // TODO Auto-generated constructor stub
    }

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TeacherBean trBean=new TeacherBean(0, request.getParameter(TeacherParameters.NAME), 
												 request.getParameter(TeacherParameters.ADDRESS), 
												 request.getParameter(TeacherParameters.DOB), 
												 Long.parseLong(request.getParameter(TeacherParameters.MOB)), 
												 request.getParameter(TeacherParameters.EMAIL), 
												 "Teacher", 
												 request.getParameter(TeacherParameters.DESIGNATION), 
												 request.getParameter(TeacherParameters.PASSWORD),0);
		int strID=Integer.parseInt(request.getParameter(TeacherParameters.STRID));
		TeacherDao trDao=new TeacherDaoImpl();
		int id=trDao.addTeacher(trBean,strID);
//		String sublist[]=request.getParameterValues(TeacherParameters.SUBCODE);
//		int sesID=Integer.parseInt(request.getParameter(TeacherParameters.SESID));
		
		if(id>0){
			request.getSession().setAttribute(TeacherParameters.DEL_ADD_SUCCESS,id);
			response.sendRedirect("registerteacher.jsp");
		}//else
			//request.getSession().setAttribute(SubjectParameters.DEL_ADD_FAILURE, SubjectParameters.DEL_ADD_FAILURE);
	}

}
