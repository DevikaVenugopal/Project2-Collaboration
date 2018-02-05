package com.niit.Config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;

import com.niit.Model.Blog;
import com.niit.Model.BlogComment;
import com.niit.Model.Forum;
import com.niit.Model.ForumComment;
import com.niit.Model.ForumRequest;
import com.niit.Model.Friend;
import com.niit.Model.Job;
import com.niit.Model.JobApplication;
import com.niit.Model.Notification;
import com.niit.Model.User;

@Configuration
@ComponentScan("com.niit")
//@EnableTransactionManagement
public class DBConfig 
{	
	    @Autowired
	    @Bean
	    public SessionFactory sessionFactory(DataSource dataSource)
	    {
	        LocalSessionFactoryBuilder sessionBuilder  = new LocalSessionFactoryBuilder(dataSource);
	        /*sessionBuilder.setProperty("hibernate.show_sql", "true");*/
	        
	        sessionBuilder.addProperties(getHibernateProperties());
	        
	        sessionBuilder.addAnnotatedClass(User.class);
	        sessionBuilder.addAnnotatedClass(Job.class);
	        sessionBuilder.addAnnotatedClass(Blog.class);
	        sessionBuilder.addAnnotatedClass(Forum.class);
	        sessionBuilder.addAnnotatedClass(Friend.class);
	        sessionBuilder.addAnnotatedClass(JobApplication.class);
	        sessionBuilder.addAnnotatedClass(BlogComment.class);
	        sessionBuilder.addAnnotatedClass(Notification.class);
	        sessionBuilder.addAnnotatedClass(ForumRequest.class);
	        sessionBuilder.addAnnotatedClass(ForumComment.class);
	        return sessionBuilder.buildSessionFactory();
	    }
	    @Autowired
	    @Bean(name = "datasource")
	    public DataSource dataSource() {
	        DriverManagerDataSource dataSource = new DriverManagerDataSource();
	        dataSource.setDriverClassName("org.h2.Driver");
	        dataSource.setUrl("jdbc:h2:tcp://localhost/~/collab");
	        dataSource.setUsername("sa");
	        dataSource.setPassword("");
	        return dataSource;
	    }

	    private Properties getHibernateProperties() {
	        Properties properties = new Properties();
	        properties.put("hibernate.show_sql", "true");
	        properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
	        properties.put("hibernate.format_sql", "true");
	        properties.put("hibernate.hbm2ddl.auto", "update");
	       // properties.put("hibernate.connection.autocommit", true);
	        return properties;
	    }
	    @Bean
		@Autowired
	        public HibernateTransactionManager txManager(SessionFactory sessionFactory) {
	                return new HibernateTransactionManager(sessionFactory);
	        }
}

