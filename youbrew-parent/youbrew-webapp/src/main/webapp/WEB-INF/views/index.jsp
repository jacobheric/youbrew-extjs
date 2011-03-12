<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>
<%--@ page trimDirectiveWhitespaces="true" --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
    <title><fmt:message key="app.title"/></title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/ext-3.3.0/resources/css/ext-all.css" />" media="screen, projection"/>
	<link rel="stylesheet" type="text/css" href="<c:url value="/styles/youbrew.css" />"  media="screen, projection"/>

    <script type="text/javascript" language="JavaScript" src="<c:url value="/ext-3.3.0/adapter/ext/ext-base-debug.js"/>"></script>
    <script type="text/javascript" language="JavaScript" src="<c:url value="/ext-3.3.0/ext-all-debug.js"/>"></script>

    <script type="text/javascript" src="<c:url value="/scripts/utils/App.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/scripts/youbrew.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/scripts/recipe/recipeForm.js"/>"></script>
	<script type="text/javascript" src="<c:url value="/scripts/recipe/recipeGrid.js"/>"></script>	
	
</head>
<body>

    <script type="text/javascript">
		Ext.BLANK_IMAGE_URL = '<c:url value="/ext-3.3.0/resources/images/default/s.gif"/>';
	</script>

    <div style="width:900px; margin: 20px;">
    	<div id="recipe-grid" style="margin-bottom: 20px;"></div>
    	<div id="recipe-form"></div>		
	</div>
</body>
</html>