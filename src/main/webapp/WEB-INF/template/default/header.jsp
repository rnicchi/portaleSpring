<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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

<div id="header">
<div id="nav">
    <div id="nav_wrapper">
        <ul style="right : 200px">
            <li><a href="index" target="ifra">Home</a>
            </li>
            <li> <a href="aboutUs">&nbsp;About us&nbsp; </a>
            </li>
            <li> <a href="#">Amministrazione&nbsp;</a>
                <ul>
                    <li><a href="listUsers"  target="ifra" tabindex="3">Lista Utenti</a>
                    </li>
                    <li><a href="listRoles"  target="ifra" tabindex="2">Lista Ruoli</a>
                    </li>
                    <li><a href="listPermissions"  target="ifra" tabindex="4">Lista Permessi</a>
                    </li>
                </ul>
            </li>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <li style="right : 320px"> <a href="<c:url value="/j_spring_security_logout"/>">Logout</a>
            </li>
       
        </ul>
    </div>
    <!-- Nav wrapper end -->
</div>
<!-- Nav end -->

 


<table class="color"> 
	
	<tr> 		
		<td> <h3 class="form">| Benvenuto ${loggedUser} |</h3></td>
		<!-- <td><a href="<c:url value="/j_spring_security_logout"/>" class="logout"> Logout</a></td>  -->
	    <td> <h2 class="form">ISC Kpi Management</h2></td>
	
	</tr>
	
	</table>

</div>

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

