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
		<div class="clearfix"></div>
		<br />
		<form class="form-inline  pull-right" action="" onsubmit="return false" ng-submit="getOrdersByFilter(otra.state,currentpage);">
		  <div class="form-group">
		    <label for="pwd">No Order</label>
		    <input type="text" class="form-control" id="" ng-model="otra.id" placeholder="<s:message code="buy.search"/>">
		  </div>
		  <button type="submit" class="btn btn-default"><s:message code="buy.search"/></button>
		</form>
		<div class="clearfix"></div>
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
		
		<!-- Modal -->
		<div id="myModal" class="modal fade myModal" role="dialog">
		  <div class="modal-dialog">
		
		    <!-- Modal content-->
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal">&times;</button>
		        <h4 class="modal-title">Email contact</h4>
		      </div>
		      <div class="modal-body">
			      <form action="" onsubmit="return false;" ng-submit="orderContact();">
				        <div class="form-group">
					        <label for="">To:</label>
					        <div class="form-control disabled" disabled="disabled" > {{ordercontactor.name}} </div>
				        </div>
				        <div class="form-group">
					        <label for="">Subject:</label>
					        <input class="form-control" type="text" ng-model="ordercontactor.subject" ng-required="true" />
				        </div>
				        <div class="form-group">
					        <label for="">Message:</label>
					        <textarea name="" id="" class="form-control" rows="5" ng-required="true" ng-model="ordercontactor.message"></textarea>
					    </div>
				      </div>
				      <div class="text-center">
				      	<button class="btn btn-primary" type="submit">Send</button>
				      </div>
				      <div class="clearfix"></div>
				      <br /><br />
			      </form>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		      </div>
		    </div>
		
		  </div>
		</div>
		
		<div class="" ng-if="cuanto">    
			<div class="col-xs-12 form-opacity">
			  <div class="margin15em"></div>
		      <div class="table-responsive">
		      	<table class="table table-bordered ">
			            <tr class="btn-black a">
			                <td>Open</td>
			                <td>Send&nbsp;Email</td>
			                <td>Order</td>
			                <td>Customer Name</td>
			                <td>Status</td>
			                <td>Creation</td>
			            </tr>       
			            <tr class="text-center" ng-repeat="(key, value) in ordenes">
			            	<td><a href="${ linkOrder }?order={{value.id}}"><i class="fa fa-eye"></i></a></td>
			            	<td class="click" ng-click="ordercontactor.orderId=value.id; ordercontactor.name = value.userText; " data-toggle="modal" data-target="#myModal">
			            		<a href="javascript:void();"><i class="fa fa-envelope-open-o"></i></a>
			            	</td>
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
