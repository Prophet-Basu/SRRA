<%@page import="com.bean.TeacherBean"%>
<%@page import="com.servlet.LoginSrv"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
		response.setHeader("Cache-Control","no-cache");
		response.setHeader("Cache-Control","no-store");
		response.setHeader("Pragma","no-cache");
		response.setDateHeader ("Expires", 0);
		Object bean = session.getAttribute(LoginSrv.Parameters.TEACHERBEAN);
		if (bean != null) {
			 TeacherBean tbean=(TeacherBean)bean;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
	div div{
		height:65px;
		alignment: left;
		margin: 10px 10px 10px 10px;
	}
	
</style>
</head> 
<body>
<%
	
%>
<center>
	
	<div style="display:block; background-color:#999999; font-size:45px; width:700px; margin-top:100px; padding: 10px 10px 10px 10px">
		<div><h2>Your Profile</h2></div>
		<div>Name: <%=tbean.getTeacherName() %></div>
		<div>Address: <%=tbean.getTeacherAddress() %></div>
		<div>D.O.B.: <%=tbean.getTeacherDOB() %></div>
		<div>Email: <%=tbean.getTeacherEmail() %></div>
		<div>Mobile: <%=tbean.getTeacherMob() %></div>
		<div>Designation: <%=tbean.getTeacherDesignation() %></div>
		<div></div>
		<div style="background-color:green"><a style="text-decoration:none;color:white" href="teacherupdate.jsp">Update</a></div>
		<div style="background-color:blue"><a style="text-decoration:none;color:white" href="teacherhome.jsp">Home</a></div>		
	</div>
</center>
</body>
</html>
<%}else{response.sendRedirect("LogoutSrv");}%>