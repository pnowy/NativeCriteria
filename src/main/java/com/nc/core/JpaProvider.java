package com.nc.core;

import javax.persistence.EntityManager;

/**
 * JPA Query provider.
 */
public class JpaProvider implements NativeProvider
{
	private EntityManager em;

	public JpaProvider(EntityManager em)
	{
		this.em = em;
	}

	@Override
	public QueryProvider getQueryProvider(String sql)
	{
		return new JpaQueryProvider(em.createNativeQuery(sql));
	}
}
