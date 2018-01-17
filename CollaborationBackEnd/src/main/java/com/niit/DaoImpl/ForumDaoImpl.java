package com.niit.DaoImpl;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.Dao.ForumDao;
import com.niit.Model.Forum;
import com.niit.Model.ForumComment;
import com.niit.Model.ForumRequest;

@Repository("ForumDao")
@EnableTransactionManagement
public class ForumDaoImpl implements ForumDao
{
	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	public ForumDaoImpl(SessionFactory sessionFactory) {
		
		this.sessionFactory = sessionFactory;
	}
	
	
	@Transactional
	public boolean addForum(Forum forum) {
		try
		{
		sessionFactory.getCurrentSession().save(forum);
		return true;
		}
		catch(Exception e)
		{
		System.out.println(e);
		return false;
		}
	}

	
	@Transactional
	public boolean updateForum(Forum forum) {
		try
		{
		sessionFactory.getCurrentSession().saveOrUpdate(forum);
		return true;
		}
		catch(Exception e)
		{
		System.out.println(e);
		return false;
		}
	}
	
	
	
	
	@Transactional
	public boolean deleteForum(Forum forum) {
		try
		{
		sessionFactory.getCurrentSession().delete(forum);
		return true;
		}
		catch(Exception e)
		{
		System.out.println(e);
		return false;
		}
	}

	
	
	@Transactional
	public Forum getForum(int forumId) {
		Session session=sessionFactory.openSession();
		Forum forum = (Forum) session.get(Forum.class, forumId);
		session.close();
		return forum;
	}
	
	
	
	@Transactional
	public ArrayList<Forum> getAllForum() {
		Session session = sessionFactory.openSession();
		ArrayList<Forum> forumList=(ArrayList<Forum>)session.createQuery("from Forum").list();
		session.close();
		return forumList;
	}



	
	
	@Transactional
	public boolean addForumComment(ForumComment forumcomment) {
		
		try
		{
		sessionFactory.getCurrentSession().save(forumcomment);
		return true;
		}
		catch(Exception e)
		{
		System.out.println(e);
		return false;
		}
	}

	
	
	@Transactional
	public boolean updateForumComment(ForumComment forumcomment) {
		try
		{
		sessionFactory.getCurrentSession().saveOrUpdate(forumcomment);
		return true;
		}
		catch(Exception e)
		{
		System.out.println(e);
		return false;
		}
	}

	
	
	@Transactional
	public boolean deleteForumComment(ForumComment forumcomment)
	{
		try
	{
	sessionFactory.getCurrentSession().delete(forumcomment);
	return true;
	}
	catch(Exception e)
	{
	System.out.println(e);
	return false;
	}
	}


	@Transactional
	public ForumComment getForumComment(int commentId) {
		Session session=sessionFactory.openSession();
		ForumComment forumcomment = (ForumComment) session.get(ForumComment.class, commentId);
		session.close();
		return forumcomment;
	}

	
@Transactional
public ArrayList<ForumComment> getAllForumCommentsById(int forumid) {
		Session ssn=sessionFactory.openSession();
		
		
		org.hibernate.Query q= ssn.createQuery("from ForumComments where forumid="+forumid);
		ArrayList<ForumComment> l=(ArrayList<ForumComment>) q.list();
		
        
        ssn.close();


		
		return l;
		
	}

@Transactional
public boolean addForumRequest(ForumRequest forumrequest) {
	try
	{
		sessionFactory.getCurrentSession().save(forumrequest);
	return true;
	}
	catch(Exception e)
	{
	System.out.println(e);
	return false;
	}
}

@Transactional
public boolean acceptForumRequest(ForumRequest forumrequest) {
	try
	{
	sessionFactory.getCurrentSession().saveOrUpdate(forumrequest);
	return true;
	}
	catch(Exception e)
	{
	System.out.println(e);
	return false;
	}
}

@Transactional
public boolean blockUser(ForumRequest forumrequest) {
	try
	{
	sessionFactory.getCurrentSession().saveOrUpdate(forumrequest);
	return true;
	}
	catch(Exception e)
	{
	System.out.println(e);
	return false;
	}	
}

@Transactional
public ArrayList<ForumRequest> getAllForumRequest() {
	Session session = sessionFactory.openSession();
	ArrayList<ForumRequest> forumReqList=(ArrayList<ForumRequest>)session.createQuery("from ForumRequests where status='A'").list();
	session.close();
	return forumReqList;

}

@Transactional
public ForumRequest getForumRequest(int ForumReqId) {
	
	Session session=sessionFactory.openSession();
	ForumRequest forumReq = (ForumRequest) session.get(ForumRequest.class, ForumReqId);
	session.close();
	return forumReq;
	
}

@Transactional
public ArrayList<ForumRequest> getAllMyForum(int myid) {
	Session session = sessionFactory.openSession();
	ArrayList<ForumRequest> myforums=(ArrayList<ForumRequest>)session.createQuery("from ForumRequests where userid="+myid+" and status='YES'").list();
	session.close();
	return myforums;
	
}

@Transactional
public ArrayList<ForumRequest> checkIfMyForum(int ForumId, int myid) {
	
	Session session = sessionFactory.openSession();
	ArrayList<ForumRequest> myforums=(ArrayList<ForumRequest>)session.createQuery("from ForumRequests where userid="+myid+" and forumid="+ForumId).list();
	session.close();
	return myforums;
}

@Transactional
public ArrayList<ForumRequest> forreqbyforid(int forumid) {
	Session session = sessionFactory.openSession();
	ArrayList<ForumRequest> forumsbyforid=(ArrayList<ForumRequest>)session.createQuery("from ForumRequests where forumid="+forumid+" and status='YES'").list();
	session.close();
	return forumsbyforid;
	
}


}
