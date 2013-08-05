package com.github.pnowy.nc.core.hibernate;

import com.github.pnowy.nc.core.NativeQuery;
import com.github.pnowy.nc.core.QueryInfo;
import org.hibernate.SQLQuery;

import java.util.Collection;
import java.util.List;

/**
 *
 */
public class HibernateNativeQuery implements NativeQuery
{
	private SQLQuery query;
	private QueryInfo queryInfo;

	public HibernateNativeQuery(String sql, SQLQuery sqlQuery)
	{
		this.queryInfo = new QueryInfo(sql);
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
		queryInfo.getParameters().put(name, val);
		query.setParameter(name, val);
		return this;
	}

	@Override
	public NativeQuery setString(String name, String value)
	{
		queryInfo.getParameters().put(name, value);
		query.setString(name, value);
		return this;
	}

	@Override
	public NativeQuery setParameterList(String name, Collection values)
	{
		queryInfo.getParameters().put(name, values);
		query.setParameterList(name, values);
		return this;
	}

	@Override
	public NativeQuery setParameterList(String name, Object[] values)
	{
		queryInfo.getParameters().put(name, values);
		query.setParameterList(name, values);
		return this;
	}

	@Override
	public QueryInfo getQueryInfo()
	{
		return queryInfo;
	}
}
