package com.mragowo.hibernate.initial.test;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mragowo.hibernate.initial.HibernateUtil;
import com.mragowo.hibernate.initial.entities.Person;

import net.sf.ehcache.CacheManager;

public class TestIsolationLevel {

	Logger log = Logger.getLogger(this.getClass().getName());

	SessionFactory sessionFactory;
	CacheManager cacheManager;

	@Before
	public void setUp() {
		cacheManager = CacheManager.newInstance();
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	@After
	public void tearDown() {
		sessionFactory.close();
		cacheManager.shutdown();
	}

	@Test
	public void testIsolation() {

		Session session = sessionFactory.openSession();
		session.doWork(c -> {
			c.setTransactionIsolation(8);
			
		});
		
		Transaction transaction = session.beginTransaction(); 
		try {
			Person person = new Person(); 
			person.setFirstname("Beata");
			person.setLastname("Ferrowoskiy");
			person.setSsn("1234567");
			session.save(person); 
			transaction.commit(); 
			
		
		}
		catch(Exception e) {
			transaction.rollback(); 
			log.error("Exception occured" , e);
			assert(false); 
		}
		finally {
			if(session.isOpen()) {
				session.doWork( c -> {
					c.setTransactionIsolation(2);
				});
				session.close(); 
			}
		}
	}

}
