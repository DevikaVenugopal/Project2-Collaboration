package com.niit.CollaborationBackEnd;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.Dao.JobDao;
import com.niit.Model.Job;


public class JobDaoTest {
	
	@Autowired
	private static  JobDao jobDao;
	@BeforeClass
	public static void initialize()
	{
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		
		jobDao=(JobDao)context.getBean("JobDao");
	}
	//@Ignore
	@Test
	public void addjob()
	{
		Job job=new Job();
		job.setJobdesc("Devops");
		job.setJobprofile("Web App developer");
		job.setQualification("Degree");
		job.setSalary(10000);
		job.setCompany("xyu");
		job.setCompanydesc("ghjdfgndr");
		
		
		
		
		assertTrue("Problem in inserting job",jobDao.addjob(job));		
	}
    
	@Ignore
	@Test
	public void getJob()
	{
		Job job=jobDao.getjob(1);
		System.out.println(job.getJobdesc());
		
		
	}
	
    @Ignore
	@Test
	public void getAllJobs()
	{
		
		ArrayList<Job> job=(ArrayList<Job>)jobDao.getAlljobs();
		for(Job j:job)
		{
			System.out.println(j.getJobdesc());
			
		}
		
	}
    
    @Ignore
    @Test
    public void updateJob()
    {

	    Job job=jobDao.getjob(1);
	    job.setJobdesc("software developer");
	    assertTrue("problem in updating Job",jobDao.updatejob(job));
	
     }
    
    @Ignore
	@Test
	public void deletejob()
	{
		
		Job job=jobDao.getjob(67);
		assertTrue("problem in deleting Job",jobDao.deletejob(job));
	}
}