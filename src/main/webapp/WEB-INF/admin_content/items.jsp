<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
	<h1>Galería de imágenes con efecto lightbox solo con CSS</h1>
	
	<ul class="galeria">
		<li><a href="#img1"><img src="http://www.paisajesimagenes.com/wp-content/uploads/Descripci%C3%B3n-de-paisajes..jpg"></a></li>
		<li><a href="#img2"><img src="http://neo-deco.es/wp-content/uploads/2013/01/Paisaje-de-monta%C3%B1a.jpg"></a></li>
		<li><a href="#img3"><img src="http://www.forodefotos.com/attachments/naturaleza/16230d1294773821-fotos-de-paisajes-fotos-de-paisajes-nevado.jpg"></a></li>
		<li><a href="#img4"><img src="http://altavistaonline.info/wp-content/uploads/2015/07/paisaje-oto-al-10834.jpg"></a></li>
	</ul>

		<h3>Paisaje 1</h3>
		<div class="imagen">
			<a href="#img4">&#60;</a>
			<a href="#img2"><img src="http://www.paisajesimagenes.com/wp-content/uploads/Descripci%C3%B3n-de-paisajes..jpg"></a>
			<a href="#img2">></a>
		</div>
		<a class="cerrar" href="">X</a>
	</div>
	
	<div class="modal" id="img2">
		<h3>Paisaje 2</h3>
		<div class="imagen">
			<a href="#img1">&#60;</a>
			<a href="#img3"><img src="http://neo-deco.es/wp-content/uploads/2013/01/Paisaje-de-monta%C3%B1a.jpg"></a>
			<a href="#img3">></a>
		</div>
		<a class="cerrar" href="">X</a>
	</div>
	
	<div class="modal" id="img3">
		<h3>Paisaje 3</h3>
		<div class="imagen">
			<a href="#img2">&#60;</a>
			<a href="#img4"><img src="http://www.forodefotos.com/attachments/naturaleza/16230d1294773821-fotos-de-paisajes-fotos-de-paisajes-nevado.jpg"></a>
			<a href="#img4">></a>
		</div>
		<a class="cerrar" href="">X</a>
	</div>
	
	<div class="modal" id="img4">
		<h3>Paisaje 4</h3>
		<div class="imagen">
			<a href="#img3">&#60;</a>
			<a href="#img1"><img src="http://altavistaonline.info/wp-content/uploads/2015/07/paisaje-oto-al-10834.jpg"></a>
			<a href="#img1">></a>
		</div>
		<a class="cerrar" href="">X</a>
	</div>
	 --%>

<div id="page-wrapper" ng-controller="${ appname }Prod" ng-init="getAllProducts();getProductSizes();overs= {};newsize = {};eachitem = {}; coveraux = {};">
	<div class="graphs">
		<h3 class="blank1 center"><s:message code="admin.items"/></h3>
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
					 		</table>
					 	</div>
					 	<div class="marginem">
					 		<a href="{{a.url}}" target="_blank" class="btn btn-black">Add picture <i class="fa fa-plus"></i></a>
					 	</div>
					 	<div class="container-img-muestras">
			 				<ul class="galeriaq list-inline" > <%-- ng-init="modalessss(p.productDetails,$index,p.id);">--%>
								<li class="col-xs-6 col-sm-4 col-md-3 col-lg-2 center" ng-repeat="a in p.productDetails">
									<div class="img-info">
										<button class="btn btn-black btn-delete hidden-xs" ng-click="deleteimg('img'+p.id+''+a.id);" ><i class="fa fa-times"></i></button>
										<a href="{{a.url}}" target="_blank" class="btn btn-edit btn-black"><i class="fa fa-eye"></i></a>
										<button class="btn btn-black btn-edit hidden"><i class="fa fa-pencil"></i></button>
									</div>
									<img class="click img-responsive img-thumbnail" src="{{a.url}}">
								</li>
							</ul>
						</div>
						<%--
						<div ng-repeat="h in p.datamodales"  ng-bind-html="h">
							<!--<span ng-bind-html="h"></span>{{h}}-->
						</div> 
						--%>
						<%--
						<div class="modal">
						</div>
						<div ng-repeat="h in p.datamodales"  ng-bind-html="h">
								<!--<span ng-bind-html="h"></span>{{h}}-->
						</div> 
						 
						
						<span ng-bind-html="infoText"></span>--%>
							<%-- <div class="modal" id="">
							
							<h3>
							Product {{p.id}}
							</h3>
							<div class="imagen hidden">
								<div ng-if="modalestotal[p.id] > 0">
									<a href="#img{{p.id}}{{p.productDetails[modalestotal[p.id]].id}}" ng-if="modales[p.id] == 0" >
										&#60;
									</a>
									<a href="#img{{p.id}}{{p.productDetails[modales[p.id]-1].id}}" ng-if="modales[p.id] > 0" >
										#img{{p.id}}{{p.productDetails[modales[p.id]-1].id}}&#60;</a>
								</div>
								
								<img src="{{a.url}}">
								
								<div ng-if="p.productDetails.length > 0">
									<a href="#img{{p.id}}{{a.id}}">></a>
								</div>
							</div>
							<a class="cerrar" href="#img">X</a>
							<div ng-init="modales[p.id] [modales[p.id] +1] =  modales[p.id] +1 ;"></div>
							--%>
						</div>
					 </div>
				</div>
			</div>
		</div>
	</div>

<div id="coverflow-slider"></div>



<style>
/*Estilos de la galeria*/

.galeria {
	width: 90%;
	margin: auto;
	list-style: none;
	padding: 20px;
	box-sizing: border-box;
	display: flex;
	flex-wrap: wrap;
	justify-content: space-around;
}

.galeria li {
	margin: 5px;
}

.galeria img {
/*
	width: 150px;
	height: 100px;
*/
}

/*Estilos del modal*/

.modal {
	display: none;
}

.modal:target , .modal-actives {
	
	display: block;
	position: fixed;
	background: rgba(0,0,0,0.8);
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
}

.modal h3 {
	color: #fff;
	font-size: 30px;
	text-align: center;
	margin: 15px 0;
}

.imagen {
	/*
	width: 100%;
	height: 50%;
	*/
	
	display: flex;
	justify-content: center;
	align-items: center;
}

.imagen a {
	color: #fff;
	font-size: 40px;
	text-decoration: none;
	margin: 0 10px;
}

.imagen a:nth-child(2) {
	margin: 0;
	height: 100%;
	flex-shrink: 2;
}

.imagen img {
	width: 500px;
	height: 100%;
	max-width: 100%;
	border: 7px solid #fff;
	box-sizing: border-box;
}

.cerrar {
	display: block;
	background: #fff;
	width: 25px;
	height: 25px;
	margin: 15px auto;
	text-align: center;
	text-decoration: none;
	font-size: 25px;
	color: #000;
	padding: 5px;
	border-radius: 50%;
	line-height: 25px;
}

	.ui-coverflow-wrapper{
	    height:400px;
	    margin-top: 100px;
	}
	.ui-state-active{
	    border:0px;
	}
</style>
<link type='text/css' rel="stylesheet" href="${ContextPath}/r/css/jquery.flipster.min.css" /> 
<script type="text/javascript" src="${ContextPath}/r/js/jquery.flipster.min.js"></script>
<!--
<link type='text/css' rel="stylesheet" href="${ContextPath}/r/css/coverflow.css" /> 
<script type="text/javascript" src="${ContextPath}/r/js/coverflow.standalone.min.js"></script>
<link type='text/css' rel="stylesheet" href="https://cdn.rawgit.com/coverflowjs/coverflow/master/dist/coverflow.css" />
<script type="text/javascript" src="https://cdn.rawgit.com/coverflowjs/coverflow/master/dist/coverflow.min.js" charset="utf*-8"></script> 
--> 
<script type="text/javascript" src="${ContextPath}/r/js${DeployContext}/products.js" charset="utf-8"></script>
<script>
$(function() {
	/*
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
    */
    $('.coverflow').flipster();
});

function cover(a,x)
{
	console.log(a);
	for ( var f in x) 
	{
		contenido = '<li data-flip-title="Red" ng-repeat="c in p.productDetails">'+
	    '<img class="img-responsive" src="'+x[f].url+'" />'+
		'</li>';
		$(a).append(contenido);
	}
	$('.coverflow').flipster();
}

</script>