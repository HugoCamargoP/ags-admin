//angular.module(appname+'App')
//var app = angular.module(appname+'App', [ 'ngSanitize']);

app.controller(appname+'Report',['$scope','$sce', appname+'Service',

function($scope,$sce, Service)
{	
	//{"id":3,"idTalla":3,"tallaText":"L"},
	$scope.sizes = {};
		
	$scope.getProductSizeDescription = function()
	{
		console.log('getProductSizeDescription');
		Service.getProductSizeDescription().then(
		function successCallback(response)
		{
			$scope.sizes = response.data.data;
		},  
		function errorCallback(response){	
		});	
	}
	 
	$scope.users = {};
		
	$scope.getUserByFilter = function()
	{
		console.log('getUserByFilter');
		Service.getUserByFilter('',2,1,999999).then(
		function successCallback(response)
		{
			$scope.users = response.data.data;
		},  
		function errorCallback(response){	
		});	
	} 
	
	$scope.products = {};
		
	$scope.getOnlyProducts = function()
	{
		console.log('products');
		Service.getOnlyProducts().then(
		function successCallback(response)
		{
			$scope.products = response.data.data;
		},  
		function errorCallback(response){	
		});	
	} 
	
	$scope.status = {};
		
	$scope.getStatus = function()
	{
		console.log('status');
		Service.getStatus().then(
		function successCallback(response)
		{
			$scope.status = response.data.data;
		},  
		function errorCallback(response){	
		});	
	}
	
	
	$scope.reportes = {};
	$scope.getReportSchema = function()
	{
		Service.getReportSchema().then(
				function successCallback(response)
				{
					$scope.reportes = response.data;
					for ( var i in $scope.reportes) {
						//console.log($scope.reportes[i]);
						for ( var a in $scope.reportes[i].parameters) {							
							//console.log($scope.reportes[i].parameters[a]);
							$scope.reportes[i].parameters[a].nameAux = $scope.reportes[i].parameters[a].name;
							$scope.reportes[i].parameters[a].string = $scope.reportes[i].parameters[a].model;
							if($scope.reportes[i].parameters[a].type == 4)
							{
								$scope.reportes[i].parameters[a].model = new Date();
								$scope.reportes[i].parameters[a].opened = false;
							}
						}
					}
				},  
				function errorCallback(response){	
				});	
	}

  $scope.dateOptions = {
    formatYear: 'yyyy',
    //maxDate: new Date(2020, 5, 22),
    //minDate: new Date(),
    startingDay: 1,
    showWeeks: false
  };



}])