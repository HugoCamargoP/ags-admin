<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- Error: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />--%>
<div class="sign-in-up" ng-controller="${ appname }user">
    <section>
			<div id="page-wrapper" class="sign-in-wrapper">
				<div class="graphs">
					<div class="sign-in-form">
						<div class="">
                            <img class="img-responsive" src="${ContextPath}/r/images/text4070-1-0.png" alt="" />
                            <!-- p><span>100 -</span> Store <span>Management</span> Suite</p -->
						</div>
						<div class="signin">
							<form action="${ linkLogin }" method="post" name="formlogin" id="formlogin" ng-model="formlogin">
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
										   ng-model="usuario" ng-required="true"/>
										</div>
										<div class="clearfix"> </div>
									</div>
									<div class="log-input">
										<div class="log-input-left1">
										   <input type="password" class="lock" value="" name="password" name="password"
										   ng-model="password" ng-required="true"/>
										</div>
										<div class="clearfix"> </div>
									</div>
									
									<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
									
									<!-- <input type="submit" value="Ingresar"> -->
		                            <button type="submit" value="Ingresar">Ingresar</button>
		                    	</center>
							</form>	 
						</div>
					</div>
				</div>
			</div>
	</section>
</div>

