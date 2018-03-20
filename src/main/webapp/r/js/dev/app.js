angular.module( appname+'App', ['ui.bootstrap','rt.select2'])
.config([appname+'ServiceProvider', function(ServiceProvider){
	//ServiceProvider.setUrl('/'+appname+'/api');
	ServiceProvider.setUrl('/ags-admin/rest');
//	ServiceProvider.setUrl('/rest');
}])


var app = angular.module( appname+'App',  ['ui.bootstrap','rt.select2']);
app.config([appname+'ServiceProvider', function(ServiceProvider){
	//ServiceProvider.setUrl('/'+appname+'/api');
	ServiceProvider.setUrl('/ags-admin/rest');
//	ServiceProvider.setUrl('/rest');
}])