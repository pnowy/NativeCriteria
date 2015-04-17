package com.github.pnowy.nc.select;

import com.github.pnowy.nc.core.CriteriaResult;
import com.github.pnowy.nc.core.NativeCriteria;
import com.github.pnowy.nc.core.NativeExps;
import com.github.pnowy.nc.core.NativeQueryProvider;
import com.github.pnowy.nc.utils.HibernateUtil;
import com.github.pnowy.nc.utils.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Simple database test.
 *
 * Przemek Nowak [przemek.nowak.pl@gmail.com]
 * Date: 30.07.13 17:41
 */
public class SelectTestAsCriteria implements Transactional {
    private static final Logger log = LoggerFactory.getLogger(SelectTestAsCriteria.class);

    @Override
    public void transaction(NativeQueryProvider provider) {
        NativeCriteria nc = new NativeCriteria(provider, "ADDRESS", "a");
        nc.add(NativeExps.eq("a.city", "WARSAW"));
        CriteriaResult result = nc.criteriaResult();
        while (result.next()) {
            String desc = result.getCurrentRecordDesc();
            assertThat(desc).isNotNull().containsIgnoringCase("|").contains("WARSAW");
        }
        assertThat(result.getRowsNumber()).isGreaterThan(0);
    }

    @Test
    public void test() {
        HibernateUtil.executeTransaction(this);
    }
}
