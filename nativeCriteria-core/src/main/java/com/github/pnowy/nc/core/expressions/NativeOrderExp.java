package com.github.pnowy.nc.core.expressions;

import com.github.pnowy.nc.utils.Strings;

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
         * The ASC.
         */
        ASC("ASC"),
        /**
         * The DESC.
         */
        DESC("DESC");

        /**
         * The type.
         */
        private String type;

        /**
         * Instantiates a new order type.
         *
         * @param type the type
         */
        private OrderType(String type) {
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
