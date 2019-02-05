package com.mragowo.hibernate.initial;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.mragowo.hibernate.initial.entities.Person;


public class HibernateUtil {

	 private static SessionFactory sessionFactory = buildSessionFactory();
	    private static ServiceRegistry serviceRegistry;
	    
	    private static SessionFactory buildSessionFactory() {
	        try {
	            // Create the SessionFactory from hibernate.cfg.xml in resources directory
	        	Configuration configuration = new Configuration().configure()
	            	.addAnnotatedClass(Person.class);
	        	StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().
	        	applySettings(configuration.getProperties());
	        	serviceRegistry = builder.build();
	        	return configuration.buildSessionFactory(serviceRegistry);
	        }
	        catch (Throwable ex) {
	            // Make sure you log the exception, as it might be swallowed
	            System.err.println("Initial SessionFactory creation failed." + ex);
	            throw new ExceptionInInitializerError(ex);
	        }
	    }

	    public static SessionFactory getSessionFactory() {
	        return sessionFactory;
	    }

	    public static void stopService() {
	    	StandardServiceRegistryBuilder.destroy(serviceRegistry);
	    }
}
