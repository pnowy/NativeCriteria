package com.nc.core.expressions;

import com.nc.core.NativeQuery;
import org.apache.commons.lang3.StringUtils;
import com.nc.utils.VarGenerator;

/**
 * Native LIKE expression.
 */
public class NativeLikeExp implements NativeExp
{
	private String columnName;
	private String varName;
	private String value;
	
	/**
	 *
	 * @param columnName the column name
	 * @param value the value
	 */
	public NativeLikeExp(String columnName, String value)
	{
		if (StringUtils.isBlank(columnName))
			throw new IllegalStateException("columnName is null!");
		if (StringUtils.isBlank(value))
			throw new IllegalStateException("value is null!");
		
		this.columnName = columnName;
		this.value = value;
	}
	
	@Override
	public String toSQL()
	{
		varName = VarGenerator.gen(columnName);
		return columnName + " LIKE :" + varName;
	}

	@Override
	public void setValues(NativeQuery query)
	{
		query.setString(varName, value);
	}

//	@Override
//	public void setValues(SQLQuery query)
//	{
//		query.setString(varName, value);
//	}
}
