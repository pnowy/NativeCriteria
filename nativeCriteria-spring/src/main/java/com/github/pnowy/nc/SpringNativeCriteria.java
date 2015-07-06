package com.github.pnowy.nc;

import com.github.pnowy.nc.core.NativeCriteria;
import com.github.pnowy.nc.core.NativeExps;
import com.github.pnowy.nc.core.NativeQueryProvider;
import com.github.pnowy.nc.core.expressions.NativeOrderExp;
import com.google.common.base.Preconditions;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * @author Przemek Nowak [przemek.nowak.pl@gmail.com]
 */
public class SpringNativeCriteria extends NativeCriteria {

    private Pageable pageable;

    /**
     * Constructor.
     *
     * @param nativeProvider native select provider
     * @param mainTable      the main table
     * @param mainAlias      the main alias
     */
    public SpringNativeCriteria(NativeQueryProvider nativeProvider, String mainTable, String mainAlias) {
        super(nativeProvider, mainTable, mainAlias);
    }


    public NativeCriteria setPageable(Pageable pageable) {
        this.pageable = Preconditions.checkNotNull(pageable);
        this.setOffset(pageable.getOffset());
        this.setLimit(pageable.getPageSize());
        if (pageable.getSort() != null) {
            for (Sort.Order springOrder : pageable.getSort()) {
                this.setOrder(NativeExps.order().add(springOrder.getProperty(), NativeOrderExp.OrderType.valueOf(springOrder.getDirection().name())));
            }
        }
        return this;
    }


}
