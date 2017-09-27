//angular.module(appname+'App')
//var app = angular.module(appname+'App', [ 'ngSanitize']);

app.controller(appname+'Report',['$scope','$sce', appname+'Service',

function($scope,$sce, Service)
{	
	$scope.getReportSchema = function()
	{
		Service.getReportSchema().then(
				function successCallback(response)
				{
						$scope.reportes = response.data;
				}, 
				function errorCallback(response){	
				});	
	}
}])