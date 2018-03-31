<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<link href="style.css" type="text/css" rel="stylesheet"/>

<style>

table.color {
	color : #ffffff;
	bottom: 5;
    
  }
  
  h2.form
  {
  	color : #CCC;
  	position: absolute;
  	right : 5;
  	bottom: 0;
  	padding: 2;
  }
  
  h3.form
  {
  	color : #CCC;
  	position: absolute;
  	right : 470;
  	bottom: 7;
  	padding: 2;
  }
  
  a.logout{
  	color : #ffffff;
  	background-color: #036;
  	position: absolute;
  	right : 390;
  	bottom: 6;  
  }
  
   a.logout:active{
  color : #fff;
background-color : #369;
border : 1px solid #fff;
}
  
  
</style>
<sec:authentication property="authorities" var="roles" scope="page"/>
            <c:choose>
            <c:when test="${fn:contains(roles, 'ROLE_ADMIN')}">
            
<div id="header">
<div id="nav">
    <div id="nav_wrapper">
        <ul style="right :70px">
            <li><a href="index" target="ifra">&nbsp;<img src="resource/images/home.png" style="height: 14px; width: 14px;">&nbsp;Home&nbsp;</a>
            </li>
            <li> <a href="aboutUs">&nbsp;<img src="resource/images/info.png" style="height: 14px; width: 14px;">&nbsp;About us&nbsp; </a>
            </li>
            <li> <a>&nbsp;<img src="resource/images/menu.png" style="height: 12px; width: 12px;">&nbsp;Amministrazione&nbsp;</a>
           		<ul>
                    <li><a href="listUsers"  target="ifra" tabindex="3" style="width: 144px;">Lista Utenti</a>
                    </li>
                    <hr width="100%" size=3 color="grey">               
                    <li><a href="listRoles"  target="ifra" tabindex="2" style="width: 144px;">Lista Ruoli</a>
                    </li>
                    <hr width="100%" size=3 color="grey">
                    <li><a href="listPermissions"  target="ifra" tabindex="4" style="width: 144px;">Lista Permessi</a>
                    </li>
                    <hr width="100%" size=3 color="grey">
                    <li><a href="listMenuElements"  target="ifra" tabindex="4" style="width: 144px;">Lista Link Admin</a>
                    </li>
                    <hr width="100%" size=3 color="grey">
                    <li><a href="listMenuUserElements"  target="ifra" tabindex="4" style="width: 144px;">Lista Link User</a>
                    </li>
                </ul>           
            </li>
            <li style="margin-left: 900px;"><a href=#">&nbsp;<img src="resource/images/man.png" style="height: 14px; width: 14px;">&nbsp;<sec:authentication property="name"/>&nbsp;</a>
            <ul style="margin-left: -160px; width: 200px;">
                    <li><a style="width: 200px;">Username: <sec:authentication property="name"/></a> </li>
                    <hr width="100%" size=3 color="grey">
                   	<li><a style="width: 200px;">Tel: ${loggedUserTel}</a></li>
                   	<hr width="100%" size=3 color="grey">
                    <li><a style="width: 200px;">Mail: ${loggedUserEmail}</a></li>
                    <hr width="100%" size=3 color="grey">
                    <li><a style="width: 200px;">Ruolo: <br><sec:authentication property="authorities"/></a></li>
                    
                </ul>
            
            </li>
            <li style="margin-left: 10px;"> <a href="#">&nbsp;<img src="resource/images/settings.png" style="height: 14px; width: 14px;">&nbsp;</a>
            <ul style="margin-left: -138px;">
                                        
                    <li><a href="updateLoggedUserInfo" style="width: 150px;">Modifica dati utente</a></li>
                    <hr width="100%" size=3 color="grey">
                    <li><a href="<c:url value="/j_spring_security_logout"/>" style="width: 150px;">Logout</a>
                    
                </ul>
            </li>
           
           <!--  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
             -->
            
       
        </ul>
    </div>
    <!-- Nav wrapper end -->
</div>
</div>
<!-- Nav end -->
</c:when>
<c:otherwise>
     <div id="header">
<div id="nav">
    <div id="nav_wrapper">
        <ul style="right :69px">
            <li><a href="index" target="ifra">&nbsp;<img src="resource/images/home.png" style="height: 14px; width: 14px;">&nbsp;Home&nbsp;</a>
            </li>
            <li> <a href="aboutUs">&nbsp;<img src="resource/images/info.png" style="height: 14px; width: 14px;">&nbsp;About us&nbsp; </a>
            </li>
            <li style="margin-left: 1050px;"><a href=#">&nbsp;<img src="resource/images/man.png" style="height: 14px; width: 14px;">&nbsp;<sec:authentication property="name"/>&nbsp;</a>
            <ul style="margin-left: -175px; width: 200px;">
                    <li><a style="width: 200px;">Username: <sec:authentication property="name"/></a> </li>
                    <hr width="100%" size=3 color="grey">
                   	<li><a style="width: 200px;">Tel: ${loggedUserTel}</a></li>
                   	<hr width="100%" size=3 color="grey">
                    <li><a style="width: 200px;">Mail: ${loggedUserEmail}</a></li>
                    <hr width="100%" size=3 color="grey">
                    <li><a style="width: 200px;">Ruolo: <br><sec:authentication property="authorities"/></a></li>
                    
                </ul>
            
            </li>
            <li style="margin-left: 10px;"> <a href="#">&nbsp;<img src="resource/images/settings.png" style="height: 14px; width: 14px;">&nbsp;</a>
            <ul style="margin-left: -138px;">
                                      	
                    <li><a href="updateLoggedUserInfo" style="width: 150px;">Modifica dati utente</a></li>
                    <hr width="100%" size=3 color="grey">
                    <li><a href="<c:url value="/j_spring_security_logout"/>" style="width: 150px;">Logout</a>
                    
                </ul>
            </li>
           
           <!--  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
             -->
            
       
        </ul>
    </div>
    <!-- Nav wrapper end -->
</div>
</div>       
            
</c:otherwise>
</c:choose>

 
<!--  

<table class="color"> 
	
	<tr> 		
		<td> <h3 class="form">| Benvenuto ${loggedUser} |</h3></td>
		
	    <td> <h2 class="form">ISC Kpi Management</h2></td>
	
	</tr>
	
	</table>

</div>
-->

 <!-- 
<div id="header">
	

	<table class="color" cellpadding="3"> 
	
	<tr> 
	
		<td style="left : 100px"><a href=""> Menu 1 </a>|</td>
	
		<td style="left : 100px"><a href=""> Menu 2 </a>|</td>
		<td style="left : 100px"><a href=""> Menu 3 </a>|</td>
		
		<td> <h3 class="form">| Benvenuto ${loggedUser} |</h3></td>
		<td><a href="<c:url value="/j_spring_security_logout"/>" class="logout"> Logout</a></td>
	    <td> <h2 class="form">ISC Kpi Management</h2></td>
	
	</tr>
	
	</table>
		
	
</div>
-->
</html>