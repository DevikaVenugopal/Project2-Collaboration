package com.niit.Dao;

import java.util.ArrayList;

import com.niit.Model.Friend;
import com.niit.Model.User;

public interface UserDao 
{
	ArrayList<User> getAllUser();
	 public boolean saveUser(User user);
	 public boolean updateOnlineStatus(User user);
		public User getUser(int userid);
		public boolean checkLogin(User user);
		public User getUserbyId(int uderid);
		public User getUserbyemail(String email);
		public ArrayList<Friend> checkismyfriend(int userid,int myid);
		public ArrayList<User> userrequests();
		public boolean approveusers(User users);
		public boolean checkLoginsimp(User user);
		public boolean checkLoginsemail(User user);
		public boolean rejectusers(User user);
	

}
