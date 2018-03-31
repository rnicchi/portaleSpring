<%@ page contentType="text/html;charset=UTF-8" language="java" %>  
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
 
<tiles:insertDefinition name="defaultTemplate">
    <tiles:putAttribute name="body">
<html>  
<head>  
    <title></title>  
</head>  
<body>  
<h2>ADMIN PAGE</h2> 

 <h2>Benvenuto, sei entrato nella sezione dedicata agli amministratori! </h2> 
</body>  
</html>  
<br>
<br>
<a href="<c:url value="/j_spring_security_logout" />" > Logout</a>
<!--  <strong>logout.jsp</strong> -->
 </tiles:putAttribute>
</tiles:insertDefinition>