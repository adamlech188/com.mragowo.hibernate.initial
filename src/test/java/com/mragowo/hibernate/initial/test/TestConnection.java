package com.mragowo.hibernate.initial.test;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mragowo.hibernate.initial.HibernateUtil;

public class TestConnection {

	Logger log = Logger.getLogger(this.getClass().getName());
	
	SessionFactory sessionFactory; 
	
	@Before
	public void setUp() {
		sessionFactory = HibernateUtil.getSessionFactory(); 
	}
	
	@After
	public void tearDown() {
		sessionFactory.close(); 
	}
	@Test
	public void test() {
		
		log.info("Testing connection");
	}
}
