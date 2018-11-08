<%@page import="com.servlet.GenerateStudentJSONSrv"%>
<%@page import="com.servlet.AddSubjectSrv"%>
<%@page import="com.servlet.GenerateSubjectsJSONSrv"%>
<%@page import="com.servlet.StreamSemesterCountSrv"%>
<%@page import="com.bean.StreamBean"%>
<%@page import="com.bean.SessionBean"%>
<%@page import="com.dao.SessionDao"%>
<%@page import="com.dao.SessionDaoImpl"%>
<%@page import="com.dao.StreamDao"%>
<%@page import="com.bean.SubjectBean"%>
<%@page import="java.util.List"%>
<%@page import="com.dao.SubjectDaoImpl"%>
<%@page import="com.dao.StreamDaoImpl"%>
<%@page import="com.dao.SubjectDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
 
		SubjectDao subjectDao = new SubjectDaoImpl();
		StreamDao streamDao = new StreamDaoImpl();
		List<StreamBean> streamBeans = streamDao.fetchAllStream();
		SessionDao sesDao=new SessionDaoImpl();
		List<SessionBean> sesBean=sesDao.fetchAllSession();
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="js/jquery.min.js"></script>
<script type="text/javascript">

function updateSubjTable(){
	var session=$('#sessionSelect').val();
	var dept=$('#streamSelect').val();
	//alert(dept);
	$.ajax({
		type:"GET",
		url:"GenerateStudentJSONSrv",
		data: "<%=GenerateStudentJSONSrv.Parameters.STRID%>="+dept+"&<%=GenerateStudentJSONSrv.Parameters.SESSION%>="+session,
		dataType: 'json',
		cache: false,
		success:function(data){
			var trHTML = '';
            
	        $.each(data.studentpersonaldetails, function (i, item) {
	        	
	            trHTML += '<tr><td>' + item.studRoll +
	            '</td><td>' + item.studName + 
	            '</td><td>' + item.strID + 
	            '</td><td>' + item.sesID + 
	            '</td><td>' + "<a href='EditStudentSrv?Roll='+item.studRoll+' onclick='return confirm('Are you sure you want to delete this StudentRecord?')'><button>Delete</button></a>" + 
	            '</td><td>' + "<a href='#' onclick='return confirm('Are you sure you want to edit details of this Student?')'><button>Edit</button></a>" + 
	            '</td></tr>';
	        });

	        
	        $('#studentTable tbody > tr').remove();
	        $('#studentTable').append(trHTML);
			}
		}
	);
}

</script>


</head>
<body>



		<select name="" id="streamSelect" onchange="">
			<option value="0">Select Stream</option>
		 <%
		for(StreamBean s:streamBeans){
		%>
			<option value="<%=s.getStrID()%>"><%=s.getStrID()%>.<%=s.getStrName() %></option>
		<%
		}
		%>
		
		</select>
		
		<select name="" id="sessionSelect" onchange="updateSubjTable()">
			<option value="0">Select Session</option>
		 <%
		for(SessionBean s:sesBean){
		%>
			<option value="<%=s.getSesID()%>"><%=s.getSesID() %>.<%=s.getSesStartDate() %>-<%=s.getSesEndDate() %></option>
		<%
		}
		%>
		</select>
		<br/><br/>
		
		<div id="ctn2">
    
           <table style="width: 100%;" cellpadding="5px" id="studentTable">
           <thead>
	           <tr>
	           <th>Roll No</th>
	           <th>Name</th>
	           <th>Stream</th>
	           <th>Session</th>
	           <th></th>
	           <th></th>
	           </tr>
           </thead>
           <tbody></tbody>
                    
           </table>        

	</div>
<br/><br/>

</body>

<style>


#ctn2{
	background-color: rgba(177, 42, 20, 0.36);
    padding: 10px;
    border-radius: 20px;
}


th{
padding: 5px;
border: 2px solid;
background-color: #fff;
font-size: medium;
}
td{
padding: 3px;
border: 1px solid;
background-color: #fff;
text-align: center;
}
tr{
}

</style>
</html>