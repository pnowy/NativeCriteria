package com.github.pnowy.nc.expressions;

import com.github.pnowy.nc.core.NativeExps;

import static org.assertj.core.api.Assertions.assertThat;

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
