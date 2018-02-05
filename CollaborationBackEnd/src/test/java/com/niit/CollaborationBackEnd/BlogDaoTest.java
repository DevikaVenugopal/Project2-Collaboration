package com.niit.CollaborationBackEnd;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.Dao.BlogDao;
import com.niit.Dao.UserDao;
import com.niit.Model.Blog;
import com.niit.Model.BlogComment;
import com.niit.Model.User;

import static org.junit.Assert.*;
import java.util.*;
public class BlogDaoTest 
{

	@Autowired
	private static BlogDao blogDAO;
		@Autowired
	private static UserDao userDAO;	
		
		
		
		@BeforeClass
		public static void initialize()
		{
			AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
			context.scan("com.niit");
			context.refresh();
			
			blogDAO=(BlogDao)context.getBean("BlogDao");
			userDAO=(UserDao)context.getBean("UserDao");
		
		}

	    @Ignore
		@Test
		public void addBlogTest()
		{
			Blog blog=new Blog();
			
			blog.setBlogcontent("hgfvuu");
			blog.setBlogname("hibernate spring");
			blog.setDislikes(4);
			blog.setLikes(10);
			blog.setStatus("A");
			blog.setUsername("qwerty");
			blog.setViews(14);
			User user=(User)userDAO.getUser(1);
			
			System.out.println(user.getEmail());
			assertTrue("Problem in inserting   Blog",blogDAO.addBlog(blog));

		}

		@Ignore
		@Test
		public void getblogTest()
		{
			
			Blog blog=blogDAO.getBlog(8);
			System.out.println(blog.getBlogcontent());
			System.out.println(blog.getBlogname());
		}

	    @Ignore
		@Test
		public void updateBlogTest()
		{
			
			Blog blog=(Blog)blogDAO.getBlog(11);
			blog.setBlogcontent("it is hibernate based");
			blog.setBlogname("hibernate core");
			assertTrue("Problem in Updating  Blog",blogDAO.updateBlog(blog));
			
			
		}

		@Ignore
		@Test
		public void deleteBlogTest()
		{
			Blog blog=(Blog)blogDAO.getBlog(33);
			assertTrue("Problem in Updating  Blog",blogDAO.deleteBlog(blog));
	
		}

		@Ignore
		@Test
		public void getAllBlogTest()
		{
			
		
			ArrayList<Blog> blog=(ArrayList<Blog>)blogDAO.getAllBlogs();
		for(Blog b:blog)
		{
			System.out.println(b.getBlogname());
		}
		
		}
		
		
		
		
	   @Ignore
		@Test
		public void approveBlogTest()
		{
			Blog blog=(Blog)blogDAO.getBlog(8);
			blog.setStatus("A");
			assertTrue("Problem in Approving  Blog",blogDAO.approveBlog(blog));
			
		}
		
		
		
		
		@Ignore
		@Test
		public void rejectBlogTest()
		{
			Blog blog=(Blog)blogDAO.getBlog(8);
			blog.setStatus("R");
			assertTrue("Problem in Rejecting  Blog",blogDAO.rejectBlog(blog));
			
		}
		
		
		
		
		@Ignore
		@Test
		public void likeBlogTest()
		{
		
			Blog blog =blogDAO.getBlog(8);
			blog.setLikes(blog.getLikes()+1);
					assertTrue("problemin liking blog",blogDAO.updateBlog(blog));
			
		}
		
		
		
		
		@Ignore
		@Test
		public void dislikeBlogTest()
		{
		
			Blog blog =blogDAO.getBlog(8);
			blog.setDislikes(blog.getDislikes()+1);
			assertTrue("problemin disliking blog",blogDAO.updateBlog(blog));
			
			
		}

	    @Ignore
		@Test
		public void addBlogComment()
		{
			BlogComment blogcomments = new BlogComment();
			
			blogcomments.setBlogid(8);
			blogcomments.setBlogcomm("v good");
			blogcomments.setBlogcomid(1000);
		
			assertTrue("Problem in Inserting BlogComment",blogDAO.addBlogComment(blogcomments));
		
		}

	    @Ignore
		@Test
		public void getBlogCommentTest()
		{
			BlogComment blogcomments=(BlogComment)blogDAO.getBlogComment(8);
			System.out.println(blogcomments.getBlogcomm());
			
		}

	    @Ignore
		@Test
		public void updateBlogCommentTest()
		{
			BlogComment blogcomments =blogDAO.getBlogComment(8);
			blogcomments.setBlogcomm("amazing");
			assertTrue("Problem in Updating  Blog",blogDAO.updateBlogComment(blogcomments));		
			
		}
		
		@Ignore
		@Test
		public void deleteBlogCommentTest()
		{
			BlogComment blogcomments=(BlogComment)blogDAO.getBlogComment(8);
			assertTrue("Problem in deleting  Blog",blogDAO.deleteBlogComment(blogcomments));
			
			

		}
}
		
