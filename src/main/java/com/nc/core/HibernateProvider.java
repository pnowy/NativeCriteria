package com.nc.core;

import org.hibernate.Session;

/**
 * Przemek Nowak <przemek.nowak.pl@gmail.com>
 * Date: 29.07.13 23:58
 */
public class HibernateProvider implements NativeProvider
{
	private Session session;

	public HibernateProvider(Session session)
	{
		this.session = session;
	}

	@Override
	public QueryProvider getQueryProvider(String sql)
	{
		return new HibernateQueryProvider(session.createSQLQuery(sql));
	}
}
