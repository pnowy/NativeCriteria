package com.github.pnowy.nc.core.mappers;

import com.github.pnowy.nc.core.CriteriaResult;

/**
 * Criteria result transformer interface.
 *
 * Przemek Nowak <przemek.nowak.pl@gmail.com>
 * Date: 09.02.14 15:45
 */
public interface CriteriaResultTransformer<T> {
	T transform(CriteriaResult criteriaResult);
}
