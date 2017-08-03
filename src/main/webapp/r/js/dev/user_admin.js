angular.module(appname+'App')

.controller(appname+'userAdmin',['$scope', appname+'Service',

	
function($scope, Service)
{	
/*Declarcaion de variables**/
	$scope.consulta = {};
	$scope.strbusqueda = "";
/*Declarcaion de variables**/
	
	$scope.getUserById = function (a)
	{
		Service.getUserById(a).then(function successCallback(response){
			$scope.user = response.data.data;
			delete $scope.resultadoconsulta;
			$scope.strbusqueda = $scope.user.userCompleteName; 
			msjexito('Datos del usuario cargados');
			$scope.getCarsByUser();
			$scope.getLotsByUser();
		},
		function errorCallback(){})
	}
	
	$scope.getUserByString = function ()
	{
		$scope.consulta.consulta = $scope.strbusqueda;
		$scope.consulta.limite 	 = 3;
		Service.getUserByString($scope.consulta).then(function successCallback(response){
			$scope.resultadoconsulta = response.data.data; 
		},
		function errorCallback(response){
			msjerror('Error');
		});
	}
	
	
	$scope.createUser = function ()
	{
		if($scope.formregister.$valid)
		{
			if($scope.user.password == $scope.user.repassword)
			{
				Service.createUser($scope.user).then(function successCallback(response){
					console.log(response);
					msjexito('Usuario creado correctamente');
					$scope.busqueda = true;
					$scope.user = response.data.data;
					$scope.getCarsByUser();
					$scope.getLotsByUser();
					
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

	$scope.createUpdate = function ()
	{
		if($scope.formupdate.$valid)
		{
			Service.updateUser($scope.user).then(function successCallback(response){
				console.log(response);
				msjexito('Usuario actualizado');
				$scope.getCarsByUser();
				$scope.getLotsByUser();
			},
			function errorCallback(response){
				msjerror(response.data.error);
			});
		}
		else
		{
			msjerror("Por favor completar el formulario");
		}
	}
}])