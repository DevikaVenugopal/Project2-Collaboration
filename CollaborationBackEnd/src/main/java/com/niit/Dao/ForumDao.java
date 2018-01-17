package com.niit.Dao;

import java.util.ArrayList;

import com.niit.Model.Forum;
import com.niit.Model.ForumComment;
import com.niit.Model.ForumRequest;

public interface ForumDao 
{
	public boolean addForum(Forum forum);
	public boolean updateForum(Forum forum);
	public boolean deleteForum(Forum forum);
	public Forum getForum(int forumId);
	public ArrayList<Forum> getAllForum();
	public ArrayList<ForumRequest> getAllMyForum(int userid);
	
	
	public boolean addForumComment(ForumComment forumcomment);
	public boolean updateForumComment(ForumComment forumcomment);
	public boolean deleteForumComment(ForumComment forumcomment);
	public ForumComment getForumComment(int commentId);
	public ArrayList<ForumComment> getAllForumCommentsById(int forumid);

	
	public boolean addForumRequest(ForumRequest forumrequest);
	public boolean acceptForumRequest(ForumRequest forumrequest);
	public boolean blockUser(ForumRequest forumrequest);
	public ArrayList<ForumRequest> getAllForumRequest();
	public ForumRequest getForumRequest(int ForumReqId);
		
	public ArrayList<ForumRequest> checkIfMyForum(int ForumId, int myid);
	public ArrayList<ForumRequest> forreqbyforid(int forumid);

}
