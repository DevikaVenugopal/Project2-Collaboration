package com.niit.DaoImpl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class EventDaoImpl 
{
	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	public EventDaoImpl(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}
	
	
	
	

}
