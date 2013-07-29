package pl.nc.core.expressions;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import pl.nc.core.expressions.NativeExp;
import pl.nc.utils.VarGenerator;

/**
 * Expression between native SQL.
 */
public class NativeBetweenExp implements NativeExp
{
	
	/** Column name. */
	private String columnName;
	
	/** Lower value. */
	private Object lowValue;
	
	/** Variable name for lowValue. */
	private String lowValueVar;
	
	/** Higer value. */
	private Object highValue;
	
	/** Variable name for highValue. */
	private String highValueVar;
	
	/**
	 * Construcotr.
	 *
	 * @param columnName Column name
	 * @param lowValue lower value
	 * @param highValue higer value
	 */
	public NativeBetweenExp(String columnName, Object lowValue, Object highValue)
	{
		if (StringUtils.isBlank(columnName))
			throw new IllegalStateException("ColumnName is null!");
		if (lowValue == null)
			throw new IllegalStateException("LowValue is null!");
		if (highValue == null)
			throw new IllegalStateException("HidhValue is null!");
		
		this.columnName = columnName;
		this.lowValue = lowValue;
		this.highValue = highValue;
	}
	
	@Override
	public String toSQL()
	{
		lowValueVar = VarGenerator.gen(columnName);
		highValueVar = VarGenerator.gen(columnName);
		return columnName + " BETWEEN :" + lowValueVar + " AND :" + highValueVar;
	}
	
	public void setValues(SQLQuery query)
	{
		query.setParameter(lowValueVar, lowValue);
		query.setParameter(highValueVar, highValue);
	}
}
