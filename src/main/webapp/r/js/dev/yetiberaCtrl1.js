angular.module('YetiApp')
.controller('YetiController',['$scope', 'yetiService',

function($scope, yetiService)
{
	$scope.logout = function() 
	{
		yetiService.loggoutRest().then(
				function successCallback(response) {
					window.location.reload();
				}, function errorCallback(response) {
				});
	}
	
	$scope.login = function ()
	{
		if($scope.loginnav.$valid == true)
		{
			yetiService.logginRest($scope.user).then(
					function successCallback(response) {
						if(response.data.status == 0)
						{

							msjexito(response.data.data);
							window.location.reload();
							/*
							
							setTimeout(function() {
								window.location.reload();
						  	    },1000);
						  	*/
						}
						else
						{
							msjerror(response.data.data);
						}
					}, function errorCallback(response) {
					});			
		}
	}
}])