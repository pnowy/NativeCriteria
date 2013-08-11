package com.github.pnowy.nc.core.expressions;

import com.github.pnowy.nc.core.NativeQuery;
import com.github.pnowy.nc.core.NativeQuery;
import org.apache.commons.lang3.StringUtils;

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
	
	@Override
	public String toSQL()
	{
		return sql;
	}

	@Override
	public void setValues(NativeQuery query)
	{
		// don't need to set parameters
	}
}
