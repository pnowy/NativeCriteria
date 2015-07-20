package com.github.pnowy.nc.expressions;

import com.github.pnowy.nc.NativeTestProvider;
import com.github.pnowy.nc.core.NativeCriteria;
import com.github.pnowy.nc.core.NativeExps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.github.pnowy.nc.core.NativeExps.*;
import static org.fest.assertions.Assertions.assertThat;

/**
 * Przemek Nowak <przemek.nowak.pl@gmail.com>
 * Date: 07.08.13 21:16
 */
public class NativeJoinTest {
    private static final Logger log = LoggerFactory.getLogger(NativeJoinTest.class);
    private NativeCriteria nc;

    @BeforeMethod
    public void setUp() throws Exception {
        nc = new NativeCriteria(new NativeTestProvider(), "tableOne", "t1");
    }

    @Test
    public void testInnerJoin() throws Exception {
        nc.addJoin(innerJoin("tableTwo", "t2", "t1.documentId", "t2.id"));
        nc.addJoin(innerJoin("tableThree", "t3", conjunction().add(eq("t3.status", "OPEN")).add(sql("t2.statusId = t3.id"))));
        nc.criteriaResult();
        String sql = nc.getQueryInfo().getSql();
        assertThat(sql).containsIgnoringCase("inner join");
        log.info("sql: {}", nc.getQueryInfo().getSql());
    }

    @Test
    public void testCustomJoinTable() throws Exception {
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
    public void testLeftJoin() throws Exception {
        nc.addJoin(leftJoin("tableTwo", "t2", "t1.documentId", "t2.id"));
        nc.criteriaResult();
        String sql = nc.getQueryInfo().getSql();
        assertThat(sql).containsIgnoringCase("left outer join");
        log.info("sql: {}", nc.getQueryInfo().getSql());
    }

    @Test
    public void testRightJoin() throws Exception {
        nc.addJoin(rightJoin("tableTwo", "t2", "t1.documentId", "t2.id"));
        nc.criteriaResult();
        String sql = nc.getQueryInfo().getSql();
        assertThat(sql).containsIgnoringCase("right outer join");
        log.info("sql: {}", nc.getQueryInfo().getSql());
    }

    @Test
    public void testFullOuterJoin() throws Exception {
        nc.addJoin(fullJoin("tableTwo", "t2", "t1.documentId", "t2.id"));
        nc.criteriaResult();
        String sql = nc.getQueryInfo().getSql();
        assertThat(sql).containsIgnoringCase("full outer join");
        log.info("sql: {}", nc.getQueryInfo().getSql());
    }

    @Test
    public void testCrossJoin() throws Exception {
        nc.addJoin(crossJoin("tableTwo", "t2"));
        nc.criteriaResult();
        String sql = nc.getQueryInfo().getSql();
        assertThat(sql).containsIgnoringCase("cross join");
        log.info("sql: {}", nc.getQueryInfo().getSql());
    }

    @Test
    public void testNaturalJoin() throws Exception {
        nc.addJoin(naturalJoin("tableTwo", "t2"));
        nc.criteriaResult();
        String sql = nc.getQueryInfo().getSql();
        assertThat(sql).containsIgnoringCase("natural join");
        log.info("sql: {}", nc.getQueryInfo().getSql());
    }
}
