<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div id="page-wrapper" ng-controller="${ appname }Prod" ng-init="getAllProducts();getProductSizes();overs= {};newsize = {};eachitem = {};">
	<div class="graphs">
		<h3 class="blank1 center"><s:message code="admin.report"/></h3>
			<div class="tab-content">
				<div class="jumbotron">
					 <div ng-repeat="p in productos" class="container marginem" ng-mouseover="overs[p.id] = true;" ng-mouseleave="overs[p.id] = false;" >
					 	<legend>Producto {{p.id}}</legend>
					 	<div class="table-responsive center" ng-init="productos[$index].indexado = $index;">
					 		<table class="table table-bordered">
					 			<tr class="tabletitulos">
					 				<td ng-class="{show: overs[p.id] || newsize[p.id]}" class="btn-black hidden-xs" style="display:none;">
					 					<span class="fa fa-pencil" aria-hidden="true"></span>
					 				</td>
					 				<td colspan="4">
					 					<b>Detalles</b>
					 				</td>
					 			</tr>
					 			<tr class="">
					 				<td ng-class="{show: overs[p.id] || newsize[p.id]}" class="hidden-xs" style="display:none;color:rgba(255,255,255,0);">
					 					<span class="fa fa-user" aria-hidden="true"></span>
					 				</td>
					 				<td colspan="4">
					 					<div class="click hidden-xs" ng-hide="eachitem[p.description].description" ng-dblclick="eachitem[p.description].description = true;">{{p.description}}</div>
					 					<div class="visible-xs">{{p.description}}</div>
					 					<div ng-show="eachitem[p.description].description">
					 						<div class="col-xs-12 col-md-10">
					 							<input class="form-control form-control-min" type="text" ng-model="productos[p.indexado].description"/>
					 						</div>
					 						<div class="col-xs-6 col-md-1">
					 							<button class="btn btn-success"><span class="fa fa-floppy-o" aria-hidden="true"></span></button>
					 						</div>
					 						<div class="col-xs-6 col-md-1">
					 							<button class="btn btn-danger" ng-click="eachitem[p.description].description = false;">
					 								<span class="fa fa-times-circle" aria-hidden="true"></span>
					 							</button>
					 						</div>
					 					</div>
					 				</td>
					 			</tr>
					 			<tr class="tabletitulos">
					 				<td ng-hide="newsize[p.id]" ng-class="{show: overs[p.id]}" ng-click="newsize[p.id] = true;" class="btn-black hidden-xs" style="display:none;">
					 					<span class="fa fa-plus" aria-hidden="true"></span>
					 				</td>
					 				<td ng-show="newsize[p.id]" ng-class="{show:  overs[p.id] || newsize[p.id]}" ng-click="newsize[p.id] = false;" class="btn-black hidden-xs" style="display:none;">
					 					<span class="fa fa-times-circle" aria-hidden="true"></span>
					 				</td>
					 				<td><b>SKU</b></td>
					 				<td><b>Size</b></td>
					 				<td><b>Price</b></td>
					 				<td><b>Stock</b></td>
					 			</tr>
					 			<tr ng-repeat="a in p.skuProduct">
							 		<td ng-show="overs[p.id] || newsize[p.id]" class="hidden-xs">
							 			<span class="fa fa-minus-square" aria-hidden="true"></span>
							 		</td>
					 				<td>
					 					<div class="click hidden-xs" ng-hide="eachitem[a.id].sku" ng-dblclick="eachitem[a.id].sku = true;">{{a.sku}}</div>
					 					<div class="visible-xs">{{a.sku}}</div>
					 					<div ng-show="eachitem[a.id].sku">
					 						<ul class="list-inline">
					 							<li><input class="form-control form-control-min" type="text" ng-model="productos[p.indexado].skuProduct[$index].sku"/></li>
					 							<li><button class="btn btn-success"><span class="fa fa-floppy-o" aria-hidden="true"></span></button></li>
					 							<li><button class="btn btn-danger" ng-click="eachitem[a.id].sku = false;"><span class="fa fa-times-circle" aria-hidden="true"></span></button></li>
					 						</ul>
					 					</div>
					 				</td>
							 		<td>
					 					<div class="click hidden-xs" ng-hide="eachitem[a.id].sizeText" ng-dblclick="eachitem[a.id].sizeText = true;">{{a.sizeText}}</div>
					 					<div class="visible-xs">{{a.sizeText}}</div>
					 					<div ng-show="eachitem[a.id].sizeText">
					 						<ul class="list-inline">
					 							<li>
					 								<select class="form-control form-control-min" name="" id=""
					 										ng-model="productos[p.indexado].skuProduct[$index].size"
					 										ng-init="productos[p.indexado].skuProduct[$index].size = productos[p.indexado].skuProduct[$index].size.toString();">
					 									<option value="{{a.idTalla}}"  ng-repeat="a in sizes">{{a.tallaText}}</option>
					 								</select>
					 							</li>
					 							<li><button class="btn btn-success"><span class="fa fa-floppy-o" aria-hidden="true"></span></button></li>
					 							<li><button class="btn btn-danger" ng-click="eachitem[a.id].sizeText = false;"><span class="fa fa-times-circle" aria-hidden="true"></span></button></li>
					 						</ul>
					 					</div>
							 		</td>
							 		<td>
					 					<div class="click hidden-xs" ng-hide="eachitem[a.id].price" ng-dblclick="eachitem[a.id].price = true;">{{a.price}}</div>
					 					<div class="visible-xs">{{a.price}}</div>
					 					<div ng-show="eachitem[a.id].price">
					 						<ul class="list-inline">
					 							<li><input class="form-control form-control-min" type="text" ng-model="productos[p.indexado].skuProduct[$index].price"/></li>
					 							<li><button class="btn btn-success"><span class="fa fa-floppy-o" aria-hidden="true"></span></button></li>
					 							<li><button class="btn btn-danger" ng-click="eachitem[a.id].price = false;"><span class="fa fa-times-circle" aria-hidden="true"></span></button></li>
					 						</ul>
					 					</div>
							 		</td>
							 		<td>
					 					<div class="click hidden-xs" ng-hide="eachitem[a.id].stock" ng-dblclick="eachitem[a.id].stock = true;">{{a.stock}}</div>
					 					<div class="visible-xs">{{a.stock}}</div>
					 					<div ng-show="eachitem[a.id].stock">
					 						<ul class="list-inline">
					 							<li><input class="form-control form-control-min" type="text" ng-model="productos[p.indexado].skuProduct[$index].stock"/></li>
					 							<li><button class="btn btn-success"><span class="fa fa-floppy-o" aria-hidden="true"></span></button></li>
					 							<li><button class="btn btn-danger" ng-click="eachitem[a.id].stock = false;"><span class="fa fa-times-circle" aria-hidden="true"></span></button></li>
					 						</ul>
					 					</div>
							 		</td>
					 			</tr>
					 			<tr ng-show="newsize[p.id]">
							 		<td class="" style="" ng-click="newsize[p.id] = false;">
							 			<span class="form-control btn-black fa fa-floppy-o" aria-hidden="true"></span>
							 		</td>
					 				<td><input class="form-control form-control-min" type="text" /></td>
							 		<td>
							 			<select name="" id="" class="form-control form-control-min">
							 				<option value="{{a.idTalla}}"  ng-repeat="a in sizes">{{a.tallaText}}</option>
							 			</select>
							 		</td>
							 		<td><input class="form-control form-control-min" type="text" /></td>
							 		<td><input class="form-control form-control-min" type="text" /></td>
					 			</tr>
					 			<tr>
					 				<td ng-class="{show: overs[p.id] || newsize[p.id]}" class="hidden-xs" style="display:none;color:rgba(255,255,255,0);">
					 					<span class="fa fa-user" aria-hidden="true"></span>
					 				</td>
					 				<td colspan="4">
										<div>
											<div  class="coverflow">
												<img src="http://placehold.it/350x350" />
												<img src="http://placehold.it/350x350/E8117F/FFFFFF" />
												<img src="http://placehold.it/350x350" />
												<img src="http://placehold.it/350x350/E8117F/FFFFFF" />
												<img src="http://placehold.it/350x350" />
											</div>
										</div>
										<div id="coverflow-slider"></div>
					 				</td>
					 				
					 			</tr>
					 		</table>
					 	</div>
					 </div>
				</div>
			</div>
		</div>
	</div>
	
	
<div>
	<div  class="coverflow">
		<img src="http://placehold.it/350x350" />
		<img src="http://placehold.it/350x350/E8117F/FFFFFF" />
		<img src="http://placehold.it/350x350" />
		<img src="http://placehold.it/350x350/E8117F/FFFFFF" />
		<img src="http://placehold.it/350x350" />
	</div>
</div>

<div id="coverflow-slider"></div>

<style>
	.ui-coverflow-wrapper{
	    height:400px;
	    margin-top: 100px;
	}
	.ui-state-active{
	    border:0px;
	}
</style>

<link type='text/css' rel="stylesheet" href="${ContextPath}/r/css/coverflow.css" /> 
<script type="text/javascript" src="${ContextPath}/r/js/coverflow.standalone.min.js"></script>
<script type="text/javascript" src="${ContextPath}/r/js${DeployContext}/products.js" charset="utf-8"></script>
<!--<link type='text/css' rel="stylesheet" href="https://cdn.rawgit.com/coverflowjs/coverflow/master/dist/coverflow.css" />
<script type="text/javascript" src="https://cdn.rawgit.com/coverflowjs/coverflow/master/dist/coverflow.min.js" charset="utf*-8"></script> --> 
<script>
$(function() {

    $('.coverflow').coverflow({
        active: 2,
        select: function(event, ui){
            console.log('here');
        }
    });
    
    $('.coverflow img').click(function() {
        if( ! $(this).hasClass('ui-state-active')){
            return;
        }
        
        $('.coverflow').coverflow('next');
        
        
    });
    
});
</script>