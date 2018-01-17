app.controller("UserCtrl", function ($scope,$location,$http,$rootScope,$cookieStore) {
	 $scope.msg = "Register  page";
	 $scope.User={username:'',firstname:'',lastname:'',gender:'',email:'',password:'',status:'P',role:'ROLE_USER',isonline:'NO',phone:''};
	 $scope.register=function()
	 {
		 console.log("in register controller angualar");
		
		 $http.post("http://localhost:8081/Middleware/user/registerUser",$scope.User).then(function(response){
		 
			 console.log("Registerd Successfully")
			 $scope.User=response.data;
			 $location.path("/login")
								
			},function(error){
				console.error("Error while creating user"+error)
			});
		 
	 }
	 
	 
	 
	 $scope.login=function()
	 {
		 console.log("in login method");
		 $http.post("http://localhost:8081/Middleware/user/login",$scope.User).then(function(response)
				 
		 {	 
			 $scope.Userdet=response.data;
			 
			 if($scope.Userdet.status=='P')
				 {
				     alert("You are not been approved by the admin...Wait for the approval")
				     $location.path("/")
				 }
			 else if($scope.Userdet.status=='R')
				 {
				     alert("Sorry..You are Rejected by the Admin")
			         $location.path("/")
				 }
			 else
				 {
			         $rootScope.currentuser=response.data;
			         //console.log("ROLE"+$rootScope.currentuser.role);
			         $http.defaults.headers.common['Authorization'] = 'Basic' + $rootScope.currentuser;
			         $cookieStore.put('currentUser', $rootScope.currentuser);
			         console.log($cookieStore.get('currentUser'));
			         $location.path("/")
				 }
		 });
		 
	 }
	 
});



app.controller("logoutcontroller", function ($scope,$location,$http,$rootScope,$cookieStore) {
console.log("in logout controller")
	 $scope.logout=function()
	 {
	 
		 console.log( $rootScope.currentuser.email)
			$http.get("http://localhost:8081/Middleware/user/logout/"+ $rootScope.currentuser.email)
				.then(function(response)
				{
					 $rootScope.currentuser=null;
					 $cookieStore.remove('currentUser');
					 $location.path("/login")
					
				},function(error)
				{
				}
				);
	 }
});
	 
	 

app.controller("userrequestcontroller", function ($scope,$http,$location,$rootScope,$cookieStore) {
	function fetchAlluserreq()
	{
	
	 $http.get("http://localhost:8081/Middleware/user/getAllUsersreq")
	    .then(function(response)
	    		{
	    	
	    
		 $scope.userrequests=response.data;
	
		 $location.path('/userrequests')
							
		},function(error){
			console.error("Error while fetching requests");
		});
	}
	
	
	fetchAlluserreq();
	
	
	
	
	
	
	 $scope.acceptuserrequests=function(id)
	 {
		 
		 
		console.log("in user request  accept method")
		 $http.get("http://localhost:8081/Middleware/user/approveusers/"+id).then(fetchAlluserreq(),function(response){
			 
			 console.log("userrequets accepted  successfully");
			 $location.path('/userrequests')
								
			},function(error){
				console.error("Error while accepting userrequets");
			});
		$location.path('/blog')
		 
	 }
	 
	 
	 
	 $scope.rejectuserrequests=function(id)
	 {
		 
		 
		console.log("in user request  reject method")
		 $http.get("http://localhost:8081/Middleware/user/rejectusers/"+id).then(fetchAlluserreq(),function(response){
			 
			 console.log("userrequets rejected  successfully");
			 $location.path('/userrequests')
								
			},function(error){
				console.error("Error while rejecting userrequets");
			});
		$location.path('/blog')
		 
	 }


});
