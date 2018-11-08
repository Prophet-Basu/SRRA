package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import weka.classifiers.functions.LinearRegression;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.experiment.InstanceQuery;

/**
 * Servlet implementation class WekaLinearRegressionSrv
 */
@WebServlet("/WekaLinearRegressionSrv")
public class WekaLinearRegressionSrv extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private class Subject{
		String name;
		int pos;
		
		Subject(String name,int pos){
			this.name=name;
			this.pos=pos;
		}
	}
	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] subjs = request.getParameterValues("subjs");
		String[] marks = request.getParameterValues("marks");
		
		HashMap<String, Subject> subjMap = new HashMap<>();
		double[] marksArray = new double[11];
		
		subjMap.put("HU101",new Subject("HU101",0));
		subjMap.put("PH101",new Subject("PH101",1));
		subjMap.put("M101",new Subject("M101",2));
		subjMap.put("ME101",new Subject("ME101",3));
		subjMap.put("EE101",new Subject("EE101",4));
		subjMap.put("CH101",new Subject("CH101",5));
		subjMap.put("PH191",new Subject("PH191",6));
		subjMap.put("EE191",new Subject("EE191",7));
		subjMap.put("ME191",new Subject("ME191",8));
		subjMap.put("ME192",new Subject("ME192",9));
		
		PrintWriter pw = response.getWriter();
		
		pw.append("Subjects:\n\n\n");
		int i=0;
		for(String s:subjs){
			pw.append(s+"\n");
			if(!s.equals("0")){
				Subject sb = subjMap.get(s);
				marksArray[sb.pos]=Double.parseDouble(marks[i++]);
			}
		}
				
		pw.append("\n\nMarks:\n\n\n");
		for(double d:marksArray)
			pw.append(d+"\n");
		
		try{
		InstanceQuery query = new InstanceQuery();
		 query.setUsername("subh");
		 query.setPassword("subh");
		 query.setQuery("select HU101,PH101,M101,ME101,EE101,CH101,PH191,EE191,ME191,ME192,SGPA1 from sem1_numeric");
		 
		 Instances data = query.retrieveInstances();
		 data.setClassIndex(data.numAttributes() - 1);
		 
		 
		 DenseInstance denseInstance = new DenseInstance(1.0, marksArray);
		 
		 denseInstance.setMissing(data.numAttributes() - 1);
		 
		 
		 for(i=0;i<marksArray.length;i++){
			 if(marksArray[i]==0.0)
				 denseInstance.setMissing(i); 
		 }
		 
		 data.add(denseInstance);
		 //System.out.println(data);
		 
		 LinearRegression model = new LinearRegression();
		 String[] options = weka.core.Utils.splitOptions("-S 0 -R 1.0E-8 -num-decimal-places 4");
		 
		 model.setOptions(options);
		 model.buildClassifier(data);
		 
		 pw.append(model.toString());
		 
		 Instance mySGPA = data.lastInstance();
		 double sgpa = model.classifyInstance(mySGPA);
		 pw.append("\n\n\nMy sgpa ("+mySGPA+"): "+sgpa);
		 
		}catch (Exception e) {
			e.printStackTrace();
			
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
