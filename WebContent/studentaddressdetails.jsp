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
	<input type="text" name=<%=StudentParameters.PRESENTADDRESS %> placeholder="Enter Present Address" required="required"><br></br>
	<input type="text" name=<%=StudentParameters.PRESENTADDRESSCITY %> placeholder="Enter Present City" required="required"><br></br>
	<input type="text" name=<%=StudentParameters.PRESENTADDRESSPIN %> placeholder="Enter Pin Code" required="required"><br></br>
	<input type="text" name=<%=StudentParameters.PRESENTADDRESSPOSTOFFICE %> placeholder="Enter PostOffice" required="required"><br></br>
    <input type="text" name=<%=StudentParameters.PRESENTADDRESSDISTRICT %> placeholder="Enter District" required="required"><br></br>
    <input type="text" name=<%=StudentParameters.PRESENTADDRESSSTATE %> placeholder="Enter State" required="required"><br></br>
   	<input type="text" name=<%=StudentParameters.PERMANENTADDRESS %> placeholder="Enter Present Address" required="required"><br></br>
	<input type="text" name=<%=StudentParameters.PERMANENTADDRESSCITY %> placeholder="Enter Present City" required="required"><br></br>
	<input type="text" name=<%=StudentParameters.PERMANENTADDRESSPIN %> placeholder="Enter Pin Code" required="required"><br></br>
	<input type="text" name=<%=StudentParameters.PERMANENTADDRESSPOSTOFFICE %> placeholder="Enter PostOffice" required="required"><br></br>
    <input type="text" name=<%=StudentParameters.PERMANENTADDRESSDISTRICT %> placeholder="Enter District" required="required"><br></br>
    <input type="text" name=<%=StudentParameters.PERMANENTADDRESSSTATE %> placeholder="Enter State" required="required"><br></br>
    <%
    	HttpSession ses=request.getSession();
    	ses.setAttribute("PageNo", 5);
    %>
    <input type="submit" value="NEXT">     	
	</form>

</body>
</html>