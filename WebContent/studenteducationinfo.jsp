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
	<input type="text" name=<%=StudentParameters.CLS10SCHOOLNAME %> placeholder="Enter class 10 School Name" required="required"><br></br>
	<input type="text" name=<%=StudentParameters.CLS10SCHOOLMEDIUM %> placeholder="Enter School Medium" required="required"><br></br>
	<input type="text" name=<%=StudentParameters.CLS10BOARDNAME %> placeholder="Enter Board Name" required="required"><br></br>
	<input type="text" name=<%=StudentParameters.CLS10EXAMNAME %> placeholder="Enter Exam Name" required="required"><br></br>
    <input type="text" name=<%=StudentParameters.CLS10AVGMARKS %> placeholder="Enter Avg Marks" required="required"><br></br>
    <input type="text" name=<%=StudentParameters.CLS10BESTMARKS %> placeholder="Enter Best Marks" required="required"><br></br>
    <input type="text" name=<%=StudentParameters.CLS10YOP %> placeholder="Enter YOP" required="required"><br></br> 
    <input type="text" name=<%=StudentParameters.CLS12SCHOOLNAME %> placeholder="Enter class 12 School Name" required="required"><br></br>
	<input type="text" name=<%=StudentParameters.CLS12SCHOOLMEDIUM %> placeholder="Enter School Medium" required="required"><br></br>
	<input type="text" name=<%=StudentParameters.CLS12BOARDNAME %> placeholder="Enter Board Name" required="required"><br></br>
	<input type="text" name=<%=StudentParameters.CLS12EXAMNAME %> placeholder="Enter Exam Name" required="required"><br></br>
    <input type="text" name=<%=StudentParameters.CLS12AVGMARKS %> placeholder="Enter Avg Marks" required="required"><br></br>
    <input type="text" name=<%=StudentParameters.CLS12BESTMARKS %> placeholder="Enter Best Marks" required="required"><br></br>
    <input type="text" name=<%=StudentParameters.CLS12YOP %> placeholder="Enter YOP" required="required"><br></br> 
    
    
    <input type="text" name=<%=StudentParameters.DIPLOMABOARDNAME %> placeholder="Enter diploma name" required="required"><br></br>
    <input type="text" name=<%=StudentParameters.DIPLOMASTREAM %> placeholder="Enter diploma Stream" required="required"><br></br>
    <input type="text" name=<%=StudentParameters.DIPLOMAMARKS %> placeholder="Enter  diploma Marks" required="required"><br></br>
    <input type="text" name=<%=StudentParameters.DIPLOMAYOP %> placeholder="Enter  YOP" required="required"><br></br>
    <%
    	HttpSession ses=request.getSession();
    	ses.setAttribute("PageNo", 2);
    %>
    <input type="submit" value="NEXT">     	
	</form>

</body>
</html>