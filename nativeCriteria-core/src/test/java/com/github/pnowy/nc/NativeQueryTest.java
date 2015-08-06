package com.github.pnowy.nc;

import com.beust.jcommander.internal.Lists;
import com.github.pnowy.nc.core.NativeQuery;
import com.github.pnowy.nc.core.QueryInfo;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 *  Native test implementation of query.
 * </p>
 * <p>
 *  Doesn't work on database, only register generated sql. Used for testing application.
 * </p>
 *
 * Przemek Nowak [przemek.nowak.pl at gmail.com]
 */
public class NativeQueryTest implements NativeQuery {
    private QueryInfo queryInfo;

    public NativeQueryTest(String sql) {
        this.queryInfo = new QueryInfo(sql);
    }

    @Override
    public List list() {
        return Lists.newArrayList();
    }

    @Override
    public Object uniqueResult() {
        return new Object();
    }

    @Override
    public NativeQuery setMaxResults(int maxResults) {
        return this;
    }

    @Override
    public NativeQuery setFirstResult(int firstResult) {
        return this;
    }

    @Override
    public NativeQuery setParameter(String name, Object val) {
        queryInfo.getParameters().put(name, val);
        return this;
    }

    @Override
    public NativeQuery setString(String name, String value) {
        queryInfo.getParameters().put(name, value);
        return this;
    }

    @Override
    public NativeQuery setParameterList(String name, Collection values) {
        for (Object o : values) {
            String objectName = name + "[" + o.toString() + "]";
            queryInfo.getParameters().put(objectName, o);
        }
        return this;
    }

    @Override
    public NativeQuery setParameterList(String name, Object[] values) {
        for (Object o : values) {
            String objectName = name + "[" + o.toString() + "]";
            queryInfo.getParameters().put(objectName, o);
        }
        return this;
    }

    @Override
    public QueryInfo getQueryInfo() {
        return queryInfo;
    }
}
