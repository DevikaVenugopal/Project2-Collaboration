app.controller("blogCtrl", function ($scope,$http,$location,$rootScope) {
    console.log("blog controller",$rootScope.currentuser.email)	 
	 $scope.Blog={blogname:'',blogcontent:'',status:'P',likes:'0',dislikes:'0',views:'0',username:$rootScope.currentuser.email};
	$scope.BlogComment={blogcomm:'',blogid:'',username:''};

	function fetchAllBlogs()
	{
	$http.get("http://localhost:8081/Middleware/blogs/getAllBlogs")
		.then(function(response)
		{
			console.log("Blogs retrieve successfully")
			$scope.blogs=response.data;
			console.log($scope.blogs)
						
		},function(error)
		{
			console.log("Error on retrieving blogs")
		});
	};
	fetchAllBlogs();
	
	function myallblogs()
	{
		console.log("in all my blogs method")
		console.log($rootScope.currentuser.email)
		$http.get("http://localhost:8081/Middleware/blogs/getAllMyBlogs/"+$rootScope.currentuser.userid)
		.then(function(response)
		{
			
			$rootScope.myblogs=response.data;
			
						
		},function(error)
		{
			console.log("Error on retrieving blogs")
		});	
		
		
	}
	myallblogs();
	
	function fetchBlogById(idd)
	{
		
		 $http.get("http://localhost:8081/Middleware/blogs/getBlogById/"+idd).then(function(response){
				$scope.blogbyid=response.data;
				$rootScope.gblog=response.data;
				 
				
				console.log("blog fetched successfully")				
				},function(error){
					console.log("error in fetching blog")
				});
		
	}
	
	
	
	
	 $scope.maximum=getSelectedBlog
	 function getSelectedBlog(idd)
	 {
		 console.log("in add max method------"+idd)
		 
		 
		  $http.get("http://localhost:8081/Middleware/blogs/incview/"+idd).then(function(response){
			
			},function(error){
			
			});
		 
		 console.log("view incremented")

		 $http.get("http://localhost:8081/Middleware/blogs/getBlogById/"+idd).then(function(response){
				$scope.blogbyid=response.data;
				$rootScope.gblog=response.data;
				 
				
				console.log("blog fetched successfully")				
				},function(error){
					console.log("error in fetching blog")
				});
	
		
		 $http.get("http://localhost:8081/Middleware/blogs/getAllBlogComments/"+idd)
			.then(function(response)
			{
				
				$rootScope.gblogcomm=response.data;
				console.log($rootScope.gblogcomm)
				
			},function(error)
			{
				
			});		
		
		 
		
		 $location.path('/blog')
		 
	 }

	 $scope.addBlog=function()
	 {
		console.log("in add blog method"+$scope.Blog.blogname)
		 $http.post("http://localhost:8081/Middleware/blogs/addBlog",$scope.Blog).then(fetchAllBlogs(),function(response)
				 {
			 $scope.User=response.data;
			 console.log("Blog added successfully")
								
			},function(error){
				console.error("Error while creating blog")
			});
		$location.path('/blog')
		 
	 }

	 
	 
	 $scope.fetchforedit=function(idd)
	 {
		 
		 $http.get("http://localhost:8081/Middleware/blogs/getBlogById/"+idd).then(function(response){
				$rootScope.eblog=response.data; 
					
				},function(error){
				
				});
		 $location.path('/blogforedit')	 
	 }
	 
	 
	 
	 

	 $scope.editBlog=function()
	 {
		
	console.log($scope.Blog.blogname+" "+$scope.Blog.blogcontent)
		 $http.get("http://localhost:8081/Middleware/blogs/updateBlog/"+$rootScope.eblog.blogid+"/"+$scope.Blog.blogname+"/"+$scope.Blog.blogcontent).then(function(response){
			 console.log("Blog updated successfully");
								
			},function(error){
				console.error("Error while updating blog");
			});
		 
		 
		 $http.get("http://localhost:8081/Middleware/blogs/getBlogById/"+$rootScope.eblog.blogid).then(function(response){
				$rootScope.eblog=response.data; 
					
				},function(error){
				
				});
		
		$location.path('/blog')	 
		 
	 }
	 
	 $scope.deleteBlog=function(idd)
	 {
		console.log("in delete blog method")
		 $http.get("http://localhost:8081/Middleware/blogs/deleteBlog/"+idd).then(fetchAllBlogs(),function(response){
			 console.log("Blog deleted successfully");
								
			},function(error){
				console.error("Error while deleting blog");
			});
		
		
		
		$location.path('/blog')	 
		 
	 }
	 
	 
	
	 
	 $scope.rejetctBlog=function(idd)
	 {
		console.log("in reject blog method")
		 $http.get("http://localhost:8081/Middleware/blogs/rejectBlog/"+idd).then(fetchBlog(idd),fetchAllBlogs(),function(response){
			 console.log("Blog rejected successfully");
								
			},function(error){
				console.error("Error while rejecting blog");
			});
		$location.path('/blog')	 
		 
	 }
	 
	 $scope.likeBlog=function(idd)
	 {
		console.log("in like blog method")
		 $http.get("http://localhost:8081/Middleware/blogs/likeBlog/"+idd).then(fetchBlogById(idd),function(response){
			 console.log("Blog liked successfully");
								
			},function(error){
				console.error("Error while liking blog");
			});
		
		$location.path('/blogview')	 
		 
	 }
	 
	 
	 $scope.dislikeBlog=function(idd)
	 {
		console.log("in like blog method")
		 $http.get("http://localhost:8081/Middleware/blogs/dislikeBlog/"+idd).then(fetchBlogById(idd),function(response){
			 console.log("Blog disliked successfully");
								
			},function(error){
				console.error("Error while disliking blog");
			});
		
		$location.path('/blogview')	 
		 
	 }

	 
	 
	 $scope.addBlogComment=function()
	 {
		console.log("in add blogComment method")
		console.log($rootScope.gblog.blogid+$rootScope.currentuser.email+$scope.BlogComments.blogcomm)

		$http.get("http://localhost:8081/Middleware/blogs/addBlogComments/"+$rootScope.gblog.blogid+"/"+$rootScope.currentuser.email+"/"+$scope.BlogComments.blogcomm).then(function(response){
			 console.log("BlogComment added successfully")
								
			},function(error){
				
			});
		
		$http.get("http://localhost:8081/Middleware/blogs/getAllBlogComments/"+$rootScope.gblog.blogid)
		.then(function(response)
		{
			
			$rootScope.gblogcomm=response.data;
			
			
		},function(error)
		{
			
		});		
		
		$location.path('/blogview')	 
		 
	 }
	 
	 
	 $scope.updateBlogComment=function(idd)
	 {
		console.log("in update blogComment method")
		 $http.post("http://localhost:8081/Middleware/blogs/updateBlogComments/"+idd,$scope.BlogComments).then(function(response){
			 console.log("BlogComment updated successfully")
								
			},function(error){
				console.error("Error while updating blogComment")
			});
		$location.path('/blog')	 
		 
	 }
	 
	 
	 $scope.deleteBlogComment=function(idd)
	 {
		console.log("in delete blogcomment method")
		 $http.get("http://localhost:8081/Middleware/blogs/deleteBlogComment/"+idd).then(function(response){
			 console.log("Blogcomment deleted successfully");
								
			},function(error){
				console.error("Error while deleting blogcomment");
			});
		
		$location.path('/blog')	 
		 
	 }
	 
	 
	 
});

app.controller("blogrequestcontroller", function ($scope,$http,$location,$rootScope) {
	function fetchAllblogreq()
	{
	
	 $http.get("http://localhost:8081/Middleware/blogs/blogrequests")
	    .then(function(response)
	    		{
	    	
	    
		 $scope.blogequests=response.data;
	
		 $location.path('/blogrequests')
							
		},function(error){
			
		});
	}
	fetchAllblogreq();
	
	 $scope.acceptblogrequests=function(id)
	 {
		 
		 console.log('in acceptblog request method')
		
		 $http.get("http://localhost:8081/Middleware/blogs/approveblogRequests/"+id).then(fetchAllblogreq(),function(response){
			 
		console.log("accepted successfully")
			 $location.path('/blogrequests')
								
			},function(error){
				console.error("Error while accepting blogrequests Forum");
			});
		 
	 }
	 
	 $scope.rejectblogrequests=function(id)
	 {
		 
		 console.log('in rejectblog request method')
		
		 $http.get("http://localhost:8081/Middleware/blogs/rejectblogRequests/"+id).then(fetchAllblogreq(),function(response){
			 
		console.log("Rejected successfully")
			 $location.path('/blogrequests')
								
			},function(error){
				console.error("Error while rejecting blogrequests Forum");
			});
		 
	 }
	
	
});