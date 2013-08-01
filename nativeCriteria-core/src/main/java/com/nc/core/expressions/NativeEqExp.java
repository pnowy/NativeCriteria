package com.nc.core.expressions;

import com.nc.core.NativeQuery;
import org.apache.commons.lang3.StringUtils;
import com.nc.utils.VarGenerator;

/**
 * Native equals expression.
 */
public class NativeEqExp implements NativeExp
{
	/** Column name. */
	private String columnName;
	
	/** Variable name. */
	private String varName;
	
	/** Compared value. */
	private Object value;
	
	/**
	 *
	 * @param columnName the column name
	 * @param value the value
	 */
	public NativeEqExp(String columnName, Object value)
	{
		if (StringUtils.isBlank(columnName))
			throw new IllegalStateException("columnName is null!");
		if (value == null)
			throw new IllegalStateException("value is null!");
		
		this.columnName = columnName;
		this.value = value;
	}
	
	@Override
	public String toSQL()
	{
		varName = VarGenerator.gen(columnName);
		return columnName + " = :" + varName;
	}

	@Override
	public void setValues(NativeQuery query)
	{
		query.setParameter(varName, value);
	}

//	public void setValues(SQLQuery query)
//	{
//		query.setParameter(varName, value);
//	}
}
