angular.module( appname+'App',[])
.config([appname+'ServiceProvider', function(ServiceProvider){
	//ServiceProvider.setUrl('/'+appname+'/api');
	ServiceProvider.setUrl('/'+appname+'bera/rest');
}])
