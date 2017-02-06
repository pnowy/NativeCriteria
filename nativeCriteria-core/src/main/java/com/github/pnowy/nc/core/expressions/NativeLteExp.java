package com.github.pnowy.nc.core.expressions;

import com.github.pnowy.nc.core.NativeQuery;
import com.github.pnowy.nc.utils.Strings;
import com.github.pnowy.nc.utils.VarGenerator;

/**
 * The Class NativeLteExp.
 */
public class NativeLteExp implements NativeExp {
    private String columnName;
    private String varName;
    private Object value;

    /**
     * @param columnName the column name
     * @param value      the value
     */
    public NativeLteExp(String columnName, Object value) {
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
        return columnName + " <= :" + varName;
    }

    @Override
    public void setValues(NativeQuery query) {
        query.setParameter(varName, value);
    }

}
