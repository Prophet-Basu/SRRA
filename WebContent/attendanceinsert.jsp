<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.sql.Date"%>
<%@page import="com.bean.StudentPersonalDetailsBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.text.DateFormat"%>
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
  <link rel="stylesheet" type="text/css" href="css/site_global_table.css?crc=443350757"/>
  <link rel="stylesheet" type="text/css" href="css/index_table.css?crc=80948381" id="pagesheet"/>
  <!-- Other scripts -->
  <script type="text/javascript">
   var __adobewebfontsappname__ = "muse";
</script>
  <!-- JS includes -->
  <script type="text/javascript">
   document.write('\x3Cscript src="' + (document.location.protocol == 'https:' ? 'https:' : 'http:') + '//webfonts.creativecloud.com/roboto:n7:default;damion:n4:default.js" type="text/javascript">\x3C/script>');
</script>
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
   </head>
 <body>

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
        <img class="block" id="u121_img" src="images/logout-icon.png?crc=3787223987" alt="" data-image-width="25" data-image-height="29"/>
       </div>
       <div class="clearfix grpelem" id="u131-4"><!-- content -->
        <p>Logout</p>
       </div>
       <div class="grpelem" id="u143"><!-- simple frame --></div>
      </div>
     </div>
    </div>
   </div>
   <div class="clearfix colelem" id="u146-4"><!-- content -->
    <p>Insert Attendance</p>
   </div>
    
<%
	//DateFormat formatDat
	DateTimeFormatter dtf=DateTimeFormatter.ofPattern("yyyy/MM/dd");
	LocalDate ldate=LocalDate.now();
	//Date date=new Date();
	String s=dtf.format(ldate);
	ArrayList<StudentPersonalDetailsBean> spList=(ArrayList<StudentPersonalDetailsBean>)session.getAttribute("STLIST");
%>
   
   <div  style="text-align:center;display:inline-table;margin-top:100px;position:relative;width: 100%;">
  <center>
	<form action="InsertAttendanceSrv" method="post">
		<b>Date</b>&nbsp;&nbsp;
		<input type="date" name="attnDate" value="<%=s%>"><br/><br/>
		<table>
		<tr>
		<th>Student Roll</th><th>Student Name</th><th>Attendance</th>
		</tr>
		<%
		for(StudentPersonalDetailsBean spb:spList){
		%>
		<tr>
		<td><%=spb.getStudRoll() %></td><td><%=spb.getStudName() %></td><td><input type="checkbox" name="studAttn" value="<%=spb.getStudRoll()%>"></td>
		</tr>
		<%
		}
		%>
		</table><br/><br/>
		<input type="submit" value="Mark Attendance">
	</form>
  </center>
  <br/><br/>
		
</div>

<style>
table{
display:inline-table;
background-color:#333333;
width:75%;
border-color:#999;

}

th{
font-size:18px;font-weight:normal;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#999;color:#fff;background-color:#004373;padding:10px;
}

tr:nth-child(odd) {background-color: #f2f2f2;padding:20px;}
tr:nth-child(even) {background-color: #D2E4FC;padding:20px;}
</style>

  
   
   
  
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
