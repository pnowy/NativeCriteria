package com.github.pnowy.nc.core;

import java.util.Date;

/**
 * Interface CriteriaResult.
 */
public interface CriteriaResult
{
	
	/**
	 * Next.
	 *
	 * @return true, if successful
	 */
	public boolean next();
	
	/**
	 * Gets the string.
	 *
	 * @param idx the idx
	 * @param defaultResult the default result
	 * @return the string
	 */
	public String getString(int idx, String defaultResult);
	
	/**
	 * Gets the string.
	 *
	 * @param columnName the column name
	 * @param defaultResult the default result
	 * @return the string
	 */
	public String getString(String columnName, String defaultResult);
	
	/**
	 * Gets the integer.
	 *
	 * @param idx the idx
	 * @param defaultResult the default result
	 * @return the integer
	 */
	public Integer getInteger(int idx, Integer defaultResult);
	
	/**
	 * Gets the double.
	 *
	 * @param idx the idx
	 * @param defaultResult the default result
	 * @return the double
	 */
	public Double getDouble(int idx, Double defaultResult);
	
	/**
	 * Gets the double.
	 *
	 * @param columnName the column name
	 * @param defaultResult the default result
	 * @return the double
	 */
	public Double getDouble(String columnName, Double defaultResult);
	
	/**
	 * Gets the integer.
	 *
	 * @param columnName the column name
	 * @param defaultResult the default result
	 * @return the integer
	 */
	public Integer getInteger(String columnName, Integer defaultResult);
	
	/**
	 * Gets the long.
	 *
	 * @param idx the idx
	 * @param defaultResult the default result
	 * @return the long
	 */
	public Long getLong(int idx, Long defaultResult);
	
	/**
	 * Gets the long.
	 *
	 * @param columnName the column name
	 * @param defaultResult the default result
	 * @return the long
	 */
	public Long getLong(String columnName, Long defaultResult);
	
	/**
	 * Gets the short.
	 *
	 * @param idx the idx
	 * @param defaultResult the default result
	 * @return the short
	 */
	public Short getShort(int idx, Short defaultResult);
	
	/**
	 * Gets the short.
	 *
	 * @param columnName the column name
	 * @param defaultResult the default result
	 * @return the short
	 */
	public Short getShort(String columnName, Short defaultResult);
	
	/**
	 * Gets the boolean.
	 *
	 * @param idx the idx
	 * @param defaultResult the default result
	 * @return the boolean
	 */
	public Boolean getBoolean(int idx, Boolean defaultResult);
	
	/**
	 * Gets the boolean.
	 *
	 * @param columnName the column name
	 * @param defaultResult the default result
	 * @return the boolean
	 */
	public Boolean getBoolean(String columnName, Boolean defaultResult);
	
	/**
	 * Gets the date.
	 *
	 * @param idx the idx
	 * @param defaultResult the default result
	 * @return the date
	 */
	public Date getDate(int idx, Date defaultResult);
	
	/**
	 * Gets the date.
	 *
	 * @param columnName the column name
	 * @param defaultResult the default result
	 * @return the date
	 */
	public Date getDate(String columnName, Date defaultResult);
	
	/**
	 * Gets the value.
	 *
	 * @param idx the idx
	 * @param defaultResult the default result
	 * @return the value
	 */
	public Object getValue(int idx, Object defaultResult);
	
	/**
	 * Gets the value.
	 *
	 * @param columnName the column name
	 * @param defaultResult the default result
	 * @return the value
	 */
	public Object getValue(String columnName, Object defaultResult);
	
	/**
	 * Gets the rows number.
	 *
	 * @return the rows number
	 */
	public Integer getRowsNumber();

	public QueryInfo getQueryInfo();
}
