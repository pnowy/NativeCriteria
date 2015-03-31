package com.github.pnowy.nc.utils;

import com.github.pnowy.nc.core.NativeQueryProvider;

/**
 * Simple interface to test selects on database.
 * <p/>
 * Przemek Nowak <przemek.nowak.pl@gmail.com>
 * Date: 12.08.13 22:44
 */
public interface Transactional {
    public void transaction(NativeQueryProvider provider);
}
