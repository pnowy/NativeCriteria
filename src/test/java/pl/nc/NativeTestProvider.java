package pl.nc;

import pl.nc.core.NativeProvider;
import pl.nc.core.QueryProvider;

/**
 * Przemek Nowak <przemek.nowak.pl@gmail.com>
 * Date: 30.07.13 17:39
 */
public class NativeTestProvider implements NativeProvider
{
	@Override
	public QueryProvider getQueryProvider(String sql)
	{
		return new QueryTestProvider();
	}
}
