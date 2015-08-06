package com.github.pnowy.nc.spring;

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
        return setPageable(pageable, OrderByApproach.DEFAULT);
    }

    public NativeCriteria setPageable(Pageable pageable, OrderByApproach orderByApproach) {
        this.pageable = Preconditions.checkNotNull(pageable);
        Preconditions.checkNotNull(orderByApproach);

        this.setOffset(pageable.getOffset());
        this.setLimit(pageable.getPageSize());

        if (pageable.getSort() != null) {
            NativeOrderExp nativeOrder = NativeExps.order();
            for (Sort.Order sort : pageable.getSort()) {
                nativeOrder.add(orderByApproach.sort(sort));
            }
            this.setOrder(nativeOrder);
        }
        return this;
    }


}
