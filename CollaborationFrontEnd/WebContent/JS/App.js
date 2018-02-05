var app = angular.module("myApp", ["ngRoute","ngCookies"]);

app.config(function($routeProvider) {
    $routeProvider
    .when("/", {
        templateUrl : "Views/Home.html"
    })
    .when("/login", {
        templateUrl : "Views/User/Login.html"
    })
    .when("/registration", {
        templateUrl : "Views/User/RegistrationForm.html"
    })
    .when("/job", {
        templateUrl : "Views/Job/job.html"
    })
    .when("/blog", {
        templateUrl : "Views/Blog/blog.html"
    })
    .when("/blogmanage", {
        templateUrl : "Views/Blog/blogmanage.html"
    })
    .when("/blogrequests", {
        templateUrl : "Views/Blog/blogrequests.html"
    })
     .when("/jobmanage", {
        templateUrl : "Views/Job/jobmanage.html"
    })
    .when("/newjob", {
        templateUrl : "Views/Job/newjob.html"
    })
    .when("/jobapplied", {
        templateUrl : "Views/Job/jobapplied.html"
    })
    .when("/updatejob", {
        templateUrl : "Views/Job/updatejob.html"
    })
    .when("/newblog", {
        templateUrl : "Views/Blog/newblog.html"
    })
    .when("/updateblog", {
        templateUrl : "Views/Blog/updateblog.html"
    })
    .when("/userlist", {
        templateUrl : "Views/User/UserList.html"
    })
    .when("/userrequests", {
        templateUrl : "Views/User/userrequests.html"
    })
    .when("/friendrequests", {
        templateUrl : "Views/Friend/FriendRequests.html"
    })
    .when("/acceptedfriends", {
        templateUrl : "Views/Friend/acceptedfriends.html"
    })
    .when("/profile", {
        templateUrl : "Views/MyProfile.html"
    })
    .when("/myblogs", {
        templateUrl : "Views/Blog/myblogs.html"
    })
    .when("/userlist", {
        templateUrl : "Views/User/UserList.html"
    })
    .when("/notification", {
        templateUrl : "Views/Notification/Notification.html"
    })
    .when('/chat',
		{
			templateUrl : 'Views/chat.html',
			controller : 'ChatController'	
		})
		
	.when("/forum", {
        templateUrl : "Views/Forum/Forum.html"
    })
    .when("/forummanage", {
        templateUrl : "Views/Forum/forummanage.html"
    })	
    .when("/forumrequests", {
        templateUrl : "Views/Forum/forumrequests.html"
    })
    .when("/newforum", {
        templateUrl : "Views/Forum/newforum.html"
    })	
    .when("/updateforum", {
        templateUrl : "Views/Forum/updateforum.html"
    })
    .when("/myforum", {
        templateUrl : "Views/Forum/myforum.html"
    })
    .when("/blogview", {
        templateUrl : "Views/Blog/blogview.html"
    })
    .when("/viewforum", {
        templateUrl : "Views/Forum/viewforum.html"
    })
    .when("/notification", {
        templateUrl : "Views/Notification/Notification.html"
    })
    ;
    
});
    
    
    
    
    app.run( function ($rootScope, $location, $cookieStore, $http) 
    		{
    			 $rootScope.$on('$locationChangeStart', function (event, next, current) 
    			 {
    				 console.log("$locationChangeStart")
    				    
    				 var userPages = ['/blog','/Chat','/Forum','/viewforum'];
    				 var adminPages = [];
    				
    				 var currentPage = $location.path();
    				 
    				 var isUserPage = $.inArray(currentPage, userPages);
    				 var isAdminPage = $.inArray(currentPage, adminPages);
    				 
    				 var isLoggedIn = $rootScope.currentuser.username;
    			        
    			     console.log("isLoggedIn:" +isLoggedIn)
    			     console.log("isUserPage:" +isUserPage)
    			     console.log("isAdminPage:" +isAdminPage)
    			        
    			        if(!isLoggedIn)
    			        	{
    			        	
    			        		if(isUserPage!=-1 || isAdminPage!=-1)  
    			        	 	{
    				        	  console.log("Navigating to login page:")
    				        	  alert("You need to Login first!")
    				        	  $location.path('/login');
    				         	}
    			        	}
    			        
    					 else //logged in
    			        	{
    			        	
    						 var role = $rootScope.currentuser.role;
    						 if(isAdminPage!=-1 && role!='ADMIN' )
    							 {
    							  alert("You cannot view this page as a " + role )
    							  $location.path('/');
    							 }
    			        	}
    			 });
    			 
    			 // to keep the user logged in after page refresh
    		    $rootScope.currentuser = $cookieStore.get('currentUser') || {};
    		    if ($rootScope.currentuser)
    		    {
    		        $http.defaults.headers.common['Authorization'] = 'Basic' + $rootScope.currentuser; 
    		    }
    		    
    		    // to keep the blog view in after page refresh
    		    $rootScope.gblog = $cookieStore.get('blog') || {};
			    if ($rootScope.gblog)
			    {
			        $http.defaults.headers.common['Authorization'] = 'Basic' + $rootScope.gblog; 
			    }
			    
			    
			    $rootScope.gblogcomm = $cookieStore.get('blogcomm') || {};
			    if ($rootScope.gblogcomm)
			    {
			        $http.defaults.headers.common['Authorization'] = 'Basic' + $rootScope.gblogcomm; 
			    }
			    
			 // to keep the forum view  in after page refresh
			    
			    $rootScope.ForumByid = $cookieStore.get('forum') || {};
			    if ($rootScope.ForumByid)
			    {
			        $http.defaults.headers.common['Authorization'] = 'Basic' + $rootScope.ForumByid; 
			    }
			    
			    $rootScope.fcheck = $cookieStore.get('forumcheck') || {};
			    if ($rootScope.fcheck)
			    {
			        $http.defaults.headers.common['Authorization'] = 'Basic' +$rootScope.fcheck; 
			    }
			    
			    
			    $rootScope.gforumcomm = $cookieStore.get('forumcomm') || {};
			    if ($rootScope.gforumcomm)
			    {
			        $http.defaults.headers.common['Authorization'] = 'Basic' +$rootScope.gforumcomm; 
			    }
    		});