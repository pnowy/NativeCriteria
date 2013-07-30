package pl.nc.core.expressions;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import pl.nc.core.QueryProvider;
import pl.nc.core.expressions.NativeExp;
import pl.nc.utils.VarGenerator;

/**
 * Native NOT EQUAL expression.
 */
public class NativeNotEqExp implements NativeExp
{
	private String columnName;
	private String varName;
	private Object value;
	
	/**
	 *
	 * @param columnName the column name
	 * @param value the value
	 */
	public NativeNotEqExp(String columnName, Object value)
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
		return columnName + " <> :" + varName;
	}

	@Override
	public void setValues(QueryProvider query)
	{
		query.setParameter(varName, value);
	}

//	@Override
//	public void setValues(SQLQuery query)
//	{
//		query.setParameter(varName, value);
//	}
}
