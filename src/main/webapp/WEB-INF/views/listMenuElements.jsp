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
    width: 950px;
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



<h1 align="center">LISTA ELEMENTI MENU ADMIN</h1>
<br>
<br>
<table style="border:1px solid #000000" class="center"> 
   <tr><td colspan="7"><a href="insertNewMenuElement" style=";font-size: 150%;">Add New Element</a></td></tr>  
</table>  
<br>
<br>

<br>
<br>
 <!-- <div style="width:800px;height:300px;overflow-y: scroll; border:1px solid black;scrollbar-base-color: #000000;
    scrollbar-arrow-color: #CCCCCC;scrollbar-track-color: #CCCCCC;" class="center"> -->
    <div id="BARRA" class="center">
<table width="100%" style="border:1px solid #000000" class="table">
				<tr style="background: grey;">
					<th style="border:1px solid #000000">ID Link</th>
					<th style="border:1px solid #000000">Link Name</th>
					<th style="border:1px solid #000000">Link</th>
					<th style="border:1px solid #000000">Img Link</th>
					<th style="border:1px solid #000000">Target</th>
					<th style="border:1px solid #000000">Parent Id</th>
					<th style="border:1px solid #000000">Sub-Parent Id</th>
					<th style="border:1px solid #000000">Modifica Info</td> 
					<th style="border:1px solid #000000">Delete</th>
				</tr>

   
   <c:forEach var="menuElement" items="${menuElementsList}">  <!-- varStatus="status" -->
   			
   <tr style="border:1px solid #000000">     
      
    
     <!--<td>${status.index + 1}</td> 
     
     style="border:1px solid #000000"
      --> 
     <td>${menuElement.link_id}</td>  
     <td>${menuElement.link_name}</td>  
     <td>${menuElement.link}</td>  
     <td>${menuElement.img_link}</td>  
     <td>${menuElement.target}</td>
     <td>${menuElement.parent_id}</td>
     <td>${menuElement.subParent_id}</td>
     <td style="border:1px solid #000000; background: #036;"><a	href="updateLinkInfo?link_id=${menuElement.link_id}&link_name=${menuElement.link_name}&link=${menuElement.link}&img_link=${menuElement.img_link}&target=${menuElement.target}&parent_id=${menuElement.parent_id}&subParent_id=${menuElement.subParent_id}">Modifica Info</a></td>
     <td style="border:1px solid #000000; background: #036;"><a href="deleteMenuElement?link_id=${menuElement.link_id}&parent_id=${menuElement.parent_id}&subParent_id=${menuElement.subParent_id}">Delete</a></td> 
     
   <!--   <td style="border:1px solid #000000; background: #036;"><a	href="updateMenuElementInfo?id=${menuElement.link_id}&linkname=${menuElement.link_name}&link=${menuElement.link}&imglink=${menuElement.img_link}&target=${menuElement.target}&parentid=${menuElement.parent_id}">Modifica Info</a></td> 
     
     <td style="border:1px solid #000000; background: #036;"><a href="deleteMenuElement?id=${menuElement.link_id}">Delete</a></td>  
   --> 
   
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
 
 ${messageScript}

</body>  
</html>  
<br>
<br>

 </tiles:putAttribute>
</tiles:insertDefinition>