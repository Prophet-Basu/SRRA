<%@page import="com.servlet.AddStudentSrv.StudentParameters"%>
<%@page import="com.servlet.AddStudentSrv"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<body>
	<form action="AddStudentSrv" method="post">
	<input type="text" name=<%=StudentParameters.FATHERNAME %> placeholder="Enter Father Name" required="required"><br></br>
	<input type="text" name=<%=StudentParameters.FATHERSERVICE %> placeholder="Enter Father Service" required="required"><br></br>
	<input type="text" name=<%=StudentParameters.MOTHERNAME %> placeholder="Enter Mother Name" required="required"><br></br>
	<input type="text" name=<%=StudentParameters.MOTHERSERVICE %> placeholder="Enter Mother Service" required="required"><br></br>
    <input type="text" name=<%=StudentParameters.PARENTMOB1 %> placeholder="Enter parent Mobile No" required="required"><br></br>
    <input type="text" name=<%=StudentParameters.PARENTMOB2 %> placeholder="Enter  ALternate parent Mobile No" required="required"><br></br>
    <input type="text" name=<%=StudentParameters.LOCALGUARDIANNAME %> placeholder="Enter LOCAL GUARDIAN NAME" required="required"><br></br> 
    <input type="text" name=<%=StudentParameters.LOCALGUARDIANRELATION %> placeholder="Enter LOCAL GUARDIAN RELATION" required="required"><br></br>
    <input type="text" name=<%=StudentParameters.LOCALGUARDIANSERVICE %> placeholder="Enter LOCAL GUARDIAN SERVICE" required="required"><br></br>
    <input type="number" name=<%=StudentParameters.LOCALGUARDIANMOB %> placeholder="Enter LOCAL GUARDIAN MOB" required="required"><br></br>
    <%
    	HttpSession ses=request.getSession();
    	ses.setAttribute("PageNo", 4);
    %>
    <input type="submit" value="NEXT">     	
	</form>
</body>
</html>