<%@page import="com.bean.StudentPersonalDetailsBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<%
		HttpSession ses=request.getSession();
		StudentPersonalDetailsBean studentPersonal=(StudentPersonalDetailsBean)ses.getAttribute("UpdateStudentPersonal");
	%>
	<form action="">
	<input type="text" value=<%=studentPersonal.getStudName() %> name="Name"  required="required"><br></br>
	<input type="text" value=<%=studentPersonal.getStudFName() %> name="" required="required"><br></br>
	<input type="text" value=<%=studentPersonal.getStudLName() %> required="required"><br></br>
	<input type="text" value=<%=studentPersonal.getStudGender()%> name="" required="required"><br></br>
    <input type="text" value=<%=studentPersonal.getStudDOB() %> required="required"><br></br>
    <input type="text" value=<%=studentPersonal.getStudBloodGroup() %>  required="required"><br></br>
    <input type="text" value=<%=studentPersonal.getStudLandlineCode() %>  required="required"><br></br>
    <input type="text" value=<%=studentPersonal.getStudLandline() %>  required="required"><br></br> 
    <input type="text" value=<%=studentPersonal.getStudMob1() %>  required="required"><br></br>
    <input type="text" value=<%=studentPersonal.getStudMob2() %>  required="required"><br></br>
    <input type="email" value=<%=studentPersonal.getStudEmail() %>  required="required"><br></br>
    
    <input type="submit" value="Update"><br></br>
    <a href="teacherhome.jsp">HOME</a>
    
    

</form>
</body>
</html>