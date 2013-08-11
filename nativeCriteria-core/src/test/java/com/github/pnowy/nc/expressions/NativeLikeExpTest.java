package com.github.pnowy.nc.expressions;

import com.github.pnowy.nc.core.NativeExps;
import com.google.common.collect.Lists;
import org.testng.Assert;

/**
 * Przemek Nowak <przemek.nowak.pl@gmail.com>
 * Date: 08.08.13 21:35
 */
public class NativeLikeExpTest extends NativeExpGenericTest {

	@Override
	protected void prepareCriteria()
	{
		nc.add(NativeExps.like(MAIN_ALIAS_WITH_DOT+"col1", "A1"));
	}

	@Override
	protected void checkConditions()
	{
		Assert.assertTrue(sql.contains("like"));
		Assert.assertTrue(parameters.containsValue("A1"));
	}

}
