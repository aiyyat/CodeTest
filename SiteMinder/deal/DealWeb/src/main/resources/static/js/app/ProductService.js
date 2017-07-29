'use strict';

angular.module('crudApp').factory('ProductService',
    ['$localStorage', '$http', '$q', 'urls',
        function ($localStorage, $http, $q, urls) {
    		console.log("product service")
            var factory = {
    			loadAllProducts: loadAllProducts,
                getAllProducts: getAllProducts,
                submitQuote: submitQuote
            };

            return factory;
            
            function submitQuote(name,email,purpose,productArray) {
                var deferred = $q.defer();
                $http({
                	  method: 'POST',
                	  url: urls.SERVICE_API+"/quote",
                	  headers: {
                		   'Content-Type': "application/json"
                	  },
                	  data: {
                		  		'username':name,
                		  		'emailId':email,
                		  		'purpose':purpose,
                		  		'productIds':productArray
                	  }
                	}).then(
                         function (response) {
                             console.log('successfully Fetched all quotes'+response.data);
                             deferred.resolve(response);
                         },
                         function (errResponse) {
                             console.error('Error while loading quotes'+errResponse.stack);
                             deferred.reject(errResponse);
                         }
                	);
                return deferred.promise;
            }
            
            function loadAllProducts() {
                var deferred = $q.defer();
                $http.get(urls.SERVICE_API+"/products")
                    .then(
                        function (response) {
                            console.log('Fetched products successfully');
                            $localStorage.products = response.data;
                            deferred.resolve(response);
                        },
                        function (errResponse) {
                            console.error('Error while loading products');
                            deferred.reject(errResponse);
                        }
                    );
                return deferred.promise;
            }

            function getAllProducts(){
                return $localStorage.products;
            }
        }
    ]);