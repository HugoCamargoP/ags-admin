<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div id="page-wrapper" ng-controller="${ appname }user" ng-init="filter = 2;">
	<div class="graphs">
		<h3 class="blank1"></h3>
			<div class="tab-content">
				<div class="jumbotron">
					<div class="container">
						<form action="" onsubmit="return false" ng-submit="getUserByFilter();"  id="form-users" name="form-users" ng-model="formu" class="form-inline">
							  <div class="form-group center">
							    <label for="email"><i class="fa fa-user"></i>&nbsp;<b>Email:</b></label>
						   	 	<input type="text" class="form-control" value="" name="email" id="email" ng-model="email"/>
							  </div>
							  <div class="form-group">
							    <label for="pwd"><i class="fa fa-filter"></i></label> 
							    <select class="js-example-basic-single" id="filter" required="required" class="form-control">
							      <option value="1">Exactamente</option>
							      <option value="2" selected>Contiene</option>
							      <option value="3">Comienza con</option>
							      <option value="4">Termina con</option>
							    </select>
							  </div>
							<button type="submit" class="btn-black btn"><span class="fa fa-search" aria-hidden="true"></span>
							&nbsp;Buscar</button>
						</form>
						
						<div class="table-responsive marginem">
							<table class="table table-striped table-bordered center">
								<tr class="ttitulo">
									<th>Seleccion</th>
									<th>Email</th>
									<th>Nombre</th>
									<th>Permiso</th>
								</tr>
								<tr ng-repeat="u in usuarios">
									<td>
										<input type="checkbox" id="" ng-model="check[$index];" class="form-control" ng-model="cambiador" ng-change="statuscheck($index);" />
								    </td>
									<td>{{u.email}}</td>
									<td>{{u.name}}</td>
									<td ng-if="u.type == 'ROLE_ADMIN'">
										<button class="btn btn-danger" ng-click="usuarios[$index].type = 'ROLE_USER';userUpdateRol();">Revocar</button>
									</td>
									<td ng-if="u.type != 'ROLE_ADMIN'">
										<button class="btn btn-success" ng-click="usuarios[$index].type = 'ROLE_ADMIN';userUpdateRol();">Otorgar</button>
									</td>
								</tr>
							</table>
							
							<div class="pull-left">
								<button class="btn btn-danger" ng-click="per = 'ROLE_USER';cambiaSelected();">Revocar permisos a los seleccionados</button>
							</div>
							<div class="pull-right">
								<button class="btn btn-success" ng-click="per = 'ROLE_ADMIN';cambiaSelected();">Otorgar permisos a los seleccionados</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

<script type="text/javascript" src="${ContextPath}/r/js${DeployContext}/user.js" charset="utf-8"></script>
