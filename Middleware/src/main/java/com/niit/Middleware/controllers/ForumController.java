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

import com.niit.Dao.ForumDao;
import com.niit.Dao.UserDao;
import com.niit.Model.Forum;
import com.niit.Model.ForumComment;
import com.niit.Model.ForumRequest;
import com.niit.Model.User;

@RestController
@RequestMapping("/forums")
public class ForumController {
	@Autowired 
	ForumDao forumDao;
	@Autowired 
	UserDao userDao;
	
	
	@RequestMapping(value="/getAllForums",method=RequestMethod.GET)
	public  ArrayList<Forum> getAllForums(){
		
		ArrayList<Forum> forum=forumDao.getAllForum();
		
		return  forum;
				
	}
	
	
	@RequestMapping(value="/addForum",method=RequestMethod.POST)
	public ResponseEntity<Forum> addForum(@RequestBody Forum forum){
		
		
		boolean isSaved=forumDao.addForum(forum);
		if(isSaved)
		return new ResponseEntity<Forum>(forum,HttpStatus.OK);
		else
			return new ResponseEntity<Forum>(forum,HttpStatus.BAD_REQUEST);
		
	}
	
	
	@RequestMapping(value="/getForumById/{forumid}",method=RequestMethod.GET)
	public ResponseEntity<Forum> getForum(@PathVariable("forumid") int forumId){
	
	
	if(forumDao.getForum(forumId)==null){
		
	}
	return new ResponseEntity<Forum>(forumDao.getForum(forumId),HttpStatus.OK);	
			
	
	
	
	}
	
	
	

	
	
	
	
	@RequestMapping(value="/deleteForum/{forumid}",method=RequestMethod.GET)
	public ResponseEntity<Forum> deleteForum(@PathVariable("forumid") int forumId){
	
	Forum forum=forumDao.getForum(forumId);
	forumDao.deleteForum(forum);
	if(forumDao.getForum(forumId)==null){
		
	}
	return new ResponseEntity<Forum>(forum,HttpStatus.OK);	
			
	
	
	
	}

	@RequestMapping(value="/updateForum/{forumid}/{forumname}/{forumcontent}",method=RequestMethod.POST)
	public ResponseEntity<Forum> updateForum(@PathVariable("forumid") int forumid,@PathVariable("forumname") String forumname,@PathVariable("forumcontent") String forumcontent){
		System.err.println(forumid+"  "+forumname+"  "+forumcontent);
		Forum tempforum=forumDao.getForum(forumid);
		tempforum.setFormname(forumname);
		tempforum.setFormcontent(forumcontent);
		boolean isSaved=forumDao.updateForum(tempforum);
		if(isSaved)
		return new ResponseEntity<Forum>(tempforum,HttpStatus.OK);
		else
			return new ResponseEntity<Forum>(tempforum,HttpStatus.BAD_REQUEST);
		
	}
	
	

	

	
	@RequestMapping(value="/addForumComments/{forumid}/{username}/{forumcomm}",method=RequestMethod.GET)
	public ResponseEntity<ForumComment> addForumcomments(@PathVariable("forumid") int forumid,@PathVariable("username") String username,@PathVariable("forumcomm") String forumcomm){

		
		ForumComment forumcomment=new ForumComment();
		forumcomment.setForumcomm(forumcomm);
		forumcomment.setForumid(forumid);
		forumcomment.setUsername(username);

		boolean isSaved=forumDao.addForumComment(forumcomment);
		if(isSaved)
		return new ResponseEntity<ForumComment>(forumcomment,HttpStatus.OK);
		else
			return new ResponseEntity<ForumComment>(forumcomment,HttpStatus.BAD_REQUEST);
		
	}
	
	
	
	@RequestMapping(value="/updateForumComments",method=RequestMethod.PUT)
	public ResponseEntity<ForumComment> updateBlogComments(@RequestBody ForumComment forumcomment){
		
		boolean isSaved=forumDao.updateForumComment(forumcomment);
		if(isSaved)
		return new ResponseEntity<ForumComment>(forumcomment,HttpStatus.OK);
		else
			return new ResponseEntity<ForumComment>(forumcomment,HttpStatus.BAD_REQUEST);
		
	}
	
	
	@RequestMapping(value="/deleteForumComment/{forumcommentid}",method=RequestMethod.DELETE)
	public ResponseEntity<ForumComment> deleteForumComment(@PathVariable("forumcommentid") int forumcommentId){
	
	ForumComment forumComments=forumDao.getForumComment(forumcommentId);
forumDao.deleteForumComment(forumComments);
	if(forumDao.getForumComment(forumcommentId)==null)
	{
		
	}
	return new ResponseEntity<ForumComment>(forumDao.getForumComment(forumcommentId),HttpStatus.OK);	
			
	}
	
	
	@RequestMapping(value="/getForumComment/{forumcommentId}",method=RequestMethod.GET)
	public ResponseEntity<ForumComment> getforumComment(@PathVariable("forumcomentid") int forumcommentId){
	
	
	if(forumDao.getForumComment(forumcommentId)==null){
		
	}
	return new ResponseEntity<ForumComment>(forumDao.getForumComment(forumcommentId),HttpStatus.OK);	
			
	
	
	
	}
	
	@RequestMapping(value="/getAllForumComments/{forumId}",method=RequestMethod.GET)
	public ArrayList<ForumComment> getAllForumComment(@PathVariable("forumId") int forumId){
		System.err.println(forumId);
	
	ArrayList<ForumComment> forumcomments=forumDao.getAllForumCommentsById(forumId);
			if(forumcomments.isEmpty()){
				return null;
			}
			else
			{
			return forumcomments;
					
			}
	
	}
	
	@RequestMapping(value="/myforums/{myid}",method=RequestMethod.GET)
	public ArrayList<Forum> getmyforums(@PathVariable("myid") int myid)
	{ArrayList<Forum> myforums=new ArrayList<Forum>();  
		ArrayList<ForumRequest> freq=forumDao.getAllMyForum(myid);
		for(ForumRequest f:freq)
		{
			
			Forum fo=forumDao.getForum(f.getForumid());
			myforums.add(fo);
		}
		return myforums;
	}
	
	
	
	@RequestMapping(value="/checkIfMyForum/{forumid}/{myid}",method=RequestMethod.GET)
	public ResponseEntity<ArrayList<ForumRequest>> getcheckifmyforum(@PathVariable("forumid") int forumId,@PathVariable("myid") int myid){
		
		System.err.println(forumId+"  "+myid);
		ForumRequest f=new ForumRequest();
	ArrayList<ForumRequest> foru=forumDao.checkIfMyForum(forumId, myid);
	return new ResponseEntity<ArrayList<ForumRequest>>(foru,HttpStatus.OK);	
			
	
	
	
	}
	
	
	@RequestMapping(value="/addForumReq/{forumid}/{myid}",method=RequestMethod.GET)
	public ResponseEntity<ForumRequest> addForumReq(@PathVariable("forumid") int forumId,@PathVariable("myid") int myid){
	
	User u=userDao.getUser(myid);
	Forum f=forumDao.getForum(forumId);
	ForumRequest fr=new ForumRequest();
	fr.setForumid(forumId);
	fr.setUserid(myid);
	fr.setStatus("A");
	fr.setUsername(u.getEmail());
	fr.setForumname(f.getFormname());
	
	
	
		boolean isSaved=forumDao.addForumRequest(fr);
		if(isSaved)
		{
			return new ResponseEntity<ForumRequest>(fr,HttpStatus.OK);	
		}
		else
		{
			return new ResponseEntity<ForumRequest>(fr,HttpStatus.BAD_REQUEST);
		}
				
	
	
	
	}
	
	
	@RequestMapping(value="/getForumRequests",method=RequestMethod.GET)
	public ResponseEntity<ArrayList<ForumRequest>> getForumrequests()
    {
		ArrayList<ForumRequest> ff=forumDao.getAllForumRequest();
		for(ForumRequest fff:ff)
		{
			System.err.println(fff.getForumname());
			System.err.println(fff.getUsername());
			
		}
	return new ResponseEntity<ArrayList<ForumRequest>>(forumDao.getAllForumRequest(),HttpStatus.OK);		
	}
	
	
	@RequestMapping(value="/approveForumRequests/{forumReqId}",method=RequestMethod.GET)
	public void approveForumRequets(@PathVariable("forumReqId") int forumreqid)
	{
		ForumRequest fr=forumDao.getForumRequest(forumreqid);
		fr.setStatus("YES");
boolean IsSaved=forumDao.acceptForumRequest(fr);
	}
	
	@RequestMapping(value="/forumreqbyforumid/{forumid}",method=RequestMethod.GET)
	public ArrayList<User> getforumusers(@PathVariable("forumid") int forumid)
	{ArrayList<User> users=new ArrayList<User>(); 
		ArrayList<ForumRequest> f=forumDao.forreqbyforid(forumid);
		for(ForumRequest ff:f)
		{
			User u=userDao.getUser(ff.getUserid());
			users.add(u);
		}
		return users;
	}


}
