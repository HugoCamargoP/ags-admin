angular.module('YetiApp')

.controller('YetiUser',['$scope', 'yetiService',

function($scope, yetiService)
{	
	$scope.logginRest = function ()
	{
		yetiService.logginRest($scope.user).then(
				function successCallback(response) {
				}, function errorCallback(response) {
				});
	}
	
	$scope.userRegister = function ()
	{
		$scope.registro.birthday = born;
		
		if($scope.registro.system == 0)
		{
			$scope.registro.height = $scope.registro.m+"."+$scope.registro.cm;
		}
		else
		{
			$scope.registro.height = $scope.registro.ft+"."+$scope.registro.inc;	
		}
		//console.log($scope.registro);
		if($scope.registroform.$valid)
		{
			yetiService.userRegister($scope.registro).then(
				function successCallback(response) {
					console.log(response);
					if(response == 1 || 1)
					{
						$scope.registro = {};
						msjexito('New acount created');
					}
					else
					{
						msjerror("User already registred");
					}
				}, function errorCallback(response) {
				});
		}
		
	}
}])