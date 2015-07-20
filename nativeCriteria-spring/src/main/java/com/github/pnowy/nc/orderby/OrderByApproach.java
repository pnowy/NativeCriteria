package com.github.pnowy.nc.orderby;

import com.github.pnowy.nc.core.NativeExps;
import com.github.pnowy.nc.core.expressions.NativeOrderExp;
import org.springframework.data.domain.Sort;

/**
 *
 * Enum determine the way how the ordering should be performed.
 *
 * @author Przemek Nowak [przemek.nowak.pl@gmail.com]
 */
public enum OrderByApproach {
    /**
     * Default sort order (ASC for ASC direction, DESC for DESC direction).
     */
    DEFAULT() {
        @Override
        public NativeOrderExp sort(Sort.Order sortOrder) {
            return NativeExps.order().add(sortOrder.getProperty(), NativeOrderExp.OrderType.valueOf(sortOrder.getDirection().name()));
        }
    },
    /**
     * Sort order where ASC ordering is performed with nulls first and DESC ordering is performed with nulls last.
     */
    ASC_NULLS_FIRST_DESC_NULL_LAST() {
        @Override
        public NativeOrderExp sort(Sort.Order sortOrder) {
            NativeOrderExp.OrderType orderTypeWithNulls =
                    NativeOrderExp.OrderType.valueOf(sortOrder.getDirection().name()) == NativeOrderExp.OrderType.ASC
                    ? NativeOrderExp.OrderType.ASC_NULLS_FIRST
                    : NativeOrderExp.OrderType.DESC_NULLS_LAST;
            return NativeExps.order().add(sortOrder.getProperty(), orderTypeWithNulls);
        }
    };

    public abstract NativeOrderExp sort(Sort.Order sortOrder);

}
