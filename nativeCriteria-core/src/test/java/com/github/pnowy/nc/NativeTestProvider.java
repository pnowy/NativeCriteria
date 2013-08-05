package com.github.pnowy.nc;

import com.github.pnowy.nc.core.NativeQuery;
import com.github.pnowy.nc.core.NativeQueryProvider;

/**
 *
 * Native test provider. Used for testing application without database.
 *
 * Przemek Nowak <przemek.nowak.pl@gmail.com>
 * Date: 30.07.13 17:39
 */
public class NativeTestProvider implements NativeQueryProvider
{
	@Override
	public NativeQuery getNativeQuery(String sql)
	{
		return new NativeQueryTest(sql);
	}
}
