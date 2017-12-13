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
	
	<div class="container form-opacity videoenbebido" ng-controller="${ appname }orders" ng-init="getOrders();">
		<div class="">    
			<div class="col-xs-12 form-opacity">
			  <div class="margin15em"></div>
		      <div class="table-responsive">
		      	<table class="table table-bordered " onload="cargaTable();">
			            <tr class="black a">
			                <td></td>
			                <td>Order</td>
			                <td>Customer Name</td>
			                <td>Status</td>
			                <td>Order Date</td>
			            </tr>
				      <c:forEach items="${ orders }" var="a" varStatus="theCount">
				      		<tr class="center1 visible${theCount.count} hidden todas a">
				      			<td>
				      				<a href="${ linkUserOrder }${a.id}" style="color:black;">
					      				<div>
					      					<i class="fa fa-eye" aria-hidden="true"></i>
					      				</div>
				      				</a>
				      			</td>
				      			<td>
				      				${ a.id }
				      			</td>
					         	<td>${ sessionScope.userSession.name }</td>
					         	<td>
					         	<%-- 
						         	<c:forEach items="${a.orderRecord}" var="current" varStatus="loop">
									    <c:if test="${loop.last}">${ current.stateText }</c:if>
									</c:forEach>
								--%>
								${ a.statusTex }					         	
					         	</td>
					         	<td>${ a.creation }</td>
				         	</tr>
					      <script>
					      	var theCount = ${theCount.count};
					      </script>
				      </c:forEach>
		      	</table>
		      </div>
		      <style>
		      	.liblack a
		      	{
		      		background-color: black !important;
		      		color:white !important;
		      	}
		      </style>
		      
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
		</div>
	<div class="clearfix"></div>
	</div>
</div>

<script type="text/javascript" src="${ContextPath}/r/js${DeployContext}/orders.js" charset="utf-8"></script>
