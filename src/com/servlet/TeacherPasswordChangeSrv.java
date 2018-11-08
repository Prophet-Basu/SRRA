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
 * Servlet implementation class TeacherPasswordChangeSrv
 */
@WebServlet("/TeacherPasswordChangeSrv")
public class TeacherPasswordChangeSrv extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public static class Parameters{
    	public static final String OPASS="oldpassword";
    	public static final String NPASS="newpassword";
    	public static final String CNPASS="confirmnewpassword";
    	public static final String MSG="message";
    }
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeacherPasswordChangeSrv() {
        super();
        // TODO Auto-generated constructor stub
    }

    private boolean checkPassword(String upass,String orgpass)
    {
    	boolean flag=false;
    	if(upass.equals(orgpass))
    		flag=true;
    	return flag;
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String opass=request.getParameter(Parameters.OPASS);
		String npass=request.getParameter(Parameters.NPASS);
		String cnpass=request.getParameter(Parameters.CNPASS);
		TeacherBean tb=(TeacherBean)request.getSession().getAttribute(LoginSrv.Parameters.TEACHERBEAN);
		String msg;
		if(!checkPassword(opass, tb.getTeacherPassword())){
			msg="Old Password incorrect!";
		}
		else if(!checkPassword(npass, cnpass)){
			msg="New Passwords do not match!";
		}
		else{
			int strID=(int)request.getSession().getAttribute("stream");
			int teacherID=tb.getTeacherID();
			TeacherDao tdao=new TeacherDaoImpl();
			if(tdao.updateTeacherPassword(teacherID, strID, npass))
				msg="Password Changed!!!";
			else
				msg="Password Change Failed!";
		}
		request.getSession().setAttribute(Parameters.MSG, msg);
		response.sendRedirect("teacherpassword.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
