
<%@page import="com.bean.StreamBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.dao.StreamDaoImpl"%>
<%@page import="com.dao.StreamDao"%>
<%@page import="com.servlet.TeacherRegisterSrv"%>
<%@page import="com.servlet.ConfigSrv"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


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
    border-radius: 15px;
	-moz-border-radius: 15px;
	-webkit-border-radius: 15px;
  	-ms-border-radius: 15px;
  	-o-border-radius: 15px;
  	font-weight: bold;
  	text-transform: uppercase;
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
  <script type="text/javascript">
   
</script>
<script type="text/javascript">

function updateSelect()
{
	var session=$('#sessionSelect').val();
	//window.alert("Are you Ok?");
	$.ajax({
	    type:"GET",
	    url:"GetSectionsOfSessionSrv",
	    data:"sesID="+session,
	    dataType: 'json',
	    cache: false,
	    success:function(data) {
	    	//alert(stream);
	       	var selector = $('#sectionSelect');
	        selector.empty(); // remove old options
	        var d='<option value="0">Select Semester</option>';
	        $(d).appendTo('#sectionSelect');
	        //$("<option value='0'>Select Semester</option>").appendTo('#sectionSelect');
			$.each(data.sections, function (i, item) {
	        	
				var div_data='<option value="'+item.secName+'">'+item.secName+'</option>';
				$(div_data).appendTo('#sectionSelect');
	        });
        }									     
    }
	);	
}
</script>
   </head>
 <body style="font-family:roboto;">

  
   <div class="clearfix colelem" id="u146-4"><!-- content -->
    <p>Register As Teacher</p>
   </div>
   <div class="clearfix colelem" id="u149-3"><!-- content -->
    <p>&nbsp;</p>
   </div>
   
   <div id="u134-wrapper">
    <div class="pinned-colelem" id="u134"><!-- custom html -->
	     <center>
			<form margin-top="25px" action="TeacherRegisterSrv" method="post">
				<input type="text" name="<%=TeacherRegisterSrv.TeacherParameters.NAME%>" placeholder="Enter Name" required="required"><br><br>
				<input type="text" name="<%=TeacherRegisterSrv.TeacherParameters.ADDRESS %>" placeholder="Enter Address" required="required"><br><br>
				<input type="text" name="<%=TeacherRegisterSrv.TeacherParameters.DOB %>" placeholder="D.O.B." required="required"><br><br>
				<input type="number" name="<%=TeacherRegisterSrv.TeacherParameters.MOB %>" placeholder="Mobile Number" required="required"><br><br>
				<input type="email" name="<%=TeacherRegisterSrv.TeacherParameters.EMAIL %>" placeholder="Email" required="required"><br><br>
				<input type="designation" name="<%=TeacherRegisterSrv.TeacherParameters.DESIGNATION%>" placeholder="Your Designation" required="required"><br><br>
				<input type="password" name="<%=TeacherRegisterSrv.TeacherParameters.PASSWORD%>" placeholder="Enter Password" required="required"><br><br>
				
				<%
				StreamDao streamDao = new StreamDaoImpl();
				ArrayList<StreamBean> streamList = streamDao.fetchAllStream();
				%> 
				<select name="<%=TeacherRegisterSrv.TeacherParameters.STRID %>" id="streamSelect" onchange="updateSubjectSelectDropdown()">
						
					 <%
					for(StreamBean s:streamList){
					%>
						<option value="<%=s.getStrID()%>"><%=s.getStrID()%>.<%=s.getStrName() %></option>
					<%
					}
					%>
					
				</select>
				<br><br>
				<input type="submit" value="Register"> 
				
				<br>
				<a href="index.jsp">Go to Login</a><br/><br/>
				<%
				Object o=session.getAttribute(TeacherRegisterSrv.TeacherParameters.DEL_ADD_SUCCESS);
				if(o!=null){
					String msg=o.toString();
				%>
					<span style="color: green;font-size: 18px;font-weight: bold;">Teacher registered with ID :<%=msg %> </span><br/>
				<%
					session.removeAttribute(TeacherRegisterSrv.TeacherParameters.DEL_ADD_SUCCESS);
				}
				%>
			</form>
		
				
		</center>
	</div>

    </div>
   
   
   
<!--    <div class="verticalspacer" data-offset-top="200" data-content-above-spacer="200" data-content-below-spacer="400"></div> -->
<!--    <div class="browser_width colelem" id="u97-bw"> -->
<!--     <div id="u97">simple frame</div> -->
<!--    </div> -->
<!--   </div> -->
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

