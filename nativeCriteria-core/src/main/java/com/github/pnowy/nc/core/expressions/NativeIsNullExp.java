package com.github.pnowy.nc.core.expressions;

import com.github.pnowy.nc.core.NativeQuery;
import com.github.pnowy.nc.utils.Strings;

/**
 * Native IS NULL expression.
 */
public class NativeIsNullExp implements NativeExp {
    /**
     * Column name.
     */
    private String columnName;

    /**
     * Instantiates a new native is null exp.
     *
     * @param columnName the column name
     */
    public NativeIsNullExp(String columnName) {
        if (Strings.isBlank(columnName))
            throw new IllegalStateException("columnName is null!");

        this.columnName = columnName;
    }

    @Override
    public String toSQL() {
        return columnName + " IS NULL";
    }

    @Override
    public void setValues(NativeQuery query) {
        // ----------
    }
}
