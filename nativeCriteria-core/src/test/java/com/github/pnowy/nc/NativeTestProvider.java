package com.github.pnowy.nc;

import com.github.pnowy.nc.core.NativeQuery;
import com.github.pnowy.nc.core.NativeQueryProvider;

/**
 * <p>
 *  Native test provider. Used for testing application without database.
 * </p>
 * Przemek Nowak [przemek.nowak.pl at gmail.com]
 */
public class NativeTestProvider implements NativeQueryProvider {
    @Override
    public NativeQuery getNativeQuery(String sql) {
        return new NativeQueryTest(sql);
    }
}
