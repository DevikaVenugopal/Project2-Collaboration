package com.niit.Dao;

import java.util.ArrayList;

import com.niit.Model.Blog;
import com.niit.Model.BlogComment;

public interface BlogDao 
{
	public boolean addBlog(Blog blog);
	public boolean updateBlog(Blog blog);
	public boolean deleteBlog(Blog blog);
	public Blog getBlog(int blogId);
	public ArrayList<Blog> getAllBlogs();
	public boolean approveBlog(Blog blog);
	public boolean rejectBlog(Blog blog);
	public boolean addBlogComment(BlogComment blogcomment);
	public boolean deleteBlogComment(BlogComment blogcomment);
	public boolean updateBlogComment(BlogComment blogcomment);
	public BlogComment getBlogComment(int commentId);
	public ArrayList<BlogComment> getAllBlogComments(int blogid);
	public boolean like(int blogid);
	public boolean dislike(int blogid);
	public boolean incview(int blogid);
	public ArrayList<Blog> getAllBlogRequests();
	public ArrayList<Blog> getAllMyBlogs(String email);

}
