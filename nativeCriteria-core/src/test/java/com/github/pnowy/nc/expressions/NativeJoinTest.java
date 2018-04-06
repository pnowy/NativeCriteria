package com.github.pnowy.nc.expressions;

import com.github.pnowy.nc.NativeTestProvider;
import com.github.pnowy.nc.core.NativeCriteria;
import com.github.pnowy.nc.core.NativeExps;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.github.pnowy.nc.core.NativeExps.*;
import static org.assertj.core.api.Assertions.assertThat;

public class NativeJoinTest {
    private static final Logger log = LoggerFactory.getLogger(NativeJoinTest.class);
    private NativeCriteria nc;

    @Before
    public void setUp() {
        nc = new NativeCriteria(new NativeTestProvider(), "tableOne", "t1");
    }

    @Test
    public void testInnerJoin() {
        nc.addJoin(innerJoin("tableTwo", "t2", "t1.documentId", "t2.id"));
        nc.addJoin(innerJoin("tableThree", "t3", conjunction().add(eq("t3.status", "OPEN")).add(sql("t2.statusId = t3.id"))));
        nc.criteriaResult();
        String sql = nc.getQueryInfo().getSql();
        assertThat(sql).containsIgnoringCase("inner join");
        log.info("sql: {}", nc.getQueryInfo().getSql());
    }

    @Test
    public void testCustomJoinTable() {
        NativeCriteria subNativeCriteria = new NativeCriteria(new NativeTestProvider(), "subTableOne", "st1");
        subNativeCriteria.setProjection(NativeExps.projection().addProjection("st1.request_id", "requestId"));
        subNativeCriteria.addJoin(NativeExps.innerJoin("subTableTwo", "st2", "st1.id", "st1.sb1_id"));
        subNativeCriteria.add(NativeExps.eq("st1.name", "Awangarda"));

        nc.addJoin(NativeExps.innerJoin(subNativeCriteria, "w", "w.requestId", "t1.request_id"));
        nc.criteriaResult();
        String sql = nc.getQueryInfo().getSql();
        assertThat(sql).containsIgnoringCase("inner join");
        log.info("sql: {}", nc.getQueryInfo().getSummary());
    }

    @Test
    public void testLeftJoin() {
        nc.addJoin(leftJoin("tableTwo", "t2", "t1.documentId", "t2.id"));
        nc.criteriaResult();
        String sql = nc.getQueryInfo().getSql();
        assertThat(sql).containsIgnoringCase("left outer join");
        log.info("sql: {}", nc.getQueryInfo().getSql());
    }

    @Test
    public void testRightJoin() {
        nc.addJoin(rightJoin("tableTwo", "t2", "t1.documentId", "t2.id"));
        nc.criteriaResult();
        String sql = nc.getQueryInfo().getSql();
        assertThat(sql).containsIgnoringCase("right outer join");
        log.info("sql: {}", nc.getQueryInfo().getSql());
    }

    @Test
    public void testFullOuterJoin() {
        nc.addJoin(fullJoin("tableTwo", "t2", "t1.documentId", "t2.id"));
        nc.criteriaResult();
        String sql = nc.getQueryInfo().getSql();
        assertThat(sql).containsIgnoringCase("full outer join");
        log.info("sql: {}", nc.getQueryInfo().getSql());
    }

    @Test
    public void testCrossJoin() {
        nc.addJoin(crossJoin("tableTwo", "t2"));
        nc.criteriaResult();
        String sql = nc.getQueryInfo().getSql();
        assertThat(sql).containsIgnoringCase("cross join");
        log.info("sql: {}", nc.getQueryInfo().getSql());
    }

    @Test
    public void testNaturalJoin() {
        nc.addJoin(naturalJoin("tableTwo", "t2"));
        nc.criteriaResult();
        String sql = nc.getQueryInfo().getSql();
        assertThat(sql).containsIgnoringCase("natural join");
        log.info("sql: {}", nc.getQueryInfo().getSql());
    }
}
