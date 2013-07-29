package pl.nc.core.expressions;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import pl.nc.core.expressions.NativeExp;

/**
 * Native expression IS NOT NULL .
 */
public class NativeIsNotNullExp implements NativeExp
{
	
	/** Column name. */
	private String columnName;
	
	/**
	 * Instantiates a new native is not null exp.
	 *
	 * @param columnName the column name
	 */
	public NativeIsNotNullExp(String columnName)
	{
		if (StringUtils.isBlank(columnName))
			throw new IllegalStateException("Warto�� columnName jest pusta!");
		
		this.columnName = columnName;
	}
	
	@Override
	public String toSQL()
	{
		return columnName + " IS NOT NULL";
	}
	
	@Override
	public void setValues(SQLQuery query)
	{
		// -------
	}
}
