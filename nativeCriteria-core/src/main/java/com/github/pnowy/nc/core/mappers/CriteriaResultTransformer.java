package com.github.pnowy.nc.core.mappers;

import com.github.pnowy.nc.core.CriteriaResult;

/**
 * Criteria result transformer interface.
 *
 * Przemek Nowak [przemek.nowak.pl@gmail.com]
 */
public interface CriteriaResultTransformer<T> {
	T transform(CriteriaResult criteriaResult);
}
