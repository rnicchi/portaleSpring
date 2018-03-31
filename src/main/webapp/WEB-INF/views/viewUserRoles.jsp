<%@ page contentType="text/html;charset=UTF-8" language="java" %>  
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<!-- < % @ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  -->
 
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
    width: 700px;
    height: 110px;
    padding: 5px;
    border: Solid 1px #000000;
    scrollbar-base-color: #036;
    scrollbar-arrow-color: #CCCCCC;
    scrollbar-track-color: #CCCCCC;
}

   #BARRA2
{
    overflow: Auto;
    color: black;
    width: 500px;
    height: 110px;
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
<h2>LISTA RUOLI UTENTE: ${username} - ID: ${userId}</h2> 
<br>


  <c:choose>
 <c:when test="${not empty userRolesList}">
 <div id="BARRA" class="center">
 <table border="1" style="border:1px solid #000000; width: 700px;" class="table">  
   <tr style="background: grey;"> 
    <td class="heading" style="border:1px solid #000000">Role Id</td>  
    <td class="heading" style="border:1px solid #000000">Role Name</td>  
    <td class="heading" style="border:1px solid #000000">Role Description</td>  
    <td class="heading" style="border:1px solid #000000">Vedi Permessi</td>  
    <td class="heading" style="border:1px solid #000000">Modifica Ruoli</td>  
   </tr>  
   <c:forEach var="role" items="${userRolesList}" varStatus="status">
   
       
    <tr>  
   <td style="border:1px solid #000000">${role.role_id}</td>
   <td style="border:1px solid #000000">${role.role_name}</td>
   <td style="border:1px solid #000000">${role.role_desc}</td>
    
    
      <td style="border:1px solid #000000; background: #036;"><a href="viewRolesPermissions?id=${userId}&username=${username}&role_id=${role.role_id}">Vedi Permessi</a></td>
     <td style="border:1px solid #000000; background: #036;"><a href="modifyUserRoles?id=${userId}&username=${username}">Modifica Ruoli</a></td>
    
    </tr>  
   </c:forEach>  
   
  
  </table>  
 </div>
  
  <br>
   <br>
    <br>
   
   <h2>LISTA PERMESSI UTENTE : ${username}</h2> 
   
<br>
<div id="BARRA2" class="center">
 <table border="1" style="border:1px solid #000000; width: 500px;" class="table">  
   <tr style="background: grey;">  
    <td class="heading" style="border:1px solid #000000">Permission Id</td>  
    <td class="heading" style="border:1px solid #000000">Permission Name</td>  
    <td class="heading" style="border:1px solid #000000">Permission Description</td>  
   </tr> 
   
   <c:forEach var="permission" items="${userRolesPermissionsList}">  
   
    <tr>  
        <td style="border:1px solid #000000"> ${permission.permission_id}</td>
        <td style="border:1px solid #000000"> ${permission.permission_name}</td>
        <td style="border:1px solid #000000"> ${permission.permission_desc}</td>
     
     
   </c:forEach>  
    
  </table>  
  </div>
  
   </c:when>
 
 <c:otherwise>  
  
  
  <table style="border:1px solid #000000" class="center" >
  <tr>
  <td><a href="modifyUserRoles?id=${userId}&username=${username}">Associa Ruoli</a></td>
 </tr></table>
    </c:otherwise>  
 
 </c:choose>  
 
 
</body>  
</html>  
<br>
<br>
<!--  
<table style="border:1px solid #000000" class="center">
<tr><td>
<a href="<c:url value="/j_spring_security_logout" />" > Logout</a>
</td></tr>
</table>
-->
<!--  <strong>logout.jsp</strong> -->
  </tiles:putAttribute>
</tiles:insertDefinition>  