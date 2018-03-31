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
<h2>MODIFICA UTENTE: ${username} CON ID: ${userId}</h2> 
<br>

 <form action="updateLoggedUser?originalUsername=${username}" method="post">
 
		<table style="border:1px solid #000000" class="center">
			  <tr>
			  <td><input type="hidden" name="login_id" value="<c:out value="${userId}"/>" /><td>
			  </tr>
			<tr>
				<td>Username:</td>
				<td style="border:1px solid #000000"><input type="text" name="username" value="<c:out value="${username}"/>" /></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td style="border:1px solid #000000"><input type="text" name="password" value="<c:out value="${password}"/>"/></td>
			</tr>
			<tr>
				<td>Email:</td>
				<td style="border:1px solid #000000"><input type="text" name="email" value="<c:out value="${email}"/>"/></td>
			</tr>
			<tr>
				<td>Tel:</td>
				<td style="border:1px solid #000000"><input type="text" name="tel" value="<c:out value="${tel}"/>"/></td>
			</tr>
			<tr>
				<td>Enabled:</td>
								
				<c:choose>
					<c:when test="${enabled == 1}">
						<td><input type="checkbox" checked="checked" name="enabled" value="1"></td>
					</c:when>
					
					<c:otherwise>
						
						<td><input type="checkbox" name="enabled" value="0"></td>
						
					</c:otherwise>			
				</c:choose>
				
				
			</tr>
			
		</table>
		<br>
			<table style="border:1px solid #000000" class="center">
			<tr>
				<td colspan="2" align="center"><input type="submit" value="Update"></td>
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
<td><a href="<c:url value="/j_spring_security_logout" />" >Logout</a> </td>
</tr>
</table>
-->
 </tiles:putAttribute>
</tiles:insertDefinition>