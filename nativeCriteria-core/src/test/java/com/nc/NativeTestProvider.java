package com.nc;

import com.nc.core.NativeQuery;
import com.nc.core.NativeQueryProvider;

/**
 * Przemek Nowak <przemek.nowak.pl@gmail.com>
 * Date: 30.07.13 17:39
 */
public class NativeTestProvider implements NativeQueryProvider
{
	@Override
	public NativeQuery getNativeQuery(String sql)
	{
		return new NativeQueryTest();
	}
}
