'use strict';

angular.module('crudApp').controller('ProductController',
    ['ProductService', '$location', '$rootScope', '$scope',  function( ProductService, $location, $rootScope, $scope) {
    	console.log("product controller..")
        var self = this;
        self.products=[];

        self.submit = submit;
        self.getAllProducts = getAllProducts;
        self.reset = reset;

        self.successMessage = '';
        self.errorMessage = '';
        self.done = false;

        self.onlyIntegers = /^\d+$/;
        self.onlyNumbers = /^\d+([,.]\d+)?$/;
        	 
        function submit() {
        	var productArray = [];
        	angular.forEach(self.products, function(product){
        		console.log(product)
        		if (product.selected==true) productArray.push(product.id);
        	})
        	if(productArray.length > 0) {
	            ProductService.submitQuote($scope.username,$scope.emailId,$scope.purpose,productArray).then(
	                    function (response) {
	                        console.log(response.data);
	                        reset()
	                        self.successMessage="email sending status: "+response.data.status
	                    },
	                    function (errResponse) {
	                        console.error('Error sending quote: '+errResponse.stack);
	                        deferred.reject(errResponse);
	                    }
	           	)
	        } else {
           		self.errorMessage="Please select the product you want best quote for"
           	}
        }
        function submitQuote(email,username,purpose,productArray){
        	return ProductService.submitQuote(email,username,purpose,productArray);
        }
        function getAllProducts(){
            self.products=ProductService.getAllProducts();
        }

        function reset(){
            self.successMessage='';
            self.errorMessage='';
            $scope.username=''
            $scope.emailId=''
            $scope.purpose=''
            $scope.products=''
            $scope.comments=''
            $scope.myForm.$setPristine(); 
            angular.forEach(self.products, function(product){
        		product.selected=false;
        	})
        }
    }
    ]);