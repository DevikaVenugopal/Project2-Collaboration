app.controller("forumcontroller", function ($scope,$http,$location,$rootScope,$cookieStore) {
	console.log("in forum controller")
	$scope.Forum={formname:'',formcontent:''};
	$scope.ForumRequest={userid:'',forumid:'',forumname:'',username:'',status:'P'}
	$scope.ForumComments={forumcomm:'',forumid:'',userid:'',username:''};
	function fetchAllForums()
	{
		console.log("in fetch all forums method")
	$http.get("http://localhost:8081/Middleware/forums/getAllForums")
		.then(function(response)
		{
			console.log("Forums retrieve successfully")
			$scope.forums=response.data;
	console.log($scope.forums)
						
		},function(error)
		{
			console.log("Error on retrieving forums")
		});
	};
	
	
	fetchAllForums();
	
	
	
	function myallforums()
	{
		
	
		$http.get("http://localhost:8081/Middleware/forums/myforums/"+$rootScope.currentuser.userid)
		.then(function(response)
		{
			
			$rootScope.myforum=response.data;
			
						
		},function(error)
		{
			console.log("Error on retrieving forum")
		});	
		
		
	}
	myallforums();
	
	
	
	
	
	
	/*function fetchForumByIdd(idd)
	{
		
		 $http.get("http://localhost:8081/Middleware/forums/getForumById/"+idd).then(function(response){
				

				$rootScope.Forumid=response.data; 
				
				},function(error){
					console.log("Error on retrieving forum")
				});
		
		

	$http.get("http://localhost:8081/Middleware/forums/checkIfMyForum/"+idd+"/"+$rootScope.currentuser.userid).then(function(response){
		$rootScope.fcheck=response.data;
		
		
			


		

			});
	
	}*/
	
	 $scope.addForum=function()
	 {
		console.log("in add forum method")
		 $http.post("http://localhost:8081/Middleware/forums/addForum",$scope.Forum).then(fetchAllForums(),function(response){
			 alert("Forum added successfully")
			 console.log("Forum added successfully")
			 
								
			},function(error){
				console.error("Error while adding forum")
			});
		$location.path('/forummanage')
		 
	 }
	 
$rootScope.fetchforumbyid=function(idd)
{
console.log('in fetch forum by id method'+idd)
	 $http.get("http://localhost:8081/Middleware/forums/getForumById/"+idd).then(function(response){
		

			$rootScope.ForumByid=response.data; 
			$cookieStore.put('forum',$rootScope.ForumByid);
			},function(error){
				console.log("Error on retrieving forum")
			});

$http.get("http://localhost:8081/Middleware/forums/checkIfMyForum/"+idd+"/"+$rootScope.currentuser.userid).then(function(response){
	$rootScope.fcheck=response.data;
	$cookieStore.put('forumcheck',$rootScope.fcheck);

		});


$http.get("http://localhost:8081/Middleware/forums/getAllForumComments/"+idd)
.then(function(response)
{
	
	$rootScope.gforumcomm=response.data;
	$cookieStore.put('forumcomm',$rootScope.gforumcomm);
	
},function(error)
{
	
});		


$http.get("http://localhost:8081/Middleware/forums/forumreqbyforumid/"+idd)
.then(function(response)
{
	
	$rootScope.forusers=response.data;
	$location.path('/viewforum');
	
},function(error)
{
	
});/*
console.log("forum forusers :",$rootScope.forusers)
console.log("forum comments :",$rootScope.gforumcomm)*/

}

$scope.fetchforumforedit=function(idd)
{
	console.log("in forum fetch for edit  method")
	 $http.get("http://localhost:8081/Middleware/forums/getForumById/"+idd).then(function(response){
		 

			$rootScope.eforum=response.data; 
		
			},function(error){
				console.log("Error on retrieving forum")
			});
	
	$location.path('/updateforum')

}

	 $scope.editForum=function(idd)
	 {
		console.log("in edit forum method")
		if($scope.Forum.formname==null)
			{
			$scope.Forum.formname=$rootScope.eforum.formname;
			}
		if($scope.Forum.formcontent==null)
			{
			$scope.Forum.formcontent=$rootScope.eforum.formcontent;
			}
		 $http.post("http://localhost:8081/Middleware/forums/updateForum/"+idd+"/"+$scope.Forum.formname+"/"+$scope.Forum.formcontent).then(fetchAllForums(),function(response){
			 console.log("Forum updated successfully");
								
			},function(error){
				console.error("Error while updating Forum");
			});
		
		
		
		 $http.get("http://localhost:8081/Middleware/forums/getForumById/"+$rootScope.eforum.forumid).then(function(response){
				$rootScope.eforum=response.data; 
				 alert("Forum updated successfully")

					
				},function(error){
				
				});
		 
		 $location.path('/forummanage')
		 
		 
		 
	 }
	
	
	 $scope.deleteForum=function(idd)
	 {
		console.log("in delete forum method")
		 $http.get("http://localhost:8081/Middleware/forums/deleteForum/"+idd).then(fetchAllForums(),function(response){
			alert("Forum deleted successfully")
			 console.log("Forum deleted successfully");
			 location.path('/forummanage')
								
			},function(error){
				console.error("Error while deleting Forum");
			});
		
		 $location.path('/forummanage')
	 }
	
	 
	$scope.myforums=function()
	{
		
 $http.get("http://localhost:8081/Middleware/forums/myforums/"+$rootScope.currentuser.userid).then(function(response){
			 
			$scope.myforums=response.data;
								
			},function(error){
				console.error("Error while accepting forumrequets Forum");
			});	
		
		
	}
	
	
	
	 $rootScope.sendforumrequests=function(id)
	 {
		console.log('in send froum request')
		 $http.get("http://localhost:8081/Middleware/forums/addForumReq/"+id+"/"+$rootScope.currentuser.userid).then(fetchForumByIdd($rootScope.ForumByid.forumid),function(response){
			 alert("Forumrequested successfully")
			 console.log("Forumrequested successfully");
		 });
		
		 $location.path('/forum')
	 }
	 
	 
	 $rootScope.leaveforum=function()
	 {
		
		
		 $http.get("http://localhost:8081/Middleware/forums/leaveforum/"+$rootScope.ForumByid.forumid+"/"+$rootScope.currentuser.userid).then(fetchForumByIdd($rootScope.ForumByid.forumid),function(response){
			 
		 });
		
		 $location.path('/forum')
	 }
	 
	 
	 
	 
	 $scope.addForumComment=function()
	 {
		console.log("in add forumComment method")
		console.log($rootScope.ForumByid.forumid+$rootScope.currentuser.username+$scope.ForumComments.forumcomm)

		$http.get("http://localhost:8081/Middleware/forums/addForumComments/"+$rootScope.ForumByid.forumid+"/"+$rootScope.currentuser.username+"/"+$scope.ForumComments.forumcomm).then(function(response){
			 console.log("BlogComments added successfully")
								
			},function(error){
				
			});
		
		$http.get("http://localhost:8081/Middleware/forums/getAllForumComments/"+$rootScope.ForumByid.forumid)
		.then(function(response)
		{
			
			$rootScope.gforumcomm=response.data;
			
			
		},function(error)
		{
			
		});		
		
		$location.path('/viewforum')	 
		 
	 }
	
	
	});


app.controller("forumrequestcontroller", function ($scope,$http,$location,$rootScope) {
	function fetchAllForumreq()
	{
	
	 $http.get("http://localhost:8081/Middleware/forums/getForumRequests")
	    .then(function(response)
	    		{
	    	
	    
		 $scope.forumrequests=response.data;
	
		 $location.path('/forumrequests')
							
		},function(error){
			console.error("Error while deleting Forum");
		});
	}
	
	
	fetchAllForumreq();
	
	 $rootScope.acceptforumrequests=function(id)
	 {
		 
		 
		console.log("in forum request  accept method")
		 $http.get("http://localhost:8081/Middleware/forums/approveForumRequests/"+id).then(fetchAllForumreq(),function(response){
			 
			 console.log("Forumrequests accepted  successfully");
			 $location.path('/forumrequests')
								
			},function(error){
				console.error("Error while accepting forumrequets Forum");
			});
		$location.path('/forummanage')
		 
	 }
	 
	 $rootScope.rejectforumrequests=function(id)
	 {
		 
		 
		console.log("in forum reject  accept method")
		 $http.get("http://localhost:8081/Middleware/forums/rejectForumRequests/"+id).then(fetchAllForumreq(),function(response){
			 
			 console.log("Forumrequests rejected  successfully");
			 $location.path('/forumrequests')
								
			},function(error){
				console.error("Error while rejecting forumrequets Forum");
			});
		$location.path('/forummanage')
		 
	 }

	 
	
	
});