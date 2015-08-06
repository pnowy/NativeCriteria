package com.github.pnowy.nc.expressions;

import com.github.pnowy.nc.core.NativeExps;
import com.github.pnowy.nc.core.expressions.NativeExp;
import com.github.pnowy.nc.core.expressions.NativeJunctionExp;
import com.google.common.collect.Lists;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Przemek Nowak <przemek.nowak.pl@gmail.com>
 * Date: 08.08.13 21:35
 */
public class NativeConjunctionExpTest extends NativeExpGenericTest {

    @Override
    protected void prepareCriteria() {
        String COLUMN_ONE = MAIN_ALIAS_WITH_DOT + "column_one";
        String COLUMN_TWO = MAIN_ALIAS_WITH_DOT + "column_two";
        String COLUMN_THREE = MAIN_ALIAS_WITH_DOT + "column_three";
        String COLUMN_FOUR = MAIN_ALIAS_WITH_DOT + "column_four";
        // first conjunction
        NativeJunctionExp conjunction = NativeExps.conjunction();
        conjunction.add(NativeExps.eq(COLUMN_ONE, "value1"));
        conjunction.add(NativeExps.like(COLUMN_TWO, "value2"));
        // second conjunction
        List<NativeExp> expressions = Lists.newArrayList();
        expressions.add(NativeExps.eq(COLUMN_THREE, "value3"));
        expressions.add(NativeExps.gt(COLUMN_FOUR, 56));
        NativeJunctionExp conjunction2 = NativeExps.conjunction();
        conjunction2.add(expressions);

        nc.add(NativeExps.conjunction(Lists.<NativeExp>newArrayList(conjunction, conjunction2)));
    }

    @Override
    protected void checkConditions() {
        assertThat(sql).containsIgnoringCase("and").containsIgnoringCase("(").containsIgnoringCase(")");
    }

}
