package com.niit.DaoImpl;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.Dao.FriendDao;
import com.niit.Model.Friend;
import com.niit.Model.User;

@Repository("FriendDao")
@EnableTransactionManagement
public class FriendDaoImpl implements FriendDao 
{
	@Autowired
	SessionFactory sessionFactory;
	
    @Autowired
	public FriendDaoImpl(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public boolean addFriend(Friend friend)
	{
		try
		{
		sessionFactory.getCurrentSession().save(friend);
		return true;
		}
		catch(Exception e)
		{
		System.out.println(e);
		return false;
		}
	}

	@Transactional
	public boolean delete(Friend friend)
	{
		try
		{
			
		sessionFactory.getCurrentSession().delete(friend);
		return true;
		}
		catch(Exception e)
		{
		System.out.println(e);
		return false;
		}
	}

	@Transactional
	public boolean accept(int friendreqid) {
		try
		{
			Session session=sessionFactory.openSession();
		    Friend friend=(Friend) session.get(Friend.class,friendreqid);
		    friend.setStatus("Y");
		    session.saveOrUpdate(friend);
		    session.close();
		    return true;
		}
		catch(Exception e)
		{
		    System.out.println(e);
		    return false;
		}
	}

	@Transactional
	public boolean reject(int friendreqid)
	{
		try
		{
			Session session=sessionFactory.openSession();
		    Friend friend=(Friend) session.get(Friend.class,friendreqid);
		    friend.setStatus("N");
		    session.saveOrUpdate(friend);
		    session.close();
		    return true;
		}
		catch(Exception e)
		{
		    System.out.println(e);
		    return false;
		}
	}

	@Transactional
	public Friend getfriendrequest(int friendreqid, int myid) {
		Session session = sessionFactory.openSession();
		Query q= session.createQuery("from Friend where (U_ID="+myid+" and FRI_ID="+friendreqid+") or (U_ID="+friendreqid+" and FRI_ID="+myid+")" );
	    Friend mynfriend=(Friend)q.list().get(0);
	    return mynfriend;
	}

	@Transactional
	public ArrayList<Friend> getAllFriendRequestsByUser(int userid) 
	{
		Session session = sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		ArrayList<Friend> myfriends=(ArrayList<Friend>)session.createQuery("from Friend where FRI_ID="+userid+" and status='A'").list();
		session.close();
		return myfriends;
	}

	@Transactional
	public ArrayList<Friend> getAllFriend() 
	{
		Session session = sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		ArrayList<Friend> Allfriends=(ArrayList<Friend>)session.createQuery("from Friend").list();
		session.close();
		return Allfriends;
	}

	@Transactional
	public ArrayList<Friend> getAllMyFriend(int myid)
	{
		Session session = sessionFactory.openSession();
		Query q= session.createQuery("from Friend where status='YES' and (U_ID="+myid+" or FRI_ID="+myid+")" );
	    @SuppressWarnings("unchecked")
		ArrayList<Friend> myfriends=(ArrayList<Friend>)q.list();
	    return myfriends;
	}

	@Transactional
	public ArrayList<Friend> getAllpendingentries(int myid) 
	{
		Session session = sessionFactory.openSession();
		Query q= session.createQuery("from Friend where  status='A' and( U_ID="+myid+" or FRI_ID="+myid+") ");
	    @SuppressWarnings("unchecked")
		ArrayList<Friend> myfriends=(ArrayList<Friend>)q.list();
	    return myfriends;
	}

	@Transactional
	public ArrayList<Friend> getAllPendingrequests(int userid) 
	{
		Session session = sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		ArrayList<Friend> myfriends=(ArrayList<Friend>)session.createQuery("from Friend where U_ID="+userid+" and status='A'").list();
		session.close();
		return myfriends;
	}

	@Transactional
	public User getUserById(int userid) {
		User user=new User();
		try{
			Session session= sessionFactory.openSession();
			Query query=session.createQuery("from Users where userid="+userid);
			 user=(User)query.list().get(0);
			session.close();
			
		}
		catch(Exception e)
		{
			
			
		}
		return user;
	}

	@Transactional
	public Friend acceptfriendrequest(Friend friend) {
		try
		{
		sessionFactory.getCurrentSession().update(friend);
		return null;
		}
		catch(Exception e)
		{
		System.out.println(e);
		return null;
		}
	}


	public ArrayList<Friend> getAllMyFriendpend(int myid)
	{
		Session session = sessionFactory.openSession();
		Query q= session.createQuery("from Friend where (U_ID="+myid+" or FRI_ID="+myid+")");
	ArrayList<Friend> myfriends=(ArrayList<Friend>)q.list();
	return myfriends;
		}
	
	}
	


