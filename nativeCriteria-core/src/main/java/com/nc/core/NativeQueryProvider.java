package com.nc.core;

/**
 * Native provider.
 */
public interface NativeQueryProvider
{
	/**
	 * Get native query implementation based on build sql query by NativeCriteria class.
	 * @param buildSql pure sql query
	 * @return native query implementation
	 */
	public NativeQuery getNativeQuery(String buildSql);
}
