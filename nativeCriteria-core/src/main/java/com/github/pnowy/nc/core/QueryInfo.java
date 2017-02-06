package com.github.pnowy.nc.core;

import com.github.pnowy.nc.utils.Objects;

import java.util.HashMap;
import java.util.Map;

/**
 * Class contain some information about executed query. Available information:
 * <ul>
 *  <li>sql</li>
 *  <li>map with parameters</li>
 * </ul>
 */
public class QueryInfo {
    private String sql;
    private Map<String, Object> parameters;

    public QueryInfo(String sql) {
        this.sql = sql;
        this.parameters = new HashMap<>();
    }

    public String getSql() {
        return sql;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    /**
     * Class returns summary of executed query.
     *
     * @return description which contains the following information about query:
     *      <ul>
     *          <li>sql</li>
     *          <li>map with parameters</li>
     *      </ul>
     */
    public String getSummary() {
        StringBuilder sb = new StringBuilder("QueryInfo:\n");
        sb.append("\tSQL: ").append(sql).append("\n");
        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
            sb.append("\tParameter name: ").append(entry.getKey()).append(", parameter value: ").append(Objects.toString(entry.getValue()));
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return getSummary();
    }
}
