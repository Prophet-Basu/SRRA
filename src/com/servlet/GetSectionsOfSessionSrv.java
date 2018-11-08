package com.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.dao.SessionDao;
import com.dao.SessionDaoImpl;
import com.util.DBSchema;

/**
 * Servlet implementation class GetSectionsOfSessionSrv
 */
@WebServlet("/GetSectionsOfSessionSrv")
public class GetSectionsOfSessionSrv extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetSectionsOfSessionSrv() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    private JSONObject mapToJson(String secName){
    	JSONObject obj = new JSONObject();
		obj.put("secName", secName);
		return obj;
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sesID=(request.getParameter("sesID"));
		int strID=(int)request.getSession().getAttribute("stream");
		System.out.println(sesID+" "+strID);
		SessionDao sdao=new SessionDaoImpl();
		
		ArrayList<String> secList=sdao.fetchSectionsforSession(strID, sesID);
		JSONObject json_response=new JSONObject();
		JSONArray jarr=new JSONArray();
		for(String s:secList){
			jarr.add(mapToJson(s));
		}
		json_response.put("sections", jarr);
		
		System.out.println(json_response.toString());
		response.setContentType("application/Json");
        
        response.getWriter().write(json_response.toString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
