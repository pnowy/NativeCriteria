package pl.nc.core.expressions;

import org.hibernate.SQLQuery;

/**
 * Interface for native SQL Expressions.
 */
public interface NativeExp
{
	
	/**
	 * To sql.
	 *
	 * @return the string
	 */
	public String toSQL();
	
	/**
	 * Sets the values.
	 *
	 * @param query the new values
	 */
	public void setValues(SQLQuery query);
}
