package com.github.pnowy.nc.core.expressions;

import com.github.pnowy.nc.core.NativeQuery;
import com.github.pnowy.nc.utils.Strings;
import com.github.pnowy.nc.utils.VarGenerator;

/**
 * Native NOT ILIKE expression (not case sensitive like for some databases).
 */
public class NativeNotILikeExp implements NativeExp {
    private String columnName;
    private String varName;
    private String value;

    /**
     * @param columnName the column name
     * @param value      the value
     */
    public NativeNotILikeExp(String columnName, String value) {
        if (Strings.isBlank(columnName))
            throw new IllegalStateException("columnName is null!");
        if (Strings.isBlank(value))
            throw new IllegalStateException("value is null!");

        this.columnName = columnName;
        this.value = value;
    }

    @Override
    public String toSQL() {
        varName = VarGenerator.gen(columnName);
        return columnName + " NOT ILIKE :" + varName;
    }

    @Override
    public void setValues(NativeQuery query) {
        query.setParameter(varName, value);
    }

}
