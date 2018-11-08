package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.TeacherBean;
import com.dao.TeacherDao;
import com.dao.TeacherDaoImpl;

/**
 * Servlet implementation class TeacherProfileUpdateSrv
 */
@WebServlet("/TeacherProfileUpdateSrv")
public class TeacherProfileUpdateSrv extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public static class Parameters{
    	public static final String NAME="name";
    	public static final String PASS="password";
    	public static final String ADDR="address";
    	public static final String MOB="mobile";
    	public static final String EMAIL="email";
    	public static final String DOB="dob";
    	public static final String DESG="designation";
    	public static final String MSG="message";
    }
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeacherProfileUpdateSrv() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    private boolean checkPassword(String upass,String orgpass)
    {
    	boolean flag=false;
    	if(upass.equals(orgpass))
    		flag=true;
    	return flag;
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pass=request.getParameter(Parameters.PASS);
		TeacherBean tb=(TeacherBean)request.getSession().getAttribute(LoginSrv.Parameters.TEACHERBEAN);
		String msg;
		if(checkPassword(pass, tb.getTeacherPassword())){
			TeacherDao tdao=new TeacherDaoImpl();
			int strID=(int)request.getSession().getAttribute("stream");
			String tname=request.getParameter(Parameters.NAME);
			String taddr=request.getParameter(Parameters.ADDR);
			String tdob=request.getParameter(Parameters.DOB);
			long tmob=Long.parseLong(request.getParameter(Parameters.MOB));
			String temail=request.getParameter(Parameters.EMAIL);
			String tdesg=request.getParameter(Parameters.DESG);
			TeacherBean tbean=new TeacherBean(tb.getTeacherID(), tname, taddr, tdob, tmob, temail, tb.getTeacherRole(), tdesg, tb.getTeacherPassword(), tb.getApprove());
			if(tdao.updateTeacher(tbean, strID)){
				request.getSession().setAttribute(LoginSrv.Parameters.TEACHERBEAN, tbean);
				msg="Profile has been updated successfully!!!";
			}
			else{
				msg="Profile not updated!";
			}
		}
		else{
			msg="Incorrect Password!!!";
		}
		request.getSession().setAttribute(Parameters.MSG, msg);
		response.sendRedirect("teacherupdate.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
