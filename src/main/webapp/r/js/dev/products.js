angular.module('YetiApp')
.controller('YetiProducts',['$scope', 'yetiService',

function($scope, yetiService)
{	
/*Address*/
	$scope.getCountries = function ()
	{
		yetiService.getCountries().then(
				function successCallback(response){
					$scope.paises = response.data.data;
				},
				function errorCallback(response){
					
				})		
	}
	

	$scope.getStates = function ()
	{
		yetiService.getStates().then(
				function successCallback(response){
					$scope.states = response.data.data;
				},
				function errorCallback(response){
					
				})		
	}
	

	$scope.getCities = function (a)
	{
		for (var b in $scope.states) 
		{
			//console.log($scope.states[b].nomEnt);
			if($scope.states[b].nomEnt == a)
			{
				yetiService.getCities($scope.states[b].cveEnt).then(
						function successCallback(response){
							$scope.cities = response.data.data;
						},
						function errorCallback(response){
							
						})	
			}
		}	
	}
	
	$scope.newAddress = function(a)
	{	

		$scope.resumen = true;
		$scope.direccion = false;
		direcciones(3);
		/*
		if($scope.formaddress.$valid)
		{
			$scope.address.user = $scope.usuario;
			yetiService.newAddress($scope.address).then(
					function successCallback(response){
						$scope.address = {};
						$scope.resumen = true;
						$scope.direccion = false;
					},
					function errorCallback(response){
						
					})	
		}
		*/
	}
/*Address*/

/*Pagar paypal*/
$scope.pago = function ()
{
	todoalazador= $scope.carProduct.total;
	console.log('hola en el pago paypal');
	console.log(todoalazador);
	 /*paypal.Button.render({

        env: 'sandbox', // sandbox | production

        // PayPal Client IDs - replace with your own
        // Create a PayPal app: https://developer.paypal.com/developer/applications/create
        client: {
            //sandbox:    'AZDxjDScFpQtjWTOUtWKbyN_bDt4OgqaF4eYXlewfBP4-8aqX3PiV8e1GWU6liB2CUXlkA59kJXE7M6R',
            sandbox:	'AUXQHyLkVRKv_XKtaly04VlR3hwWxAN9AmnTpn9F5QfNVN2MU_dYTYgMD0OsS6ARVnHq4p80Avrual57',
            production: '<insert production client id>'
        },

        // Show the buyer a 'Pay Now' button in the checkout flow
        commit: true,

        // payment() is called when the button is clicked
        payment: function(data, actions) {

            // Make a call to the REST api to create the payment
            return actions.payment.create({
                transactions: [
                    {
                        amount: { total: todoalazador, currency: 'MXN' }
                    	//amount: { total: '0.01', currency: 'USD' }
                    }
                ]
            });
        },

        // onAuthorize() is called when the buyer approves the payment
        onAuthorize: function(data, actions) {

            // Make a call to the REST api to execute the payment
            return actions.payment.execute().then(function() {
                window.alert('Payment Complete!');
            });
        }

    }, '#paypal-button-container');*/
}

/*Pagar paypal*/
	
/*ORDERS*/
// Lista de tipos de carritos
// 2 shopping
// 1 wishlist
	$scope.checkCar = function()
	{
		$scope.getOrder($scope.usuario,2);
	}
	$scope.removeOrder = function (a)
	{
		yetiService.removeOrder(a).then(
				function successCallback(response){
					$scope.getOrder($scope.usuario,2);	
				},
				function errorCallback(response){
				})
	}

	$scope.removeOrder1 = function (a)
	{
		console.log(a);
		yetiService.removeOrder(a).then(
				function successCallback(response){
					$scope.getOrder($scope.usuario,1);
					refnevo();
					updateCarWish($scope.usuario);	
				},
				function errorCallback(response){
				})
	}
	
	$scope.fromWishListToShoppingCar = function (a)
	{
		$scope.order = {};
		$scope.orderDetail = {};
			yetiService.getOrder($scope.usuario,2).then(
					function successCallback(response)
					{
						if(response.data.status != 'ExternalError' && response.data.data != null)
						{
							yetiService.fromWishListToShoppingCar(a).then(
									function successCallback(response){
										refnevo();
										updateCarWish($scope.usuario);
										$scope.getOrder($scope.usuario,1);
									},
									function errorCallback(response){
									})
						}
						else
						{
							$scope.order.user = $scope.usuario;
							$scope.order.state = 2;
							yetiService.addNewOrder($scope.order).then(
									function successCallback(response)
									{
										if(response.data.data != null && response.data.status == 'OK')
										{
											yetiService.fromWishListToShoppingCar(a).then(
													function successCallback(response){
														refnevo();
														updateCarWish($scope.usuario);
														$scope.getOrder($scope.usuario,1);
													},
													function errorCallback(response){
													})
										}
									},
									function errorCallback(response)
									{	
									});
						}
					},
					function errorCallback(response)
					{
						
					});	
	}
	

	$scope.cuantosProd = function(user,status)
	{
		yetiService.getOrder(user,status).then(
				function successCallback(response){
					if(status == 2)
					{
						if(response.data.status == 'ExternalError')
						{
							$scope.cuantosCar = 0 ;
						}
						else
						{
							$scope.cuantosCar = response.data.data.orderDetail.length ;	
						}	
					}
					else 
					{
						if(response.data.status == 'ExternalError')
						{
							$scope.cuantosWish = 0;
						}
						else
						{
							$scope.cuantosWish = response.data.data.orderDetail.length ;
						}
					}
				}, 
				function errorCallback(response){
				});
	}
	
	$scope.carProduct = {};
	$scope.getOrder = function(user,status)
		{	
			$scope.usuario = user;
			yetiService.getOrder(user,status).then(
					function successCallback(response){
						if(response.data.data == null || response.data.data.orderDetail.length < 1)
						{
							$scope.status1 = true;
						}
						else
						{
							$scope.carProduct = response.data.data;
							$scope.carProduct.cuantos = response.data.data.orderDetail.length;
							$scope.carProduct.total = 0;
							for ( var a  in response.data.data.orderDetail ) {
								$scope.carProduct.total+= (response.data.data.orderDetail[a].product.price*response.data.data.orderDetail[a].amount);
							}
							$scope.status1 = false;
						}
					}, 
					function errorCallback(response){
					});
		}
	
	$scope.decrementAmountAtOrderDetail = function(a,b)
	{
		if(b > 1)
		{
			yetiService.decrementAmountAtOrderDetail(a).then(
					function successCallback(){
						$scope.getOrder($scope.usuario,2);
					},
					function errorCallback(){
					})
		}
	}
	
	$scope.incrementAmountAtOrderDetail = function(a)
	{
		yetiService.incrementAmountAtOrderDetail(a).then(
				function successCallback(){
					$scope.getOrder($scope.usuario,2);
				},
				function errorCallback(){
				})
	}
	

/*ORDERS*/
			
/*PRODUCTS*/

	$scope.asignadas = function (a)
	{
		$scope.currentpage = a;
		$scope.getAllProducts();
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
	
	$scope.product = {};
	$scope.prodpage = productosporpagina;
	$scope.getAllProducts = function ()
	{
		yetiService.getProductsCountByFilter($scope.product,$scope.prodpage).then(
				function successCallback(response){
					$scope.ultimo = response.data.data.pages;
					$scope.paginacion();
					if(response.data.data.total > 0 )
					{
						yetiService.getProductsByFilter($scope.product,$scope.currentpage,$scope.prodpage).then(
								function successCallback(response){
									$scope.productos = response.data.data;
								}, 
								function errorCallback(response){	
								});
					}
				}, 
				function errorCallback(response){	
				});
	}

	$scope.skuSize = {};
	$scope.price = {};
	$scope.active = function (a,b,c)
	{
		$scope.price[a] = c; 
		$scope.skuSize[a] =  b;
		active(a,b);
	}
	
	$scope.order = {};
	$scope.orderDetail = {};
	

	$scope.removeOneOrderDetail = function (a)
		{
			yetiService.removeOneOrderDetail(a).then(
					function successCallback(){
							$scope.getOrder($scope.usuario,2);
							updateCarWish($scope.usuario);
					},
					function errorCallback(){
					})		
		}
	
	$scope.removeOneOrderDetail1 = function (a)
	{
		yetiService.removeOneOrderDetail(a).then(
				function successCallback(){
						$scope.getOrder($scope.usuario,1);
						updateCarWish($scope.usuario);
				},
				function errorCallback(){
				})		
	}
	
	$scope.buyProduct =  function (us,sta,sku,url)
	{
		$scope.order = {};
		$scope.orderDetail = {};
		if(us != "")
		{
			yetiService.getOrder(us,sta).then(
					function successCallback(response)
					{
						if(response.data.status != 'ExternalError' && response.data.data != null)
						{
							$scope.order = response.data.data;
							$scope.orderDetail.idOrder 		= $scope.order.id;
							$scope.orderDetail.idProductSku	= sku;
							$scope.orderDetail.amount		= 1;
							
							yetiService.addProduct($scope.orderDetail).then(
								function successCallback(response)
								{	
									window.location = url;
								},
								function errorCallback(response)
								{	
								});
						}
						else
						{
							$scope.order.user = us;
							$scope.order.state = sta;
							yetiService.addNewOrder($scope.order).then(
									function successCallback(response)
									{
										if(response.data.data != null && response.data.status == 'OK')
										{
											$scope.order = response.data.data;
											$scope.orderDetail.idOrder 		= $scope.order.id;
											$scope.orderDetail.idProductSku	= sku;
											$scope.orderDetail.amount		= 1;
											
											yetiService.addProduct($scope.orderDetail).then(
												function successCallback(response)
												{	
													window.location = url;
													updateCarWish(us);
												},
												function errorCallback(response)
												{
												});	
										}
									},
									function errorCallback(response)
									{	
									});
						}
					},
					function errorCallback(response)
					{
						
					});	
		}		
	}
	
	$scope.addProduct = function (us,sta,sku)
	{
		$scope.order = {};
		$scope.orderDetail = {};
		//console.log(sku);
		if(us != "")
		{
			yetiService.getOrder(us,sta).then(
					function successCallback(response)
					{
						if(response.data.status != 'ExternalError' && response.data.data != null)
						{
							$scope.order = response.data.data;
							$scope.orderDetail.idOrder 		= $scope.order.id;
							$scope.orderDetail.idProductSku	= sku;
							$scope.orderDetail.amount		= 1;
							
							yetiService.addProduct($scope.orderDetail).then(
								function successCallback(response)
								{	
									msjexito('agregado');
									refnevo();
									updateCarWish(us);
								},
								function errorCallback(response)
								{	
								});
						}
						else
						{
							$scope.order.user = us;
							$scope.order.state = sta;
							yetiService.addNewOrder($scope.order).then(
									function successCallback(response)
									{
										if(response.data.data != null && response.data.status == 'OK')
										{
											$scope.order = response.data.data;
											$scope.orderDetail.idOrder 		= $scope.order.id;
											$scope.orderDetail.idProductSku	= sku;
											$scope.orderDetail.amount		= 1;
											
											yetiService.addProduct($scope.orderDetail).then(
												function successCallback(response)
												{	
													refnevo();
													msjexito('agregado');
													updateCarWish(us);
												},
												function errorCallback(response)
												{
													msjerror(' al realizar la adicion');
												});	
										}
									},
									function errorCallback(response)
									{	
									});
						}
					},
					function errorCallback(response)
					{
						
					});	
		}
	}
/*PRODUCTS*/
	
}])