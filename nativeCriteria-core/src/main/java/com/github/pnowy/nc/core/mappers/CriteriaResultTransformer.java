package com.github.pnowy.nc.core.mappers;

import com.github.pnowy.nc.core.CriteriaResult;

/**
 * Criteria result transformer interface.
 *
 * @author Przemek Nowak
 */
public interface CriteriaResultTransformer<T> {
    T transform(CriteriaResult criteriaResult);
}
