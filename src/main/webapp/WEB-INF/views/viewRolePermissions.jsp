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
<h2>LISTA PERMESSI ASSOCIATI AL RUOLO ${rolename} - ID: ${roleId}</h2> 
<br>
  <c:choose>
 <c:when test="${not empty rolePermissionsList}">
 
 <div id="BARRA" class="center">
 <table border="1" style="border:1px solid #000000;  width: 700px;" class="table">  
   <tr style="background: grey;"> 
    <td class="heading" style="border:1px solid #000000">Permission Id</td>  
    <td class="heading" style="border:1px solid #000000">Permission Name</td>  
    <td class="heading" style="border:1px solid #000000">Permission Description</td>  
    <td class="heading" style="border:1px solid #000000">Modifica Permessi</td>  
   </tr>  
   <c:forEach var="permission" items="${rolePermissionsList}" varStatus="status">
   
       
    <tr>  
   <td style="border:1px solid #000000">${permission.permission_id}</td>
   <td style="border:1px solid #000000">${permission.permission_name}</td>
   <td style="border:1px solid #000000">${permission.permission_desc}</td>
       
     <td style="border:1px solid #000000; background: #036;"><a href="modifyRolePermissions?id=${roleId}&rolename=${rolename}">Modifica Permessi</a></td> 
   
    </tr>  
   </c:forEach>  
  </table>  
  </div>
  
   </c:when>
 
 <c:otherwise>  
  
  
  <table style="border:1px solid #000000" class="center">
  <tr>
  <td><a href="modifyRolePermissions?id=${roleId}&rolename=${rolename}">Associa Permessi</a></td>
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
  </tiles:putAttribute>
</tiles:insertDefinition>  