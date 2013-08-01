package com.nc.core.expressions;

import com.nc.core.NativeQuery;

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
	public void setValues(NativeQuery query);
}
