package com.nc.core.expressions;

import com.nc.core.NativeQuery;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.nc.utils.VarGenerator;

import java.util.Collection;

/**
 * The Class NativeInExp.
 */
public class NativeInExp implements NativeExp
{
	private static final Logger log = LoggerFactory.getLogger(NativeInExp.class);
	private String columnName;
	@SuppressWarnings("unchecked")
	private Collection values;
	private Object[] arrValues;
	private String varName;
	
	/**
	 *
	 * @param columnName the column name
	 * @param values the values
	 */
	@SuppressWarnings("unchecked")
	public NativeInExp(String columnName, Collection values)
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
	public NativeInExp(String columnName, Object[] values)
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
		return columnName + " IN (:" + varName + ")";
	}

	@Override
	public void setValues(NativeQuery query)
	{
		if (values != null)
		{
			query.setParameterList(varName, values);
		}
		else if (arrValues != null)
		{
			query.setParameterList(varName, arrValues);
		}
	}

//	public void setValues(SQLQuery query)
//	{
//		if (values != null)
//		{
//			query.setParameterList(varName, values);
//			if (log.isDebugEnabled()) log.debug("varName={}, values={}",varName,values);
//		}
//		else if (arrValues != null)
//		{
//			query.setParameterList(varName, arrValues);
//			if (log.isDebugEnabled()) log.debug("varName={}, values={}",varName,Arrays.toString(arrValues));
//		}
//	}
}
