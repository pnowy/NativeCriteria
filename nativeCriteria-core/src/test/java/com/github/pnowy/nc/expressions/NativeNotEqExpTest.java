package com.github.pnowy.nc.expressions;

import com.github.pnowy.nc.core.NativeExps;
import org.assertj.core.api.Assertions;
import org.testng.Assert;

import static org.assertj.core.api.StrictAssertions.assertThat;

/**
 * Przemek Nowak [przemek.nowak.pl@gmail.com]
 */
public class NativeNotEqExpTest extends NativeExpGenericTest {
    private static String VALUE_TO_CHECK = "valueToCheck";

    @Override
    protected void prepareCriteria() {
        nc.add(NativeExps.notEq(MAIN_ALIAS_WITH_DOT + "col1", VALUE_TO_CHECK));
    }

    @Override
    protected void checkConditions() {
        assertThat(sql).contains("<>");
        assertThat(parameters.containsValue(VALUE_TO_CHECK)).isTrue();
    }

}
