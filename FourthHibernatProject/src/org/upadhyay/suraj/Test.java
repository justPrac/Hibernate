package org.upadhyay.suraj;

import java.util.List;

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
		
		//************************OBJECT CREATION START****************************
		
		Vehicle v1 = new Vehicle("Audi");
		Vehicle v2 = new Vehicle("Tesla");
		Vehicle v3 = new Vehicle("BMW");
		Vehicle v4 = new Vehicle("Toyota");
		Vehicle v5 = new Vehicle("RR");
		Vehicle v6 = new Vehicle("Aston Martin");
		

		//************************OBJECT CREATION END****************************
		
		
		//Opening a new session using SessionFactory object --> openSession();
		Session session = sessionFactory.openSession();
		
		//starting a transaction --> it is unit work --> 
		//can have save(), upadate(), delete()..in 1 transaction (1 unit of work)
		session.beginTransaction();
		
		//************************TRANSACTION START****************************
		//session.save(v1);
		//session.save(v2);
		//session.save(v3);
		//session.save(v4);
		//session.save(v5);
		//session.save(v6);	
		
		
		int id = 4;
		
		
		Query<Integer> query1 = session.createQuery("select count (id) from Vehicle where id > :id");
		query1.setParameter("id", id);
		List<Integer> result1 = query1.list();
		
		Query<Vehicle> query2 = session.getNamedQuery("Vehicle.getById");
		query2.setParameter("id", id);
		List<Vehicle> result2 = query2.list();		
		
		
		Query<Vehicle> query3 = session.getNamedNativeQuery("Vehicle.getName");
		query3.setParameter("id", id+1);
		List<Vehicle> result3 = query3.list();
		
		
		//************************TRANSACTION END****************************
		
		//terminating a transaction by commiting --> commit();
		session.getTransaction().commit();
		session.close();
		
		//************************PRINTING AT CONSOLE****************************
		
		System.out.println("query1 received: " + result1.get(0));
		System.out.println("Vehicle with id: " + id + " is: " + result2.get(0).getName());
		System.out.println("Vehicle with id: " + (id+1) + " is: " + result3.get(0));

	}

}
