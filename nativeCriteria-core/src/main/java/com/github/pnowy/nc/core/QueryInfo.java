package com.github.pnowy.nc.core;

import com.github.pnowy.nc.utils.Objects;

import java.util.HashMap;
import java.util.Map;

/**
 * Class contain some information about executed query.
 * Information:
 * <ul>
 * 		<li>sql</li>
 * 		<li>map with parameters</li>
 * </ul>
 *
 * @author Przemek Nowak <przemek.nowak.pl@gmail.com>
 */
public class QueryInfo
{
	private String sql;
	private Map<String, Object> parameters;

	public QueryInfo(String sql)
	{
		this.sql = sql;
		this.parameters = new HashMap<String, Object>();
	}

	public String getSql()
	{
		return sql;
	}

	public Map<String, Object> getParameters() {
		return parameters;
	}

	public String getSummary()
	{
		StringBuilder sb = new StringBuilder("QueryInfo:\n");
		sb.append("sql: ").append(sql).append("\n");
		for (Map.Entry<String, Object> entry : parameters.entrySet()) {
			sb.append("parameter name: ").append(entry.getKey()).append(", parameter value: ").append(Objects.toString(entry.getValue())).append("\n");
		}
		return sb.toString();
	}


	@Override
	public String toString()
	{
		return "QueryInfo{" +
				"parameters=" + parameters +
				", sql='" + sql + '\'' +
				'}';
	}
}
