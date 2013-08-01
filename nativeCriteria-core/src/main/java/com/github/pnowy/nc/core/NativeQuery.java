package com.github.pnowy.nc.core;

import java.util.Collection;
import java.util.List;

/**
 *
 * Native query interface.
 *
 */
public interface NativeQuery
{
	public List list();
	public Object uniqueResult();
	public NativeQuery setMaxResults(int maxResults);
	public NativeQuery setFirstResult(int firstResult);
	public NativeQuery setParameter(String name, Object val);
	public NativeQuery setString(String name, String value);
	public NativeQuery setParameterList(String name, Collection values);
	public NativeQuery setParameterList(String name, Object[] values);

	/**
	 * Method return information about done query.
	 * @return query information
	 */
	public QueryInfo getQueryInfo();
}
