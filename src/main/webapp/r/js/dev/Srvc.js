/**
 * @constructor
 * @param http
 * @param url
 * @returns
 */
function Service(http, url) 
{
    /************ORDERS**************/
   
    //Trae la orden que corresponda al usuario y estado en que se encuentre (wishlist,shopping car, etc)
        //Parámetros necesarios: String user, Integer status
    this.getOrder = function(user, status){
        return http({
            'method': 'GET',
            'url': url + '/user_order/' +user + ' ' + status
        })
    }   
   
    //Trae las ordenes que correspondan a los campos no nulos del objeto order
        //Parámetros necesarios: Order order, Integer page, Integer inPage (inPage = cuantos por página)
    this.getOrdersByFilter = function(order, page, inPage){
        var path = "";
        path = path + "creation=" + order.creation + "&state=" + order.state + "&user=" + order.user;
        return http({
            'method': 'GET',
            'url': url + '/get_order/filter' +path + ' ' + page + ' ' + inPage
        })
    }
   
    //Trae el status, total de ordenes, el número de páginas que correspondan a los campos no nulos del objeto order
        //Parámetros necesarios: Order order, Integer inPage (inPage = cuantos por página)
    this.getOrdersCountByFilter = function(order, inPage){
        var path = "";
        path = path + "creation=" + order.creation + "&state=" + order.state + "&user=" + order.user;
        return http({
            'method': 'GET',
            'url': url + '/get_count_order/filter' +path + ' ' + inPage
        })
    }   

   
    //Crea una nueva orden (aun sin productos) y regresa el objeto con id incluido
        //Parámetros necesarios: Order order (extra data de la entidad no necesaria)
    this.addNewOrder = function(order){
        return http.post(url+'/new_order',order)
    }
   
    //Agrega un producto a la orden y regresa el objeto con su id
        //Parámetros necesarios: OrderDetail orderDetail
    this.addProduct = function(orderDetail){
        return http.post(url+'/add_product',orderDetail)
    }
   
    //Elimina una orden y su detalle correspondiente
        //Parámetros necesarios: Integer idOrder
    this.removeOrder = function(idOrder){
        return http({
            'method': 'DELETE',
            'url': url + '/new_order/' + idOrder
        })
    }
   
    //Obtiene la orden con su detalle incluido (Order con extra data)
        //Parámetros necesarios: Integer idOrder
    this.getOrderById = function(idOrder){
        return http({
            'method': 'GET',
            'url': url + '/get_order/' + idOrder
        })
    }
   
    //Elimina una orden y su detalle correspondiente
        //Parámetros necesarios: Integer idOrder
    this.removeOneOrderDetail = function(idOrderDetail){
        return http({
            'method': 'DELETE',
            'url': url + '/add_product/' + idOrderDetail
        })
    }
   
    //Actualiza la orden deseada pero no modifica su estado (Regresa objeto actualizado)
        //Parámetros necesarios: Order order
    this.updateOrder = function(order){
        return http.put(url + '/update_order',order)
    }
   
    //Actualiza el estado de la orden y agrega informacion al historial
        //Parámetros necesarios: Order order
    this.updateOrderStatus = function(order){
        return http.put(url + '/update_state',order)
    }
   
    //Actualiza el estado individual de un orderDetail para llevarlo de wishList a ShoppingCar
        //Parámetros necesarios: OrderDetail orderDetail
    this.fromWishListToShoppingCar = function(orderDetail){
        return http.put(url + '/wish_car',orderDetail)
    }
   
    //Decrementa por uno la cantidad del objeto en el detalle de orden
        //Parámetros necesarios: Integer idOrderDetail
    this.decrementAmountAtOrderDetail = function(idOrderDetail){
        return http({
            'method':'GET',
            'url': url + '/update_detail/down/' + idOrderDetail
        })
    }
   
    //Incrementa por uno la cantidad del objeto en el detalle de orden
        //Parámetros necesarios: Integer idOrderDetail
    this.incrementAmountAtOrderDetail = function(idOrderDetail){
        return http({
            'method':'GET',
            'url': url + '/update_detail/up/' + idOrderDetail
        })
    }
   
    //Crea el costo total del carrito de compras
        //Parámetros necesarios: OrderAmount orderAmount
    this.createOrderAmount = function(orderAmount){
        return http.post(url + '/order_amount',orderAmount)
    }
   
    //Revisa que el stock y la cantidad del producto en el carrito sea correcta
        //Parámetros necesarios: String user (email)
    this.checkingStock = function(user){
        return http({
            'method': 'GET',
            'url': url + '/cheking_stock/' + user
        })
    }
   
   
    /*************Log in***************/
   
    //Realiza un login
        //Parámetros necesarios: User user (Sin extra data)
    this.logginRest = function(user){
        return http.post(url + '/login',user)
    }
   
    /*************Log out***************/
   
    //Realiza un logout
        //Parámetros no necesarios
    this.loggoutRest = function(){
        return http({
            'method': 'GET',
            'url': url + '/logout'
        })
    }
   
    /************* Users and Measures ***************/
   
    //Realiza el registro del usuario
        //Parámetros necesarios: atributos de User y de Measure (ambos sin extra data) en el mismo objeto
    this.userRegister = function(data){
        return http.post(url + '/user/register',data)
    }
   
    //Agrega una nueva medida de un usuario
        //Parámetros necesarios: Measure measure
    this.newMeasure = function(measure){
        return http.post(url +'/measure',measure)
    }
   
    //Regresa una lista de usuarios basados en el filtro deseado y el correo base
        //Parámetros necesarios: email (String), filter (Integer)
        //Filter: 1=exactamente, 2= contiene, 3=comienza con, 4= termina con
    this.getUserByFilter = function(email,filter){
        return http({
            'method':'GET',
            'url': url + '/users/filter?email='+email+'&filter='+filter
        })
    }
    
    this.userUpdateRol = function(users){
    	return http.put(url + '/users/rol', users)
    }
   
    /*  Measure FitBase  */
   
    //Regresa todos los datos correspondientes a la estatura y peso del usuario
        //Parámetros necesarios: weight, height cantidades respectivas y anglo es un boolean que indica
        //si es medida anglosajona o no
    this.fitbaseGetMeasure = function(weight,height,anglo){
        return http({
            'method':'GET',
            'url':'http://189.211.186.79:8081/fitbase/api/measures?weight='+ weight +'&height='+ weight +'&anglo=' + anglo
        })       
    }
   
    /**************Address*************/
   
    //Obtiene todas las direcciones
        //Parámetros no necesarios
    this.getAllAddresses = function(){
        return http({
            'method': 'GET',
            'url': url + '/address'
        })
    }
   
    //Obtiene todas las direcciones del usuario
        //Parámetros necesarios: String user
    this.getAddressByUser = function(user){
        return http({
            'method': 'GET',
            'url': url + '/address/user/' + user
        })
    }
   
    //Agrega una nueva direccion a un usuario
        //Parámetros necesarios: Address address
    this.newAddress = function(address){
        console.log(address);
        return http.post(url + '/address', address)
    }
   
    //Actualiza una dirección
        //Parámetros necesarios: Address address
    this.updateAddress = function(address){
        return http.put(url + '/address', address)
    }
   
    //Elimina una dirección
        //Parámetros necesarios: Integer idAddress
    this.removeAddress = function(idAddress){
        return http({
            'method':'DELETE',
            'url':url + '/address/' + idAddress
        })
    }
   
    //Trae todos los paises disponibles en la base de datos (regresa un IdNameTable)
        //Parámetros no necesarios
    this.getCountries = function(){
        return http({
            'method': 'GET',
            'url':url + '/country'
        })
    }
   
    /* 
     * ESTADOS Y MUNICIPIOS
     */
   
    this.getStates = function(){
        return http({
            'method':'GET',
            'url':'http://189.211.186.79:8081/geobase/rest/states/MX'
        })
    }
   
    this.getCities = function(cveEnt){
        return http({
            'method':'GET',
            'url':'http://189.211.186.79:8081/geobase/rest/cities/state/' + cveEnt
        })
    }
   

    /* 
     * FIN ESTADOS Y MUNICIPIOS
     */
   
    /**************Product*************/
   
    //Obtiene todos los productos y sus detalles
        //Parámetros no necesitados
    this.getAllProducts = function(){
        return http({
            'method': 'GET',
            'url': url + '/product'
        })
    }
   
    //Obtiene el producto con el sku correspondiente y sus detalles
        //Parámetros necesitados: Integer sku   
    this.getProductBySku = function(sku){
        return http({
            'method': 'GET',
            'url': url + '/product/' + sku
        })
    }
    
    //Crea un nuevo SkuProduct
    	//Parámetros necesitados: SkuProduct skuProduct
    this.createSkuProduct = function(skuProduct){
    	return http.post(url + '/product_sku', skuProduct)
    }
   
    //Trae las ordenes que correspondan a los campos no nulos del objeto product
        //Parámetros necesarios: Product product, Integer page, Integer inPage (inPage = cuantos por página)
    this.getProductsByFilter = function(product, page, inPage){
        var path = "";
        path = path + "sku=" + product.sku + "&title=" + product.title + "&description=" + product.description + "&talla=" + product.size + "&greaterThan="+ product.greaterThan + "&lessThan=" + product.lessThan;
        return http({
            'method': 'GET',
            'url': url + '/product_filter/' + path + ' ' + page + ' ' + inPage
        })
    }
   
    //Trae el status, total de productos, el número de páginas que correspondan a los campos no nulos del objeto product
        //Parámetros necesarios: Product product, Integer inPage (inPage = cuantos por página)
    this.getProductsCountByFilter = function(product, inPage){
        var path = "";
        path = path + "sku=" + product.sku + "&description=" + product.description;
        return http({
            'method': 'GET',
            'url': url + '/product_filter/count/' + path + ' ' + inPage
        })
    }       
   
    //Actualiza un producto
        //Parámetros necesitados: Product product (Extra Data no necesaria)
    this.updateProduct = function(product){
        return http.put(url + '/product',product)
    }
    
    //Crea un producto
    	//Parámetros necesitados: Product product (Extra Data no necesaria)
    this.createProduct = function(product){
    	return http.post(url + '/product',product)
    }
   
    //Elimina el producto con el sku correspondiente junto con sus detalles y sku's
        //Parámetros necesitados: Integer idProduct  (id del producto)
    this.removeProduct = function(idProduct){
        return http({
            'method': 'DELETE',
            'url': url + '/product/' + idProduct
        })
    }
    
    
    //Elimina el producto con el sku correspondiente junto con sus detalles y sku's
    	//Parámetros necesitados: Integer idProduct  (id del producto)    
    this.removeSkuProduct = function(idSkuProduct){
        return http({
            'method': 'DELETE',
            'url': url + '/product_sku/' + idSkuProduct
        })
    }
   
    //Obtiene todos los detalles de productos
        //Parámetros no necesitados
    this.getAllProductDetails = function(){
        return http({
            'method': 'GET',
            'url': url + '/product_detail'
        })
    }
   
    //Obtiene los detalles de un producto specifico
        //Parámetros necesitados: Integer sku   
    this.getProductDetailByProduct = function(sku){
        return http({
            'method': 'GET',
            'url': url + '/product_detail/' + sku
        })
    }
   
    //Agrega una imagen relacionada con el producto
        //Parámetros necesitados: MultipartFile image, Integer product
    this.addProductDetail = function(myFile,product){
    	return http.post(url +'/product_detail/'+product , myFile, { headers: {'Content-Type': undefined} });
    }
    
    //Agrega una imagen relacionada con el producto
    	//Parámetros necesitados: MultipartFile image, Integer product
	this.addProductDetailList = function(myFile,product){
		return http.post(url +'/product_detail/'+product+'/list' , myFile, { headers: {'Content-Type': undefined} });
	}
	
	//Agrega una imagen relacionada con el producto
		//Parámetros necesitados: MultipartFile image, Integer product
	this.addProductDetailArray = function(myFile,product){
		return http.post(url +'/product_detail/'+product+'/array' , myFile, { headers: {'Content-Type': undefined} });
	}
   
	
    //Elimina el detalle de producto con el id correspondiente
        //Parámetros necesitados: Integer id   
    this.removeProductDetail = function(id){
        return http({
            'method': 'DELETE',
            'url': url + '/product_detail/' + id
        })
    }
   
    //Trae una lista con las dos tallas recomendadas (dos objetos SizeDescription), siendo la de la posición 0 la recomendada
     //Y la de la 1 la talla que le quedaría ajustada. Si la posición 0 contiene la información de la talla no habrá segunda talla recomendada
      //Es posible que la lista llegue vacía
    //Parametros necesitados: FitBaseEntity (mandar el objeto tal cual llegue del webservice "fitbase")
    this.recomendedSize = function(fitbase){
        return http.post(url+'/product/recomended_size',fitbase)
    }
   
	//Regresa una lista con las tallas y su id (e.g., id:1 name:S)
		//Parámetros necesitados: Ninguno
	this.getProductSizes = function(){
		return http({
			'method':'GET',
			'url':url + '/product_sizes'
		})
	}
	
	//Regresa una lista de objetos SizeDescription, los cuales contienen las diferentes medidas que corresponden a una talla
		//Parámetros necesitados: Ninguno
	this.getProductSizeDescription = function(){
		return http({
			'method':'GET',
			'url':url + '/product_sizes/description'
		})
	}
	
	//Crea una medida_descripcion nueva en la base de datos, junto con una talla nueva (no se puede crear una sin depender de una talla)
		//Parámetros necesitados: Objeto SizeDescription junto con el extraData (Que será el nombre de la talla)
	this.createSizeDescription = function(sizeDescription){
		return http.post(url+'/product_sizes/description',sizeDescription)
	}


	//Actualiza  una medida_descripción de la base de datos
		//Parámetros necesitados: Objeto SizeDescription
		//NOTA: No se podrá actualizar una medida_descripcion a una talla que ya posea una.
	this.updateSizeDescription = function(sizeDescription){
		return http.put(url+'/product_sizes/description',sizeDescription)
	}
	
	/*********************REPORTS**************/
	
	this.getReportSchema = function(){
		return http({
			'method':'GET',
			'url':url+'/reports'
		})
	}
}

/*
 * Angular code
 */
/*
angular.module('YetiApp')

.provider('yetiService', [

function()
{
    var
    url;
   
    this.setUrl = function(url) {
        this.url = url;
    }
   
    this.$get = ['$http', function yetiServiceFactory($http) {
        return new YetiberaService($http, this.url);
    }]
   
}])
*/
/*
 * Angular code
 */
angular.module(appname+'App')

.provider(appname+'Service', [

function()
{
    var
    url;
   
    this.setUrl = function(url) {
        this.url = url;
    }
   
    this.$get = ['$http', function ServiceFactory($http) {
        return new Service($http, this.url);
    }]
   
}])