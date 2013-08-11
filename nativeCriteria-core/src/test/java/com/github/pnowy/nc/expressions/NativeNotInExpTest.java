package com.github.pnowy.nc.expressions;

import com.github.pnowy.nc.core.NativeExps;
import com.google.common.collect.Lists;
import org.testng.Assert;

/**
 * Przemek Nowak <przemek.nowak.pl@gmail.com>
 * Date: 08.08.13 21:35
 */
public class NativeNotInExpTest extends NativeExpGenericTest {

	@Override
	protected void prepareCriteria()
	{
		nc.add(NativeExps.notIn(MAIN_ALIAS_WITH_DOT + "col1", Lists.newArrayList("A1", "A2")));
		nc.add(NativeExps.notIn(MAIN_ALIAS_WITH_DOT + "col2", new String[]{"B1", "B2"}));
	}

	@Override
	protected void checkConditions()
	{
		Assert.assertTrue(sql.contains("not in"));
		Assert.assertTrue(parameters.containsValue("A1"));
		Assert.assertTrue(parameters.containsValue("B1"));
		Assert.assertFalse(parameters.containsValue("A3"));
		Assert.assertFalse(parameters.containsValue("B3"));
	}

}
