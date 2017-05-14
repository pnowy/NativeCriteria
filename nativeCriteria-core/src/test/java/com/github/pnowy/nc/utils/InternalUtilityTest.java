package com.github.pnowy.nc.utils;


import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <p>Test for some internal utilities for {@linkplain com.github.pnowy.nc.core.NativeCriteria}
 *
 * @author Przemek Nowak
 */
public class InternalUtilityTest {

    @Test
    public void shouldSplitColumnWithAliasToSeparateColumnAndAlias() throws Exception {
        String columnWithAliasLowercase = "p.name as productName";
        String columnWithAliasUppercase = "p.name AS productName";
        String columnWithAliasMixed = "p.name As productName";
        String columnWithAliasMixed2 = "p.name aS productName";

        final ArrayList<String> columnsWithAliases =
            Lists.newArrayList(columnWithAliasLowercase, columnWithAliasUppercase, columnWithAliasMixed, columnWithAliasMixed2);

        for (String statement : columnsWithAliases) {
            final String[] result = statement.split("(?i) AS ");
            System.out.println(Arrays.toString(result));
            assertThat(result).hasSize(2);
            assertThat(result[0]).isEqualTo("p.name");
            assertThat(result[1]).isEqualTo("productName");
        }
    }
}
