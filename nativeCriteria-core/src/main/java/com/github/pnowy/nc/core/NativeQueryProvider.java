package com.github.pnowy.nc.core;

/**
 * Interface for native provider which supply engine to execute query.
 */
public interface NativeQueryProvider {
    /**
     * Get native query implementation based on build sql query by NativeCriteria class.
     *
     * @param buildSql pure sql query
     * @return native query implementation
     */
    NativeQuery getNativeQuery(String buildSql);
}
