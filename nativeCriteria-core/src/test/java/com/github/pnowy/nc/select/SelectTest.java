package com.github.pnowy.nc.select;

import com.github.pnowy.nc.core.CriteriaResult;
import com.github.pnowy.nc.core.NativeCriteria;
import com.github.pnowy.nc.core.NativeExps;
import com.github.pnowy.nc.core.NativeQueryProvider;
import com.github.pnowy.nc.utils.HibernateUtil;
import com.github.pnowy.nc.utils.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import static org.fest.assertions.Assertions.assertThat;

/**
 * Simple database test.
 * Przemek Nowak <przemek.nowak.pl@gmail.com> Date: 30.07.13 17:41
 */
public class SelectTest implements Transactional
{
	private static final Logger log = LoggerFactory.getLogger(SelectTest.class);

	@Override
	public void transaction(NativeQueryProvider provider)
	{
		NativeCriteria nc = new NativeCriteria(provider, "ADDRESS", "a");
		nc.setProjection(NativeExps.projection().addProjection("id"));
		nc.add(NativeExps.eq("a.city", "WARSAW"));
		CriteriaResult result = nc.criteriaResult();
		while(result.next())
		{
			Long id = result.getLong("id");
			assertThat(id).isNotNull();
		}
		assertThat(result.getRowsNumber()).isGreaterThan(0);
	}

	@Test
	public void test()
	{
		HibernateUtil.executeTransaction(this);
	}
}
