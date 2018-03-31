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
	<li style="width: 145"><a style="height:28px; width: 185;text-align: right;">&nbsp;MENU&nbsp;</a></li>
	
	<c:forEach var="menu" items="${menuElementsList}">
	
		
			<c:if test="${menu.parent_id == 0 && menu.link_id != 1}">
			
			
					<li style="width: 145"><a href="${menu.link}" target="${menu.target}" style="width: 139; padding-top: 7;">&nbsp;<img src="${menu.img_link}"style="height: 14px; width: 14px;"/>&nbsp;${menu.link_name}&nbsp;</a></li>
			
			</c:if>
			
			
			<c:if test="${menu.parent_id == 1}">
			
			<li style="width: 145"><a href="${menu.link}" target="${menu.target}" style="width: 139; padding-top: 7;">&nbsp;<img src="${menu.img_link}"style="height: 14px; width: 14px;"/>&nbsp;${menu.link_name}&nbsp;</a>
				<ul>
				<c:forEach var="menu2" items="${menuElementsList}">
				
				 <c:if test="${menu2.parent_id == menu.link_id && menu2.subParent_id == 0}">
				 	<li style="width: 145"><a href="${menu2.link}" target="${menu2.target}" style="width: 139; padding-top: 7;">&nbsp;<img src="${menu2.img_link}" style="height: 14px; width: 14px;"/>&nbsp;${menu2.link_name}&nbsp;</a></li>
					<hr width="100%" size=3 color="grey"/>
				 </c:if>
				 
				 <c:if test="${menu2.parent_id == menu.link_id && menu2.subParent_id == 1}">
				 	<li style="width: 145"><a href="${menu2.link}" target="${menu2.target}" style="width: 139; padding-top: 7;">&nbsp;<img src="${menu2.img_link}"style="height: 14px; width: 14px;"/>&nbsp;${menu2.link_name}&nbsp;</a>
						<ul>
						<c:forEach var="menu3" items="${menuElementsList}">
				
							<c:if test="${menu3.subParent_id == menu2.link_id}">
				
								<li style="width: 145"><a href="${menu3.link}" target="${menu3.target}" style="width: 139; padding-top: 7;">&nbsp;<img src="${menu3.img_link}" style="height: 14px; width: 14px;"/>&nbsp;${menu3.link_name}&nbsp;</a></li>
									<hr width="100%" size=3 color="grey"/>
				
							</c:if>				
						</c:forEach>
						</ul>
					</li>
				</c:if>
		</c:forEach>
		</ul>
		</li>
	</c:if>
						
	</c:forEach>
						
</ul>

</div>
</div>   

</c:when>
<c:otherwise>
<div id="MENU" onclick="off()" onmouseover="on();">

<div id="dhtmlgoodies_menu">

<ul>
	<li style="width: 145"><a style="height:28px; width: 185;text-align: right;">&nbsp;MENU&nbsp;</a></li>
	
	<c:forEach var="menu" items="${menuUserElementsList}">
	
		
			<c:if test="${menu.parent_id == 0 && menu.link_id != 1}">
			
			
					<li style="width: 145"><a href="${menu.link}" target="${menu.target}" style="width: 139; padding-top: 7;">&nbsp;<img src="${menu.img_link}"style="height: 14px; width: 14px;"/>&nbsp;${menu.link_name}&nbsp;</a></li>
			
			</c:if>
			
			
			<c:if test="${menu.parent_id == 1}">
			
			<li style="width: 145"><a href="${menu.link}" target="${menu.target}" style="width: 139; padding-top: 7;">&nbsp;<img src="${menu.img_link}"style="height: 14px; width: 14px;"/>&nbsp;${menu.link_name}&nbsp;</a>
				<ul>
				<c:forEach var="menu2" items="${menuUserElementsList}">
				
				 <c:if test="${menu2.parent_id == menu.link_id && menu2.subParent_id == 0}">
				 	<li style="width: 145"><a href="${menu2.link}" target="${menu2.target}" style="width: 139; padding-top: 7;">&nbsp;<img src="${menu2.img_link}" style="height: 14px; width: 14px;"/>&nbsp;${menu2.link_name}&nbsp;</a></li>
					<hr width="100%" size=3 color="grey"/>
				 </c:if>
				 
				 <c:if test="${menu2.parent_id == menu.link_id && menu2.subParent_id == 1}">
				 	<li style="width: 145"><a href="${menu2.link}" target="${menu2.target}" style="width: 139; padding-top: 7;">&nbsp;<img src="${menu2.img_link}"style="height: 14px; width: 14px;"/>&nbsp;${menu2.link_name}&nbsp;</a>
						<ul>
						<c:forEach var="menu3" items="${menuUserElementsList}">
				
							<c:if test="${menu3.subParent_id == menu2.link_id}">
				
								<li style="width: 145"><a href="${menu3.link}" target="${menu3.target}" style="width: 139; padding-top: 7;">&nbsp;<img src="${menu3.img_link}" style="height: 14px; width: 14px;"/>&nbsp;${menu3.link_name}&nbsp;</a></li>
									<hr width="100%" size=3 color="grey"/>
				
							</c:if>				
						</c:forEach>
						</ul>
					</li>
				</c:if>
		</c:forEach>
		</ul>
		</li>
	</c:if>
						
	</c:forEach>
						
</ul>

</div>
</div>

</c:otherwise>    
</c:choose> 
 
 
</body>
</html>