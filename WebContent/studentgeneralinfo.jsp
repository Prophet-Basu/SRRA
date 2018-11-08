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
	<form action="AddStudentSrv" method="post">
	<input type="text" name=<%=StudentParameters.NAME %> placeholder="Enter Name" required="required"><br></br>
	<input type="text" name=<%=StudentParameters.FNAME %> placeholder="Enter First Name" required="required"><br></br>
	<input type="text" name=<%=StudentParameters.LNAME %> placeholder="Enter Last Name" required="required"><br></br>
	<input type="radio" name="<%=StudentParameters.GENDER %>" value="Male" >Male
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <input type="radio" name="<%=StudentParameters.GENDER %>" value="Female" >Female<br></br>
    <input type="text" name=<%=StudentParameters.DOB %> placeholder="MM/DD/YYYY" required="required"><br></br>
    <input type="text" name=<%=StudentParameters.BLOODGROUP %> placeholder="Enter BloodGrp" required="required"><br></br>
    <input type="text" name=<%=StudentParameters.LANDLINECODE %> placeholder="Enter STD Code" required="required"><br></br>
    <input type="text" name=<%=StudentParameters.LANDLINE %> placeholder="Enter Landline Number" required="required"><br></br> 
    <input type="text" name=<%=StudentParameters.MOB1 %> placeholder="Enter Mobile Number" required="required"><br></br>
    <input type="text" name=<%=StudentParameters.MOB2 %> placeholder="Enter Alternate Mobile Number" required="required"><br></br>
    <input type="email" name=<%=StudentParameters.EMAIL %> placeholder="Enter E-mail ID" required="required"><br></br>
    <%
    	HttpSession ses=request.getSession();
    	ses.setAttribute("PageNo", 1);
    %>
    <input type="submit" value="NEXT">     	
	</form>
</body>
</html>