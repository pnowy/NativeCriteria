package com.github.pnowy.nc.core.expressions;

import com.github.pnowy.nc.utils.Strings;
import com.google.common.base.Preconditions;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Native ORDER BY expression.
 */
public class NativeOrderExp {
    private Map<String, OrderType> orders;

    public enum OrderType {

        /**
         * The ASC sorting with default db nulls last/first behaviour.
         */
        ASC("ASC"),
        /*
         * The ASC sorting with nulls first.
         */
        ASC_NULLS_FIRST("ASC NULLS FIRST"),
        /**
         * The ASC sorting with nulls last.
         */
        ASC_NULLS_LAST("ASC NULLS LAST"),
        /**
         * The DESC sorting with default db null last/first behaviour.
         */
        DESC("DESC"),
        /**
         * The DESC sorting with nulls first.
         */
        DESC_NULLS_FIRST("DESC NULLS FIRST"),
        /**
         * The DESC sorting with nulls last.
         */
        DESC_NULLS_LAST("DESC NULLS LAST");

        /**
         * The type.
         */
        private String type;

        /**
         * Instantiates a new order type.
         *
         * @param type the type
         */
        OrderType(String type) {
            this.type = type;
        }

        /**
         * Gets the type.
         *
         * @return the type
         */
        public String getType() {
            return type;
        }
    }

    public NativeOrderExp() {
        orders = new LinkedHashMap<String, OrderType>();
    }

    /**
     * Adds the.
     *
     * @param columnName the column name
     * @param orderType  the order type
     * @return the native order exp
     */
    public NativeOrderExp add(String columnName, OrderType orderType) {
        if (Strings.isBlank(columnName)) {
            throw new IllegalStateException("columnName is null!");
        }
        if (orderType == null) {
            throw new IllegalStateException("orderType is null!");
        }

        orders.put(columnName, orderType);
        return this;
    }

    /**
     * Copy orders from different native expression.
     *
     * @param otherNativeExp different native order expression
     * @return current native order expression with added expressions with different one provied as parameter
     */
    public NativeOrderExp add(NativeOrderExp otherNativeExp) {
        Preconditions.checkNotNull(otherNativeExp);
        for (Entry<String, OrderType> otherEntry : otherNativeExp.orders.entrySet()) {
            this.add(otherEntry.getKey(), otherEntry.getValue());
        }
        return this;
    }

    /**
     * To sql.
     *
     * @return the string
     */
    public String toSQL() {
        StringBuilder order = new StringBuilder();
        if (orders.size() > 0) {
            for (Entry<String, OrderType> entry : orders.entrySet()) {
                if (order.length() > 0) {
                    order.append(", ")
                            .append(entry.getKey()).append(" ")
                            .append(entry.getValue().getType());
                } else {
                    order.append(entry.getKey()).append(" ")
                            .append(entry.getValue().getType());
                }
            }
        }

        if (order.length() > 0) {
            return "ORDER BY " + order.toString();
        } else {
            return "";
        }
    }
}
