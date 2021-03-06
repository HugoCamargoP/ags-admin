//angular.module(appname+'App')
//var app = angular.module(appname+'App', [ 'ngSanitize']);

app.controller(appname+'Prod',['$scope','$sce', appname+'Service',

function($scope,$sce, Service)
{	
	$scope.modifyItem = {};
	$scope.p = {};
	
	$scope.pdate = new Date();
	$scope.addpro = {};
	$scope.addpro.releaseDate =  new Date();
	
	$scope.showFlag = false;
	$scope.listaFlag = true;
	$scope.editaFlag = false;
	$scope.whoIsSelected = "";
	
	//^\d{4}([\-/.])(0?[1-9]|1[1-2])\1(3[01]|[12][0-9]|0?[1-9])$
	

	//var b = 1, c = 10;
	//getProductsByFilter($scope.searchprodruct,b,c)
	//getProductsByFilter = function(product, page, inPage){
	$scope.currentpage = 1;
	$scope.cuantoPage = String(10);
	$scope.ultimo = 0;
	$scope.paginacion = function ()
	{
		var menos = 2;
		console.log($scope.ultimo+' ultimo');
		$scope.antes ={};
		if($scope.ultimo <= menos)
		{
			menos = $scope.ultimo;
		}
		var antes = $scope.currentpage-menos,hasta = $scope.currentpage+menos;
		if (antes < 1) {
			 antes = 1;
		}
		console.log(hasta +' '+ $scope.ultimo);
		if(hasta > $scope.ultimo)
		{
			hasta = $scope.ultimo;
		}
		for ( ; antes <= hasta; antes++) {
			$scope.antes [antes] = antes;
		}
	}
	
	$scope.dateOptions = {
	    formatYear: 'yyyy',
	    //maxDate: new Date(2020, 5, 22),
	    //minDate: new Date(),
	    startingDay: 1,
	    showWeeks: false,
	    timezone: 'utc'
	};
	
	$scope.selectFrontBack = function(a,b,c)
	{
		if(a == 1)
		{	
			for ( var q in c.productDetails ) 
			{
				if(c.productDetails[q].side != 2)
				{
					c.productDetails[q].side = 3;
				}
			}
			c.productDetails[b].side = a;
		}
		else if(a == 2)
		{	
			for ( var q in c.productDetails ) 
			{
				if(c.productDetails[q].side != 1)
				{
					c.productDetails[q].side = 3;
				}
			}
			c.productDetails[b].side = a;
		}
		$scope.updateProductNew(c);
	}
	
	$scope.checkFechirri = function()
	{
		if($scope.p.releaseDate == undefined || $scope.p.releaseDate == '')
		{
			$scope.p.releaseDate = '';
			msjerror('Please complete the form');
			return false;
		}
		else
		{
			return true;
		}
	}
	
	$scope.updateProductSetDate = function()
	{
		console.log($scope.p);
		$scope.p.strReleaseDate = relaseDatemod+' fechirri';
		console.log($scope.p);
	}
	
	$scope.updateProductNew = function(a)
	{
		/*
		console.log(relaseDatemod+'antes');
		$scope.p.strReleaseDate = relaseDatemod;
		console.log(relaseDatemod+' despues');
		console.log($scope.p);
		*/
		console.log($scope.p);
		$scope.p.strReleaseDate = relaseDatemod+' fechirri';
		console.log($scope.p);
		
		console.log(checkDates(relaseDatemod));
		if(checkDates(relaseDatemod) && $scope.updateproductn.$valid)
		{
			a.strReleaseDate = relaseDatemod;
			Service.updateProduct(a).then(
			function successCallback(response){
				if(response.data.status == 'OK')
				{
					$scope.getProductsByFilter();
					$scope.getProductById($scope.p.id);
					console.log($scope.p);
					msjexito('Success');
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
			if(!checkDates(relaseDatemod))
			{	
				msjerror('Please check field Release Date');
			}
			else{
				msjerror('Please complete the form');
			}
			a.strReleaseDate = '';
		}
	}
	
	$scope.getConfigEntity = function()
	{
		Service.getConfigEntity().then(
		function successCallback(response){
			if(response.data.status == 'OK')
			{
				$scope.conf = response.data.data;
			}
			else
			{
				msjerror('Error');
			}
		},
		function errorCallback(){
		})
	}
	
	$scope.updateConfigEntity = function()
	{
		Service.updateConfigEntity($scope.conf).then(
		function successCallback(response){msjexito('Change done');},
		function errorCallback(){})
	}
	
	
	$scope.getProductById = function(a)
	{
		console.log('getProductById '+a);
		Service.getProductById(a).then(
		function successCallback(response){
			if(response.data.status == 'OK')
			{
				$scope.p = response.data.data;
				$scope.setDate($scope.p.releaseDate);
			}
			else
			{
				msjerror('Error');
			}
		},
		function errorCallback(){
		})
	}
	
	$scope.haytallaExistente = false;
	
	$scope.checkIfExist = function(a)
	{
		$scope.haytallaExistente = false;
		for ( var q in $scope.p.skuProduct) 
		{
			console.log($scope.p.skuProduct[q].size+' a b '+a);
			if($scope.p.skuProduct[q].size == a)
			{
				$scope.haytallaExistente = true;
			}
		}
		console.log($scope.haytallaExistente+' haytalla');
	}
	/*
	$scope.checkDates = function(a)
	{
		console.log('lo que viene del checkDates '+a);
		var str = "^\d{4}([\-/.])(0?[1-9]|1[1-2])\1(3[01]|[12][0-9]|0?[1-9])$";
		var patt = new RegExp(str);
		var res = patt.test(a);
		//console.log(str);
		//console.log(patt);
		console.log('checkDates '+res);
		
		var string = "sample1";
		var re = new RegExp("^([a-z0-9]{5,})$");
		if (re.test(string)) {
		    console.log("Valid");
		} else {
		    console.log("Invalid");
		}
		
		return res;
	}
	*/
	
	$scope.setDate = function(a) {
		//console.log('set date '+a);
		/*
	    console.log('ReleaseDate nuevo '+a);
		var aux = a.split('-');
	 	console.log('Aux '+aux);
	 	
	    var d = new Date();
	    $scope.p.releaseDate = d.setUTCFullYear(aux[0], aux[1], aux[2]);
	    */
	 	//$scope.p.releaseDate = new Date(a+'T12:00:00-06:00');
	 	//$scope.p.releaseDate = new Date(a).setHours(0,0,0,0);
	 	//$scope.p.releaseDate = new Date(aux[0], aux[1], aux[2]);
	 	//$scope.p.releaseDate = moment(aux[2]+"/"+aux[1]+"/"+aux[0], "YYYY-MM-DD").format();
	};

	$scope.activaEditMode = function(p,l)
	{
		$scope.whoIsSelected = l;
		$scope.showFlag = false;
		$scope.listaFlag = false;
		$scope.editaFlag = true;
		$scope.modifyItem = p;
		$scope.p = p;
		relaseDatemod = $scope.p.strReleaseDate;
		$scope.setDate($scope.p.releaseDate);
		//console.log($scope.p);
		//console.log(" edita flag activaEditaMode");
	}

	$scope.showList = function()
	{
		$scope.getProductsByFilter();
		$scope.showFlag = false;
		$scope.listaFlag = true;
		$scope.editaFlag = false;
		//console.log('showList');
	}
		 
	$scope.showItem =  function(p,l)
	{
		$scope.whoIsSelected = l;
		$scope.showFlag = true;
		$scope.listaFlag = false;
		$scope.editaFlag = false;
		$scope.modifyItem = p;
		$scope.p = p;
		//$scope.setDate($scope.p.releaseDate);
		//console.log($scope.p);
		//console.log(" edita flag showItem");
	}
	
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
			$scope.newformssize.department = 1;
			Service.createSkuProduct($scope.newformssize).then(
			function successCallback(response){
				if(response.data.status == 'OK')
				{
					var aux = $scope.newformssize.product;
					$scope.newformssize = {}
					$scope.newformssize.product = aux;
					$scope.productos = response.data.data;
					msjexito(response.data.error);
					$scope.getProductsByFilter();
					$scope.getProductById($scope.p.id);
					closeModal('newYetibera');
				}
				else
				{
					msjerror(response.data.error);
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
						msjexito('Success');
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
						msjexito('Success');
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
				msjerror('Error: Upload images');
				document.getElementById("img").value='';
				return false;
			}
		}
		else
		{
			msjerror('Select image');
		}
	}
	
	$scope.muchasimg = function()
	{
		console.log(allfilestemp);
		/*
		Service.addProductDetailList(fd,9).then(
			function successCallback(response)
			{
				
			}, 
			function errorCallback(response){	
			});
		*/
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
					console.log(response.data.data + ' lo que viene del response en get product');
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
	
	$scope.subeVariasImages = function()
	{
		fd = new FormData();
		var hayimgtoupload = false;
		for ( var a in allfilestemp) 
		{
			hayimgtoupload = true;
		    fd.append("file", allfilestemp[a]);
		}
		
		if(hayimgtoupload)
		{
			Service.addProductDetailList(fd,$scope.p.id).then(
			function successCallback(response)
			{
				$scope.addpro =  {};
				$scope.addpro.skuProduct = [];
				$scope.sizes = [];
				$scope.getProductSizes();
				$scope.getProductsByFilter();
				allfilestemp = [];
				clearDiv('.statusId');
				msjexito('Product added');
				if(response.data.error != "")
				{
					var errorTemp = response.data.error.split('||');
					var mensajeerrror = ''; 
					for ( var v in errorTemp) {
						 mensajeerrror += errorTemp[v]+'<br />';
					}
					msjerror(mensajeerrror);
					closeModal('newProducto');
				}
				$scope.getProductById($scope.p.id);
			}, 
			function errorCallback(response){	
				msjerror(response.data.error);
			});	
		}
		else
		{
			msjerror('Please select images');
		}
	}
	
	$scope.quienEnSeCreo = '';
	$scope.seCreoNewProduct = false;
	$scope.createProduct = function()
	{
		console.log(relaseDatemod1);
		$scope.addpro.strReleaseDate = relaseDatemod1;
		console.log(checkDates(relaseDatemod1));
		if(checkDates(relaseDatemod1))
		{
			objetcauz = []
			var inter = 0 ;
			for ( var a in $scope.addpro.skuProduct) {
				if($scope.addpro.skuProduct[a].sku != undefined && $scope.addpro.skuProduct[a].price != undefined && $scope.addpro.skuProduct[a].stock != undefined )
				{
					objetcauz[inter] = $scope.addpro.skuProduct[a];
					inter++;
				}
			}
			$scope.addpro.skuProduct = objetcauz;
			console.log(allfilestemp.length);
			$scope.getProductSizes();
			
	
			$scope.addpro.department = 1;
			Service.createProduct($scope.addpro).then(
			function successCallback(response){
				if(response.data.status == "OK" && response.data.data.id != "")
				{
					fd = new FormData();
					var hayimgtoupload = false;
					for ( var a in allfilestemp) 
					{
						hayimgtoupload = true;
					    fd.append("file", allfilestemp[a]);
					}

					$scope.seCreoNewProduct = true;
					$scope.quienEnSeCreo = response.data.data.id;
					if(hayimgtoupload)
					{
						Service.addProductDetailList(fd,response.data.data.id).then(
						function successCallback(response)
						{
							$scope.addpro =  {};
							$scope.addpro.skuProduct = [];
							$scope.sizes = [];
							$scope.getProductSizes();
							$scope.getProductsByFilter();
							allfilestemp = [];
							clearDiv('.statusId');
							msjexito('Product added');
							if(response.data.error != "")
							{
								var errorTemp = response.data.error.split('||');
								var mensajeerrror = ''; 
								for ( var v in errorTemp) {
									 mensajeerrror += errorTemp[v]+'<br />';
								}
								msjerror(mensajeerrror);
								closeModal('newProducto');
							}
							closeModal('newProducto');
						}, 
						function errorCallback(response){	
							msjerror(response.data.error);
						});	

						closeModal('newProducto');
					}
					else
					{
						$scope.addpro =  {};
						$scope.addpro.skuProduct = [];
						$scope.sizes = [];
						$scope.getProductSizes();
						$scope.getProductsByFilter();
						allfilestemp = [];
						closeModal('newProducto');	
					}
				}
			}, 
			function errorCallback(response){	
			});
		}
		else
		{
			if(!checkDates(relaseDatemod1))
			{	
				msjerror('Please check field Release Date');
			}
			else{
				msjerror('Please complete the form');
			}
			$scope.addpro.releaseDate = '';
		}
		
	}	
	
	$scope.getProductsByFilter = function()
	{  
		console.log($scope.currentpage + ' currentpagecurrentpage');
		Service.getProductsCountByFilter($scope.searchprodruct , $scope.cuantoPage).then(
			function successCallback(response){
				console.log(response);
				$scope.ultimo = response.data.data.pages;
				$scope.paginacion();
				
				//console.log($scope.searchprodruct);
				console.log($scope.currentpage);
				console.log($scope.cuantoPage);
				
				Service.getProductsByFilter($scope.searchprodruct,$scope.currentpage, $scope.cuantoPage).then(
				function successCallback(response){
					if(response.data.data.length > 0 || response.data.status == "OK")
					{
						$scope.productos = response.data.data;

						if($scope.seCreoNewProduct)
						{
							for ( var a in $scope.productos) {
								console.log(a+' secreonewproductos');
								if( $scope.productos[a].id == $scope.quienEnSeCreo)
								{
									console.log('secreonewproductos');
									$scope.whoIsSelected = a;
									$scope.activaEditMode($scope.productos[a],a);
								}
							}
							$scope.quienEnSeCreo = '';
							$scope.seCreoNewProduct = false;
						}
						
						console.log($scope.whoIsSelected+' $scope.whoIsSelected');
						if($scope.whoIsSelected != "")
						{
							$scope.modifyItem = $scope.productos[$scope.whoIsSelected];
							$scope.p = $scope.productos[$scope.whoIsSelected];	
						}
					}
				}, 
				function errorCallback(response){	
				});
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
	
	
	$scope.removeProduct = function(a)
	{
		var confirmacion = confirm('¿Delete product?');
		console.log(confirmacion);
		if(confirmacion)
		{
			Service.removeProduct(a).then(
			function successCallback(response){
				if(response.data.status == 'OK' )
				{
					$scope.getProductsByFilter();
					showList();
				}
			}, 
			function errorCallback(response){	
			});
		}
	}
	
	$scope.removeSkuProduct = function(a,b)
	{
		if(confirm('¿Delete this SKU?'))
		{
			Service.removeSkuProduct(a).then(
					function successCallback(response){
						if(response.data.status == 'OK' )
						{
							console.log('delete sku '+$scope.whoIsSelected);
							//$scope.p.skuProduct[b].borrado = true; 
							$scope.getProductsByFilter();
							$scope.getProductById($scope.p.id);
						}
					}, 
					function errorCallback(response){	
					});
		}
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

	$scope.nuevo1 = function()
	{
		//$scope.registro.measure[0].strBirthday = born;
		//console.log($scope.registro);
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