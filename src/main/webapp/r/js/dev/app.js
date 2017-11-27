angular.module( appname+'App', ['ui.bootstrap'])
.config([appname+'ServiceProvider', function(ServiceProvider){
	//ServiceProvider.setUrl('/'+appname+'/api');
	ServiceProvider.setUrl('/ags-admin/rest');
}])


var app = angular.module( appname+'App',  ['ui.bootstrap']);
app.config([appname+'ServiceProvider', function(ServiceProvider){
	//ServiceProvider.setUrl('/'+appname+'/api');
	ServiceProvider.setUrl('/ags-admin/rest');
}])