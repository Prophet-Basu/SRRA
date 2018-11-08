package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.TeacherBean;
import com.dao.StreamDao;
import com.dao.StreamDaoImpl;
import com.dao.TeacherDao;
import com.dao.TeacherDaoImpl;

/**
 * Servlet implementation class LoginSrv
 */
@WebServlet("/LoginSrv")
public class LoginSrv extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static class Parameters{
		public static final String USERNAME = "user";
		public static final String PASSWORD = "pass";
		public static final String STREAM = "stream";
		public static final String MODE = "mode";
		public static final String ADMINBEAN = "admin";
		public static final String TEACHERBEAN = "tbean";
		public static final String NEWADMIN = "newadmin";
		public static final String MESSAGE="message";
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = request.getParameter(Parameters.USERNAME);
		String pass = request.getParameter(Parameters.PASSWORD);
		
		
		StreamDao streamDao = new StreamDaoImpl();
		
		if(streamDao.findStreamTable()){
			//String stream = request.getParameter(Parameters.STREAM);
			String mode = request.getParameter(Parameters.MODE);
			int strID=Integer.parseInt(request.getParameter(Parameters.STREAM));
			if((user!=null)&&(pass!=null)){
				
				if(user.equals("lolwa")&&pass.equals("pass")){
					request.getSession().setAttribute(Parameters.NEWADMIN, Parameters.NEWADMIN);
					request.getSession().setAttribute(LoginSrv.Parameters.STREAM, strID);
					response.sendRedirect("admin.jsp");
				}
				else if(mode.equals("0")){
					TeacherDao tdao=new TeacherDaoImpl();
					if(tdao.validateTeacherLogin(user, pass,strID))
					{
						TeacherBean tbean=tdao.fetchTeacher(user ,pass, strID);
						if(tbean.getTeacherRole().equals("Admin")){
							request.getSession().setAttribute(Parameters.MESSAGE, "You are an Admin!! Login As Admin");
							response.sendRedirect("index.jsp");
						}
						else{
							request.getSession().setAttribute(LoginSrv.Parameters.TEACHERBEAN,tbean);
							request.getSession().setAttribute(LoginSrv.Parameters.STREAM, strID);
							response.sendRedirect("teacherhome.jsp");
						}
					}
					else{
						request.getSession().setAttribute(Parameters.MESSAGE, "Invalid Credentials or Not Verified!!!");
						response.sendRedirect("index.jsp");
					}
				}
				else{
					TeacherDao tdao=new TeacherDaoImpl();
					if(tdao.validateAdminLogin(user, pass,strID))
					{
						TeacherBean tbean=tdao.fetchTeacher(user ,pass, strID);
						request.getSession().setAttribute(LoginSrv.Parameters.TEACHERBEAN,tbean);
						request.getSession().setAttribute(LoginSrv.Parameters.STREAM, strID);
						response.sendRedirect("teacherhome.jsp");
					}
					else{
						request.getSession().setAttribute(Parameters.MESSAGE, "Invalid Credentials or Not Verified!!!");
						response.sendRedirect("index.jsp");
					}
				}
			}
			
		}else{
			if((user!=null)&&(pass!=null)){
				
				if(user.equals("lolwa")&&pass.equals("pass")){
					request.getSession().setAttribute(Parameters.NEWADMIN, Parameters.NEWADMIN);
					response.sendRedirect("admin.jsp");
				}else{
					response.sendRedirect("index.jsp");
				}
			}else{
				response.sendRedirect("index.jsp");
			}
		}
		
	}

}
