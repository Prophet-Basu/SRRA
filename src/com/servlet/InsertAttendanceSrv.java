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

/**
 * Servlet implementation class InsertAttendanceSrv
 */
@WebServlet("/InsertAttendanceSrv")
public class InsertAttendanceSrv extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertAttendanceSrv() {
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
		String presentRolls[]=request.getParameterValues("studAttn");
		System.out.println(presentRolls[0]);
		ArrayList<StudentPersonalDetailsBean> splist=(ArrayList<StudentPersonalDetailsBean>)ses.getAttribute("STLIST");
		ArrayList<Boolean> blist=new ArrayList<>();
		int i,j;
		for(i=0,j=0;i<presentRolls.length;j++){
			System.out.println(presentRolls[i]+" "+splist.get(j).getStudRoll());
			if(presentRolls[i].equals(String.valueOf(splist.get(j).getStudRoll()))){
				blist.add(true);
				i++;
			}else{
				blist.add(false);
			}
		}
		for(;j<splist.size();j++){
			blist.add(false);
		}
		int attnCount=presentRolls.length;
		
		ses.removeAttribute("sesID");
		ses.removeAttribute("ABEAN");
		ses.removeAttribute("STLIST");
		
		AttendanceDao adao=new AttendanceDaoImpl();
		if(adao.insertSubjectAttendanceforDate(strID, sesID, abean, date, blist, attnCount)){
			ses.setAttribute("MSG", "Attendance Details inserted successfully!!!");
		}else{
			ses.setAttribute("MSG", "Failed to insert Attendance Details! Try Again");
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
