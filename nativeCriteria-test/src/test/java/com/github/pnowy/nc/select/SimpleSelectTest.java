package com.github.pnowy.nc.select;

import com.github.pnowy.nc.AbstractDbTest;
import com.github.pnowy.nc.core.CriteriaResult;
import com.github.pnowy.nc.core.NativeCriteria;
import com.github.pnowy.nc.core.NativeExps;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.StrictAssertions.assertThat;

/**
 * Simple select database test with WHERE clause.
 *
 * Przemek Nowak [przemek.nowak.pl@gmail.com]
 */
public class SimpleSelectTest extends AbstractDbTest {
    private static final Logger log = LoggerFactory.getLogger(SimpleSelectTest.class);
    public static final String WARSAW = "Warsaw";

    @Test
    public void selectAddress() throws Exception {
        NativeCriteria nc = createNativeCriteria("ADDRESS", "a");
        nc.add(NativeExps.eq("a.city", WARSAW));
        CriteriaResult result = nc.criteriaResult();
        while (result.next()) {
            String currentRecordDesc = result.getCurrentRecordDesc();
            log.info(currentRecordDesc);
            assertThat(currentRecordDesc).isNotNull().containsIgnoringCase("|").contains(WARSAW);
        }
        assertThat(result.getRowsNumber()).isGreaterThan(0);
        log.info("{}", nc.getQueryInfo());
    }
}
