package com.github.pnowy.nc.core.jpa;

import com.github.pnowy.nc.core.NativeQuery;
import com.github.pnowy.nc.core.NativeQueryProvider;

import javax.persistence.EntityManager;

/**
 * JPA Query provider.
 */
@SuppressWarnings("unused")
public class JpaQueryProvider implements NativeQueryProvider {
    private EntityManager em;

    public JpaQueryProvider(EntityManager em) {
        this.em = em;
    }

    @Override
    public NativeQuery getNativeQuery(String sql) {
        return new JpaNativeQuery(sql, em.createNativeQuery(sql));
    }
}
