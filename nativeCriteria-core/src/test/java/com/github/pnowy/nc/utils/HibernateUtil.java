package com.github.pnowy.nc.utils;

import com.github.pnowy.nc.core.hibernate.HibernateQueryProvider;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * Date: 31.07.13 16:35
 */
public class HibernateUtil
{
	private static SessionFactory sessionFactory;

	static
	{
		try
		{
			Configuration configuration = new Configuration().configure("/ds/hibernate.cfg.xml");

			ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		}
		catch (HibernateException he)
		{
			System.err.println("Error creating Session: " + he);
			throw new ExceptionInInitializerError(he);
		}
	}

	public static SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}

	public static void executeTransaction(Transactional transaction)
	{
		Session s = HibernateUtil.getSessionFactory().getCurrentSession();
		s.beginTransaction();
		transaction.transaction(new HibernateQueryProvider(s));
		s.getTransaction().commit();
		s.close();
	}

}