package com.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.AttendanceBean;
import com.bean.StudentPersonalDetailsBean;
import com.dao.AttendanceDao;
import com.dao.AttendanceDaoImpl;
import com.dao.StudentPersonalDao;
import com.dao.StudentPersonalDaoImpl;

/**
 * Servlet implementation class SetupAttendanceSrv
 */
@WebServlet("/SetupAttendanceSrv")
public class SetupAttendanceSrv extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetupAttendanceSrv() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int strID=Integer.parseInt(request.getParameter("strID"));
		String sesID=request.getParameter("sesID");
		String sesSecName=request.getParameter("sesSecName");
		//System.out.println(sesSecName);
		int semNo=Integer.parseInt(request.getParameter("semNo"));
		String subCode=request.getParameter("subCode");
		String choice=request.getParameter("choice");
		
		HttpSession ses=request.getSession();
		ses.setAttribute("sesID", sesID);
		AttendanceBean abean=new AttendanceBean(subCode, sesSecName, semNo, null, 0);
		ses.setAttribute("ABEAN", abean);
		if(choice.equals("Insert")){
			StudentPersonalDao spdao = new StudentPersonalDaoImpl();
			ArrayList<StudentPersonalDetailsBean> studList = spdao.fetchAllStudent(strID, sesID, sesSecName);
			ses.setAttribute("STLIST", studList);
			response.sendRedirect("attendanceinsert.jsp");
		}
		else if(choice.equals("Modify")){
			AttendanceDao adao=new AttendanceDaoImpl();
			ArrayList<ArrayList<String>> aalist=adao.fetchSubjectAttendance(strID, sesID, abean);
			ArrayList<String> colList=aalist.get(0);
			ses.setAttribute("COLLIST", colList);
			ArrayList<ArrayList<String>> valList=new ArrayList<ArrayList<String>>();
			for(int i=1;i<aalist.size();i++){
				valList.add(aalist.get(i));
			}
			ses.setAttribute("VALLIST", valList);
			response.sendRedirect("attendancemodify.jsp");
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
