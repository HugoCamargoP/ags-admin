<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% response.setStatus(503); %>

<html> 
    <head>
    	<tiles:insertTemplate template="/WEB-INF/admin_templates/vars.jsp" />
    	
        <title><s:message code="app.title" /></title>

    	<link rel="shortcut icon" href="${ContextPath}/r/images/favicon.ico">
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

        <tiles:insertTemplate template="/WEB-INF/admin_templates/include.jsp" />
		<tiles:insertTemplate template="/WEB-INF/admin_templates/includefooter.jsp" />
	</head>
	<body ng-app="${ appname }App" class="left-side-collapsed">
		<script>
			var Newpath = "${ ContextPath }";
		</script>
		<div style="" class="mensajes" id="mensajes">
		</div>
		<tiles:insertTemplate template="/WEB-INF/admin_templates/menulateral.jsp" />
		<div class="main-content">
			<tiles:insertTemplate template="/WEB-INF/admin_templates/header.jsp" />
			<div id="page-wrapper" ng-controller="${ appname }Prod" ng-init="getConfigEntity();">
				
				<input type="hidden" id="csrf" name="${_csrf.parameterName}" value="${_csrf.token}" />
				
				<div class="graphs">
					<h3 class="blank1 center">503</h3>
					<div class="tab-content">
						<div class="jumbotron text-center">
							<div class="form-opacity container" >
								<div class="container  bajorrelieve center1" style="color:black;" >
									<div class="margin15em "></div>
								  
									<s:message code="error503" />
								
									<div class="clearfix"></div>
									<div class="margin15em"></div>
								</div>
								<div class="clearfix"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<tiles:insertTemplate template="/WEB-INF/admin_templates/footer.jsp" />
		</div>
	</body>
</html>