package com.niit.Middleware.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.Dao.FriendDao;
import com.niit.Dao.UserDao;
import com.niit.Model.Friend;
import com.niit.Model.User;

@RestController
@RequestMapping("/friend")

public class FriendController {
	
	@Autowired 
	FriendDao friendDao;
	@Autowired 
	UserDao userDao;
	
	
	@RequestMapping(value="/addFriend/{myid}/{friendid}",method=RequestMethod.GET)
	public ResponseEntity<String> addBlog(@PathVariable("myid") int myid,@PathVariable("friendid") int friendid){
	
Friend friend=new Friend();

friend.setU_ID(myid);
friend.setFRI_ID(friendid);
friend.setStatus("A");

		
		
		boolean isSaved=friendDao.addFriend(friend);
		if(isSaved)
		{
			
		return new ResponseEntity<String>("Adding friend successfull",HttpStatus.OK);
		}
		else
		{	
			return new ResponseEntity<String>("Error in adding friend",HttpStatus.BAD_REQUEST);
		}

	}
	
	 @RequestMapping(value="/getMyFriends/{myid}",method=RequestMethod.GET)
	 public ArrayList<User> getMyFriends(@PathVariable("myid") int myid)
	 {
		 
	ArrayList<Friend> myFriends=friendDao.getAllMyFriend(myid) ;
	ArrayList<User> user=new ArrayList<User>();
	
	
	for(Friend s:myFriends)
	{
		if(s.getU_ID()==myid)
		{
			user.add(userDao.getUserbyId(s.getFRI_ID()));
					}
		else if(s.getFRI_ID()==myid)
		{
		 user.add(userDao.getUserbyId(s.getU_ID()));
		}
	}
	
	
	
	return user;
		 
	 }
	
	

	 @RequestMapping(value="/getAllOtherUsers/{myid}",method=RequestMethod.GET)
		public ArrayList<User> getAllFriends(@PathVariable("myid") int myid)
	 {User us=new User();
		ArrayList<User> searchFriends=new ArrayList<User>();
		ArrayList<Friend> myFriends=friendDao.getAllMyFriend(myid);
		ArrayList<String> myfriendsname=new ArrayList<String>(); 
		for(Friend s:myFriends)
		{
			if(s.getU_ID()==myid)
			{
				myfriendsname.add(userDao.getUser(s.getFRI_ID()).getEmail());
			}
			else if(s.getFRI_ID()==myid)
			{
				myfriendsname.add(userDao.getUser(s.getU_ID()).getEmail());
			}
		}
	
		
		ArrayList<User> allUser=userDao.getAllUser();
			
		
		for(User u:allUser)
		{int count=0;
			if(u.getUserid()!=myid)
			{
			for(String s:myfriendsname)
			{
				if(u.getEmail()!=s)
				{
					count++;
				}
					
				
			}
			if(count==myfriendsname.size())
			{
				searchFriends.add(u);
			}
		}
			
		}
ArrayList<Friend> pend=friendDao.getAllpendingentries(myid);
ArrayList<String> pendnames=new ArrayList<String>();


for(Friend s:pend)
{
	if(s.getU_ID()==myid)
	{
		pendnames.add(userDao.getUser(s.getFRI_ID()).getEmail());
	}
	else if(s.getFRI_ID()==myid)
	{
		pendnames.add(userDao.getUser(s.getU_ID()).getEmail());
	}
}


ArrayList<User> newFriends=new ArrayList<User>();
for(User uu:searchFriends)
{ int count=0;
	
	for(String ff:pendnames)
	{
		if(uu.getEmail()!=ff)
		{
			count++;
		}
		
	}
	if(count==pendnames.size())
	{
		newFriends.add(uu);
	}
}
		return newFriends; 	
		}
	 
	 
	 
	 @RequestMapping(value="/getOnlineFriends/{myid}",method=RequestMethod.GET)
	 public ArrayList<User> getOnlineFriends(@PathVariable("myid") int myid)
	 {
		 
	ArrayList<Friend> myFriends=friendDao.getAllMyFriend(myid) ;
	ArrayList<User> user=new ArrayList<User>();
	for(Friend s:myFriends)
	{
		if(s.getU_ID()==myid)
		{
			user.add(userDao.getUserbyId(s.getFRI_ID()));
		}
		else if(s.getFRI_ID()==myid)
		{
			user.add(userDao.getUserbyId(s.getU_ID()));
		}
	}
for(User us:user)
{
	System.err.println(us.getEmail());
}
	ArrayList<User> onlineusers=new ArrayList<User>();
	for(User uu:user)
	{
		if(uu.getIsonline().equals("YES"))
		{
			onlineusers.add(uu);
		}
	}
	return onlineusers;
		 
	 }
	 
	 @RequestMapping(value="/getAllMyFriendRequests/{myid}",method=RequestMethod.GET)
	 public ArrayList<User> getAllMyFriendRequests(@PathVariable("myid") int myid)
	 
	 {
		 ArrayList<User> frequests =new ArrayList<User>(); 
	 ArrayList<Friend> frireq=friendDao.getAllFriendRequestsByUser(myid);
	 for(Friend f:frireq)
	 {
		 System.err.println(f.getU_ID());
	 }
	 
	 
	 for(Friend f:frireq)
	 {

			if(f.getU_ID()==myid)
			{
				frequests.add(userDao.getUserbyId(f.getFRI_ID()));
			}
			else if(f.getFRI_ID()==myid)
			{
				frequests.add(userDao.getUserbyId(f.getU_ID()));
			} 
	 }
	 return frequests; 
	 }
	 
     @RequestMapping(value="/unfriend/{myid}/{friendid}",method=RequestMethod.GET)
     public User unfriend(@PathVariable("friendid") int friendid,@PathVariable("myid") int myid)
     {
     Friend fr=friendDao.getfriendrequest(myid, friendid);
     friendDao.delete(fr);
     return null;
     }


     @RequestMapping(value="/acceptfriend/{myid}/{friendid}",method=RequestMethod.GET)
     public User acceptfriend(@PathVariable("friendid") int friendid,@PathVariable("myid") int myid)
     {
     Friend fr=friendDao.getfriendrequest(myid, friendid);
     fr.setStatus("YES");
     friendDao.acceptfriendrequest(fr);
     return null;
     }




}