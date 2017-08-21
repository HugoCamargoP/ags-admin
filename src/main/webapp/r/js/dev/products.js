//angular.module(appname+'App')
//var app = angular.module(appname+'App', [ 'ngSanitize']);

app.controller(appname+'Prod',['$scope','$sce', appname+'Service',

function($scope,$sce, Service)
{	
/*Address*/
	$scope.getCountries = function ()
	{
		Service.getCountries().then(
				function successCallback(response){
					$scope.paises = response.data.data;
				},
				function errorCallback(response){
					
				})		
	}
	

	$scope.getStates = function ()
	{
		Service.getStates().then(
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
			if($scope.states[b].nomEnt == a)
			{
				Service.getCities($scope.states[b].cveEnt).then(
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
			Service.newAddress($scope.address).then(
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
		Service.removeOrder(a).then(
				function successCallback(response){
					$scope.getOrder($scope.usuario,2);	
				},
				function errorCallback(response){
				})
	}

	$scope.removeOrder1 = function (a)
	{
		Service.removeOrder(a).then(
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
			Service.getOrder($scope.usuario,2).then(
					function successCallback(response)
					{
						if(response.data.status != 'ExternalError' && response.data.data != null)
						{
							Service.fromWishListToShoppingCar(a).then(
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
							Service.addNewOrder($scope.order).then(
									function successCallback(response)
									{
										if(response.data.data != null && response.data.status == 'OK')
										{
											Service.fromWishListToShoppingCar(a).then(
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
		Service.getOrder(user,status).then(
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
			Service.getOrder(user,status).then(
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
			Service.decrementAmountAtOrderDetail(a).then(
					function successCallback(){
						$scope.getOrder($scope.usuario,2);
					},
					function errorCallback(){
					})
		}
	}
	
	$scope.incrementAmountAtOrderDetail = function(a)
	{
		Service.incrementAmountAtOrderDetail(a).then(
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
		$scope.getProductsByFilter();
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
	//$scope.prodpage = productosporpagina;
	
	$scope.deleteimg = function(a, b , c)
	{
		 Service.removeProductDetail(c).then(
			function successCallback(response){
				if(response.data.status == 'OK')
				{
					msjexito('Eliminado');
					$scope.productos[a].productDetails.splice(b, 1);	
				}
				else
				{
					msjerror('Error');
				}
			},
			function errorCallback(){
			})
	}

	$scope.updateProduct = function(a)
	{
		Service.updateProduct($scope.productos[a]).then(
			function successCallback(response){
				if(response.data.status == 'OK')
				{
					$scope.getProductsByFilter();
					msjexito('Exito');
				}
				else
				{
					msjerror('Error');
				}
			},
			function errorCallback(){
			})
	}
	
	$scope.createSkuProduct = function(a)
	{
		console.log($scope.formsnewsize);
		if($scope.formsnewsize.$valid)
		{
			Service.createSkuProduct($scope.newformssize).then(
			function successCallback(response){
				if(response.data.status == 'OK')
				{
					$scope.getProductsByFilter();
					var aux = $scope.newformssize.product;
					$scope.newformssize = {}
					$scope.newformssize.product = aux;
					$scope.productos = response.data.data;
					msjexito('Exito');
				}
				else
				{
					msjerror('Error');
				}
			},
			function errorCallback(){
			})
		}
		else
		{
			msjerror('Error');
		}
	}
	
	$scope.uploadFile = function(files) {
	    var fd = new FormData();
	    //Take the first selected file
	    fd.append("file", files[0]);
	    
	    Service.addProductDetail(fd , $scope.newformssizeimg.id).then(
				function successCallback(response){
					if(response.data.status == 'OK')
					{
						$scope.getProductsByFilter();
						document.getElementById("img").value='';
						msjexito('Exito');
					}
					else
					{
						msjerror('Error');
					}
				},
				function errorCallback(){
				})
	}
	
	$scope.addProductDetail = function()
	{  
		if(!document.getElementById("img").value.length==0)
		{
			if (/.(gif|jpeg|jpg|png)$/i.test(document.getElementById("img").value))
			{
				formdata = new FormData(document.getElementById('formsnewsizeimg'));
				var imagensilla = document.getElementById('img').files[0];
				formdata.append('file', document.getElementById('img').files[0]);
				$scope.newformssizeimg.f = formdata;
				
				//subirarchivo(formdata, $scope.newformssizeimg.id); 
				
				Service.addProductDetail(formdata , $scope.newformssizeimg.id).then(
				function successCallback(response){
					if(response.data.status == 'OK')
					{
						$scope.getProductsByFilter();
						document.getElementById("img").value='';
						msjexito('Exito');
					}
					else
					{
						msjerror('Error');
					}
				},
				function errorCallback(){
				})
				
			}
			else
			{
				msjerror('Subir solo imagenes');
				document.getElementById("img").value='';
				return false;
			}
		}
		else
		{
			msjerror('Selecciona una imagen');
		}
	}
	
	$scope.hascoverflow = function(b,x)
	{
		cover(b,x);
	}
	
	$scope.prodpage = 10000;
	$scope.getProductsCountByFilter = function ()
	{
		Service.getProductsCountByFilter($scope.product,$scope.prodpage).then(
				function successCallback(response){
					$scope.ultimo = response.data.data.pages;
					$scope.paginacion();
					if(response.data.data.total > 0 )
					{
						Service.getProductsByFilter($scope.product,$scope.currentpage,$scope.prodpage).then(
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
	
	$scope.getAllProducts = function()
	{
		Service.getAllProducts().then(
				function successCallback(response){
					if(response.data.data.length > 0 )
					{
							$scope.productos = response.data.data;
					}
				}, 
				function errorCallback(response){	
				});
	}
	
	$scope.addProduct = function()
	{
		Service.addProduct($scope.addpro).then(
				function successCallback(response){
					if(response.data.data.length > 0 || response.data.status == "OK")
					{
							$scope.productos = response.data.data;
					}
				}, 
				function errorCallback(response){	
				});
	}	
	
	$scope.getProductsByFilter = function()
	{
		var b = 1, c = 10000;
		Service.getProductsByFilter($scope.searchprodruct,b,c).then(
				function successCallback(response){
					if(response.data.data.length > 0 || response.data.status == "OK")
					{
							$scope.productos = response.data.data;
					}
				}, 
				function errorCallback(response){	
				});
	}

	$scope.getProductSizes = function()
	{
		Service.getProductSizes().then(
				function successCallback(response){
					if(response.data.data.length > 0 )
					{
							$scope.sizes = response.data.data;
							tallasperronas = response.data.data;
					}
				}, 
				function errorCallback(response){	
				});
	}
	
	$scope.removeSkuProduct = function(a)
	{
		Service.removeSkuProduct(a).then(
				function successCallback(response){
					if(response.data.status == 'OK' )
					{
						$scope.getProductsByFilter();
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
			Service.removeOneOrderDetail(a).then(
					function successCallback(){
							$scope.getOrder($scope.usuario,2);
							updateCarWish($scope.usuario);
					},
					function errorCallback(){
					})		
		}
	
	$scope.removeOneOrderDetail1 = function (a)
	{
		Service.removeOneOrderDetail(a).then(
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
			Service.getOrder(us,sta).then(
					function successCallback(response)
					{
						if(response.data.status != 'ExternalError' && response.data.data != null)
						{
							$scope.order = response.data.data;
							$scope.orderDetail.idOrder 		= $scope.order.id;
							$scope.orderDetail.idProductSku	= sku;
							$scope.orderDetail.amount		= 1;
							
							Service.addProduct($scope.orderDetail).then(
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
							Service.addNewOrder($scope.order).then(
									function successCallback(response)
									{
										if(response.data.data != null && response.data.status == 'OK')
										{
											$scope.order = response.data.data;
											$scope.orderDetail.idOrder 		= $scope.order.id;
											$scope.orderDetail.idProductSku	= sku;
											$scope.orderDetail.amount		= 1;
											
											Service.addProduct($scope.orderDetail).then(
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
		if(us != "")
		{
			Service.getOrder(us,sta).then(
					function successCallback(response)
					{
						if(response.data.status != 'ExternalError' && response.data.data != null)
						{
							$scope.order = response.data.data;
							$scope.orderDetail.idOrder 		= $scope.order.id;
							$scope.orderDetail.idProductSku	= sku;
							$scope.orderDetail.amount		= 1;
							
							Service.addProduct($scope.orderDetail).then(
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
							Service.addNewOrder($scope.order).then(
									function successCallback(response)
									{
										if(response.data.data != null && response.data.status == 'OK')
										{
											$scope.order = response.data.data;
											$scope.orderDetail.idOrder 		= $scope.order.id;
											$scope.orderDetail.idProductSku	= sku;
											$scope.orderDetail.amount		= 1;
											
											Service.addProduct($scope.orderDetail).then(
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