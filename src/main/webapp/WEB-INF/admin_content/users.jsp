<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div id="page-wrapper" ng-controller="${ appname }user" ng-init="filter = 2;">
	<div class="graphs">
		<h3 class="blank1"></h3>
			<div class="tab-content">
				<div class="jumbotron">
				<form action="" onsubmit="return false" ng-submit="getUserByFilter();"  id="form-users" name="form-users" ng-model="formu" class="form-inline">
					  <div class="form-group">
					    <label for="email"><i class="fa fa-user"></i>&nbsp;Email:</label>
				   	 	<input type="text" class="form-control" value="" name="name" id="name" ng-model="email" ng-required="true"/>
					  </div>
					  <div class="form-group">
					    <label for="pwd"><i class="fa fa-filter"></i></label> 
					    <select class="js-example-basic-single" id="filter" class="form-control" ng-model="filter">
					      <option value="1">Exactamente</option>
					      <option value="2" selected>Contiene</option>
					      <option value="3">Comienza con</option>
					      <option value="4">Termina con</option>
					    </select>
					  </div>
					<button type="submit" class="btn-black btn">Buscar</button>
				</form>
				</div>
			</div>
		</div>
	</div>

<script type="text/javascript" src="${ContextPath}/r/js${DeployContext}/user.js" charset="utf-8"></script>