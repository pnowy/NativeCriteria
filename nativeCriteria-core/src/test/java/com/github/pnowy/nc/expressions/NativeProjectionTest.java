package com.github.pnowy.nc.expressions;

import com.github.pnowy.nc.NativeTestProvider;
import com.github.pnowy.nc.core.NativeCriteria;
import com.github.pnowy.nc.core.NativeExps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

/**
 * Przemek Nowak <przemek.nowak.pl@gmail.com>
 * Date: 01.08.13 21:07
 */
public class NativeProjectionTest
{
	private static final Logger log = LoggerFactory.getLogger(NativeProjectionTest.class);

	@Test
	public void testSimpleProjection() throws Exception
	{
		NativeCriteria nc = new NativeCriteria(new NativeTestProvider(), "mainTable", "mt");
		nc.setProjection(NativeExps.projection().addProjection("twojaStara", "tw"));
		nc.list();


		log.info("QueryInfo: {}", nc.getQueryInfo().getSql());

	}
}
