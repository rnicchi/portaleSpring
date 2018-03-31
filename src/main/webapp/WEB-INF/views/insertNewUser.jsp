<%@page import="jrn.dao.entities.ValidationException"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>  
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 
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
<br>
<h2>INSERISCI NUOVO UTENTE</h2> 
<br>
<br>
 <form action="saveNewUser" method="post">
		<table style="border:1px solid #000000" class="center">
			  <tr><td><input type="hidden" name="login_id"/><td></tr>
			<tr>
				<td>Username:</td>
				<td style="border:1px solid #000000"><input type="text" name="username" /></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td style="border:1px solid #000000"><input type="text" name="password" /></td>
			</tr>
			<tr>
				<td>Email:</td>
				<td style="border:1px solid #000000"><input type="text" name="email" /></td>
			</tr>
			<tr>
				<td>Tel:</td>
				<td style="border:1px solid #000000"><input type="text" name="tel" /></td>
			</tr>
			<tr>
				<td>Enabled:</td>
				<!-- <td style="border:1px solid #000000"><input type="text" name="enabled"/></td>  -->
				<td><input type="checkbox" checked="checked" name="enabled" value="1"></td>
					
			</tr>
			
		</table>
		<br>
		    <table style="border:1px solid #000000" class="center">
			<tr>
				<td colspan="2" align="center"><input type="submit" value="Save"></td>
			</tr>
			</table>
			
		</form>

 ${messageScript}
		
</body>  
</html>  
<br>
<br>
<!--  
<table style="border:1px solid #000000" class="center">
<tr>
<td>
<a href="<c:url value="/j_spring_security_logout" />" > Logout</a>
</td>
</tr>
</table>
-->
<!--  <strong>logout.jsp</strong> -->
 </tiles:putAttribute>
</tiles:insertDefinition>