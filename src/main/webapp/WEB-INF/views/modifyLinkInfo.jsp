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
<h2>MODIFICA LINK: ${link_name} CON ID: ${link_id}  - ${target} - ${link}</h2> 
<br>
 <form action="updateLink" method="post">
 
		<table style="border:1px solid #000000" class="center">
			  <tr>
			  <td><input type="hidden" name="link_id" value="<c:out value="${link_id}"/>" /><td></tr>
			<tr>
			
				<td>Link Name:</td>
				<td style="border:1px solid #000000"><input type="text" name="link_name" value="<c:out value="${link_name}"/>" /></td>
			</tr>
			<tr>
				<td>Link:</td>
				<td style="border:1px solid #000000"><input type="text" name="link" value="${link}"/></td>
			</tr>
			<tr>
				<td>Img Link:</td>
				<td style="border:1px solid #000000">
				<select name="img_link" style="width: 100%">
					 <option selected="selected" value="${img_link}"></option>
  					 <option value="resource/images/home.png">Home</option>
  					 <option value="resource/images/info.png">Info</option>
  					 <option value="resource/images/menu.png">Menu</option>
 					 <option value="resource/images/network.png">Default</option>
 					 <option value="resource/images/man.png">User</option>
 					 <option value="resource/images/settings.png">Settings</option> 
				</select>
				</td>
			</tr>
			<tr>
				<td>Target:</td>
				<td style="border:1px solid #000000">
				<select name="target" style="width: 100%">
					 <option selected="selected" value="${target}">${target}</option>
  					 <option value="_self">self</option>
  					 <option value="_blank">blank</option>
  					 <option value="_parent">parent</option>
 					 <option value="_top">top</option>
 					 <option value="framename">framename</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>Parent Id:</td>
				<td style="border:1px solid #000000">
				<select name="parent_id" style="width: 100%">
					 <option selected="selected" value="${parent_id}">${parent_id}</option>
  					 <option value="0">no child (0)</option>
  					 <option value="1">has child (1)</option>
  					 <c:forEach var="menu" items="${menuElementsList}">
  					 	<c:if test="${menu.link_id != 1}">
  					 		<option value="${menu.link_id}">${menu.link_id}</option>
  					 	</c:if>
  					 </c:forEach>
				</select>
			</tr>
			<tr>
				<td>Sub-Parent Id:</td>
				<td style="border:1px solid #000000">
				<select name="subParent_id" style="width: 100%">
					 <option selected="selected" value="${subParent_id}">${subParent_id}</option>
  					 <option value="0">no child (0)</option>
  					 <option value="1">has child (1)</option>
  					 <c:forEach var="menu" items="${menuElementsList}">
  					 	<c:if test="${menu.link_id != 1}">
  					 		<option value="${menu.link_id}">${menu.link_id}</option>
  					 	</c:if>
  					 </c:forEach>
				</select>
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