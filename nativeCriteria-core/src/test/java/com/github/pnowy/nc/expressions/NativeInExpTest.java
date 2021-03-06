package com.github.pnowy.nc.expressions;

import com.github.pnowy.nc.core.NativeExps;
import com.google.common.collect.Lists;

import static org.assertj.core.api.Assertions.assertThat;

public class NativeInExpTest extends NativeExpGenericTest {

    @Override
    protected void prepareCriteria() {
        nc.add(NativeExps.in(MAIN_ALIAS_WITH_DOT + "col1", Lists.newArrayList("A1", "A2")));
        nc.add(NativeExps.in(MAIN_ALIAS_WITH_DOT + "col2", new String[]{"B1", "B2"}));
    }

    @Override
    protected void checkConditions() {
        assertThat(sql).contains("in");
        assertThat(parameters.containsValue("A1")).isTrue();
        assertThat(parameters.containsValue("B1")).isTrue();
        assertThat(parameters.containsValue("A3")).isFalse();
        assertThat(parameters.containsValue("B3")).isFalse();
    }

}
