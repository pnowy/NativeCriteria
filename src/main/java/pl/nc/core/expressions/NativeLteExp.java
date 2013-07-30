package pl.nc.core.expressions;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.nc.core.QueryProvider;
import pl.nc.utils.VarGenerator;

/**
 * The Class NativeLteExp.
 */
public class NativeLteExp implements NativeExp
{
	private static final Logger log = LoggerFactory.getLogger(NativeLteExp.class);
	private String columnName;
	private String varName;
	private Object value;
	
	/**
	 *
	 * @param columnName the column name
	 * @param value the value
	 */
	public NativeLteExp(String columnName, Object value)
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
		return columnName + " <= :" + varName;
	}

	@Override
	public void setValues(QueryProvider query)
	{
		query.setParameter(varName, value);
	}

//	public void setValues(SQLQuery query)
//	{
//		query.setParameter(varName, value);
//		if (log.isDebugEnabled()) log.debug("varName={}, value={}",varName,value);
//	}
}
