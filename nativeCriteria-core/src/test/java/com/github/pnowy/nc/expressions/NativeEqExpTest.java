package com.github.pnowy.nc.expressions;

import com.github.pnowy.nc.core.NativeExps;
import org.testng.Assert;

/**
 * Przemek Nowak <przemek.nowak.pl@gmail.com>
 * Date: 08.08.13 21:35
 */
public class NativeEqExpTest extends NativeExpGenericTest {
    private static String VALUE_TO_CHECK = "valueToCheck";

    @Override
    protected void prepareCriteria() {
        nc.add(NativeExps.eq(MAIN_ALIAS_WITH_DOT + "col1", VALUE_TO_CHECK));
        nc.addOr(NativeExps.eq(MAIN_ALIAS_WITH_DOT + "col2", VALUE_TO_CHECK));
    }

    @Override
    protected void checkConditions() {
        Assert.assertTrue(sql.contains("="));
        Assert.assertTrue(parameters.containsValue(VALUE_TO_CHECK));
    }

}
