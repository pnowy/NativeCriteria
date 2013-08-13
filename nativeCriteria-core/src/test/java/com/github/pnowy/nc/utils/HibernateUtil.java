package com.github.pnowy.nc.utils;

import com.github.pnowy.nc.core.hibernate.HibernateQueryProvider;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.io.InputStreamReader;

/**
 * Hibernate test utils to test NativeCriteria in real database (somethig like unit/integration tests).
 *
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
		init();
	}

	public static SessionFactory getSessionFactory()
	{
		return sessionFactory;
	}

	/**
	 * Initialize test database when session factory is prepared.
	 */
	private static void init()
	{
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();

		ScriptRunner scriptRunner = new ScriptRunner(s);
		scriptRunner.runScript(new InputStreamReader(HibernateUtil.class.getResourceAsStream("/sql/1.sql")));

		s.getTransaction().commit();
		s.close();
	}

	/**
	 * Method which execute transaction.
	 *
	 * @param transaction transaction to execute, see {@link Transactional}
	 */
	public static void executeTransaction(Transactional transaction)
	{
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		transaction.transaction(new HibernateQueryProvider(s));
		s.getTransaction().commit();
		s.close();
	}

}