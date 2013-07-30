package com.nc;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;

/**
 * Date: 30.07.13 20:32
 */
@ContextConfiguration(locations={"classpath*:ds/nc-beans.xml",
		"classpath*:ds/test-persistence.xml"})
public class GenericTest extends AbstractTransactionalTestNGSpringContextTests {
}
