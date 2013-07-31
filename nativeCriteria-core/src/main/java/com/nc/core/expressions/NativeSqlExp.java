package com.nc.core.expressions;

import org.apache.commons.lang3.StringUtils;
import com.nc.core.QueryProvider;

/**
 * Default expression for where.
 * 
 */
public class NativeSqlExp implements NativeExp
{
	private String sql;
	
	/**
	 * Constructor with sql argument.
	 *
	 * @param sql the sql
	 */
	public NativeSqlExp(String sql)
	{
		if (StringUtils.isEmpty(sql))
			throw new IllegalStateException("sql is empty!");
		
		this.sql = sql;
	}
	
//	@Override
//	public void setValues(SQLQuery query)
//	{
//		// don't need to set parameteres
//	}
	
	@Override
	public String toSQL()
	{
		return sql;
	}

	@Override
	public void setValues(QueryProvider query)
	{
		// don't need to set parameters
	}
}
