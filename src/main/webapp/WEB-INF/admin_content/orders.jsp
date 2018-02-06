<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${empty sessionScope.userSession.email}">
	<script>
		//window.location = "${ linkHome }";
	</script>
</c:if>

<c:if test="${ param.delete  == true}">
	<input type="hidden" value="<s:message code="success.orderDelete" />" id="mensajeExisto" />
	<script>
		msjexito(document.getElementById('mensajeExisto').value);
	</script>
</c:if>

<input type="hidden" id="ordenes" value="${ requestScope.orders }" />
<div class="backgroundPalmeras ">
	<div class="bgimg-2">
		<div>
			<div class="container center1">
				<h1 class="bajorrelieve"><s:message code="app.history" /></h1>
			</div>
		</div>
	</div>
	
     <style>
     	.liblack a
     	{
     		background-color: black !important;
     		color:white !important;
     	}
     </style>
		      
	<div class="container form-opacity videoenbebido" ng-controller="${ appname }orders" ng-init="currentpage=1;getOrdersByFilter('',currentpage);">
		<ul class="nav nav-pills">
		  <li class="active"><a data-toggle="pill" href="#Sometida" ng-click="estado = 'todas';currentpage=1;getOrdersByFilter('',currentpage);" >All</a></li>
		  <li class=""><a data-toggle="pill" href="#Sometida" ng-click="estado = 'sometidas';currentpage=1;getOrdersByFilter(4,currentpage);" >Submited</a></li>
		  <li><a data-toggle="pill" href="#Aprobada" ng-click="estado = 'aprobadas';currentpage=1;getOrdersByFilter(5,currentpage);">Success</a></li>
		  <li><a data-toggle="pill" href="#curso" ng-click="estado = 'en curso';currentpage=1;getOrdersByFilter(6,currentpage);">In process</a></li>
		  <li><a data-toggle="pill" href="#Completa" ng-click="estado = 'Completadas';currentpage=1;getOrdersByFilter(7,currentpage);">Complete</a></li>
		  <li><a data-toggle="pill" href="#Atencion" ng-click="estado = 'en atención';currentpage=1;getOrdersByFilter(8,currentpage);">Atention</a></li>
		</ul>
		<div class="clearfix"></div>
		<br />
		<div class="" ng-if="!cuanto">
			<div class="alert alert-info">
			  <strong>Without information {{estado}}</strong> 
			</div>
		</div>
		<div class="" ng-if="cuanto">    
			<div class="col-xs-12 form-opacity">
			  <div class="margin15em"></div>
		      <div class="table-responsive">
		      	<table class="table table-bordered ">
			            <tr class="btn-black a">
			                <td>Open</td>
			                <td>Order</td>
			                <td>Customer Name</td>
			                <td>Status</td>
			                <td>Creation</td>
			            </tr>
			            <tr class="text-center" ng-repeat="(key, value) in ordenes">
			            	<td><a href="${ linkOrder }?idOrders={{value.id}}"><i class="fas fa-eye"></i></a></td>
							<td>{{value.id}}</td>
							<td>{{value.userText}}</td>
							<td>{{value.statusText}}</td>
							<td>{{value.creation}}</td>
			            </tr>
		      	</table>
		      </div>
		      
		      <div class="clearfix"></div>
		      <div style="" class="sintransitional center center1">
		      	 <ul class="pagination a center1 center" ng-show="paginasGlobals > 0" ng-init="cambiaPage(1);">
		      	 	<li ng-class="{'hidden': currectPage1 == 1 }" ng-click="cambiaPage(1);currectPage1 = 1">
		      	 		<a href="javascript:void(0);">
		      	 			<i class="fa fa-angle-double-left" aria-hidden="true"></i>
		      	 		</a>
		      	 	</li>
  					<li ng-class="{'hidden': currectPage1 == 1 }" ng-click="cambiaPage(currectPage1-1);currectPage1 = currectPage1 - 1">
		      	 		<a href="javascript:void(0);">
		      	 			<i class="fa fa-angle-left" aria-hidden="true"></i>
		      	 		</a>
		      	 	</li>
  					<li class="paginaSelect selected{{a}}" ng-repeat="a in currectPage" ng-click="cambiaPage(a);currectPage1 = a">
  						<a href="javascript:void(0);">{{a}}</a>
  					</li>
  					<li ng-class="{'hidden': currectPage1 == paginasGlobals+1 }" ng-click="cambiaPage(currectPage1+1);currectPage1 = currectPage1 +1">
		      	 		<a href="javascript:void(0);">
		      	 			<i class="fa fa-angle-right" aria-hidden="true"></i>
		      	 		</a>
		      	 	</li>
  					<li ng-class="{'hidden': currectPage1 == paginasGlobals+1 }" ng-click="cambiaPage(paginasGlobals+1);currectPage1 = paginasGlobals+1">
		      	 		<a href="javascript:void(0);">
		      	 			<i class="fa fa-angle-double-right" aria-hidden="true"></i>
		      	 		</a>
		      	 	</li>
				 </ul>
		      </div>
			</div>
			
			<%-- Paginacion $scope.antes --%>
			<div class="center">
				<ul class="pagination">
				  <li ng-if="currentpage > 1" ng-click="asignadas( currentpage = currentpage - 1);"><a href="javascript:void(0);"><i class="fa fa-angle-left"></i></a></li>
				  <li ng-repeat="pag in antes" ng-class="{'active': currentpage == pag}" class="" ng-click="asignadas( pag );">
				  	<a href="javascript:void(0);">
				  		{{pag}}
				  	</a>
				  </li>
				  <li ng-if="currentpage < ultimo" ng-click="asignadas( currentpage = currentpage + 1);"><a href="javascript:void(0);"><i class="fa fa-angle-right"></i></a></li>
				</ul>
			</div>
			<%-- Paginacion $scope.antes --%>				
		</div>
		
	<div class="clearfix"></div>
	</div>
</div>

<script type="text/javascript" src="${ContextPath}/r/js${DeployContext}/orders.js" charset="utf-8"></script>
