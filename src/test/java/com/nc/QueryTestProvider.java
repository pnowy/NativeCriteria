package com.nc;

import com.beust.jcommander.internal.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.ObjectUtils;
import com.nc.core.QueryProvider;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Przemek Nowak <przemek.nowak.pl@gmail.com>
 * Date: 30.07.13 17:40
 */
public class QueryTestProvider implements QueryProvider {
	private Map<String, String> query = Maps.newHashMap();

	@Override
	public List list()
	{
		return Lists.newArrayList(query.entrySet());
	}

	@Override
	public Object uniqueResult()
	{
		return new Object();
	}

	@Override
	public QueryProvider setMaxResults(int maxResults)
	{
		query.put("maxResult", ObjectUtils.toString(maxResults));
		return this;
	}

	@Override
	public QueryProvider setFirstResult(int firstResult)
	{
		query.put("firstResult", ObjectUtils.toString(firstResult));
		return this;
	}

	@Override
	public QueryProvider setParameter(String name, Object val)
	{
		query.put(name, ObjectUtils.toString(val));
		return this;
	}

	@Override
	public QueryProvider setString(String name, String value)
	{
		query.put(name, value);
		return this;
	}

	@Override
	public QueryProvider setParameterList(String name, Collection values)
	{
		query.put(name, ObjectUtils.toString(values));
		return this;
	}

	@Override
	public QueryProvider setParameterList(String name, Object[] values)
	{
		query.put(name, ObjectUtils.toString(values));
		return this;
	}
}
