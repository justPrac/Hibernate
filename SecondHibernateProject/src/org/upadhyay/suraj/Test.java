package org.upadhyay.suraj;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.upadhyay.suraj.dto.User;
import org.upadhyay.suraj.dto.Vehicle;

public class Test
{

	public static void main(String[] args)
	{
		SessionFactory sessionFactory = null;
		
		StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
				.configure() // configures settings from hibernate.cfg.xml
				.build();
		
		try 
		{
			sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
		}
		catch (Exception e) 
		{
			// The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
			// so destroy it manually.
			StandardServiceRegistryBuilder.destroy( registry );
		}
		
		//************************OBJECT CREATION START****************************
		
		Vehicle v1 = new Vehicle("Audi");
		Vehicle v2 = new Vehicle("Tesla");
		Vehicle v3 = new Vehicle("BMW");
		
		User u1 = new User();
		u1.getV().add(v1);
		u1.getV().add(v2);
		

		//************************OBJECT CREATION END****************************
		
		
		//Opening a new session using SessionFactory object --> openSession();
		Session session = sessionFactory.openSession();
		
		//starting a transaction --> it is unit work --> 
		//can have save(), upadate(), delete()..in 1 transaction (1 unit of work)
		session.beginTransaction();
		
		//************************TRANSACTION START****************************
		
		//session.save(v3);
		//session.save(v2);
		//session.save(v1);
		//session.persist(u1);
		
		
		User u2 = (User)session.get(User.class, 1);
		u2.getV().add(v3);
		
		//************************TRANSACTION END****************************
		
		//terminating a transaction by commiting --> commit();
		session.getTransaction().commit();
		session.close();
		
		System.out.println("User has " + u2.getV().size() + " vehicle(s).");
		

	}

}
