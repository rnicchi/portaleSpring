<%@ page contentType="text/html;charset=UTF-8" language="java" %>  
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!--   < %@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  -->
 
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
  
  table tr td
  {
  	font-size: small;
  
  }
  
</style>

</head>  
<body>  
<h2>MODIFICA RUOLO: ${rolename} CON ID: ${roleId}</h2> 
<br>
 <form action="updateRole" method="post">
 
		<table style="border:1px solid #000000" class="center">
			  <tr>
			  <td><input type="hidden" name="role_id" value="<c:out value="${roleId}"/>" /><td></tr>
			<tr>
			
				<td>Role Name:</td>
				<td style="border:1px solid #000000"><input type="text" name="role_name" value="<c:out value="${roleName}"/>" /></td>
			</tr>
			<tr>
				<td>Role Desc:</td>
				<td style="border:1px solid #000000"><input type="text" name="role_desc" value="<c:out value="${roleDesc}"/>"/></td>
			</tr>
			
		</table>
		<br>
		<table style="border:1px solid #000000" class="center">
			<tr>
				<td colspan="2" align="center"><input type="submit" value="Update"></td>
			</tr>
		</table>
		</form>
</body>  
</html>  
<br>
<br>
<!--  
<table style="border:1px solid #000000" class="center">
<tr>
<td><a href="<c:url value="/j_spring_security_logout" />" >Logout</a><td>
</tr>
</table>
-->
 </tiles:putAttribute>
</tiles:insertDefinition>