<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div id="page-wrapper" ng-controller="${ appname }user" ng-init="busqueda = false; user = {};">
	<div class="graphs">
		<h3 class="blank1">Creación de Colono</h3>
			<div class="tab-content">
			
			<div class="jumbotron">
				<div class="container">
			        <div class="allRight col-md-10">
						<form ng-show="busqueda" onsubmit="return false;" ng-submit="getUserByString();" class="navbar-form" role="search" style="">
				            <input type="search" class="form-control" style="width: 95%;" placeholder="Buscar usuario... Jaime" ng-model="strbusqueda" name="srch-term" id="srch-term">
				            <button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-search"></i></button>
					        <div class="clearfix"></div>
				            <div ng-if="resultadoconsulta">
					        	<div style="overflow: hidden;max-height: 200px;height:auto;overflow-y: scroll;">
					        		<div>
						        		<div ng-repeat="a in resultadoconsulta" class="input-mod">
						        			<div ng-click="getUserById(a.id);" style="cursor:pointer;">
							        			<div>{{a.userCompleteName}}</div>
							        			<div class="clearfix"></div>
							        		</div>
						        		</div>
						        	</div>
					        	</div>
					        </div>
				        </form>
			        </div>
			        
					<div class="clearfix"></div>
					 <div class="panel-body panel-body-inputin">
						<form ng-hide="busqueda" role="form" name="formregister" id="formregister" class="form-horizontal" 
						ng-model="formregister" onsubmit="return false" ng-submit="createUser();">
						<input type="hidden" ng-model="user.role" ng-init="user.role = 'ROLE_USER'"  />
								 	<h4>Información del dueño (colono):</h4> 
									<br />
									<div  class="textcenter" style="">
										<b>Datos del usuario:</b>
									</div>
									<div class="form-group">
										<label class="col-md-2 control-label">Usuario:</label>
										<div class="col-md-8">
											<div class="input-group in-grp1">							
												<span class="input-group-addon">
													<i class="fa fa-user"></i>
												</span>
												<input type="text" class="form-control1" ng-model="user.user" ng-required="true" placeholder="Jaime">
											</div>
										</div>
										<div class="clearfix"> </div>
									</div>
						            
									<div class="form-group">
										<label class="col-md-2 control-label">Nombres:</label>
										<div class="col-md-8">
											<div class="input-group in-grp1">							
												<span class="input-group-addon">
													<i class="fa fa-user"></i>
												</span>
												<input type="text" class="form-control1" ng-model="user.userFirstName" ng-required="true" placeholder="Jaime Alberto">
											</div>
										</div>
										<div class="clearfix"> </div>
									</div>
									
									<div class="form-group">
										<label class="col-md-2 control-label">Apellidos:</label>
										<div class="col-md-8">
											<div class="input-group in-grp1">							
												<span class="input-group-addon">
													<i class="fa fa-user"></i>
												</span>
												<input type="text" class="form-control1" ng-model="user.userLastName" ng-required="true" placeholder="Cuevas Estrada">
											</div>
										</div>
										<div class="clearfix"> </div>
									</div>
									
									<div class="form-group">
										<label class="col-md-2 control-label">Contraseña:</label>
										<div class="col-md-8">
											<div class="input-group input-icon right in-grp1">
												<span class="input-group-addon">
													<i class="fa fa-key"></i>
												</span>
												<input class="form-control1" ng-model="user.password" ng-required="true" type="password" placeholder="******">
											</div>
										</div>
										<div class="clearfix"> </div>
									</div>            
									<div class="form-group">
										<label class="col-md-2 control-label">Repite Contraseña:</label>
										<div class="col-md-8">
											<div class="input-group in-grp1">
												<span class="input-group-addon">
													<i class="fa fa-key"></i>
												</span>
												<input type="password" class="form-control1" ng-model="user.repassword" ng-required="true"  placeholder="******">
											</div>
										</div>
										<div class="clearfix"> </div>
									</div>
									
									<div  class="textcenter" style="">
										<b>Contacto:</b>
									</div>
									<div class="clearfix"></div>
									<div class="form-group">
										<label class="col-md-2 control-label">Teléfono domicilio</label>
										<div class="col-md-8">
											<div class="input-group in-grp1 col-xs-6">							
												<span class="input-group-addon">
													<i class="fa fa-phone"></i>
												</span>
												<input type="text" class="form-control1" ng-model="user.phoneNumber" ng-required="true" placeholder="33123412">
											</div>
										</div>
										<div class="clearfix"> </div>
									</div>
									<div class="form-group">
										<label class="col-md-2 control-label">Teléfono movil:</label>
										<div class="col-md-8">
											<div class="input-group in-grp1 col-xs-6">							
												<span class="input-group-addon">
													<i class="fa fa-mobile"></i>
												</span>
												<input type="text" class="form-control1" ng-model="user.contactNumber" placeholder="3312345678">
											</div>
										</div>
										<div class="clearfix"> </div>
									</div>
									<div class="form-group">
										<label class="col-md-2 control-label">Correo electrónico</label>
										<div class="col-md-8">
											<div class="input-group in-grp1">							
												<span class="input-group-addon">
													<i class="fa fa-envelope"></i>
												</span>
												<input type="text" class="form-control1" ng-model="user.email" placeholder="Jaime@gmail.com">
											</div>
										</div>
										<div class="clearfix"> </div>
									</div>
									<center>
										<div class="row">
											<div class="">
												<button class="btn-success btn">Registrar</button>
											</div>
										</div>
									</center>
							</div>   
					</form>
					
					<form ng-show="user.id" role="form" name="formupdate" id="formupdate" class="form-horizontal" 
						ng-model="formupdate" onsubmit="return false" ng-submit="createUpdate();">
									<h4>Información del dueño (colono):</h4> 
									<br />
									<div  class="textcenter" style="">
										<b>Datos del usuario:</b>
									</div>
									<div class="form-group">
										<label class="col-md-2 control-label">Usuario:</label>
										<div class="col-md-8">
											<div class="input-group in-grp1">							
												<span class="input-group-addon">
													<i class="fa fa-user"></i>
												</span>
												<input type="text" class="form-control1" ng-model="user.user" ng-required="true" placeholder="Jaime">
											</div>
										</div>
										<div class="clearfix"> </div>
									</div>
						            
									<div class="form-group">
										<label class="col-md-2 control-label">Nombres:</label>
										<div class="col-md-8">
											<div class="input-group in-grp1">							
												<span class="input-group-addon">
													<i class="fa fa-user"></i>
												</span>
												<input type="text" class="form-control1" ng-model="user.userFirstName" ng-required="true" placeholder="Jaime Alberto">
											</div>
										</div>
										<div class="clearfix"> </div>
									</div>
									
									<div class="form-group">
										<label class="col-md-2 control-label">Apellidos:</label>
										<div class="col-md-8">
											<div class="input-group in-grp1">							
												<span class="input-group-addon">
													<i class="fa fa-user"></i>
												</span>
												<input type="text" class="form-control1" ng-model="user.userLastName" ng-required="true" placeholder="Cuevas Estrada">
											</div>
										</div>
										<div class="clearfix"> </div>
									</div>
									<div>
										<a href="" ng-hide="pass" class="btn btn-danger" ng-click="pass = true">Cambiar password del usuario</a>
										<a href="" ng-show="pass" class="btn btn-danger" ng-click="pass = false; user.password = '';">Cancelar</a>
									</div>
									<div class="clearfix"></div><br />
									
									<div ng-show="pass" ng-init="pass =  false">
										<div class="form-group">
											<label class="col-md-2 control-label">Nuevo Contraseña:</label>
											<div class="col-md-8">
												<div class="input-group input-icon right in-grp1">
													<span class="input-group-addon">
														<i class="fa fa-key"></i>
													</span>
													<input class="form-control1" ng-model="user.password" type="password" placeholder="******">
												</div>
											</div>
											<div class="clearfix"> </div>
										</div>  
										          
										<div class="form-group">
											<label class="col-md-2 control-label">Repite Contraseña:</label>
											<div class="col-md-8">
												<div class="input-group in-grp1">
													<span class="input-group-addon">
														<i class="fa fa-key"></i>
													</span>
													<input type="password" class="form-control1" ng-model="user.repassword" placeholder="******">
												</div>
											</div>
											<div class="clearfix"> </div>
										</div>
									</div>
									<div  class="textcenter" style="">
										<b>Contacto:</b>
									</div>
									<div class="clearfix"></div>
									<div class="form-group">
										<label class="col-md-2 control-label">Teléfono domicilio</label>
										<div class="col-md-8">
											<div class="input-group in-grp1 col-xs-6">							
												<span class="input-group-addon">
													<i class="fa fa-phone"></i>
												</span>
												<input type="text" class="form-control1" ng-model="user.phoneNumber" ng-required="true" placeholder="33123412">
											</div>
										</div>
										<div class="clearfix"> </div>
									</div>
									<div class="form-group">
										<label class="col-md-2 control-label">Teléfono movil:</label>
										<div class="col-md-8">
											<div class="input-group in-grp1 col-xs-6">							
												<span class="input-group-addon">
													<i class="fa fa-mobile"></i>
												</span>
												<input type="text" class="form-control1" ng-model="user.contactNumber" placeholder="3312345678">
											</div>
										</div>
										<div class="clearfix"> </div>
									</div>
									<div class="form-group">
										<label class="col-md-2 control-label">Correo electrónico</label>
										<div class="col-md-8">
											<div class="input-group in-grp1">							
												<span class="input-group-addon">
													<i class="fa fa-envelope"></i>
												</span>
												<input type="text" class="form-control1" ng-model="user.email" placeholder="Jaime@gmail.com">
											</div>
										</div>
										<div class="clearfix"> </div>
									</div>
									<center>
										<div class="row">
											<div class="">
												<button class="btn-success btn">Actualizar</button>
												<button class="btn-danger btn hidden" ng-click="" >Eliminar usuario</button>
												
											</div>
										</div>
									</center>
							</div>   
					</form>
				</div>
				
			</div>
			
			<div class="cleafix"></div>
			<br />
			
			<div class="jumbotron" ng-show="user.id" >
				<div class="container">
			        <div class="allRight"> 
						<a href="#newLot" ng-show="user.id" class="btn-success btn" data-toggle="modal">
							Nuevo lote [+]
						</a>
						<a href="javascript:msjerror('Por favor primero registra o selecciona un usuario');" ng-hide="user.id" class="btn-success btn">
							Nuevo lote [+]
						</a>
						<h4>Información de lote</h4> 
					</div>
					 <div class="panel-body panel-body-inputin table-responsive">
					 	<div ng-if="lote">
						 	<table class="table table-striped">
						 		<tr class="centerText">
						 			<th>No.&nbsp;Lote</th>
						 			<th>Condominio&nbsp;(Coto)</th>
						 			<th>Calle</th>
						 			<th>No.&nbsp;Ext/Int</th>
						 			<th>Eliminar</th>
						 		</tr>
						 		<tr class="centerText" ng-repeat="n in lote">
							 		<td>{{n.id}}</td>
							 		<td>{{n.preserveText}}</td>
							 		<td>{{n.streetText}}</td>
							 		<td>{{n.extNumber}} / {{n.inNumber}}</td>
							 		<td>
								 		<button class="btn btn-danger" ng-click="deleteLot(n.id);">
								 			<i class="fa fa-trash" aria-hidden="true"></i>
								 		</button>
							 		</td>
						 		</tr>
						 	</table>
					 	</div>
					 	<div ng-if="!lote">
					 		Usuario sin lotes registrados
					 	</div>
					</div>
				</div>
			</div>
			
			<div class="cleafix"></div>
			<br />
			
			<div class="jumbotron"  ng-show="user.id" >
				<div class="container">
			        <div class="allRight"> 
						<a href="#newCar" ng-show="user.id" class="btn-success btn" data-toggle="modal">
							Nuevo vehículo [+]
						</a>
						<a href="javascript:msjerror('Por favor primero registra o selecciona un usuario');" ng-hide="user.id" class="btn-success btn">
							Nuevo lote [+]
						</a>
						<h4>Información de vehículos</h4> 
					</div>
					 <div class="panel-body panel-body-inputin table-responsive">
					 	<div ng-if="vehiculos">
						 	<table class="table table-striped centerText">
						 		<tr class="centerText">
						 			<th>Tag</th>
						 			<th>Placas</th>
						 			<th>No.&nbsp;de&nbsp;serie</th>
						 			<th>Eliminar</th>
						 		</tr>
						 		<tr class="centerText" ng-repeat="n in vehiculos">
							 		<td>{{n.tag}}</td>
							 		<td>{{n.plate}}</td>
							 		<td>{{n.series}}</td>
							 		<td>
								 		<button class="btn btn-danger" ng-click="deleteCar(n.carId);">
							 				<i class="fa fa-trash" aria-hidden="true"></i>
								 		</button>
							 		</td>
						 		</tr>
						 	</table>
					 	</div>
					 	<div ng-if="!vehiculos">
					 		Usuario sin Vehículos registrados
					 	</div>
					</div>
				</div>
			</div>
		</div>
		<!-- Modal -->
		<div id="newLot" class="modal fade" role="dialog">
		  <div class="modal-dialog">
		
		    <!-- Modal content-->
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal">&times;</button>
		        <h4 class="modal-titlem hidden">Nuevo Lote</h4>
		        <div  class="textcenter" style="">
					<b>Nuevo Lote</b>
				</div>
		      </div>
		      <div class="modal-body">
		        <form role="form" name="formlot" id="formlot" class="form-horizontal" 
						ng-model="formlot" onsubmit="return false" ng-submit="createLot();">
						
							<div class="form-group">
								<label class="col-md-4 col-xs-12 control-label">Condominio (coto):</label>
								<div class="col-md-8 col-xs-12">
									<div class="input-group in-grp1">							
										<span class="input-group-addon">
											<i class="fa fa-building "></i>
										</span>
										<select name="" id="" class="form-control1" ng-model="lot.preserveId" ng-required="true"
										ng-init="getPreserveByPrivateNeighborhood()" ng-change="getStreetByPreserve(lot.preserveId);">
											<option value=""></option>
											<option value="{{a.id}}" ng-repeat="a in condominios">{{a.name}}</option>
										</select>
										<%--input type="text" class="form-control1" ng-model="lot.preserveId" ng-required="true" placeholder="España"--%>
									</div>
								</div>
								<div class="clearfix"> </div>
							</div>
				            
							<!-- div class="form-group hidden">
								<label class="col-md-4 col-xs-12 control-label">Numero de lote:</label>
								<div class="col-md-8 col-xs-12">
									<div class="input-group in-grp1">							
										<span class="input-group-addon">
											<i class="fa fa-hashtag"></i>
										</span>
										<input type="text" class="form-control1" ng-model="lot.id" ng-required="true" placeholder="123456">
									</div>
								</div>
								<div class="clearfix"> </div>
							</div -->
							<div class="form-group">
								<label class="col-md-4 col-xs-12 control-label">Calle</label>
								<div class="col-md-8 col-xs-12">
									<div class="input-group input-icon right in-grp1">
										<span class="input-group-addon">
											<i class="fa fa-street-view"></i>
										</span>
										<select name="" id="" class="form-control1" ng-model="lot.streetId" ng-required="true">
											<option value=""></option>
											<option value="{{a.id}}" ng-repeat="a in calles">{{a.name}}</option>
										</select>
										<!-- input class="form-control1 hidden" ng-model="lot.strretId" ng-required="true" type="text" placeholder="Barcelona" -->
									</div>
								</div>
								<div class="clearfix"> </div>
							</div>    
							
							 <div  class="textcenter" style="">
								<b>Numero:</b>
							</div>       
							<div class="form-group">
								<label class="col-md-4 col-xs-12 control-label">Exterior</label>
								<div class="col-md-8 col-xs-12">
									<div class="input-group in-grp1">
										<span class="input-group-addon">
											<i class="fa fa-hashtag"></i>
										</span>
										<input type="text" class="form-control1" ng-model="lot.extNumber" ng-required="true"  placeholder="11">
									</div>
								</div>
								<div class="clearfix"> </div>
							</div>   
							
							<div class="form-group">
								<label class="col-md-4 col-xs-12 control-label">Interior</label>
								<div class="col-md-8 col-xs-12">
									<div class="input-group in-grp1">
										<span class="input-group-addon">
											<i class="fa fa-hashtag"></i>
										</span>
									<input type="text" class="form-control1" ng-model="lot.inNumber" placeholder="2C">
									</div>
								</div>
								<div class="clearfix"> </div>
							</div>
							
							<div class="row">
								<div class="col-sm-12" >
									<center>
										<button class="btn-success btn" type="submit">Registrar</button>
									</center>
								</div>
							</div>
						</div>   
					</form>
					
		        	<button type="button" class="btn btn-default" style="float: right; margin:20px;" data-dismiss="modal">Cancelar</button>
		        	<div class="clearfix"></div>
		      </div>
		    </div>
		
		  </div>
		
		<!-- Modal -->
		<div id="newCar" class="modal fade" role="dialog">
		  <div class="modal-dialog">
		
		    <!-- Modal content-->
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal">&times;</button>
		        <h4 class="modal-title hidden">Modal Header</h4>
		        <div  class="textcenter" style="">
					<b>Nuevo vehículo</b>
				</div>
		      </div>
		      <div class="modal-body">
		         <form role="form" name="formvehiculo" id="formvehiculo" class="form-horizontal" 
						ng-model="formvehiculo"  onsubmit="return false" ng-submit="createCar()">
							<div class="form-group">
								<label class="col-md-4 col-xs-12 control-label">Tag:</label>
								<div class="col-md-8 col-xs-12">
									<div class="input-group in-grp1">							
										<span class="input-group-addon">
											<i class="fa fa-building "></i>
										</span>
										<input type="text" class="form-control1" ng-model="car.tag" ng-required="true" placeholder="77777">
									</div>
								</div>
								<div class="clearfix"> </div>
							</div>
				            
							<div class="form-group">
								<label class="col-md-4 col-xs-12 control-label">Placas:</label>
								<div class="col-md-8 col-xs-12">
									<div class="input-group in-grp1">							
										<span class="input-group-addon">
											<i class="fa fa-user"></i>
										</span>
										<input type="text" class="form-control1" ng-model="car.plate" ng-required="true" placeholder="JYX-77777">
									</div>
								</div>
								<div class="clearfix"> </div>
							</div>
							<div class="form-group">
								<label class="col-md-4 col-xs-12 control-label">Número de series:</label>
								<div class="col-md-8 col-xs-12">
									<div class="input-group input-icon right in-grp1">
										<span class="input-group-addon">
											<i class="fa fa-key"></i>
										</span>
										<input class="form-control1" ng-model="car.series" ng-required="true" type="text" placeholder="jyx77777">
									</div>
								</div>
								<div class="clearfix"> </div>
							</div>   
							<div class="row">
								<div class="col-sm-12" >
									<center>
										<button class="btn-success btn">Registrar</button>
									</center>
								</div>
							</div>
						</form>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
		      </div>
		    </div>
		  </div>
		</div>
		
		<!-- Modal -->
		<div id="Avisos" class="modal fade" role="dialog">
		  <div class="modal-dialog hidden">
		
		    <!-- Modal content-->
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal">&times;</button>
		        <div  class="textcenter" style="">
		        	<center>
						<b>{{appname | uppercase}}</b>
					</center>
				</div>
		      </div>
		      <div class="modal-body">
		      		¿Desea eliminar lote?
		      		<center>
		        		<button type="button" class="btn btn-default" data-dismiss="modal">Si</button>
		      		</center>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
		      </div>
		    </div>
		  </div>
		</div>
	</div>
	
<script type="text/javascript" src="${ContextPath}/r/js${DeployContext}/user.js" charset="utf-8"></script>
<script type="text/javascript" src="${ContextPath}/r/js${DeployContext}/lot.js" charset="utf-8"></script>