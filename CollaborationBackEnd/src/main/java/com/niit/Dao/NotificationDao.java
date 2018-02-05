package com.niit.Dao;

import java.util.ArrayList;

import com.niit.Model.Notification;

public interface NotificationDao 
{
	public boolean addNotifications(Notification notification) ;
	public ArrayList<Notification> getAllNotifications( String username) ;
	public boolean deleteNotifications(Notification notification);
	public Notification getNotifications(int notifid);

}
