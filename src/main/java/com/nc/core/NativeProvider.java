package com.nc.core;

/**
 * Native select provider.
 */
public interface NativeProvider
{
	public QueryProvider getQueryProvider(String sql);
}
