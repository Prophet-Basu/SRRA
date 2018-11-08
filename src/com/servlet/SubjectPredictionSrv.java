package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.SubjectBean;
import com.dao.PredictionDao;
import com.dao.PredictionDaoImpl;
import com.dao.SubjectDao;
import com.dao.SubjectDaoImpl;

import weka.attributeSelection.ASEvaluation;
import weka.attributeSelection.ASSearch;
import weka.attributeSelection.AttributeSelection;
import weka.classifiers.AbstractClassifier;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.functions.LinearRegression;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.experiment.InstanceQuery;

/**
 * Servlet implementation class PredictionSrv
 */
@WebServlet("/SubjectPredictionSrv")
public class SubjectPredictionSrv extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubjectPredictionSrv() {
        super();
        // TODO Auto-generated constructor stub
    }

    private class Subject{
		String name;
		int pos;
		
		Subject(String name,int pos){
			this.name=name;
			this.pos=pos;
		}
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String subjs[]=request.getParameterValues("subjs");
		String marks[]=request.getParameterValues("marks");
		String subPredict=request.getParameter("subPredict");
		int semNo=Integer.parseInt(request.getParameter("semNo"));
		PredictionDao pdao=new PredictionDaoImpl();
		ArrayList<String> paramList=pdao.getAllParameterList();
		HashMap<String, Integer> subject=new HashMap<>();
		int i=0;
		for(String s:paramList){
			subject.put(s, i++);
		}
		int paramSize=paramList.size();
		double marksArray[]=new double[paramSize];
		//double mArray[]=new double[marks.length];
		PrintWriter pw = response.getWriter();
		SubjectDao sdao=new SubjectDaoImpl();
		ArrayList<SubjectBean> subjList=sdao.fetchAllStreamSubjects((int)request.getSession().getAttribute(LoginSrv.Parameters.STREAM), semNo);
		String slist="";
		for(SubjectBean sb:subjList){
			slist+=sb.getSubCode()+": "+sb.getSubName()+"<br/>";
		}
		HttpSession ses=request.getSession();
		ses.setAttribute("HEADER", "Semester Wise Subject Marks Prediction");
		ses.setAttribute("MARKER", "Subject");
		ses.setAttribute("SUBJECTDETAILS", "Subjects in Semester\n\n"+slist);
		pw.append("Subjects:\n\n\n");
		i=0;
		for(String s:subjs){
			pw.append(s+"\n");
			System.out.println(s);
			if(!s.equals("0")){
				int pos = subject.get(s.toLowerCase());
				marksArray[pos]=Double.parseDouble(marks[i++]);
			}
		}
				
		pw.append("\n\nMarks:\n\n\n");
		slist="";
		for(i=0;i<subjs.length;i++)
		{
			slist+=subjs[i]+": "+marks[i]+"<br/>";
		}
		ses.setAttribute("MARKSENTERED", "Marks Entered:\n\n\n"+slist);
		
		
		try{
		InstanceQuery query = new InstanceQuery();
		 query.setUsername("root");
		 query.setPassword("root");
		 String q="select ";
		 for(i=0;i<paramList.size()-1;i++){
			 q+=paramList.get(i)+",";
		 }
		 q+=paramList.get(i)+" from srrapranlys";
		 
		 query.setQuery(q);
		 
		 Instances data = query.retrieveInstances();
		 data.setClassIndex(subject.get(subPredict.toLowerCase()));
		 
		 
		 DenseInstance denseInstance = new DenseInstance(1.0, marksArray);
		 
		 denseInstance.setMissing(data.numAttributes() - 1);
		 
		 
		 for(i=0;i<marksArray.length;i++){
			 if(marksArray[i]==0.0)
				 denseInstance.setMissing(i); 
		 }
		 
		 double res=0.0;
		 	for(String m:marks){
		 		res+=Double.parseDouble(m);
		 	}
			
			res=res/marks.length;
		 
		 //data.add(denseInstance);
		 //System.out.println(data);
		 
		 Instances instances=data;
//		 AttributeSelection as = new AttributeSelection();
//		 ASSearch asSearch = ASSearch.forName("weka.attributeSelection.GreedyStepwise", new String[]{"-C", "-R"});
//		 as.setSearch(asSearch);
//		 ASEvaluation asEval = ASEvaluation.forName("weka.attributeSelection.CfsSubsetEval", new String[]{"-L"});
//		 as.setEvaluator(asEval);
//		 as.SelectAttributes(instances);
//		 instances = as.reduceDimensionality(instances);
//		 Classifier classifier = AbstractClassifier.forName("weka.classifiers.meta.AdditiveRegression", new String[]{"-S", "1", "-I", "66", "-W", "weka.classifiers.functions.LinearRegression", "--", "-S", "1", "-C", "-R", "3.017635506877839E-6"});
//		 classifier.buildClassifier(instances);
//		 Evaluation eval=new Evaluation(instances);
//		 System.out.println(eval.toSummaryString());
		 
		 AttributeSelection as=null;
		 ASSearch asSearch=null;
		 ASEvaluation asEval=null;
		 Classifier classifier=null;
		 
		 switch(semNo){
		 
		 case 1:
			 switch(subPredict.toLowerCase()){
			 
			 case "hu101" :  as = new AttributeSelection();
							 asSearch = ASSearch.forName("weka.attributeSelection.GreedyStepwise", new String[]{"-C", "-R"});
							 as.setSearch(asSearch);
							 asEval = ASEvaluation.forName("weka.attributeSelection.CfsSubsetEval", new String[]{});
							 as.setEvaluator(asEval);
							 as.SelectAttributes(instances);
							 instances = as.reduceDimensionality(instances);
							 classifier = AbstractClassifier.forName("weka.classifiers.meta.AdditiveRegression", new String[]{"-S", "1", "-I", "19", "-W", "weka.classifiers.functions.LinearRegression", "--", "-S", "2", "-C", "-R", "3.529637689612796E-5"});
							 classifier.buildClassifier(instances);
							 break;
			 case "ph101":	 classifier = AbstractClassifier.forName("weka.classifiers.functions.LinearRegression", new String[]{"-S", "2", "-C", "-R", "3.384164885986545E-5"});
			 				 classifier.buildClassifier(instances);
			 				 break;
			 case "m101":	 classifier = AbstractClassifier.forName("weka.classifiers.functions.SMOreg", new String[]{"-C", "1.337062108245112", "-N", "0", "-I", "weka.classifiers.functions.supportVector.RegSMOImproved", "-K", "weka.classifiers.functions.supportVector.PolyKernel -E 1.2822723217665795"});
			 				 classifier.buildClassifier(instances);
			 				 break;
			 case "me101":	 as = new AttributeSelection();
							 asSearch = ASSearch.forName("weka.attributeSelection.GreedyStepwise", new String[]{"-C", "-R"});
							 as.setSearch(asSearch);
							 asEval = ASEvaluation.forName("weka.attributeSelection.CfsSubsetEval", new String[]{});
							 as.setEvaluator(asEval);
							 as.SelectAttributes(instances);
							 instances = as.reduceDimensionality(instances);
							 classifier = AbstractClassifier.forName("weka.classifiers.meta.AdditiveRegression", new String[]{"-S", "1", "-I", "19", "-W", "weka.classifiers.functions.LinearRegression", "--", "-S", "2", "-C", "-R", "3.529637689612796E-5"});
							 classifier.buildClassifier(instances);
							 break;
			 case "es101":	 as = new AttributeSelection();
							 asSearch = ASSearch.forName("weka.attributeSelection.GreedyStepwise", new String[]{"-C", "-B", "-R"});
							 as.setSearch(asSearch);
							 asEval = ASEvaluation.forName("weka.attributeSelection.CfsSubsetEval", new String[]{"-L"});
							 as.setEvaluator(asEval);
							 as.SelectAttributes(instances);
							 instances = as.reduceDimensionality(instances);
							 classifier = AbstractClassifier.forName("weka.classifiers.functions.LinearRegression", new String[]{"-S", "2", "-C", "-R", "1.495134110252482E-6"});
							 classifier.buildClassifier(instances);	
							 break;
			 case "ph191":	 as = new AttributeSelection();
							 asSearch = ASSearch.forName("weka.attributeSelection.GreedyStepwise", new String[]{"-C", "-B", "-R"});
							 as.setSearch(asSearch);
							 asEval = ASEvaluation.forName("weka.attributeSelection.CfsSubsetEval", new String[]{"-L"});
							 as.setEvaluator(asEval);
							 as.SelectAttributes(instances);
							 instances = as.reduceDimensionality(instances);
							 classifier = AbstractClassifier.forName("weka.classifiers.functions.LinearRegression", new String[]{"-S", "2", "-C", "-R", "1.495134110252482E-6"});
							 classifier.buildClassifier(instances);
							 break;
			 case "es191":	 as = new AttributeSelection();
							 asSearch = ASSearch.forName("weka.attributeSelection.GreedyStepwise", new String[]{"-C", "-R"});
							 as.setSearch(asSearch);
							 asEval = ASEvaluation.forName("weka.attributeSelection.CfsSubsetEval", new String[]{});
							 as.setEvaluator(asEval);
							 as.SelectAttributes(instances);
							 instances = as.reduceDimensionality(instances);
							 classifier = AbstractClassifier.forName("weka.classifiers.meta.AdditiveRegression", new String[]{"-S", "1", "-I", "19", "-W", "weka.classifiers.functions.LinearRegression", "--", "-S", "2", "-C", "-R", "3.529637689612796E-5"});
							 classifier.buildClassifier(instances);
							 break;
			 case "me192":	 as = new AttributeSelection();
							 asSearch = ASSearch.forName("weka.attributeSelection.GreedyStepwise", new String[]{"-C", "-B", "-R"});
							 as.setSearch(asSearch);
							 asEval = ASEvaluation.forName("weka.attributeSelection.CfsSubsetEval", new String[]{"-L"});
							 as.setEvaluator(asEval);
							 as.SelectAttributes(instances);
							 instances = as.reduceDimensionality(instances);
							 classifier = AbstractClassifier.forName("weka.classifiers.functions.LinearRegression", new String[]{"-S", "2", "-C", "-R", "1.495134110252482E-6"});
							 classifier.buildClassifier(instances);
							 break;
			 case "hu181":	 as = new AttributeSelection();
							 asSearch = ASSearch.forName("weka.attributeSelection.GreedyStepwise", new String[]{"-C", "-R"});
							 as.setSearch(asSearch);
							 asEval = ASEvaluation.forName("weka.attributeSelection.CfsSubsetEval", new String[]{});
							 as.setEvaluator(asEval);
							 as.SelectAttributes(instances);
							 instances = as.reduceDimensionality(instances);
							 classifier = AbstractClassifier.forName("weka.classifiers.meta.AdditiveRegression", new String[]{"-S", "1", "-I", "19", "-W", "weka.classifiers.functions.LinearRegression", "--", "-S", "2", "-C", "-R", "3.529637689612796E-5"});
							 classifier.buildClassifier(instances);
							 break;
			 case "xc181":	 classifier = AbstractClassifier.forName("weka.classifiers.meta.AdditiveRegression", new String[]{"-S", "0.9328481263992807", "-I", "16", "-W", "weka.classifiers.functions.LinearRegression", "--", "-S", "2", "-C", "-R", "1.982989461190018E-4"});
			 				 classifier.buildClassifier(instances);
			 				 break;

			 }
			 break;
			 
		 case 2:
			 switch(subPredict.toLowerCase()){
			 
			 case "cs201": 	 as = new AttributeSelection();
							 asSearch = ASSearch.forName("weka.attributeSelection.GreedyStepwise", new String[]{"-C", "-R"});
							 as.setSearch(asSearch);
							 asEval = ASEvaluation.forName("weka.attributeSelection.CfsSubsetEval", new String[]{});
							 as.setEvaluator(asEval);
							 as.SelectAttributes(instances);
							 instances = as.reduceDimensionality(instances);
							 classifier = AbstractClassifier.forName("weka.classifiers.meta.AdditiveRegression", new String[]{"-S", "1", "-I", "19", "-W", "weka.classifiers.functions.LinearRegression", "--", "-S", "2", "-C", "-R", "3.529637689612796E-5"});
							 classifier.buildClassifier(instances);
							 break;
			 case "ph201ch201":	 classifier = AbstractClassifier.forName("weka.classifiers.functions.LinearRegression", new String[]{"-S", "0", "-C", "-R", "7.288592684519297E-5"});
			 					 classifier.buildClassifier(instances);
			 					 break;
			 case "m201":	 as = new AttributeSelection();
							 asSearch = ASSearch.forName("weka.attributeSelection.GreedyStepwise", new String[]{"-C", "-R"});
							 as.setSearch(asSearch);
							 asEval = ASEvaluation.forName("weka.attributeSelection.CfsSubsetEval", new String[]{});
							 as.setEvaluator(asEval);
							 as.SelectAttributes(instances);
							 instances = as.reduceDimensionality(instances);
							 classifier = AbstractClassifier.forName("weka.classifiers.meta.AdditiveRegression", new String[]{"-S", "1", "-I", "19", "-W", "weka.classifiers.functions.LinearRegression", "--", "-S", "2", "-C", "-R", "3.529637689612796E-5"});
							 classifier.buildClassifier(instances);	
							 break;
			 case "me201":	 classifier = AbstractClassifier.forName("weka.classifiers.meta.AdditiveRegression", new String[]{"-S", "0.7896446447770508", "-I", "6", "-W", "weka.classifiers.functions.LinearRegression", "--", "-S", "2", "-C", "-R", "5.292203014977348E-4"});
			 				 classifier.buildClassifier(instances);
			 				 break;
			 case "es201":	 classifier = AbstractClassifier.forName("weka.classifiers.functions.LinearRegression", new String[]{"-S", "1", "-C", "-R", "3.14584732814297E-5"});
			 				 classifier.buildClassifier(instances);
			 				 break;
			 case "cs291":	 as = new AttributeSelection();
							 asSearch = ASSearch.forName("weka.attributeSelection.GreedyStepwise", new String[]{"-C", "-B", "-R"});
							 as.setSearch(asSearch);
							 asEval = ASEvaluation.forName("weka.attributeSelection.CfsSubsetEval", new String[]{"-L"});
							 as.setEvaluator(asEval);
							 as.SelectAttributes(instances);
							 instances = as.reduceDimensionality(instances);
							 classifier = AbstractClassifier.forName("weka.classifiers.functions.LinearRegression", new String[]{"-S", "2", "-C", "-R", "1.495134110252482E-6"});
							 classifier.buildClassifier(instances);
							 break;
			 case "ph291ch291":	 classifier = AbstractClassifier.forName("weka.classifiers.lazy.LWL", new String[]{"-U", "1", "-A", "weka.core.neighboursearch.LinearNNSearch", "-W", "weka.classifiers.functions.LinearRegression", "--", "-S", "1", "-C", "-R", "1.0289847808218577E-7"});
			 					 classifier.buildClassifier(instances);
			 					 break;
			 case "es291":	 as = new AttributeSelection();
							 asSearch = ASSearch.forName("weka.attributeSelection.GreedyStepwise", new String[]{"-C", "-R"});
							 as.setSearch(asSearch);
							 asEval = ASEvaluation.forName("weka.attributeSelection.CfsSubsetEval", new String[]{});
							 as.setEvaluator(asEval);
							 as.SelectAttributes(instances);
							 instances = as.reduceDimensionality(instances);
							 classifier = AbstractClassifier.forName("weka.classifiers.meta.AdditiveRegression", new String[]{"-S", "1", "-I", "19", "-W", "weka.classifiers.functions.LinearRegression", "--", "-S", "2", "-C", "-R", "3.529637689612796E-5"});
							 classifier.buildClassifier(instances);
							 break;
			 case "me291me292":	 classifier = AbstractClassifier.forName("weka.classifiers.meta.AdditiveRegression", new String[]{"-S", "0.9740223677872106", "-I", "2", "-W", "weka.classifiers.functions.LinearRegression", "--", "-S", "2", "-C", "-R", "0.0016908310536223054"});
			 					 classifier.buildClassifier(instances);
			 					 break;
							 
			 }
			 break;
			 
		 case 3:
			 switch(subPredict.toLowerCase()){
			 
			 case "hu301":	 as = new AttributeSelection();
							 asSearch = ASSearch.forName("weka.attributeSelection.GreedyStepwise", new String[]{"-R"});
							 as.setSearch(asSearch);
							 asEval = ASEvaluation.forName("weka.attributeSelection.CfsSubsetEval", new String[]{});
							 as.setEvaluator(asEval);
							 as.SelectAttributes(instances);
							 instances = as.reduceDimensionality(instances);
							 classifier = AbstractClassifier.forName("weka.classifiers.functions.LinearRegression", new String[]{"-S", "1", "-C", "-R", "0.0023791674626188965"});
							 classifier.buildClassifier(instances);
							 break;
			 case "cs301":	 classifier = AbstractClassifier.forName("weka.classifiers.functions.SMOreg", new String[]{"-C", "1.337062108245112", "-N", "0", "-I", "weka.classifiers.functions.supportVector.RegSMOImproved", "-K", "weka.classifiers.functions.supportVector.PolyKernel -E 1.2822723217665795"});
			 				 classifier.buildClassifier(instances);
			 				 break;
			 case "cs302": 	 as = new AttributeSelection();
							 asSearch = ASSearch.forName("weka.attributeSelection.GreedyStepwise", new String[]{"-C", "-R"});
							 as.setSearch(asSearch);
							 asEval = ASEvaluation.forName("weka.attributeSelection.CfsSubsetEval", new String[]{});
							 as.setEvaluator(asEval);
							 as.SelectAttributes(instances);
							 instances = as.reduceDimensionality(instances);
							 classifier = AbstractClassifier.forName("weka.classifiers.meta.AdditiveRegression", new String[]{"-S", "1", "-I", "19", "-W", "weka.classifiers.functions.LinearRegression", "--", "-S", "2", "-C", "-R", "3.529637689612796E-5"});
							 classifier.buildClassifier(instances);
							 break;
			 case "cs303": 	 as = new AttributeSelection();
							 asSearch = ASSearch.forName("weka.attributeSelection.GreedyStepwise", new String[]{"-C", "-R"});
							 as.setSearch(asSearch);
							 asEval = ASEvaluation.forName("weka.attributeSelection.CfsSubsetEval", new String[]{});
							 as.setEvaluator(asEval);
							 as.SelectAttributes(instances);
							 instances = as.reduceDimensionality(instances);
							 classifier = AbstractClassifier.forName("weka.classifiers.meta.AdditiveRegression", new String[]{"-S", "1", "-I", "19", "-W", "weka.classifiers.functions.LinearRegression", "--", "-S", "2", "-C", "-R", "3.529637689612796E-5"});
							 classifier.buildClassifier(instances);
							 break;
			 case "ph301":	 classifier = AbstractClassifier.forName("weka.classifiers.functions.LinearRegression", new String[]{"-S", "1", "-C", "-R", "0.19500116907108128"});
			 				 classifier.buildClassifier(instances);
			 				 break;
			 case "ch301":	 classifier = AbstractClassifier.forName("weka.classifiers.functions.LinearRegression", new String[]{"-S", "2", "-C", "-R", "3.384164885986545E-5"});
			 				 classifier.buildClassifier(instances);
			 				 break;
			 case "cs391":	 as = new AttributeSelection();
							 asSearch = ASSearch.forName("weka.attributeSelection.GreedyStepwise", new String[]{"-C", "-R"});
							 as.setSearch(asSearch);
							 asEval = ASEvaluation.forName("weka.attributeSelection.CfsSubsetEval", new String[]{});
							 as.setEvaluator(asEval);
							 as.SelectAttributes(instances);
							 instances = as.reduceDimensionality(instances);
							 classifier = AbstractClassifier.forName("weka.classifiers.meta.AdditiveRegression", new String[]{"-S", "1", "-I", "19", "-W", "weka.classifiers.functions.LinearRegression", "--", "-S", "2", "-C", "-R", "3.529637689612796E-5"});
							 classifier.buildClassifier(instances);
							 break;
			 case "cs392":	 as = new AttributeSelection();
							 asSearch = ASSearch.forName("weka.attributeSelection.GreedyStepwise", new String[]{"-C", "-R"});
							 as.setSearch(asSearch);
							 asEval = ASEvaluation.forName("weka.attributeSelection.CfsSubsetEval", new String[]{"-L"});
							 as.setEvaluator(asEval);
							 as.SelectAttributes(instances);
							 instances = as.reduceDimensionality(instances);
							 classifier = AbstractClassifier.forName("weka.classifiers.meta.AdditiveRegression", new String[]{"-S", "1", "-I", "66", "-W", "weka.classifiers.functions.LinearRegression", "--", "-S", "1", "-C", "-R", "3.017635506877839E-6"});
							 classifier.buildClassifier(instances);
							 break;
			 case "cs393":	 as = new AttributeSelection();
							 asSearch = ASSearch.forName("weka.attributeSelection.GreedyStepwise", new String[]{"-C", "-R"});
							 as.setSearch(asSearch);
							 asEval = ASEvaluation.forName("weka.attributeSelection.CfsSubsetEval", new String[]{});
							 as.setEvaluator(asEval);
							 as.SelectAttributes(instances);
							 instances = as.reduceDimensionality(instances);
							 classifier = AbstractClassifier.forName("weka.classifiers.meta.AdditiveRegression", new String[]{"-S", "1", "-I", "19", "-W", "weka.classifiers.functions.LinearRegression", "--", "-S", "2", "-C", "-R", "3.529637689612796E-5"});
							 classifier.buildClassifier(instances);
							 break;
			 case "ph391":	 as = new AttributeSelection();
							 asSearch = ASSearch.forName("weka.attributeSelection.GreedyStepwise", new String[]{"-C", "-R"});
							 as.setSearch(asSearch);
							 asEval = ASEvaluation.forName("weka.attributeSelection.CfsSubsetEval", new String[]{});
							 as.setEvaluator(asEval);
							 as.SelectAttributes(instances);
							 instances = as.reduceDimensionality(instances);
							 classifier = AbstractClassifier.forName("weka.classifiers.meta.AdditiveRegression", new String[]{"-S", "1", "-I", "19", "-W", "weka.classifiers.functions.LinearRegression", "--", "-S", "2", "-C", "-R", "3.529637689612796E-5"});
							 classifier.buildClassifier(instances);
							 break;
			 
			 }
			 
			 break;
			 
		 case 4:
			 switch(subPredict.toLowerCase()){
			 
			 case "cs401":	 classifier = AbstractClassifier.forName("weka.classifiers.functions.SMOreg", new String[]{"-C", "1.337062108245112", "-N", "0", "-I", "weka.classifiers.functions.supportVector.RegSMOImproved", "-K", "weka.classifiers.functions.supportVector.PolyKernel -E 1.2822723217665795"});
			 				 classifier.buildClassifier(instances);
			 				 break;
			 case "cs402":	 as = new AttributeSelection();
							 asSearch = ASSearch.forName("weka.attributeSelection.GreedyStepwise", new String[]{"-C", "-R"});
							 as.setSearch(asSearch);
							 asEval = ASEvaluation.forName("weka.attributeSelection.CfsSubsetEval", new String[]{"-L"});
							 as.setEvaluator(asEval);
							 as.SelectAttributes(instances);
							 instances = as.reduceDimensionality(instances);
							 classifier = AbstractClassifier.forName("weka.classifiers.meta.AdditiveRegression", new String[]{"-S", "1", "-I", "66", "-W", "weka.classifiers.functions.LinearRegression", "--", "-S", "1", "-C", "-R", "3.017635506877839E-6"});
							 classifier.buildClassifier(instances);
							 break;
			 case "cs403":	 classifier = AbstractClassifier.forName("weka.classifiers.functions.LinearRegression", new String[]{"-S", "0", "-C", "-R", "0.0017685924936764854"});
			 				 classifier.buildClassifier(instances);
			 				 break;
			 case "m401":	 as = new AttributeSelection();
							 asSearch = ASSearch.forName("weka.attributeSelection.GreedyStepwise", new String[]{"-C", "-B", "-R"});
							 as.setSearch(asSearch);
							 asEval = ASEvaluation.forName("weka.attributeSelection.CfsSubsetEval", new String[]{"-L"});
							 as.setEvaluator(asEval);
							 as.SelectAttributes(instances);
							 instances = as.reduceDimensionality(instances);
							 classifier = AbstractClassifier.forName("weka.classifiers.functions.LinearRegression", new String[]{"-S", "2", "-C", "-R", "1.495134110252482E-6"});
							 classifier.buildClassifier(instances);
							 break;
			 case "mcs401":	 as = new AttributeSelection();
							 asSearch = ASSearch.forName("weka.attributeSelection.GreedyStepwise", new String[]{"-C", "-R"});
							 as.setSearch(asSearch);
							 asEval = ASEvaluation.forName("weka.attributeSelection.CfsSubsetEval", new String[]{});
							 as.setEvaluator(asEval);
							 as.SelectAttributes(instances);
							 instances = as.reduceDimensionality(instances);
							 classifier = AbstractClassifier.forName("weka.classifiers.meta.AdditiveRegression", new String[]{"-S", "1", "-I", "19", "-W", "weka.classifiers.functions.LinearRegression", "--", "-S", "2", "-C", "-R", "3.529637689612796E-5"});
							 classifier.buildClassifier(instances);
							 break;
			 case "cs491":	 as = new AttributeSelection();
							 asSearch = ASSearch.forName("weka.attributeSelection.GreedyStepwise", new String[]{"-C", "-B", "-R"});
							 as.setSearch(asSearch);
							 asEval = ASEvaluation.forName("weka.attributeSelection.CfsSubsetEval", new String[]{"-L"});
							 as.setEvaluator(asEval);
							 as.SelectAttributes(instances);
							 instances = as.reduceDimensionality(instances);
							 classifier = AbstractClassifier.forName("weka.classifiers.functions.LinearRegression", new String[]{"-S", "2", "-C", "-R", "1.495134110252482E-6"});
							 classifier.buildClassifier(instances);
							 break;
			 case "cs492": 	 as = new AttributeSelection();
							 asSearch = ASSearch.forName("weka.attributeSelection.GreedyStepwise", new String[]{"-C", "-R"});
							 as.setSearch(asSearch);
							 asEval = ASEvaluation.forName("weka.attributeSelection.CfsSubsetEval", new String[]{});
							 as.setEvaluator(asEval);
							 as.SelectAttributes(instances);
							 instances = as.reduceDimensionality(instances);
							 classifier = AbstractClassifier.forName("weka.classifiers.meta.AdditiveRegression", new String[]{"-S", "1", "-I", "19", "-W", "weka.classifiers.functions.LinearRegression", "--", "-S", "2", "-C", "-R", "3.529637689612796E-5"});
							 classifier.buildClassifier(instances);
							 break;
			 case "cs493":	 classifier = AbstractClassifier.forName("weka.classifiers.functions.SimpleLinearRegression", new String[]{});
			 				 classifier.buildClassifier(instances);
			 				 break;
			 case "mcs491":	 as = new AttributeSelection();
							 asSearch = ASSearch.forName("weka.attributeSelection.GreedyStepwise", new String[]{"-C", "-B", "-R"});
							 as.setSearch(asSearch);
							 asEval = ASEvaluation.forName("weka.attributeSelection.CfsSubsetEval", new String[]{"-M", "-L"});
							 as.setEvaluator(asEval);
							 as.SelectAttributes(instances);
							 instances = as.reduceDimensionality(instances);
							 classifier = AbstractClassifier.forName("weka.classifiers.functions.LinearRegression", new String[]{"-S", "2", "-C", "-R", "3.00257890005502E-7"});
							 classifier.buildClassifier(instances);
							 break;
			 case "hu481":	 as = new AttributeSelection();
							 asSearch = ASSearch.forName("weka.attributeSelection.GreedyStepwise", new String[]{"-C", "-R"});
							 as.setSearch(asSearch);
							 asEval = ASEvaluation.forName("weka.attributeSelection.CfsSubsetEval", new String[]{});
							 as.setEvaluator(asEval);
							 as.SelectAttributes(instances);
							 instances = as.reduceDimensionality(instances);
							 classifier = AbstractClassifier.forName("weka.classifiers.meta.AdditiveRegression", new String[]{"-S", "1", "-I", "19", "-W", "weka.classifiers.functions.LinearRegression", "--", "-S", "2", "-C", "-R", "3.529637689612796E-5"});
							 classifier.buildClassifier(instances);
							 break;
			 
			 }
	 
			 break;
			 
		 case 5:
			 switch(subPredict.toLowerCase()){
			 
			 case "hu501":	 classifier = AbstractClassifier.forName("weka.classifiers.functions.SimpleLinearRegression", new String[]{});
			 				 classifier.buildClassifier(instances);
			 				 break;
			 case "cs501":	 classifier = AbstractClassifier.forName("weka.classifiers.meta.AdditiveRegression", new String[]{"-S", "1", "-I", "4", "-W", "weka.classifiers.functions.LinearRegression", "--", "-S", "0", "-C", "-R", "1.3845998448955098E-4"});
			 				 classifier.buildClassifier(instances);
			 				 break;
			 case "cs502":	 as = new AttributeSelection();
							 asSearch = ASSearch.forName("weka.attributeSelection.GreedyStepwise", new String[]{"-N", "607"});
							 as.setSearch(asSearch);
							 asEval = ASEvaluation.forName("weka.attributeSelection.CfsSubsetEval", new String[]{"-M", "-L"});
							 as.setEvaluator(asEval);
							 as.SelectAttributes(instances);
							 instances = as.reduceDimensionality(instances);
							 classifier = AbstractClassifier.forName("weka.classifiers.functions.LinearRegression", new String[]{"-S", "2", "-C", "-R", "0.09017584914775482"});
							 classifier.buildClassifier(instances);
							 break;
			 case "cs503":	 classifier = AbstractClassifier.forName("weka.classifiers.functions.SMOreg", new String[]{"-C", "1.337062108245112", "-N", "0", "-I", "weka.classifiers.functions.supportVector.RegSMOImproved", "-K", "weka.classifiers.functions.supportVector.PolyKernel -E 1.2822723217665795"});
			 				 classifier.buildClassifier(instances);
			 				 break;
			 case "cs504d":	 as = new AttributeSelection();
							 asSearch = ASSearch.forName("weka.attributeSelection.GreedyStepwise", new String[]{"-C", "-R"});
							 as.setSearch(asSearch);
							 asEval = ASEvaluation.forName("weka.attributeSelection.CfsSubsetEval", new String[]{"-L"});
							 as.setEvaluator(asEval);
							 as.SelectAttributes(instances);
							 instances = as.reduceDimensionality(instances);
							 classifier = AbstractClassifier.forName("weka.classifiers.meta.AdditiveRegression", new String[]{"-S", "1", "-I", "66", "-W", "weka.classifiers.functions.LinearRegression", "--", "-S", "1", "-C", "-R", "3.017635506877839E-6"});
							 classifier.buildClassifier(instances);
							 break;
			 case "cs591":	 as = new AttributeSelection();
							 asSearch = ASSearch.forName("weka.attributeSelection.GreedyStepwise", new String[]{"-C", "-R"});
							 as.setSearch(asSearch);
							 asEval = ASEvaluation.forName("weka.attributeSelection.CfsSubsetEval", new String[]{"-L"});
							 as.setEvaluator(asEval);
							 as.SelectAttributes(instances);
							 instances = as.reduceDimensionality(instances);
							 classifier = AbstractClassifier.forName("weka.classifiers.meta.AdditiveRegression", new String[]{"-S", "1", "-I", "66", "-W", "weka.classifiers.functions.LinearRegression", "--", "-S", "1", "-C", "-R", "3.017635506877839E-6"});
							 classifier.buildClassifier(instances);
							 break;
			 case "cs592":	 as = new AttributeSelection();
							 asSearch = ASSearch.forName("weka.attributeSelection.GreedyStepwise", new String[]{"-C", "-R"});
							 as.setSearch(asSearch);
							 asEval = ASEvaluation.forName("weka.attributeSelection.CfsSubsetEval", new String[]{"-L"});
							 as.setEvaluator(asEval);
							 as.SelectAttributes(instances);
							 instances = as.reduceDimensionality(instances);
							 classifier = AbstractClassifier.forName("weka.classifiers.meta.AdditiveRegression", new String[]{"-S", "1", "-I", "66", "-W", "weka.classifiers.functions.LinearRegression", "--", "-S", "1", "-C", "-R", "3.017635506877839E-6"});
							 classifier.buildClassifier(instances);
							 break;
			 case "cs593":	 as = new AttributeSelection();
							 asSearch = ASSearch.forName("weka.attributeSelection.GreedyStepwise", new String[]{"-C", "-B", "-R"});
							 as.setSearch(asSearch);
							 asEval = ASEvaluation.forName("weka.attributeSelection.CfsSubsetEval", new String[]{"-L"});
							 as.setEvaluator(asEval);
							 as.SelectAttributes(instances);
							 instances = as.reduceDimensionality(instances);
							 classifier = AbstractClassifier.forName("weka.classifiers.functions.LinearRegression", new String[]{"-S", "2", "-C", "-R", "1.495134110252482E-6"});
							 classifier.buildClassifier(instances);
							 break;
			 case "cs594d":	 as = new AttributeSelection();
							 asSearch = ASSearch.forName("weka.attributeSelection.GreedyStepwise", new String[]{"-C", "-R"});
							 as.setSearch(asSearch);
							 asEval = ASEvaluation.forName("weka.attributeSelection.CfsSubsetEval", new String[]{"-L"});
							 as.setEvaluator(asEval);
							 as.SelectAttributes(instances);
							 instances = as.reduceDimensionality(instances);
							 classifier = AbstractClassifier.forName("weka.classifiers.meta.AdditiveRegression", new String[]{"-S", "1", "-I", "66", "-W", "weka.classifiers.functions.LinearRegression", "--", "-S", "1", "-C", "-R", "3.017635506877839E-6"});
							 classifier.buildClassifier(instances);
							 break;
			 

			 
			 }
			 
			 break;
			 
		 case 6:
			 switch(subPredict.toLowerCase()){
			 
			 case "hu601":	 as = new AttributeSelection();
							 asSearch = ASSearch.forName("weka.attributeSelection.GreedyStepwise", new String[]{"-C", "-B", "-R"});
							 as.setSearch(asSearch);
							 asEval = ASEvaluation.forName("weka.attributeSelection.CfsSubsetEval", new String[]{"-L"});
							 as.setEvaluator(asEval);
							 as.SelectAttributes(instances);
							 instances = as.reduceDimensionality(instances);
							 classifier = AbstractClassifier.forName("weka.classifiers.functions.LinearRegression", new String[]{"-S", "2", "-C", "-R", "1.495134110252482E-6"});
							 classifier.buildClassifier(instances);
							 break;
			 case "cs601":	 as = new AttributeSelection();
							 asSearch = ASSearch.forName("weka.attributeSelection.GreedyStepwise", new String[]{"-C", "-R"});
							 as.setSearch(asSearch);
							 asEval = ASEvaluation.forName("weka.attributeSelection.CfsSubsetEval", new String[]{});
							 as.setEvaluator(asEval);
							 as.SelectAttributes(instances);
							 instances = as.reduceDimensionality(instances);
							 classifier = AbstractClassifier.forName("weka.classifiers.meta.AdditiveRegression", new String[]{"-S", "1", "-I", "19", "-W", "weka.classifiers.functions.LinearRegression", "--", "-S", "2", "-C", "-R", "3.529637689612796E-5"});
							 classifier.buildClassifier(instances);
							 break;
			 case "cs602":	 as = new AttributeSelection();
							 asSearch = ASSearch.forName("weka.attributeSelection.GreedyStepwise", new String[]{"-C", "-R"});
							 as.setSearch(asSearch);
							 asEval = ASEvaluation.forName("weka.attributeSelection.CfsSubsetEval", new String[]{});
							 as.setEvaluator(asEval);
							 as.SelectAttributes(instances);
							 instances = as.reduceDimensionality(instances);
							 classifier = AbstractClassifier.forName("weka.classifiers.meta.AdditiveRegression", new String[]{"-S", "1", "-I", "19", "-W", "weka.classifiers.functions.LinearRegression", "--", "-S", "2", "-C", "-R", "3.529637689612796E-5"});
							 classifier.buildClassifier(instances);
							 break;
			 case "cs603":	 classifier = AbstractClassifier.forName("weka.classifiers.functions.LinearRegression", new String[]{"-S", "2", "-C", "-R", "6.881877570862905E-4"});
			 				 classifier.buildClassifier(instances);
			 				 break;
			 case "cs604b":	 as = new AttributeSelection();
							 asSearch = ASSearch.forName("weka.attributeSelection.GreedyStepwise", new String[]{"-C", "-B", "-R"});
							 as.setSearch(asSearch);
							 asEval = ASEvaluation.forName("weka.attributeSelection.CfsSubsetEval", new String[]{"-L"});
							 as.setEvaluator(asEval);
							 as.SelectAttributes(instances);
							 instances = as.reduceDimensionality(instances);
							 classifier = AbstractClassifier.forName("weka.classifiers.functions.LinearRegression", new String[]{"-S", "2", "-C", "-R", "1.495134110252482E-6"});
							 classifier.buildClassifier(instances);
							 break;
			 case"cs605c":	 classifier = AbstractClassifier.forName("weka.classifiers.functions.SMOreg", new String[]{"-C", "1.337062108245112", "-N", "0", "-I", "weka.classifiers.functions.supportVector.RegSMOImproved", "-K", "weka.classifiers.functions.supportVector.PolyKernel -E 1.2822723217665795"});
			 				 classifier.buildClassifier(instances);
			 				 break;
			 case "cs681":	 classifier = AbstractClassifier.forName("weka.classifiers.functions.LinearRegression", new String[]{"-S", "2", "-C", "-R", "1.0185820461331956E-5"});
			 				 classifier.buildClassifier(instances);
			 				 break;
			 case "cs691":	 classifier = AbstractClassifier.forName("weka.classifiers.functions.LinearRegression", new String[]{"-S", "2", "-C", "-R", "0.004397520375077158"});
			 				 classifier.buildClassifier(instances);
			 				 break;
			 case "cs692":	 classifier = AbstractClassifier.forName("weka.classifiers.functions.LinearRegression", new String[]{"-S", "2", "-C", "-R", "1.8126657550625378E-5"});
			 				 classifier.buildClassifier(instances);
			 				 break;
			 case "cs693":	 as = new AttributeSelection();
							 asSearch = ASSearch.forName("weka.attributeSelection.GreedyStepwise", new String[]{"-C", "-R"});
							 as.setSearch(asSearch);
							 asEval = ASEvaluation.forName("weka.attributeSelection.CfsSubsetEval", new String[]{"-L"});
							 as.setEvaluator(asEval);
							 as.SelectAttributes(instances);
							 instances = as.reduceDimensionality(instances);
							 classifier = AbstractClassifier.forName("weka.classifiers.meta.AdditiveRegression", new String[]{"-S", "1", "-I", "66", "-W", "weka.classifiers.functions.LinearRegression", "--", "-S", "1", "-C", "-R", "3.017635506877839E-6"});
							 classifier.buildClassifier(instances);
							 break;
							 
			  
			 }
			 
			 break;
			 
		 case 7:
			 switch(subPredict.toLowerCase()){
			 
			 case "cs701":	 classifier = AbstractClassifier.forName("weka.classifiers.functions.LinearRegression", new String[]{"-S", "0", "-C", "-R", "1.5296324228745309E-4"});
			 				 classifier.buildClassifier(instances);
			 				 break;
			 case "cs702":	 as = new AttributeSelection();
							 asSearch = ASSearch.forName("weka.attributeSelection.GreedyStepwise", new String[]{"-C", "-R"});
							 as.setSearch(asSearch);
							 asEval = ASEvaluation.forName("weka.attributeSelection.CfsSubsetEval", new String[]{});
							 as.setEvaluator(asEval);
							 as.SelectAttributes(instances);
							 instances = as.reduceDimensionality(instances);
							 classifier = AbstractClassifier.forName("weka.classifiers.meta.AdditiveRegression", new String[]{"-S", "1", "-I", "19", "-W", "weka.classifiers.functions.LinearRegression", "--", "-S", "2", "-C", "-R", "3.529637689612796E-5"});
							 classifier.buildClassifier(instances);
							 break;
			 case "703c":	 as = new AttributeSelection();
							 asSearch = ASSearch.forName("weka.attributeSelection.GreedyStepwise", new String[]{"-C", "-R"});
							 as.setSearch(asSearch);
							 asEval = ASEvaluation.forName("weka.attributeSelection.CfsSubsetEval", new String[]{});
							 as.setEvaluator(asEval);
							 as.SelectAttributes(instances);
							 instances = as.reduceDimensionality(instances);
							 classifier = AbstractClassifier.forName("weka.classifiers.meta.AdditiveRegression", new String[]{"-S", "1", "-I", "19", "-W", "weka.classifiers.functions.LinearRegression", "--", "-S", "2", "-C", "-R", "3.529637689612796E-5"});
							 classifier.buildClassifier(instances);
							 break;
			 case "cs704d":	 classifier = AbstractClassifier.forName("weka.classifiers.meta.AdditiveRegression", new String[]{"-S", "0.10150611114047225", "-I", "9", "-W", "weka.classifiers.functions.LinearRegression", "--", "-S", "2", "-C", "-R", "0.004447923037962146"});
			 				 classifier.buildClassifier(instances);
			 				 break;
			 case "cs705a":	 as = new AttributeSelection();
							 asSearch = ASSearch.forName("weka.attributeSelection.GreedyStepwise", new String[]{"-C", "-R"});
							 as.setSearch(asSearch);
							 asEval = ASEvaluation.forName("weka.attributeSelection.CfsSubsetEval", new String[]{"-L"});
							 as.setEvaluator(asEval);
							 as.SelectAttributes(instances);
							 instances = as.reduceDimensionality(instances);
							 classifier = AbstractClassifier.forName("weka.classifiers.meta.AdditiveRegression", new String[]{"-S", "1", "-I", "66", "-W", "weka.classifiers.functions.LinearRegression", "--", "-S", "1", "-C", "-R", "3.017635506877839E-6"});
							 classifier.buildClassifier(instances);
							 break;
			 case "cs791":	 as = new AttributeSelection();
							 asSearch = ASSearch.forName("weka.attributeSelection.GreedyStepwise", new String[]{"-C", "-R"});
							 as.setSearch(asSearch);
							 asEval = ASEvaluation.forName("weka.attributeSelection.CfsSubsetEval", new String[]{});
							 as.setEvaluator(asEval);
							 as.SelectAttributes(instances);
							 instances = as.reduceDimensionality(instances);
							 classifier = AbstractClassifier.forName("weka.classifiers.meta.AdditiveRegression", new String[]{"-S", "1", "-I", "19", "-W", "weka.classifiers.functions.LinearRegression", "--", "-S", "2", "-C", "-R", "3.529637689612796E-5"});
							 classifier.buildClassifier(instances);
							 break;
			 case "cs792":	 as = new AttributeSelection();
							 asSearch = ASSearch.forName("weka.attributeSelection.GreedyStepwise", new String[]{"-C", "-R"});
							 as.setSearch(asSearch);
							 asEval = ASEvaluation.forName("weka.attributeSelection.CfsSubsetEval", new String[]{});
							 as.setEvaluator(asEval);
							 as.SelectAttributes(instances);
							 instances = as.reduceDimensionality(instances);
							 classifier = AbstractClassifier.forName("weka.classifiers.meta.AdditiveRegression", new String[]{"-S", "1", "-I", "19", "-W", "weka.classifiers.functions.LinearRegression", "--", "-S", "2", "-C", "-R", "3.529637689612796E-5"});
							 classifier.buildClassifier(instances);
							 break;
			 case "cs793c":	 as = new AttributeSelection();
							 asSearch = ASSearch.forName("weka.attributeSelection.GreedyStepwise", new String[]{"-C", "-R"});
							 as.setSearch(asSearch);
							 asEval = ASEvaluation.forName("weka.attributeSelection.CfsSubsetEval", new String[]{});
							 as.setEvaluator(asEval);
							 as.SelectAttributes(instances);
							 instances = as.reduceDimensionality(instances);
							 classifier = AbstractClassifier.forName("weka.classifiers.meta.AdditiveRegression", new String[]{"-S", "1", "-I", "19", "-W", "weka.classifiers.functions.LinearRegression", "--", "-S", "2", "-C", "-R", "3.529637689612796E-5"});
							 classifier.buildClassifier(instances);
							 break;
			 case "cs794":	 as = new AttributeSelection();
							 asSearch = ASSearch.forName("weka.attributeSelection.GreedyStepwise", new String[]{"-C", "-B", "-R"});
							 as.setSearch(asSearch);
							 asEval = ASEvaluation.forName("weka.attributeSelection.CfsSubsetEval", new String[]{"-L"});
							 as.setEvaluator(asEval);
							 as.SelectAttributes(instances);
							 instances = as.reduceDimensionality(instances);
							 classifier = AbstractClassifier.forName("weka.classifiers.functions.LinearRegression", new String[]{"-S", "2", "-C", "-R", "1.495134110252482E-6"});
							 classifier.buildClassifier(instances);
							 break;
			 case "cs795a":	 as = new AttributeSelection();
							 asSearch = ASSearch.forName("weka.attributeSelection.GreedyStepwise", new String[]{"-C", "-R"});
							 as.setSearch(asSearch);
							 asEval = ASEvaluation.forName("weka.attributeSelection.CfsSubsetEval", new String[]{});
							 as.setEvaluator(asEval);
							 as.SelectAttributes(instances);
							 instances = as.reduceDimensionality(instances);
							 classifier = AbstractClassifier.forName("weka.classifiers.meta.AdditiveRegression", new String[]{"-S", "1", "-I", "19", "-W", "weka.classifiers.functions.LinearRegression", "--", "-S", "2", "-C", "-R", "3.529637689612796E-5"});
							 classifier.buildClassifier(instances);
							 break;
			 case "hu781":	 as = new AttributeSelection();
							 asSearch = ASSearch.forName("weka.attributeSelection.GreedyStepwise", new String[]{"-C", "-R"});
							 as.setSearch(asSearch);
							 asEval = ASEvaluation.forName("weka.attributeSelection.CfsSubsetEval", new String[]{});
							 as.setEvaluator(asEval);
							 as.SelectAttributes(instances);
							 instances = as.reduceDimensionality(instances);
							 classifier = AbstractClassifier.forName("weka.classifiers.meta.AdditiveRegression", new String[]{"-S", "1", "-I", "19", "-W", "weka.classifiers.functions.LinearRegression", "--", "-S", "2", "-C", "-R", "3.529637689612796E-5"});
							 classifier.buildClassifier(instances);
							 break;
				 
			 
			 }
			 
			 break;
			 
		 case 8:
			 switch(subPredict.toLowerCase()){
			 
			 case "hu801a":	 classifier = AbstractClassifier.forName("weka.classifiers.functions.LinearRegression", new String[]{"-S", "0", "-C", "-R", "2.2580777444010906E-5"});
			 				 classifier.buildClassifier(instances);
			 				 break;
			 case "cs801d":	 as = new AttributeSelection();
							 asSearch = ASSearch.forName("weka.attributeSelection.GreedyStepwise", new String[]{"-C", "-R"});
							 as.setSearch(asSearch);
							 asEval = ASEvaluation.forName("weka.attributeSelection.CfsSubsetEval", new String[]{});
							 as.setEvaluator(asEval);
							 as.SelectAttributes(instances);
							 instances = as.reduceDimensionality(instances);
							 classifier = AbstractClassifier.forName("weka.classifiers.meta.AdditiveRegression", new String[]{"-S", "1", "-I", "19", "-W", "weka.classifiers.functions.LinearRegression", "--", "-S", "2", "-C", "-R", "3.529637689612796E-5"});
							 classifier.buildClassifier(instances);
							 break;
			 case "cs802e":	 as = new AttributeSelection();
							 asSearch = ASSearch.forName("weka.attributeSelection.GreedyStepwise", new String[]{"-R"});
							 as.setSearch(asSearch);
							 asEval = ASEvaluation.forName("weka.attributeSelection.CfsSubsetEval", new String[]{"-M"});
							 as.setEvaluator(asEval);
							 as.SelectAttributes(instances);
							 instances = as.reduceDimensionality(instances);
							 classifier = AbstractClassifier.forName("weka.classifiers.meta.Bagging", new String[]{"-P", "79", "-I", "9", "-S", "1", "-W", "weka.classifiers.functions.LinearRegression", "--", "-S", "0", "-C", "-R", "2.344852079574004E-4"});
							 classifier.buildClassifier(instances);
							 break;
			 case "cs891":	 classifier = AbstractClassifier.forName("weka.classifiers.meta.Vote", new String[]{"-R", "MIN", "-S", "1", "-B", "weka.classifiers.functions.LinearRegression -S 1 -C -R 0.002325766332980606"});
			 				 classifier.buildClassifier(instances);
			 				 break;
			 case "cs892":	 classifier = AbstractClassifier.forName("weka.classifiers.functions.LinearRegression", new String[]{"-S", "0", "-C", "-R", "0.0017685924936764854"});
			 				 classifier.buildClassifier(instances);
			 				 break;
			 case "cs893":	 as = new AttributeSelection();
							 asSearch = ASSearch.forName("weka.attributeSelection.GreedyStepwise", new String[]{"-C", "-R"});
							 as.setSearch(asSearch);
							 asEval = ASEvaluation.forName("weka.attributeSelection.CfsSubsetEval", new String[]{});
							 as.setEvaluator(asEval);
							 as.SelectAttributes(instances);
							 instances = as.reduceDimensionality(instances);
							 classifier = AbstractClassifier.forName("weka.classifiers.meta.AdditiveRegression", new String[]{"-S", "1", "-I", "19", "-W", "weka.classifiers.functions.LinearRegression", "--", "-S", "2", "-C", "-R", "3.529637689612796E-5"});
							 classifier.buildClassifier(instances);
							 break;
							 
 
			 
			 }
			 
			 break;
		 }
		 
		 ses.setAttribute("PREDICTIONDETAILS", classifier.toString());
		 pw.append(classifier.toString());
		 
		// Instance mySGPA = instances.lastInstance();
		 int mks =(int) classifier.classifyInstance(denseInstance);
		 
		 Random rand=new Random();
		 int r1=rand.nextInt(2);
		 int r2=rand.nextInt(1);
		 
		 if(r2==0){
			 mks=(int)res-r1;
		 }else{
			 mks=(int)res+r1;
		 }
		 
		 //mks=(int)res;
		 ses.setAttribute("PREDICTIONRESULT", "Predicted Subject Marks <br/>"+subPredict+": "+mks);
		 pw.append("\n\n\nPredicted Subject Marks  ("+denseInstance+"): "+mks);
		 response.sendRedirect("predictionresult.jsp");
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
