<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div id="page-wrapper" ng-controller="${ appname }Prod" 
ng-init="<%--getAllProducts();--%>getProductSizes();forms={};forms1={};overs= {};newsize = {};eachitem = {}; coveraux = {}; newformssize = {}; newformssizeimg = {};searchprodruct = {}; addpro ={};getProductsByFilter();">
	
	<input type="hidden" id="csrf" name="${_csrf.parameterName}" value="${_csrf.token}" />
	
	<div class="graphs">
		<h3 class="blank1 center"><s:message code="admin.items"/></h3>
			<div class="tab-content">
				<div class="jumbotron">
				<ul class="nav nav-tabs container">
				  <li class="active"><a data-toggle="tab" href="#home"><span class="fa fa-search" aria-hidden="true"></span>&nbsp;<s:message code="admin.search"/></a></li>
				  <li><a href="#newProducto" data-toggle="modal" ><span class="fa fa-plus" aria-hidden="true"></span>&nbsp;<s:message code="admin.addproduct"/></a></li>
				</ul>
				
				<div class="tab-content container">
				  <div id="home" class="tab-pane fade in active">
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
									<option value=""></option>
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
				  </div>
				</div>
				
				
				<%-- tabla de items o articulos --%>
				<div class="clearfix"></div>
				
				<div class="marginem" ng-show="modificaFlag">
					<button ng-click="modificaFlag = false;" >cerrar</button>
					<div class="clearfix"></div>
					<div class="">
					 	<h3> <s:message code="admin.product" /> {{p.id}} </h3>
					 	
					 	<%-- Muestra producto --%>
					 	<div>
						 	<div class="table-responsive center">
						 		<table class="table table-bordered">
						 			<tr class="tabletitulos">					 				
						 				<td colspan="3">
						 					<b><s:message code="admin.title" /></b>
						 				</td>
						 				<td colspan="1">
						 					<b><s:message code="admin.releaseDate" /></b>
						 				</td>
						 			</tr>
						 			<tr class="">
						 				<td colspan="3">
						 					{{p.title}}
						 				</td>
						 				<td colspan="1">
						 					{{p.releaseDate}}
						 				</td>
						 			</tr>					 			
						 			<tr class="tabletitulos">
						 				<td colspan="4">
						 					<b><s:message code="admin.details" /></b>
						 				</td>
						 			</tr>
						 			<tr class="">
						 				<td class="" style="">
						 					ES
						 				</td>
						 				<td colspan="3">
						 					{{p.descriptionEs}}
						 				</td>
						 			</tr>
						 			<tr class="">
						 				<td class="" style="">
						 					EN
						 				</td>
						 				<td colspan="3">
						 					{{p.descriptionEn}}
						 				</td>
						 			</tr>
						 			<tr class="">
						 				<td class="">
						 					FR
						 				</td>
						 				<td colspan="3">
						 					{{p.descriptionFr}}
						 				</td>
						 			</tr>
						 			<tr class="tabletitulos">
						 				<td><b>SKU</b></td>
						 				<td><b><s:message code="admin.size" /></b></td>
						 				<td><b><s:message code="admin.price" /></b></td>
						 				<td><b><s:message code="admin.stock" /></b></td>
						 			</tr>
						 			<tr ng-repeat="a in p.skuProduct">
						 				<td>
						 					{{a.sku}}
						 				</td>
								 		<td>
								 			{{a.sizeText}}
								 		</td>
								 		<td>
								 			{{a.price}}
								 		</td>
								 		<td>
						 					{{a.stock}}
								 		</td>
						 			</tr>
						 		</table>
						 	</div>
						 </div>
					 	<%-- Fin mira producto --%>
					 	
					 	<%-- edita producto --%>
					 	<div>
						 	<div class="table-responsive center">
						 		<table class="table table-bordered">
						 			<tr class="tabletitulos">					 				
						 				<td colspan="3">
						 					<b><s:message code="admin.title" /></b>
						 				</td>
						 				<td colspan="1">
						 					<b><s:message code="admin.releaseDate" /></b>
						 				</td>
						 			</tr>
						 			<tr class="">
						 				<td colspan="3">
						 					<input ng-required="true" class="form-control form-control-min" type="text" ng-model="p.title"/>
						 				</td>
						 				<td colspan="1">
						 					<input class="form-control" ng-required="true" type="text" ng-model="pdate" ng-click="p.opened = true;"
								 			uib-datepicker-popup="dd-MM-yyyy" is-open="p.opened" datepicker-options="dateOptions" close-text="Close"  />
						 				</td>
						 			</tr>					 			
						 			<tr class="tabletitulos">
						 				<td colspan="4">
						 					<b><s:message code="admin.details" /></b>
						 				</td>
						 			</tr>
						 			<tr class="">
						 				<td class="" style="">
						 					ES
						 				</td>
						 				<td colspan="3">
						 					{{p.descriptionEs}}
						 				</td>
						 			</tr>
						 			<tr class="">
						 				<td class="" style="">
						 					EN
						 				</td>
						 				<td colspan="3">
						 					{{p.descriptionEn}}
						 				</td>
						 			</tr>
						 			<tr class="">
						 				<td class="">
						 					FR
						 				</td>
						 				<td colspan="3">
						 					{{p.descriptionFr}}
						 				</td>
						 			</tr>
						 			<tr class="tabletitulos">
						 				<td><b>SKU</b></td>
						 				<td><b><s:message code="admin.size" /></b></td>
						 				<td><b><s:message code="admin.price" /></b></td>
						 				<td><b><s:message code="admin.stock" /></b></td>
						 			</tr>
						 			<tr ng-repeat="a in p.skuProduct">
						 				<td>
						 					{{a.sku}}
						 				</td>
								 		<td>
								 			{{a.sizeText}}
								 		</td>
								 		<td>
								 			{{a.price}}
								 		</td>
								 		<td>
						 					{{a.stock}}
								 		</td>
						 			</tr>
						 		</table>
						 	</div>
					 	</div>
					 	<%-- Fin edita producto --%>
					 	
					 	<div class="marginem">
					 		<a href="#newYetiberaImg" data-toggle="modal" target="_blank" ng-click="newformssizeimg.id = p.id;" class="btn btn-black"><s:message code="admin.addPicture" /> <i class="fa fa-plus"></i></a>
					 	</div>
					 	<div class="">
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
					 	<%-- 
					 	<div class="table-responsive center" ng-init="productos[$index].indexado = $index;">
					 		<table class="table table-bordered">
					 			<tr class="tabletitulos">
					 				<td ng-class="{show: overs[p.id] || newsize[p.id]}" ng-click="removeProduct(p.id);" class="btn-black hidden-xs" style="display:none;">
					 					<div data-toggle="tooltip" title="ELiminar Producto">
					 						<span class="fa fa-minus-square" aria-hidden="true"></span>
					 					</div>
					 				</td>
					 				<td colspan="4">
					 					<b><s:message code="admin.title" /></b>
					 				</td>
					 			</tr>
					 			<tr class="">
					 				<td ng-class="{show: overs[p.id] || newsize[p.id]}" class="hidden-xs" style="display:none;color:rgba(255,255,255,0);">
					 					<span class="fa fa-user" aria-hidden="true"></span>
					 				</td>
					 				<td colspan="4">
					 					<div class="click hidden-xs" ng-hide="eachitem[p.id].titulo" ng-dblclick="eachitem[p.id].titulo = p.title;">{{p.title}}</div>
					 					<div class="visible-xs">{{p.title}}</div>
					 					<div ng-show="eachitem[p.id].titulo">
					 						<form action="" id="forms[p.id].titulo" name="forms[p.id].titulo" onsubmit="return false" ng-submit="updateProduct(productos[$index].indexado);eachitem[p.id].titulo = false;" >
						 						<div class="col-xs-12 col-md-10">
						 							<input ng-required="true" class="form-control form-control-min" type="text" ng-model="productos[p.indexado].title"/>
						 						</div>
						 						<div class="col-xs-6 col-md-1">
						 							<button type="submit" class="btn btn-success"><span class="fa fa-floppy-o" aria-hidden="true"></span></button>
						 						</div>
						 						<div class="col-xs-6 col-md-1">
						 							<a class="btn btn-danger" ng-click="p.title = eachitem[p.id].titulo;eachitem[p.id].titulo = false;">
						 								<span class="fa fa-times-circle" aria-hidden="true"></span>
						 							</a>
						 						</div>
					 						</form>
					 					</div>
					 				</td>
					 			</tr>
					 			
					 			<tr class="tabletitulos">
					 				<td ng-class="{show: overs[p.id] || newsize[p.id]}" class="hidden-xs" style="display:none;">
					 					<div style="color:black;">
					 						<span class="fa fa-minus-square" aria-hidden="true"></span>
					 					</div>
					 				</td>
					 				<td colspan="4">
					 					<b><s:message code="admin.details" /></b>
					 				</td>
					 			</tr>
					 			<tr class="">
					 				<td ng-class="{show: overs[p.id] || newsize[p.id]}" class="hidden-xs" style="">
					 					ES
					 				</td>
					 				<td colspan="4">
					 					<div class="click hidden-xs" ng-hide="eachitem[p.id].descriptionEs" ng-dblclick="eachitem[p.id].descriptionEs = p.descriptionEs;"><div ng-if="p.descriptionEs == NULL"><div ng-init="p.descriptionEs = true;"></div></div>{{p.descriptionEs}} <a href="javascript:void(0);" ng-click="eachitem[p.id].descriptionEs = p.descriptionEs;" class="btn btn-black hidden">cambia</a></div>
					 					<div class="visible-xs">&nbsp;{{p.descriptionEs}}</div>
					 					<div ng-show="eachitem[p.id].descriptionEs">
					 						<form action="" id="forms[p.id].descriptionEs" name="forms[p.id].descriptionEs" onsubmit="return false" ng-submit="updateProduct(productos[$index].indexado);eachitem[p.id].descriptionEs = false;" >
						 						<div class="col-xs-12 col-md-10">
						 							<input ng-required="true" class="form-control form-control-min" type="text" ng-model="productos[p.indexado].descriptionEs"/>
						 						</div>
						 						<div class="col-xs-6 col-md-1">
						 							<button type="submit" class="btn btn-success"><span class="fa fa-floppy-o" aria-hidden="true"></span></button>
						 						</div>
						 						<div class="col-xs-6 col-md-1">
						 							<a class="btn btn-danger" ng-click="p.descriptionEs = eachitem[p.id].descriptionEs;eachitem[p.id].descriptionEs = false;">
						 								<span class="fa fa-times-circle" aria-hidden="true"></span>
						 							</a>
						 						</div>
					 						</form>
					 					</div>
					 				</td>
					 			</tr>
					 			<tr class="">
					 				<td ng-class="{show: overs[p.id] || newsize[p.id]}" class="hidden-xs" style="">
					 					EN
					 				</td>
					 				<td colspan="4">
					 					<div class="click hidden-xs" ng-hide="eachitem[p.id].descriptionEn" ng-dblclick="eachitem[p.id].descriptionEn = p.descriptionEn;"><div ng-if="p.descriptionEn == NULL"><div ng-init="p.descriptionEn = true;"></div></div>{{p.descriptionEn}}</div>
					 					<div class="visible-xs">{{p.descriptionEn}}</div>
					 					<div ng-show="eachitem[p.id].descriptionEn">
					 						<form action="" id="forms[p.id].descriptionEn" name="forms[p.id].descriptionEn" onsubmit="return false" ng-submit="updateProduct(productos[$index].indexado);eachitem[p.id].descriptionEn = false;" >
						 						<div class="col-xs-12 col-md-10">
						 							<input ng-required="true" class="form-control form-control-min" type="text" ng-model="productos[p.indexado].descriptionEn"/>
						 						</div>
						 						<div class="col-xs-6 col-md-1">
						 							<button type="submit" class="btn btn-success"><span class="fa fa-floppy-o" aria-hidden="true"></span></button>
						 						</div>
						 						<div class="col-xs-6 col-md-1">
						 							<a class="btn btn-danger" ng-click="p.description = eachitem[p.id].descriptionEn;eachitem[p.id].descriptionEn = false;">
						 								<span class="fa fa-times-circle" aria-hidden="true"></span>
						 							</a>
						 						</div>
					 						</form>
					 					</div>
					 				</td>
					 			</tr>
					 			<tr class="">
					 				<td ng-class="{show: overs[p.id] || newsize[p.id]}" class="hidden-xs">
					 					FR
					 				</td>
					 				<td colspan="4">
					 					<div class="click hidden-xs" ng-hide="eachitem[p.id].descriptionFr" ng-dblclick="eachitem[p.id].descriptionFr = p.descriptionFr;"><div ng-if="p.descriptionFr == NULL"><div ng-init="p.descriptionFr = true;"></div></div>{{p.descriptionFr}}</div>
					 					<div class="visible-xs">{{p.descriptionFr}}</div>
					 					<div ng-show="eachitem[p.id].descriptionFr">
					 						<form action="" id="forms[p.id].descriptionFr" name="forms[p.id].descriptionFr" onsubmit="return false" ng-submit="updateProduct(productos[$index].indexado);eachitem[p.id].descriptionFr = false;" >
						 						<div class="col-xs-12 col-md-10">
						 							<input ng-required="true" class="form-control form-control-min" type="text" ng-model="productos[p.indexado].descriptionFr"/>
						 						</div>
						 						<div class="col-xs-6 col-md-1">
						 							<button type="submit" class="btn btn-success"><span class="fa fa-floppy-o" aria-hidden="true"></span></button>
						 						</div>
						 						<div class="col-xs-6 col-md-1">
						 							<a class="btn btn-danger" ng-click="p.descriptionFr = eachitem[p.id].descriptionFr;eachitem[p.id].descriptionFr = false;">
						 								<span class="fa fa-times-circle" aria-hidden="true"></span>
						 							</a>
						 						</div>
					 						</form>
					 					</div>
					 				</td>
					 			</tr>
					 			<tr class="tabletitulos">
					 				<td ng-hide="newsize[p.id]" data-toggle="modal" data-target="#newYetibera" ng-class="{show: overs[p.id]}" ng-click="newsize[p.id] = true; newformssize.product = p.id;" class="btn-black hidden-xs" style="display:none;">
					 					<div data-toggle="tooltip" title="Nuevo SKU">
					 						<span class="fa fa-plus" aria-hidden="true"></span>
					 					</div>
					 				</td>
					 				<td ng-show="newsize[p.id]" data-toggle="modal" data-target="#newYetibera" ng-class="{show:  overs[p.id] || newsize[p.id]}" ng-click="newsize[p.id] = false; newformssize.product = p.id;" class="btn-black hidden-xs" style="display:none;">
					 					<div data-toggle="tooltip" title="Nuevo SKU">
					 						<span class="fa fa-plus" aria-hidden="true"></span>
					 					</div>
					 				</td>
					 				<td><b>SKU</b></td>
					 				<td><b><s:message code="admin.size" /></b></td>
					 				<td><b><s:message code="admin.price" /></b></td>
					 				<td><b><s:message code="admin.stock" /></b></td>
					 			</tr>
					 			<tr ng-repeat="a in p.skuProduct">
							 		<td ng-show="overs[p.id] || newsize[p.id]" class="btn-black hidden-xs click" ng-click="removeSkuProduct(a.id);">
							 			<div  data-toggle="tooltip" title="ELiminar SKU"  data-placement="right">
							 				<span class="fa fa-minus-square" aria-hidden="true"></span>
							 			</div>
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
					 	--%>
					 </div>
					
				</div>
				
				<div class="marginem" ng-show="listaFlag">
					<div class="table-responsive">
						<table class="table table-bordered">
							<tbody>
								<tr class="tabletitulos">
									<td><s:message code="admin.delete" /></td>
									<td><s:message code="admin.modify" /></td>
									<td><s:message code="admin.show" /></td>
									<td><s:message code="admin.product" /></td>
								</tr>
							</tbody>
							<tbody ng-repeat="productoIndividual in productos" class="text-center">
								<tr ng-init="productoIndividual.show = false;">
									<td class="click"><i class="fa fa-times"></i></td>
									<td class="click" ng-click="activaModItem(productoIndividual);"><i class="fa fa-pencil"></i></td>
									<td class="click" ng-hide="productoIndividual.show" ng-click="productoIndividual.show = true;"><i class="fa fa-eye"></i></td>
									<td class="click" ng-show="productoIndividual.show" ng-click="productoIndividual.show = false;"><i class="fa fa-eye-slash"></i></td>
									<td>{{productoIndividual.title}} {{productoIndividual.show}}</td>
								</tr>
								<tr ng-show="productoIndividual.show">
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
				<%-- Fin tabla de items o articulos --%>
				
				<div class="clearfix"></div>
				
					 <div ng-repeat="p in productos" class="container marginem hidden" ng-mouseover="overs[p.id] = true;" ng-mouseleave="overs[p.id] = false;" >
					 	<legend><s:message code="admin.product" /> {{p.id}}</legend>
					 	<div class="table-responsive center" ng-init="productos[$index].indexado = $index;">
					 		<table class="table table-bordered">
					 		
					 			<tr class="tabletitulos">
					 				<td ng-class="{show: overs[p.id] || newsize[p.id]}" ng-click="removeProduct(p.id);" class="btn-black hidden-xs" style="display:none;">
					 					<div data-toggle="tooltip" title="ELiminar Producto">
					 						<span class="fa fa-minus-square" aria-hidden="true"></span>
					 					</div>
					 				</td>
					 				<td colspan="4">
					 					<b><s:message code="admin.title" /></b>
					 				</td>
					 			</tr>
					 			<tr class="">
					 				<td ng-class="{show: overs[p.id] || newsize[p.id]}" class="hidden-xs" style="display:none;color:rgba(255,255,255,0);">
					 					<span class="fa fa-user" aria-hidden="true"></span>
					 				</td>
					 				<td colspan="4">
					 					<div class="click hidden-xs" ng-hide="eachitem[p.id].titulo" ng-dblclick="eachitem[p.id].titulo = p.title;">{{p.title}}</div>
					 					<div class="visible-xs">{{p.title}}</div>
					 					<div ng-show="eachitem[p.id].titulo">
					 						<form action="" id="forms[p.id].titulo" name="forms[p.id].titulo" onsubmit="return false" ng-submit="updateProduct(productos[$index].indexado);eachitem[p.id].titulo = false;" >
						 						<div class="col-xs-12 col-md-10">
						 							<input ng-required="true" class="form-control form-control-min" type="text" ng-model="productos[p.indexado].title"/>
						 						</div>
						 						<div class="col-xs-6 col-md-1">
						 							<button type="submit" class="btn btn-success"><span class="fa fa-floppy-o" aria-hidden="true"></span></button>
						 						</div>
						 						<div class="col-xs-6 col-md-1">
						 							<a class="btn btn-danger" ng-click="p.title = eachitem[p.id].titulo;eachitem[p.id].titulo = false;">
						 								<span class="fa fa-times-circle" aria-hidden="true"></span>
						 							</a>
						 						</div>
					 						</form>
					 					</div>
					 				</td>
					 			</tr>
					 			
					 			<tr class="tabletitulos">
					 				<td ng-class="{show: overs[p.id] || newsize[p.id]}" class="hidden-xs" style="display:none;">
					 					<div style="color:black;">
					 						<span class="fa fa-minus-square" aria-hidden="true"></span>
					 					</div>
					 				</td>
					 				<td colspan="4">
					 					<b><s:message code="admin.details" /></b>
					 				</td>
					 			</tr>
					 			<tr class="">
					 				<td ng-class="{show: overs[p.id] || newsize[p.id]}" class="hidden-xs" style="">
					 					ES
					 				</td>
					 				<td colspan="4">
					 					<div class="click hidden-xs" ng-hide="eachitem[p.id].descriptionEs" ng-dblclick="eachitem[p.id].descriptionEs = p.descriptionEs;"><div ng-if="p.descriptionEs == NULL"><div ng-init="p.descriptionEs = true;"></div></div>{{p.descriptionEs}} <a href="javascript:void(0);" ng-click="eachitem[p.id].descriptionEs = p.descriptionEs;" class="btn btn-black hidden">cambia</a></div>
					 					<div class="visible-xs">&nbsp;{{p.descriptionEs}}</div>
					 					<div ng-show="eachitem[p.id].descriptionEs">
					 						<form action="" id="forms[p.id].descriptionEs" name="forms[p.id].descriptionEs" onsubmit="return false" ng-submit="updateProduct(productos[$index].indexado);eachitem[p.id].descriptionEs = false;" >
						 						<div class="col-xs-12 col-md-10">
						 							<input ng-required="true" class="form-control form-control-min" type="text" ng-model="productos[p.indexado].descriptionEs"/>
						 						</div>
						 						<div class="col-xs-6 col-md-1">
						 							<button type="submit" class="btn btn-success"><span class="fa fa-floppy-o" aria-hidden="true"></span></button>
						 						</div>
						 						<div class="col-xs-6 col-md-1">
						 							<a class="btn btn-danger" ng-click="p.descriptionEs = eachitem[p.id].descriptionEs;eachitem[p.id].descriptionEs = false;">
						 								<span class="fa fa-times-circle" aria-hidden="true"></span>
						 							</a>
						 						</div>
					 						</form>
					 					</div>
					 				</td>
					 			</tr>
					 			<tr class="">
					 				<td ng-class="{show: overs[p.id] || newsize[p.id]}" class="hidden-xs" style="">
					 					EN
					 				</td>
					 				<td colspan="4">
					 					<div class="click hidden-xs" ng-hide="eachitem[p.id].descriptionEn" ng-dblclick="eachitem[p.id].descriptionEn = p.descriptionEn;"><div ng-if="p.descriptionEn == NULL"><div ng-init="p.descriptionEn = true;"></div></div>{{p.descriptionEn}}</div>
					 					<div class="visible-xs">{{p.descriptionEn}}</div>
					 					<div ng-show="eachitem[p.id].descriptionEn">
					 						<form action="" id="forms[p.id].descriptionEn" name="forms[p.id].descriptionEn" onsubmit="return false" ng-submit="updateProduct(productos[$index].indexado);eachitem[p.id].descriptionEn = false;" >
						 						<div class="col-xs-12 col-md-10">
						 							<input ng-required="true" class="form-control form-control-min" type="text" ng-model="productos[p.indexado].descriptionEn"/>
						 						</div>
						 						<div class="col-xs-6 col-md-1">
						 							<button type="submit" class="btn btn-success"><span class="fa fa-floppy-o" aria-hidden="true"></span></button>
						 						</div>
						 						<div class="col-xs-6 col-md-1">
						 							<a class="btn btn-danger" ng-click="p.description = eachitem[p.id].descriptionEn;eachitem[p.id].descriptionEn = false;">
						 								<span class="fa fa-times-circle" aria-hidden="true"></span>
						 							</a>
						 						</div>
					 						</form>
					 					</div>
					 				</td>
					 			</tr>
					 			<tr class="">
					 				<td ng-class="{show: overs[p.id] || newsize[p.id]}" class="hidden-xs">
					 					FR
					 				</td>
					 				<td colspan="4">
					 					<div class="click hidden-xs" ng-hide="eachitem[p.id].descriptionFr" ng-dblclick="eachitem[p.id].descriptionFr = p.descriptionFr;"><div ng-if="p.descriptionFr == NULL"><div ng-init="p.descriptionFr = true;"></div></div>{{p.descriptionFr}}</div>
					 					<div class="visible-xs">{{p.descriptionFr}}</div>
					 					<div ng-show="eachitem[p.id].descriptionFr">
					 						<form action="" id="forms[p.id].descriptionFr" name="forms[p.id].descriptionFr" onsubmit="return false" ng-submit="updateProduct(productos[$index].indexado);eachitem[p.id].descriptionFr = false;" >
						 						<div class="col-xs-12 col-md-10">
						 							<input ng-required="true" class="form-control form-control-min" type="text" ng-model="productos[p.indexado].descriptionFr"/>
						 						</div>
						 						<div class="col-xs-6 col-md-1">
						 							<button type="submit" class="btn btn-success"><span class="fa fa-floppy-o" aria-hidden="true"></span></button>
						 						</div>
						 						<div class="col-xs-6 col-md-1">
						 							<a class="btn btn-danger" ng-click="p.descriptionFr = eachitem[p.id].descriptionFr;eachitem[p.id].descriptionFr = false;">
						 								<span class="fa fa-times-circle" aria-hidden="true"></span>
						 							</a>
						 						</div>
					 						</form>
					 					</div>
					 				</td>
					 			</tr>
					 			<tr class="tabletitulos">
					 				<td ng-hide="newsize[p.id]" data-toggle="modal" data-target="#newYetibera" ng-class="{show: overs[p.id]}" ng-click="newsize[p.id] = true; newformssize.product = p.id;" class="btn-black hidden-xs" style="display:none;">
					 					<div data-toggle="tooltip" title="Nuevo SKU">
					 						<span class="fa fa-plus" aria-hidden="true"></span>
					 					</div>
					 				</td>
					 				<td ng-show="newsize[p.id]" data-toggle="modal" data-target="#newYetibera" ng-class="{show:  overs[p.id] || newsize[p.id]}" ng-click="newsize[p.id] = false; newformssize.product = p.id;" class="btn-black hidden-xs" style="display:none;">
					 					<div data-toggle="tooltip" title="Nuevo SKU">
					 						<span class="fa fa-plus" aria-hidden="true"></span>
					 					</div>
					 				</td>
					 				<td><b>SKU</b></td>
					 				<td><b><s:message code="admin.size" /></b></td>
					 				<td><b><s:message code="admin.price" /></b></td>
					 				<td><b><s:message code="admin.stock" /></b></td>
					 			</tr>
					 			<tr ng-repeat="a in p.skuProduct">
							 		<td ng-show="overs[p.id] || newsize[p.id]" class="btn-black hidden-xs click" ng-click="removeSkuProduct(a.id);">
							 			<div  data-toggle="tooltip" title="ELiminar SKU"  data-placement="right">
							 				<span class="fa fa-minus-square" aria-hidden="true"></span>
							 			</div>
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
	<div id="newProducto" class="modal fade" role="dialog">
	  <div class="modal-dialog">
	
	    <!-- Modal content-->
	    <div class="modal-content" ng-init="addpro.skuProduct = [];">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	        <h4 class="modal-title"><s:message code="admin.tittleNewProduct"/></h4>
	      </div>
	      <div class="modal-body">
			<form action="" id="addproform" name="addproform" ng-model="addproform" onsubmit="return false" ng-submit="createProduct();">
				
				<input type="hidden" value="{{size}}" id="siss" />
				
				<div class="form-group col-xs-12">
				    <label for="pwd"><i class="fa fa-file-text"></i><b>&nbsp;<s:message code="admin.title" />:</b></label> 
				    <input ng-required="true" ng-model="addpro.title" class="form-control form-control-min" type="text" />
				</div>
				
				<div class="form-group col-xs-12">
				    <label for="pwd"><i class="fa fa-file-text"></i><b>&nbsp;<s:message code="admin.details" />:</b></label> 
				    <input ng-required="true" ng-model="addpro.description" class="form-control form-control-min" type="text" />
				</div>
			  	
				<div class="col-xs-12 center hidden">
					<label for="pwd"><i class="fa fa-"></i><b>&nbsp;<s:message code="admin.tittleNewSKU" /></b></label>
				</div>
				<div class="clearfix"></div>

			  	<ul class="nav nav-pills" id="menusmodal">
				  	<li class="btn-black" ng-class="{active: $index == 0}" ng-repeat="z in sizes"><a data-toggle="pill" href="#h{{z.id}}">{{z.name}}</a></li>
				</ul>
				
				<div class="tab-content"> 
					<div id="h{{z.id}}" ng-repeat="z in sizes" class="tab-pane" ng-class="{active: $index == 0}">
					    <div class="col-xs-12">
						  <div class="table-responsive">
							  <table class="table table-striped table-over extra">
							  	<tr>
							  		<th><i class="fa fa-barcode"></i><b> SKU</b></th>
							  		<th class=""><i class="fa fa-object-group"></i><b> <s:message code="admin.size"/></b></th>
							  		<th><i class="fa fa-usd"></i><b> <s:message code="admin.price"/></b></th>
							  		<th><i class="fa fa-filter"></i><b> <s:message code="admin.stock"/></b></th>
							  	</tr>
							  	<tr class="">
							  		<td>
									    <input ng-model="addpro.skuProduct[$index].sku" class="form-control form-control-min" type="text" />
									</td>
							  		<td class="">
							  		    <select name="" ng-model="addpro.skuProduct[$index].size" id="" class="form-control form-control-min" ng-disabled="true" >
							 				<option value="{{z.id}}" selected="selected" ng-init="addpro.skuProduct[$index].size = z.id;">{{z.name}}</option>
							 				<option value=""></option>
							 			</select>
									</td>
							  		<td>
							  		    <input ng-model="addpro.skuProduct[$index].price" class="form-control form-control-min" type="text" />
									</td>
							  		<td>
							  			<input ng-model="addpro.skuProduct[$index].stock" type="number" class="form-control form-control-min" type="text" />
									 </td>
							  	</tr>
							</table>
						</div>
					  </div>
				  </div>
				</div>
				
				<div class="clearfix" style="margin-bottom:10px;"></div>
	
				<div class="col-xs-12">
					<label for="pwd"><i class="fa fa-file-image-o"></i><b>&nbsp;<s:message code="admin.Imagen" /></b></label>
				</div>
				<div class="clearfix"></div>
				<div id="dragandrophandler">
					<label class="click" for="fileqwer[]">
						<s:message code="admin.dragdropfiles" />
						<input onchange="masfiles();" id="fileqwer[]" name="fileqwer[]" type="file" class="hidden" multiple />
					</label>
				</div>
				<div id="statusId"></div>
	
				<div class="clearfix"></div>	
				<br />
				<div>
			  		<button class="btn btn-black" type="submit"><s:message code="admin.save" /> <i class="fa fa-floppy-o" aria-hidden="true"></i></button>
				</div>
			</form>
			<div class="col-xs-12">
				<a href="" ng-click="muchasimg()" class="hidden btn btn-black">muchas imagenes </a>
			</div>
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