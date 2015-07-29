package com.github.pnowy.nc.core;

import java.util.Collection;
import java.util.List;

/**
 * Native query interface.
 */
public interface NativeQuery {
    List list();

    Object uniqueResult();

    NativeQuery setMaxResults(int maxResults);

    NativeQuery setFirstResult(int firstResult);

    NativeQuery setParameter(String name, Object val);

    NativeQuery setString(String name, String value);

    NativeQuery setParameterList(String name, Collection values);

    NativeQuery setParameterList(String name, Object[] values);

    /**
     * Method return information about done query.
     *
     * @return query information
     */
    QueryInfo getQueryInfo();
}
