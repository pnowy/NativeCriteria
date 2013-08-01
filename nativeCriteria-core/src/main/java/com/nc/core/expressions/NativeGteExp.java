package com.nc.core.expressions;

import com.nc.core.NativeQuery;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.nc.utils.VarGenerator;

/**
 * Greater or equal native expression.
 */
public class NativeGteExp implements NativeExp
{
	
	/** The Constant log. */
	private static final Logger log = LoggerFactory.getLogger(NativeGteExp.class);
	/** Column name. */
	private String columnName;
	private String varName;
	
	/** Compared value. */
	private Object value;
	
	/**
	 *
	 * @param columnName the column name
	 * @param value the value
	 */
	public NativeGteExp(String columnName, Object value)
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
		return columnName + " >= :" + varName;
	}

	@Override
	public void setValues(NativeQuery query)
	{
		query.setParameter(varName, value);
	}
}