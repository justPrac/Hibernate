package com.java.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.criterion.Restrictions;

import com.java.model.User;

public class UserDao
{
	private SessionFactory sessionFactory;
	
	public UserDao()
	{
		StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
				.configure() // configures settings from hibernate.cfg.xml
				.build();
		
		try 
		{
			sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		}
		catch (Exception e) 
		{
			// The registry would be destroyed by the SessionFactory, 
			// but we had trouble building the SessionFactory
			// so destroy it manually.
			StandardServiceRegistryBuilder.destroy( registry );
		}
	}

	public User getUserByUsername(String username)
	{		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		@SuppressWarnings("deprecation")
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Restrictions.ilike("username", username));
		@SuppressWarnings("unchecked")
		List<User> user = criteria.list();
		session.close();
		if(user != null && user.size() > 0)
		{
			return user.get(0);
		}
		return null;		
	}

	public User getUserById(int id)
	{
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		User user = session.get(User.class, id);
		session.close();
		
		return user;		
	}
	
	public boolean saveUser(User user)
	{
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		User temp = session.get(User.class, user.getId());
		
		if(temp == null)
		{
			session.save(user);
			session.getTransaction().commit();
			session.close();
			return true;
		}
		
		session.close();
		return false;
	}

	public boolean deleteUser(int id)
	{
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		User user = session.get(User.class, id);
		
		if(user != null)
		{
			session.remove(user);
			session.getTransaction().commit();
			session.close();
			return true;
		}
		
		session.close();
		return false;
	}

	public boolean updateUser(User user, int id)
	{
		if(user.getId() != id) return false;
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		if(session.get(User.class, id) != null)
		{
			session.save(user);
			session.getTransaction().commit();
			session.close();
			return true;
		}
		session.close();
		return false;
	}
	
	public boolean addUser(User user)
	{
		if(this.getUserById(user.getId()) == null &&
				this.getUserByUsername(user.getUsername()) == null)
		{
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(user);
			session.getTransaction().commit();
			session.close();
			return true;
		}
		return false;
	}

}
