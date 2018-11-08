package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.SubjectBean;
import com.dao.SubjectDao;
import com.dao.SubjectDaoImpl;

/**
 * Servlet implementation class AddSubjectSrv
 */
@WebServlet("/AddSubjectSrv")
public class AddSubjectSrv extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public static class SubjectParameters{
    	public static final String SUBJECT_CODE="subCode";
    	public static final String SUBJECT_NAME="subName";
    	public static final String SUBJECT_SEMESTER="subSem";
    	public static final String SUBJECT_TAUGHT_DURATION="subTaughtDuration";
    	public static final String STREAM_ID="strID";
    	public static final String MESSAGE= "deladdsuccess";
    
    }

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String subjSem = request.getParameter(SubjectParameters.SUBJECT_SEMESTER);
		String stream = request.getParameter(SubjectParameters.STREAM_ID);
		
		String subjCode[] = request.getParameterValues(SubjectParameters.SUBJECT_CODE);
		String subjName[] = request.getParameterValues(SubjectParameters.SUBJECT_NAME);		
		String subjTaughtDur[] = request.getParameterValues(SubjectParameters.SUBJECT_TAUGHT_DURATION);
		
		SubjectDao sdao=new SubjectDaoImpl();
		boolean flag=false;
		StringBuilder sb = new StringBuilder();
		
		try{
			for(int i=0;i<subjCode.length;i++){
				SubjectBean subBean = new SubjectBean(subjCode[i], subjName[i], Integer.parseInt(subjSem),
						Integer.parseInt(subjTaughtDur[i]),Integer.parseInt(stream));
				//System.out.println(subBean);
				flag = sdao.addSubject(subBean);
				if(flag){
					sb.append("<br/>Added Subject: "+subjName[i]);
				}else{
					sb.append("<br/>Couldnot Add Subject: "+subjName[i]);
					
				}
			}
		}catch(Exception e){
			sb.append("<br/>Error: "+e.getMessage());
		}
		
		request.getSession().setAttribute(SubjectParameters.MESSAGE, sb.toString());
		response.sendRedirect("initsetupsubject.jsp");
		

	}

}
