package com.github.pnowy.nc.core.mappers;

import com.github.pnowy.nc.core.CriteriaResult;

/**
 * Iterface to map object from criteria result.
 *
 * @author Przemek Nowak
 */
public interface NativeObjectMapper<T> {
    T mapObject(CriteriaResult cr);
}
