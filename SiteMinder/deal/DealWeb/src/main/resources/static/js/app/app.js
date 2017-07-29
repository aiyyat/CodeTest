var app = angular.module('crudApp', [ 'ui.router', 'ngStorage' ]);
var parts = window.location.href.split("/");
var url = parts[0] + "//" + parts[2]
app.constant('urls', {
	SERVICE_API : url+"/service"
});

app.config([
		'$stateProvider',
		'$urlRouterProvider',
		function($stateProvider, $urlRouterProvider) {
			$stateProvider.state('home', {
				url : '/',
				templateUrl : 'partials/list',
				controller : 'ProductController',
				controllerAs : 'ctrl',
				resolve : {
					products : function($q, ProductService) {
						console.log('Load all Products');
						var deferred = $q.defer();
						ProductService.loadAllProducts().then(deferred.resolve,
								deferred.resolve);
						return deferred.promise;
					}
				}
			});
			$urlRouterProvider.otherwise('/');
		} ]);
