package com.github.pnowy.nc.core.mappers;

import com.github.pnowy.nc.core.CriteriaResult;

/**
 *
 * Iterface to map object from criteria result.
 *
 * Przemek Nowak <przemek.nowak.pl@gmail.com>
 * Date: 15.08.13 23:32
 */
public interface NativeObjectMapper<T> {
	public T mapObject(CriteriaResult cr);
}
