angular.module('YetiApp', [])
.config(['yetiServiceProvider', function(yetiServiceProvider){
	yetiServiceProvider.setUrl('/yetibera/rest');
}])
