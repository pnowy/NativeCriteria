package com.github.pnowy.nc.expressions;

import com.github.pnowy.nc.core.NativeExps;
import com.github.pnowy.nc.core.expressions.NativeConjunctionExp;
import com.github.pnowy.nc.core.expressions.NativeJunctionExp;
import com.github.pnowy.nc.core.expressions.NativeOrderExp;
import org.testng.Assert;

/**
 * Przemek Nowak <przemek.nowak.pl@gmail.com>
 * Date: 08.08.13 21:35
 */
public class NativeConjunctionExpTest extends NativeExpGenericTest {

	@Override
	protected void prepareCriteria()
	{
		String COLUMN_ONE = MAIN_ALIAS_WITH_DOT + "column_one";
		String COLUMN_TWO = MAIN_ALIAS_WITH_DOT + "column_two";
		NativeJunctionExp conjunction = NativeExps.conjunction().add(NativeExps.eq(COLUMN_ONE, "value1")).add(NativeExps.like(COLUMN_TWO, "value2"));
		nc.add(conjunction);
	}

	@Override
	protected void checkConditions()
	{
//		Assert.assertTrue(sql.contains("and"));
	}

}
