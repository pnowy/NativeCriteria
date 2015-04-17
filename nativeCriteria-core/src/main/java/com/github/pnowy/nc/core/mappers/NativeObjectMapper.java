package com.github.pnowy.nc.core.mappers;

import com.github.pnowy.nc.core.CriteriaResult;

/**
 * Iterface to map object from criteria result.
 * <p/>
 * Przemek Nowak [przemek.nowak.pl@gmail.com]
 */
public interface NativeObjectMapper<T> {
    T mapObject(CriteriaResult cr);
}
