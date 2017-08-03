<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<style>
html {
    position: relative;
    min-height: 100%;
}
.carousel-fade .carousel-inner .item {
    opacity: 0;
    transition-property: opacity;
}
.carousel-fade .carousel-inner .active {
    opacity: 1;
}
.carousel-fade .carousel-inner .active.left,
.carousel-fade .carousel-inner .active.right {
    left: 0;
    opacity: 0;
    z-index: 1;
}
.carousel-fade .carousel-inner .next.left,
.carousel-fade .carousel-inner .prev.right {
    opacity: 1;
}
.carousel-fade .carousel-control {
    z-index: 2;
}
@media all and (transform-3d),
(-webkit-transform-3d) {
    .carousel-fade .carousel-inner > .item.next,
    .carousel-fade .carousel-inner > .item.active.right {
        opacity: 0;
        -webkit-transform: translate3d(0, 0, 0);
        transform: translate3d(0, 0, 0);
    }
    .carousel-fade .carousel-inner > .item.prev,
    .carousel-fade .carousel-inner > .item.active.left {
        opacity: 0;
        -webkit-transform: translate3d(0, 0, 0);
        transform: translate3d(0, 0, 0);
    }
    .carousel-fade .carousel-inner > .item.next.left,
    .carousel-fade .carousel-inner > .item.prev.right,
    .carousel-fade .carousel-inner > .item.active {
        opacity: 1;
        -webkit-transform: translate3d(0, 0, 0);
        transform: translate3d(0, 0, 0);
    }
}
.item:nth-child(1) {
    background: url(https://ununsplash.imgix.net/photo-1417021423914-070979c8eb34?q=75&fm=jpg&s=55746bd56e02a131b1e48c12196ea866) no-repeat center center fixed;
    -webkit-background-size: cover;
    -moz-background-size: cover;
    -o-background-size: cover;
    background-size: cover;
}
.item:nth-child(2) {
    background: url(https://ununsplash.imgix.net/reserve/oY3ayprWQlewtG7N4OXl_DSC_5225-2.jpg?q=75&fm=jpg&s=85ab821f3fa535036a68155aab571bad) no-repeat center center fixed;
    -webkit-background-size: cover;
    -moz-background-size: cover;
    -o-background-size: cover;
    background-size: cover;
}
.item:nth-child(3) {
    background: url(https://unsplash.imgix.net/photo-1414073875831-b47709631146?q=75&fm=jpg&s=731b6d5150eea8bafa63ae8194e72ebd) no-repeat center center fixed;
    -webkit-background-size: cover;
    -moz-background-size: cover;
    -o-background-size: cover;
    background-size: cover;
}
.carousel {
    z-index: -99;
}
.carousel .item {
    position: fixed;
    width: 100%;
    height: 100%;
}
.title {
  text-align: center;
  margin-top: 20px;
  padding: 10px;
  text-shadow: 2px 2px #000;
  color: #FFF;
}
</style>
<%-- Error: <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />--%>

<!-- Inspired by https://codepen.io/transportedman/pen/NPWRGq -->

<div class="carousel slide carousel-fade" data-ride="carousel">

    <!-- Wrapper for slides -->
    <div class="carousel-inner" role="listbox">
        <div class="item active">
        </div>
        <div class="item">
        </div>
        <div class="item">
        </div>
    </div>
</div>

<!-- Remeber to put all the content you want on top of the slider below the slider code -->

<div class="title hidden">
  <h1>This is Awesome</h1>
</div>

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
							<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION.message }">
								<div class="alert alert-danger textcenter">
									${SPRING_SECURITY_LAST_EXCEPTION.message}
								</div>
							</c:if>
						</div>
						<div class="signin">
							<form action="" method="post" name="formlogin" id="formlogin" ng-model="formlogin">
								<center>
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
									
									<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
									
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
<script>
	$('.carousel').carousel();
</script>