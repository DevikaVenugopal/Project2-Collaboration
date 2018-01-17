package com.niit.Dao;

import java.util.ArrayList;

import com.niit.Model.Job;
import com.niit.Model.JobApplication;

public interface JobDao 
{
	public boolean addjob(Job job);
	public boolean updatejob(Job job);
	public boolean deletejob(Job job);
	public Job getjob(int jobId);
	public ArrayList<Job> getAlljobs();
	public boolean applyJob(JobApplication jobapplications);
	public ArrayList<JobApplication> myjobs(int myid);
	public ArrayList<JobApplication> checkIfApplied(int jobid,int myid);
	public ArrayList<JobApplication> jobapps(int jobid);

}
