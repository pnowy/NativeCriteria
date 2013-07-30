package com.nc.select;

import com.nc.core.CriteriaResult;
import com.nc.core.HibernateProvider;
import com.nc.core.NativeCriteria;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Przemek Nowak <przemek.nowak.pl@gmail.com>
 * Date: 30.07.13 17:41
 */
public class SelectTest //extends GenericTest
{
	@PersistenceContext
	EntityManager em;

	@BeforeClass
	public void setUp() throws Exception {


	}

	@Test
	public void selectTest()
	{

		NativeCriteria nc = new NativeCriteria(new HibernateProvider(em), "TEST_TABLE", "s");
//		NativeCriteria nc = new NativeCriteria(new JpaProvider(em), "TEST_TABLE", "s");
		//nc.setProjection(NativeExps.projection().addProjection("id"));
		//nc.setLimit(12);
		nc.setOffset(2);
		//nc.add(NativeExps.eq("column_name", 15));
		CriteriaResult result = nc.criteriaResult();
		while (result.next()) {
			System.out.println(result.getLong(0, 0l) + " | "+result.getString(1,null));
		}
	}
}
