<%@ page contentType="text/html;charset=UTF-8" language="java" %> 
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<link href="nivo-slider.css" type="text/css" rel="stylesheet"/>
<link href="style.css" type="text/css" rel="stylesheet"/>

 
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
<html>  
<head>  
    <title></title> 
    
 <style>  
 
    table.center {
    margin-left:auto; 
    margin-right:auto;
  } 
  
 </style>
  
  <!-- 
   <script type="text/javascript">
$(function(){
    $('#show1 img:gt(0)').hide();
    setInterval(function(){$('#show1 :first-child').fadeOut().next('img').fadeIn().end().appendTo('#show1');}, 5000);
});
</script>

-->
 
</head>  
<body> 

<br>
<h1>"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt 
<br>ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco 
<br>laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate 
<br>velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt 
<br>in culpa qui officia deserunt mollit anim id est laborum."</h1> 

<div id="slider" class="nivoSlider">
	<img src="resource/images/imgAboutUs850_369.jpg" alt=""/> 
	<img src="resource/images/imgAboutUs2_850_369.jpg" alt="" title="Possiamo inserire una didascalia nel title" />
	<img src="resource/images/imgAboutUs850_369.jpg" alt=""/> 
	<img src="resource/images/imgAboutUs2_850_369.jpg" alt="" title="Possiamo inserire una didascalia nel title" />
</div>


<!--  
<div id="htmlcaption" class="nivo-html-caption">
<h1 style="color: white">Questa è una didascalia in HTML con un <a href="http://www.html.it">link</a>.</h1>
</div>

-->
 
 
<!--  <img src="resource/images/imgAboutUs.jpg" alt="aboutUs" width="850" height="300"/> -->
<!--  
<div id="slide">
<div id="show1"  class="fadein" style="width:850px; height:300px;">
  <img src="resource/images/imgAboutUs.jpg" width="850" height="300" /> 
  <img src="resource/images/imgAboutUs2.jpg" width="850" height="300" /> 
</div>

</div>
-->

 
<script type="text/javascript" src="resource/js/jquery-1.4.3.min.js"></script>
<script type="text/javascript" src="resource/js/jquery.nivo.slider.js"></script>
<script type="text/javascript" src="resource/js/jquery.nivo.slider.pack.js"></script>
<script type="text/javascript">
$(window).load(function() {
  $('#slider').nivoSlider({
	  effect:'fade',
	  pauseTime: 5000
	  });
});
</script>
 
 
</body> </html>
 </tiles:putAttribute>
</tiles:insertDefinition>