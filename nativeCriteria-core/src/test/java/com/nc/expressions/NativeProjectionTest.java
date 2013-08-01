package com.nc.expressions;

import com.nc.NativeTestProvider;
import com.nc.core.NativeCriteria;
import com.nc.core.NativeExps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

/**
 * Przemek Nowak <przemek.nowak.pl@gmail.com>
 * Date: 01.08.13 21:07
 */
public class NativeProjectionTest {
	private static final Logger log = LoggerFactory.getLogger(NativeProjectionTest.class);
	@Test
	public void testSimpleProjection() throws Exception
	{
		log.info("test one");
		NativeCriteria nc = new NativeCriteria(new NativeTestProvider(), "mainTable", "mt");
		nc.setProjection(NativeExps.projection().addProjection("mt.one", "mt.two"));
		nc.list();

		log.info("test two");

	}
}
