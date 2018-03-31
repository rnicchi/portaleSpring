<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <tiles:insertDefinition name="defaultTemplateNoMenu">
    <tiles:putAttribute name="body">
    
		<title>Login Page</title>
<style>
.errorblock {
color: #0B0B61;
background-color: #CEE3F6;
border: 3px solid #A9BCF5;
padding: 8px;
margin: 16px;
width: 500;
margin-left:auto; 
margin-right:auto;
}

table.center {
    margin-left:auto; 
    margin-right:auto;
  }
  
  input.color
  {
  	background-color: #CEE3F6;
  
  }
  
</style>
 

</head>
<body onload='document.f.j_username.focus();'>
<br>
<br>
<h3>Login with Username and Password</h3>
<br>
<c:if test="${not empty error}">
<div class="errorblock">
Login error : Please try again.<br />Root Cause:
${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
</div>
</c:if>

<form  name='f' action="<c:url value='j_spring_security_check' />"
method='POST'>

<table align="center" style="border:1px solid #000000" class="center">
<tr align="center">
<td>User:</td>
<td style="border:1px solid #000000"><input type='text' name='j_username' value='' class="color">
</td>
</tr>
<tr align="center">
<td>Password:</td>
<td style="border:1px solid #000000"><input type='password' name='j_password' class="color"/>
</td>
</tr>
<tr align="center">
<td colspan='2'><input name="submit" type="submit" value="Login" style="border:1px solid #000000" />
<input name="reset" type="reset" value="Reset" style="border:1px solid #000000"/>
</td>
</tr>
</table>

</form>

${messageScript}
</body>
	
	
       
    </tiles:putAttribute>
</tiles:insertDefinition>