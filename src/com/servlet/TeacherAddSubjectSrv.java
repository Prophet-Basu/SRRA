package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.TeacherBean;
import com.bean.TeachesBean;
import com.dao.TeachesDao;
import com.dao.TeachesDaoImpl;

/**
 * Servlet implementation class TeacherAddSubjectSrv
 */
@WebServlet("/TeacherAddSubjectSrv")
public class TeacherAddSubjectSrv extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public static class Parameters{
    	public static final String STRID="stream";
    	public static final String TEACHERBEAN="tbean";
    	public static final String SUBCODE="subCode";
    	public static final String SESID="sesID";
    	public static final String SECNAME="secName";
    }
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeacherAddSubjectSrv() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int strID=(int)request.getSession().getAttribute("stream");
		TeacherBean tbean=(TeacherBean)request.getSession().getAttribute("tbean");
		int teacherID=tbean.getTeacherID();
		String sesID=request.getParameter(Parameters.SESID);
		String secName=request.getParameter(Parameters.SECNAME);
		String subCode=request.getParameter(Parameters.SUBCODE);
		TeachesDao tdao=new TeachesDaoImpl();
		tdao.addTeachesDetails(new TeachesBean(strID, subCode, sesID, secName, teacherID, 0));
		int type=Integer.parseInt(request.getParameter("type"));
		if(type==1){
			response.sendRedirect("teacheraddsubject.jsp");
		}
		else{
			response.sendRedirect("teacherhome.jsp");
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
