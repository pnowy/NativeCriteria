package com.nc.select;

import com.nc.core.CriteriaResult;
import com.nc.core.HibernateProvider;
import com.nc.core.NativeCriteria;
import com.nc.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.context.internal.ThreadLocalSessionContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Przemek Nowak <przemek.nowak.pl@gmail.com> Date: 30.07.13 17:41
 */
public class SelectTest // extends GenericTest
{
	@BeforeClass
	public void setUp() throws Exception {

	}

	@Test(enabled = false)
	public void selectTest() {

		Session s = HibernateUtil.getSessionFactory().getCurrentSession();

		s.beginTransaction();

		NativeCriteria nc = new NativeCriteria(new HibernateProvider(s), "TEST_TABLE", "s");
		// NativeCriteria nc = new NativeCriteria(new JpaProvider(em), "TEST_TABLE", "s");
		// nc.setProjection(NativeExps.projection().addProjection("id"));
		// nc.setLimit(12);
		nc.setOffset(2);
		// nc.add(NativeExps.eq("column_name", 15));
		CriteriaResult result = nc.criteriaResult();
		while(result.next()) {
			System.out.println(result.getLong(0, 0l) + " | " + result.getString(1, null));
		}

		s.getTransaction().commit();

		ThreadLocalSessionContext.unbind(HibernateUtil.getSessionFactory());
	}
}
