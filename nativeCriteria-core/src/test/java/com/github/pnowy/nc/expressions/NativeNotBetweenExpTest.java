package com.github.pnowy.nc.expressions;

import com.github.pnowy.nc.core.NativeExps;

import static org.assertj.core.api.StrictAssertions.assertThat;

/**
 * Przemek Nowak [przemek.nowak.pl@gmail.com]
 */
public class NativeNotBetweenExpTest extends NativeExpGenericTest {

    @Override
    protected void prepareCriteria() {
        nc.add(NativeExps.notBetween("col1", 12, 34));
    }

    @Override
    protected void checkConditions() {
        assertThat(sql).contains("not between");
        assertThat(parameters.containsValue(12)).isTrue();
    }
}
