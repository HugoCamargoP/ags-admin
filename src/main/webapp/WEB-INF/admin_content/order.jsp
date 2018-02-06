<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${empty sessionScope.userSession.email}">
	<script>
		//window.location = "${ linkHome }";
	</script>
</c:if>
<input type="hidden" id="ordenes" value="${ requestScope.orders }" />
<div class="backgroundPalmeras ">
	<div class="bgimg-2">
		<div style="">
			<div class="container center1">
				<h1 class="bajorrelieve"><s:message code="buy.details" /></h1>
			</div>
		</div>
	</div>
	<style>
	.border
	{
		border:1px solid #e3e3e3;
	}
	</style>
	<div class="container form-opacity videoenbebido" ng-controller="${ appname }orders"> <%-- ng-init="getCountries();">  --%>
	n
	${ order }
	m
		<input type="hidden" value="${ linkHistory }" id="historial" />
		<input type="hidden" value="${ linkPayment }" id="paymentLink" />
		<div class="margin14em"></div>
		<a href="${ linkHistory }" class="pull-left">
			<i style="color: black;font-size: 2em;" class="fa fa-x2 fa-arrow-circle-o-left"></i>
		</a>
		<div class="clearfix"></div>
		<div class="margin15em"></div>
		<div class="">    
			<div class="col-xs-12 form-opacity">
				<%-- 
		        <c:if test="${ order.state < 4 or order.state > 7  }">
					<div class="clearfix"></div>
					<div class="margin15em"></div>
					<button ng-click="removeOrder(${ param.order })" class="btn btn-danger col-xs-12 a">
						<s:message code="buy.deleteOrder" />
					</button>
					<div class="clearfix"></div>
					<div class="margin15em"></div>
				</c:if>
				--%>
				
			  <div class="margin15em"></div>
			  <div>
				  <div class="col-xs-12">
				  	<a href="" class=" center center1 visible-xs visile-sm">
				  		<img class="" id="" src="${ContextPath}/r/img/logos/lnegro.png" alt="" />
				  	</a>
				  	<a href="" class="hidden-xs hidden-sm">
				  		<img class="" id="" src="${ContextPath}/r/img/logos/lnegro.png" alt="" />
				  	</a>
				  	<div class="" style="float: right;">
					  	<h3><s:message code="order.idOrder" />: ${ order.id }<br />
					  	<s:message code="order.date" />: ${ order.creation } </h3>
				  	</div>
				  </div>
				  <div class="clearfix"></div>
			  	  <div class="margin15em"></div>
				  <div class="col-xs-12 col-md-6">
				  	<h3><s:message code="order.client" />: ${ sessionScope.userSession.name }</h3>
				  </div>
				  <div class="clearfix"></div>
				  <div ng-if="address">
				  		<div class="">
				  			<h3 class="black center1"><s:message code="buy.address" /></h3>
				  		</div>
				  		<ul class="list-inline center1">
				  			<li class="col-xs-6 col-md-4 border">
				  				<h2><s:message code="address.country" /></h2>
				  				<span class="a" ng-repeat="a in paises | filter:address.country ">{{a.name}}</span>
				  			</li>
				  			<li class="col-xs-6 col-md-4 border">
				  				<h2><s:message code="address.state" /></h2>
				  				<span class="a">{{address.state}}</span>
				  			</li>
				  			<li class="col-xs-12 col-md-4 border">
				  				<h2><s:message code="address.city" /></h2>
				  				<span class="a">{{address.city}}</span>
				  			</li>
				  			<li class="col-xs-12 col-md-12 border">
				  				<h2><s:message code="address.street" /></h2>
				  				<span class="a">{{address.detail1}}</span>
				  			</li>
				  			<li class="col-xs-4 border">
				  				<h2><s:message code="address.zip" /></h2>
				  				<span class="a">{{address.zip}}</span>
				  			</li>
				  			<li class="col-xs-8 border">
				  				<h2><s:message code="address.phone" /></h2>
				  				<span class="a">{{address.phone}}</span>
				  			</li>
				  		</ul>
				  </div>
				  
				  <div class="clearfix"></div>
				  <div class="margin15em"></div>
				  
				  <div class="center1 a">
				  	<div class="col-xs-12 col-md-6 black border">
				  		<h2><s:message code="buy.status" />:</h2>
				  	</div>
				  	<div class="col-xs-12 col-md-6 border">
				  		<h2>
				  		<%--
				  		${ order.statusTex }
				  		
						<c:forEach items="${ order.orderRecord }" var="a" varStatus="theCount">
							<c:if test="${ theCount.last }">${ a.stateText }</c:if>
						</c:forEach> 
						--%>
						</h2>
				  	</div>
				  </div>
				  
				  <div class="clearfix"></div>
				  <div class="margin15em"></div>
				  
				  <style>
				  	.vertical-aling-middel
				  	{
				  		vertical-align: middle !important;
				  	}
				  </style>
				  		<div class="">
				  			<h3 class="black center1"><s:message code="buy.details" /></h3>
				  		</div>
				  		<div class="table-responsive">
				  			<table class="table table-bordered a">
				  				<tr class="black a" ng-init="price = 0">
				  					<td class="vertical-aling-middel">
				  					</td>
				  					<td class="vertical-aling-middel hidden">
				  						<s:message code="buy.title" />
				  					</td>
				  					<td class="vertical-aling-middel">
				  						<s:message code="buy.price" />
				  					</td>
				  					<%-- 
				  					<td class="vertical-aling-middel hidden">
				  						<s:message code="buy.taxes" />
				  					</td>
				  					--%>
				  					<td class="vertical-aling-middel">
				  						<s:message code="buy.quantity" />
				  					</td>
				  					<td class="vertical-aling-middel">
				  						TOTAL
				  					</td>
				  				</tr>
						       <c:forEach items="${ order.orderDetail }" var="a" varStatus="theCount">
					  				<tr class="center center1 a">
					  					<td class="vertical-aling-middel">
					  						<img src="${ a.url }" class="img-responsive center center1" alt="" style="max-width:70px;" />
					  					</td>
					  					<td class="vertical-aling-middel hidden">${ a.product }</td>
					  					<td class="vertical-aling-middel">${ a.individualPrice }</td>
					  					<%-- 
					  					<td class="vertical-aling-middel hidden">${ a.taxesAmount }</td>
					  					--%>
					  					<td class="vertical-aling-middel">${ a.amount }</td>
					  					<td class="vertical-aling-middel" ng-init="price = price + ${ ((a.individualPrice) * a.amount) };">${ ((a.individualPrice) * a.amount) }</td>
					  				</tr>
						       </c:forEach>
						       <tr class="center center1 a">
						       		<td></td>
						       		<td></td>
						       		<%-- 
						       		<td class="hidden"></td>
						       		--%>
						       		<td>Total</td>
						       		<td>{{ price | currency:"USD$ " }}</td>
						       </tr>
				  			</table>
				  		</div>
				  		
			        	<c:if test="${ order.state == 4  }">
					    	<div class="col-xs-12 col-md-6 pull-left">
					    		<button class="btn-lg btn btn-success col-xs-12 col-md-6" type="submit" ng-click="cancelRequestedOrder(${ order.id });">
					    			<i class="fa fa-pencil"></i>&nbsp;<s:message code="order.edit" />
					    		</button>
						    </div>
						</c:if>
			        	<c:if test="${ order.state == 3 }">
					    	<div class="col-xs-12 col-md-6 pull-left">
					    		<button class="btn-lg btn btn-success col-xs-12 col-md-6" type="submit" onclick="window.location = '${ linkPayment}'">
					    			<i class="fa fa-pencil"></i>&nbsp;<s:message code="order.edit" />
					    		</button>
						    </div>
						</c:if>
			        	<c:if test="${ order.state == 3 or order.state == 4  }">
					    	<div class="col-xs-12 col-md-6 pull-right">
					    		<form action="${ linkPayment }" method="POST">
					    			<input name="idOrder" type="hidden" class="hidden" value="${ order.id }" />
					    			<h3 class="col-xs-12 col-md-6 center center1">
					    				<s:message code="buy.payProced" />:
					    			</h3>
						    		<button class="btn btn-warning col-xs-12 col-md-6" onclick="activeLoader();">
						    			<img src="${ContextPath}/r/img/paypalLogo.png" class="img-responsive" alt="" />
						    		</button>
					    		</form>
						    </div>
						</c:if>
				  		<div class="clearfix"></div>
				  		<div class="margin15em"></div>
				  		
				  		<div class="hidden">
				  			<h3 class="black center1"><s:message code="buy.status" /></h3>
				  		</div>
				  		<ul class="list-inline center1 hidden">
						<c:forEach items="${ order.orderRecord }" var="a" varStatus="theCount">
							<li class="col-xs-12 col-md-3 border">
				  				<h2>${ a.stateText }</h2>
				  				<span class="a"> ${ a.update }</span>
					  		</li>
						</c:forEach>
				  		</ul>
				  <div ng-init="getAddressById(${ order.address });">
				  </div>
			  </div>
			  <div class="clearfix"></div>
			  <div class="margin15em"></div>
		      <%-- 
		      <div class="table-responsive hidden">
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
						         	<c:forEach items="${a.orderRecord}" var="current" varStatus="loop">
									    <c:if test="${loop.last}">${ current.stateText }</c:if>
									</c:forEach>					         	
					         	</td>
					         	<td>${ a.creation }</td>
				         	</tr>
					      <script>
					      	var theCount = ${theCount.count};
					      </script>
				      </c:forEach>
		      	</table>
		      </div>
		      --%>
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
