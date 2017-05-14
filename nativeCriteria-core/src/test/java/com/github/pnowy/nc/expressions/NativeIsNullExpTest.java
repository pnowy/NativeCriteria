package com.github.pnowy.nc.expressions;

import com.github.pnowy.nc.core.NativeExps;

import static org.assertj.core.api.Assertions.assertThat;

public class NativeIsNullExpTest extends NativeExpGenericTest {

    @Override
    protected void prepareCriteria() {
        nc.add(NativeExps.isNull(MAIN_ALIAS_WITH_DOT + "col1"));
    }

    @Override
    protected void checkConditions() {
        assertThat(sql).contains("is null");
        assertThat(sql.contains(MAIN_ALIAS_WITH_DOT + "col1")).isTrue();
    }

}
