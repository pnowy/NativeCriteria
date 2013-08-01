package com.nc.core.hibernate;

import com.nc.core.NativeQuery;
import com.nc.core.QueryInfo;
import org.hibernate.SQLQuery;

import java.util.Collection;
import java.util.List;

/**
 *
 */
public class HibernateNativeQuery implements NativeQuery
{
	private SQLQuery query;
	private String buildSql;

	public HibernateNativeQuery(String sql, SQLQuery sqlQuery)
	{
		this.buildSql = sql;
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
	public NativeQuery setMaxResults(int maxResults)
	{
		query.setMaxResults(maxResults);
		return this;
	}

	@Override
	public NativeQuery setFirstResult(int firstResult)
	{
		query.setFirstResult(firstResult);
		return this;
	}

	@Override
	public NativeQuery setParameter(String name, Object val)
	{

		query.setParameter(name, val);
		return this;
	}

	@Override
	public NativeQuery setString(String name, String value)
	{
		query.setString(name, value);
		return this;
	}

	@Override
	public NativeQuery setParameterList(String name, Collection values)
	{
		query.setParameterList(name, values);
		return this;
	}

	@Override
	public NativeQuery setParameterList(String name, Object[] values)
	{
		query.setParameterList(name, values);
		return this;
	}

	@Override
	public QueryInfo getQueryInfo()
	{
		return null;
	}
}
