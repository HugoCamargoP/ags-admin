/**
 * @constructor
 * @param http
 * @param url
 * @returns
 */
function YetiberaService(http, url) 
{

	/************* Users and Measures ***************/
	
	//Regresa una lista de usuarios basados en el filtro deseado y el correo base
		//Parámetros necesarios: email (String), filter (Integer)
		//Filter: 1=exactamente, 2= contiene, 3=comienza con, 4= termina con
	this.getUserByFilter = function(email,filter){
		return http({
			'method':'GET',
			'url': url + '/users/filter?email='+email+'&filter='+filter
		})
	}
	
	//Recibe una lista de usuarios de los cuales obtendrá el rol y el id; actualizará el rol en la bd
		//Parámetros necesarios: Un arreglo de User
	this.userUpdateRol = function(users){
		return http.pu(url+'/users/rol',users)
	}

	/************* States and Cities ***************/
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
	
	//Trae las ordenes que correspondan a los campos no nulos del objeto product 
		//Parámetros necesarios: Product product, Integer page, Integer inPage (inPage = cuantos por página)
	this.getProductsByFilter = function(product, page, inPage){
		var path = "";
		path = path + "sku=" + product.sku + "&description=" + product.description;
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
	
	//Elimina el producto con el sku correspondiente junto con sus detalles
		//Parámetros necesitados: Integer sku	
	this.removeProduct = function(sku){
		return http({
			'method': 'DELETE',
			'url': url + '/product/' + sku
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
	
	//Actualiza un detalle de producto
		//Parámetros necesitados: ProductDetail productDetail
	this.updateProduct = function(productDetail){
		return http.put(url + '/product_detail',productDetail)
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
	
}

/*
 * Angular code
 */
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