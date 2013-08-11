package com.github.pnowy.nc.expressions;

import com.github.pnowy.nc.core.NativeExps;
import org.testng.Assert;

/**
 * Przemek Nowak <przemek.nowak.pl@gmail.com>
 * Date: 08.08.13 21:35
 */
public class NativeNotLikeExpTest extends NativeExpGenericTest {

	@Override
	protected void prepareCriteria()
	{
		nc.add(NativeExps.notLike(MAIN_ALIAS_WITH_DOT+"col1", "A1"));
	}

	@Override
	protected void checkConditions()
	{
		Assert.assertTrue(sql.contains("not like"));
		Assert.assertTrue(parameters.containsValue("A1"));
	}

}
