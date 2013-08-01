package com.github.pnowy.nc;

import com.beust.jcommander.internal.Lists;
import com.github.pnowy.nc.core.QueryInfo;
import com.google.common.collect.Maps;
import com.github.pnowy.nc.core.NativeQuery;
import org.apache.commons.lang3.ObjectUtils;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Przemek Nowak <przemek.nowak.pl@gmail.com>
 * Date: 30.07.13 17:40
 */
public class NativeQueryTest implements NativeQuery
{
	private QueryInfo queryInfo;

	public NativeQueryTest(String sql)
	{
		this.queryInfo = new QueryInfo(sql);
	}

	@Override
	public List list()
	{
		return Lists.newArrayList();
	}

	@Override
	public Object uniqueResult()
	{
		return new Object();
	}

	@Override
	public NativeQuery setMaxResults(int maxResults)
	{
//		query.put("maxResult", ObjectUtils.toString(maxResults));
		return this;
	}

	@Override
	public NativeQuery setFirstResult(int firstResult)
	{
//		query.put("firstResult", ObjectUtils.toString(firstResult));
		return this;
	}

	@Override
	public NativeQuery setParameter(String name, Object val)
	{
//		query.put(name, ObjectUtils.toString(val));
		return this;
	}

	@Override
	public NativeQuery setString(String name, String value)
	{
//		query.put(name, value);
		return this;
	}

	@Override
	public NativeQuery setParameterList(String name, Collection values)
	{
//		query.put(name, ObjectUtils.toString(values));
		return this;
	}

	@Override
	public NativeQuery setParameterList(String name, Object[] values)
	{
//		query.put(name, ObjectUtils.toString(values));
		return this;
	}

	@Override
	public QueryInfo getQueryInfo()
	{
		return queryInfo;
	}
}
