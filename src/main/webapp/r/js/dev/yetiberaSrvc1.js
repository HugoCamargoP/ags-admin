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
	this.getProductSizes = function(){
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