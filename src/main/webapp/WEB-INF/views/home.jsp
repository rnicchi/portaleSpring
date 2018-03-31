<%@ page contentType="text/html;charset=UTF-8" language="java" %> 
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>


 
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
 
</head>  
<body> 
<br>

<!--<h1><sec:authentication property="name"/></h1> -->
<h1>Benvenuto ${loggedUser}, sei loggato come:</h1>
<br>
<h1> <sec:authentication property="authorities"/></h1>

<br>
<br>
<!--  
<table style="border:1px solid #000000" class="center">
<td>
<a href="<c:url value="/j_spring_security_logout" />" > Logout</a>
</td>
</table>
-->

<img src="resource/images/imageISC.gif" alt="ISC" width="200" height="200"/>
<br>
<br>
<br>
<br>
<h1>Questa è la Home del sito!</h1> 

 
</body> </html>
 </tiles:putAttribute>
</tiles:insertDefinition>