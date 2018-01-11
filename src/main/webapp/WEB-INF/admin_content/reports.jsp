<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div id="page-wrapper" ng-controller="${ appname }Report" 
	 ng-init="getProductSizeDescription();getStatus();getUserByFilter();getOnlyProducts();getReportSchema();">
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
				    	  <input type="hidden" name="report" value="{{r.name}}" />
				    	  <input type="hidden" name="idReport" value="{{r.id}}" />
						  <div class="col-xs-12">   
							  <div class="form-group col-xs-12 col-sm-4" ng-repeat="p in r.parameters">
							    <label ng-if="p.type != 5" for="pwd"><b> {{p.nameAux}} :</b></label> 
								<input ng-if="p.type == 3" class="form-control" ng-required="p.required" name="{{p.string}}" type="text"/>
								<input ng-if="p.type == 2" class="form-control" ng-required="p.required" name="{{p.string}}" type="text"/>
								<select ng-if="p.type == 1 && p.string == 'size'" class="form-control" ng-required="p.required" name="{{p.string}}" id="">
									<%-- <option value="">{{p.string}}</option> --%>
									<option value=""></option>
									<option value="{{n.id}}" ng-repeat="n in sizes">{{n.tallaText}}</option>
								</select>
								<select ng-if="p.type == 1 && p.string == 'product'" class="form-control" ng-required="p.required" name="{{p.string}}" id="">
									<%-- <option value="">{{p.string}}</option> --%>
									<option value=""></option>
									<option value="{{n.id}}" ng-repeat="n in products">{{n.title}}</option>
								</select>
								<select ng-if="p.type == 1 && p.string == 'client'" class="form-control"  ng-required="p.required" name="{{p.string}}" id="">
									<option value=""></option>
									<option value="{{n.id}}" ng-repeat="n in users">{{n.name}}</option>
								</select>
								<select ng-if="p.type == 1 && p.string == 'status'" class="form-control" ng-required="p.required" name="{{p.string}}" id="">
									<%-- <option value="">{{p.string}}</option> --%>
									<option value=""></option>
									<option value="{{n.id}}" ng-repeat="n in status">{{n.name}}</option>
								</select>
								<input ng-if="p.type == 4" ng-click="p.opened = true" class="form-control"  ng-required="p.required" type="text" name="{{p.string}}" ng-model="p.name" 
								 uib-datepicker-popup="dd-MM-yyyy" is-open="p.opened" datepicker-options="dateOptions" close-text="Close"  />
								<input ng-if="p.type == 5" type="hidden"  ng-required="p.required" name="{{p.string}}" value="true" />
							  </div>
						  </div>
							<button class="btn btn-black" ><s:message code="admin.reporteCreate" /></button>
						</form>
				    </div>
				  </div>
				</div>
				
				<div class="clearfix"></div>
				<br />
				<div class="col-xs-12 center">
					<div class="col-xs-12 col-md-3">
						<a href="${ linkFive }?top=1" class="btn btn-black"><s:message code="reports.topFiveProducts" /></a>
					</div>
					<div class="col-xs-12 col-md-3">
						<a href="${ linkFive }?top=3" class="btn btn-black"><s:message code="reports.topFiveOrders" /></a>
					</div>
					<div class="col-xs-12 col-md-3">
						<a href="${ linkFive }?top=4" class="btn btn-black"><s:message code="reports.topFiveCountries" /></a>
					</div>
					<div class="col-xs-12 col-md-3">
						<a href="${ linkFive }?top=2" class="btn btn-black"><s:message code="reports.topFiveOthers" /></a>
					</div>
				</div>
				<div class="clearfix"></div>
				
			</div>
		</div>
	</div>	
</div>

<script type="text/javascript" src="${ContextPath}/r/js${DeployContext}/reports.js" charset="utf-8"></script>