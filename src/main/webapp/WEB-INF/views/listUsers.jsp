<%@ page contentType="text/html;charset=UTF-8" language="java" %>  
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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

<h1 align="center">LISTA UTENTI</h1>
<br>
<br>
<table style="border:1px solid #000000" class="center"> 
   <tr><td colspan="7"><a href="insertNewUser" style=";font-size: 150%;">Add New User</a></td></tr>  
</table>  
<br>
<br>
<form action="filterUser" method="post">
<table style="border:1px solid #000000" class="center">
<tr style="background: grey;"> 


					<th style="border:1px solid #000000">Nome Ruolo</td>
					<th style="border:1px solid #000000">Username</th>
					<th style="border:1px solid #000000">Abilitazione</td>
					<th style="border:1px solid #000000">Email</th>
					<th style="border:1px solid #000000">Tel</th>
					<th style="border:1px solid #000000">Filtra</td> 
					
</tr>
<tr>
	<td style="border:1px solid #000000">
		<select name="roleName" id="roleName">
			<option selected="selected" value=""></option>
			<c:forEach var="role" items="${rolesList}">
            		<option value="${role.role_name}" >${role.role_name}</option>
            </c:forEach>
		</select>
	</td>
	<td style="border:1px solid #000000">
		<select name="userName" id="userName" >
			<option selected="selected" value=""></option>
			<c:forEach var="user" items="${userListFilter}">
            		<option value="${user.username}">${user.username}</option>
            </c:forEach>
		</select>
	</td>
	<td style="border:1px solid #000000">
		<select name="userAbilitation" id="userAbilitation" style="width: 100%">
			<option selected="selected" value=""></option>
			<option value="0">False</option>
			<option value="1">True</option>
			<!-- <c:forEach var="user" items="${userList}">
            		<option value="${user.enabled}">${user.enabled}</option>
            </c:forEach> -->
		</select>
	</td>
	<td style="border:1px solid #000000">
		<select name="userEmail" id="userEmail" >
			<option selected="selected" value=""></option>
			<c:forEach var="user" items="${userListFilter}">
            		<option value="${user.email}">${user.email}</option>
            </c:forEach>
		</select>
	</td>
	<td style="border:1px solid #000000">
		<select name="userTel" id="userTel" >
			<option selected="selected" value=""></option>
			<c:forEach var="user" items="${userListFilter}">
            		<option value="${user.tel}">${user.tel}</option>
            </c:forEach>
		</select>
	</td>
	
	<td colspan="2" align="center" style="border:1px solid #000000"><input type="submit" value="Filter" style="width: 100%"></td>
</tr>
</table>
</form>
<br>
<br>
 <!-- <div style="width:800px;height:300px;overflow-y: scroll; border:1px solid black;scrollbar-base-color: #000000;
    scrollbar-arrow-color: #CCCCCC;scrollbar-track-color: #CCCCCC;" class="center"> -->
    <div id="BARRA" class="center">
<table width="100%" style="border:1px solid #000000" class="table">
				<tr style="background: grey;">
					<th style="border:1px solid #000000">ID User</th>
					<th style="border:1px solid #000000">Username</th>
					<th style="border:1px solid #000000">Password</th>
					<th style="border:1px solid #000000">Email</th>
					<th style="border:1px solid #000000">Tel</th>
					<th style="border:1px solid #000000">Enabled</th>
					<th style="border:1px solid #000000">Vedi Ruoli</th>
					<th style="border:1px solid #000000">Edit</th>
					<th style="border:1px solid #000000">Delete</th>
				</tr>

   
   <c:forEach var="user" items="${userList}">  <!-- varStatus="status" -->
   			
   <tr style="border:1px solid #000000">  
      
    
     <!--<td>${status.index + 1}</td>  --> 
     <td style="border:1px solid #000000">${user.login_id}</td>  
     <td style="border:1px solid #000000">${user.username}</td>  
     <td style="border:1px solid #000000">${user.password}</td>  
     <td style="border:1px solid #000000">${user.email}</td>  
     <td style="border:1px solid #000000">${user.tel}</td>
     <c:choose>
     	<c:when test="${user.enabled == 1}">
     	<!-- <td style="border:1px solid #000000">${user.enabled}</td> -->
     		<td style="border:1px solid #000000">true</td>
     	</c:when>
     	<c:otherwise>
     		<td style="border:1px solid #000000">false</td>
     	</c:otherwise>
     </c:choose>
     <td style="border:1px solid #000000; background: #036;"><a href="viewRoles?id=${user.login_id}&username=${user.username}">Vedi Ruoli</a></td> 
     
     <td style="border:1px solid #000000; background: #036;"><a	href="updateUserInfo?id=${user.login_id}&username=${user.username}&password=${user.password}&email=${user.email}&tel=${user.tel}&enabled=${user.enabled}">Modifica Info</a></td> 
     
     <td style="border:1px solid #000000; background: #036;"><a href="deleteUser?id=${user.login_id}">Delete</a></td>  
    </tr>  
    
   </c:forEach>  
</table>   
</div>
<br>
<br>
<!-- 
<table style="border:1px solid #000000" class="center">
<tr align="right">
<td>
<a href="<c:url value="/j_spring_security_logout" />" > Logout</a>
</td>
</tr>
</table>  
 -->

</body>  
</html>  
<br>
<br>

 </tiles:putAttribute>
</tiles:insertDefinition>