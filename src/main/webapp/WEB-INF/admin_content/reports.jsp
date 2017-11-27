<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%--

--%>


<div id="page-wrapper" ng-controller="${ appname }Report" ng-init="getReportSchema();">
	<div class="graphs">
		<h3 class="blank1 center"><s:message code="admin.report"/></h3>
			<div class="tab-content">
				<div class="container">
					<div class="panel-group">
					  <div class="panel panel-primary" ng-repeat="r in reportes">
	  					<div class="panel-heading btn-black click" data-toggle="collapse" data-target="#_{{r.name}}">
	  						<span class="glyphicon glyphicon-plus"></span> {{r.name}} 
	  					 </div>
					    <div class="panel-body collapse" id="_{{r.name}}">
					    	<form action="${ linkSales }" onsubmit="" ng-submit="" ng-model="r.name">
					    	 
							  <div class="col-xs-12">
								  <div class="form-group col-xs-12 col-sm-4" ng-repeat="p in r.parameters">
								    <label ng-if="p.type != 5" for="pwd"><b> {{p.nameAux}} :</b></label> 
									<input ng-if="p.type == 1" class="form-control" name="{{p.string}}" type="text" ng-model="p.name"/>
									<input ng-if="p.type == 3" class="form-control" name="{{p.string}}" type="text" ng-model="p.name"/>
									<select ng-if="p.type == 2" class="form-control" name="{{p.string}}" ng-model="p.name" id="">
										<option value="">1</option>
										<option value="">2</option>
										<option value="">3</option>
										<option value="">4</option>
									</select>
									<input ng-if="p.type == 4" ng-click="p.opened = true" class="form-control" type="text" name="{{p.string}}" ng-model="p.name" 
									 uib-datepicker-popup="yyyy-mm-dd" is-open="p.opened" datepicker-options="dateOptions" close-text="Close" alt-input-formats="altInputFormats" />
									<input ng-if="p.type == 5" type="hidden" name="{{p.string}}" value="true" />
								  </div>
							  </div>
								<button class="btn btn-black" ><s:message code="admin.reporteCreate" /></button>
							</form>
					    </div>
					  </div>
					</div>
				</div>
			</div>
		</div>
	</div>

<script type="text/javascript" src="${ContextPath}/r/js${DeployContext}/reports.js" charset="utf-8"></script>