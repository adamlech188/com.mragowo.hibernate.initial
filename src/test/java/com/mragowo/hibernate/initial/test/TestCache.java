package com.mragowo.hibernate.initial.test;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mragowo.hibernate.initial.HibernateUtil;
import com.mragowo.hibernate.initial.entities.Person;

import net.sf.ehcache.CacheManager;

public class TestCache {

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
	public void testCache() {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Query<Person> query = session.createQuery("from Person where lastname like 'Le%'", Person.class)
				.setCacheable(true);
		List<Person> persons = query.list();

		persons.forEach(p -> {
			log.info(String.format("Person first name is: %s", p.getFirstname()));
		});
		
		Query<Person> query2 = session.createQuery("from Person where lastname like 'Lec%'", Person.class)
				.setCacheable(true);
		List<Person> persons2 = query2.list();

		persons2.forEach(p -> {
			log.info(String.format("Person first name is: %s", p.getFirstname()));
		});
		session.close();
	}
}
