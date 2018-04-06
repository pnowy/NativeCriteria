package com.github.pnowy.nc.core.expressions;

/**
 * Simple wrapper for projection column name and alias.
 */
public class ProjectionColumn {
    private final String columnName;
    private final String alias;

    public ProjectionColumn(String columnName, String alias) {
        this.columnName = columnName;
        this.alias = alias;
    }

    public String getColumnName() {
        return columnName;
    }

    public String getAlias() {
        return alias;
    }
}
