package org.upadhyay.suraj;

import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
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
		
		
		
		firstLevelCache(sessionFactory);		
		
		sessionFactory.close();
	}
	
	
	/**
	 * 
	 * @param sessionFactory SessionFactory object to use Session objects
	 */
	public static void firstLevelCache(SessionFactory sessionFactory)
	{
		
		//************************OBJECT CREATION START****************************
		/*
		Vehicle v1 = new Vehicle("Audi");
		Vehicle v2 = new Vehicle("Tesla");
		Vehicle v3 = new Vehicle("BMW");
		Vehicle v4 = new Vehicle("Toyota");
		Vehicle v5 = new Vehicle("RR");
		Vehicle v6 = new Vehicle("Aston Martin");
		*/

		//************************OBJECT CREATION END****************************
		
		
		//Opening a new session using SessionFactory object --> openSession();
		Session session = sessionFactory.openSession();
		
		//starting a transaction --> it is unit work --> 
		//can have save(), upadate(), delete()..in 1 transaction (1 unit of work)
		session.beginTransaction();
		
		//************************TRANSACTION START****************************
		/*
		session.save(v1);
		session.save(v2);
		session.save(v3);
		session.save(v4);
		session.save(v5);
		session.save(v6);	
		*/
		
		Vehicle vX = (Vehicle)session.get(Vehicle.class, 3);
		System.out.println("Vehicle vX retrieved: " + vX.getName());
		
		vX.setName("BMW2");
		
		Vehicle vY = (Vehicle)session.get(Vehicle.class, 3);
		System.out.println("Vehicle vY retrieved: " + vY.getName());
		
		//************************TRANSACTION END****************************
		
		//terminating a transaction by commiting --> commit();
		session.getTransaction().commit();
		session.close();
		
		//************************PRINTING AT CONSOLE****************************
		
	}

}
