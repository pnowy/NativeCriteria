package com.github.pnowy.nc.core.expressions;

import com.github.pnowy.nc.core.NativeQuery;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * <p>Native custom user SQL expression.</p>
 *
 * <p>In order to use SQL query parameters you have to put parameter variable name within query according to hibernate convention
 * (the name with colon at the beginning, something like  ':exampleVar') and define the value which should be assign during query execution.
 * </p>
 */
public class NativeCustomExp implements NativeExp {

    private String sql;

    /**
     * Values. The var name in the query is the key and the value is query value.
     */
    private Map<String, Object> values = new LinkedHashMap<String, Object>();

    /**
     * Create custom SQL query without dynamic parameters.
     *
     * @param sql sql custom query
     */
    public NativeCustomExp(String sql) {
        if (sql == null) {
            throw new NullPointerException("SQL custom query part cannot be null!");
        }
        this.sql = checkNotNull(sql);
    }

    /**
     * Create custom SQL query with single dynamic parameter.
     *
     * @param sql sql custom query
     * @param varName variable name (important: without the colon at the beginning, the colon should be used only within the query)
     * @param value tha value to assign
     */
    public NativeCustomExp(String sql, String varName, Object value) {
        this(sql);
        this.values.put(varName, value);
    }

    /**
     * Create custom SQL with multiple dynamics parameters.
     *
     * @param sql sql custom query
     * @param values the map with the variable names (important: without the colon at the beginning) and the values to assign
     */
    public NativeCustomExp(String sql, Map<String, Object> values) {
        this(sql);
        this.values = values;
    }

    @Override
    public String toSQL() {
        return " " + sql+ " ";
    }

    @Override
    public void setValues(NativeQuery query) {
        for (Map.Entry<String, Object> entry : values.entrySet()) {
            Object value = entry.getValue();
            if (value instanceof Collection) {
                query.setParameterList(entry.getKey(), (Collection) value);
            } else if (value instanceof Object[]) {
                query.setParameterList(entry.getKey(), (Object[]) value);
            } else {
                query.setParameter(entry.getKey(), value);
            }
        }
    }

}
