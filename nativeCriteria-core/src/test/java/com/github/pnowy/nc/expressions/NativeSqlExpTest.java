package com.github.pnowy.nc.expressions;

import static com.github.pnowy.nc.core.NativeExps.eq;
import static com.github.pnowy.nc.core.NativeExps.sql;
import static org.assertj.core.api.Assertions.assertThat;

public class NativeSqlExpTest extends NativeExpGenericTest {

    @Override
    protected void prepareCriteria() {
        nc.add(eq("column_one", "value1"));
        nc.add(sql("column_two < SYSDATE "));
    }

    @Override
    protected void checkConditions() {
        assertThat(sql).containsIgnoringCase("sysdate");
    }

}
