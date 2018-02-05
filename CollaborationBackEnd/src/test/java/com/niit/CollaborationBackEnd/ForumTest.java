package com.niit.CollaborationBackEnd;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.Dao.ForumDao;
import com.niit.Dao.UserDao;
import com.niit.Model.Forum;
import com.niit.Model.ForumComment;
import com.niit.Model.ForumRequest;

public class ForumTest 
{
	@Autowired
	private static ForumDao forumDao;
	@Autowired
	private static UserDao userDao;
	@BeforeClass
	public static void initialize()
	{
		AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();
		
		forumDao=(ForumDao)context.getBean("forumDao");
		userDao=(UserDao)context.getBean("UserDao");
	}

	@Ignore
	@Test
	public void addForumTest()
	{
		Forum forum=new Forum();
		
		
		forum.setFormcontent("Ruby on raisl");
		forum.setFormname("fgvfdhb");
		
		
		
		assertTrue("Problem in adding Forum  ",forumDao.addForum(forum));
	
	
	}

	@Ignore
	@Test
	public void getForumTest()
	{
		
		Forum forum=forumDao.getForum(1);
		System.out.println(forum.getFormcontent());	
		System.out.println(forum.getFormname());
	}


    @Ignore
	@Test
	public void updateForumTest()
	{
		
	Forum forum=forumDao.getForum(1);
	forum.setFormcontent("Kotlin or java");
	forum.setFormname("Android tech");
		assertTrue("Problem in Updading forum",forumDao.updateForum(forum));
		
		
	}
	
    @Ignore
	@Test
	public void deleteForumTest()
	{
		Forum forum=forumDao.getForum(1);
		assertTrue("Problem in deleting forum",forumDao.deleteForum(forum));
	}

	@Ignore
	@Test
	public void getAllForumTest()
	{
		
	
		ArrayList<Forum> forum=(ArrayList<Forum>)forumDao.getAllForum();
	for(Forum f:forum)
	{
		System.out.println(f.getFormname());
	}
	
	}
	
    @Ignore
	@Test
	public void addForumComment()
	{
		
		ForumComment forumcomment =new ForumComment();
		forumcomment.setForumcomm("nicely written");
		forumcomment.setForumid(2);
		
		
		assertTrue("Problem in adding Forumcomment ",forumDao.addForumComment(forumcomment));
		
	}
	
	
	
@Ignore
	@Test
	public void getForumCommentTest()
	{
		
		ForumComment forumcomment=forumDao.getForumComment(2);
		System.out.println(forumcomment.getForumcomm());
		
	}
	
@Ignore
@Test
public void getForumCommentByForumidTest()
{
	
	ArrayList<ForumComment> forumcomment=(ArrayList<ForumComment>)forumDao.getAllForumCommentsById(2);
	for(ForumComment f:forumcomment)
		
	{
		System.out.println(f.getForumcomm());
	}
	
}
@Ignore
	
	@Test
	public void updateForumCommentTest()
	{
		ForumComment forumcomment =forumDao.getForumComment(2);
		forumcomment.setForumcomm("Too good");
		assertTrue("Problem in updating Forumcomment ",forumDao.updateForumComment(forumcomment));
		
		
	}
	
	
@Ignore
	@Test
	public void deleteForumCommentTest()
	{
		ForumComment forumcomment=forumDao.getForumComment(4);
		assertTrue("Problem in deleting Forumcomment ",forumDao.deleteForumComment(forumcomment));
		
	}

@Ignore
@Test
public void addForumRequestTest()
{
	ForumRequest forumrequest=new ForumRequest();


forumrequest.setForumid(1);
forumrequest.setUserid(1);
assertTrue("Problem in inserting forumreq ",forumDao.addForumRequest(forumrequest));
	
	
}


@Ignore
@Test
public void acceptForumRequestTest()
{
ForumRequest forumrequest=forumDao.getForumRequest(1);
forumrequest.setStatus("YES");
assertTrue("Problem in  forumreq ",forumDao.acceptForumRequest(forumrequest));
}

@Ignore
@Test
public void blockUserTest()
{
	ForumRequest forumrequest=forumDao.getForumRequest(1);
	forumrequest.setStatus("NO");
	assertTrue("Problem in  forumreq ",forumDao.blockUser(forumrequest));	
	
}


@Ignore
@Test
public void getForumRequestTest()
{
	ForumRequest forumrequest=forumDao.getForumRequest(1);
	System.out.println(forumrequest.getForumid());
	
}

@Ignore
@Test
public void getAllForumRequestTest()
{
	ArrayList<ForumRequest> forumrequests=forumDao.getAllForumRequest();
	for(ForumRequest f:forumrequests)
	{
		System.out.println(f.getForreqid());
	}
}
}
