package com.servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

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
 * Servlet implementation class MarksBulkSrv
 */
@WebServlet("/MarksBulkSrv")
@MultipartConfig
public class MarksBulkSrv extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String savePath = "D:\\upload\\";
	
	public static class Parameters{
		
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
    public MarksBulkSrv() {
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
		Scanner s;
		Part sespart=request.getPart("sesID");
		s=new Scanner(sespart.getInputStream());
		String sesID=s.next();
		int strID=(int)request.getSession().getAttribute(LoginSrv.Parameters.STREAM);
		
		Part sempart=request.getPart("semNo");
		s=new Scanner(sempart.getInputStream());
		int semNo=s.nextInt();
		//System.out.print(sesID+ "   "+semNo+" "+strID);
		Part filepart=request.getPart("file");
		String filename=extractFileName(filepart);
		File folder = (File) getServletContext().getAttribute(ServletContext.TEMPDIR);
		File result = new File(folder, filename);
		
		try (InputStream input = filepart.getInputStream()) {
		    Files.copy(input, result.toPath(), StandardCopyOption.REPLACE_EXISTING);
		}
		
		String msg=BulkAddition.marksDataExtract(result, strID, sesID, semNo);
		ses.setAttribute("msg", msg);
		response.sendRedirect("marksbulkaddition.jsp");
	}

}
