/*
angular.module('YetiApp')
.controller('orders',['$scope', '$timeout', 'yetiService',
 
 */
angular.module(appname+'App')

.controller(appname+'orders',['$scope', appname+'Service',
function($scope , Service)
{	

	$scope.msjTemp = 'vacio';
	$scope.getMessage = function(a,b)
	{
		Service.getMessage(a).then(
				function successCallback(response){
					if(b == 0 )
					{msjexito(response.data.data);}
					else 
					{msjerror(response.data.data);}
				},
				function errorCallback(response){
					$scope.getMessage('err.error',1);			
				});	
	}
	
	$scope.ordersNew = {};
	$scope.currectPage1 = 1;
	$scope.currectPage = 1;
	$scope.rowsByPage = 10;
	$scope.paginasGlobals;
	$scope.paginacion = 0
	var theCount = 1;
	
	$scope.getOrders = function()
	{
		
		if(theCount == undefined)
		{
			theCount = 1;
		}
		$scope.ordersNew = theCount;
		$scope.paginasGlobals = parseInt( $scope.ordersNew / $scope.rowsByPage );	
		if($scope.paginasGlobals == 0)
		{
			for (var i = 1; i <= theCount ;  i++) {
				addremoveclass('hidden','visible'+i);
			}
		}
		else
		{
			$scope.currectPage = $scope.paginasGlobals+1;
			for (var i = 1; i <= $scope.rowsByPage;  i++) {
				addremoveclass('hidden','visible'+i);
			}
		}
		var arr = new Array();
			for (var i = 0; i < $scope.currectPage ; i++) {
				arr.push(i+1);
			}
		$scope.currectPage = arr;
	}
	
	$scope.cambiaPage = function(a)
	{
		//desseleccionar(a);
		//addHide('todas');
		for (var i = ((a * $scope.rowsByPage)-9); i <= (a * $scope.rowsByPage);  i++) {
			addremoveclass('hidden','visible'+i);
		}
	}

	$scope.status = {};
	$scope.getStatus = function()
	{
		Service.getStatus().then(
		function successCallback(response){
			$scope.status = response.data.data;
		},
		function errorCallback(response){
			$scope.getMessage('err.error',1);			
		});	
	}
	
	$scope.ord = {};
	$scope.updateOrderStatus = function()
	{
		console.log($scope.ord);
		Service.updateOrderStatus($scope.ord).then(
		function successCallback(response){
			$scope.ord = {};
			$scope.getMessage('err.error',1);	
			location.reload();
		},
		function errorCallback(response){
			$scope.getMessage('err.error',1);			
		});	
	}

	$scope.getAddressById = function(a)
	{
		console.log(a+'address');
		Service.getAddressById(a).then(
		function successCallback(response){
			$scope.address = response.data.data;
		},
		function errorCallback(response){
			$scope.getMessage('err.error',1);			
		});	
	}
	
	$scope.getOrderById = function(a)
	{
		Service.getOrderById(a).then(
		function successCallback(response){
			$scope.orderComplete = response.data.data;
		},
		function errorCallback(response){
			$scope.getMessage('err.error',1);			
		});	
	}

	$scope.removeOrder = function (a)
	{
		//console.log(document.getElementById('historial').value);
		var nuevo = document.getElementById('historial').value;
		
		Service.removeOrder(a).then(
			function successCallback(response){
				/*
				$scope.getOrder($scope.usuario,3);	
				refnevo();
				updateCarWish($scope.usuario);
				*/
				window.location=''+nuevo+'?delete=true';
			},
			function errorCallback(response){
			})
		
	}
	
	$scope.cancelRequestedOrder = function (a)
	{
		var paymentLink = document.getElementById('paymentLink').value;
		Service.cancelRequestedOrder(a).then(
			function successCallback(response){
				//console.log(response.data);
				//console.log(response.data.error + ' error y status ' + response.data.status);
				if(response.data.error == '' && response.data.status == 'OK')
				{
					window.location=''+paymentLink;	
				}
				else
				{
					//$scope.getMessage('conectError',1);
					msjerror(response.data.error);
				}
			},
			function errorCallback(response){
			})
		
	}

	$scope.getCountries = function ()
	{
		Service.getCountries().then(
				function successCallback(response){
					$scope.paises = response.data.data;
				},
				function errorCallback(response){
					
				})		
	}
	$scope.order = 4; 
	$scope.page = 1;
	$scope.inPage = String(10);
	$scope.otra = {};
	$scope.cuanto = true;
	$scope.currentpage  = 1;
	$scope.getOrdersByFilter = function (a,b)
	{
		$scope.otra.state = a;
		Service.getOrdersCountByFilter($scope.otra , $scope.inPage).then(
		function successCallback(response){
			console.log(response);
			$scope.ultimo = response.data.pages;
			$scope.paginacion();

			Service.getOrdersByFilter($scope.otra, $scope.currentpage ,$scope.inPage).then(
				function successCallback(response){
					
					$scope.ordenes = response.data.data;
					if($scope.ordenes.length > 0)
					{
						$scope.cuanto = true;
					} 
					else
					{
						$scope.cuanto = false;
					}
					
					console.log($scope.cuanto);
				},
				function errorCallback(response){
					
				})		
		},
		function errorCallback(response){
			
		})	
	}
	
	$scope.asignadas = function (a)
	{
		$scope.currentpage = a;
		$scope.getOrdersByFilter($scope.otra.state , $scope.currentpage);
	}

	$scope.asignadas1 = function (a)
	{
		$scope.currentpage = a;
	}
	
	$scope.paginacion = function ()
	{
		$scope.antes ={};
		if($scope.ultimo <= menos)
		{
			menos = $scope.ultimo;
		}
		var antes = $scope.currentpage-menos,hasta = $scope.currentpage+menos;
		if (antes < 1) {
			 antes = 1;
		}
		if(hasta > $scope.ultimo)
		{
			hasta = $scope.ultimo;
		}
		for ( antes ; antes <= hasta; antes++) {
			$scope.antes [antes] = antes;
		}
	}
	
	
}])