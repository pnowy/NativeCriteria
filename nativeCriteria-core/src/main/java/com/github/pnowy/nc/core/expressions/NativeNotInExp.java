package com.github.pnowy.nc.core.expressions;

import java.util.List;

import com.github.pnowy.nc.core.NativeQuery;
import com.github.pnowy.nc.utils.VarGenerator;
import com.github.pnowy.nc.core.NativeQuery;
import org.apache.commons.lang3.StringUtils;
import com.github.pnowy.nc.utils.VarGenerator;

/**
 * Native NOT IN expression.
 */
public class NativeNotInExp implements NativeExp
{
	private String columnName;
	@SuppressWarnings("unchecked")
	private List values;
	private Object[] arrValues;
	private String varName;
	
	/**
	 *
	 * @param columnName the column name
	 * @param values the values
	 */
	@SuppressWarnings("unchecked")
	public NativeNotInExp(String columnName, List values)
	{
		if (StringUtils.isBlank(columnName))
			throw new IllegalStateException("columnName is null!");
		if (values == null)
			throw new IllegalStateException("values is null!");
		
		this.columnName = columnName;
		this.values = values;
	}
	
	/**
	 *
	 * @param columnName the column name
	 * @param values the values
	 */
	public NativeNotInExp(String columnName, Object[] values)
	{
		if (StringUtils.isBlank(columnName))
			throw new IllegalStateException("columnName is null!");
		if (values == null)
			throw new IllegalStateException("values is null!");
		
		this.columnName = columnName;
		this.arrValues = values;
	}
	
	@Override
	public String toSQL()
	{
		varName = VarGenerator.gen(columnName);
		return columnName + " NOT IN (:" + varName + ")";
	}

	@Override
	public void setValues(NativeQuery query)
	{
		if (values != null)
			query.setParameterList(varName, values);
		else if (arrValues != null)
			query.setParameterList(varName, arrValues);
	}

//	public void setValues(SQLQuery query)
//	{
//		if (values != null)
//			query.setParameterList(varName, values);
//		else if (arrValues != null)
//			query.setParameterList(varName, arrValues);
//	}
}
