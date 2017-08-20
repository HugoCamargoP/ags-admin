<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
    	<tiles:insertTemplate template="/WEB-INF/admin_templates/vars.jsp" />
    	
        <title><s:message code="app.title" /></title>

    	<link rel="shortcut icon" href="${ContextPath}/r/images/favicon.ico">
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

        <tiles:insertTemplate template="/WEB-INF/admin_templates/include.jsp" />
		<tiles:insertAttribute name="js" />
	</head>
	<body ng-app="${ appname }App" class="left-side-collapsed">
		<div style="" class="mensajes" id="mensajes">
			<%--div class="alert alert2 alert-success text-center">
				hola
			</div --%>
		</div>
		<tiles:insertAttribute name="menulateral" />
		<div class="main-content">
			<tiles:insertAttribute name="header" />
			<tiles:insertAttribute name="content" />
			<tiles:insertAttribute name="footer" />
		</div>
	</body>
</html>