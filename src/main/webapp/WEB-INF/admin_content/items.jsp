<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div id="page-wrapper" ng-controller="${ appname }Prod" 
ng-init="<%--getAllProducts();--%>getProductSizes();forms={};forms1={};overs= {};newsize = {};eachitem = {}; coveraux = {}; newformssize = {}; newformssizeimg = {};searchprodruct = {}; addpro ={};getProductsByFilter();">
	
	<input type="hidden" id="csrf" name="${_csrf.parameterName}" value="${_csrf.token}" />
	
	<div class="graphs">
		<h3 class="blank1 center"><s:message code="admin.items"/></h3>
			<div class="tab-content">
				<div class="jumbotron">
				<ul class="nav nav-tabs container" ng-show="listaFlag">
				  <li class="active"><a data-toggle="tab" href="#home"><span class="fa fa-search" aria-hidden="true"></span>&nbsp;<s:message code="admin.search"/></a></li>
				  <li><a href="#newProducto" data-toggle="modal" ><span class="fa fa-plus" aria-hidden="true"></span>&nbsp;<s:message code="admin.addproduct"/></a></li>
				</ul>
				
				<div class="tab-content container" ng-show="listaFlag">
				  <div id="home" class="tab-pane fade in active">
				    <div class="center">
						<form action="" onsubmit="return false" ng-submit="getProductsByFilter();showList();"  id="form-users" name="form-users" ng-model="formu" class="">
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
				<div>
						<div class="clearfix"></div>
						<%--
						Show item: {{showFlag}}
						--%>
						<div class="clearfix"></div>
					 	<%-- Muestra producto --%>
						<div class="" ng-show="showFlag">
							<div class="clearfix"></div>
						 	<h3> <s:message code="admin.product" /> {{p.id}} </h3>
							<div class="marginem"></div>
					 		<div class="clearfix"></div>
					 		<div class="pull-right">
								<button class="btn btn-danger"  ng-click="removeProduct(p.id);showList();"><s:message code="admin.delete" /></button>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<a href="javascript:void(0);" ng-click="showList();" class="btn btn-black" ><s:message code="admin.cancel" /></a>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<a href="javascript:void(0);" ng-click="activaEditMode( modifyItem , whoIsSelected );" class="btn btn-primary" ><s:message code="admin.modify" /></a>
							</div>
							<div class="clearfix"></div>
							<br />
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
						 				<td>
						 					<b><s:message code="app.Lang" /></b>
						 				</td>
						 				<td colspan="3">
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
						 	<div class="">
						 		<div class="col-xs-12 text-center tabletitulos">
						 			<h3><s:message code="admin.images" /></h3>
						 		</div>
						 		<div class="clearfix"></div>
						 		<br />
				 				<ul class="galeriaq list-inline" >
									<li class="col-xs-6 col-sm-4 col-md-3 col-lg-2 center" ng-repeat="a in p.productDetails">
										<div class="">
											<a href="{{a.url}}" target="_blank" class="btn btn-edit btn-black"><i class="fa fa-eye"></i></a>
											<button class="btn btn-black btn-edit hidden"><i class="fa fa-pencil"></i></button>
										</div>
										<img class="click img-responsive img-thumbnail" src="{{a.url}}">
									</li>
								</ul>
							</div>
						 </div>
					 	<%-- Fin mira producto --%>
					 	
					 	<%-- edita producto --%>
						<div class="clearfix"></div>
					 	<%--
					 	Edit flag: {{editaFlag}} 
					 	--%>
						<div class="clearfix"></div>
					 	<div ng-show="editaFlag">
							<div class="clearfix"></div>
						 	<h3> <s:message code="admin.product" /> {{p.id}} </h3>
							<div class="marginem"></div>
							<form action="" onsubmit="return false;" id="updateproductn" 
								  name="updateproductn" ng-model="updateproductn" ng-submit="updateProductNew(p);">
								<div class="pull-right">
									<a class="btn btn-danger"  ng-click="removeProduct(p.id);"><s:message code="admin.delete" /></a>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<a href="javascript:void(0);" ng-click="showList();" class="btn btn-black" ><s:message code="admin.cancel" /> </a>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<button class="btn btn-success" id="mandamodificado" ><s:message code="admin.save" /></button>
								</div>
								<div class="clearfix"></div>
								<br />
							 	<div class="table-responsive center">
							 		<table class="table table-bordered">
							 			<tr class="tabletitulos">					 				
							 				<td colspan="4">
							 					<b><s:message code="admin.title" /></b>
							 				</td>
							 				<td colspan="1">
							 					<b><s:message code="admin.releaseDate" /></b>
							 				</td>
							 			</tr>
							 			<tr class="">
							 				<td colspan="4">
							 					<input ng-required="true" class="text-center form-control" type="text" ng-model="p.title"/>
							 				</td>
							 				<td colspan="1">
							 					<input class="text-center form-control datepicker relaseDatemod" ng-model="p.strReleaseDate" required="true" type="text" />
							 					<%--
							 					<input class="text-center form-control" 
							 					ng-required="true" type="text" ng-model="p.releaseDate" ng-click="p.opened = true;"
									 			uib-datepicker-popup="yyyy-MM-dd" is-open="p.opened" datepicker-options="dateOptions" close-text="Close"  
									 			 />
									 			--%>
							 				</td>
							 			</tr>					 			
							 			<tr class="tabletitulos">
							 				<td>
							 					<b><s:message code="app.Lang" /></b>
							 				</td>
							 				<td colspan="4">
							 					<b><s:message code="admin.details" /></b>
							 				</td>
							 			</tr>
							 			<tr class="">
							 				<td class="" style="">
							 					ES
							 				</td>
							 				<td colspan="4">
							 					<textarea name="" id="" rows="3" ng-required="true" ng-maxlength="255" ng-model="p.descriptionEs" class="form-control"></textarea>
							 				</td>
							 			</tr>
							 			<tr class="">
							 				<td class="" style="">
							 					EN
							 				</td>
							 				<td colspan="4">
							 					<textarea name="" id="" rows="3" ng-required="true" ng-maxlength="255" ng-model="p.descriptionEn" class="form-control"></textarea>
							 				</td>
							 			</tr>
							 			<tr class="">
							 				<td class="">
							 					FR
							 				</td>
							 				<td colspan="4">
							 					<textarea name="" id="" rows="3" ng-required="true" ng-maxlength="255" ng-model="p.descriptionFr" class="form-control"></textarea>
							 				</td>
								 		</tr>
							 			<tr>
							 				<td colspan="5" class="text-left">
							 					<a href="javascript:void(0);" ng-click="newformssize.product = p.id;" class="btn btn-black" data-toggle="modal" data-target="#newYetibera">
							 						<s:message code="admin.newSKU" /> 
							 						<i class="fa fa-plus"></i>
							 					</a>
							 				</td>
							 			</tr>
							 			<tr class="tabletitulos">
											<td><s:message code="admin.delete" /></td>
							 				<td><b>SKU</b></td>
							 				<td><b><s:message code="admin.size" /></b></td>
							 				<td><b><s:message code="admin.price" /></b></td>
							 				<td><b><s:message code="admin.stock" /></b></td>
							 			</tr>
							 			<tr ng-repeat="a in p.skuProduct">
							 				<td class="click" ng-click="removeSkuProduct(a.id,$index);">
							 					<i class="fa fa-times btn btn-black col-xs-12"></i>
							 				</td>
							 				<td>
							 					<input ng-required="true" class="text-center form-control form-control-min" type="text" ng-model="a.sku"/>
							 				</td>
									 		<td>
									 			<%--
							 					<input ng-required="true" class="text-center form-control form-control-min" type="text" ng-model="a.sizeText"/>
							 					--%>
							 					<select ng-required="true" class="text-center form-control form-control-min" 
							 							ng-options="a.id as a.name for a in sizes" name="" id="" ng-model="a.size">
													<%-- 
													<option value="{{a.id}}"  ng-repeat="a in sizes">{{a.name}}</option>
													<option value=""></option>
													--%>
												</select>
									 		</td>
									 		<td>
							 					<input ng-required="true" ng-pattern="/^[0-9]+(\.[0-9]{1,4})?$/" class="text-center form-control form-control-min" type="text" ng-model="a.price"/>
									 		</td>
									 		<td>
							 					<input ng-required="true" ng-pattern="/^[0-9]+(\.[0-9]{1,4})?$/" class="text-center form-control form-control-min" type="text" ng-model="a.stock"/>
									 		</td>
							 			</tr>
							 		</table>
							 	</div>
							 	<div class="marginem">
							 		<a href="#newYetiberaImg" data-toggle="modal" target="_blank" ng-click="newformssizeimg.id = p.id;" class="btn btn-black">
							 			<s:message code="admin.addPicture" /> 
							 			<i class="fa fa-plus"></i>
							 		</a>
							 	</div>
							 	<div class="">
							 		<div class="col-xs-12 text-center tabletitulos">
							 			<h3><s:message code="admin.images" /></h3>
							 		</div>
							 		<div class="clearfix"></div>
							 		<br />
					 				<ul class="galeriaq list-inline" >
										<li class="col-xs-6 col-sm-4 col-md-3 col-lg-2 center" ng-repeat="a in p.productDetails">
											<img src="http://yetibera.com/media/ags/frenteAgs-min.png"  ng-click="selectFrontBack(1,$index,p);" ng-class="{ 'front' : a.side == 1 , 'front' : a.side == '1' }" alt="" class="click image-select img-responsive img-thumbnail" />
											<img src="http://yetibera.com/media/ags/espaldaAgs-min.png" ng-click="selectFrontBack(2,$index,p);" ng-class="{ 'back' : a.side == 2 , 'back' : a.side == '2' }" alt="" class="click image-select img-responsive img-thumbnail" />
											<div class="img-info">
												<button class="btn btn-black btn-delete hidden-xs" ng-click="deleteimg(p.indexado,$index,a.id);" ><i class="fa fa-times"></i></button>
												<a href="{{a.url}}" target="_blank" class="btn btn-edit btn-black"><i class="fa fa-eye"></i></a>
												<button class="btn btn-black btn-edit hidden"><i class="fa fa-pencil"></i></button>
											</div>
											<img class="click img-responsive img-thumbnail" src="{{a.url}}">
										</li>
									</ul>
								</div>
							</form>
					 	</div>
					 	<%-- Fin edita producto --%>
				</div>
		
				<div class="clearfix"></div>
				<%--
				lista flag: {{listaFlag}} 
				--%>
				<div class="clearfix"></div>
		
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
									<td class="click" ng-click="removeProduct(productoIndividual.id);" ><i class="fa fa-times"></i></td>
									<td class="click" ng-click="activaEditMode(productoIndividual, $index);"><i class="fa fa-pencil"></i></td>
									<td class="click" ng-click="showItem(productoIndividual, $index);"><i class="fa fa-eye"></i></td>
									<td>{{productoIndividual.title}}</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
				<%-- Fin tabla de items o articulos --%>
				
				<div class="clearfix"></div>
				</div>
			</div>
		</div>
		
		
	<!-- Modal -->
	<div id="newYetibera" class="newYetibera modal fade" role="dialog">
	  <div class="modal-dialog ">
	
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
					    <select name="" id="" ng-model="newformssize.size" ng-change="checkIfExist(newformssize.size);" class="form-control form-control-min" ng-required="true">
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
				  
				    <div class="col-xs-12 alert alert-danger text-center" ng-if="haytallaExistente">
				  		This size already exists for this product
				    </div>
				  	<button ng-if="!haytallaExistente" class="btn btn-black" type="submit"><s:message code="admin.save" /> <i class="fa fa-floppy-o" aria-hidden="true"></i></button>
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
	<div id="newProducto" class="newProducto modal fade" role="dialog">
	  <div class="modal-dialog modal-lg">
	
	    <!-- Modal content-->
	    <div class="modal-content" ng-init="addpro.skuProduct = [];">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	        <h4 class="modal-title"><s:message code="admin.tittleNewProduct"/></h4>
	      </div>
	      <div class="modal-body">
			<form action="" id="addproform" name="addproform" ng-model="addproform" onsubmit="return false" ng-submit="createProduct();">
				
				<input type="hidden" value="{{size}}" id="siss" />
				
				<div class="form-group col-xs-12 col-md-8">
				    <label for="pwd"><i class="fa fa-file-text"></i><b>&nbsp;<s:message code="admin.title" />:</b></label> 
				    <input ng-required="true" ng-model="addpro.title" class="form-control form-control-min" type="text" />
				</div>
				
				<div class="form-group col-xs-12 col-md-4">
				    <label for="pwd"><i class="fa fa-calendar"></i><b>&nbsp;<s:message code="admin.releaseDate" />:</b></label> 
				    <input class="text-center form-control datepicker relaseDatemod1" required="true" type="text" />
				    <%-- 
				    <input ng-required="true" ng-model="addpro.releaseDate" class="form-control form-control-min" type="text" />
				    
				    <input class="text-center form-control" ng-required="true" type="text" ng-model="addpro.releaseDate" 
				    	   ng-click="p.opened = true;" ng-focus="p.opened = true;" is-open="p.opened"
						   uib-datepicker-popup="yyyy-MM-dd" datepicker-options="dateOptions" close-text="Close" />
					--%>
				</div>
				
				<div class="form-group col-xs-12">
				    <label for="pwd"><i class="fa fa-file-text"></i><b>&nbsp;<s:message code="admin.details" /> (ES):</b></label> 
				    <textarea rows="3" ng-required="true" ng-model="addpro.descriptionEs" class="form-control" ></textarea>
				</div>
				<div class="form-group col-xs-12">
				    <label for="pwd"><i class="fa fa-file-text"></i><b>&nbsp;<s:message code="admin.details" /> (EN):</b></label> 
				    <textarea rows="3" ng-required="true" ng-model="addpro.descriptionEn" class="form-control" ></textarea>
				</div>
				<div class="form-group col-xs-12">
				    <label for="pwd"><i class="fa fa-file-text"></i><b>&nbsp;<s:message code="admin.details" /> (FR):</b></label> 
				    <textarea rows="3" ng-required="true" ng-model="addpro.descriptionFr" class="form-control" ></textarea>
				</div>
			  	
				<div class="col-xs-12 center hidden">
					<label for="pwd"><i class="fa fa-"></i><b>&nbsp;<s:message code="admin.tittleNewSKU" /></b></label>
				</div>
				<div class="clearfix"></div>

				<%-- 
			  	<ul class="nav nav-pills text-center" id="menusmodal">
				  	<li class="btn-black col-xs-12 col-md-3 text-center" style="margin-left: 0px;color:white !important;" 
				  		ng-class="{active: $index == 0}" ng-repeat="z in sizes">
				  		<a data-toggle="pill" href="#h{{z.id}}" style="color:white !important;">{{z.name}}</a>
				  	</li>
				</ul>
				<div class="tab-content text-center"> 
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
				--%>
				
				<div class="clearfix" style="margin-bottom:10px;"></div>
	
				<div class="col-xs-12">
					<label for="pwd"><i class="fa fa-file-image-o"></i><b>&nbsp;<s:message code="admin.Imagen" /></b></label>
				</div>
				<div class="clearfix"></div>
				<div class="dragandrophandler">
					<label class="click" for="fileqwer[]">
						<s:message code="admin.dragdropfiles" />
						<input onchange="masfiles();" id="fileqwer[]" name="fileqwer[]" type="file" class="hidden" multiple />
					</label>
				</div>
				<div class="statusId"></div>
	
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
	<div id="newYetiberaImg" class="newYetiberaImg modal fade" role="dialog">
	  <div class="modal-dialog">
	
	    <!-- Modal content-->
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	        <h4 class="modal-title"><s:message code="admin.tittleNewIMG"/></h4>
	      </div>
	      <div class="modal-body">
			<%-- 
			<form action="" enctype="multipart/form-data"  id="formsnewsizeimg" name="formsnewsizeimg" ng-model="formsnewsizeimg" onsubmit="return false" ng-submit="addProductDetail();">
				  <div class="form-group col-xs-12">
				    <label for="pwd"><i class="fa fa-file-image-o"></i><b><s:message code="admin.Imagen" /></b></label> 
				    <input id="img" file-model="imagenp" ng-required="true" class="form-control form-control-min" type="file" />
				    <%--<input type="file" name="file" class="form-control form-control-min" onchange="angular.element(this).scope().uploadFile(this.files)"/> --%
				  </div>
				  <button class="btn btn-black" ng-click="addProductDetail();" type="submit"><s:message code="admin.save" /> <i class="fa fa-floppy-o" aria-hidden="true"></i></button>
			</form>
			<div class="clearfix"></div>
			<br />
			--%>
			<form action="" onsubmit="return false" ng-submit="subeVariasImages();">
				<div class="col-xs-12">
					<label for="pwd"><i class="fa fa-file-image-o"></i><b>&nbsp;<s:message code="admin.Imagen" /></b></label>
				</div>
				<div class="clearfix"></div>
				<div class="dragandrophandler">
					<label class="click" for="fileqwer1[]">
						<s:message code="admin.dragdropfiles" />
						<input onchange="masfiles1();" id="fileqwer1[]" name="fileqwer1[]" type="file" class="hidden" multiple />
					</label>
				</div>
				<div class="statusId"></div>
	
				<div class="clearfix"></div>	
				<br />
				<div>
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
		
</div>

<script type="text/javascript" src="${ContextPath}/r/js${DeployContext}/products.js" charset="utf-8"></script>