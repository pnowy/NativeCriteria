package com.github.pnowy.nc.utils;

import java.io.InputStreamReader;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.github.pnowy.nc.core.hibernate.HibernateQueryProvider;

/**
 * Hibernate test utils to test NativeCriteria in real database (somethig like unit/integration tests).
 */
public class HibernateUtil {

	private static SessionFactory sessionFactory;

	static {
		try {
			Configuration configuration = new Configuration().configure("/ds/hibernate.cfg.xml");

			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties())
						.build();
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		} catch (HibernateException he) {
			System.err.println("Error creating Session: " + he);
			throw new ExceptionInInitializerError(he);
		}
		init();
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * Initialize test database when session factory is prepared.
	 */
	private static void init() {
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
	public static void executeTransaction(Transactional transaction) {
		Session s = HibernateUtil.getSessionFactory().openSession();
		s.beginTransaction();
		transaction.transaction(new HibernateQueryProvider(s));
		s.getTransaction().commit();
		s.close();
	}

}
