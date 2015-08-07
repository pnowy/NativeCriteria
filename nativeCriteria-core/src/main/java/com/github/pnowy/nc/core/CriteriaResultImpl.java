package com.github.pnowy.nc.core;

import com.github.pnowy.nc.core.expressions.NativeProjection;
import com.github.pnowy.nc.core.mappers.NativeObjectMapper;
import com.github.pnowy.nc.utils.Objects;
import com.google.common.base.Joiner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Implementation CriteriaResult.
 */
public class CriteriaResultImpl implements CriteriaResult {
    private QueryInfo queryInfo;
    /**
     * Query results.
     */
    private List<Object[]> results;

    /**
     * Record index.
     */
    private int recordIdx = -1;

    /**
     * Projections.
     */
    private NativeProjection projection;

    /**
     * Constructor.
     *
     * @param results    the results
     * @param projection the projection
     * @param queryInfo  query info
     */
    public CriteriaResultImpl(List<Object[]> results, NativeProjection projection, QueryInfo queryInfo) {
        if (results == null)
            throw new IllegalStateException("Results is null!");
        if (queryInfo == null)
            throw new IllegalStateException("QueryInfo is null!");

        this.results = results;
        this.queryInfo = queryInfo;
        this.projection = projection;
    }

    /**
     * Check is next record available.
     *
     * @return true, if successful
     */
    @Override
    public boolean next() {
        recordIdx++;
        return recordIdx < results.size();
    }

    @Override
    public Boolean getBoolean(int idx, Boolean defaultResult) {
        Object val = getValue(idx, null);
        if (val == null)
            return defaultResult;

        Boolean res;
        if (val instanceof String) {
            res = Boolean.valueOf(val.toString());
        } else if (val instanceof Boolean) {
            res = (Boolean) val;
        } else {
            String v = val.toString();
            res = v.equals("1");
        }

        return res;
    }

    @Override
    public Boolean getBoolean(int idx) {
        return getBoolean(idx, null);
    }

    @Override
    public Date getDate(int idx, Date defaultResult) {
        Object val = getValue(idx, null);
        if (val == null)
            return defaultResult;

        return (Date) val;
    }

    @Override
    public Date getDate(int idx) {
        return getDate(idx, null);
    }

    @Override
    public Integer getInteger(int idx, Integer defaultResult) {
        Object val = getValue(idx, null);
        if (val == null)
            return defaultResult;

        return Integer.parseInt(val.toString());
    }

    @Override
    public Integer getInteger(int idx) {
        return getInteger(idx, null);
    }

    @Override
    public Double getDouble(int idx, Double defaultResult) {
        Object val = getValue(idx, null);
        if (val == null)
            return defaultResult;

        return Double.valueOf(val.toString());
    }

    @Override
    public Double getDouble(int idx) {
        return getDouble(idx, null);
    }

    @Override
    public BigDecimal getBigDecimal(int idx) {
        return getBigDecimal(idx, null);
    }

    @Override
    public BigDecimal getBigDecimal(int idx, BigDecimal defaultResult) {
        Object val = getValue(idx, null);
        if (val == null)
            return defaultResult;

        return (BigDecimal) val;
    }

    @Override
    public Double getDouble(String columnName, Double defaultResult) {
        Object val = getValue(columnName, null);
        if (val == null)
            return defaultResult;

        return Double.valueOf(val.toString());
    }

    @Override
    public Double getDouble(String columnName) {
        return getDouble(columnName, null);
    }

    @Override
    public BigDecimal getBigDecimal(String columnName) {
        return getBigDecimal(columnName, null);
    }

    @Override
    public BigDecimal getBigDecimal(String columnName, BigDecimal defaultResult) {
        Object val = getValue(columnName);
        if (val == null)
            return defaultResult;

        return (BigDecimal) val;
    }

    @Override
    public Long getLong(int idx, Long defaultResult) {
        Object val = getValue(idx, null);
        if (val == null)
            return defaultResult;

        return Long.parseLong(val.toString());
    }

    @Override
    public Long getLong(int idx) {
        return getLong(idx, null);
    }

    @Override
    public Short getShort(int idx, Short defaultResult) {
        Object val = getValue(idx, null);
        if (val == null)
            return defaultResult;

        return Short.parseShort(val.toString());
    }

    @Override
    public Short getShort(int idx) {
        return getShort(idx, null);
    }

    @Override
    public String getString(int idx, String defaultResult) {
        Object val = getValue(idx, null);
        if (val == null)
            return defaultResult;

        return val.toString();
    }

    @Override
    public String getString(int idx) {
        return getString(idx, null);
    }

    /**
     * Preconditions to check actual record.
     */
    private void checkCurrentRecordIdx() {
        if (recordIdx == -1 || recordIdx >= results.size())
            throw new IndexOutOfBoundsException("Record out of range!. RecordIdx=" + recordIdx + ", recordSize=" + results.size());
    }

    private boolean hasMultipleProjection() {
        return projection == null || projection.countProjections() > 1;
    }

    @Override
    public String getCurrentRecordDesc() {
        checkCurrentRecordIdx();

        if (hasMultipleProjection()) {
            Object[] row = results.get(recordIdx);
            return Joiner.on(" | ").useForNull("null").join(row);
        } else {
            return Objects.toString(results.get(recordIdx));
        }
    }

    @Override
    public <T> T getRow(NativeObjectMapper<T> mapper) {
        return mapper.mapObject(this);
    }

    @Override
    public Object getValue(int idx, Object defaultResult) {
        checkCurrentRecordIdx();

        if (hasMultipleProjection()) {
            Object[] row = results.get(recordIdx);
            if (idx < 0 || idx >= row.length) {
                throw new IndexOutOfBoundsException("Value out of range in record!");
            }

            if (row[idx] == null)
                return defaultResult;

            return row[idx];
        } else {
            Object val = results.get(recordIdx);
            if (val == null)
                return defaultResult;

            return val;
        }
    }

    @Override
    public Object getValue(int idx) {
        return getValue(idx, null);
    }

    @Override
    public Integer getRowsNumber() {
        return results.size();
    }

    @Override
    public QueryInfo getQueryInfo() {
        return queryInfo;
    }

    @Override
    public Boolean getBoolean(String columnName, Boolean defaultResult) {
        if (projection == null)
            throw new IllegalStateException(
                    "Only supported method with a fixed projection!");

        return getBoolean(projection.getProjectionIndex(columnName), defaultResult);
    }

    @Override
    public Boolean getBoolean(String columnName) {
        return getBoolean(columnName, null);
    }

    @Override
    public Date getDate(String columnName, Date defaultResult) {
        if (projection == null)
            throw new IllegalStateException(
                    "Only supported method with a fixed projection!");
        return getDate(projection.getProjectionIndex(columnName), defaultResult);
    }

    @Override
    public Date getDate(String columnName) {
        return getDate(columnName, null);
    }

    @Override
    public Integer getInteger(String columnName, Integer defaultResult) {
        if (projection == null)
            throw new IllegalStateException(
                    "Only supported method with a fixed projection!");
        return getInteger(projection.getProjectionIndex(columnName), defaultResult);
    }

    @Override
    public Integer getInteger(String columnName) {
        return getInteger(columnName, null);
    }

    @Override
    public Long getLong(String columnName, Long defaultResult) {
        if (projection == null)
            throw new IllegalStateException(
                    "Only supported method with a fixed projection!");
        return getLong(projection.getProjectionIndex(columnName), defaultResult);
    }

    @Override
    public Long getLong(String columnName) {
        return getLong(columnName, null);
    }

    @Override
    public Short getShort(String columnName, Short defaultResult) {
        if (projection == null)
            throw new IllegalStateException(
                    "Only supported method with a fixed projection!");
        return getShort(projection.getProjectionIndex(columnName), defaultResult);
    }

    @Override
    public Short getShort(String columnName) {
        return getShort(columnName, null);
    }

    @Override
    public String getString(String columnName, String defaultResult) {
        if (projection == null)
            throw new IllegalStateException(
                    "Only supported method with a fixed projection!");
        return getString(projection.getProjectionIndex(columnName), defaultResult);
    }

    @Override
    public String getString(String columnName) {
        return getString(columnName, null);
    }

    @Override
    public Object getValue(String columnName, Object defaultResult) {
        if (projection == null)
            throw new IllegalStateException(
                    "Only supported method with a fixed projection!");
        return getValue(projection.getProjectionIndex(columnName), defaultResult);
    }

    @Override
    public Object getValue(String columnName) {
        return getValue(columnName, null);
    }
}
