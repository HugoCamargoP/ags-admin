//angular.module(appname+'App')
//var app = angular.module(appname+'App', [ 'ngSanitize']);

app.controller(appname+'Report',['$scope','$sce', appname+'Service',

function($scope,$sce, Service)
{	
	$scope.reportes = {};
	$scope.getReportSchema = function()
	{
		Service.getReportSchema().then(
				function successCallback(response)
				{
					$scope.reportes = response.data;
					for ( var i in $scope.reportes) {
						console.log($scope.reportes[i]);
						for ( var a in $scope.reportes[i].parameters) {							
							console.log($scope.reportes[i].parameters[a]);
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