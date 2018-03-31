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
<h1>LISTA PERMESSI</h1> 
<br>
<br>
<table style="border:1px solid #000000" class="center"> 
   <tr><td colspan="7"><a href="insertNewPermission" style=";font-size: 150%;">Add New Permission</a></td></tr>  
</table> 
<br>
<br>
<div id="BARRA" class="center">
 <table width="100%" border="1" style="border:1px solid #000000" class="table">  
 
 <tr style="background: grey;"> 


					<th style="border:1px solid #000000">Permission Id</td>  
					<th style="border:1px solid #000000">Permission Name</td>  
					<th style="border:1px solid #000000">Permission Description</td> 
					<th style="border:1px solid #000000">Modifica Info</td>  
					<th style="border:1px solid #000000">Delete</td>  
					
</tr>
  
   <c:forEach var="permission" items="${permissionsList}">  
    <tr>  
     <td style="border:1px solid #000000">${permission.permission_id}</td>  
     <td style="border:1px solid #000000">${permission.permission_name}</td>  
     <td style="border:1px solid #000000">${permission.permission_desc}</td>  
     <td style="border:1px solid #000000; background: #036;"><a href="updatePermissionInfo?id=${permission.permission_id}&permissionname=${permission.permission_name}&permissiondesc=${permission.permission_desc}">Modifica Info</a></td>
     <td style="border:1px solid #000000; background: #036;"><a href="deletePermission?id=${permission.permission_id}">Delete</a></td>
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
<table border="1" style="border:1px solid #000000" class="center">  
<tr>
<td><a href="<c:url value="/j_spring_security_logout" />" >Logout</a></td>
</tr>  
</table>
-->
 </tiles:putAttribute>
</tiles:insertDefinition>