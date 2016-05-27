package com.github.pnowy.nc.queries;

import com.github.pnowy.nc.AbstractSqlServerTest;
import com.github.pnowy.nc.core.CriteriaResult;
import com.github.pnowy.nc.core.NativeCriteria;
import com.github.pnowy.nc.core.NativeExps;
import com.github.pnowy.nc.core.expressions.NativeOrderExp;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.StrictAssertions.assertThat;

/**
 * Custom order test.
 */
public class SqlServerCustomOrderTest extends AbstractSqlServerTest {
    private static final Logger log = LoggerFactory.getLogger(SqlServerCustomOrderTest.class);

    @Test
    public void shouldOrderByNEWID() throws Exception {
        NativeCriteria nc = createNativeCriteria("ADDRESS", "a");
        nc.setOrder(NativeExps.order().add("NEWID()", NativeOrderExp.OrderType.ASC));
        nc.setLimit(10);
        CriteriaResult result = nc.criteriaResult();
        while (result.next()) {
            String currentRecordDesc = result.getCurrentRecordDesc();
            log.info(currentRecordDesc);
        }
        assertThat(result.getRowsNumber()).isGreaterThan(0);
        log.info("{}", nc.getQueryInfo());
    }
}
