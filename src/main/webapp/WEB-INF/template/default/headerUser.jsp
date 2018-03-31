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
        <ul style="right :68px">
            <li><a href="#">&nbsp;<img src="resource/images/home.png" style="height: 14px; width: 14px;">&nbsp;Home&nbsp;</a>
            </li>
            <li> <a href="#">&nbsp;<img src="resource/images/info.png" style="height: 14px; width: 14px;">&nbsp;About us&nbsp;</a>
            </li>
            <li> <a href="#">&nbsp;<img src="resource/images/menu.png" style="height: 12px; width: 12px;">&nbsp;Amministrazione&nbsp;</a>
            </li>
            <li style="margin-left: 942px;"> <a href="#">&nbsp;<img src="resource/images/man.png" style="height: 14px; width: 14px;">&nbsp;User&nbsp;</a>
            </li>
            <li style="margin-left: 5px;"> <a href="#">&nbsp;<img src="resource/images/settings.png" style="height: 14px; width: 14px;">&nbsp;</a>
            </li>
           
           <!--  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
             -->
            </li>
       
        </ul>
    </div>
    <!-- Nav wrapper end -->
</div>
</div>
<!-- Nav end -->

 
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

