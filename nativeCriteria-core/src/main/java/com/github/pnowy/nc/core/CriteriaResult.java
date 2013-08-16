package com.github.pnowy.nc.core;

import com.github.pnowy.nc.core.mappers.NativeObjectMapper;

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
	 * Gets the string. Default value is null.
	 *
	 * @param idx idx
	 * @return value,
	 */
	public String getString(int idx);

	/**
	 * Gets the string.
	 *
	 * @param columnName the column name
	 * @param defaultResult the default result
	 * @return the string
	 */
	public String getString(String columnName, String defaultResult);

	/**
	 * Gets the string. Default value is null.
	 *
	 * @param columnName the column name
	 * @return value,
	 */
	public String getString(String columnName);
	
	/**
	 * Gets the integer.
	 *
	 * @param idx the idx
	 * @param defaultResult the default result
	 * @return the integer
	 */
	public Integer getInteger(int idx, Integer defaultResult);

	/**
	 * Gets the integer. Default value is null.
	 *
	 * @param idx idx
	 * @return integer, default is null
	 */
	public Integer getInteger(int idx);

	/**
	 * Gets the double.
	 *
	 * @param idx the idx
	 * @param defaultResult the default result
	 * @return the double
	 */
	public Double getDouble(int idx, Double defaultResult);

	/**
	 * Gets the double. Default result is null.
	 * @param idx idx
	 * @return the double, default is null
	 */
	public Double getDouble(int idx);

	/**
	 * Gets the double.
	 *
	 * @param columnName the column name
	 * @param defaultResult the default result
	 * @return the double
	 */
	public Double getDouble(String columnName, Double defaultResult);

	/**
	 * Gets the double. Default result is null.
	 *
	 * @param columnName the column name
	 * @return the double, default result is null
	 */
	public Double getDouble(String columnName);

	/**
	 * Gets the integer.
	 *
	 * @param columnName the column name
	 * @param defaultResult the default result
	 * @return the integer
	 */
	public Integer getInteger(String columnName, Integer defaultResult);

	/**
	 * Gets the integer. Default value is null.
	 *
	 * @param columnName the column name
	 * @return the integer, default value is null
	 */
	public Integer getInteger(String columnName);

	/**
	 * Gets the long.
	 *
	 * @param idx the idx
	 * @param defaultResult the default result
	 * @return the long
	 */
	public Long getLong(int idx, Long defaultResult);

	/**
	 * Gets the long, default result is null.
	 * @param idx idx
	 * @return the long, default result is null
	 */
	public Long getLong(int idx);

	/**
	 * Gets the long.
	 *
	 * @param columnName the column name
	 * @param defaultResult the default result
	 * @return the long
	 */
	public Long getLong(String columnName, Long defaultResult);

	/**
	 * Gets the long, default value is null.
	 *
	 * @param columnName the column name
	 * @return the long, default value is null
	 */
	public Long getLong(String columnName);

	/**
	 * Gets the short.
	 *
	 * @param idx the idx
	 * @param defaultResult the default result
	 * @return the short
	 */
	public Short getShort(int idx, Short defaultResult);

	/**
	 * Gets the short, default result is null.
	 * @param idx idx
	 * @return the short, default result is null
	 */
	public Short getShort(int idx);

	/**
	 * Gets the short.
	 *
	 * @param columnName the column name
	 * @param defaultResult the default result
	 * @return the short
	 */
	public Short getShort(String columnName, Short defaultResult);

	/**
	 * Gets the short, default result is null.
	 * @param columnName the column name
	 * @return the short, default result is null
	 */
	public Short getShort(String columnName);

	/**
	 * Gets the boolean.
	 *
	 * @param idx the idx
	 * @param defaultResult the default result
	 * @return the boolean
	 */
	public Boolean getBoolean(int idx, Boolean defaultResult);

	/**
	 * Gets the boolean, default result is null
	 *
	 * @param idx idx
	 * @return the boolean, default result is null
	 */
	public Boolean getBoolean(int idx);

	/**
	 * Gets the boolean.
	 *
	 * @param columnName the column name
	 * @param defaultResult the default result
	 * @return the boolean
	 */
	public Boolean getBoolean(String columnName, Boolean defaultResult);

	/**
	 * Gets the boolean, default result is boolean
	 * @param columnName column name
	 * @return the boolean, default result is null
	 */
	public Boolean getBoolean(String columnName);

	/**
	 * Gets the date.
	 *
	 * @param idx the idx
	 * @param defaultResult the default result
	 * @return the date
	 */
	public Date getDate(int idx, Date defaultResult);

	/**
	 * Gets the date, default result is null.
	 * @param idx idx
	 * @return the date, default result is null
	 */
	public Date getDate(int idx);

	/**
	 * Gets the date.
	 *
	 * @param columnName the column name
	 * @param defaultResult the default result
	 * @return the date
	 */
	public Date getDate(String columnName, Date defaultResult);

	/**
	 * Gets the date, default result is null
	 * @param columnName the column name
	 * @return the date, default result is null
	 */
	public Date getDate(String columnName);

	/**
	 * Gets the value.
	 *
	 * @param idx the idx
	 * @param defaultResult the default result
	 * @return the value
	 */
	public Object getValue(int idx, Object defaultResult);

	/**
	 * Gets the object, default value is null.
	 *
	 * @param idx idx
	 * @return the object, default value is null
	 */
	public Object getValue(int idx);

	/**
	 * Gets the value.
	 *
	 * @param columnName the column name
	 * @param defaultResult the default result
	 * @return the value
	 */
	public Object getValue(String columnName, Object defaultResult);

	/**
	 * Gets the object, default value is null
	 *
	 * @param columnName the column name
	 * @return the object, default value is null
	 */
	public Object getValue(String columnName);

	/**
	 * Gets the rows number.
	 *
	 * @return the rows number
	 */
	public Integer getRowsNumber();

	/**
	 * Get query information.
	 *
	 * @return query information
	 */
	public QueryInfo getQueryInfo();

	/**
	 * Method return all values from current row with pipe separator.
	 * Example:
	 * <p>
	 *     0 | ColumnValue1 | ColumnValue2 | ColumnValue3 | ...<br />
	 *     1 | ColumnValue2 | ColumnValue2 | ColumnValue3 | ...<br />
	 *     2 | ...
	 * </p>
	 *
	 * @return current rown from result
	 */
	public String getCurrentRecordDesc();

	/**
	 * Mapping criteria result to object.
	 *
	 * @param mapper implementation of mapper
	 * @param <T> class to return
	 * @return object
	 */
	public <T> T getRow(NativeObjectMapper<T> mapper);

}
