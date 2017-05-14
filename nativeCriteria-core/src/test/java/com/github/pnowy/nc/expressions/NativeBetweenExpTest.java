package com.github.pnowy.nc.expressions;

import com.github.pnowy.nc.core.NativeExps;

import static org.assertj.core.api.Assertions.assertThat;

public class NativeBetweenExpTest extends NativeExpGenericTest {

    @Override
    protected void prepareCriteria() {
        nc.add(NativeExps.between("col1", 12, 34));
    }

    @Override
    protected void checkConditions() {
        assertThat(sql).contains("between");
        assertThat(parameters.containsValue(12)).isTrue();
    }
}
