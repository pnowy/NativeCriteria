package com.github.pnowy.nc.expressions;

import com.github.pnowy.nc.core.NativeExps;
import org.junit.Assert;

/**
 * ILIKE expression test
 */
public class NativeILikeExpTest extends NativeExpGenericTest {

    @Override
    protected void prepareCriteria() {
        nc.add(NativeExps.ilike(MAIN_ALIAS_WITH_DOT + "col1", "A1"));
    }

    @Override
    protected void checkConditions() {
        Assert.assertTrue(sql.contains("ilike"));
        Assert.assertTrue(parameters.containsValue("A1"));
    }

}
