angular.module(appname+'App')

.controller(appname+'user',['$scope', appname+'Service',

	
function($scope, Service)
{	
/*Declarcaion de variables**/
	$scope.consulta = {};
	$scope.email = '';
	$scope.filter = '';
	cheksboxes = [];
	usuarios1 = [];
	$scope.check = [];
/*Declarcaion de variables**/
	
	$scope.statuscheck = function(a)
	{
		console.log($scope.check[a]);
		if(cheksboxes.indexOf(a) == -1 && $scope.check[a])
		{
			cheksboxes.push(a);
		}
		else if(cheksboxes.indexOf(a) != -1 && !$scope.check[a])
		{
			cheksboxes.splice(a, 1);
		}
		console.log(cheksboxes);
	}
	
	$scope.cambiaSelected = function()
	{
		if(cheksboxes.length > 0)
		{
			for ( var c in cheksboxes) {
				 $scope.usuarios[cheksboxes[c]].type = $scope.per; 
				 usuarios1.push($scope.usuarios[cheksboxes[c]]);
			}
			Service.userUpdateRol(usuarios1).then(function successCallback(response){
				if(response.data.data.length > 0 )
				{
					if(response.data.status == 'OK')
					{
						$scope.getUserByFilter();
					}
				}
				else
				{
					msjerror(response.data.status);
				}
			},
			function errorCallback(){
				msjerror(response.data.status);
			})
		}
	}
	
	
	$scope.getUserByFilter = function ()
	{
		$scope.cheksboxes = {};
		$scope.filter = document.getElementById('filter').value;
		Service.getUserByFilter($scope.email,$scope.filter).then(function successCallback(response){
			if(response.data.data.length > 0 )
			{
				$scope.usuarios = response.data.data;
				cheksboxes = [];
				usuarios1 = [];
				$scope.check = [];
			}
			else
			{
				msjerror(response.data.status);
			}
		},
		function errorCallback(){
			msjerror(response.data.status);
		})
	}
	
	$scope.userUpdateRol = function ()
	{
		Service.userUpdateRol($scope.usuarios).then(function successCallback(response){
			if(response.data.data.length > 0 )
			{
				if(response.data.status == 'OK')
				{
					msjexito('Cambio realiazdo');
					$scope.usuarios = response.data.data;
				}
			}
			else
			{
				msjerror(response.data.status);
			}
		},
		function errorCallback(){
			msjerror(response.data.status);
		})
	}
	
	$scope.getPreserveByPrivateNeighborhood = function ()
	{
		$scope.numeroFraccionamiento = 1;
		Service.getPreserveByPrivateNeighborhood($scope.numeroFraccionamiento).then(function successCallback(response){
			if(response.data.data.length > 0 )
			{
				$scope.condominios = response.data.data;
			}
		},
		function errorCallback(){
		})
	}
	
	

	$scope.getStreetByPreserve = function (a)
	{
		Service.getStreetByPreserve(a).then(function successCallback(response){
			if(response.data.data.length > 0 )
			{
				$scope.calles = response.data.data;
			}
		},
		function errorCallback(){
		})
	}
	
	
	$scope.getCarsByUser = function ()
	{
		Service.getCarsByUser($scope.user.id).then(function successCallback(response){
			if(response.data.data.length > 0 )
			{
				$scope.vehiculos = response.data.data;
			}
			else
			{
				delete $scope.vehiculos;
			}
		},
		function errorCallback(){
		})
	}
	
	$scope.getLotsByUser = function ()
	{
		Service.getLotsByUser($scope.user.id).then(function successCallback(response){
			if(response.data.data.length > 0 )
			{
				$scope.lote = response.data.data;
			}
			else
			{
				delete $scope.lote;
			}
		},
		function errorCallback(){
		})
	}

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
	$scope.strbusqueda = "";
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
	
	
	$scope.setMessage = function ()
	{
		Service.setMessage('hola').then(function successCallback(response){
			console.log(response);
		},
		function errorCallback(response){
			msj('Error');
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

	$scope.createCar = function ()
	{
		if($scope.formvehiculo.$valid)
		{
			if($scope.user.id != '' && $scope.user.id > 0)
			{
				$scope.car.idOwner = $scope.user.id;
				Service.createCar($scope.car).then(function successCallback(response){
					console.log(response);
					msjexito('Se agrego el vehículo al usuario con exito');
					$scope.car = {};
					$scope.getCarsByUser();
				},
				function errorCallback(response){
					msjerror(response.data.error);
				});
			}
			else
			{
				msjerror("Por favor primero registra o selecciona un usuario");
			}
		}
		else
		{
			msjerror("Por favor completar el formulario");
		}
	}
	
	$scope.createLot = function ()
	{
		if($scope.formlot.$valid)
		{
			if($scope.user.id != '' && $scope.user.id > 0)
			{
				$scope.lot.ownerId = $scope.user.id;
				Service.createLot($scope.lot).then(function successCallback(response){
					$scope.lot = {};
					$scope.getLotsByUser();
					msjexito('Se agrego el lote al usuario con exito');
				},
				function errorCallback(response){
					msjerror(response.data.error);
				});
			}
			else
			{
				msjerror("Por favor primero registra o selecciona un usuario");
			}
		}
		else
		{
			msjerror("Por favor completar el formulario");
		}
	}

	$scope.deleteLot = function (a)
	{
		if(confirm('¿Desea eliminar el lote '+a+'?'))
		{
			Service.deleteLot(a).then(function successCallback(response){
				$scope.getLotsByUser();
			},
			function errorCallback(response){
				msjerror(response.data.error);
			});
		}
	}
	
	$scope.deleteCar = function (a)
	{
		if(confirm('¿Desea eliminar el auto?'))
		{
			Service.deleteCar(a).then(function successCallback(response){
				$scope.getCarsByUser();
			},
			function errorCallback(response){
				msjerror(response.data.error);
			});
		}
	}
}])