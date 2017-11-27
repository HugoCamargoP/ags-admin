//angular.module(appname+'App')
//var app = angular.module(appname+'App', [ 'ngSanitize']);

app.controller(appname+'Report',['$scope','$sce', appname+'Service',

function($scope,$sce, Service)
{	
	$scope.getReportSchema = function()
	{
		Service.getReportSchema().then(
				function successCallback(response)
				{
					$scope.reportes = response.data;
					for ( var i in $scope.reportes) {
						console.log($scope.reportes[i]);
						for ( var a in $scope.reportes[i].parameters) {							
							console.log($scope.reportes[i].parameters[a]);
							$scope.reportes[i].parameters[a].nameAux = $scope.reportes[i].parameters[a].name;
							$scope.reportes[i].parameters[a].string = $scope.reportes[i].parameters[a].model;
							if($scope.reportes[i].parameters[a].type == 4)
							{
								$scope.reportes[i].parameters[a].model = new Date();
								$scope.reportes[i].parameters[a].opened = false;
							}
						}
					}
				},  
				function errorCallback(response){	
				});	
	}
	
	 $scope.today = function() {
		    $scope.dt = new Date();
		  };
		  $scope.today();

		  $scope.clear = function() {
		    $scope.dt = null;
		  };

		  $scope.inlineOptions = {
		    customClass: getDayClass,
		    minDate: new Date(),
		    showWeeks: true
		  };

		  $scope.dateOptions = {
		    dateDisabled: disabled,
		    formatYear: 'yyyy',
		    //maxDate: new Date(2020, 5, 22),
		    //minDate: new Date(),
		    startingDay: 1,
		    showWeeks: false
		  };

		  // Disable weekend selection
		  function disabled(data) {
		    var date = data.date,
		      mode = data.mode;
		    return mode === 'day' && (date.getDay() === 0 || date.getDay() === 6);
		  }

		  $scope.toggleMin = function() {
		    $scope.inlineOptions.minDate = $scope.inlineOptions.minDate ? null : new Date();
		    $scope.dateOptions.minDate = $scope.inlineOptions.minDate;
		  };

		  $scope.toggleMin();

		  $scope.open1 = function() {
		    $scope.popup1.opened = true;
		  };

		  $scope.open2 = function() {
		    $scope.popup2.opened = true;
		  };

		  $scope.setDate = function(year, month, day) {
		    $scope.dt = new Date(year, month, day);
		  };

		  $scope.formats = ['dd-MMMM-yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate'];
		  $scope.format = $scope.formats[0];
		  $scope.altInputFormats = ['M!/d!/yyyy'];

		  $scope.popup1 = {
		    opened: false
		  };

		  $scope.popup2 = {
		    opened: false
		  };

		  var tomorrow = new Date();
		  tomorrow.setDate(tomorrow.getDate() + 1);
		  var afterTomorrow = new Date();
		  afterTomorrow.setDate(tomorrow.getDate() + 1);
		  $scope.events = [
		    {
		      date: tomorrow,
		      status: 'full'
		    },
		    {
		      date: afterTomorrow,
		      status: 'partially'
		    }
		  ];

		  function getDayClass(data) {
		    var date = data.date,
		      mode = data.mode;
		    if (mode === 'day') {
		      var dayToCheck = new Date(date).setHours(0,0,0,0);

		      for (var i = 0; i < $scope.events.length; i++) {
		        var currentDay = new Date($scope.events[i].date).setHours(0,0,0,0);

		        if (dayToCheck === currentDay) {
		          return $scope.events[i].status;
		        }
		      }
		    }

		    return '';
		  }
}])