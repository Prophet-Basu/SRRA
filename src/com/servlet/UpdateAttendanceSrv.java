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
 * Servlet implementation class UpdateAttendanceSrv
 */
@WebServlet("/UpdateAttendanceSrv")
public class UpdateAttendanceSrv extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateAttendanceSrv() {
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
		ArrayList<String> splist=(ArrayList<String>)ses.getAttribute("COLLIST");
		ArrayList<Boolean> blist=new ArrayList<>();
		int i,j;
		for(i=0,j=1;i<presentRolls.length;j++){
			if(presentRolls[i].equals(splist.get(j))){
				blist.add(true);
				i++;
			}else{
				blist.add(false);
			}
		}
		for(;j<splist.size()-1;j++){
			blist.add(false);
		}
		int attnCount=presentRolls.length;
		
		//String sesID=(String)ses.getAttribute("sesID");
		ses.removeAttribute("sesID");
		ses.removeAttribute("ABEAN");
		ses.removeAttribute("COLLIST");
		ses.removeAttribute("VALLIST");
		
		AttendanceDao adao=new AttendanceDaoImpl();
		if(adao.updateSubjectAttendanceforDate(strID, sesID, abean, date, blist, attnCount)){
			ses.setAttribute("MSG", "Attendance Details updated successfully!!!");
		}else{
			ses.setAttribute("MSG", "Failed to update Attendance Details! Try Again");
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
