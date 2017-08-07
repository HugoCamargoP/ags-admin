<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div id="page-wrapper" ng-controller="${ appname }user" ng-init="getUserByFilter();">
	<div class="graphs">
		<h3 class="blank1"></h3>
			<div class="tab-content">
				<div class="jumbotron">
					<button ng-click="getUserByFilter();">activado</button>
				</div>
			</div>
		</div>
	</div>

<script type="text/javascript" src="${ContextPath}/r/js${DeployContext}/user.js" charset="utf-8"></script>