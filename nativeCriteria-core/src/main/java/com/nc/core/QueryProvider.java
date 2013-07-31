package com.nc.core;

import java.util.Collection;
import java.util.List;

/**
 * Native query provider.
 */
public interface QueryProvider
{
	public List list();
	public Object uniqueResult();
	public QueryProvider setMaxResults(int maxResults);
	public QueryProvider setFirstResult(int firstResult);
	public QueryProvider setParameter(String name, Object val);
	public QueryProvider setString(String name, String value);
	public QueryProvider setParameterList(String name, Collection values);
	public QueryProvider setParameterList(String name, Object[] values);
}
