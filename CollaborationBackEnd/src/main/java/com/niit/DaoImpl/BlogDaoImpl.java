package com.niit.DaoImpl;

import java.util.ArrayList;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.Dao.BlogDao;
import com.niit.Model.Blog;
import com.niit.Model.BlogComment;

@Repository("BlogDao")
@EnableTransactionManagement
public class BlogDaoImpl implements BlogDao
{
	
	@Autowired
	SessionFactory sessionFactory;
	

	
	@Autowired
	public BlogDaoImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory=sessionFactory;
	}



@Transactional
	public boolean like(int blogid) {
		try
		{
			Session session=sessionFactory.openSession();
			Blog blog = (Blog) session.get(Blog.class, blogid);
			
			session.update(blog);
			
		return true;
		}
		catch(Exception e)
		{
		System.out.println(e);
		return false;
		}
	}

@Transactional
	public boolean dislike(int blogid) {
		try
		{
			Session session=sessionFactory.openSession();
			Blog blog = (Blog) session.get(Blog.class, blogid);
			blog.setLikes(blog.getDislikes()+1);
			session.update(blog);
		return true;
		}
		catch(Exception e)
		{
		System.out.println(e);
		return false;
		}
	}

@Transactional
	public boolean incview(int blogid) {
		try
		{
			Session session=sessionFactory.openSession();
			Blog blog = (Blog) session.get(Blog.class, blogid);
			
			session.update(blog);
			
		return true;
		}
		catch(Exception e)
		{
		System.out.println(e);
		return false;
		}	
	}


@Transactional
	public boolean addBlog(Blog blog) {
		try
		{
		sessionFactory.getCurrentSession().save(blog);
		return true;
		}
		catch(Exception e)
		{
		System.out.println(e);
		return false;
		}
	}


@Transactional
	public boolean updateBlog(Blog blog) {
		try
		{
		sessionFactory.getCurrentSession().update(blog);
		return true;
		}
		catch(Exception e)
		{
		System.out.println(e);
		return false;
		}
	}


@Transactional
	public boolean deleteBlog(Blog blog) {
		try
		{
		sessionFactory.getCurrentSession().delete(blog);
		return true;
		}
		catch(Exception e)
		{
		System.out.println(e);
		return false;
		}
	}


@Transactional
	public Blog getBlog(int blogId) {
		Session session=sessionFactory.openSession();
		Blog blog = (Blog) session.get(Blog.class, blogId);
		session.close();
		return blog;
	}


@Transactional
	public ArrayList<Blog> getAllBlogs() 
	{

		Session session = sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		ArrayList<Blog> blogList=(ArrayList<Blog>)session.createQuery("from Blog where status='A'").list();
		session.close();
		return blogList;
	}

@Transactional
	public boolean approveBlog(Blog blog)
	{
		try
		{
			blog.setStatus("A");
			sessionFactory.getCurrentSession().saveOrUpdate(blog);
			return true;
			
		}
		catch(Exception e)
		{
		
		return false;
		}
	}

@Transactional
	public boolean rejectBlog(Blog blog)
	{
		try
		{
			blog.setStatus("R");
			sessionFactory.getCurrentSession().saveOrUpdate(blog);
			return true;
			
		}
		catch(Exception e)
		{
		
		return false;
		}
	}

@Transactional
	public boolean addBlogComment(BlogComment blogcomment)
	{
		try
		{
		sessionFactory.getCurrentSession().save(blogcomment);
		return true;
		}
		catch(Exception e)
		{
		System.out.println(e);
		return false;
		}
	}

@Transactional
	public boolean deleteBlogComment(BlogComment blogcomment) 
	{
		try
		{
		sessionFactory.getCurrentSession().delete(blogcomment);
		return true;
		}
		catch(Exception e)
		{
		System.out.println(e);
		return false;
		}
	}

@Transactional
	public boolean updateBlogComment(BlogComment blogcomment) 
	{
		try
		{
		sessionFactory.getCurrentSession().update(blogcomment);
		return true;
		}
		catch(Exception e)
		{
		System.out.println(e);
		return false;
		}

	}

@Transactional
	public BlogComment getBlogComment(int commentId)
	{
		Session session=sessionFactory.openSession();
		BlogComment blogcomment = (BlogComment) session.get(BlogComment.class, commentId);
		session.close();
		return blogcomment;
	}

@Transactional
	public ArrayList<BlogComment> getAllBlogComments(int blogid)
	{
        Session ssn=sessionFactory.openSession();
		
		org.hibernate.Query q= ssn.createQuery("from BlogComment where blogid="+blogid);
		@SuppressWarnings("unchecked")
		ArrayList<BlogComment> l=(ArrayList<BlogComment>) q.list();
		
	    ssn.close();

		return l;
	}

@Transactional
	public ArrayList<Blog> getAllBlogRequests()
	{

		Session session = sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		ArrayList<Blog> blogreq=(ArrayList<Blog>)session.createQuery("from Blog where status='P'").list();
		session.close();
		return blogreq;	
	}

    @Transactional
	public ArrayList<Blog> getAllMyBlogs(String email)
	{
		Session session = sessionFactory.openSession();
		@SuppressWarnings("unchecked")
		ArrayList<Blog> myblogs=(ArrayList<Blog>)session.createQuery("from Blog where username='"+email+"'").list();
		session.close();
		return myblogs;
	}

	
	

}

