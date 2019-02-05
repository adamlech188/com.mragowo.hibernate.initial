package com.mragowo.hibernate.initial;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.mragowo.hibernate.initial.entities.Person;

public class Application {

	public static void main(String[] args) {

		SessionFactory sessionFactry = HibernateUtil.getSessionFactory();
		Session session = sessionFactry.getCurrentSession(); 
		
		session.beginTransaction(); 
		Person person = new Person(); 
		person.setFirstname("Adam");
		person.setLastname("Lech");
		person.setSsn("123456789");
		session.save(person); 
		session.flush(); 
		session.close(); 
		
		
	
	}
}
