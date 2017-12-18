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
				    	  <input type="hidden" name="report" value="{{r.name}}" />
				    	  <input type="hidden" name="idReport" value="{{r.id}}" />
						  <div class="col-xs-12">   
							  <div class="form-group col-xs-12 col-sm-4" ng-repeat="p in r.parameters">
							    <label ng-if="p.type != 5" for="pwd"><b> {{p.nameAux}} :</b></label> 
								<input ng-if="p.type == 1" class="form-control" ng-required="p.required" name="{{p.string}}" type="text"/>
								<input ng-if="p.type == 3" class="form-control" ng-required="p.required" name="{{p.string}}" type="text"/>
								
								<select ng-if="p.type == 2" class="form-control" ng-required="p.required" name="{{p.string}}" id="">
									<option value="">{{p.string}}</option>
								</select>
								
								<%--
								<input type="text" ng-model="customPopupSelected" placeholder="Custom popup template" 
								uib-typeahead="state as state.name for state in statesWithFlags | filter:{name:$viewValue}" 
								typeahead-popup-template-url="customPopupTemplate.html" class="form-control">
								--%>
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
			</div>
		</div>
	</div>
	
	<style>
  .typeahead-demo .custom-popup-wrapper {
    position: absolute;
    top: 100%;
    left: 0;
    z-index: 1000;
    display: none;
    background-color: #f9f9f9;
  }

  .typeahead-demo .custom-popup-wrapper > .message {
    padding: 10px 20px;
    border-bottom: 1px solid #ddd;
    color: #868686;
  }

  .typeahead-demo .custom-popup-wrapper > .dropdown-menu {
    position: static;
    float: none;
    display: block;
    min-width: 160px;
    background-color: transparent;
    border: none;
    border-radius: 0;
    box-shadow: none;
  }
</style>

<script type="text/ng-template" id="customTemplate.html">
  <a>
      <img ng-src="http://upload.wikimedia.org/wikipedia/commons/thumb/{{match.model.flag}}" width="16">
      <span ng-bind-html="match.label | uibTypeaheadHighlight:query"></span>
  </a>
</script>

<script type="text/ng-template" id="customPopupTemplate.html">
  <div class="custom-popup-wrapper"
     ng-style="{top: position().top+'px', left: position().left+'px'}"
     style="display: block;"
     ng-show="isOpen() && !moveInProgress"
     aria-hidden="{{!isOpen()}}">
    <p class="message">select location from drop down.</p>

    <ul class="dropdown-menu" role="listbox">
      <li class="uib-typeahead-match" ng-repeat="match in matches track by $index" ng-class="{active: isActive($index) }"
        ng-mouseenter="selectActive($index)" ng-click="selectMatch($index)" role="option" id="{{::match.id}}">
        <div uib-typeahead-match index="$index" match="match" query="query" template-url="templateUrl"></div>
      </li>
    </ul>
  </div>
</script>

<div class='container-fluid typeahead-demo'>
    <h4>Custom popup templates for typeahead's dropdown</h4>
    <pre>Model: {{customPopupSelected | json}}</pre>
    <input type="text" ng-model="customPopupSelected" placeholder="Custom popup template" uib-typeahead="state as state.name for state in statesWithFlags | filter:{name:$viewValue}" typeahead-popup-template-url="customPopupTemplate.html" class="form-control">
</div>
	
	
</div>

<script type="text/javascript" src="${ContextPath}/r/js${DeployContext}/reports.js" charset="utf-8"></script>