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
 * Servlet implementation class DeleteAttendanceSrv
 */
@WebServlet("/DeleteAttendanceSrv")
public class DeleteAttendanceSrv extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteAttendanceSrv() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession ses=request.getSession();
		int strID=(int)ses.getAttribute(LoginSrv.Parameters.STREAM);
		String sesID=(String)ses.getAttribute("sesID");
		AttendanceBean abean=(AttendanceBean)ses.getAttribute("ABEAN");
		String date=request.getParameter("attnDate");
		
		ses.removeAttribute("sesID");
		ses.removeAttribute("ABEAN");
		ses.removeAttribute("STLIST");
		
		AttendanceDao adao=new AttendanceDaoImpl();
		if(adao.deleteSubjectAttendanceforDate(strID, sesID, abean, date)){
			ses.setAttribute("MSG", "Attendance Details deleted successfully!!!");
		}else{
			ses.setAttribute("MSG", "Failed to delete Attendance Details! Try Again");
		}
		response.sendRedirect("SetupAttendanceSrv?strID="+strID+"&choice=Modify&sesID="+sesID+"&subCode="+abean.getSubCode()+"&semNo="+abean.getSemNo()+"&sesSecName="+abean.getSesSecName());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
