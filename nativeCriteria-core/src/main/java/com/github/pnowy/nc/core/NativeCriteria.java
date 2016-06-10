package com.github.pnowy.nc.core;

import com.github.pnowy.nc.core.expressions.NativeExp;
import com.github.pnowy.nc.core.expressions.NativeJoin;
import com.github.pnowy.nc.core.expressions.NativeProjection;
import com.github.pnowy.nc.core.mappers.CriteriaResultTransformer;
import com.github.pnowy.nc.core.mappers.NativeObjectMapper;
import com.github.pnowy.nc.utils.Strings;
import com.google.common.base.Stopwatch;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

/**
 * Class to build native sql queries.
 */
public class NativeCriteria implements NativeExp {
    private static final Logger LOG = LoggerFactory.getLogger(NativeCriteria.class);
    private static final Logger PERFORMANCE_LOG = LoggerFactory.getLogger("NativeCriteriaPerformance");
    private static final String SPACE = " ";

    /**
     * Hibernate session.
     */
    private NativeQueryProvider nativeProvider;
    /**
     * Native query.
     */
    private NativeQuery nativeQuery;

    /**
     * Tables with aliases to FROM clause.
     */
    private Map<String, String> tables;

    /**
     * Joins.
     */
    private List<NativeExp> joins;

    /**
     * WHERE expressions.
     */
    private Map<NativeExp, Operator> whereExps;

    /**
     * Having expressions.
     */
    private Map<NativeExp, Operator> havingExps;

    /**
     * Order expression.
     */
    private NativeExp orderExp;

    /**
     * Limit (max result).
     */
    private Integer limit;

    /**
     * Offset (first result).
     */
    private Integer offset;

    /**
     * Distinct.
     */
    private boolean distinct;

    /**
     * Projections.
     */
    private NativeProjection projection;

    /**
     * SQL operators.
     */
    private enum Operator {
        /**
         * The AND.
         */
        AND("AND"),

        /**
         * The OR.
         */
        OR("OR");

        /**
         * The value.
         */
        private String value;

        /**
         * Instantiates a new operator.
         *
         * @param value the value
         */
        Operator(String value) {
            this.value = value;
        }

        /**
         * Gets the value.
         *
         * @return the value
         */
        public String getValue() {
            return value;
        }
    }

    /**
     * Constructor.
     *
     * @param nativeProvider native select provider
     * @param mainTable      the main table
     * @param mainAlias      the main alias
     */
    public NativeCriteria(NativeQueryProvider nativeProvider, String mainTable, String mainAlias) {
        if (nativeProvider == null) {
            throw new IllegalStateException("NativeProvider is null!");
        }
        if (Strings.isBlank(mainTable)) {
            throw new IllegalStateException("mainTable is null");
        }
        if (Strings.isBlank(mainAlias)) {
            throw new IllegalStateException("mainAlias is null");
        }

        this.nativeProvider = nativeProvider;
        this.distinct = false;

        // main table
        this.tables = new LinkedHashMap<String, String>();
        this.tables.put(mainTable, mainAlias);

        this.whereExps = new LinkedHashMap<NativeExp, Operator>();
        this.havingExps = new LinkedHashMap<NativeExp, Operator>();
        this.joins = new ArrayList<NativeExp>();
    }

    /**
     * Add table to FROM clause.
     *
     * @param tableName  the table name
     * @param tableAlias the table alias
     * @return the native criteria
     */
    public NativeCriteria addTable(String tableName, String tableAlias) {
        this.tables.put(tableName, tableAlias);
        return this;
    }

    /**
     * Set projection.
     *
     * @param projection the projection
     * @return the native criteria
     */
    public NativeCriteria setProjection(NativeProjection projection) {
        this.projection = projection;
        return this;
    }

    /**
     * Gets the projection.
     *
     * @return the projection
     */
    public NativeProjection getProjection() {
        return projection;
    }

    /**
     * <p>Add new condition to query with AND operator.</p>
     *
     * <p>Info about custom query: because it is a WHERE part of the query and could be invoked multiple times during criteria building
     * the custom SQL cannot contains the 'WHERE' clause. This clause is added automatically by NativeCriteria engine.</p>
     *
     * @param exp the exp
     * @return the native criteria
     */
    public NativeCriteria add(NativeExp exp) {
        if (exp == null) {
            throw new IllegalStateException("Object exp is null!");
        }

        whereExps.put(exp, Operator.AND);
        return this;
    }

    /**
     * Add "or" condition to query.
     *
     * @param exp the exp
     * @return the native criteria
     */
    public NativeCriteria addOr(NativeExp exp) {
        if (exp == null) {
            throw new IllegalStateException("Object exp is null!");
        }

        whereExps.put(exp, Operator.OR);
        return this;
    }

    /**
     * Add having AND.
     *
     * @param exp the exp
     * @return the native criteria
     */
    public NativeCriteria addHaving(NativeExp exp) {
        if (exp == null) {
            throw new IllegalStateException("Object exp is null!");
        }

        havingExps.put(exp, Operator.AND);
        return this;
    }

    /**
     * Add having OR.
     *
     * @param exp the exp
     * @return the native criteria
     */
    public NativeCriteria addOrHaving(NativeExp exp) {
        if (exp == null) {
            throw new IllegalStateException("Object exp is null!");
        }

        havingExps.put(exp, Operator.OR);
        return this;
    }

    /**
     * <p>Add join.</p>
     *
     * <p>If you decide to add custom SQL here please remember that custom SQL has to contain the JOIN clause at the beginning.</p>
     *
     * @param join the join
     * @return the native criteria
     */
    public NativeCriteria addJoin(NativeExp join) {
        if (join == null) {
            throw new IllegalStateException("Object exp is null!");
        }

        joins.add(join);
        return this;
    }

    /**
     * <p>Add order by.</p>
     *
     * <p>On this place could be used {@link com.github.pnowy.nc.core.expressions.NativeCustomExp}. If you decide to add custom SQL here
     * please remember that custom SQL has to contain the ORDER BY text at the beginning.
     * ORDER BY part.</p>
     *
     * @param orderExp the order exp
     * @return the native criteria
     */
    public NativeCriteria setOrder(NativeExp orderExp) {
        this.orderExp = orderExp;
        return this;
    }

    /**
     * Set distinct to query.
     *
     * @param distinct the distinct
     * @return the native criteria
     */
    public NativeCriteria setDistinct(boolean distinct) {
        this.distinct = distinct;
        return this;
    }

    /**
     * Set limitu.
     *
     * @param limit the limit
     * @return the native criteria
     */
    public NativeCriteria setLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    /**
     * Set offsetu.
     *
     * @param offset the offset
     * @return the native criteria
     */
    public NativeCriteria setOffset(Integer offset) {
        this.offset = offset;
        return this;
    }

    /**
     * Return result as {@link CriteriaResult}
     *
     * @return the criteria result
     */
    public CriteriaResult criteriaResult() {
        return new CriteriaResultImpl(list(), projection, nativeQuery.getQueryInfo());
    }

    /**
     * Fetch count without distinct.
     *
     * @param columnName the column name for row count
     * @return number of rows for given criteria
     */
    public int fetchCount(String columnName) {
        return fetchCount(columnName, false);
    }

    /**
     * Method fetch the row count for prepared criteria.
     *
     * @param columnName the column name for row count
     * @param distinct use distinct count for specified column name
     * @return number of rows for given criteria
     */
    public int fetchCount(String columnName, boolean distinct) {
        NativeExp backupOrder = this.orderExp;
        Integer backupOffset = this.offset;
        Integer backupLimit = this.limit;
        boolean backupDistinct = this.distinct;
        NativeProjection backupProjection = this.getProjection();

        this.setOrder(null);
        this.setLimit(null);
        this.setOffset(null);
        this.setDistinct(distinct);
        this.setProjection(NativeExps.projection().addAggregateProjection(columnName, NativeProjection.AggregateProjection.COUNT));
        CriteriaResult res = this.criteriaResult();

        this.setOrder(backupOrder);
        this.setOffset(backupOffset);
        this.setLimit(backupLimit);
        this.setDistinct(backupDistinct);
        this.setProjection(backupProjection);

        if (res.next()) {
            return res.getInteger(columnName);
        } else {
            throw new IllegalStateException("The fetch count did not work!");
        }
    }

    /**
     * Return result according with provided {@link CriteriaResultTransformer}.
     *
     * @param criteriaResultTransformer criteriaResultTransformer prepared criteria result transformer
     * @return transformed object by provided criteria result transformer
     */
    public <T> T criteriaResult(CriteriaResultTransformer<T> criteriaResultTransformer) {
        return criteriaResultTransformer.transform(criteriaResult());
    }

    /**
     * Return result list based on object mapper.
     *
     * @param mapper object mapper
     * @param <T>    object mapper type
     * @return result list
     */
    public <T> List<T> criteriaResult(NativeObjectMapper<T> mapper) {
        CriteriaResult criteriaResult = criteriaResult();
        List<T> result = Lists.newArrayList();
        while (criteriaResult.next()) {
            result.add(criteriaResult.getRow(mapper));
        }
        return result;
    }

    public QueryInfo getQueryInfo() {
        if (nativeQuery == null) {
            throw new IllegalStateException("You cannot get query info before execute query!");
        }

        return nativeQuery.getQueryInfo();
    }

    /**
     * Return result as objects arrays lists.
     *
     * @return the list
     */
    @SuppressWarnings("unchecked")
    public List<Object[]> list() {
        nativeQuery = buildCriteriaQuery();
        if (isLogPerformance()) {
            Stopwatch stopwatch = Stopwatch.createStarted();
            List<Object[]> res = nativeQuery.list();
            checkEndExecutionTime(stopwatch.stop());
            return res;
        } else {
            return nativeQuery.list();
        }
    }

    private boolean isLogPerformance() {
        return PERFORMANCE_LOG.isInfoEnabled();
    }

    /**
     * Unique result.
     *
     * @return the object
     */
    public Object uniqueResult() {
        nativeQuery = buildCriteriaQuery();
        if (isLogPerformance()) {
            Stopwatch stopwatch = Stopwatch.createStarted();
            Object res = nativeQuery.uniqueResult();
            checkEndExecutionTime(stopwatch.stop());
            return res;
        } else
            return nativeQuery.uniqueResult();
    }

    /**
     * Log time to logger.
     *
     * @param stopwatch stopwatch with measure tim
     */
    private void checkEndExecutionTime(Stopwatch stopwatch) {
        PERFORMANCE_LOG.info("Execution time ({}ms, - {}", stopwatch.elapsed(TimeUnit.MILLISECONDS), toSQL());
    }

    /**
     * Build object SQLQuery based on created criteria query.
     *
     * @return the sQL query
     */
    private NativeQuery buildCriteriaQuery() {
        NativeQuery nativeQuery = nativeProvider.getNativeQuery(toSQL());
        setValues(nativeQuery);

        return nativeQuery;
    }

    /**
     * Add having clause.
     *
     * @param sqlBuilder the sql builder
     */
    private void appendHavingSQL(StringBuilder sqlBuilder) {
        boolean first = true;
        if (havingExps.size() > 0) {
            sqlBuilder.append("HAVING").append(SPACE);

            for (Entry<NativeExp, Operator> exp : havingExps.entrySet()) {
                if (first) {
                    sqlBuilder.append(exp.getKey().toSQL()).append(SPACE);
                    first = false;
                } else {
                    sqlBuilder.append(exp.getValue().getValue())
                            .append(SPACE)
                            .append(exp.getKey().toSQL())
                            .append(SPACE);
                }
            }
        }
    }

    /**
     * Add group by clause (projections).
     *
     * @param sqlBuilder the sql builder
     */
    private void appendGroupBySQL(StringBuilder sqlBuilder) {
        if (projection == null) {
            return;
        }

        sqlBuilder.append(projection.groupByToSQL());
    }

    /**
     * Add order by clause.
     *
     * @param sqlBuilder the sql builder
     */
    private void appendOrderBySQL(StringBuilder sqlBuilder) {
        if (orderExp == null) {
            return;
        }

        sqlBuilder.append(orderExp.toSQL());
    }

    /**
     * Add where clause
     *
     * @param sqlBuilder the sql builder
     */
    private void appendWhereSQL(StringBuilder sqlBuilder) {
        boolean first = true;
        if (whereExps.size() > 0) {
            sqlBuilder.append("WHERE").append(SPACE);

            for (Entry<NativeExp, Operator> exp : whereExps.entrySet()) {
                NativeExp whereExpression = exp.getKey();
                if (first) {
                    sqlBuilder.append(whereExpression.toSQL()).append(SPACE);
                    first = false;
                } else {
                    Operator ope = exp.getValue();
                    sqlBuilder.append(ope.getValue()).append(SPACE).append(whereExpression.toSQL()).append(SPACE);
                }
            }
        }
    }

    /**
     * Add join.
     *
     * @param sqlBuilder the sql builder
     */
    private void appendJoinSQL(StringBuilder sqlBuilder) {
        for (NativeExp join : joins) {
            sqlBuilder.append(join.toSQL()).append(SPACE);
        }
    }

    /**
     * Add FROM clause.
     *
     * @param sqlBuilder the sql builder
     */
    private void appendFromSQL(StringBuilder sqlBuilder) {
        sqlBuilder.append(SPACE).append("FROM").append(SPACE);

        boolean first = true;
        for (Entry<String, String> from : tables.entrySet()) {
            if (first) {
                sqlBuilder.append(from.getKey()).append(SPACE).append(from.getValue());
                first = false;
            } else {
                sqlBuilder.append(", ").append(from.getKey()).append(SPACE).append(from.getValue());
            }
        }

        sqlBuilder.append(SPACE);
    }

    /**
     * Add column to sql builder.
     *
     * @param sqlBuilder the sql builder
     */
    private void appendProjectionSQL(StringBuilder sqlBuilder) {
        // if column was defined add to sql
        if (projection != null && projection.hasProjections()) {
            sqlBuilder.append(projection.projectionToSQL());
        }

        // if there is no defined projection we are adding projection automatically based on joins and tables
        if (projection == null || projection.countProjections() == 0) {
            boolean first = true;
            for (String alias : tables.values()) {
                if (first) {
                    sqlBuilder.append(alias).append(".*");
                    first = false;
                } else {
                    sqlBuilder.append(", ").append(alias).append(".*");
                }
            }
            if (joins.size() > 0) {
                for (NativeExp join : joins) {
                    // we don't execute this part of code for custom joins
                    if (join instanceof NativeJoin) {
                        NativeJoin nativeJoin = (NativeJoin) join;
                        sqlBuilder.append(", ").append(nativeJoin.getTableAlias()).append(".*");
                    }
                }
            }
        }
    }

    /**
     * Build sql query
     *
     * @return sql query
     */
    @Override
    public String toSQL() {
        StringBuilder sqlBuilder = new StringBuilder();

        // clause select i distinct
        sqlBuilder.append("SELECT").append(SPACE);
        if (distinct) {
            sqlBuilder.append("DISTINCT").append(SPACE);
        }

        // columns
        appendProjectionSQL(sqlBuilder);

        // FROM
        appendFromSQL(sqlBuilder);

        // JOIN
        appendJoinSQL(sqlBuilder);

        // WHERE
        appendWhereSQL(sqlBuilder);

        // GROUP BY (PROJECTIONS)
        appendGroupBySQL(sqlBuilder);

        // HAVING
        appendHavingSQL(sqlBuilder);

        // ORDER BY
        appendOrderBySQL(sqlBuilder);

        String sql = sqlBuilder.toString();
        LOG.debug("NativeCriteria SQL: " + sql);
        return sql;
    }

    @Override
    public void setValues(NativeQuery sqlQuery) {
        // subqueries
        if (projection != null) {
            for (NativeCriteria cr : projection.getSubqueries()) {
                cr.setValues(sqlQuery);
            }
        }

        // parameters where clause
        for (NativeExp exp : whereExps.keySet()) {
            exp.setValues(sqlQuery);
        }
        for (NativeExp exp : havingExps.keySet()) {
            exp.setValues(sqlQuery);
        }
        for (NativeExp join : joins) {
            join.setValues(sqlQuery);
        }
        if (limit != null) {
            sqlQuery.setMaxResults(limit);
        }
        if (offset != null) {
            sqlQuery.setFirstResult(offset);
        }
    }
}
