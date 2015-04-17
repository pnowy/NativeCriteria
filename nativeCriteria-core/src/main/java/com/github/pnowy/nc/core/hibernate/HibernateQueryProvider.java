package com.github.pnowy.nc.core.hibernate;

import com.github.pnowy.nc.core.NativeQuery;
import com.github.pnowy.nc.core.NativeQueryProvider;
import org.hibernate.Session;

import javax.persistence.EntityManager;

/**
 * Default hibernate provider.
 *
 * @author Przemek Nowak [przemek.nowak.pl@gmail.com]
 */
public class HibernateQueryProvider implements NativeQueryProvider {
    private Session session;

    public HibernateQueryProvider(Session session) {
        this.session = session;
    }

    public HibernateQueryProvider(EntityManager entityManager) {
        this.session = entityManager.unwrap(Session.class);
    }

    @Override
    public NativeQuery getNativeQuery(String sql) {
        return new HibernateNativeQuery(sql, session.createSQLQuery(sql));
    }
}
