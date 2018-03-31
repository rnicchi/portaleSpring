<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ page import="jrn.dao.entities.Permissions"%>
<%@ page import="java.util.*"%>
 
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
    width: 680px;
    height: 400px;
    padding: 5px;
    border: Solid 1px #000000;
    scrollbar-base-color: #036;
    scrollbar-arrow-color: #CCCCCC;
    scrollbar-track-color: #CCCCCC;
}
  
</style>

</head>  
<body>  

<h2>MODIFICHIAMO I VARI PERMESSI PER OGNI RUOLO ${roleId} chiamato ${rolename}</h2> 
<br>
<form action="saveAssRolePermissions?id=${roleId}&rolename=${rolename}" method="post">
<div id="BARRA" class="center">
<table border="1" style="border:1px solid #000000" class="table">  
	
			<tr style="background: grey;">
				<th style="border:1px solid #000000">Permission Id</th>
				<th style="border:1px solid #000000">Permission Name</th>
				<th style="border:1px solid #000000">Permission Description</th>
				<th style="border:1px solid #000000">Seleziona</th>
			</tr>
		
		
		
	<c:forEach var="p" items="${listaPermessi}">			
			
		
		<tr>
						
			<td width="160" align="center" style="border:1px solid #000000">${p.permission_id}</td>
			<td width="160" align="center" style="border:1px solid #000000">${p.permission_name}</td>
			<td width="160" align="center" style="border:1px solid #000000">${p.permission_desc}</td>
				
			
		<c:set var="i" value="${i}"/> 
			
			<c:choose>
			
				<c:when test="${i < fn:length(listaIdPermessi)}">
				
					<c:choose>
					<c:when test="${listaIdPermessi[i] == p.permission_id}">
				
				
			
			<td width="160" align="center" style="border:1px solid #000000">
				<input type="checkbox" checked="checked" name="elementoSelezionato" value="${p.permission_id}">
			</td>
		
		</tr>
		
			<c:set var="i" value="${i + 1}"/>  
					</c:when> 
				<c:otherwise>
		
						 
			<td width="160" align="center" style="border:1px solid #000000">  
				<input type="checkbox" name="elementoSelezionato" value="${p.permission_id}">
			</td>
			</tr>
				
	
				</c:otherwise>
				</c:choose>
				
				</c:when>
				<c:otherwise>
	
			<td width="160" align="center" style="border:1px solid #000000">
				<input type="checkbox"   name="elementoSelezionato" value="${p.permission_id}">
			</td>
		
		</tr>
			</c:otherwise>
			</c:choose>
			</c:forEach>
			
	
	</table>
	</div>
	<tr>
	    
	    <br>
		<table style="border:1px solid #000000" class="center">
			<tr>
				<td><input type="submit" name="associa" value="Save"></center></td>
			</tr>
		</table>
		<br>
		</tr>
		</form>


</body>  
</html>  
<br>
<br>
<!-- 
<table style="border:1px solid #000000" class="center">
<tr>
<td><a href="<c:url value="/j_spring_security_logout" />" > Logout</a></td>
</tr>
</table>
 -->
</tiles:putAttribute>
</tiles:insertDefinition>  