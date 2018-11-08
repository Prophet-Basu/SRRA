package com.servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.bulk.BulkAddition;

/**
 * Servlet implementation class StudentBulkSrv
 */
@WebServlet("/StudentBulkSrv")
@MultipartConfig
//(fileSizeThreshold=1024*1024*2, // 2MB
//maxFileSize=1024*1024*60,      // 60MB
//maxRequestSize=1024*1024*80)	// 80MB
public class StudentBulkSrv extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String savePath = "D:\\upload\\";

	public static class Parameters{
		//public static final String SUBJ = "subj";
		public static final String FILE = "file";
	}
	
	private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length()-1);
            }
        }
        return "";
    }
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentBulkSrv() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession ses=request.getSession();
		String sesID=request.getParameter("sesID");
		int strID=1;//(int)request.getSession().getAttribute(LoginSrv.Parameters.STREAM);
		Part filepart=request.getPart("file");
		String filename=extractFileName(filepart);
		File folder = (File) getServletContext().getAttribute(ServletContext.TEMPDIR);
		File result = new File(folder, filename);
		
		try (InputStream input = filepart.getInputStream()) {
		    Files.copy(input, result.toPath(), StandardCopyOption.REPLACE_EXISTING);
		}
		
		String msg=BulkAddition.studentDataExtract(result, strID, sesID);
		ses.setAttribute("msg", msg);
		response.sendRedirect("bulkaddition.jsp");
//		File fileSaveDir = new File(savePath);
//        if (!fileSaveDir.exists()) {
//            fileSaveDir.mkdir();
//        }
//		
//		//for (Part part : request.getParts()) {
//        	
//	            String fileName = extractFileName(filepart);
//	            String fileName2 = savePath + File.separator +fileName;
//	           
//		            filepart.write(fileName2);
//		            System.out.println(BulkAddition.studentDataExtract(fileName2, strID, sesID));
//	        
//        	
//        //}
	}

}
