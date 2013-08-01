package com.github.pnowy.nc.core.expressions;

import com.github.pnowy.nc.core.NativeQuery;
import org.apache.commons.lang3.StringUtils;
import com.github.pnowy.nc.utils.VarGenerator;

/**
 * Native NOT BETWEEN expression.
 */
public class NativeNotBetweenExp implements NativeExp
{
	private String columnName;
	private Object lowValue;
	private String lowValueVar;
	private Object highValue;
	private String highValueVar;
	
	/**
	 *
	 * @param columnName Nazwa kolumny
	 * @param lowValue Wi�ksza warto��
	 * @param highValue Mniejsza warto��
	 */
	public NativeNotBetweenExp(String columnName, Object lowValue, Object highValue)
	{
		if (StringUtils.isBlank(columnName))
			throw new IllegalStateException("columnName is null!");
		if (lowValue == null)
			throw new IllegalStateException("lowValue is null!");
		if (highValue == null)
			throw new IllegalStateException("highValue is null!");
		
		this.columnName = columnName;
		this.lowValue = lowValue;
		this.highValue = highValue;
	}
	
	@Override
	public String toSQL()
	{
		lowValueVar = VarGenerator.gen(columnName);
		highValueVar = VarGenerator.gen(columnName);
		return columnName + " NOT BETWEEN :" + lowValueVar + " AND :" + highValueVar;
	}

	@Override
	public void setValues(NativeQuery query)
	{
		query.setParameter(lowValueVar, lowValue);
		query.setParameter(highValueVar, highValue);
	}

//	public void setValues(SQLQuery query)
//	{
//		query.setParameter(lowValueVar, lowValue);
//		query.setParameter(highValueVar, highValue);
//	}
}
