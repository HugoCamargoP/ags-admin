<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div id="page-wrapper" ng-controller="${ appname }userAdmin" ng-init="busqueda = true; user = {};">
	<div class="graphs">
		<h3 class="blank1">Modificación de usuario</h3>
			<div class="tab-content">
			
			<div class="jumbotron">
				<div class="container">
			        <div class="allRight col-md-10">
						<form onsubmit="return false;" ng-submit="getUserByString();" class="navbar-form" role="search" style="">
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
					 		<form ng-show="user.id" role="form" name="formupdate" id="formupdate" class="form-horizontal" ng-model="formupdate" onsubmit="return false" ng-submit="createUpdate();">
								<div class="form-group">
									<label class="col-md-2 control-label">Tipo:</label>
									<div class="col-md-8">
										<div class="input-group in-grp1">							
											<span class="input-group-addon">
												<i class="fa fa-lock"></i>
											</span>
											<select name="" id="" class="form-control1" ng-required="true" ng-model="user.role" />
												<option value=""></option>
												<option value="0">Administrador</option>
											</select>
										</div>
									</div>
									<div class="clearfix"> </div>
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
									<label class="col-md-2 control-label">Nombre completo:</label>
									<div class="col-md-8">
										<div class="input-group in-grp1">							
											<span class="input-group-addon">
												<i class="fa fa-user"></i>
											</span>
											<input type="text" class="form-control1" ng-model="user.userCompleteName" ng-required="true" placeholder="Jaime Cuevas">
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
								<div class="row centerText">
									<div class="">
										<button class="btn-success btn">Actualizar</button>
										<button class="btn-danger btn hidden" ng-click="" >Eliminar usuario</button>
										
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

<script type="text/javascript" src="${ContextPath}/r/admin/js${DeployContext}/user_admin.js" charset="utf-8"></script>
<script type="text/javascript" src="${ContextPath}/r/js${DeployContext}/user_admin.js" charset="utf-8"></script>