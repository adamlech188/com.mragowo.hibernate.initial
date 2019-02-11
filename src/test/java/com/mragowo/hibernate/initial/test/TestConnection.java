package com.mragowo.hibernate.initial.test;

import org.apache.log4j.Logger;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mragowo.hibernate.initial.HibernateUtil;

import net.sf.ehcache.CacheManager;


public class TestConnection {

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
	public void test() {
		
		log.info("Testing connection");
	}
}
