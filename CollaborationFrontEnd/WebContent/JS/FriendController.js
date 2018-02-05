app.controller("friendrequestcontroller", function ($scope,$http,$location,$rootScope) {
$scope.Friend={U_ID:'',friendid:'',status:'P'}
	/*$http.get("http://localhost:8081/Middleware/friend/getAllMyFriendRequests/"+ $rootScope.currentuser.userid)

	.then(function(response) {
		$scope.myfriendreqs = response.data;
		console.log("all my friendsreqs  fetched")
	},function(error)
	{
		console.log("Error on retrieving friend")
	});*/
function fetchAllfriendreq()
{

 $http.get("http://localhost:8081/Middleware/friend/getAllMyFriendRequests/"+ $rootScope.currentuser.userid)
 
 .then(function(response) {
		$scope.myfriendreqs = response.data;
		console.log("all my friendsreqs  fetched")
	},function(error)
	{
		console.log("Error on retrieving friend")
	});
}
fetchAllfriendreq();

	
$scope.acceptfriend = function(friendid)
{
console.log("in unfriend method",friendid)
	$http.get("http://localhost:8081/Middleware/friend/acceptfriend/"+$rootScope.currentuser.userid+'/'+friendid)
	.then(fetchAllfriendreq(), function(response) {
		console.log("successful friend add ");
		$location.path("/profile")
	});
}

$scope.rejectfriend = function(friendid)
{
console.log("in unfriend method")
	$http.get("http://localhost:8081/Middleware/friend/rejectfriend/"+$rootScope.currentuser.userid+'/'+friendid)
	.then(fetchAllfriendreq(), function(response) {
		console.log("Rejected friend request");
		$location.path("/profile")
	});
}
});




app.controller("friendcontroller", function ($scope,$http,$location,$rootScope,$cookieStore) {
	 
	function fetchAlluser()
	{
		
	 $http.get("http://localhost:8081/Middleware/user/getAllUsers/"+ $rootScope.currentuser.userid)
	    .then(function(response)
	    		{
	    	
	    console.log(response.data)
		 $scope.users=response.data;
	    
		 /*$location.path('/userlist')*/
							
		},function(error){
			console.error("Error while fetching requests");
		});
	}
	
	
   fetchAlluser();
	
	
	function fetchAllUsers()
	{
		
		
		$http.get("http://localhost:8081/Middleware/friend/getMyFriends/"+ $rootScope.currentuser.userid)

		.then(function(response) {
			$scope.myfriends = response.data;
			console.log("all my friends fetched")
		},function(error)
		{
			console.log("Error on retrieving forums")
		});
		
		
		$http.get("http://localhost:8081/Middleware/friend/getAllOtherUsers/"+ $rootScope.currentuser.userid)

		.then(function(response) {
			$scope.otherusers = response.data;
			console.log("all other user fetched")
		},function(error)
		{
			console.log("Error on retrieving forums")
		});
		

		$http.get("http://localhost:8081/Middleware/friend/getOnlineFriends/"+ $rootScope.currentuser.userid)

		.then(function(response) {
			$scope.onlineusers = response.data;
			console.log("all online users fetched")
		},function(error)
		{
			console.log("Error on retrieving forums")
		});
		
		
		$http.get("http://localhost:8081/Middleware/friend/getAllMyFriendRequests/"+ $rootScope.currentuser.userid)

		.then(function(response) {
			$scope.myfriendreqs = response.data;
			console.log("all my friendsreqs  fetched")
		},function(error)
		{
			console.log("Error on retrieving forums")
		});
		
		
		
		
	}
	;
	fetchAllUsers();
	
	$scope.insertFriend = function(friendid)
	{
		console.log('entered add friend method'+friendid);
		$http.get('http://localhost:8081/Middleware/friend/addFriend/'+$rootScope.currentuser.userid+'/'+friendid)
		.then(fetchAllUsers(), function(response) {
			console.log("successful friend add ");
			$location.path("/userlist")
		});
	}
	
	
	

	$scope.unfriend = function(friendid)
	{
	console.log("in unfriend method")
		$http.get('http://localhost:8081/Middleware/friend/unfriend/'+$rootScope.currentuser.userid+'/'+friendid)
		.then(fetchAllUsers(), function(response) {
			console.log("successful friend add ");
			$location.path("/profile")
		});
	}
	
	
	
	$rootScope.friendpreview=function(friendid)
	{
		if(friendid==$rootScope.currentuser.userid)
			{
			$location.path("/profile")
			}
		else
			{
		$http.get("http://localhost:8081/Middleware/user/getUser/"+friendid)

		.then(function(response) {
			$rootScope.friendpreviewdata = response.data;
			$scope.fr=response.data;
			console.log($rootScope.friendpreviewdata.email);
			console.log($rootScope.friendpreviewdata.userid);
			console.log($rootScope.currentuser.userid);
		},function(error)
		{
			console.log("Error on retrieving forums")
		});

	$http.get("http://localhost:8081/Middleware/user/ismyfriend/"+$rootScope.friendpreviewdata.userid+"/"+$rootScope.currentuser.userid)

		.then(function(response) {
			$rootScope.ismyfriend = response.data;
		
		},function(error)
		{
			
		});
		
		
		$http.get("http://localhost:8081/Middleware/user/friendsfriends/"+$rootScope.friendpreviewdata.userid+"/"+$rootScope.currentuser.userid)

		.then(function(response) {
			$rootScope.friendsfriends = response.data;
		
		},function(error)
		{
			
		});
		
		
		
		
		$http.get("http://localhost:8081/Middleware/blogs/getAllMyBlogs/"+$rootScope.friendpreviewdata.userid)
		.then(function(response)
		{
			
			$rootScope.friendblogs=response.data;
			
						
		},function(error)
		{
			console.log("Error on retrieving blogs")
		});	
		
		$http.get("http://localhost:8081/Middleware/forums/myforums/"+$rootScope.friendpreviewdata.userid)
		.then(function(response)
		{
			
			$rootScope.friendforums=response.data;
			
						
		},function(error)
		{
			console.log("Error on retrieving blogs")
		});	
		$location.path("/")
	}
	}
	
	
});
/*app.controller("userlistcontroller", function ($scope,$http,$location,$rootScope,$cookieStore) {
	function fetchAllusers()
	{
	
	 $http.get("http://localhost:8081/Middleware/user/getAllUsers")
	    .then(function(response)
	    		{
	    	
	    
		 $scope.users=response.data;
	
		 $location.path('/userlist')
							
		},function(error){
			console.error("Error while fetching requests");
		});
	}
	
	
	fetchAllusers();
});*/

