package com.github.pnowy.nc.expressions;

import com.github.pnowy.nc.core.NativeExps;

import static org.assertj.core.api.Assertions.assertThat;

public class NativeNotLikeExpTest extends NativeExpGenericTest {

    @Override
    protected void prepareCriteria() {
        nc.add(NativeExps.notLike(MAIN_ALIAS_WITH_DOT + "col1", "A1"));
    }

    @Override
    protected void checkConditions() {
        assertThat(sql).contains("not like");
        assertThat(parameters.containsValue("A1")).isTrue();
    }

}
