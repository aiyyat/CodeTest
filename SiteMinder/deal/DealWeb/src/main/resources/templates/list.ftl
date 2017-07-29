<div class="generic-container">
    <div class="panel panel-default">
		<div class="panel-body">
		    <div class="formcontainer">
		        <div class="alert alert-success" role="alert" ng-if="ctrl.successMessage">{{ctrl.successMessage}}</div>
		        <div class="alert alert-danger" role="alert" ng-if="ctrl.errorMessage">{{ctrl.errorMessage}}</div>
		        <form ng-submit="ctrl.submit(u)" name="myForm" class="form-horizontal">		    
			        <!-- Default panel contents -->
			        <div class="panel-heading"><span class="lead">List of Products</span></div>
					<label>I,
						<input type="text" class="mandatory" size="40" name="input" ng-model="username" placeholder="Please enter your name here" required ng-trim="false">
					</label> & &nbsp;
				    <label>Email Id:
						<input type="email" class="mandatory" size="40" name="email" ng-model="emailId" placeholder="Please enter your email id here" required ng-trim="false">
					</label><br>
				    <label>
				    would like to have a quote of the best possible Deals from your stores for the <br>
				    	Purpose/Occasion:
						<textarea class="mandatory" style="width:152%" name="user.purpose" ng-model="purpose" placeholder="Please describe the purpose/occasion here as accurate as you possible; This will help the store help you with what is most appropriate." rows="3" required ng-trim="false"></textarea>
					</label><br>
					<label>
						Following are the items I would require from your store:
					</label>
					<div class="panel-body" ng-init="ctrl.getAllProducts()">
							<table class="rounded">
								<tr>
									<td>
										<label>Products:
											<input class="filter" type="text" name="input" ng-model="products" ng-trim="false">
										</label>
									</td>
									<td>
										<label>Comments:  
											<input class="filter" type="text" name="input" ng-model="comments" ng-trim="false">
										</label>
									</td>
								</tr>
							</table>
						<div class="table-responsive">
					        <table class="table table-hover">
					            <thead>
					            <tr>
					                <th>ID</th>
					                <th>Product Name</th>
					                <th>Comments</th>
					                <th>Quote</th>
					            </tr>
					            </thead>
					            <tbody>
					            <tr ng-repeat="u in ctrl.products  | filter:{productName:products,categoryNames:categories,comments:comments}">
					                <td>{{u.id}}</td>
					                <td>{{u.productName}}</td>
					                <td>{{u.comments}}</td>
					                <td><input type="checkbox" ng-model="u.selected" name="{{u.id}}"></td>
					            </tr>
					            </tbody>
					        </table>	
						</div>
					</div>
					<div class="form-actions floatRight">
	                	<input type="submit" class="btn btn-primary btn-sm">
	                	<button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Reset Form</button>
	           		 </div>
				</form>
			</div>
		</div>	
    </div>
</div>