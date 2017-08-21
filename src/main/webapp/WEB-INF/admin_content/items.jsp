<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div id="page-wrapper" ng-controller="${ appname }Prod" ng-init="getAllProducts();getProductSizes();forms={};forms1={};overs= {};newsize = {};eachitem = {}; coveraux = {}; newformssize = {}; newformssizeimg = {};searchprodruct = {}">
	
	<input type="hidden" id="csrf" name="${_csrf.parameterName}" value="${_csrf.token}" />
	
	<div class="graphs">
		<h3 class="blank1 center"><s:message code="admin.items"/></h3>
			<div class="tab-content">
				<div class="jumbotron">
				
				<div class="center">
					<form action="" onsubmit="return false" ng-submit="getProductsByFilter();"  id="form-users" name="form-users" ng-model="formu" class="">
					  <ul class="list-inline">
					  	<li>
						  	<div class="form-group">
							    <label for="email"><i class="fa fa-barcode"></i>&nbsp;<b> SKU:</b></label>
						   	 	<input type="text" style="width:100%;" class="form-control" value="" name="" id="" ng-model="searchprodruct.sku"/>
							</div>
					  	</li>
					  	<li>
						  	<div class="form-group">
							    <label for="email"><i class="fa fa-file-text"></i>&nbsp;<b> <s:message code="admin.details" />:</b></label>
						   	 	<input type="text" style="width:100%;" class="form-control" value="" name="" id="" ng-model="searchprodruct.description"/>
							</div>
					  	</li>
					  	<li>
						  <div class="form-group">
						    <label for="pwd"><i class="fa fa-object-group"></i><b> <s:message code="admin.size"/>:</b></label> 
							<select class="form-control form-control-min" name="" id="" ng-model="searchprodruct.size">
								<option value="{{a.id}}"  ng-repeat="a in sizes">{{a.name}}</option>
							</select>
						  </div>
					  	</li>
					  </ul>
					  <div class="clearfix visible-xs"></div> 
					  <br class="visible-xs"/>
					  <div class="center">
					  	<button type="submit" class="btn-black btn"><span class="fa fa-search" aria-hidden="true"></span>&nbsp;<s:message code="admin.search"/></button>
					  </div>
					</form>
				</div>
				
					 <div ng-repeat="p in productos" class="container marginem" ng-mouseover="overs[p.id] = true;" ng-mouseleave="overs[p.id] = false;" >
					 	<legend><s:message code="admin.product" /> {{p.id}}</legend>
					 	<div class="table-responsive center" ng-init="productos[$index].indexado = $index;">
					 		<table class="table table-bordered">
					 			<tr class="tabletitulos">
					 				<td ng-class="{show: overs[p.id] || newsize[p.id]}" class="btn-black hidden-xs" style="display:none;color:rgba(255,255,255,0);">
					 					<span class="fa fa-pencil" aria-hidden="true"></span>
					 				</td>
					 				<td colspan="4">
					 					<b><s:message code="admin.details" /></b>
					 				</td>
					 			</tr>
					 			<tr class="">
					 				<td ng-class="{show: overs[p.id] || newsize[p.id]}" class="hidden-xs" style="display:none;color:rgba(255,255,255,0);">
					 					<span class="fa fa-user" aria-hidden="true"></span>
					 				</td>
					 				<td colspan="4">
					 					<div class="click hidden-xs" ng-hide="eachitem[p.id].description" ng-dblclick="eachitem[p.id].description = p.description;">{{p.description}}</div>
					 					<div class="visible-xs">{{p.description}}</div>
					 					<div ng-show="eachitem[p.id].description">
					 						<form action="" id="forms[p.id].description" name="forms[p.id].description" onsubmit="return false" ng-submit="updateProduct(productos[$index].indexado);eachitem[p.id].description = false;" >
						 						<div class="col-xs-12 col-md-10">
						 							<input ng-required="true" class="form-control form-control-min" type="text" ng-model="productos[p.indexado].description"/>
						 						</div>
						 						<div class="col-xs-6 col-md-1">
						 							<button type="submit" class="btn btn-success"><span class="fa fa-floppy-o" aria-hidden="true"></span></button>
						 						</div>
						 						<div class="col-xs-6 col-md-1">
						 							<a class="btn btn-danger" ng-click="p.description = eachitem[p.id].description;eachitem[p.id].description = false;">
						 								<span class="fa fa-times-circle" aria-hidden="true"></span>
						 							</a>
						 						</div>
					 						</form>
					 					</div>
					 				</td>
					 			</tr>
					 			<tr class="tabletitulos">
					 				<td ng-hide="newsize[p.id]" data-toggle="modal" data-target="#newYetibera" ng-class="{show: overs[p.id]}" ng-click="newsize[p.id] = true; newformssize.product = p.id;" class="btn-black hidden-xs" style="display:none;">
					 					<span class="fa fa-plus" aria-hidden="true"></span>
					 				</td>
					 				<td ng-show="newsize[p.id]" data-toggle="modal" data-target="#newYetibera" ng-class="{show:  overs[p.id] || newsize[p.id]}" ng-click="newsize[p.id] = false; newformssize.product = p.id;" class="btn-black hidden-xs" style="display:none;">
					 					<span class="fa fa-plus" aria-hidden="true"></span>
					 				</td>
					 				<td><b>SKU</b></td>
					 				<td><b><s:message code="admin.size" /></b></td>
					 				<td><b><s:message code="admin.price" /></b></td>
					 				<td><b><s:message code="admin.stock" /></b></td>
					 			</tr>
					 			<tr ng-repeat="a in p.skuProduct">
							 		<td ng-show="overs[p.id] || newsize[p.id]" class="hidden-xs">
							 			<span class="fa fa-minus-square" aria-hidden="true"></span>
							 		</td>
					 				<td>
					 					<div class="click hidden-xs" ng-hide="eachitem[a.id].sku" ng-dblclick="eachitem[a.id].sku = a.sku;">{{a.sku}}</div>
					 					<div class="visible-xs">{{a.sku}}</div>
					 					<div ng-show="eachitem[a.id].sku">
					 					<form action="" id="forms[a.id].sku" name="forms[a.id].sku" onsubmit="return false" ng-submit="updateProduct(p.indexado);eachitem[a.id].sku = false;" >
					 						<ul class="list-inline">
					 							<li><input ng-required="true" class="form-control form-control-min" type="text" ng-model="productos[p.indexado].skuProduct[$index].sku"/></li>
					 							<li><button class="btn btn-success" type="submit" ><span class="fa fa-floppy-o" aria-hidden="true"></span></button></li>
					 							<li><a class="btn btn-danger" ng-click="a.sku = eachitem[a.id].sku; eachitem[a.id].sku = false;"><span class="fa fa-times-circle" aria-hidden="true"></span></a></li>
					 						</ul>
					 					</form>
					 					</div>
					 				</td>
							 		<td>
					 					<div class="click hidden-xs" ng-hide="eachitem[a.id].sizeText" ng-dblclick="eachitem[a.id].sizeText = a.sizeText;">
					 						{{a.sizeText}}
					 					</div>
					 					<div class="visible-xs">{{a.sizeText}}</div>
					 					<div ng-show="eachitem[a.id].sizeText">
					 					<form action="" id="forms[a.id].skuproduct" name="forms[a.id].skuproduct" onsubmit="return false" ng-submit="updateProduct(p.indexado);eachitem[a.id].sizeText = false;" >
					 						<ul class="list-inline">
					 							<li>
					 								<select class="form-control form-control-min" name="" id=""
					 										ng-model="productos[p.indexado].skuProduct[$index].size"
					 										ng-init="productos[p.indexado].skuProduct[$index].size = productos[p.indexado].skuProduct[$index].size.toString();">
					 									<option value="{{a.id}}"  ng-repeat="a in sizes">{{a.name}}</option>
					 								</select>
					 							</li>
					 							<li><button class="btn btn-success" type="submit"><span class="fa fa-floppy-o" aria-hidden="true"></span></button></li>
					 							<li><a class="btn btn-danger" ng-click="a.sizeText = eachitem[a.id].sizeText;eachitem[a.id].sizeText = false;"><span class="fa fa-times-circle" aria-hidden="true"></span></a></li>
					 						</ul>
					 					</form>
					 					</div>
							 		</td>
							 		<td>
					 					<div class="click hidden-xs" ng-hide="eachitem[a.id].price" ng-dblclick="eachitem[a.id].price = a.price;">{{a.price}}</div>
					 					<div class="visible-xs">{{a.price}}</div>
					 					<div ng-show="eachitem[a.id].price">
					 					<form action="" id="forms[a.id].price" name="forms[a.id].price" onsubmit="return false" ng-submit="updateProduct(p.indexado,a.id);eachitem[a.id].price = false;" >
					 						<ul class="list-inline">
					 							<li><input ng-pattern="/^[0-9]+(\.[0-9]{1,4})?$/" class="form-control form-control-min" type="text" ng-model="productos[p.indexado].skuProduct[$index].price"/></li>
					 							<li><button class="btn btn-success" type="submit"><span class="fa fa-floppy-o" aria-hidden="true"></span></button></li>
					 							<li><a class="btn btn-danger" ng-click="a.price = eachitem[a.id].price; eachitem[a.id].price = false;"><span class="fa fa-times-circle" aria-hidden="true"></span></a></li>
					 						</ul>
					 					</form>
					 					</div>
							 		</td>
							 		<td>
					 					<div class="click hidden-xs" ng-hide="eachitem[a.id].stock" ng-dblclick="eachitem[a.id].stock = a.stock;">{{a.stock}}</div>
					 					<div class="visible-xs">{{a.stock}}</div>
					 					<div ng-show="eachitem[a.id].stock">
					 					<form action="" id="forms[a.id].stock" name="forms[a.id].stock" onsubmit="return false" ng-submit="updateProduct(p.indexado);eachitem[a.id].stock = false;" >
					 						<ul class="list-inline">
					 							<li><input type="number" class="form-control form-control-min" type="text" ng-model="productos[p.indexado].skuProduct[$index].stock"/></li>
					 							<li><button class="btn btn-success" type="submit"><span class="fa fa-floppy-o" aria-hidden="true"></span></button></li>
					 							<li><a class="btn btn-danger" ng-click="a.stock = eachitem[a.id].stock; eachitem[a.id].stock = false;"><span class="fa fa-times-circle" aria-hidden="true"></span></a></li>
					 						</ul>
					 					</form>
					 					</div>
							 		</td>
					 			</tr>
					 		</table>
					 	</div>
					 	<div class="marginem">
					 		<a href="#newYetiberaImg" data-toggle="modal" target="_blank" ng-click="newformssizeimg.id = p.id;" class="btn btn-black"><s:message code="admin.addPicture" /> <i class="fa fa-plus"></i></a>
					 	</div>
					 	<div class="container-img-muestras">
			 				<ul class="galeriaq list-inline" > <%-- ng-init="modalessss(p.productDetails,$index,p.id);">--%>
								<li class="col-xs-6 col-sm-4 col-md-3 col-lg-2 center" ng-repeat="a in p.productDetails">
									<div class="img-info">
										<button class="btn btn-black btn-delete hidden-xs" ng-click="deleteimg(p.indexado,$index,a.id);" ><i class="fa fa-times"></i></button>
										<a href="{{a.url}}" target="_blank" class="btn btn-edit btn-black"><i class="fa fa-eye"></i></a>
										<button class="btn btn-black btn-edit hidden"><i class="fa fa-pencil"></i></button>
									</div>
									<img class="click img-responsive img-thumbnail" src="{{a.url}}">
								</li>
							</ul>
						</div>
					 </div>
				</div>
			</div>
		</div>
		
		
	<!-- Modal -->
	<div id="newYetibera" class="modal fade" role="dialog">
	  <div class="modal-dialog">
	
	    <!-- Modal content-->
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	        <h4 class="modal-title"><s:message code="admin.tittleNewSKU"/></h4>
	      </div>
	      <div class="modal-body">
			<form action="" id="formsnewsize" name="formsnewsize" ng-model="formsnewsize" onsubmit="return false" ng-submit="createSkuProduct();">
				  <div class="form-group col-xs-12">
				    <label for="pwd"><i class="fa fa-barcode"></i><b>SKU:</b></label> 
				    <input ng-required="true" ng-model="newformssize.sku" class="form-control form-control-min" type="text" />
				  </div>
				  
				  <div class="col-xs-12">
					  <div class="form-group col-xs-12 col-sm-4">
					    <label for="pwd"><i class="fa fa-object-group"></i><b> <s:message code="admin.size"/>:</b></label> 
					    <select name="" id="" ng-model="newformssize.size" class="form-control form-control-min" ng-required="true">
			 				<option value="{{a.id}}"  ng-repeat="a in sizes">{{a.name}}</option>
			 			</select>
					  </div>
					  
					  <div class="form-group col-xs-12 col-sm-4">
					    <label for="pwd"><i class="fa fa-usd"></i><b> <s:message code="admin.price"/>:</b></label> 
					    <input  ng-pattern="/^[0-9]+(\.[0-9]{1,4})?$/"  ng-model="newformssize.price"  ng-required="true" class="form-control form-control-min" type="text" />
					  </div>
					  
					  <div class="form-group col-xs-12 col-sm-4">
					    <label for="pwd"><i class="fa fa-filter"></i><b> <s:message code="admin.stock"/>:</b></label>
			 			<input type="number"  ng-required="true" ng-model="newformssize.stock" class="form-control form-control-min" type="text" />
					  </div>
				  
				  	<button class="btn btn-black" type="submit"><s:message code="admin.save" /> <i class="fa fa-floppy-o" aria-hidden="true"></i></button>
				  </div>
			</form>
			<div class="clearfix"></div>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	      </div>
	    </div>
	
	  </div>
	</div>	
	
			
	<!-- Modal -->
	<div id="newYetiberaImg" class="modal fade" role="dialog">
	  <div class="modal-dialog">
	
	    <!-- Modal content-->
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	        <h4 class="modal-title"><s:message code="admin.tittleNewIMG"/></h4>
	      </div>
	      <div class="modal-body">
			<form action="" enctype="multipart/form-data"  id="formsnewsizeimg" name="formsnewsizeimg" ng-model="formsnewsizeimg" onsubmit="return false" ng-submit="addProductDetail();">
				  <div class="form-group col-xs-12">
				    <label for="pwd"><i class="fa fa-file-image-o"></i><b><s:message code="admin.Imagen" /></b></label> 
				    <input id="img" file-model="imagenp" ng-required="true" class="form-control form-control-min" type="file" />
				    <%--<input type="file" name="file" class="form-control form-control-min" onchange="angular.element(this).scope().uploadFile(this.files)"/> --%>
				  </div>
				  <buton class="btn btn-black" ng-click="addProductDetail();" type="submit"><s:message code="admin.save" /> <i class="fa fa-floppy-o" aria-hidden="true"></i></button>
			</form>
			<div class="clearfix"></div>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	      </div>
	    </div>
	
	  </div>
	</div>		
		
</div>



<script type="text/javascript" src="${ContextPath}/r/js${DeployContext}/products.js" charset="utf-8"></script>