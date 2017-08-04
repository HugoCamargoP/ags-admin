<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
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
.item
{
	-webkit-background-size: cover;
    -moz-background-size: cover;
    -o-background-size: cover;
    background-size: cover;
}
.item:nth-child(1) {
    background: url(http://hugoe.tk:8081/yetibera/r/img/contensAdds/h7.jpg) no-repeat center center fixed;
}
.item:nth-child(2) {
    background: url(http://hugoe.tk:8081/yetibera/r/img/contensAdds/h4.jpg) no-repeat center center fixed;
}
.item:nth-child(3) {
    background: url(http://hugoe.tk:8081/yetibera/r/img/contensAdds/h6.jpg) no-repeat center center fixed;
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

<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION.message }">
	<script>
		msjerror('${SPRING_SECURITY_LAST_EXCEPTION.message}');
	</script>
	<div id="" class=" hidden mensajes alert alert-danger center alert0">
		<b style="cursor:pointer;" onclick="addremoveclass('alert2','alert0');addremoveclass('alert','alert0');"><i class="fa fa-window-close" aria-hidden="true"></i></b>
		<div onload=" "></div>
		&nbsp;
		<b>${SPRING_SECURITY_LAST_EXCEPTION.message}</b>
	</div>
</c:if>

<div class="container center">

	<div class="white-opacity displayCenter col-xs-12 col-sm-8 col-md-8 col-lg-4">
		<div class="col-xs-12 col-sm-6">
                 <img class="img-loginlogo" style="" src="${ContextPath}/r/img/logos/lnegro.png" alt="" />
		</div>
		<div class="col-xs-12 col-sm-6">
			<form action="" class="formulario" method="post" name="formlogin" id="formlogin" ng-model="formlogin">
				
				  <div class="input-group ">
				    <span class="input-group-addon"><i class="fa fa-user"></i></span>
				    <input type="text" class="form-control" value="" name="name" id="name" ng-model="user.email" ng-required="true"/>
				  </div>
				  <div class="input-group ">
				    <span class="input-group-addon"><i class="fa fa-lock"></i></span>
					<input type="password" class="form-control" value="" name="password" name="password" ng-model="user.password" ng-required="true"/>
				  </div>
				
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				<button class="btn btn-black btn-primary btnboot" type="submit">Ingresar</button>   
			</form>	 
		</div>	
	</div>
</div>



<script type="text/javascript" src="${ContextPath}/r/js${DeployContext}/user_admin.js" charset="utf-8"></script>
<script>
	$('.carousel').carousel();
</script>