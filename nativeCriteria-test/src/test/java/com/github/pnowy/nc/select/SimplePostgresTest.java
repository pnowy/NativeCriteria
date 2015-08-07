package com.github.pnowy.nc.select;

import com.github.pnowy.nc.AbstractPostgresqlTest;
import com.github.pnowy.nc.core.CriteriaResult;
import com.github.pnowy.nc.core.NativeCriteria;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.StrictAssertions.assertThat;

/**
 * Example test which execute only for Postgresql profile.
 *
 * Przemek Nowak [przemek.nowak.pl@gmail.com]
 */
public class SimplePostgresTest extends AbstractPostgresqlTest {
    private static final Logger log = LoggerFactory.getLogger(SimplePostgresTest.class);

    @Test
    public void selectAddress() throws Exception {
        NativeCriteria nc = createNativeCriteria("SUPPLIER", "s");
        CriteriaResult result = nc.criteriaResult();
        assertThat(result.getRowsNumber()).isGreaterThan(0);
        log.info("{}", nc.getQueryInfo());
    }
}
