<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div id="page-wrapper" ng-controller="${ appname }Prod" ng-init="getConfigEntity();">
	
	<input type="hidden" id="csrf" name="${_csrf.parameterName}" value="${_csrf.token}" />
	
	<div class="graphs">
		<h3 class="blank1 center"><s:message code="admin.configTitle"/></h3>
		<div class="tab-content">
			<div class="jumbotron">
				<form action="" name="configform" id="configform" onsubmit="return false;" ng-submit="updateConfigEntity();">
					<div>
					  	<div class="form-group col-xs-12 col-md-4" >
						    <label for="email"><i class="fa fa-barcode"></i>&nbsp;<b> <s:message code="admin.iva" /> %</b></label>
					   	 	<input type="text" style="width:100%;" class="form-control" value="" name="" id="" ng-required="true" ng-pattern="/^[0-9]+?$/" ng-model="conf.iva"/>
						</div>
					  	<div class="form-group col-xs-12 col-md-4" >
						    <label for="email"><i class="fa fa-barcode"></i>&nbsp;<b> <s:message code="admin.littleBox" /> </b></label>
					   	 	<input type="text" style="width:100%;" class="form-control" value="" name="" id="" ng-required="true" ng-pattern="/^[0-9]+(\.[0-9]{1,4})?$/" ng-model="conf.littleBox"/>
						</div>
					  	<div class="form-group col-xs-12 col-md-4" >
						    <label for="email"><i class="fa fa-barcode"></i>&nbsp;<b>  <s:message code="admin.mediumBox" /> </b></label>
					   	 	<input type="text" style="width:100%;" class="form-control" value="" name="" id="" ng-required="true" ng-pattern="/^[0-9]+(\.[0-9]{1,4})?$/" ng-model="conf.mediumBox"/>
						</div>
					  	<div class="form-group col-xs-12 col-md-4" >
						    <label for="email"><i class="fa fa-barcode"></i>&nbsp;<b> <s:message code="admin.bigBox" /></b></label>
					   	 	<input type="text" style="width:100%;" class="form-control" value="" name="" id="" ng-required="true" ng-pattern="/^[0-9]+(\.[0-9]{1,4})?$/" ng-model="conf.bigBox"/>
						</div>
					  	<div class="form-group col-xs-12 col-md-4" >
						    <label for="email"><i class="fa fa-barcode"></i>&nbsp;<b> <s:message code="admin.shipmentCost" /> </b></label>
					   	 	<input type="text" style="width:100%;" class="form-control" value="" name="" id="" ng-required="true" ng-pattern="/^[0-9]+(\.[0-9]{1,4})?$/" ng-model="conf.shipmentCost"/>
						</div>
					  	<div class="form-group col-xs-12 col-md-4" >
						    <label for="email"><i class="fa fa-barcode"></i>&nbsp;<b> <s:message code="admin.dollarCost" /> </b></label>
					   	 	<input type="text" style="width:100%;" class="form-control" value="" name="" id="" ng-required="true" ng-pattern="/^[0-9]+(\.[0-9]{1,4})?$/" ng-model="conf.dollarCost"/>
						</div>
						
					</div>
					<div class="col-xs-12 text-center">
						<button class="btn btn-success btn-lg text-center"><s:message code="admin.save" /></button>
					</div>
				</form>
				
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript" src="${ContextPath}/r/js${DeployContext}/products.js" charset="utf-8"></script>