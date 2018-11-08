<%@page import="com.servlet.LoginSrv"%>
<%@page import="com.servlet.AddStreamSrv"%>
<%@page import="java.util.List"%>
<%@page import="com.dao.StreamDaoImpl"%>
<%@page import="com.dao.StreamDao"%>
<%@page import="com.bean.StreamBean"%>
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
</script>

</head>
<body>


<h1>Add Stream Details</h1><br/><br/><br/>
<br/><br/>

<%
 
StreamDao streamDao = new StreamDaoImpl();
List<StreamBean> streamList = streamDao.fetchAllStream();
%>

<h1>ALL Streams</h1>

<div id="ctn2">
    <%if(streamList.size()>0){ %>
           <table style="width: 100%;" cellpadding="5px">
           <tr>
           <th>StreamID</th>
           <th>Name</th>
           <th>No. of Semesters</th>
           <th></th>
           <th></th>
           </tr>
           
           <%for(StreamBean sb : streamList){%>
	           <tr>
	           <td id="did"><%=sb.getStrID() %></td>
	           <td><%=sb.getStrName() %></td>
	           <td><%=sb.getStrNoOfSem()%></td>
	            
	           <td><a href="StreamDeleteSrv?strID=<%=sb.getStrID() %>"
	            onclick="return confirm('Are you sure you want to delete this stream?')"> <button>Delete</button></a></td>
	           <td><a href="SetupStreamUpdateSrv?strID=<%=sb.getStrID() %>"
	            onclick="return confirm('Are you sure you want to edit details of this stream?')"> <button>Edit</button></a></td>
	           </tr>           
           <%}%>
           
           </table>        
                    
<%} %>
</div>
<br/><br/>
<a href="initsetup.jsp"><button>HOME</button></a><br/><br/><br/>
	
	
	
<h3>Choose Stream To Add</h3>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<button id="addButton" onclick="addsubj()">Add </button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<button id="delButton" onclick="delsubj()">Delete </button>

 <br/><br/>	
 
 <form action="AddStreamSrv" method="post">
	
	
	<div id="singleDiv">
		<b>Stream:</b> <input type="text" name="<%=AddStreamSrv.Parameters.STREAM_NAME %>" placeholder="Enter Stream Name" required="required">
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="number" name="<%=AddStreamSrv.Parameters.STREAM_NO_OF_SEM %>" placeholder="Enter No.of Sem" required="required">
		<br/><br/>	
	</div>
	 <br/><br/>	
	<input type="submit" value="Submit">

</form>

<%
if(session.getAttribute(AddStreamSrv.Parameters.MESSAGE)!=null){
	String s = (String)session.getAttribute(AddStreamSrv.Parameters.MESSAGE);
%>
<span style="color: green;font-size: 18px;font-weight: bold;"><%=s %> </span><br/>
<%
	session.removeAttribute(AddStreamSrv.Parameters.MESSAGE);
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