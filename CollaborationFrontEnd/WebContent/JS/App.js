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
    .when("/profile", {
        templateUrl : "Views/MyProfile.html"
    })
    ;
    
});
    
    
    
    
    app.run( function ($rootScope, $location, $cookieStore, $http) 
    		{
    			 $rootScope.$on('$locationChangeStart', function (event, next, current) 
    			 {
    				 console.log("$locationChangeStart")
    				    
    				 var userPages = ['/blog','/Chat','/Forum'];
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
    		});