angular.module( appname+'App',[])
.config([appname+'ServiceProvider', function(ServiceProvider){
	//ServiceProvider.setUrl('/'+appname+'/api');
	ServiceProvider.setUrl('/ags-admin/rest');
}])


var app = angular.module( appname+'App', ['ngSanitize']);
app.config([appname+'ServiceProvider', function(ServiceProvider){
	//ServiceProvider.setUrl('/'+appname+'/api');
	ServiceProvider.setUrl('/ags-admin/rest');
}])