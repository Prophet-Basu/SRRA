package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bulk.BulkExport;

/**
 * Servlet implementation class ExportTeacherExcelSrv
 */
@WebServlet("/ExportTeacherExcelSrv")
public class ExportTeacherExcelSrv extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExportTeacherExcelSrv() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int strID=(int)request.getSession().getAttribute(LoginSrv.Parameters.STREAM);
		String filepath="D:\\";
		String msg=BulkExport.teacherBulkExport(strID, filepath);
		if(msg.equals("-1")){
			request.getSession().setAttribute("MSG", "Export Unsuccessful!!!");
			response.sendRedirect("export.jsp");
		}
		else{
			request.getSession().setAttribute("MSG", "Data Exported!!!");
			request.getSession().setAttribute("FILE", msg);
			response.sendRedirect("export.jsp");
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
