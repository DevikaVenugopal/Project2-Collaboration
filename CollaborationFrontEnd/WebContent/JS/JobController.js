app.controller("jobcontroller", function ($scope,$http,$location,$rootScope,$cookieStore,$route) {
$scope.Job={jobprofile:'',jobdesc:'',qualification:'',salary:'',company:'',companydesc:''};	

	function fetchAllJobs()
	{
		console.log("fetched all jobs")
		$http.get("http://localhost:8081/Middleware/jobs/getAllJobs")

		.then(function(response) {
			$rootScope.jobsdata = response.data;
			console.log("all jobs fetched")
		});
		
		
		console.log("fetched all jobs")
		$http.get("http://localhost:8081/Middleware/jobs/myjobs/"+$rootScope.currentuser.userid)

		.then(function(response) {
			$rootScope.myjobs = response.data;
			console.log("jobs",$rootScope.myjobs)
			console.log("all my jobs fetched")
		});
		
		
		
	};
	
	fetchAllJobs();
	
	$scope.insertJobs = function()
	{
		console.log('entered insertJobs');
		$http.post('http://localhost:8081/Middleware/jobs/addJob',
				$scope.Job).then(fetchAllJobs(), function(response) {
					alert("successfully jobs entered")
			console.log("successful jobs entered");
			$location.path("/jobmanage")
		});
	}
	
	
	$scope.applyJob = function(idd)
	{
		console.log('apply job'+idd);
		$http.get('http://localhost:8081/Middleware/jobs/applyJob/'+idd+"/"+$rootScope.currentuser.userid).then(fetchAllJobs(), function(response) {
			alert("Job applied successfully")
			console.log("successful jobs applied");
			$location.path("/jobapplied")
		});
	}
	
	$scope.getjob = function(idd)
	{
		
		$http.get('http://localhost:8081/Middleware/jobs/getJob/'+idd).then(function(response) {
			$rootScope.gjob=response.data;
			$cookieStore.put('job',$rootScope.gjob);	
			console.log($rootScope.gjob.jobprofile+$rootScope.gjob.company)
		
		
	},function(error){
		console.log("Error on retrieving job")
	});
			
		
		$http.get('http://localhost:8081/Middleware/jobs/checkifapplied/'+idd+"/"+$rootScope.currentuser.userid).then(function(response) {
			$rootScope.gcheck=response.data;
			$cookieStore.put('jobcheck',$rootScope.gcheck);
			console.log($rootScope.gcheck)
			
			
			});
		
		
		
		$http.get("http://localhost:8081/Middleware/jobs/jobapplicants/"+idd)
		.then(function(response)
		{
			
			$rootScope.jobapps=response.data;
			$cookieStore.put('jobapps',$rootScope.gcheck);
			
		},function(error)
		{
			
		});

		
		
		
		$location.path("/jobview")

	}
	
	$rootScope.deletejob = function(idd)
	{
		$http({
	        method: 'GET',
	        headers: {
	            'Content-Type': 'application/json'
	        },
	        url: 'http://localhost:8081/Middleware/jobs/deleteJob/'+idd,
	        transformResponse: [
	            function (data) { 
	                return data; 
	            }
	        ]
	       
	    }).then(function (response) {
	    	alert("Jobs deleted successfully")
	    	$route.reload();
	        console.log(response);
	    }, function (response) {
	        console.log(response);
	    });
		
		/*
		$http.get('http://localhost:8081/Middleware/jobs/deleteJob/'+idd).then(function(response) {
			
		alert("Job Deleted Successfully.....")
		$location.path('/jobmanage')
		
	},function(error){
		console.log(error)
		alert("Error Occured",error);
		
	});*/
		
	}
		
	
	$scope.fetchjobforedit=function(idd)
	{
		
		$http.get("http://localhost:8081/Middleware/jobs/getJob/"+idd).then(function(response) {
			console.log('get job for edit method ok'+idd)
			$rootScope.ejob=response.data;
							
				
					});
		$location.path('/updatejob')
		
		
	}
	
	
	$scope.editjob=function(idd)
	{
		console.log("ejob",$rootScope.ejob,idd)
	if($scope.Job.jobprofile==null)
		{
		//alert("jobprofile null");
		
		$scope.Job.jobprofile=$rootScope.ejob['jobprofile'];
			}
		
		if($scope.Job.jobdesc==null)
		{
			//alert("jobdesc null");
		$scope.Job.jobdesc=$rootScope.ejob['jobdesc'];
			}
		
		
		if($scope.Job.qualification==null)
		{
			//alert("qualification null");
		$scope.Job.qualification=$rootScope.ejob['qualification'];
			}
		
		
		if($scope.Job.salary==null)
		{
			//alert("salary null");
		$scope.Job.salary=$rootScope.ejob.salary;
			}
		
		
		if($scope.Job.company==null)
		{
			//alert("company null");
		$scope.Job.company=$rootScope.ejob.company;
			}
		
		if($scope.Job.companydesc==null)
		{
			alert("companydesc null");
		$scope.Job.companydesc=$rootScope.ejob.companydesc;
			}
		$scope.Job.jobid=$rootScope.ejob.jobid;
		console.log("job",$scope.Job);
		$http.post("http://localhost:8081/Middleware/jobs/updateJob",$scope.Job).then(fetchAllJobs(), function(response)
				{
		    alert("Job Ubdated successfully")
			 console.log("job updated successfully");
				
		},function(error){
			console.error("Error while updating job");
		
		});
		
		$location.path("/jobmanage")
		
		
	}
	
	
	
	
	
});