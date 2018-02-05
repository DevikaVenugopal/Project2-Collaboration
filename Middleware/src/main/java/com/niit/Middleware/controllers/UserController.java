package com.niit.Middleware.controllers;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.niit.Dao.UserDao;
import com.niit.Dao.JobDao;
import com.niit.Dao.FriendDao;
import com.niit.Model.Friend;
import com.niit.Model.Job;
import com.niit.Model.User;

@RequestMapping("/user")
@RestController
public class UserController 
{
	@Autowired
	UserDao userDao;
	
	@Autowired
	JobDao jobDao;
	
	@Autowired
	FriendDao friendDao;
	
	@RequestMapping(value="/registerUser",method=RequestMethod.POST)
	public ResponseEntity<User> createUser(@RequestBody User user){
		System.out.println("In register controller");
		boolean isSaved=userDao.saveUser(user);
		if(isSaved)
		{
		return new ResponseEntity<User>(user,HttpStatus.OK);
		}
		else
			return new ResponseEntity<User>(user,HttpStatus.BAD_REQUEST);
		
	}
	
	@RequestMapping(value="/getAllUsers/{userid}",method=RequestMethod.GET)
	public ArrayList<User> getAllUser(@PathVariable("userid") int userid)
	{
		System.out.println("in rest controller getallusers");
		ArrayList<User> user=(ArrayList<User>)userDao.requestFriend(userid);
		System.out.println("in rest controller getallusers");
		return user;
		
	}
	
	@RequestMapping(value="/getUser/{userid}",method=RequestMethod.GET)
	public ResponseEntity<User> getUser(@PathVariable("userid") int userId)
	{
         if(userDao.getUser(userId)==null)
         {
			
		 }

		return new ResponseEntity<User>(userDao.getUser(userId),HttpStatus.OK);
				
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ResponseEntity<User> login(@RequestBody User user,HttpSession http){

		if(userDao.checkLogin(user))
		{
			 User tempuser=userDao.getUserbyemail(user.getEmail());
			 System.out.println("3..."+tempuser.getEmail());
			 System.out.println("3..."+tempuser.getPassword());
		     tempuser.setIsonline("YES");
			 userDao.updateOnlineStatus(tempuser);
			 http.setAttribute("currentuser",tempuser);
			 return new ResponseEntity<User>(tempuser,HttpStatus.OK);	
		}
		else
		{
			 return new ResponseEntity<User>(user,HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@RequestMapping(value="/logout/{email}",method=RequestMethod.GET)
	public ResponseEntity<String> logout(@PathVariable("email") String email)
	{
		System.out.println(email);
	    String emaill=email+".com";

        System.out.println(emaill);
        User tempuser=userDao.getUserbyemail(emaill);
	    tempuser.setIsonline("NO");
	    userDao.updateOnlineStatus(tempuser);
	    return new ResponseEntity<String>("Lgout success",HttpStatus.OK);		 
}
	
	 @RequestMapping(value="/getAllUsersreq",method=RequestMethod.GET)
		public ArrayList<User> getAllUserreq()
	 {
		 System.out.println("in rest controller getallusersreq");
	     ArrayList<User> userreq=(ArrayList<User>)userDao.userrequests();
	     System.out.println("in rest controller getallusersreq");

		 return userreq;		
	 } 
	 
	 @RequestMapping(value="/approveusers/{username}",method=RequestMethod.GET)
	 public void approveusers(@PathVariable("username") String username)
	 {
		 String email=username+".com";
		 User user =userDao.getUserbyemail(email);
		 user.setStatus("A");
		 userDao.approveusers(user);
	 
	 }
	 
	 @RequestMapping(value="/rejectusers/{username}",method=RequestMethod.GET)
	 public void rejectusers(@PathVariable("username") String username)
	 {
		 String email=username+".com";
		 User user =userDao.getUserbyemail(email);
		 user.setStatus("R");
		 userDao.rejectusers(user);
		 
	 }
	 
     @RequestMapping(value="/job",method=RequestMethod.POST)
	 public ResponseEntity<Job> getJob()
     {		
		return new ResponseEntity<Job>(jobDao.getjob(98),HttpStatus.BAD_REQUEST);
			
	 }
	 
	 
	 
	 @RequestMapping(value="/up",method = RequestMethod.POST)
	 public ModelAndView  upload(HttpServletRequest request,@RequestParam("uploadedFile") MultipartFile file,HttpSession session )
	 {
	 	   /*String filepath = request.getSession().getServletContext().getRealPath("/") + "resources/product/" + file.getOriginalFilename();
	 		*/
		 
		 User user = (User)session.getAttribute("currentuser");
		 	System.out.println(user.getUsername());
		 		user.setImage(user.getUsername()+".jpg");
		 	userDao.updateOnlineStatus(user); 
		 	
		 	
	 	    String filepath ="C:/devika/project2-final/project2(Re)/CollaborationFrontEnd/WebContent/Images/" + user.getUsername()+".jpg";
	 		
	 		
	 		System.out.println(filepath);
	 		try {
	 			byte imagebyte[] = file.getBytes();
	 			BufferedOutputStream fos = new BufferedOutputStream(new FileOutputStream(filepath));
	 			fos.write(imagebyte);
	 			fos.close();
	 			} catch (IOException e) {
	 			e.printStackTrace();
	 			} catch (Exception e) {
	 		
	 			e.printStackTrace();
	 			}
	 		
	 	
	 	ModelAndView mv = new ModelAndView("/Backhome");
		return mv;
	 }
	 
	 
	 @RequestMapping(value="/upcover",method = RequestMethod.POST)
	 public ModelAndView uploadcover(HttpServletRequest request,@RequestParam("uploadedFile") MultipartFile file,HttpSession session )
	 {
	 	  /* String filepath = request.getSession().getServletContext().getRealPath("/") + "resources/product/" + file.getOriginalFilename();
	 		*/
		 
		 User user = (User)session.getAttribute("currentuser");
		 	
		 		//user.setCover(user.getEmail()+"cover.jpg");
		 	userDao.updateOnlineStatus(user);
	 	    String filepath ="" +user.getUsername()+"cover.jpg";
	 		String img=file.getOriginalFilename();
	 		System.out.println(img);
	 		System.out.println(filepath);
	 		try {
	 			byte imagebyte[] = file.getBytes();
	 			BufferedOutputStream fos = new BufferedOutputStream(new FileOutputStream(filepath));
	 			fos.write(imagebyte);
	 			fos.close();
	 			} catch (IOException e) {
	 			e.printStackTrace();
	 			} catch (Exception e) {
	 			
	 			e.printStackTrace();
	 			}
	 		
	 	
	 	ModelAndView mv = new ModelAndView("backhome");
		return mv;
	 }
	 
	 
	 @RequestMapping(value="/ismyfriend/{userid}/{myid}",method = RequestMethod.GET)
	 public ArrayList<User> ismyfriend(@PathVariable("userid") int userid,@PathVariable("myid") int myid)
	 {
		 System.out.println("in is my friend controller");
		 ArrayList<Friend> friends = (ArrayList<Friend>)userDao.checkismyfriend(userid, myid);
		ArrayList<User> users= new  ArrayList<User>();
		for(Friend f:friends)
		{
			if(f.getU_ID()==myid)
			{
				users.add(userDao.getUserbyId(f.getFriendid()));
			}
			else if(f.getFriendid()==myid)
			{
				users.add(userDao.getUserbyId(f.getU_ID()));
			} 
			
		}
	 return users;
	
}
	 
	 
	 @RequestMapping(value="/friendsfriends/{userid}/{myid}",method = RequestMethod.GET)
	 public ArrayList<User> friendsfriends(@PathVariable("userid") int userid,@PathVariable("myid") int myid )
	 { 
		 System.out.println(userid+" "+myid);
	 ArrayList<User> fp=new ArrayList<User>(); 
	 ArrayList<Friend> myfriends=(ArrayList<Friend>)friendDao.getAllMyFriendpend(myid);
	 ArrayList<String> myfriendsemail=new ArrayList<String>();
	 for(Friend s:myfriends)
	 {
	 	if(s.getU_ID()==myid)
	 	{
	 	 myfriendsemail.add(userDao.getUser(s.getFriendid()).getEmail());
	 	}
	 	else if(s.getFriendid()==myid)
	 	{
	 		System.out.println(userDao.getUser(s.getU_ID()).getEmail());
	 		
	 		myfriendsemail.add(userDao.getUser(s.getU_ID()).getEmail());
	 	}
	 }

	 
	 ArrayList<Friend> hisfriends=(ArrayList<Friend>)friendDao.getAllMyFriend(userid);
	 ArrayList<String> hisfriendsemail=new ArrayList<String>();
	 for(Friend s:hisfriends)
	 {
	 	if(s.getU_ID()==userid)
	 	{
	 		hisfriendsemail.add(userDao.getUser(s.getFriendid()).getEmail());
	 	}
	 	else if(s.getFriendid()==userid)
	 	{
	 		hisfriendsemail.add(userDao.getUser(s.getU_ID()).getEmail());
	 	}
	 }

	 for(String hs:hisfriendsemail)
	 {
		 User u=userDao.getUserbyId(myid);
		if(hs==u.getEmail())
		{
			
		}
		else
		{
		 int count=0;
		 
		 for(String mf:myfriendsemail)
		 {
			 
			 if(mf!=hs)
			 {
				 count++;
			 }
			 
		 }
		 
		 if(count==myfriendsemail.size())
		 {
			 User us=userDao.getUserbyemail(hs);
			 fp.add(us);
					 
		 }
		}
		 
		 
	 }
	
	 return fp;
	 


}
 
}
