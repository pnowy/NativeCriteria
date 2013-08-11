package com.github.pnowy.nc.expressions;

import com.github.pnowy.nc.NativeTestProvider;
import com.github.pnowy.nc.core.CriteriaResult;
import com.github.pnowy.nc.core.NativeCriteria;
import com.github.pnowy.nc.core.QueryInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Map;

/**
 * Przemek Nowak <przemek.nowak.pl@gmail.com>
 * Date: 07.08.13 21:17
 */
public abstract class NativeExpGenericTest {
	protected static final Logger log = LoggerFactory.getLogger(NativeExpGenericTest.class);
	protected final String MAIN_TABLE = "mainTable";
	protected final String MAIN_ALIAS = "mt";
	protected final String MAIN_ALIAS_WITH_DOT = "mt.";

	protected NativeCriteria nc;
	protected String sql;
	protected Map<String, Object> parameters;

	@BeforeMethod
	public void setUp() throws Exception
	{
		nc = new NativeCriteria(new NativeTestProvider(), MAIN_TABLE, MAIN_ALIAS);
	}

	@Test
	protected void testMethod() {
		prepareCriteria();
		CriteriaResult criteriaResult = nc.criteriaResult();
		QueryInfo qi = criteriaResult.getQueryInfo();
		sql = qi.getSql().toLowerCase();
		parameters = qi.getParameters();
		checkConditions();
		log.info("{}: {}",getClassName(), nc.getQueryInfo().getSummary());
	}

	protected abstract void prepareCriteria();
	protected abstract void checkConditions();

	protected String getClassName()
	{
		return getClass().getSimpleName();
	}
}
