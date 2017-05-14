package com.github.pnowy.nc.expressions;

import com.github.pnowy.nc.core.NativeExps;

import static org.assertj.core.api.Assertions.assertThat;

public class NativeLikeExpTest extends NativeExpGenericTest {

    @Override
    protected void prepareCriteria() {
        nc.add(NativeExps.like(MAIN_ALIAS_WITH_DOT + "col1", "A1"));
    }

    @Override
    protected void checkConditions() {
        assertThat(sql).contains("like");
        assertThat(parameters.containsValue("A1")).isTrue();
    }

}
