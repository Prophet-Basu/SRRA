package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.AttendanceBean;
import com.dao.AttendanceDao;
import com.dao.AttendanceDaoImpl;

/**
 * Servlet implementation class GenerateAttendanceTableSrv
 */
@WebServlet("/GenerateAttendanceTableSrv")
public class GenerateAttendanceTableSrv extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GenerateAttendanceTableSrv() {
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
		System.out.println(sesSecName);
		int semNo=Integer.parseInt(request.getParameter("semNo"));
		String subCode=request.getParameter("subCode");
		
		AttendanceBean abean=new AttendanceBean(subCode, sesSecName, semNo, null, 0);
		AttendanceDao adao=new AttendanceDaoImpl();
		HttpSession ses=request.getSession();
		if(adao.insertAttendanceDetails(abean, strID, sesID)){
			ses.setAttribute("MSG", "Attendance Details inserted and table generated");
		}
		else{
			ses.setAttribute("MSG", "Attendance Details not inserted!!!");
		}
		response.sendRedirect("attendancehome.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
