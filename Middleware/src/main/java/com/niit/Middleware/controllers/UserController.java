package com.niit.Middleware.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.niit.Dao.UserDao;
import com.niit.Model.User;

@RequestMapping("/user")
@RestController
public class UserController 
{
	@Autowired
	UserDao userDao;
	
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
	
	@RequestMapping(value="/getAllUsers",method=RequestMethod.GET)
	public ArrayList<User> getAllUser()
	{
		System.out.println("in rest controller getallusers");
		ArrayList<User> user=(ArrayList<User>)userDao.getAllUser();
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
	public ResponseEntity<User> login(@RequestBody User user){

		if(userDao.checkLogin(user))
		{
			 User tempuser=userDao.getUserbyemail(user.getEmail());
			 System.out.println("3..."+tempuser.getEmail());
			 System.out.println("3..."+tempuser.getPassword());
		     tempuser.setIsonline("YES");
			 userDao.updateOnlineStatus(tempuser);
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
	 
     /*@RequestMapping(value="/job",method=RequestMethod.POST)
	 public ResponseEntity<Job> getJob()
     {		
		return new ResponseEntity<Job>(JobDao.getjob(3),HttpStatus.BAD_REQUEST);
			
	 }
	 
	 
	 
	 @RequestMapping(value="/up",method = RequestMethod.POST)
	 public ModelAndView  upload(HttpServletRequest request,@RequestParam("uploadedFile") MultipartFile file,HttpSession session )
	 {
	 	   String filepath = request.getSession().getServletContext().getRealPath("/") + "resources/product/" + file.getOriginalFilename();
	 		
		 
		 User user = (User)session.getAttribute("currentuser");
		 	System.out.println(user.getEmail());
		 		//user.setImage(user.getEmail()+".jpg");
		 	userDao.updateOnlineStatus(user); 
		 	
		 	
	 	    String filepat ="C:/Users/user/eclipse-workspace/CollaborationFrontEnd/WebContent/resources/images/" + user.getEmail()+".jpg";
	 		
	 		
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
	 
	 
	 @RequestMapping(value="/upcover",method = RequestMethod.POST)
	 public ModelAndView uploadcover(HttpServletRequest request,@RequestParam("uploadedFile") MultipartFile file,HttpSession session )
	 {
	 	   String filepath = request.getSession().getServletContext().getRealPath("/") + "resources/product/" + file.getOriginalFilename();
	 		
		 
		 User user = (User)session.getAttribute("currentUser");
		 	
		 		//user.setCover(user.getEmail()+"cover.jpg");
		 	userDao.updateOnlineStatus(user);
	 	    String filepat ="C:/Users/user/eclipse-workspace/SayhiFrontend/WebContent/resources/images/" +user.getEmail()+"cover.jpg";
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
				users.add(userDao.getUserbyId(f.getFRI_ID()));
			}
			else if(f.getFRI_ID()==myid)
			{
				users.add(userDao.getUserbyId(f.getU_ID()));
			} 
			
		}
	 return users;
	
}*/
	 
	 
	/* @RequestMapping(value="/friendsfriends/{userid}/{myid}",method = RequestMethod.GET)
	 public ArrayList<User> friendsfriends(@PathVariable("userid") int userid,@PathVariable("myid") int myid )
	 { 
		 System.out.println(userid+" "+myid);
	 ArrayList<User> fp=new ArrayList<User>(); 
	 ArrayList<Friend> myfriends=(ArrayList<Friend>)FriendDao.getAllMyFriendpend(myid);
	 ArrayList<String> myfriendsemail=new ArrayList<String>();
	 for(Friend s:myfriends)
	 {
	 	if(s.getU_ID()==myid)
	 	{
	 	 myfriendsemail.add(userDao.getUser(s.getFRI_ID()).getEmail());
	 	}
	 	else if(s.getFRI_ID()==myid)
	 	{
	 		System.out.println(userDao.getUser(s.getU_ID()).getEmail());
	 		
	 		myfriendsemail.add(userDao.getUser(s.getU_ID()).getEmail());
	 	}
	 }

	 
	 ArrayList<Friend> hisfriends=(ArrayList<Friend>)FriendDao.getAllMyFriend(userid);
	 ArrayList<String> hisfriendsemail=new ArrayList<String>();
	 for(Friend s:hisfriends)
	 {
	 	if(s.getU_ID()==userid)
	 	{
	 		hisfriendsemail.add(userDao.getUser(s.getFRI_ID()).getEmail());
	 	}
	 	else if(s.getFRI_ID()==userid)
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
	 


}*/
 
}
