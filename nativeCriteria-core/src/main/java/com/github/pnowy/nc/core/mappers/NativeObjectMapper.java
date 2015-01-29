package com.github.pnowy.nc.core.mappers;

import com.github.pnowy.nc.core.CriteriaResult;

/**
 *
 * Iterface to map object from criteria result.
 *
 * Przemek Nowak [przemek.nowak.pl@gmail.com]
 */
public interface NativeObjectMapper<T> {
	public T mapObject(CriteriaResult cr);
}
