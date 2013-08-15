package com.github.pnowy.nc.si;

import com.github.pnowy.nc.core.CriteriaResult;
import com.github.pnowy.nc.core.NativeCriteria;
import com.github.pnowy.nc.core.hibernate.HibernateQueryProvider;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Simple select test.
 *
 * Przemek Nowak <przemek.nowak.pl@gmail.com>
 * Date: 30.07.13 17:41
 */
public class SelectTest extends GenericTest
{
	@PersistenceContext
	EntityManager em;

	@Test
	public void selectTest()
	{

		NativeCriteria nc = new NativeCriteria(new HibernateQueryProvider(em), "ADDRESS", "a");
		//nc.setProjection(NativeExps.projection().addProjection("id", "city", "street"));
		CriteriaResult result = nc.criteriaResult();
		while (result.next()) {
			System.out.println(result.getCurrentRecordDesc());
		}
	}
}
