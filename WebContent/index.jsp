<%@page import="com.bean.StreamBean"%>
<%@page import="java.util.List"%>
<%@page import="com.dao.StreamDaoImpl"%>
<%@page import="com.dao.StreamDao"%>
<%@page import="com.servlet.LoginSrv"%>
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
  <link rel="stylesheet" type="text/css" href="css/index2.css?crc=443350757"/>
  <link rel="stylesheet" type="text/css" href="css/index.css?crc=418984753" id="pagesheet"/>
  <style>
	@font-face {
    font-family: damion;
    src: url("fonts/damion.ttf");
	}
	
	@font-face {
    font-family: roboto;
    src: url("fonts/roboto.ttf");
	}
	
	<!-- My Form CSS -->
	input {
		font-size: 1em;
		margin: 0;
		padding: 0;
		-webkit-appearance: none;
	}
	
	input {
	border: none;
	height: 30px;
	outline: none;
}

	input[type="text"] {
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
	 input[type="password"] {
	background-color: #fff;
	border-top: 2px solid #2c90c6;
	border-right: 1px solid #000;
	border-bottom: 2px solid #2c90c6;
	border-left: 1px solid #000;
	border-radius: 0 0 5px 5px;
	-moz-border-radius: 0 0 5px 5px;
	-webkit-border-radius: 0 0 5px 5px;
  -o-border-radius: 0 0 5px 5px;
  -ms-border-radius: 0 0 5px 5px;
	color: #363636;
	margin-bottom: 20px;
	padding-left: 36px;
	width: 204px;
}
	
	#lgnbtn {
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
	text-align: center;
	text-transform: uppercase;
	width: 240px;
}

#lgnbtn:hover {
	background-color:#06a14e;
  box-shadow: 2px 2px 20px  #2c90c6, #fff 0 -1px 2px;
}

#rgbtn {
	background-color: #faab1f;
	border: 1px solid #faab1f;
	border-radius: 15px;
	-moz-border-radius: 15px;
	-webkit-border-radius: 15px;
  -ms-border-radius: 15px;
  -o-border-radius: 15px;
	color: #fff;
	font-weight: bold;
	line-height: 48px;
	text-align: center;
	text-transform: uppercase;
	width: 240px;
}

#rgbtn:hover {
	background-color: #c2800a;
  box-shadow: 2px 2px 20px  #2c90c6, #fff 0 -1px 2px;
}
  
  </style>
  <!-- Other scripts -->
  <script type="text/javascript">
   var __adobewebfontsappname__ = "muse";
</script>
  <!-- JS includes -->
  
   </head>
 <body style="font-family: roboto;">

  <div class="clearfix borderbox" id="page"><!-- column -->
   <div class="clearfix colelem" id="pu145-4"><!-- group -->
    <div class="clearfix grpelem" id="u145-4"><!-- content -->
     <p style="font-family:damion;">SRRA</p>
    </div>
    <div class="grpelem" id="u195"><!-- simple frame --></div>
    <div class="clearfix grpelem" id="u144-11"><!-- content -->
     <p style="font-family:damion;"><span id="u144">S</span>tudent <span id="u144-3">R</span>ecord <span id="u144-5">R</span>epository and <span id="u144-7">A</span>nalysis</p>
    </div>
   </div>
   <div id="u148-wrapper">
    <div class="clip_frame pinned-colelem" id="u148"><!-- image -->
     <img class="block" id="u148_img" src="images/picture1.png?crc=4197332190" alt="" data-image-width="93" data-image-height="102"/>
    </div>
   </div>
   <div id="u150-wrapper">
    <div class="clip_frame pinned-colelem" id="u150"><!-- image -->
     <img class="block" id="u150_img" src="images/picture2.png?crc=500682757" alt="" data-image-width="92" data-image-height="92"/>
    </div>
   </div>
   <div id="u146-wrapper">
    <div class="clip_frame pinned-colelem" id="u146"><!-- image -->
     <img class="block" id="u146_img" src="images/database-cloud-512.png?crc=6193734" alt="" data-image-width="92" data-image-height="92"/>
    </div>
   </div>
   <div id="u152-4-wrapper">
    <div class="clearfix pinned-colelem" id="u152-4"><!-- content -->
     <p>Central Repository</p>
    </div>
   </div>
   <div id="u153-4-wrapper">
    <div class="clearfix pinned-colelem" id="u153-4"><!-- content -->
     <p>Visualization</p>
    </div>
   </div>
   <div id="u154-4-wrapper">
    <div class="clearfix pinned-colelem" id="u154-4"><!-- content -->
     <p>Predictive Analysis</p>
    </div>
   </div>
   <div class="browser_width" id="u155-bw">
    <div class="shadow pinned-colelem" id="u155"><!-- simple frame --></div>
   </div>
   <div id="u199-wrapper" style="    bottom: 20px;">
    <div class="pinned-colelem" id="u199" ><!-- custom html -->
     <form action="LoginSrv" method="post">

		<center>
      
        
        <input type="text" name="<%=LoginSrv.Parameters.USERNAME %>" class="form-control" placeholder="Username or email">
         

          <br/>
          <input type="password" name="<%=LoginSrv.Parameters.PASSWORD %>" class="form-control" placeholder="Password">
        <br/>
        
          
          <input type="radio" name="<%=LoginSrv.Parameters.MODE %>" value="0" checked="checked"><span style="color: white;">Teacher</span>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <input type="radio" name="<%=LoginSrv.Parameters.MODE %>" value="1" ><span style="color: white;">Administrator</span>
       
        
        <%StreamDao streamDao = new StreamDaoImpl();
        List<StreamBean> streamBeans = streamDao.fetchAllStream();%>
        
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        
        <select name="<%=LoginSrv.Parameters.STREAM%>">
			<option value="0">Select Stream</option>
		 <%
		for(StreamBean s:streamBeans){
		%>
			<option value="<%=s.getStrID()%>"><%=s.getStrID()%>.<%=s.getStrName() %></option>
		<%
		}
		%> 
		
		</select>
		
		<br/><br/>

        <button type="submit" id="lgnbtn">Login</button>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        
        <a href="registerteacher.jsp"><button id="rgbtn" formaction="registerteacher.jsp">Register as a Teacher</button></a>
        <br/>
        
        <%
       Object msgobj=session.getAttribute(LoginSrv.Parameters.MESSAGE);
       if(msgobj!=null){
    	   String msg=(String)msgobj;
       %>
       <span style="color: grey;font-size: 16px;font-weight: bold;"><%=msg %> </span><br/>
	   <%
			session.removeAttribute(LoginSrv.Parameters.MESSAGE);
       }
       %>
        
        
        </center>
       </form>
       
    </div>
   </div>
   <div class="verticalspacer" data-offset-top="179" data-content-above-spacer="178" data-content-below-spacer="422"></div>
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
Muse.Utils.fullPage('#page');/* 100% height page */
Muse.Utils.showWidgetsWhenReady();/* body */
Muse.Utils.transformMarkupToFixBrowserProblems();/* body */
}catch(b){if(b&&"function"==typeof b.notify?b.notify():Muse.Assert.fail("Error calling selector function: "+b),false)throw b;}})})};

</script>
  <!-- RequireJS script -->
  <script src="scripts/require.js?crc=244322403" type="text/javascript" async data-main="scripts/museconfig.js?crc=168988563" onload="if (requirejs) requirejs.onError = function(requireType, requireModule) { if (requireType && requireType.toString && requireType.toString().indexOf && 0 <= requireType.toString().indexOf('#scripterror')) window.Muse.assets.check(); }" onerror="window.Muse.assets.check();"></script>
   </body>
</html>
    
	
