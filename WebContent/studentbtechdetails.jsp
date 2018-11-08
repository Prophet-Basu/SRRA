<%@page import="com.bean.SessionBean"%>
<%@page import="com.dao.SessionDao"%>
<%@page import="com.dao.SessionDaoImpl"%>
<%@page import="com.servlet.AddStudentSrv.StudentParameters"%>
<%@page import="com.servlet.AddStudentSrv"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@page import="com.dao.StreamDao"%>
 <%@page import="com.bean.StreamBean"%>
<%@page import="com.dao.StreamDao"%>
<%@page import="com.bean.SubjectBean"%>
<%@page import="java.util.List"%>
<%@page import="com.dao.SubjectDaoImpl"%>
<%@page import="com.dao.StreamDaoImpl"%>
<%@page import="com.dao.SubjectDao"%>  

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
<script src="jquery.min.js"></script>
<script>
window.onload = function () {
   $('#backlog').hide();
}
</script>
</head>
<body>
	<form action="AddStudentSrv" method="post">
	<input type="text" name=<%=StudentParameters.BTECHSELECTIONRANK %> placeholder="Enter BTech Rank" required="required"><br></br>
	<input type="text" name=<%=StudentParameters.BTECHSELECTIONEXAM %> placeholder="Enter Selection Exam" required="required"><br></br>
	<input type="text" name=<%=StudentParameters.COLLEGE %> placeholder="Enter College Name" required="required"><br></br>
	<input type="text" name=<%=StudentParameters.UNIVERSITY %> placeholder="Enter University Name" required="required"><br></br>
    <p>Has Backlog?</p><br>
    <input type="radio" name=<%=StudentParameters.HASBACKLOG %> value="Yes" onclick="$('#backlog').show(500);" >YES
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <input type="radio" name=<%=StudentParameters.HASBACKLOG%> value="NO" onclick="$('#backlog').hide(500); " checked="checked">NO<br></br>
    <div id="backlog">      
    	<input type="text" name=<%=StudentParameters.BACKLOG1 %> placeholder="Enter First Backlog Subject" required="required"><br></br>
    	<input type="text" name=<%=StudentParameters.BACKLOG2 %> placeholder="Enter Second Backlog Subject" required="required"><br></br> 
    	<input type="text" name=<%=StudentParameters.BACKLOG3 %> placeholder="Enter Third Backlog Subject" required="required"><br></br>
    </div>
	<p>Has Year Gap?</p><br>
	<input type="radio" name="<%=StudentParameters.HASYEARGAP %>" value="Yes" >YES
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <input type="radio" name="<%=StudentParameters.HASYEARGAP %>" value="NO" >NO<br></br>
         
	<input type="text" name=<%=StudentParameters.YEARGAPDURATION %> placeholder="Enter Gap Duration" required="required"><br></br>
	<input type="text" name=<%=StudentParameters.YEARGAPPERIOD %> placeholder="Enter Gap Period" required="required"><br></br>
    <input type="text" name=<%=StudentParameters.YEARGAPCAUSE %> placeholder="Enter Gap Cause" required="required"><br></br>
  
    <input type="text" name=<%=StudentParameters.ACHIEVEMENT %> placeholder="Enter Achievements" required="required"><br></br>
    <select name=<%=StudentParameters.STRID %> id="streamSelect" onchange="">
			<option value="0">Select Stream</option>
		 <%
		for(StreamBean s:streamBeans){
		%>
			<option value="<%=s.getStrID()%>"><%=s.getStrID()%>.<%=s.getStrName() %></option>
		<%
		}
		%>
		
		</select>
     <select name=<%=StudentParameters.SESID %> id="sessionSelect" onchange="">
			<option value="0">Select Session</option>
		 <%
		for(SessionBean s:sesBean){
		%>
			<option value="<%=s.getSesID()%>"><%=s.getSesID() %>.<%=s.getSesStartDate() %>-<%=s.getSesEndDate() %></option>
		<%
		}
		%>
		</select>
		<%
    	HttpSession ses=request.getSession();
    	ses.setAttribute("PageNo", 3);
    %>
    <input type="submit" value="NEXT">
    </form>
</body>
</html>