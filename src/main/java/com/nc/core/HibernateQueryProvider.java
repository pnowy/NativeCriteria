package com.nc.core;

import org.hibernate.SQLQuery;

import java.util.Collection;
import java.util.List;

/**
 *
 */
public class HibernateQueryProvider implements QueryProvider {

	private SQLQuery query;

	public HibernateQueryProvider(SQLQuery sqlQuery)
	{
		this.query = sqlQuery;
	}

	@Override
	public List list()
	{
		return query.list();
	}

	@Override
	public Object uniqueResult()
	{
		return query.uniqueResult();
	}

	@Override
	public QueryProvider setMaxResults(int maxResults)
	{
		query.setMaxResults(maxResults);
		return this;
	}

	@Override
	public QueryProvider setFirstResult(int firstResult)
	{
		query.setFirstResult(firstResult);
		return this;
	}

	@Override
	public QueryProvider setParameter(String name, Object val)
	{

		query.setParameter(name, val);
		return this;
	}

	@Override
	public QueryProvider setString(String name, String value)
	{
		query.setString(name, value);
		return this;
	}

	@Override
	public QueryProvider setParameterList(String name, Collection values)
	{
		query.setParameterList(name, values);
		return this;
	}

	@Override
	public QueryProvider setParameterList(String name, Object[] values)
	{
		query.setParameterList(name, values);
		return this;
	}
}
