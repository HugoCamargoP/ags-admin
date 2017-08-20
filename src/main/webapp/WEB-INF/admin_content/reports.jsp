<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div id="page-wrapper" ng-controller="${ appname }" ng-init="">
	<div class="graphs">
		<h3 class="blank1 center"><s:message code="admin.report"/></h3>
			<div class="tab-content">
				<div class="container">
					<div class="panel-group">
					  <div class="panel panel-primary">
	  					<div class="panel-heading btn-black" style="cursor:pointer;" data-toggle="collapse" data-target="#1"><span class="glyphicon glyphicon-plus"></span>
	  					 Informe de Ventas por producto </div>
					    <div class="panel-body collapse" id="1">
							<form action="" onsubmit="return false" ng-submit="getUserByFilter();"  id="form-users" name="form-users" ng-model="formu" class="">
							  <ul class="list-inline">
							  	<li>
								  	<div class="form-group">
									    <label for="email"><i class="fa fa-user"></i>&nbsp;<b> Palabra:</b></label>
								   	 	<input type="text" style="width:100%;" class="form-control" value="" name="email" id="email" ng-model="email"/>
									</div>
							  	</li>
							  	<li>
								  <div class="form-group">
								    <label for="pwd"><i class="fa fa-filter"></i><b> Filtro</b></label> 
								    <select class="js-example-basic-single form-control" id="filter" required="required" class="form-control">
								      <option value="1">Exactamente</option>
								      <option value="2" selected>Contiene</option>
								      <option value="3">Comienza con</option>
								      <option value="4">Termina con</option>
								    </select>
								  </div>
							  	</li>
							  </ul>
							  <div class="clearfix visible-xs"></div> 
							  <br class="visible-xs"/>
							  <div class="center">
							  	<button type="submit" class="btn-black btn"><span class="fa fa-search" aria-hidden="true"></span>&nbsp;Buscar</button>
							  </div>
							</form>
					    </div>
					  </div>
					  
					  <div class="panel panel-primary">
	  					<div class="panel-heading btn-black" style="cursor:pointer;" data-toggle="collapse" data-target="#4"><span class="glyphicon glyphicon-plus"></span>
	  					 Informe de Ventas por cliente </div>
					    <div class="panel-body collapse" id="4">
							<form action="" onsubmit="return false" ng-submit="getUserByFilter();"  id="form-users" name="form-users" ng-model="formu" class="">
							  <ul class="list-inline">
							  	<li>
								  	<div class="form-group">
									    <label for="email"><i class="fa fa-user"></i>&nbsp;<b> Palabra:</b></label>
								   	 	<input type="text" style="width:100%;" class="form-control" value="" name="email" id="email" ng-model="email"/>
									</div>
							  	</li>
							  	<li>
								  <div class="form-group">
								    <label for="pwd"><i class="fa fa-filter"></i><b> Filtro</b></label> 
								    <select class="js-example-basic-single form-control" id="filter" required="required" class="form-control">
								      <option value="1">Exactamente</option>
								      <option value="2" selected>Contiene</option>
								      <option value="3">Comienza con</option>
								      <option value="4">Termina con</option>
								    </select>
								  </div>
							  	</li>
							  </ul>
							  <div class="clearfix visible-xs"></div> 
							  <br class="visible-xs"/>
							  <div class="center">
							  	<button type="submit" class="btn-black btn"><span class="fa fa-search" aria-hidden="true"></span>&nbsp;Buscar</button>
							  </div>
							</form>
					    </div>
					  </div>
					  
					  <div class="panel panel-primary">
	  					<div class="panel-heading btn-black" style="cursor:pointer;" data-toggle="collapse" data-target="#5"><span class="glyphicon glyphicon-plus"></span>
	  					 Informe de movimiento de Productos (Inventario)</div>
					    <div class="panel-body collapse" id="5">
							<form action="" onsubmit="return false" ng-submit="getUserByFilter();"  id="form-users" name="form-users" ng-model="formu" class="">
							  <ul class="list-inline">
							  	<li>
								  	<div class="form-group">
									    <label for="email"><i class="fa fa-user"></i>&nbsp;<b> Palabra:</b></label>
								   	 	<input type="text" style="width:100%;" class="form-control" value="" name="email" id="email" ng-model="email"/>
									</div>
							  	</li>
							  	<li>
								  <div class="form-group">
								    <label for="pwd"><i class="fa fa-filter"></i><b> Filtro</b></label> 
								    <select class="js-example-basic-single form-control" id="filter" required="required" class="form-control">
								      <option value="1">Exactamente</option>
								      <option value="2" selected>Contiene</option>
								      <option value="3">Comienza con</option>
								      <option value="4">Termina con</option>
								    </select>
								  </div>
							  	</li>
							  </ul>
							  <div class="clearfix visible-xs"></div> 
							  <br class="visible-xs"/>
							  <div class="center">
							  	<button type="submit" class="btn-black btn"><span class="fa fa-search" aria-hidden="true"></span>&nbsp;Buscar</button>
							  </div>
							</form>
					    </div>
					  </div>
					  
					  <div class="panel panel-default hidden" id="2">
	  					<div class="panel-heading">Panel Heading</div>
					    <div class="panel-body">Panel Content</div>
	  					<div class="panel-footer">Panel Footer</div>
					  </div>
					  <div class="panel panel-default hidden" id="3">
	  					<div class="panel-heading">Panel Heading</div>
					    <div class="panel-body">Panel Content</div>
	  					<div class="panel-footer">Panel Footer</div>
					  </div>
					</div>
				</div>
			</div>
		</div>
	</div>

<script type="text/javascript" src="${ContextPath}/r/js${DeployContext}/.js" charset="utf-8"></script>