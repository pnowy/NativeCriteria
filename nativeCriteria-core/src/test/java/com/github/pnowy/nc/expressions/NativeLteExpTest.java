package com.github.pnowy.nc.expressions;

import com.github.pnowy.nc.core.NativeExps;

import static org.assertj.core.api.StrictAssertions.assertThat;

/**
 * Przemek Nowak [przemek.nowak.pl@gmail.com]
 */
public class NativeLteExpTest extends NativeExpGenericTest {
    private static final String VALUE_TO_CHECK = "valueToCheck";

    @Override
    protected void prepareCriteria() {
        nc.add(NativeExps.lte(MAIN_ALIAS_WITH_DOT + "col1", VALUE_TO_CHECK));
    }

    @Override
    protected void checkConditions() {
        assertThat(sql).contains("<=");
        assertThat(parameters.containsValue(VALUE_TO_CHECK)).isTrue();
    }
}
