package com.github.pnowy.nc.expressions;

import com.github.pnowy.nc.core.NativeExps;
import com.github.pnowy.nc.core.expressions.NativeOrderExp;

import static org.assertj.core.api.Assertions.assertThat;

public class NativeOrderExpTest extends NativeExpGenericTest {

    @Override
    protected void prepareCriteria() {
        String COLUMN_ONE = MAIN_ALIAS_WITH_DOT + "column_one";
        nc.add(NativeExps.eq(COLUMN_ONE, "value1"));
        String COLUMN_TWO = MAIN_ALIAS_WITH_DOT + "column_two";
        nc.add(NativeExps.eq(COLUMN_TWO, "value2"));

        nc.setOrder(NativeExps.order().add(COLUMN_ONE, NativeOrderExp.OrderType.ASC).add(COLUMN_TWO, NativeOrderExp.OrderType.DESC));
    }

    @Override
    protected void checkConditions() {
        assertThat(sql).contains("order by");
        assertThat(sql).contains("asc");
        assertThat(sql).contains("desc");
    }

}
