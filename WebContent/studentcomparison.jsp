<%@page import="com.bean.TeacherBean"%>
<%@page import="com.dao.TeachesDaoImpl"%>
<%@page import="com.dao.TeachesDao"%>
<%@page import="com.bean.StreamBean"%>
<%@page import="com.dao.StreamDaoImpl"%>
<%@page import="com.dao.StreamDao"%>
<%@page import="com.servlet.LoginSrv"%>
<%@page import="com.servlet.GenerateStudentJSONSrv"%>
<%@page import="com.servlet.GenerateSubjectsJSONSrv"%>
<%@page import="com.servlet.GenerateTeacherJSONSrv"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.dao.SessionDaoImpl"%>
<%@page import="com.dao.SessionDao"%>
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



<!DOCTYPE html>
<html class="nojs html css_verticalspacer" lang="en-US">
 <head>

  <meta http-equiv="Content-type" content="text/html;charset=UTF-8"/>
  <meta name="generator" content="2017.0.0.363"/>
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  
  <script type="text/javascript">
   // Update the 'nojs'/'js' class on the html node
document.documentElement.className = document.documentElement.className.replace(/\bnojs\b/g, 'js');

// Check that all required assets are uploaded and up-to-date
if(typeof Muse == "undefined") window.Muse = {}; window.Muse.assets = {"required":["museutils.js", "museconfig.js", "jquery.watch.js", "require.js", "index.css"], "outOfDate":[]};
</script>
  
  <title>Home</title>
  <!-- CSS -->
  
  <style>
	@font-face {
    font-family: roboto;
    src: url("fonts/roboto.ttf");
	}
	
  #logout, #logout:visited{
  padding:0px;
  background-color:rgba(0,0,0,0);
  display:inline;
  }
  
 
  
  a{
  color: #fFF;
    text-decoration: none;
    background-color: #e17a7a;
    padding: 15px;
    margin-top: 20px;
    display: inline-block;
    width: 200px;
    margin-top: 30px;
  }
  
  input{
	background-color: #fff;
	border-top: 2px solid #2c90c6;
	border-right: 1px solid #000;
	border-left: 1px solid #000;
	border-radius: 5px 5px 0 0;
	-moz-border-radius: 5px 5px 0 0;
	-webkit-border-radius: 5px 5px 0 0;
  -o-border-radius: 5px 5px 0 0;
  -ms-border-radius: 5px 5px 0 0;
	color: #363636;
	padding-left: 36px;
	width: 204px;
}

 input[type="submit"] {
 background-color: #2cc674;
	border: 1px solid #2cc674;
	border-radius: 15px;
	-moz-border-radius: 15px;
	-webkit-border-radius: 15px;
  -ms-border-radius: 15px;
  -o-border-radius: 15px;
	color: #fff;
	font-weight: bold;
	line-height: 48px;
	text-transform: uppercase;
	width: 240px;
	padding-left:0px;
 
 }
  
  </style>
  
  <link rel="stylesheet" type="text/css" href="css/initsetup2.css?crc=443350757"/>
  <link rel="stylesheet" type="text/css" href="css/config.css?crc=4020712053" id="pagesheet"/>
  
  <!-- Other scripts -->
  <script type="text/javascript">
   var __adobewebfontsappname__ = "muse";
</script>
  <!-- JS includes -->
  <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<%
	TeachesDao tdao=new TeachesDaoImpl();
	TeacherBean trbean=(TeacherBean)bean;
	String ja=(String)session.getAttribute("ja");
    String yaxis=(String)session.getAttribute("yaxis");
   	String param=(String)session.getAttribute("param");
   	
   	
%>

<script src="js/jquery.min.js"></script>
<script type="text/javascript">
	
	function updateStudentDetail(){
		var session=$('#sessionSelect').val();
		var dept=$('#streamSelect').val();
		//alert(dept);
		$.ajax({
			type:"GET",
			url:"GenerateStudentJSONSrv",
			data: "<%=GenerateStudentJSONSrv.Parameters.STRID%>="+dept+"&<%=GenerateStudentJSONSrv.Parameters.SESSION%>="+session+"&<%=GenerateStudentJSONSrv.Parameters.CATEGORY%>="+'general',
			dataType: 'json',
			cache: false,
			success:function(data){
				var items="";
	            
		        $.each(data.studentpersonaldetails, function (i, item) {
		        	
		            items+="<option value='"+item.studRoll+"'>" + item.studRoll +"." + item.studName + "<option>";
		        });

		        
		        $('#studentSelect').empty();
		        $('#studentSelect').append(items);
				}
			}
		);
		
		$.ajax({
			type:"GET",
			url:"GetSectionsOfSessionSrv",
			data: "sesID="+session,
			dataType: 'json',
			cache: false,
			success:function(data){
				var selector = $('#sectionSelect');
		        selector.empty();
		        
		        $.each(data.sections, function (i, item) {
			        var str=item.secName
		        	str = str.replace(/\s+/g, '');
		        	var div_data="<option value="+str+">"+item.secName+"</option>";
	            	$(div_data).appendTo('#sectionSelect');	           
		        });
				}
		});
		
	}
	
	
	
	
	
	
	
	
	
	
	
	function updateStudentDetail1(){
		var session=$('#sessionSelect1').val();
		var dept=$('#streamSelect').val();
		//alert(dept);
		$.ajax({
			type:"GET",
			url:"GenerateStudentJSONSrv",
			data: "<%=GenerateStudentJSONSrv.Parameters.STRID%>="+dept+"&<%=GenerateStudentJSONSrv.Parameters.SESSION%>="+session+"&<%=GenerateStudentJSONSrv.Parameters.CATEGORY%>="+'general',
			dataType: 'json',
			cache: false,
			success:function(data){
				var items="";
	            
		        $.each(data.studentpersonaldetails, function (i, item) {
		        	
		            items+="<option value='"+item.studRoll+"'>" + item.studRoll +"." + item.studName + "<option>";
		        });

		        
		        $('#studentSelect1').empty();
		        $('#studentSelect1').append(items);
				}
			}
		);
		
	}
	
	
	function updateStudentDetail2(){
		var session=$('#sessionSelect2').val();
		var dept=$('#streamSelect').val();
		//alert(dept);
		$.ajax({
			type:"GET",
			url:"GenerateStudentJSONSrv",
			data: "<%=GenerateStudentJSONSrv.Parameters.STRID%>="+dept+"&<%=GenerateStudentJSONSrv.Parameters.SESSION%>="+session+"&<%=GenerateStudentJSONSrv.Parameters.CATEGORY%>="+'general',
			dataType: 'json',
			cache: false,
			success:function(data){
				var items="";
	            
		        $.each(data.studentpersonaldetails, function (i, item) {
		        	
		            items+="<option value='"+item.studRoll+"'>" + item.studRoll +"." + item.studName + "<option>";
		        });

		        
		        $('#studentSelect2').empty();
		        $('#studentSelect2').append(items);
				}
			}
		);
		
	}
	
	
	function updateStudentDetail3(){
		var session=$('#sessionSelect3').val();
		var dept=$('#streamSelect').val();
		//alert(dept);
		$.ajax({
			type:"GET",
			url:"GenerateStudentJSONSrv",
			data: "<%=GenerateStudentJSONSrv.Parameters.STRID%>="+dept+"&<%=GenerateStudentJSONSrv.Parameters.SESSION%>="+session+"&<%=GenerateStudentJSONSrv.Parameters.CATEGORY%>="+'general',
			dataType: 'json',
			cache: false,
			success:function(data){
				var items="";
	            
		        $.each(data.studentpersonaldetails, function (i, item) {
		        	
		            items+="<option value='"+item.studRoll+"'>" + item.studRoll +"." + item.studName + "<option>";
		        });

		        
		        $('#studentSelect3').empty();
		        $('#studentSelect3').append(items);
				}
			}
		);
		
	}
	
	
	function updateStudentDetail4(){
		var session=$('#sessionSelect4').val();
		var dept=$('#streamSelect').val();
		//alert(dept);
		$.ajax({
			type:"GET",
			url:"GenerateStudentJSONSrv",
			data: "<%=GenerateStudentJSONSrv.Parameters.STRID%>="+dept+"&<%=GenerateStudentJSONSrv.Parameters.SESSION%>="+session+"&<%=GenerateStudentJSONSrv.Parameters.CATEGORY%>="+'general',
			dataType: 'json',
			cache: false,
			success:function(data){
				var items="";
	            
		        $.each(data.studentpersonaldetails, function (i, item) {
		        	
		            items+="<option value='"+item.studRoll+"'>" + item.studRoll +"." + item.studName + "<option>";
		        });

		        
		        $('#studentSelect4').empty();
		        $('#studentSelect4').append(items);
				}
			}
		);
		
	}
	
	
	
	function updateStudentDetail5(){
		var session=$('#sessionSelect5').val();
		var dept=$('#streamSelect').val();
		//alert(dept);
		$.ajax({
			type:"GET",
			url:"GenerateStudentJSONSrv",
			data: "<%=GenerateStudentJSONSrv.Parameters.STRID%>="+dept+"&<%=GenerateStudentJSONSrv.Parameters.SESSION%>="+session+"&<%=GenerateStudentJSONSrv.Parameters.CATEGORY%>="+'general',
			dataType: 'json',
			cache: false,
			success:function(data){
				var items="";
	            
		        $.each(data.studentpersonaldetails, function (i, item) {
		        	
		            items+="<option value='"+item.studRoll+"'>" + item.studRoll +"." + item.studName + "<option>";
		        });

		        
		        $('#studentSelect5').empty();
		        $('#studentSelect5').append(items);
				}
			}
		);
		
	}
	
	
	
	
	
	
	
	
	
	function updateDiplomaStudentDetail1(){
		var session=$('#diplomasessionSelect1').val();
		var dept=$('#streamSelect').val();
		//alert(dept);
		$.ajax({
			type:"GET",
			url:"GenerateStudentJSONSrv",
			data: "<%=GenerateStudentJSONSrv.Parameters.STRID%>="+dept+"&<%=GenerateStudentJSONSrv.Parameters.SESSION%>="+session+"&<%=GenerateStudentJSONSrv.Parameters.CATEGORY%>="+'diploma',
			dataType: 'json',
			cache: false,
			success:function(data){
				var items="";
	            
		        $.each(data.studentpersonaldetails, function (i, item) {
		        	
		            items+="<option value='"+item.studRoll+"'>" + item.studRoll +"." + item.studName + "<option>";
		        });

		        
		        $('#diplomastudentSelect1').empty();
		        $('#diplomastudentSelect1').append(items);
				}
			}
		);
		
	}
	
	
	function updateDiplomaStudentDetail2(){
		var session=$('#diplomasessionSelect2').val();
		var dept=$('#streamSelect').val();
		//alert(dept);
		$.ajax({
			type:"GET",
			url:"GenerateStudentJSONSrv",
			data: "<%=GenerateStudentJSONSrv.Parameters.STRID%>="+dept+"&<%=GenerateStudentJSONSrv.Parameters.SESSION%>="+session+"&<%=GenerateStudentJSONSrv.Parameters.CATEGORY%>="+'diploma',
			dataType: 'json',
			cache: false,
			success:function(data){
				var items="";
	            
		        $.each(data.studentpersonaldetails, function (i, item) {
		        	
		            items+="<option value='"+item.studRoll+"'>" + item.studRoll +"." + item.studName + "<option>";
		        });

		        
		        $('#diplomastudentSelect2').empty();
		        $('#diplomastudentSelect2').append(items);
				}
			}
		);
		
	}
	
	
	function updateDiplomaStudentDetail3(){
		var session=$('#diplomasessionSelect3').val();
		var dept=$('#streamSelect').val();
		//alert(dept);
		$.ajax({
			type:"GET",
			url:"GenerateStudentJSONSrv",
			data: "<%=GenerateStudentJSONSrv.Parameters.STRID%>="+dept+"&<%=GenerateStudentJSONSrv.Parameters.SESSION%>="+session+"&<%=GenerateStudentJSONSrv.Parameters.CATEGORY%>="+'diploma',
			dataType: 'json',
			cache: false,
			success:function(data){
				var items="";
	            
		        $.each(data.studentpersonaldetails, function (i, item) {
		        	
		            items+="<option value='"+item.studRoll+"'>" + item.studRoll +"." + item.studName + "<option>";
		        });

		        
		        $('#diplomastudentSelect3').empty();
		        $('#diplomastudentSelect3').append(items);
				}
			}
		);
		
	}
	
	
	function updateDiplomaStudentDetail4(){
		var session=$('#diplomasessionSelect4').val();
		var dept=$('#streamSelect').val();
		//alert(dept);
		$.ajax({
			type:"GET",
			url:"GenerateStudentJSONSrv",
			data: "<%=GenerateStudentJSONSrv.Parameters.STRID%>="+dept+"&<%=GenerateStudentJSONSrv.Parameters.SESSION%>="+session+"&<%=GenerateStudentJSONSrv.Parameters.CATEGORY%>="+'diploma',
			dataType: 'json',
			cache: false,
			success:function(data){
				var items="";
	            
		        $.each(data.studentpersonaldetails, function (i, item) {
		        	
		            items+="<option value='"+item.studRoll+"'>" + item.studRoll +"." + item.studName + "<option>";
		        });

		        
		        $('#diplomastudentSelect4').empty();
		        $('#diplomastudentSelect4').append(items);
				}
			}
		);
		
	}
	
	
	function updateDiplomaStudentDetail5(){
		var session=$('#diplomasessionSelect5').val();
		var dept=$('#streamSelect').val();
		//alert(dept);
		$.ajax({
			type:"GET",
			url:"GenerateStudentJSONSrv",
			data: "<%=GenerateStudentJSONSrv.Parameters.STRID%>="+dept+"&<%=GenerateStudentJSONSrv.Parameters.SESSION%>="+session+"&<%=GenerateStudentJSONSrv.Parameters.CATEGORY%>="+'diploma',
			dataType: 'json',
			cache: false,
			success:function(data){
				var items="";
	            
		        $.each(data.studentpersonaldetails, function (i, item) {
		        	
		            items+="<option value='"+item.studRoll+"'>" + item.studRoll +"." + item.studName + "<option>";
		        });

		        
		        $('#diplomastudentSelect5').empty();
		        $('#diplomastudentSelect5').append(items);
				}
			}
		);
		
	}
	
	
	
	
	
	
	function updateNonDiplomaStudentDetail1(){
		var session=$('#nonDiplomaSessionSelect1').val();
		var dept=$('#streamSelect').val();
		//alert(dept);
		$.ajax({
			type:"GET",
			url:"GenerateStudentJSONSrv",
			data: "<%=GenerateStudentJSONSrv.Parameters.STRID%>="+dept+"&<%=GenerateStudentJSONSrv.Parameters.SESSION%>="+session+"&<%=GenerateStudentJSONSrv.Parameters.CATEGORY%>="+'nondiploma',
			dataType: 'json',
			cache: false,
			success:function(data){
				var items="";
	            
		        $.each(data.studentpersonaldetails, function (i, item) {
		        	
		            items+="<option value='"+item.studRoll+"'>" + item.studRoll +"." + item.studName + "<option>";
		        });

		        
		        $('#nonDiplomaStudentSelect1').empty();
		        $('#nonDiplomaStudentSelect1').append(items);
				}
			}
		);
		
	}
	
	
	function updateNonDiplomaStudentDetail2(){
		var session=$('#nonDiplomaSessionSelect2').val();
		var dept=$('#streamSelect').val();
		//alert(dept);
		$.ajax({
			type:"GET",
			url:"GenerateStudentJSONSrv",
			data: "<%=GenerateStudentJSONSrv.Parameters.STRID%>="+dept+"&<%=GenerateStudentJSONSrv.Parameters.SESSION%>="+session+"&<%=GenerateStudentJSONSrv.Parameters.CATEGORY%>="+'nondiploma',
			dataType: 'json',
			cache: false,
			success:function(data){
				var items="";
	            
		        $.each(data.studentpersonaldetails, function (i, item) {
		        	
		            items+="<option value='"+item.studRoll+"'>" + item.studRoll +"." + item.studName + "<option>";
		        });

		        
		        $('#nonDiplomaStudentSelect2').empty();
		        $('#nonDiplomaStudentSelect2').append(items);
				}
			}
		);
		
	}
	
	
	function updateNonDiplomaStudentDetail3(){
		var session=$('#nonDiplomaSessionSelect3').val();
		var dept=$('#streamSelect').val();
		//alert(dept);
		$.ajax({
			type:"GET",
			url:"GenerateStudentJSONSrv",
			data: "<%=GenerateStudentJSONSrv.Parameters.STRID%>="+dept+"&<%=GenerateStudentJSONSrv.Parameters.SESSION%>="+session+"&<%=GenerateStudentJSONSrv.Parameters.CATEGORY%>="+'nondiploma',
			dataType: 'json',
			cache: false,
			success:function(data){
				var items="";
	            
		        $.each(data.studentpersonaldetails, function (i, item) {
		        	
		            items+="<option value='"+item.studRoll+"'>" + item.studRoll +"." + item.studName + "<option>";
		        });

		        
		        $('#nonDiplomaStudentSelect3').empty();
		        $('#nonDiplomaStudentSelect3').append(items);
				}
			}
		);
		
	}
	
	
	function updateNonDiplomaStudentDetail4(){
		var session=$('#nonDiplomaSessionSelect4').val();
		var dept=$('#streamSelect').val();
		//alert(dept);
		$.ajax({
			type:"GET",
			url:"GenerateStudentJSONSrv",
			data: "<%=GenerateStudentJSONSrv.Parameters.STRID%>="+dept+"&<%=GenerateStudentJSONSrv.Parameters.SESSION%>="+session+"&<%=GenerateStudentJSONSrv.Parameters.CATEGORY%>="+'nondiploma',
			dataType: 'json',
			cache: false,
			success:function(data){
				var items="";
	            
		        $.each(data.studentpersonaldetails, function (i, item) {
		        	
		            items+="<option value='"+item.studRoll+"'>" + item.studRoll +"." + item.studName + "<option>";
		        });

		        
		        $('#nonDiplomaStudentSelect4').empty();
		        $('#nonDiplomaStudentSelect4').append(items);
				}
			}
		);
		
	}
	
	
	function updateNonDiplomaStudentDetail5(){
		var session=$('#nonDiplomaSessionSelect5').val();
		var dept=$('#streamSelect').val();
		//alert(dept);
		$.ajax({
			type:"GET",
			url:"GenerateStudentJSONSrv",
			data: "<%=GenerateStudentJSONSrv.Parameters.STRID%>="+dept+"&<%=GenerateStudentJSONSrv.Parameters.SESSION%>="+session+"&<%=GenerateStudentJSONSrv.Parameters.CATEGORY%>="+'nondiploma',
			dataType: 'json',
			cache: false,
			success:function(data){
				var items="";
	            
		        $.each(data.studentpersonaldetails, function (i, item) {
		        	
		            items+="<option value='"+item.studRoll+"'>" + item.studRoll +"." + item.studName + "<option>";
		        });

		        
		        $('#nonDiplomaStudentSelect5').empty();
		        $('#nonDiplomaStudentSelect5').append(items);
				}
			}
		);
		
	}
	
	
	function updateSectionTeacher(){
		var session=$('#multiattendancesession').val();
		var teacher=$('#teacherID').val();
		//alert(teacher);
		$.ajax({
			type:"GET",
			url:"GenerateTeacherJSONSrv",
			data: "<%=GenerateTeacherJSONSrv.Parameters.TeacherID%>="+teacher+"&<%=GenerateTeacherJSONSrv.Parameters.SESSION%>="+session+"&<%=GenerateTeacherJSONSrv.Parameters.CATEGORY%>="+'section',
			dataType: 'json',
			cache: false,
			success:function(data){
				var items="";
	            
		        $.each(data.sections, function (i, item) {
		        	
		            items+="<option value='"+item.secName+"'>" + item.secName + "<option>";
		        });

		        
		        $('#multiattendancesection').empty();
		        $('#multiattendancesection').append(items);
				}
			}
		);
		
	}
	
	
	
	
	function updateSubjectTeacher(){
		var session=$('#multiattendancesession').val();
		var teacher=$('#teacherID').val();
		var section=$('#multiattendancesection').val();
		//alert(teacher);
		$.ajax({
			type:"GET",
			url:"GenerateTeacherJSONSrv",
			data: "<%=GenerateTeacherJSONSrv.Parameters.TeacherID%>="+teacher+"&<%=GenerateTeacherJSONSrv.Parameters.SESSION%>="+session+"&<%=GenerateTeacherJSONSrv.Parameters.SECTION%>="+section+"&<%=GenerateTeacherJSONSrv.Parameters.CATEGORY%>="+'subject',
			dataType: 'json',
			cache: false,
			success:function(data){
				var items="<option value='defaultvalue'>Select Section</option>";
	            
		        $.each(data.sections, function (i, item) {
		        	
		            items+="<option value='"+item.secName+"'>" + item.secName + "<option>";
		        });

		        
		        $('#multiattendancesubject').empty();
		        $('#multiattendancesubject').append(items);
				}
			}
		);
		
	}
	
	
	
	
	
	
	
	function updateSubjectDetail(){
		var semester=$('#semester').val();
		var dept=$('#streamSelect').val();
		//alert(dept);
		$.ajax({
			type:"GET",
			url:"GenerateSubjectsJSONSrv",
			data: "<%=GenerateSubjectsJSONSrv.Parameters.STRID%>="+dept+"&<%=GenerateSubjectsJSONSrv.Parameters.SEM%>="+semester,
			dataType: 'json',
			cache: false,
			success:function(data){
				var items="";
	            
		        $.each(data.subject, function (i, item) {
		        	
		            items+="<option value='"+item.subCode+"'>" + item.subCode +"." + item.subName + "<option>";
		        });

		        
		        $('#subject').empty();
		        $('#subject').append(items);
				}
			}
		);
	}
	
	
	
	
	
	function showfield(name){
		
		if(name=='DefaultValue'){
			document.getElementById('sessiondiv').style.display="none";
			document.getElementById('studentdiv').style.display="none";
			document.getElementById('semesterdiv').style.display="none";
			document.getElementById('subjectdiv').style.display="none";
			document.getElementById('multistudentdiv').style.display="none";
			document.getElementById('multidiplomastudentdiv').style.display="none";
			document.getElementById('multinondiplomastudentdiv').style.display="none";
			document.getElementById('sectiondiv').style.display="none";
			document.getElementById('multiattendance').style.display="none";
		}
		
		
		if(name=='IndividualStudentSubjects'){
			document.getElementById('sessiondiv').style.display="block";
			document.getElementById('studentdiv').style.display="block";
			document.getElementById('semesterdiv').style.display="block";
			document.getElementById('subjectdiv').style.display="none";
			document.getElementById('multistudentdiv').style.display="none";
			document.getElementById('multidiplomastudentdiv').style.display="none";
			document.getElementById('multinondiplomastudentdiv').style.display="none";
			document.getElementById('sectiondiv').style.display="none";
			document.getElementById('multiattendance').style.display="none";
		}
		
		if(name=='IndividualStudentAttendance'){
			document.getElementById('sessiondiv').style.display="block";
			document.getElementById('studentdiv').style.display="block";
			document.getElementById('semesterdiv').style.display="block";
			document.getElementById('sectiondiv').style.display="block";
			document.getElementById('subjectdiv').style.display="none";
			document.getElementById('multistudentdiv').style.display="none";
			document.getElementById('multidiplomastudentdiv').style.display="none";
			document.getElementById('multinondiplomastudentdiv').style.display="none";
			document.getElementById('multiattendance').style.display="none";
			
		}
		
		if(name=='IndividualStudentSemesters'){
			document.getElementById('sessiondiv').style.display="block";
			document.getElementById('studentdiv').style.display="block";
			document.getElementById('semesterdiv').style.display="none";
			document.getElementById('subjectdiv').style.display="none";
			document.getElementById('multistudentdiv').style.display="none";
			document.getElementById('multidiplomastudentdiv').style.display="none";
			document.getElementById('multinondiplomastudentdiv').style.display="none";
			document.getElementById('sectiondiv').style.display="none";
			document.getElementById('multiattendance').style.display="none";
		}
		
		
		
		
		if(name=='MultipleStudentsSemester'){
			document.getElementById('multistudentdiv').style.display="block";
			document.getElementById('semesterdiv').style.display="block";
			document.getElementById('subjectdiv').style.display="none";
			document.getElementById('sessiondiv').style.display="none";
			document.getElementById('studentdiv').style.display="none";
			document.getElementById('multidiplomastudentdiv').style.display="none";
			document.getElementById('multinondiplomastudentdiv').style.display="none";
			document.getElementById('sectiondiv').style.display="none";
			document.getElementById('multiattendance').style.display="none";
		}
		
		if(name=='MultipleStudentsSubject'){
			document.getElementById('multistudentdiv').style.display="block";
			document.getElementById('semesterdiv').style.display="block";
			document.getElementById('subjectdiv').style.display="block";
			document.getElementById('sessiondiv').style.display="none";
			document.getElementById('studentdiv').style.display="none";
			document.getElementById('multidiplomastudentdiv').style.display="none";
			document.getElementById('multinondiplomastudentdiv').style.display="none";
			document.getElementById('sectiondiv').style.display="none";
			document.getElementById('multiattendance').style.display="none";
		}
		
		if(name=='Class10BoardBestMarks'){
			document.getElementById('multistudentdiv').style.display="block";
			document.getElementById('semesterdiv').style.display="none";
			document.getElementById('subjectdiv').style.display="none";
			document.getElementById('sessiondiv').style.display="none";
			document.getElementById('studentdiv').style.display="none";
			document.getElementById('multidiplomastudentdiv').style.display="none";
			document.getElementById('multinondiplomastudentdiv').style.display="none";
			document.getElementById('sectiondiv').style.display="none";
			document.getElementById('multiattendance').style.display="none";
		}
		
		if(name=='Class10BoardAvgMarks'){
			document.getElementById('multistudentdiv').style.display="block";
			document.getElementById('semesterdiv').style.display="none";
			document.getElementById('subjectdiv').style.display="none";
			document.getElementById('sessiondiv').style.display="none";
			document.getElementById('studentdiv').style.display="none";
			document.getElementById('multidiplomastudentdiv').style.display="none";
			document.getElementById('multinondiplomastudentdiv').style.display="none";
			document.getElementById('sectiondiv').style.display="none";
			document.getElementById('multiattendance').style.display="none";
		}
		
		if(name=='Class12BoardBestMarks'){
			document.getElementById('multinondiplomastudentdiv').style.display="block";
			document.getElementById('multistudentdiv').style.display="none";
			document.getElementById('semesterdiv').style.display="none";
			document.getElementById('subjectdiv').style.display="none";
			document.getElementById('sessiondiv').style.display="none";
			document.getElementById('studentdiv').style.display="none";
			document.getElementById('multidiplomastudentdiv').style.display="none";
			document.getElementById('sectiondiv').style.display="none";
			document.getElementById('multiattendance').style.display="none";
		}
		
		if(name=='Class12BoardAvgMarks'){
			document.getElementById('multinondiplomastudentdiv').style.display="block";
			document.getElementById('multistudentdiv').style.display="none";
			document.getElementById('semesterdiv').style.display="none";
			document.getElementById('subjectdiv').style.display="none";
			document.getElementById('sessiondiv').style.display="none";
			document.getElementById('studentdiv').style.display="none";
			document.getElementById('multidiplomastudentdiv').style.display="none";
			document.getElementById('sectiondiv').style.display="none";
			document.getElementById('multiattendance').style.display="none";
		}
		
		if(name=='DiplomaMarks'){
			document.getElementById('multidiplomastudentdiv').style.display="block";
			document.getElementById('multistudentdiv').style.display="none";
			document.getElementById('semesterdiv').style.display="none";
			document.getElementById('subjectdiv').style.display="none";
			document.getElementById('sessiondiv').style.display="none";
			document.getElementById('studentdiv').style.display="none";
			document.getElementById('multinondiplomastudentdiv').style.display="none";
			document.getElementById('sectiondiv').style.display="none";
			document.getElementById('multiattendance').style.display="none";
		}
		
		if(name=='JELETSelectionRank'){
			document.getElementById('multidiplomastudentdiv').style.display="block";
			document.getElementById('multistudentdiv').style.display="none";
			document.getElementById('semesterdiv').style.display="none";
			document.getElementById('subjectdiv').style.display="none";
			document.getElementById('sessiondiv').style.display="none";
			document.getElementById('studentdiv').style.display="none";
			document.getElementById('multinondiplomastudentdiv').style.display="none";
			document.getElementById('sectiondiv').style.display="none";
			document.getElementById('multiattendance').style.display="none";
		}
		
		if(name=='WBJEESelectionRank'){
			document.getElementById('multinondiplomastudentdiv').style.display="block";
			document.getElementById('multistudentdiv').style.display="none";
			document.getElementById('semesterdiv').style.display="none";
			document.getElementById('subjectdiv').style.display="none";
			document.getElementById('sessiondiv').style.display="none";
			document.getElementById('studentdiv').style.display="none";
			document.getElementById('multidiplomastudentdiv').style.display="none";
			document.getElementById('sectiondiv').style.display="none";
			document.getElementById('multiattendance').style.display="none";
		}
		
		if(name=='MultipleStudentsMultipleSemester'){
			document.getElementById('multistudentdiv').style.display="block";
			document.getElementById('semesterdiv').style.display="none";
			document.getElementById('subjectdiv').style.display="none";
			document.getElementById('sessiondiv').style.display="none";
			document.getElementById('studentdiv').style.display="none";
			document.getElementById('multidiplomastudentdiv').style.display="none";
			document.getElementById('multinondiplomastudentdiv').style.display="none";
			document.getElementById('sectiondiv').style.display="none";
			document.getElementById('multiattendance').style.display="none";
		}
		
			if(name=='MultipleStudentsAttendance'){
				document.getElementById('multistudentdiv').style.display="none";
				document.getElementById('semesterdiv').style.display="none";
				document.getElementById('subjectdiv').style.display="none";
				document.getElementById('sessiondiv').style.display="none";
				document.getElementById('studentdiv').style.display="none";
				document.getElementById('multidiplomastudentdiv').style.display="none";
				document.getElementById('multinondiplomastudentdiv').style.display="none";
				document.getElementById('sectiondiv').style.display="none";
				document.getElementById('multiattendance').style.display="block";
			}
			
				if(name=='GradeDistribution'){
					document.getElementById('multistudentdiv').style.display="none";
					document.getElementById('semesterdiv').style.display="none";
					document.getElementById('subjectdiv').style.display="none";
					document.getElementById('sessiondiv').style.display="none";
					document.getElementById('studentdiv').style.display="none";
					document.getElementById('multidiplomastudentdiv').style.display="none";
					document.getElementById('multinondiplomastudentdiv').style.display="none";
					document.getElementById('sectiondiv').style.display="none";
					document.getElementById('multiattendance').style.display="block";
				}
				
					if(name=='InternalMarks'){
						document.getElementById('multistudentdiv').style.display="none";
						document.getElementById('semesterdiv').style.display="none";
						document.getElementById('subjectdiv').style.display="none";
						document.getElementById('sessiondiv').style.display="none";
						document.getElementById('studentdiv').style.display="none";
						document.getElementById('multidiplomastudentdiv').style.display="none";
						document.getElementById('multinondiplomastudentdiv').style.display="none";
						document.getElementById('sectiondiv').style.display="none";
						document.getElementById('multiattendance').style.display="block";
					}
	}

	
	


</script>

<%
		if(ja!=null){
		if(param.equalsIgnoreCase("IndividualStudentSubjects")){
%>


<script type="text/javascript">
        
        
            google.charts.load('current', {packages: ['corechart']});
            google.charts.setOnLoadCallback(drawChart);
            
            function drawChart() {
                  var data =new google.visualization.DataTable();
                  data.addColumn('string','Subject');
                  data.addColumn('number','Marks');
				
                  data.addRows([<%=ja%>]);
                  
                  var options = {'title':'Student Marks Details',
                          'width':900,
                          'height':300};

                  var chart = new google.visualization.PieChart(document.getElementById('my_chart'));
                  chart.draw(data, options);

                }
            
            
        
</script>

<%
		}
		else if(param.equalsIgnoreCase("MultipleStudentsSubject")){
%>

<script type="text/javascript">
        
        
            google.charts.load('current', {packages: ['corechart', 'bar']})
            google.charts.setOnLoadCallback(drawStacked);
            
            function drawStacked() {
                  var data = google.visualization.arrayToDataTable([
                     ['Name', 'Marks'],
                     <%=ja%>
                  ]);

                  var options = {
                    title: 'Student Marks Details',
                    chartArea: {width: '50%'},
                    height:400,
                    isStacked: false,
                    vAxis: {
                      title: '<%=yaxis%>'
                    },
                  };
                  var chart = new google.visualization.BarChart(document.getElementById('my_chart'));
                  chart.draw(data, options);
                }
            
            
        
</script>

<%
		}
		else if(param.equalsIgnoreCase("Class10BoardBestMarks")){
%>


<script type="text/javascript">
        
        
            google.charts.load('current', {packages: ['corechart', 'bar']})
            google.charts.setOnLoadCallback(drawStacked);
            
            function drawStacked() {
                  var data = google.visualization.arrayToDataTable([
                     ['Name', 'Marks'],
                     <%=ja%>
                  ]);

                  var options = {
                    title: 'Student Class 10 Best Marks Details',
                    chartArea: {width: '50%'},
                    height:400,
                    isStacked: false,
                    vAxis: {
                      title: '<%=yaxis%>'
                    },
                  };
                  var chart = new google.visualization.BarChart(document.getElementById('my_chart'));
                  chart.draw(data, options);
                }
            
            
        
</script>

<%
		}
		else if(param.equalsIgnoreCase("Class10BoardAvgMarks")){
%>


<script type="text/javascript">
        
        
            google.charts.load('current', {packages: ['corechart', 'bar']})
            google.charts.setOnLoadCallback(drawStacked);
            
            function drawStacked() {
                  var data = google.visualization.arrayToDataTable([
                     ['Name', 'Marks'],
                     <%=ja%>
                  ]);

                  var options = {
                    title: 'Student Class 10 Average Marks Details',
                    chartArea: {width: '50%'},
                    height:400,
                    isStacked: false,
                    vAxis: {
                      title: '<%=yaxis%>'
                    },
                  };
                  var chart = new google.visualization.BarChart(document.getElementById('my_chart'));
                  chart.draw(data, options);
                }
            
            
        
</script>


<%
		}
		else if(param.equalsIgnoreCase("Class12BoardBestMarks")){
%>


<script type="text/javascript">
        
        
            google.charts.load('current', {packages: ['corechart', 'bar']})
            google.charts.setOnLoadCallback(drawStacked);
            
            function drawStacked() {
                  var data = google.visualization.arrayToDataTable([
                     ['Name', 'Marks'],
                     <%=ja%>
                  ]);

                  var options = {
                    title: 'Student Class 12 Best Marks Details',
                    chartArea: {width: '50%'},
                    height:400,
                    isStacked: false,
                    vAxis: {
                      title: '<%=yaxis%>'
                    },
                  };
                  var chart = new google.visualization.BarChart(document.getElementById('my_chart'));
                  chart.draw(data, options);
                }
            
            
        
</script>

<%
		}
		else if(param.equalsIgnoreCase("Class12BoardAvgMarks")){
%>


<script type="text/javascript">
        
        
            google.charts.load('current', {packages: ['corechart', 'bar']})
            google.charts.setOnLoadCallback(drawStacked);
            
            function drawStacked() {
                  var data = google.visualization.arrayToDataTable([
                     ['Name', 'Marks'],
                     <%=ja%>
                  ]);

                  var options = {
                    title: 'Student Class 12 Average Marks Details',
                    chartArea: {width: '50%'},
                    height:400,
                    isStacked: false,
                    vAxis: {
                      title: '<%=yaxis%>'
                    },
                  };
                  var chart = new google.visualization.BarChart(document.getElementById('my_chart'));
                  chart.draw(data, options);
                }
            
            
        
</script>


<%
		}
		else if(param.equalsIgnoreCase("DiplomaMarks")){
%>


<script type="text/javascript">
        
        
            google.charts.load('current', {packages: ['corechart', 'bar']})
            google.charts.setOnLoadCallback(drawStacked);
            
            function drawStacked() {
                  var data = google.visualization.arrayToDataTable([
                     ['Name', 'Marks'],
                     <%=ja%>
                  ]);

                  var options = {
                    title: 'Student Diploma Marks Details',
                    chartArea: {width: '50%'},
                    height:400,
                    isStacked: false,
                    vAxis: {
                      title: '<%=yaxis%>'
                    },
                  };
                  var chart = new google.visualization.BarChart(document.getElementById('my_chart'));
                  chart.draw(data, options);
                }
            
            
        
</script>

<%
		}
		else if(param.equalsIgnoreCase("WBJEESelectionRank")){
%>


<script type="text/javascript">
        
        
            google.charts.load('current', {packages: ['corechart', 'bar']})
            google.charts.setOnLoadCallback(drawStacked);
            
            function drawStacked() {
                  var data = google.visualization.arrayToDataTable([
                     ['Name', 'Rank'],
                     <%=ja%>
                  ]);

                  var options = {
                    title: 'WBJEE Rank Comparison',
                    chartArea: {width: '50%'},
                    height:400,
                    isStacked: false,
                    vAxis: {
                      title: '<%=yaxis%>'
                    },
                  };
                  var chart = new google.visualization.BarChart(document.getElementById('my_chart'));
                  chart.draw(data, options);
                }
            
            
        
</script>

<%
		}
		else if(param.equalsIgnoreCase("JELETSelectionRank")){
%>


<script type="text/javascript">
        
        
            google.charts.load('current', {packages: ['corechart', 'bar']})
            google.charts.setOnLoadCallback(drawStacked);
            
            function drawStacked() {
                  var data = google.visualization.arrayToDataTable([
                     ['Name', 'Rank'],
                     <%=ja%>
                  ]);

                  var options = {
                    title: 'JELET Rank Comparison',
                    chartArea: {width: '50%'},
                    height:400,
                    isStacked: false,
                    vAxis: {
                      title: '<%=yaxis%>'
                    },
                  };
                  var chart = new google.visualization.BarChart(document.getElementById('my_chart'));
                  chart.draw(data, options);
                }
            
            
        
</script>
<% 
		}
		else if(param.equalsIgnoreCase("IndividualStudentSemesters")){
%>


<script type="text/javascript">
        
        
			google.charts.load('current', {packages: ['corechart', 'line']});
			google.charts.setOnLoadCallback(drawBasic);
            
            function drawBasic() {
            	var data =new google.visualization.DataTable();
                data.addColumn('string','Semester');
                data.addColumn('number','Marks');
				
                data.addRows([<%=ja%>]);
                
                var options = {
                        hAxis: {
                          title: 'Semester'
                        },
                        vAxis: {
                          title: 'Marks'
                        },
                        backgroundColor: '#f1f8e9'
                      };
                
                
                  var chart = new google.visualization.LineChart(document.getElementById('my_chart'));
                  chart.draw(data, options);
                }
            
            
        
</script>




<% 
		}
		else if(param.equalsIgnoreCase("MultipleStudentsMultipleSemester")){
%>


<script type="text/javascript">
        
        
			google.charts.load('current', {packages: ['corechart', 'line']});
			google.charts.setOnLoadCallback(drawBasic);
            
            function drawBasic() {
            	var data =new google.visualization.DataTable();
            	var stud1=$('#studentSelect1').val();
            	var stud2=$('#studentSelect2').val();
            	var stud3=$('#studentSelect3').val();
            	var stud4=$('#studentSelect4').val();
            	var stud5=$('#studentSelect5').val();
                data.addColumn('string','Semester');
                data.addColumn('number',stud1);
                data.addColumn('number',stud2);
                data.addColumn('number',stud3);
                data.addColumn('number',stud4);
                data.addColumn('number',stud5);
				
                data.addRows([<%=ja%>]);
                
                var options = {
                        hAxis: {
                          title: 'Semester'
                        },
                        vAxis: {
                          title: 'Marks'
                        },
                        backgroundColor: '#f1f8e9'
                      };
                
                
                  var chart = new google.visualization.LineChart(document.getElementById('my_chart'));
                  chart.draw(data, options);
                }
            
            
        
</script>

<% 
		}
		else if(param.equalsIgnoreCase("MultipleStudentsSemester")){
%>


<script type="text/javascript">
        
        
            google.charts.load('current', {packages: ['corechart', 'bar']})
            google.charts.setOnLoadCallback(drawStacked);
            
            function drawStacked() {
                  var data = google.visualization.arrayToDataTable([
                     ['Name', 'SGPA'],
                     <%=ja%>
                  ]);

                  var options = {
                    title: 'Student SGPA Details',
                    chartArea: {width: '50%'},
                    height:400,
                    isStacked: false,
                    vAxis: {
                      title: '<%=yaxis%>'
                    },
                  };
                  var chart = new google.visualization.BarChart(document.getElementById('my_chart'));
                  chart.draw(data, options);
                }
            
            
        
</script>


<%
		}
		else if(param.equalsIgnoreCase("IndividualStudentAttendance")){
%>


<script type="text/javascript">
        
        
            google.charts.load('current', {packages: ['corechart', 'bar']})
            google.charts.setOnLoadCallback(drawStacked);
            
            function drawStacked() {
                  var data = google.visualization.arrayToDataTable([
                     ['Subject', 'Attendance'],
                     <%=ja%>
                  ]);

                  var options = {
                    title: 'SubjectWise Attendance',
                    chartArea: {width: '50%'},
                    height:400,
                    isStacked: false,
                    vAxis: {
                      title: '<%=yaxis%>'
                    },
                  };
                  var chart = new google.visualization.BarChart(document.getElementById('my_chart'));
                  chart.draw(data, options);
                }
            
            
        
</script>


<%
		}
		else if(param.equalsIgnoreCase("GradeDistribution")){
%>


<script type="text/javascript">
        
        
            google.charts.load('current', {packages: ['corechart', 'bar']})
            google.charts.setOnLoadCallback(drawStacked);
            
            function drawStacked() {
                  var data = google.visualization.arrayToDataTable([
                     ['Grade', 'Numbers'],
                     <%=ja%>
                  ]);

                  var options = {
                    title: 'Gradewise Details',
                    chartArea: {width: '50%'},
                    height:400,
                    isStacked: false,
                    vAxis: {
                      title: '<%=yaxis%>'
                    },
                  };
                  var chart = new google.visualization.BarChart(document.getElementById('my_chart'));
                  chart.draw(data, options);
                }
            
            
        
</script>


<%
		}
		else
   			
%>

<%
   	}
		session.removeAttribute("param");
		session.removeAttribute("ja");
		session.removeAttribute("yaxis");
%>
   </head>
 <body style="font-family:roboto;">

  <div class="clearfix borderbox" id="page"><!-- column -->
   <div class="browser_width colelem" id="u94-bw">
    <div id="u94"><!-- group -->
     <div class="clearfix" id="u94_align_to_page">
      <div class="clearfix grpelem" id="u100-6"><!-- content -->
       <p id="u100-4"><span id="u100">SRRA</span><span id="u100-2"> </span><span id="u100-3">V1.0</span></p>
      </div>
      <div class="grpelem" id="u104"><!-- simple frame --></div>
      <div class="clearfix grpelem" id="u107-4"><!-- content -->
       <p>Welcome</p>
      </div>
      <div class="clearfix grpelem" id="pu121"><!-- group -->
       <div class="clip_frame grpelem" id="u121"><!-- image -->
        <a href="LogoutSrv" id="logout"><img class="block" id="u121_img" src="images/logout-icon.png?crc=3787223987" alt="" data-image-width="25" data-image-height="29"/></a>
       </div>
       <div class="clearfix grpelem" id="u131-4"><!-- content -->
        <a href="LogoutSrv" id="logout" >Logout</a>
       </div>
       
      </div>
     </div>
    </div>
   </div>
   <div class="clearfix colelem" id="u146-4"><!-- content -->
    <p></p>
   </div>
   <div class="clearfix colelem" id="u149-3"><!-- content -->
    <p>&nbsp;</p>
   </div>
   
   <div id="u134-wrapper">
    <div class="pinned-colelem" id="u134"><!-- custom html -->
	<center>
	<form method="post">
	
	<input type="hidden" name=" "id="streamSelect" value="<%=(session.getAttribute("stream")).toString()%>" >
	
	<input type="hidden" name=" "id="teacherID" value="<%=trbean.getTeacherID()%>" >
	
	<%
		int strID=(int)session.getAttribute(LoginSrv.Parameters.STREAM);
		SessionDao sesdao=new SessionDaoImpl();
		ArrayList<String> sesList=sesdao.fetchDistinctSessions(strID);
		StreamDao stdao=new StreamDaoImpl();
		StreamBean strbean=stdao.fetchStreamDetails(strID);
		if(session.getAttribute("MSG")!=null){
	%>
	<h3><%=session.getAttribute("MSG").toString() %></h3>
	<% session.removeAttribute("MSG");
	} %>
	<h3>Comparison Criteria</h3>
	<select name="compareParameter" id="compareParameter" onchange="showfield(this.options[this.selectedIndex].value)">
		<option value="DefaultValue">Select Comparison Category</option>
		<option value="IndividualStudentSubjects">Individual Student Subjects</option>
		<option value="MultipleStudentsSubject">Multiple Students Subject</option>
		<option value="IndividualStudentSemesters">Individual Student Semesters</option>
		<option value="MultipleStudentsSemester">Multiple Students Single Semester Table</option>
		<!--  <option value="MultipleStudentsMultipleSemester">Multiple Students Performance Upto Present Semester</option>-->
		<option value="IndividualStudentAttendance">Individual Student Attendance</option>
		<option value="MultipleStudentsAttendance">Multiple Students Attendance Table</option>
		<option value="WBJEESelectionRank">WBJEE Selection Rank</option>
		<option value="JELETSelectionRank">JELET Selection Rank</option>
		<option value="Class10BoardBestMarks">Class10 Board Best Marks</option>
		<option value="Class10BoardAvgMarks">Class10 Board Avg Marks</option>
		<option value="Class12BoardBestMarks">Class12 Board Best Marks</option>
		<option value="Class12BoardAvgMarks">Class12 Board Avg Marks</option>
		<option value="DiplomaMarks">DiplomaMarks</option>
		<option value="GradeDistribution">Grade Distribution in a Subject</option>
<!-- 		<option value="InternalMarks">Generate Internal Marks Table</option> -->
	</select>


	<div id="sessiondiv" style="display:none;">
				<select name="sesSelect" id="sessionSelect" onchange="updateStudentDetail()">
				<option value="0">Select Session</option>
				<%
					for(String s:sesList){
						
				%>
					<option value="<%=s%>"><%=s %><option>
				<%
					}
				%>
				</select>
	</div>
	
	
	
			<%
				System.out.println(session.getAttribute("stream").toString());
			%>
	
	<div id="studentdiv" style="display:none;">
				<select name="studSelect" id="studentSelect">
				<option value="defaultvalue">Select Student</option>
				</select>
	</div>
	
	<div id="multistudentdiv" style="display:none;">
		
				1st student
				<select name="sesSelect1" id="sessionSelect1" onchange="updateStudentDetail1()">
				<option value="0">Select Session</option>
				<%
					for(String s:sesList){
						
				%>
					<option value="<%=s%>"><%=s %><option>
				<%
					}
				%>
				</select>
				
				<select name="studSelect1" id="studentSelect1">
					<option value="defaultvalue">Select Student</option>
				</select>
				<br/><br/>
				
				2nd student
				<select name="sesSelect2" id="sessionSelect2" onchange="updateStudentDetail2()">
				<option value="0">Select Session</option>
				<%
					for(String s:sesList){
						
				%>
					<option value="<%=s%>"><%=s %><option>
				<%
					}
				%>
				</select>
				
				<select name="studSelect2" id="studentSelect2">
					<option value="defaultvalue">Select Student</option>
				</select>
				<br/><br/>
				
				
				3rd student
				<select name="sesSelect3" id="sessionSelect3" onchange="updateStudentDetail3()">
				<option value="0">Select Session</option>
				<%
					for(String s:sesList){
						
				%>
					<option value="<%=s%>"><%=s %><option>
				<%
					}
				%>
				</select>
				
				<select name="studSelect3" id="studentSelect3">
					<option value="defaultvalue">Select Student</option>
				</select>
				<br/><br/>
				
				
				4th student
				<select name="sesSelect4" id="sessionSelect4" onchange="updateStudentDetail4()">
				<option value="0">Select Session</option>
				<%
					for(String s:sesList){
						
				%>
					<option value="<%=s%>"><%=s %><option>
				<%
					}
				%>
				</select>
				
				<select name="studSelect4" id="studentSelect4">
					<option value="defaultvalue">Select Student</option>
				</select>
				<br/><br/>
				
				5th student
				<select name="sesSelect5" id="sessionSelect5" onchange="updateStudentDetail5()">
				<option value="0">Select Session</option>
				<%
					for(String s:sesList){
						
				%>
					<option value="<%=s%>"><%=s %><option>
				<%
					}
				%>
				</select>
				
				<select name="studSelect5" id="studentSelect5">
					<option value="defaultvalue">Select Student</option>
				</select>
				<br/><br/>
				
				
				
	</div>
	
	
	
	
	<div id="semesterdiv" style="display:none;">
				<select name="semester" id="semester" onchange="updateSubjectDetail()">
				<option value="defaultvalue">Select Semester</option>
				<%
					for(int i=1;i<=strbean.getStrNoOfSem();i++){
				%>
				<option value="<%=i%>">Semester <%=i%></option>
				<%
					}
				%>
				</select>
	</div>
	
	
	<div id="multidiplomastudentdiv" style="display:none;">
		
				1st student
				<select name="diplomasesSelect1" id="diplomasessionSelect1" onchange="updateDiplomaStudentDetail1()">
				<option value="0">Select Session</option>
				<%
					for(String s:sesList){
						
				%>
					<option value="<%=s%>"><%=s %><option>
				<%
					}
				%>
				</select>
				
				<select name="diplomastudSelect1" id="diplomastudentSelect1">
					<option value="defaultvalue">Select Student</option>
				</select>
				<br/><br/>
				
				2nd student
				<select name="diplomasesSelect2" id="diplomasessionSelect2" onchange="updateDiplomaStudentDetail2()">
				<option value="0">Select Session</option>
				<%
					for(String s:sesList){
						
				%>
					<option value="<%=s%>"><%=s %><option>
				<%
					}
				%>
				</select>
				
				<select name="diplomastudSelect2" id="diplomastudentSelect2">
					<option value="defaultvalue">Select Student</option>
				</select>
				<br/><br/>
				
				
				3rd student
				<select name="diplomasesSelect3" id="diplomasessionSelect3" onchange="updateDiplomaStudentDetail3()">
				<option value="0">Select Session</option>
				<%
					for(String s:sesList){
						
				%>
					<option value="<%=s%>"><%=s %><option>
				<%
					}
				%>
				</select>
				
				<select name="diplomastudSelect3" id="diplomastudentSelect3">
					<option value="defaultvalue">Select Student</option>
				</select>
				<br/><br/>
				
				
				4th student
				<select name="diplomasesSelect4" id="diplomasessionSelect4" onchange="updateDiplomaStudentDetail4()">
				<option value="0">Select Session</option>
				<%
					for(String s:sesList){
						
				%>
					<option value="<%=s%>"><%=s %><option>
				<%
					}
				%>
				</select>
				
				<select name="diplomastudSelect4" id="diplomastudentSelect4">
					<option value="defaultvalue">Select Student</option>
				</select>
				<br/><br/>
				
				5th student
				<select name="diplomasesSelect5" id="diplomasessionSelect5" onchange="updateDiplomaStudentDetail5()">
				<option value="0">Select Session</option>
				<%
					for(String s:sesList){
						
				%>
					<option value="<%=s%>"><%=s %><option>
				<%
					}
				%>
				</select>
				
				<select name="diplomastudSelect5" id="diplomastudentSelect5">
					<option value="defaultvalue">Select Student</option>
				</select>
				<br/><br/>
				
				
				
	</div>
	
	
		
		
		
		<div id="multinondiplomastudentdiv" style="display:none;">
		
				1st student
				<select name="nonDiplomaSesSelect1" id="nonDiplomaSessionSelect1" onchange="updateNonDiplomaStudentDetail1()">
				<option value="0">Select Session</option>
				<%
					for(String s:sesList){
						
				%>
					<option value="<%=s%>"><%=s %><option>
				<%
					}
				%>
				</select>
				
				<select name="nonDiplomaStudSelect1" id="nonDiplomaStudentSelect1">
					<option value="defaultvalue">Select Student</option>
				</select>
				<br/><br/>
				
				2nd student
				<select name="nonDiplomaSesSelect2" id="nonDiplomaSessionSelect2" onchange="updateNonDiplomaStudentDetail2()">
				<option value="0">Select Session</option>
				<%
					for(String s:sesList){
						
				%>
					<option value="<%=s%>"><%=s %><option>
				<%
					}
				%>
				</select>
				
				<select name="nonDiplomaStudSelect2" id="nonDiplomaStudentSelect2">
					<option value="defaultvalue">Select Student</option>
				</select>
				<br/><br/>
				
				
				3rd student
				<select name="nonDiplomaSesSelect3" id="nonDiplomaSessionSelect3" onchange="updateNonDiplomaStudentDetail3()">
				<option value="0">Select Session</option>
				<%
					for(String s:sesList){
						
				%>
					<option value="<%=s%>"><%=s %><option>
				<%
					}
				%>
				</select>
				
				<select name="nonDiplomaStudSelect3" id="nonDiplomaStudentSelect3">
					<option value="defaultvalue">Select Student</option>
				</select>
				<br/><br/>
				
				
				4th student
				<select name="nonDiplomaSesSelect4" id="nonDiplomaSessionSelect4" onchange="updateNonDiplomaStudentDetail4()">
				<option value="0">Select Session</option>
				<%
					for(String s:sesList){
						
				%>
					<option value="<%=s%>"><%=s %><option>
				<%
					}
				%>
				</select>
				
				<select name="nonDiplomaStudSelect4" id="nonDiplomaStudentSelect4">
					<option value="defaultvalue">Select Student</option>
				</select>
				<br/><br/>
				
				5th student
				<select name="nonDiplomaSesSelect5" id="nonDiplomaSessionSelect5" onchange="updateNonDiplomaStudentDetail5()">
				<option value="0">Select Session</option>
				<%
					for(String s:sesList){
						
				%>
					<option value="<%=s%>"><%=s %><option>
				<%
					}
				%>
				</select>
				
				<select name="nonDiplomaStudSelect5" id="nonDiplomaStudentSelect5">
					<option value="defaultvalue">Select Student</option>
				</select>
				<br/><br/>
				
				
				
	</div>
		
		<div id="multiattendance" style="display:none;">
			<select name="multiattendancesession" id="multiattendancesession" onchange="updateSectionTeacher()">
				<option value="defaultvalue">Select Session</option>
				<%
					
					ArrayList<String> sesName=tdao.fetchTeacherSessionsTaught(trbean.getTeacherID());
					for(String s:sesName){
				%>
					<option value="<%=s%>"><%=s %><option>
				<%
					}
				%>	
				</select><br/><br/>
				
				<select name="multiattendancesection" id="multiattendancesection" onchange="updateSubjectTeacher()">
				<option value="defaultvalue">Select Section</option>
				</select>
				<br/><br/>
				
				<select name="multiattendancesubject" id="multiattendancesubject">
				<option value="defaultvalue">Select Subject</option>
				</select>
				<br/><br/>
				
				
		</div>		
	
	<div id="subjectdiv" style="display:none;">
				<select name="subject" id="subject">
				<option value="defaultvalue">Select Subject</option>
				</select>
	</div>
	
	<div id="sectiondiv" style="display:none;">
				<select name="sectionSelect" id="sectionSelect">
				<option value="defaultvalue">Select Section</option>
				</select>
	</div>
	
	<input type="submit" value="Generate Chart" onclick="form.action='GenerateStudentChartSrv';" 
 >
 
 <input type="submit" value="Generate Table" onclick="form.action='GenerateStudentTableSrv';" 
 ><br><br>
	
	</form>
	
	<div id="my_chart"></div>		

	  <%
            Object t=session.getAttribute("table");
            if(t!=null){
                boolean table=(Boolean)t;
                if(table){
                ArrayList<ArrayList<String>> aalist=(ArrayList<ArrayList<String>>)session.getAttribute("aalist");
            
        %>
            <table style="    background-color: rgba(23, 0, 0, 0.6);
    color: white;
    min-width: 800px">
                
                <%for(ArrayList<String> alist:aalist){
                    %>
                <tr style="border: 2px solid white;">
                    <%for(String x:alist){ %>
                    <td style="border: 2px solid white;padding-left: 10px;"><%=x %></td>
                    <%} %>
                </tr>
                <%} %>
            </table>
        <%
            }
            }
            session.removeAttribute("table");
        %>
		
	</center>
</div>

    </div>
   
   
   
   <div class="verticalspacer" data-offset-top="200" data-content-above-spacer="200" data-content-below-spacer="400"></div>
   <div class="browser_width colelem" id="u97-bw">
    <div id="u97"><!-- simple frame --></div>
   </div>
  </div>
  <!-- Other scripts -->
  <script type="text/javascript">
   window.Muse.assets.check=function(d){if(!window.Muse.assets.checked){window.Muse.assets.checked=!0;var b={},c=function(a,b){if(window.getComputedStyle){var c=window.getComputedStyle(a,null);return c&&c.getPropertyValue(b)||c&&c[b]||""}if(document.documentElement.currentStyle)return(c=a.currentStyle)&&c[b]||a.style&&a.style[b]||"";return""},a=function(a){if(a.match(/^rgb/))return a=a.replace(/\s+/g,"").match(/([\d\,]+)/gi)[0].split(","),(parseInt(a[0])<<16)+(parseInt(a[1])<<8)+parseInt(a[2]);if(a.match(/^\#/))return parseInt(a.substr(1),
16);return 0},g=function(g){for(var f=document.getElementsByTagName("link"),h=0;h<f.length;h++)if("text/css"==f[h].type){var i=(f[h].href||"").match(/\/?css\/([\w\-]+\.css)\?crc=(\d+)/);if(!i||!i[1]||!i[2])break;b[i[1]]=i[2]}f=document.createElement("div");f.className="version";f.style.cssText="display:none; width:1px; height:1px;";document.getElementsByTagName("body")[0].appendChild(f);for(h=0;h<Muse.assets.required.length;){var i=Muse.assets.required[h],l=i.match(/([\w\-\.]+)\.(\w+)$/),k=l&&l[1]?
l[1]:null,l=l&&l[2]?l[2]:null;switch(l.toLowerCase()){case "css":k=k.replace(/\W/gi,"_").replace(/^([^a-z])/gi,"_$1");f.className+=" "+k;k=a(c(f,"color"));l=a(c(f,"backgroundColor"));k!=0||l!=0?(Muse.assets.required.splice(h,1),"undefined"!=typeof b[i]&&(k!=b[i]>>>24||l!=(b[i]&16777215))&&Muse.assets.outOfDate.push(i)):h++;f.className="version";break;case "js":h++;break;default:throw Error("Unsupported file type: "+l);}}d?d().jquery!="1.8.3"&&Muse.assets.outOfDate.push("jquery-1.8.3.min.js"):Muse.assets.required.push("jquery-1.8.3.min.js");
f.parentNode.removeChild(f);if(Muse.assets.outOfDate.length||Muse.assets.required.length)f="Some files on the server may be missing or incorrect. Clear browser cache and try again. If the problem persists please contact website author.",g&&Muse.assets.outOfDate.length&&(f+="\nOut of date: "+Muse.assets.outOfDate.join(",")),g&&Muse.assets.required.length&&(f+="\nMissing: "+Muse.assets.required.join(",")),alert(f)};location&&location.search&&location.search.match&&location.search.match(/muse_debug/gi)?setTimeout(function(){g(!0)},5E3):g()}};
var muse_init=function(){require.config({baseUrl:""});require(["jquery","museutils","whatinput","jquery.watch"],function(d){var $ = d;$(document).ready(function(){try{
window.Muse.assets.check($);/* body */
Muse.Utils.transformMarkupToFixBrowserProblemsPreInit();/* body */
Muse.Utils.prepHyperlinks(true);/* body */
Muse.Utils.resizeHeight('.browser_width');/* resize height */
Muse.Utils.requestAnimationFrame(function() { $('body').addClass('initialized'); });/* mark body as initialized */
Muse.Utils.fullPage('#page');/* 100% height page */
Muse.Utils.showWidgetsWhenReady();/* body */
Muse.Utils.transformMarkupToFixBrowserProblems();/* body */
}catch(b){if(b&&"function"==typeof b.notify?b.notify():Muse.Assert.fail("Error calling selector function: "+b),false)throw b;}})})};

</script>
  <!-- RequireJS script -->
  <script src="scripts/require.js?crc=244322403" type="text/javascript" async data-main="scripts/museconfig.js?crc=168988563" onload="if (requirejs) requirejs.onError = function(requireType, requireModule) { if (requireType && requireType.toString && requireType.toString().indexOf && 0 <= requireType.toString().indexOf('#scripterror')) window.Muse.assets.check(); }" onerror="window.Muse.assets.check();"></script>
   </body>
</html>
<%
	} else {
		response.sendRedirect("LogoutSrv");
	}
%>
	