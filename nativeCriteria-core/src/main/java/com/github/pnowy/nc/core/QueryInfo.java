package com.github.pnowy.nc.core;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Przemek Nowak <przemek.nowak.pl@gmail.com>
 * Date: 01.08.13 22:01
 */
public class QueryInfo
{
	private String sql;
	private Map<String, Object> parameteres;
	//todo execution statistics of query

	public QueryInfo()
	{
	}

	public QueryInfo(String sql)
	{
		this.sql = sql;
		this.parameteres = Maps.newHashMap();
	}

	public String getSql()
	{
		return sql;
	}

	@Override
	public String toString()
	{
		return "QueryInfo{" +
				"parameteres=" + parameteres +
				", sql='" + sql + '\'' +
				'}';
	}
}
