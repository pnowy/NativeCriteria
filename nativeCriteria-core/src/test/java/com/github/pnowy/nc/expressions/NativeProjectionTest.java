package com.github.pnowy.nc.expressions;

import com.github.pnowy.nc.NativeTestProvider;
import com.github.pnowy.nc.core.NativeCriteria;
import com.github.pnowy.nc.core.NativeExps;
import com.github.pnowy.nc.core.expressions.NativeProjection;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;


public class NativeProjectionTest {
    private static final Logger log = LoggerFactory.getLogger(NativeProjectionTest.class);
    private NativeCriteria nc;

    @Before
    public void setUp() throws Exception {
        nc = new NativeCriteria(new NativeTestProvider(), "tableOne", "t1");
    }

    @Test
    public void testSimpleProjection() throws Exception {
        nc.addJoin(NativeExps.innerJoin("tableTwo", "t2", "t1.id", "t2.documentId"));
        NativeProjection np = new NativeProjection();
        np.addProjection("t1.c1", "t1.c2", "t2.c1");
        nc.setProjection(np);
        nc.criteriaResult();

        String sql = nc.getQueryInfo().getSql();
        assertThat(sql).contains("t1.c1");
        log.info("QueryInfo: {}", nc.getQueryInfo().getSummary());
    }

    @Test
    public void testAggregateProjection() throws Exception {
        nc.setProjection(NativeExps.projection().addProjection("t1.column").addAggregateProjection("t1.c3", NativeProjection.AggregateProjection.COUNT));
        nc.addHaving(NativeExps.gt("t1.c3", 34));
        nc.criteriaResult();

        String sql = nc.getQueryInfo().getSql();
        assertThat(sql).containsIgnoringCase("group by").containsIgnoringCase("having");
        log.info("QueryInfo: {}", nc.getQueryInfo().getSummary());
    }

    @Test
    public void testAggregateProjection2() throws Exception {
        nc.setProjection(NativeExps.projection().addAggregateProjection("t1.column", "c", NativeProjection.AggregateProjection.AVG));
        nc.criteriaResult();

        String sql = nc.getQueryInfo().getSql();
        assertThat(sql).containsIgnoringCase("avg");
        log.info("QueryInfo: {}", nc.getQueryInfo().getSummary());
    }

    @Test
    public void testAliasesProjection() throws Exception {
        nc.setProjection(NativeExps.projection().addProjectionWithAliases("t1.column as productName"));
        nc.criteriaResult();

        String sql = nc.getQueryInfo().getSql();
        assertThat(sql).contains("t1.column as productName");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIncorrectAliasesProjection() {
        nc.setProjection(NativeExps.projection().addProjectionWithAliases("t1.column by productName"));
        nc.criteriaResult();

        String sql = nc.getQueryInfo().getSql();
        assertThat(sql).contains("t1.column as productName");
    }
}
