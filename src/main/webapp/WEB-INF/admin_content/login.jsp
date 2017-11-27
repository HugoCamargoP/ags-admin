<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<link type="text/css" rel="stylesheet" href="${ContextPath}/r/css${DeployContext}/login.css"/>
    <c:if test="${not empty sessionScope.userSession.type}">
	    <script> 
	    	var type = ${sessionScope.userSession.type};
	    	if(type == 0)
	    	{
	    		window.location = "${linkAdminCreateU}";
	    		console.log("${linkAdminCreateU}");
	    	}
	    	else
	    	{
	    		console.log("${linkAdminHome}");
	    	}
	    </script>
    </c:if>
    
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


<style>
.fullscreen {
  position: fixed;
  top: 0;
  left: 0;
  bottom: 0;
  right: 0;
  overflow: auto;
}
</style>
<div class="container center fullscreen">
	<div id="menulgueador" style="display:none;" class="white-opacity displayCenter col-xs-12 col-sm-8 col-md-8 col-lg-6">
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
					<button class="btn btn-black btnboot" type="submit">Ingresar</button>
			</form>	 
		</div>	
	</div>
</div>

<script>
	$(document).ready(function (){
		$('.carousel').carousel();
		$("#menulgueador").slideDown( "slow" );
	})
</script>