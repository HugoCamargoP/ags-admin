/*
angular.module('YetiApp')
.controller('orders',['$scope', '$timeout', 'yetiService',
 
 */
angular.module(appname+'App')

.controller(appname+'orders',['$scope', appname+'Service',
function($scope, $timeout , yetiService)
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
	
	$scope.getOrders = function()
	{
		/*
		if(theCount == undefined)
		{
			theCount = 1;
		}*/
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
		desseleccionar(a);
		addHide('todas');
		for (var i = ((a * $scope.rowsByPage)-9); i <= (a * $scope.rowsByPage);  i++) {
			addremoveclass('hidden','visible'+i);
		}
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
	$scope.page = 10;
	$scope.inPage = 1;
	$scope.getOrdersByFilter = function (a,b,c)
	{
		Service.getOrdersByFilter(a,$scope.page,c).then(
				function successCallback(response){
					$scope.ordernes = response.data.data;
				},
				function errorCallback(response){
					
				})		
	}
	
}])