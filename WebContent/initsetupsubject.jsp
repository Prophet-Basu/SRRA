
<%@page import="com.servlet.LoginSrv"%>
<%@page import="com.servlet.AddSubjectSrv"%>
<%@page import="com.servlet.GenerateSubjectsJSONSrv"%>
<%@page import="com.servlet.StreamSemesterCountSrv"%>
<%@page import="com.bean.StreamBean"%>
<%@page import="com.dao.StreamDao"%>
<%@page import="com.bean.SubjectBean"%>
<%@page import="java.util.List"%>
<%@page import="com.dao.SubjectDaoImpl"%>
<%@page import="com.dao.StreamDaoImpl"%>
<%@page import="com.dao.SubjectDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
	Object bean = session.getAttribute(LoginSrv.Parameters.TEACHERBEAN);
	Object nbean = session.getAttribute(LoginSrv.Parameters.NEWADMIN);
	if (bean != null || nbean != null) {
%>    
<%
 
SubjectDao subjectDao = new SubjectDaoImpl();
StreamDao streamDao = new StreamDaoImpl();
List<StreamBean> streamBeans = streamDao.fetchAllStream();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="js/jquery.min.js"></script>
<script type="text/javascript">
var c=1;
function addsubj(){
	
	
	var div = $('#singleDiv').html();
	$("<div name=singleDiv"+c+">"+div+"</div>").insertAfter('#singleDiv');
	c=c+1;
	
}

function delsubj(){
	
	
	if(c>1){
	$("div:last").remove();
	c=c-1;
	}
	
}

function updateSemSelect(){
	var stream = $('#streamSelect').val();
	//alert(stream);
	$.ajax({
	    type: "GET",
	    url: "StreamSemesterCountSrv",
	    data: "<%=StreamSemesterCountSrv.Parameters.STRID%>="+stream,
	    success: function(data) {
	    	//alert(stream);
	       	var selector = $('#semSelect');
	        selector.empty(); // remove old options
	        $("<option value='0'>Select Semester</option>").appendTo('#semSelect');
	        var count = parseInt(data);
	        //alert(count);
	        for (i = 1; i <= count; i++) {
	        	var div_data="<option value="+i+">Semester "+i+"</option>";
            	$(div_data).appendTo('#semSelect'); 
	        } 
        }									
	        
    });
	
}

function updateSemSelectForm(){
	var stream = $('#streamSelectForm').val();
	//alert(stream);
	$.ajax({
	    type: "GET",
	    url: "StreamSemesterCountSrv",
	    data: "<%=StreamSemesterCountSrv.Parameters.STRID%>="+stream,
	    success: function(data) {
	    	//alert(stream);
	       	var selector = $('#semSelectForm');
	        selector.empty(); // remove old options
	        $("<option value='0'>Select Semester</option>").appendTo('#semSelectForm');
	        var count = parseInt(data);
	        //alert(count);
	        for (i = 1; i <= count; i++) {
	        	var div_data="<option value="+i+">Semester "+i+"</option>";
            	$(div_data).appendTo('#semSelectForm'); 
	        } 
        }									
	        
    });
	
}

function updateSubjTable(){
	var semester=$('#semSelect').val();
	var dept=$('#streamSelect').val();
	//alert(dept);
	$.ajax({
		type:"GET",
		url:"GenerateSubjectsJSONSrv",
		data: "<%=GenerateSubjectsJSONSrv.Parameters.STRID%>="+dept+"&<%=GenerateSubjectsJSONSrv.Parameters.SEM%>="+semester,
		dataType: 'json',
		cache: false,
		success:function(data){
			var trHTML = '';
            
	        $.each(data.subject, function (i, item) {
	        	
	            trHTML += '<tr><td>' + item.subCode +
	            '</td><td>' + item.subName + 
	            '</td><td>' + item.subSem + 
	            '</td><td>' + item.subTaughtDuration + 
	            '</td><td>' + item.strID + 
	            '</td><td>' + "<a href='DeleteSubjectSrv?subCode="+item.subCode+"' onclick='return confirm('Are you sure you want to delete this Subject?')'><button>Delete</button></a>" + 
	            '</td><td>' + "<a href='#' onclick='return confirm('Are you sure you want to edit details of this Subject?')'><button>Edit</button></a>" + 
	            '</td></tr>';
	        });

	        
	        $('#subjTable tbody > tr').remove();
	        $('#subjTable').append(trHTML);
			}
		}
	);
}

</script>

</head>
<body>


<h1>Add Subject Details</h1><br/><br/><br/>
<br/><br/>



<select name="" id="streamSelect" onchange="updateSemSelect()">
			<option value="0">Select Stream</option>
		 <%
		for(StreamBean s:streamBeans){
		%>
			<option value="<%=s.getStrID()%>"><%=s.getStrID()%>.<%=s.getStrName() %></option>
		<%
		}
		%>
		
		</select>
		
		<select name="" id="semSelect" onchange="updateSubjTable()">
			<option value="0">Select Semester</option>
		 
		</select>
		<br/><br/>

	<div id="ctn2">
    
           <table style="width: 100%;" cellpadding="5px" id="subjTable">
           <thead>
	           <tr>
	           <th>Subject Code</th>
	           <th>Name</th>
	           <th>Semester</th>
	           <th>Subject Taught Dur</th>
	           <th>Stream ID</th>
	           <th></th>
	           <th></th>
	           </tr>
           </thead>
           <tbody></tbody>
                    
           </table>        

	</div>
<br/><br/>
<a href="initsetup.jsp"><button>HOME</button></a><br/><br/><br/>
	
	
	
<h3>Choose Stream To Add</h3>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<button id="addButton" onclick="addsubj()">Add </button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<button id="delButton" onclick="delsubj()">Del </button>

 <br/><br/>	
 
 <form action="AddSubjectSrv" method="post">
	<select name="<%=AddSubjectSrv.SubjectParameters.STREAM_ID %>" id="streamSelectForm" onchange="updateSemSelectForm()">
			<option value="0">Select Stream</option>
		 <%
		for(StreamBean s:streamBeans){
		%>
			<option value="<%=s.getStrID()%>"><%=s.getStrID()%>.<%=s.getStrName() %></option>
		<%
		}
		%>
		
		</select>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		
		<select name="<%=AddSubjectSrv.SubjectParameters.SUBJECT_SEMESTER %>" id="semSelectForm" >
			<option value="0">Select Semester</option>
		 
		</select>
		<br/><br/>	
	
	<div id="singleDiv">
		<input type="text" name="<%=AddSubjectSrv.SubjectParameters.SUBJECT_CODE%>" placeholder="Enter Subject Code" required="required">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="text" name="<%=AddSubjectSrv.SubjectParameters.SUBJECT_NAME %>" placeholder="Enter Subject Name" required="required">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="number" name="<%=AddSubjectSrv.SubjectParameters.SUBJECT_TAUGHT_DURATION%>" placeholder="Enter Number of Lectures" required="required">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		
		
		
		<br/><br/>	
	</div>
	 <br/><br/>	
	<input type="submit" value="submit">

</form>

<%
if(session.getAttribute(AddSubjectSrv.SubjectParameters.MESSAGE)!=null){
	String s = (String)session.getAttribute(AddSubjectSrv.SubjectParameters.MESSAGE);
%>
<span style="color: green;font-size: 18px;font-weight: bold;"><%=s %> </span><br/>
<%
	session.removeAttribute(AddSubjectSrv.SubjectParameters.MESSAGE);
}
%>


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
<%
	} else {
		response.sendRedirect("LogoutSrv");
	}
%>