package com.niit.Middleware.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.Dao.JobDao;
import com.niit.Dao.UserDao;
import com.niit.Model.Job;
import com.niit.Model.JobApplication;
import com.niit.Model.User;

@RestController
@RequestMapping("/jobs")
public class JobController {
@Autowired
JobDao jobDao;

@Autowired
UserDao userDao;


@RequestMapping(value="/addJob",method=RequestMethod.POST)
public ResponseEntity<String> addJob(@RequestBody Job job){
	
	boolean isSaved=jobDao.addjob(job);
	if(isSaved)
	return new ResponseEntity<String>("Job addes ok",HttpStatus.OK);
	else
		return new ResponseEntity<String>("Job add error",HttpStatus.BAD_REQUEST);
	
}

@RequestMapping(value="/getJob/{jobid}",method=RequestMethod.GET,headers = "Accept=application/json")
public ResponseEntity<Job> getJob(@PathVariable("jobid") int jobId){

System.out.println("In get job controller"+jobId);
if(jobDao.getjob(jobId)==null){
	return new ResponseEntity<Job>(jobDao.getjob(jobId),HttpStatus.BAD_REQUEST);	
	
}
else
{
	return new ResponseEntity<Job>(jobDao.getjob(jobId),HttpStatus.OK);	
}


}

@RequestMapping(value="/getAllJobs",method=RequestMethod.GET,headers = "Accept=application/json")
public ArrayList<Job> getAllJobs(){
	ArrayList<Job> jobs=(ArrayList<Job>)jobDao.getAlljobs();
	if(jobs.isEmpty()){
		return null;
	}
	else
	{
		return jobs;	
	}
	
			
}



@RequestMapping(value="/deleteJob/{jobid}",method=RequestMethod.GET)
public ResponseEntity<String> deleteJob(@PathVariable("jobid") int jobId)
{
	Job job=jobDao.getjob(jobId);
	if(jobDao.deletejob(job))
	{
		return new ResponseEntity<String>("{\"success\"}",HttpStatus.OK);
	}
	return new ResponseEntity<String>("Job deletion error",HttpStatus.BAD_REQUEST);	
			
}

@RequestMapping(value="/updateJob",method=RequestMethod.POST)
public ResponseEntity<String> updateJob(@RequestBody Job job)
{
	
	boolean isSaved=jobDao.updatejob(job);
	if(isSaved)
	return new ResponseEntity<String>("job update succcess",HttpStatus.OK);
	else
		return new ResponseEntity<String>("job update failure",HttpStatus.BAD_REQUEST);
	
}


@RequestMapping(value="/applyJob/{jobid}/{myid}",method=RequestMethod.GET)
public ResponseEntity<String> applyJob(@PathVariable("jobid") int jobid,@PathVariable("myid") int myid)
{
	JobApplication jobapplications=new JobApplication();
	jobapplications.setJobid(jobid);
	jobapplications.setUserid(myid);
	boolean isSaved=jobDao.applyJob(jobapplications);
	if(isSaved)
	{
		return new ResponseEntity<String>("job applied successfully",HttpStatus.OK);
	}
	else
	{
		return new ResponseEntity<String>("job apply failed",HttpStatus.BAD_REQUEST);
	}
}


@RequestMapping(value="/myjobs/{myid}",method=RequestMethod.GET)
public ArrayList<Job> myjobs(@PathVariable("myid") int myid)
{
	ArrayList<Job> myjobs=new ArrayList<Job>();
	ArrayList<JobApplication> jobappli =jobDao.myjobs(myid);
	for(JobApplication jobapp:jobappli)
	{	
		myjobs.add(jobDao.getjob(jobapp.getJobapplyid()));
		
	}
	return myjobs;
}

@RequestMapping(value="/checkifapplied/{jobid}/{myid}",method=RequestMethod.GET)
public ArrayList<JobApplication> checkifapplied(@PathVariable("jobid") int jobid,@PathVariable("myid") int myid)
{
	return jobDao.checkIfApplied(jobid, myid);
}

@RequestMapping(value="/jobapplicants/{jobid}",method=RequestMethod.GET)
public ArrayList<User> jobapps(@PathVariable("jobid") int jobid)
{
	ArrayList<User> u=new ArrayList<User>();
	ArrayList<JobApplication> jobapps=jobDao.jobapps(jobid);
	for(JobApplication j:jobapps)
	{
		User us=userDao.getUser(j.getUserid());
		u.add(us);
	}
	return u;
}
}