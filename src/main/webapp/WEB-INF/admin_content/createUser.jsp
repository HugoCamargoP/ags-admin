<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div id="page-wrapper" ng-controller="${ appname }userAdmin" ng-init="busqueda = false; user = {};">
	<div class="graphs">
		<h3 class="blank1">Create User</h3>
			<div class="tab-content">
			
			<div class="jumbotron">
				<div class="container">
			        <div class="allRight col-md-10 hidden">
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
							<form ng-hide="busqueda" role="form" name="formregister" id="formregister" class="form-horizontal" ng-model="formregister" onsubmit="return false" ng-submit="createUser();">
								<div class="form-group">
									<label class="col-md-2 control-label">Type:</label>
									<div class="col-md-8">
										<div class="input-group in-grp1">							
											<span class="input-group-addon">
												<i class="fa fa-lock"></i>
											</span>
											<select name="" id="" class="form-control1" ng-required="true" ng-model="user.role" />
												<option value=""></option>
												<option value="ROLE_ADMIN">Administrator</option>
												<option value="ROLE_USER">User</option>
											</select>
										</div>
									</div>
									<div class="clearfix"> </div>
								</div>
								<div class="form-group">
									<label class="col-md-2 control-label">User:</label>
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
									<label class="col-md-2 control-label">Complete Name:</label>
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
								<div class="form-group">
									<label class="col-md-2 control-label">Password:</label>
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
									<label class="col-md-2 control-label">Repeat password:</label>
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
								<div class="row centerText">
									<div class="">
										<button class="btn-success btn">Register</button>
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