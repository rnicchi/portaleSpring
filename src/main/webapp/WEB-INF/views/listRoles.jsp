<%@ page contentType="text/html;charset=UTF-8" language="java" %>  
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 
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
  
  .table tr:nth-child(odd){
  	background: #A4D1FF;
  }
  
  .table tr:nth-child(even){
  	background: #EAF4FF;
  }
    
   div.center {
    margin-left:auto; 
    margin-right:auto;
  }
  
    #BARRA
{
    overflow: Auto;
    color: black;
    width: 800px;
    height: 300px;
    padding: 5px;
    border: Solid 1px #000000;
    scrollbar-base-color: #036;
    scrollbar-arrow-color: #CCCCCC;
    scrollbar-track-color: #CCCCCC;
}
  
</style>

</head>  
<body>
<br>
<br>
<h1>LISTA RUOLI</h1> 
<br>
<br>

<table style="border:1px solid #000000" class="center"> 
   <tr><td colspan="7"><a href="insertNewRole" style=";font-size: 150%;">Add New Role</a></td></tr>  
</table>  
<br>
<br>

<form action="filterPermission" method="post">
<table style="border:1px solid #000000" class="center">
<tr style="background: grey;">  

<tr style="background: grey;"> 
					<th style="border:1px solid #000000">Nome Permesso</td> 
					<th style="border:1px solid #000000">Id Permesso</td>
					<th style="border:1px solid #000000">Filtra</td> 
					
</tr>
<tr>
	<td style="border:1px solid #000000">
		<select name="nomePermesso" id="nomePermesso" style="width: 100%">
			<option selected="selected" value=""></option>
			<c:forEach var="permission" items="${permissionsList}">
            		<option value="${permission.permission_name}" >${permission.permission_name}</option>
            </c:forEach>
		</select>
	</td>
	<td style="border:1px solid #000000">
		<select name="idPermesso" id="idPermesso" style="width: 100%">
			<option selected="selected" value=""></option>
			<c:forEach var="permission" items="${permissionsList}">
            		<option value="${permission.permission_id}">${permission.permission_id}</option>
            </c:forEach>
		</select>
	</td>
	
	<td colspan="2" align="center" style="border:1px solid #000000"><input type="submit" value="Filter" style="width: 100%"></td>
</tr>
</table>
</form>

<br>
<br>
<div id="BARRA" class="center">
 <table border="1" width="100%" style="border:1px solid #000000" class="table">  
   
   
   <tr style="background: grey;"> 


					<th style="border:1px solid #000000">Role Id</td>  
					<th style="border:1px solid #000000">Role Name</td> 
					<th style="border:1px solid #000000">Role Description</td>
					<th style="border:1px solid #000000">Vedi Permessi</td> 
					<th style="border:1px solid #000000">Modifica Info</td>  
					<th style="border:1px solid #000000">Delete</td>  
					
</tr>
   
   
   <c:forEach var="role" items="${rolesList}">  
    <tr>  
    
     								<!-- ${status.index + 1}  -->
     <td style="border:1px solid #000000">${role.role_id}</td>  
     <td style="border:1px solid #000000">${role.role_name}</td>  
     <td style="border:1px solid #000000">${role.role_desc}</td>  
      <td style="border:1px solid #000000; background: #036;"><a href="viewPermissions?id=${role.role_id}&rolename=${role.role_name}">Vedi Permessi</a></td>
      <td style="border:1px solid #000000; background: #036;"><a	href="updateRoleInfo?id=${role.role_id}&rolename=${role.role_name}&roledesc=${role.role_desc}">Modifica Info</a></td>
      <td style="border:1px solid #000000; background: #036;"><a href="deleteRole?id=${role.role_id}">Delete</a></td>
    </tr>  
    
    
   </c:forEach>  
  
  </table>  
 </div>
 ${messageScript}
 
</body>  
</html>  
<br>
<br>
 <!--  
<table style="border:1px solid #000000" class="center">
<tr>
<td><a href="<c:url value="/j_spring_security_logout" />" >Logout</a>
</td>
</tr>
</table>
-->
<br>
<br>
 </tiles:putAttribute>
</tiles:insertDefinition>