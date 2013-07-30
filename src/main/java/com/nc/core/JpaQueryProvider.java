package com.nc.core;

import javax.persistence.Query;
import java.util.Collection;
import java.util.List;

/**
 * Przemek Nowak <przemek.nowak.pl@gmail.com>
 * Date: 30.07.13 00:12
 */
public class JpaQueryProvider implements QueryProvider {
	private Query query;

	public JpaQueryProvider(Query sqlQuery)
	{
		this.query = sqlQuery;
	}

	@Override
	public List list()
	{
		return query.getResultList();
	}

	@Override
	public Object uniqueResult()
	{
		return query.getSingleResult();
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
		query.setParameter(name, value);
		return this;
	}

	@Override
	public QueryProvider setParameterList(String name, Collection values)
	{
		query.setParameter(name, values);
		return this;
	}

	@Override
	public QueryProvider setParameterList(String name, Object[] values)
	{
		query.setParameter(name, values);
		return this;
	}
}
