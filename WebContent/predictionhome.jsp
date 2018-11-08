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
    background-color: #562357;
    padding: 15px;
    margin-top: 20px;
    display: inline-block;
    width: 200px;
    margin-top: 30px;
  }
  
  </style>
  
  <link rel="stylesheet" type="text/css" href="css/initsetup2.css?crc=443350757"/>
  <link rel="stylesheet" type="text/css" href="css/initsetup.css?crc=4020712053" id="pagesheet"/>
  
  <!-- Other scripts -->
  <script type="text/javascript">
   var __adobewebfontsappname__ = "muse";
</script>
  <!-- JS includes -->
  <script type="text/javascript">
   
</script>
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
    <p>Prediction</p>
   </div>
   <div class="clearfix colelem" id="u149-3"><!-- content -->
    <p>&nbsp;</p>
   </div>
   <div id="u134-wrapper">
    <div class="pinned-colelem" id="u134"><!-- custom html -->
     <center>
		<a href="predictsubjectmarks.jsp">Subject Marks Prediction<br/> (By Semester)</a>
		
	</center>

    </div>
   </div>
   <div id="u138-wrapper">
    <div class="pinned-colelem" id="u138"><!-- custom html -->
     <center>
		<a href="predictgpa.jsp">GPA Prediction</a>
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