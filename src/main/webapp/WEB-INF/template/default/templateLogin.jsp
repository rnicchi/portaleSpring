<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login SCI-KpiManagement</title>

<link href="resource/css/style.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="resource/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="resource/js/jquery.validate.js" /></script>
<script type="text/javascript" src="resource/js/core.js" /></script>
<script type="text/javascript" src="resource/js/utils.js" /></script>

</head>
<body>
	<div id="container">
		<tiles:insertAttribute name="header" />
		<div id="box">
			<div id="second_box">
				
			<!-- 	<div id="content_ifra">  -->
					<tiles:insertAttribute name="body" /> 
			<!--	</div> -->
			</div>
		</div>
		<tiles:insertAttribute name="footer" />
	</div>
	
	
</body>
</html>