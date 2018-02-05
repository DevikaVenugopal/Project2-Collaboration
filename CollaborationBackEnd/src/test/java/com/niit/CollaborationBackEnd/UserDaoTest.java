package com.niit.CollaborationBackEnd;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.Dao.UserDao;
import com.niit.Model.User;

public class UserDaoTest
{
	@Autowired
	public static UserDao userDao;
		
		
		@BeforeClass
		public static void initialize()
		{
			@SuppressWarnings("resource")
			AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
			context.scan("com.niit");
			context.refresh();
			
			userDao=(UserDao)context.getBean("UserDao");
		}
		
		@Ignore
		@Test
		public void getAllUserTest()
		{
			ArrayList<User> user= userDao.getAllUser();
			for(User u:user)
			{
				System.out.println(u.getFirstname());
				System.out.println(u.getLastname());
				System.out.println(u.getPassword());
			
			}
		}
		
		
	   // @Ignore
		@Test
		public void addUserTest()
		{
			User user =new User();
			user.setFirstname("xyz");
			user.setLastname("uvw");
			user.setEmail("xyz@gmail.com");
			user.setIsonline("Yes");
			user.setPassword("123");
			user.setRole("ROLE_USER");
			user.setGender("F");
			user.setPhone("9400765869");
			user.setUsername("ghy");
			user.setStatus("A");
			user.setPlace("Thrissur");
		
			
			
			
		
			assertTrue("Problem in Inserting User",userDao.saveUser(user));	
		}
		
		@Ignore
		@Test
		public void getUserTest()
		{
			
			User user=(User)userDao.getUser(1236);
			System.out.println(user.getEmail());
			
		}
		
		@Ignore
		@Test
		public void updateOnlineStatusTest()

		{
			User user=userDao.getUser(2);
			
			assertTrue("Problem in updating Online Status",userDao.updateOnlineStatus(user));
			
			
		}
		
		
		@Ignore
		@Test
		public void checklogin()
		{
			User user=(User)userDao.getUser(3);
			System.out.println(user.getEmail());
			
			assertTrue("Problem in login Status",userDao.checkLogin(user));
			
		}
		
		
		@Ignore
		@Test
		public void getuserbyemail()
		{
			User user=(User)userDao.getUserbyemail("devikavenugopal951@gmail.com");
			System.out.println(user.getEmail());
			
			
			
		}

	
}
