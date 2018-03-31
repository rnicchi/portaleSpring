<!--  
<br>
<br>
<div id="menu_left">
<ul>
<li><a href=""  target="ifra" tabindex="1">Amministrazione</a></li>
<li><a href="listUsers"  target="ifra" tabindex="3">Lista Utenti</a></li>
<li><a href="listRoles"  target="ifra" tabindex="2">Lista Ruoli</a></li>
<li><a href="listPermissions"  target="ifra" tabindex="4">Lista Permessi</a></li>
</ul>
</div>

-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>

<script type="text/javascript" src="resource/js/slideout.js"></script>
<link rel="stylesheet" type="text/css" href="resource/css/slideout.css" />


<style type="text/css">
    td
    {
        font: Normal 10px Verdana;
    }
    #MENU
    {
        position: Absolute;
        left: -150px;
        margin-top: -1px;
    }
    a
    {
        color: #FFFFFF;
    }
</style>

<script> 
function apri(url) { 
    newin = window.open(url,'titolo','scrollbars=no,resizable=yes, width=200,height=200,status=no,location=no,toolbar=no');
} 
</script>

<script type="text/javascript">
    function on()
    {
        document.getElementById("MENU").style.left = "-2px";
    }
    function off()
    {
        document.getElementById("MENU").style.left = "-150px";
    }
</script>



</head>

<body>


<sec:authentication property="authorities" var="roles" scope="page"/>
            <c:choose>
            <c:when test="${fn:contains(roles, 'ROLE_ADMIN')}">

<div id="MENU" onclick="off()" onmouseover="on();">

<div id="dhtmlgoodies_menu">

<ul>
            <li style="width: 145"><a style="height:28px; width: 185;text-align: right;">&nbsp;MENU&nbsp;</a>
            </li>
            <li style="width: 145"><a href="index" style="width: 139; padding-top: 7;">&nbsp;<img src="resource/images/home.png" style="height: 14px; width: 14px;"/>&nbsp;Home&nbsp;</a>
            </li>
            <li style="width: 145"> <a href="aboutUs" style="width: 139; padding-top: 7;">&nbsp;<img src="resource/images/info.png" style="height: 14px; width: 14px;">&nbsp;About us&nbsp; </a>
            </li>
            
            <li style="width: 145"> <a style="width: 139; padding-top: 7;">&nbsp;<img src="resource/images/menu.png" style="height: 12px; width: 12px;">&nbsp;Amministrazione&nbsp;</a>
           		
           		
           		<ul>
                    <li style="width: 145"><a href="listUsers" style="width: 144px;" >Lista Utenti</a></li>
                          <hr width="100%" size=3 color="grey">        
                    <li style="width: 145"><a href="listRoles" style="width: 144px;" >Lista Ruoli</a></li>
                    <hr width="100%" size=3 color="grey"> 
                    <li style="width: 145"><a href="listPermissions" style="width: 144px;" >Lista Permessi</a></li>
                    <hr width="100%" size=3 color="grey"> 
                    <li style="width: 145"><a href="listMenuElements" style="width: 144px;" >Lista Elementi Menu</a></li>
                </ul>           
            </li>
            <li style="width: 145"> <a style="width: 139; padding-top: 7;">&nbsp;<img src="resource/images/menu.png" style="height: 12px; width: 12px;">&nbsp;Amministrazione 2&nbsp;</a>
           		<ul>
                    <li><a href="listUsers"  style="width: 144px;">Lista Utenti</a></li>
                             <hr width="100%" size=3 color="grey">     
                    <li><a href="listRoles"   style="width: 144px;">Lista Ruoli</a></li>
                    <hr width="100%" size=3 color="grey"/> 
                    <li><a href="listPermissions"  style="width: 144px;">Lista Permessi</a></li>
                </ul>           
            </li>
            <li style="width: 145"> <a style="width: 139; padding-top: 7;">&nbsp;<img src="resource/images/menu.png" style="height: 12px; width: 12px;">&nbsp;Amministrazione 3&nbsp;</a>
           		<ul>
                    <li><a href="listUsers"  style="width: 144px;">Lista Utenti</a></li>
                           <hr width="100%" size=3 color="grey">       
                    <li><a href="listRoles"   style="width: 144px;">Lista Ruoli</a></li>
                    <hr width="100%" size=3 color="grey"> 
                    <li><a href="listPermissions"  style="width: 144px;">Lista Permessi</a></li>
                </ul>           
            </li>
            <li style="width: 145"> <a style="width: 139; padding-top: 7;">&nbsp;<img src="resource/images/menu.png" style="height: 12px; width: 12px;">&nbsp;Amministrazione 3&nbsp;</a>
           		<ul>
                    <li><a href="listUsers"  style="width: 144px;">Lista Utenti</a></li>
                           <hr width="100%" size=3 color="grey">       
                    <li><a href="listRoles"   style="width: 144px;">Lista Ruoli</a></li>
                    <hr width="100%" size=3 color="grey"> 
                    <li><a href="listPermissions"  style="width: 144px;">Lista Permessi</a></li>
                </ul>           
            </li><li style="width: 145"> <a style="width: 139; padding-top: 7;">&nbsp;<img src="resource/images/menu.png" style="height: 12px; width: 12px;">&nbsp;Amministrazione 3&nbsp;</a>
           		<ul>
                    <li><a href="listUsers"  style="width: 144px;">Lista Utenti</a></li>
                           <hr width="100%" size=3 color="grey">       
                    <li><a href="listRoles"   style="width: 144px;">Lista Ruoli</a></li>
                    <hr width="100%" size=3 color="grey"> 
                    <li><a href="listPermissions"  style="width: 144px;">Lista Permessi</a></li>
                </ul>           
            </li><li style="width: 145"> <a style="width: 139; padding-top: 7;">&nbsp;<img src="resource/images/menu.png" style="height: 12px; width: 12px;">&nbsp;Amministrazione 3&nbsp;</a>
           		<ul>
                    <li><a href="listUsers"  style="width: 144px;">Lista Utenti</a></li>
                           <hr width="100%" size=3 color="grey">       
                    <li><a href="listRoles"   style="width: 144px;">Lista Ruoli</a></li>
                    <hr width="100%" size=3 color="grey"> 
                    <li><a href="listPermissions"  style="width: 144px;">Lista Permessi</a></li>
                </ul>           
            </li><li style="width: 145"> <a style="width: 139; padding-top: 7;">&nbsp;<img src="resource/images/menu.png" style="height: 12px; width: 12px;">&nbsp;Amministrazione 3&nbsp;</a>
           		<ul>
                    <li><a href="listUsers"  style="width: 144px;">Lista Utenti</a></li>
                           <hr width="100%" size=3 color="grey">       
                    <li><a href="listRoles"   style="width: 144px;">Lista Ruoli</a></li>
                    <hr width="100%" size=3 color="grey"> 
                    <li><a href="listPermissions"  style="width: 144px;">Lista Permessi</a></li>
                </ul>           
            </li><li style="width: 145"> <a style="width: 139; padding-top: 7;">&nbsp;<img src="resource/images/menu.png" style="height: 12px; width: 12px;">&nbsp;Amministrazione 3&nbsp;</a>
           		<ul>
                    <li><a href="listUsers"  style="width: 144px;">Lista Utenti</a></li>
                           <hr width="100%" size=3 color="grey">       
                    <li><a href="listRoles"   style="width: 144px;">Lista Ruoli</a></li>
                    <hr width="100%" size=3 color="grey"> 
                    <li><a href="listPermissions"  style="width: 144px;">Lista Permessi</a></li>
                </ul>           
            </li><li style="width: 145"> <a style="width: 139; padding-top: 7;">&nbsp;<img src="resource/images/menu.png" style="height: 12px; width: 12px;">&nbsp;Amministrazione 3&nbsp;</a>
           		<ul>
                    <li><a href="listUsers"  style="width: 144px;">Lista Utenti</a></li>
                           <hr width="100%" size=3 color="grey">       
                    <li><a href="listRoles"   style="width: 144px;">Lista Ruoli</a></li>
                    <hr width="100%" size=3 color="grey"> 
                    <li><a href="listPermissions"  style="width: 144px;">Lista Permessi</a></li>
                </ul>           
            </li><li style="width: 145"> <a style="width: 139; padding-top: 7;">&nbsp;<img src="resource/images/menu.png" style="height: 12px; width: 12px;">&nbsp;Amministrazione 3&nbsp;</a>
           		<ul>
                    <li><a href="listUsers"  style="width: 144px;">Lista Utenti</a></li>
                           <hr width="100%" size=3 color="grey">       
                    <li><a href="listRoles"   style="width: 144px;">Lista Ruoli</a></li>
                    <hr width="100%" size=3 color="grey"> 
                    <li><a href="listPermissions"  style="width: 144px;">Lista Permessi</a></li>
                </ul>           
            </li><li style="width: 145"> <a style="width: 139; padding-top: 7;">&nbsp;<img src="resource/images/menu.png" style="height: 12px; width: 12px;">&nbsp;Amministrazione 3&nbsp;</a>
           		<ul>
                    <li><a href="listUsers"  style="width: 144px;">Lista Utenti</a></li>
                           <hr width="100%" size=3 color="grey">       
                    <li><a href="listRoles"   style="width: 144px;">Lista Ruoli</a></li>
                    <hr width="100%" size=3 color="grey"> 
                    <li><a href="listPermissions"  style="width: 144px;">Lista Permessi</a></li>
                </ul>           
            </li><li style="width: 145"> <a style="width: 139; padding-top: 7;">&nbsp;<img src="resource/images/menu.png" style="height: 12px; width: 12px;">&nbsp;Amministrazione 3&nbsp;</a>
           		<ul>
                    <li><a href="listUsers"  style="width: 144px;">Lista Utenti</a></li>
                           <hr width="100%" size=3 color="grey">       
                    <li><a href="listRoles"   style="width: 144px;">Lista Ruoli</a></li>
                    <hr width="100%" size=3 color="grey"> 
                    <li><a href="listPermissions"  style="width: 144px;">Lista Permessi</a></li>
                </ul>           
            </li><li style="width: 145"> <a style="width: 139; padding-top: 7;">&nbsp;<img src="resource/images/menu.png" style="height: 12px; width: 12px;">&nbsp;Amministrazione 3&nbsp;</a>
           		<ul>
                    <li><a href="listUsers"  style="width: 144px;">Lista Utenti</a></li>
                           <hr width="100%" size=3 color="grey">       
                    <li><a href="listRoles"   style="width: 144px;">Lista Ruoli</a></li>
                    <hr width="100%" size=3 color="grey"> 
                    <li><a href="listPermissions"  style="width: 144px;">Lista Permessi</a></li>
                </ul>           
            </li><li style="width: 145"> <a style="width: 139; padding-top: 7;">&nbsp;<img src="resource/images/menu.png" style="height: 12px; width: 12px;">&nbsp;Amministrazione 3&nbsp;</a>
           		<ul>
                    <li><a href="listUsers"  style="width: 144px;">Lista Utenti</a></li>
                           <hr width="100%" size=3 color="grey">       
                    <li><a href="listRoles"   style="width: 144px;">Lista Ruoli</a></li>
                    <hr width="100%" size=3 color="grey"> 
                    <li><a href="listPermissions"  style="width: 144px;">Lista Permessi</a></li>
                </ul>           
            </li><li style="width: 145"> <a style="width: 139; padding-top: 7;">&nbsp;<img src="resource/images/menu.png" style="height: 12px; width: 12px;">&nbsp;Amministrazione 3&nbsp;</a>
           		<ul>
                    <li><a href="listUsers"  style="width: 144px;">Lista Utenti</a></li>
                           <hr width="100%" size=3 color="grey">       
                    <li><a href="listRoles"   style="width: 144px;">Lista Ruoli</a></li>
                    <hr width="100%" size=3 color="grey"> 
                    <li><a href="listPermissions"  style="width: 144px;">Lista Permessi</a></li>
                </ul>           
            </li>
           </ul>
        </div>
        </div>   
   </c:when>
<c:otherwise>        
           
<div id="MENU" onclick="off()" onmouseover="on();">

	<div id="dhtmlgoodies_menu">

		<ul>
            <li style="width: 145"><a style="width: 185;text-align: right;">&nbsp;MENU&nbsp;</a>
            </li>
            <li style="width: 145"><a href="index" style="width: 139; padding-top: 7;">&nbsp;<img src="resource/images/home.png" style="height: 14px; width: 14px;">&nbsp;Home&nbsp;</a>
            </li>
            <li style="width: 145"> <a href="aboutUs" style="width: 139; padding-top: 7;">&nbsp;<img src="resource/images/info.png" style="height: 14px; width: 14px;">&nbsp;About us&nbsp; </a>
            </li>
            <li style="width: 145"> <a href="javascript:apri('aboutUs');"  style="width: 139; padding-top: 7;">&nbsp;<img src="resource/images/info.png" style="height: 14px; width: 14px;">&nbsp;About us / Pop-up&nbsp; </a>
            </li>
            
            
            
       </ul>    
	</div>
</div> 
           
 </c:otherwise>
</c:choose>          
           
           
           
           
<!-- <ul>
<li><a href="#">Siti</a>
<ul>
<li><a href="#">Grafica</a></li>
<li><a href="#">CSS</a></li>
<li><a href="#">Javascript</a></li>
<li><a href="#">Web Design</a></li>
</ul>
</li>
<li><a href="#">Servizi</a>
<ul>
<li><a href="#">Download</a></li>
<li><a href="#">Webtool</a></li>
<li><a href="#">Font.it</a></li>
<li><a href="#">Corsi</a></li>
</ul>
</li>
<li><a href="#">Forum</a>
<ul>
<li><a href="#">HTML/XHTML</a></li>
<li><a href="#">ASP</a></li>
<li><a href="#">PHP</a></li>
</ul>
</li>
<li><a href="#">Contatti</a></li>
</ul> -->




<!-- 
<div id="nav">
<ul style="display: block">
            <li style="width: 140"><a href="index" target="ifra" style="width: 140;text-align: right;">&nbsp;<img src="resource/images/home.png" style="height: 14px; width: 14px;">&nbsp;Home&nbsp;</a>
            </li>
            <br>
            <li style="width: 145"> <a href="aboutUs">&nbsp;<img src="resource/images/info.png" style="height: 14px; width: 14px;">&nbsp;About us&nbsp; </a>
            </li>
            <br>
            <li style="width: 145;left: auto;"> <a>&nbsp;<img src="resource/images/menu.png" style="height: 12px; width: 12px;">&nbsp;Amministrazione&nbsp;</a>
           		<ul style="left: auto;">
                    <li style="width: 145"><a href="listUsers"  target="ifra" tabindex="3" style="width: 144px;">Lista Utenti</a></li>
                    <hr width="100%" size=4 color="grey">               
                    <li style="width: 145"><a href="listRoles"  target="ifra" tabindex="2" style="width: 144px;">Lista Ruoli</a></li>
                    <hr width="100%" size=4 color="grey">
                    <li style="width: 145"><a href="listPermissions"  target="ifra" tabindex="4" style="width: 144px;">Lista Permessi</a></li>
                </ul>           
            </li>
           </ul>

</div>
-->

<!--  
    <table cellpadding="5" cellspacing="0"
           border="1" bordercolor="#000000">
    <tr>
    <td bgcolor="#FF0000" width="128" valign="top"
        style="padding: 0px 0px 0px 10px;">
            <a href="#">LINK # 1</a><br>
            <a href="#">LINK # 2</a><br>
            <a href="#">LINK # 3</a><br>
            <a href="#">LINK # 4</a><br>
            <a href="#">LINK # 5</a><br>
    </td>
    <td bgcolor="#FF0000" width="10" valign="top">
        M<br>
        E<br>
        N<br>
        U<br>
    </td>
    </tr>
    </table>
   -->
   

</body>
</html>