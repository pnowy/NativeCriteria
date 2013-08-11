package com.github.pnowy.nc.expressions;

import com.github.pnowy.nc.core.NativeExps;
import org.testng.Assert;

/**
 * Przemek Nowak <przemek.nowak.pl@gmail.com>
 * Date: 07.08.13 21:16
 */
public class NativeNotBetweenExpTest extends NativeExpGenericTest
{

	@Override
	protected void prepareCriteria()
	{
		nc.add(NativeExps.notBetween("col1", 12, 34));
	}

	@Override
	protected void checkConditions()
	{
		Assert.assertTrue(sql.contains("not between"));
		Assert.assertTrue(parameters.containsValue(12));
	}
}
