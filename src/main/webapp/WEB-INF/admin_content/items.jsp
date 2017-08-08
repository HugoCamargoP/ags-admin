<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div id="page-wrapper" ng-controller="${ appname }Prod" ng-init="getAllProducts();overs= {};">
	<div class="graphs">
		<h3 class="blank1 center"><s:message code="admin.report"/></h3>
			<div class="tab-content">
				<div class="jumbotron">
					 <div ng-repeat="p in productos" class="container marginem" ng-mouseover="overs[$index] = true;" ng-mouseleave="overs[$index] = false;" >
					 	<button class="btn btn-black" ng-class="{show: overs[$index]}" style="display:none;float:right;position:relative;">
		 					<span class="fa fa-pencil" aria-hidden="true"></span>
	 					</button>
					 	<div class="table-responsive center">
					 		<table class="table table-bordered">
					 			<tr>
					 				<td colspan="4" class="btn-black">
					 					<b>Detalles</b>
					 				</td>
					 			</tr>
					 			<tr class="">
					 				<td colspan="4">{{p.description}}</td>
					 			</tr>
					 			<tr class="btn-black">
					 				<td><b>SKU</b></td>
					 				<td><b>Size</b></td>
					 				<td><b>Price</b></td>
					 				<td><b>Stock</b></td>
					 			</tr>
					 			<tr ng-repeat="a in p.skuProduct">
					 				<td>{{a.sku}}</td>
							 		<td>{{a.sizeText}}</td>
							 		<td>{{a.price}}</td>
							 		<td>{{a.stock}}</td>
					 			</tr>
							 	<div class="" >
							 	</div>
					 		</table>
					 	</div>
					 </div>
				</div>
			</div>
		</div>
	</div>

<script type="text/javascript" src="${ContextPath}/r/js${DeployContext}/products.js" charset="utf-8"></script>