<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- Error: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />--%>
<div class="sign-in-up" ng-controller="${ appname }userAdmin">
    <section>
    
    <c:if test="${not empty sessionScope.userSession.type}">
	    <script> 
	    	var type = ${sessionScope.userSession.type};
	    	console.log(type);
	    	if(type == 0)
	    	{
	    		window.location = "${linkAdminCreateU}";
	    		console.log("${linkAdminCreateU}");
	    	}
	    	else
	    	{
	    		//window.location = "${linkHome}";
	    		console.log("${linkAdminHome}");
	    	}
	    </script>
    </c:if>
			<div id="page-wrapper" class="sign-in-wrapper">
				<div class="graphs">
					<div class="sign-in-form">
						<div class="centerText">
                            <img class="img-loginlogo" style="" src="${ContextPath}/r/img/logo_yeti.png" alt="" />
                            <!-- p><span>100 -</span> Store <span>Management</span> Suite</p -->
						</div>
						<div class="signin">
							<form action="" method="post" name="formlogin" id="formlogin" ng-model="formlogin"
							onsubmit="return false;" ng-submit="logginRest();">
								<center>
									<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION.message }">
										<div class="alert alert-danger textcenter">
											${SPRING_SECURITY_LAST_EXCEPTION.message}
										</div>
									</c:if>
									<%--
									<c:if test="${not empty requestScope.error }">
										<div class="alert alert-danger textcenter">
											${requestScope.error}
										</div>
									</c:if>
									--%>
									<div class="log-input">
										<div class="log-input-left1">
										   <input type="text" class="user" value="" name="name" id="name"
										   ng-model="user.email" ng-required="true"/>
										</div>
										<div class="clearfix"> </div>
									</div>
									<div class="log-input">
										<div class="log-input-left1">
										   <input type="password" class="lock" value="" name="password" name="password"
										   ng-model="user.password" ng-required="true"/>
										</div>
										<div class="clearfix"> </div>
									</div>
									<!-- <input type="submit" value="Ingresar"> -->
		                            <button type="submit">Ingresar</button>
		                    	</center>
							</form>	 
						</div>
					</div>
				</div>
			</div>
	</section>
</div>


<script type="text/javascript" src="${ContextPath}/r/js${DeployContext}/user_admin.js" charset="utf-8"></script>
