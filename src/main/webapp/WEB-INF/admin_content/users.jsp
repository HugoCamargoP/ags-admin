<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div id="page-wrapper" ng-controller="${ appname }user" ng-init="filter = 2;">
	<div class="graphs">
		<h3 class="blank1 center"><s:message code="admin.users"/></h3>
			<div class="tab-content">
				<div class="jumbotron">
					<div class="container">
							<table class="table table-striped table-bordered center">
								<tr>
									<td>
										<form action="" onsubmit="return false" ng-submit="getUserByFilter();"  id="form-users" name="form-users" ng-model="formu" class="">
										  <ul class="list-inline">
										  	<li>
											  	<div class="form-group">
												    <label for="email"><i class="fa fa-user"></i>&nbsp;<b> <s:message code="app.loginUser"/>:</b></label>
											   	 	<input type="text" style="width:100%;" class="form-control" value="" name="email" id="email" ng-model="email" ng-change="name = email;"/>
												</div>
										  	</li>
										  	<li>
											  <div class="form-group">
											    <label for="pwd"><i class="fa fa-filter"></i><b> <s:message code="admin.filter"/>:</b></label> 
											    <select class="js-example-basic-single form-control" id="filter" required="required" class="form-control">
											      <option value="1"><s:message code="admin.exactly"/></option>
											      <option value="2" selected><s:message code="admin.contains"/></option>
											      <option value="3"><s:message code="admin.beginsWith"/></option>
											      <option value="4"><s:message code="admin.endsWith"/></option>
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
									</td>
								</tr>
							</table>
						<div class="table-responsive marginem">
							<table class="table table-striped table-bordered center">
								<tr class="ttitulo" ng-if='usuarios'>
									<th><s:message code="admin.select"/></th>
									<th><s:message code="admin.email"/></th>
									<th><s:message code="admin.name"/></th>
									<th><s:message code="admin.Permissions"/></th>
								</tr>
								<tr ng-repeat="u in usuarios"  ng-if='usuarios.length > 0'>
									<td>
										<input type="checkbox" id="" ng-model="check[$index];" class="form-control" ng-model="cambiador" ng-change="statuscheck($index);" />
								    </td>
									<td>{{u.email}}</td>
									<td>{{u.name}}</td>
									<td ng-if="u.type == 'ROLE_ADMIN'">
										<button class="btn btn-danger" ng-click="usuarios[$index].type = 'ROLE_USER';userUpdateRol();"><s:message code="admin.revoke"/></button>
									</td>
									<td ng-if="u.type != 'ROLE_ADMIN'">
										<button class="btn btn-success" ng-click="usuarios[$index].type = 'ROLE_ADMIN';userUpdateRol();"><s:message code="admin.grant"/></button>
									</td>
								</tr>
								<tr ng-if='usuarios.length == 0'>
									<td colspan="4">
										<b><s:message code="admin.withoutResults"/></b>
									</td>
								</tr>
							</table>
						</div>
						<div class="clearfix"></div>
						
						<%-- Paginacion $scope.antes --%>
						<div class="center">
							<ul class="pagination">
							  <li ng-if="currentpage > 1" ng-click="asignadas( currentpage = currentpage - 1);"><a href="javascript:void(0);"><i class="fa fa-angle-left"></i></a></li>
							  <li ng-repeat="pag in antes" ng-class="{'active': currentpage == pag}" class="" ng-click="asignadas( pag );">
							  	<a href="javascript:void(0);">
							  		{{pag}}
							  	</a>
							  </li>
							  <li ng-if="currentpage < ultimo" ng-click="asignadas( currentpage = currentpage + 1);"><a href="javascript:void(0);"><i class="fa fa-angle-right"></i></a></li>
							</ul>
						</div>
						<%-- Paginacion $scope.antes --%>
							
						<div class="clearfix"></div>
						<br />
						<div  ng-if='usuarios'>
							<div class="hidden-xs">
								<p>
									
								</p>
								<div class="pull-left">
									<button class="btn btn-danger" ng-click="cambiaSelected('ROLE_USER');"><s:message code="admin.revokePermissionsAll"/></button>
								</div>
								<div class="pull-right">
									<button class="btn btn-success" ng-click="cambiaSelected('ROLE_ADMIN');"><s:message code="admin.grantPermissionsAll"/></button>
								</div>
							</div>
							<div class="visible-xs center">
								<b><s:message code="admin.forSelected"/>:</b>
								<div class="">
									<button class="btn btn-danger" ng-click="cambiaSelected('ROLE_USER');"><s:message code="admin.revokePermisson"/></button>
								</div>
								<div class="clearfix"></div>
								<br />
								<div class="">
									<button class="btn btn-success" ng-click="cambiaSelected('ROLE_ADMIN');"><s:message code="admin.grantPermisson"/></button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

<script type="text/javascript" src="${ContextPath}/r/js${DeployContext}/user.js" charset="utf-8"></script>
