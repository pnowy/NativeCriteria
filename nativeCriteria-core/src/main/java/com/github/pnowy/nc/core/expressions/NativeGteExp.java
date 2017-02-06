package com.github.pnowy.nc.core.expressions;

import com.github.pnowy.nc.core.NativeQuery;
import com.github.pnowy.nc.utils.Strings;
import com.github.pnowy.nc.utils.VarGenerator;

/**
 * Greater or equal native expression.
 */
public class NativeGteExp implements NativeExp {
    /**
     * Column name.
     */
    private String columnName;
    private String varName;

    /**
     * Compared value.
     */
    private Object value;

    /**
     * @param columnName the column name
     * @param value      the value
     */
    public NativeGteExp(String columnName, Object value) {
        if (Strings.isBlank(columnName))
            throw new IllegalStateException("columnName is null!");
        if (value == null)
            throw new IllegalStateException("value is null!");

        this.columnName = columnName;
        this.value = value;
    }

    @Override
    public String toSQL() {
        varName = VarGenerator.gen(columnName);
        return columnName + " >= :" + varName;
    }

    @Override
    public void setValues(NativeQuery query) {
        query.setParameter(varName, value);
    }
}
