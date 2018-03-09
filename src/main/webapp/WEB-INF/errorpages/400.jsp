<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% response.setStatus(400); %>

<html> 
    <head>
    	<tiles:insertTemplate template="/WEB-INF/templates/vars.jsp" />
    	<link rel="shortcut icon" href="${ContextPath}/r/img/favicon.ico">
        <title><s:message code="app.title" /></title>

        <meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, ,minimum-scale=1, user-scale=no">
		<meta name="keywords" content="Yetiebra, YETI, yeti, guayabera, guayaberas, playa, palmeras">
  		<meta name="description" content="The best guayaberas in the worl, Yetibera">
		
        <tiles:insertTemplate template="/WEB-INF/templates/include.jsp" />
	</head>
	<body ng-app="YetiApp" class="body regular" style="">	
		<script>
			var Newpath = "${ ContextPath }";
		</script>
		<style>
			
			.loader {
				height: 10px;
				width: 100%;
				position: relative;
				overflow: hidden;
				background-color: #222;
				margin-top: 5px;
			}
			.loader:before{
			  display: block;
			  position: absolute;
			  content: "";
			  left: -200px;
			  width: 200px;
			  height: 10px;
			  background-color: #ddd;
			  animation: loading 2s linear infinite;
			}
			
			@keyframes loading {
			    from {left: -200px; width: 30%;}
			    50% {width: 30%;}
			    70% {width: 70%;}
			    80% { left: 50%;}
			    95% {left: 120%;}
			    to {left: 100%;}
			}
			
			.signal {
			    border: 5px solid #222;
			    border-radius: 30px;
			    height: 30px;
			    opacity: 0;
			    width: 30px;
			    margin:0 auto;
			 
			    animation: pulsate 1s ease-out;
			    animation-iteration-count: infinite;
			}
			
			@keyframes pulsate {
			    0% {
			      transform: scale(.1);
			      opacity: 0.0;
			    }
			    50% {
			      opacity: 1;
			    }
			    100% {
			      transform: scale(2);
			      opacity: 0;
			    }
			}
			
		</style>
		<div id="loader" class="backgroundPalmeras middleContent" style="position: absolute; top: 0px; right: 0px; bottom: 0px; left: 0px;min-height: 100%;height: auto;text-align: center;">
			<div style="margin: 0 auto;">			
				<div class="signal"></div>
				<div class="clearfix"></div>
				<div class="margin15em"></div>
				<img src="${ContextPath}/r/img/mona.png" style="width: 40%" class="img-responsive center center1" alt="" />
				<div class="loader center center1" style="width: 70%"></div>
				<div class="clearfix"></div>
				<div class="margin15em"></div>
			</div>
		</div>
		
		<div style="display:none;" id="myDiv" class="animate-bottom transitional">
			<div id="scrollHome"></div>
			<tiles:insertTemplate template="/WEB-INF/templates/header.jsp" />
				<div class="clearfix"></div>
				<div>
					<tiles:insertTemplate template="/WEB-INF/content/loginfloat.jsp" />
				</div>
				<div class="bgimg-1" style="width:100%;overflow:hidden;">
					<div  class="todo" style="width:200%;" id='todo'>
						<div class="" style="width:50%;" id="cont1" >
							<div class="clearfix" style=""></div>
								<div class="backgroundPalmeras">
									<div class="bgimg-2">
										<div style="">
											<div class="container center1">
												<h1 class="bajorrelieve">400</h1>
											</div>
										</div>
									</div>
									<div class="form-opacity container" >
										<div class="container  bajorrelieve center1" style="color:black;" >
											<div class="margin15em "></div>
										  
											<s:message code="error400" />
										
											<div class="clearfix"></div>
											<div class="margin15em"></div>
										</div>
										<div class="clearfix"></div>
									</div>
								</div>   
						</div>
						<div class="car" style="position:fixed;" id="cont2" >
							<tiles:insertTemplate template="/WEB-INF/content/car1.jsp" />
						</div>
					</div>
				</div>
				<div class="clearfix"></div>
			<div class="scrolltop">
				<a href="javascript:scrolltop();">
					<span class="fa-stack fa-lg">
					  <i class="fa fa-circle fa-stack-2x" style="color: black;"></i>
					  <i class="fa  fa-arrow-up fa-x3 fa-stack-1x fa-inverse"></i>
					</span>
				</a>
			</div>
			<tiles:insertTemplate template="/WEB-INF/templates/footer.jsp" />
		</div>
	</body>
</html>