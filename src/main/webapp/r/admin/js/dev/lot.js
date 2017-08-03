angular.module(appname+'App')

.controller(appname+'lot',['$scope', appname+'Service',

function($scope, Service)
{
	$scope.createUser = function ()
	{
		if($scope.formregister.$valid)
		{
			if($scope.user.password == $scope.user.repassword)
			{
				Service.createUser($scope.user).then(function successCallback(response){
					console.log(response);
					msjexito('Usuario creado correctamente');
				},
				function errorCallback(response){
					msj(response.data.error);
				});
			}
			else
			{
				msjerror("Las contraseñas no coinciden");
				$scope.user.password = "";
				$scope.user.repassword = "";
			}	
		}
		else
		{
			msjerror("Por favor completar el formulario");
		}
	}
	
	$scope.funciona = function ()
	{
		console.log('funciona este chunche');
		msjexito('Usuario creado correctamente');
	}
	
	$scope.getMessage = function ()
	{
		Service.getMessage().then(function successCallback(response){
			console.log(response);
		},
		function errorCallback(response){
			msj('Error');
		});
	}
}])