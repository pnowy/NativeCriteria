package com.nc.core.expressions;

import org.apache.commons.lang3.StringUtils;
import com.nc.core.QueryProvider;

/**
 * Native IS NULL expression.
 *
 */
public class NativeIsNullExp implements NativeExp
{
	/** Column name. */
	private String columnName;
	
	/**
	 * Instantiates a new native is null exp.
	 *
	 * @param columnName the column name
	 */
	public NativeIsNullExp(String columnName)
	{
		if (StringUtils.isBlank(columnName))
			throw new IllegalStateException("columnName is null!");
		
		this.columnName = columnName;
	}
	
	@Override
	public String toSQL()
	{
		return columnName + " IS NULL";
	}

	@Override
	public void setValues(QueryProvider query)
	{
		// ----------
	}
}
