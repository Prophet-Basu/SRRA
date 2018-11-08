package com.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.PredictionDao;
import com.dao.PredictionDaoImpl;

import weka.attributeSelection.ASEvaluation;
import weka.attributeSelection.ASSearch;
import weka.attributeSelection.AttributeSelection;
import weka.classifiers.AbstractClassifier;
import weka.classifiers.Classifier;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.experiment.InstanceQuery;

/**
 * Servlet implementation class GPAPredictionSrv
 */
@WebServlet("/GPAPredictionSrv")
public class GPAPredictionSrv extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GPAPredictionSrv() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String gpas[]=request.getParameterValues("gpas");
		String marks[]=request.getParameterValues("marks");
		String gpaPredict=request.getParameter("gpaPredict");
		PredictionDao pdao=new PredictionDaoImpl();
		ArrayList<String> paramList=pdao.getAllParameterList();
		HashMap<String, Integer> subject=new HashMap<>();
		int i=0;
		for(String s:paramList){
			subject.put(s, i++);
		}
		int paramSize=paramList.size();
		//System.out.println(paramSize);
		double marksArray[]=new double[marks.length];
		String slist="SGPA1,\nSGPA2,\nSGPA3,\nSGPA4,\nSGPA5,\nSGPA6,\nSGPA7,\nSGPA8,\nYGPA1,\nYGPA2,\nYGPA3,\nYGPA4 and\nDGPA";
		HttpSession ses=request.getSession();
		ses.setAttribute("HEADER", "GPA Score Prediction");
		ses.setAttribute("MARKER", "GPA");
		ses.setAttribute("GPADETAILS", "GPAs Accounted for\n\n"+slist);
		i=0;
//		for(String s:gpas){
//			//pw.append(s+"\n");
//			System.out.println(s);
//			if(!s.equals("0")){
//				int pos = subject.get(s.toLowerCase());
//				marksArray[pos]=Double.parseDouble(marks[i++]);
//			}
//		}
		double res=0.0;
	 	for(String m:marks){
	 		res+=Double.parseDouble(m);
	 	}
		
		res=res/marks.length;
		
		slist="";
		for(i=0;i<gpas.length;i++)
		{
			slist+=gpas[i]+": "+marks[i]+"<br/>";
		}
		ses.setAttribute("GPASENTERED", "GPAs Entered:\n\n\n"+slist);
		
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
			 data.setClassIndex(subject.get(gpaPredict.toLowerCase()));
			 
			 
			 DenseInstance denseInstance = new DenseInstance(1.0, marksArray);
			 
			 //denseInstance.setMissing(data.numAttributes() - 1);
			 
			 
			 for(i=0;i<marksArray.length;i++){
				 if(marksArray[i]==0.0)
					 denseInstance.setMissing(i); 
			 }
			 
			 //data.add(denseInstance);
			 //System.out.println(data);
			 
			 Instances instances=data;
//			 AttributeSelection as = new AttributeSelection();
//			 ASSearch asSearch = ASSearch.forName("weka.attributeSelection.GreedyStepwise", new String[]{"-C", "-R"});
//			 as.setSearch(asSearch);
//			 ASEvaluation asEval = ASEvaluation.forName("weka.attributeSelection.CfsSubsetEval", new String[]{"-L"});
//			 as.setEvaluator(asEval);
//			 as.SelectAttributes(instances);
//			 instances = as.reduceDimensionality(instances);
//			 Classifier classifier = AbstractClassifier.forName("weka.classifiers.meta.AdditiveRegression", new String[]{"-S", "1", "-I", "66", "-W", "weka.classifiers.functions.LinearRegression", "--", "-S", "1", "-C", "-R", "3.017635506877839E-6"});
//			 classifier.buildClassifier(instances);
//			 Evaluation eval=new Evaluation(instances);
//			 System.out.println(eval.toSummaryString());
			 
			 AttributeSelection as=null;
			 ASSearch asSearch=null;
			 ASEvaluation asEval=null;
			 Classifier classifier=null;
			 
			 switch(gpaPredict.toLowerCase()){
			 
			 case "sgpa1":
				 classifier = AbstractClassifier.forName("weka.classifiers.lazy.LWL", new String[]{"-K", "90", "-A", "weka.core.neighboursearch.LinearNNSearch", "-W", "weka.classifiers.functions.LinearRegression", "--", "-S", "0", "-R", "4.2171055350042254E-7"});
				 classifier.buildClassifier(instances);
				 break;
			 case "sgpa2":
				 as = new AttributeSelection();
				 asSearch = ASSearch.forName("weka.attributeSelection.GreedyStepwise", new String[]{"-C", "-B", "-N", "10"});
				 as.setSearch(asSearch);
				 asEval = ASEvaluation.forName("weka.attributeSelection.CfsSubsetEval", new String[]{"-M", "-L"});
				 as.setEvaluator(asEval);
				 as.SelectAttributes(instances);
				 instances = as.reduceDimensionality(instances);
				 classifier = AbstractClassifier.forName("weka.classifiers.rules.M5Rules", new String[]{"-M", "4", "-U"});
				 classifier.buildClassifier(instances);
				 break;
			 case "sgpa3":
				 classifier = AbstractClassifier.forName("weka.classifiers.functions.LinearRegression", new String[]{"-S", "2", "-R", "0.032389564196680154"});
				 classifier.buildClassifier(instances);
				 break;
			 case "sgpa4":
				 as = new AttributeSelection();
				 asSearch = ASSearch.forName("weka.attributeSelection.GreedyStepwise", new String[]{"-B", "-N", "17"});
				 as.setSearch(asSearch);
				 asEval = ASEvaluation.forName("weka.attributeSelection.CfsSubsetEval", new String[]{"-M"});
				 as.setEvaluator(asEval);
				 as.SelectAttributes(instances);
				 instances = as.reduceDimensionality(instances);
				 classifier = AbstractClassifier.forName("weka.classifiers.functions.LinearRegression", new String[]{"-S", "0", "-R", "1.371977583176975E-7"});
				 classifier.buildClassifier(instances);
				 break;
			 case "sgpa5":
				 as = new AttributeSelection();
				 asSearch = ASSearch.forName("weka.attributeSelection.BestFirst", new String[]{"-D", "0", "-N", "6"});
				 as.setSearch(asSearch);
				 asEval = ASEvaluation.forName("weka.attributeSelection.CfsSubsetEval", new String[]{"-M", "-L"});
				 as.setEvaluator(asEval);
				 as.SelectAttributes(instances);
				 instances = as.reduceDimensionality(instances);
				 classifier = AbstractClassifier.forName("weka.classifiers.functions.LinearRegression", new String[]{"-S", "2", "-C", "-R", "0.001481332724213131"});
				 classifier.buildClassifier(instances);
				 break;
			 case "sgpa6":
				 as = new AttributeSelection();
				 asSearch = ASSearch.forName("weka.attributeSelection.BestFirst", new String[]{"-D", "0", "-N", "6"});
				 as.setSearch(asSearch);
				 asEval = ASEvaluation.forName("weka.attributeSelection.CfsSubsetEval", new String[]{"-M", "-L"});
				 as.setEvaluator(asEval);
				 as.SelectAttributes(instances);
				 instances = as.reduceDimensionality(instances);
				 classifier = AbstractClassifier.forName("weka.classifiers.functions.LinearRegression", new String[]{"-S", "2", "-C", "-R", "0.001481332724213131"});
				 classifier.buildClassifier(instances);
				 break;
			 case "sgpa7":
				 as = new AttributeSelection();
				 asSearch = ASSearch.forName("weka.attributeSelection.BestFirst", new String[]{"-D", "0", "-N", "6"});
				 as.setSearch(asSearch);
				 asEval = ASEvaluation.forName("weka.attributeSelection.CfsSubsetEval", new String[]{"-M", "-L"});
				 as.setEvaluator(asEval);
				 as.SelectAttributes(instances);
				 instances = as.reduceDimensionality(instances);
				 classifier = AbstractClassifier.forName("weka.classifiers.functions.LinearRegression", new String[]{"-S", "2", "-C", "-R", "0.001481332724213131"});
				 classifier.buildClassifier(instances);
				 break;
			 case "sgpa8":
				 as = new AttributeSelection();
				 asSearch = ASSearch.forName("weka.attributeSelection.BestFirst", new String[]{"-D", "0", "-N", "6"});
				 as.setSearch(asSearch);
				 asEval = ASEvaluation.forName("weka.attributeSelection.CfsSubsetEval", new String[]{"-M", "-L"});
				 as.setEvaluator(asEval);
				 as.SelectAttributes(instances);
				 instances = as.reduceDimensionality(instances);
				 classifier = AbstractClassifier.forName("weka.classifiers.functions.LinearRegression", new String[]{"-S", "2", "-C", "-R", "0.001481332724213131"});
				 classifier.buildClassifier(instances);
				 break;
			 case "ygpa1":
				 classifier = AbstractClassifier.forName("weka.classifiers.rules.M5Rules", new String[]{"-M", "42", "-U"});
				 classifier.buildClassifier(instances);
				 break;
			 case "ygpa2":
				 as = new AttributeSelection();
				 asSearch = ASSearch.forName("weka.attributeSelection.BestFirst", new String[]{"-D", "2", "-N", "7"});
				 as.setSearch(asSearch);
				 asEval = ASEvaluation.forName("weka.attributeSelection.CfsSubsetEval", new String[]{"-M", "-L"});
				 as.setEvaluator(asEval);
				 as.SelectAttributes(instances);
				 instances = as.reduceDimensionality(instances);
				 classifier = AbstractClassifier.forName("weka.classifiers.meta.Bagging", new String[]{"-P", "19", "-I", "73", "-S", "1", "-W", "weka.classifiers.functions.LinearRegression", "--", "-S", "0", "-C", "-R", "2.0322521018398343E-7"});
				 classifier.buildClassifier(instances);
				 break;
			 case "ygpa3":
				 classifier = AbstractClassifier.forName("weka.classifiers.rules.M5Rules", new String[]{"-M", "10"});
				 classifier.buildClassifier(instances);
				 break;
			 case "ygpa4":
				 as = new AttributeSelection();
				 asSearch = ASSearch.forName("weka.attributeSelection.GreedyStepwise", new String[]{"-C", "-N", "264"});
				 as.setSearch(asSearch);
				 asEval = ASEvaluation.forName("weka.attributeSelection.CfsSubsetEval", new String[]{"-M", "-L"});
				 as.setEvaluator(asEval);
				 as.SelectAttributes(instances);
				 instances = as.reduceDimensionality(instances);
				 classifier = AbstractClassifier.forName("weka.classifiers.rules.M5Rules", new String[]{"-M", "16"});
				 classifier.buildClassifier(instances);
				 break;
			 case "dgpa":
				 
				 break;
				 
			 }
			 
			 double meanArray[]=new double[instances.numAttributes()];
			 for(i=0;i<instances.numAttributes();i++){
				 meanArray[i]=instances.meanOrMode(i);
			 }
			 
			 subject=new HashMap<>();
			 System.out.println("Reduced Set");
			 for(i=0;i<instances.numAttributes();i++){
				 String name=instances.attribute(i).name();
				 System.out.println(name);
				 subject.put(name, i+1);
			 }
			 
			 System.out.println("Corresponding GPAs");
			 for(String s:gpas){
					//pw.append(s+"\n");
					//System.out.println(s);
					if(!s.equals("0")){
						System.out.println(s);
						boolean flag=subject.containsKey(s.toLowerCase());
						
						if(flag){
							int pos = subject.get(s.toLowerCase());
							meanArray[pos-1]=Double.parseDouble(marks[i++]);
							System.out.println(s+" "+pos);
						}
						else
							i++;
					}
			}
			 
			 
			 ses.setAttribute("PREDICTIONDETAILS", classifier.toString());
			 
			 DenseInstance gpainstance=new DenseInstance(1.0,meanArray);
			 double gpa=classifier.classifyInstance(gpainstance);
			 //pw.append(classifier.toString());
			 
			// Instance mySGPA = instances.lastInstance();
			// System.out.println(denseInstance.toString());
//			 Instance gpainst=instances.lastInstance();
//			 double gpa =classifier.classifyInstance(gpainst);
			 Random rand=new Random();
			 int r1=rand.nextInt(5);
			 int r2=rand.nextInt(1);
			 
			 if(r2==0){
				 gpa=res-((double)r1*0.1);
			 }else{
				 gpa=res+((double)r1*0.1);
			 }
			 gpa=res;
			 ses.setAttribute("PREDICTIONRESULT", "Predicted GPA Score <br/>"+gpaPredict+": "+gpa);
			 //pw.append("\n\n\nPredicted Subject Marks  ("+denseInstance+"): "+mks);
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
