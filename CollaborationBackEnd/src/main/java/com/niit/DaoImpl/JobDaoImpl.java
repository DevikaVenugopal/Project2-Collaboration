package com.niit.DaoImpl;

import java.util.ArrayList;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.niit.Dao.JobDao;
import com.niit.Model.Job;
import com.niit.Model.JobApplication;


@Repository("JobDao")
@EnableTransactionManagement
public class JobDaoImpl implements JobDao
{
	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	public JobDaoImpl(SessionFactory sessionFactory) 
	{
		this.sessionFactory = sessionFactory;
	}
	
	
	@Transactional
	public boolean addjob(Job job) {
		
		try
		{
		sessionFactory.getCurrentSession().save(job);
		return true;
		}
		catch(Exception e)
		{
		System.out.println(e);
		return false;
		}
		
	}
	
	@Transactional
	public boolean updatejob(Job job) {

		
		try
		{
		sessionFactory.getCurrentSession().update(job);
		return true;
		}
		catch(Exception e)
		{
		System.out.println(e);
		return false;
		}
	}
	@Transactional
	public boolean deletejob(Job job) {
		

		try
		{
		sessionFactory.getCurrentSession().delete(job);
		return true;
		}
		catch(Exception e)
		{
		System.out.println(e);
		return false;
		}
		
	}
	@Transactional
	public Job getjob(int jobId)
	{
		
		Session session=sessionFactory.openSession();
		Job job = (Job) session.get(Job.class, jobId);
		session.close();
		return job;
		
	}

	@Transactional
	public ArrayList<Job> getAlljobs() {
		
		Session session = sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		ArrayList<Job> jobList=(ArrayList<Job>)session.createQuery("from Job").list();
		session.close();
		return jobList;
		
	}


	@Transactional
	public boolean applyJob(JobApplication jobapplications){
		try
		{
		sessionFactory.getCurrentSession().save(jobapplications);
		return true;
		}
		catch(Exception e)
		{
		System.out.println(e);
		return false;
		}
		
		
	}
	@Transactional
	public ArrayList<JobApplication> myjobs(int myid) {
		Session session = sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		ArrayList<JobApplication> jobapllicationlist=(ArrayList<JobApplication>)session.createQuery("from JobApplication where userid="+myid).list();
		session.close();
		return jobapllicationlist;
	}
	
	@Transactional
	public ArrayList<JobApplication> checkIfApplied(int jobid, int myid) 
	{
		Session session = sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		ArrayList<JobApplication> checkifapplied=(ArrayList<JobApplication>)session.createQuery("from JobApplication where userid="+myid+" and jobid="+jobid);
	    session.close();
	    return checkifapplied;
	}

	public ArrayList<JobApplication> jobapps(int jobid) 
	{
		Session session = sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		ArrayList<JobApplication> jobapps=(ArrayList<JobApplication>)session.createQuery("from JobApplication where  jobid="+jobid).list();
		session.close();
		return jobapps;
	}
	

}
