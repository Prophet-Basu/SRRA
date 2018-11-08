<%@page import="com.servlet.LoginSrv"%>
<%@page import="com.bean.StreamBean"%>
<%@page import="com.dao.StreamDaoImpl"%>
<%@page import="com.dao.StreamDao"%>
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
<style type="text/css">
	form{
		display:block; 
		background-color:#999999; 
		
		width:250px; 
		margin-top:100px; 
		padding: 10px 10px 10px 10px
	}
	form input{
		height:35px;
		font-size:20px; 
		alignment: left;
		margin: 10px 10px 10px 10px;
	}
	form div{
		height:30px;
		width:80px;
		font-size:20px; 
		margin: 10px 10px 10px 10px;
	}
	
</style>
</head>
<body>
<%
	int strID=(int)session.getAttribute("upstrID");
	StreamDao sdao=new StreamDaoImpl();
	StreamBean sb=sdao.fetchStreamDetails(strID);
%>
<center>
	<form action="StreamUpdateSrv" method="post">
		<h1>Update Stream</h1>
		<input type="hidden" name="" value="<%=sb.getStrID() %>" >
		<input type="text" name="" value="<%=sb.getStrName() %>" placeholder="Enter Stream Name" required="required">
		<input type="text" name="" value="<%=sb.getStrNoOfSem() %>" placeholder="Enter No. of Semesters" required="required">
		<input type="submit" value="Update">
		<div style="background-color:blue"><a style="text-decoration:none;color:white" href="initsetupstream.jsp">Back</a></div>
	</form>
</center>
</body>
</html>
<%
	} else {
		response.sendRedirect("LogoutSrv");
	}
%>