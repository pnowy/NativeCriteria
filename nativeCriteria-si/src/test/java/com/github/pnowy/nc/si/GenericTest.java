package com.github.pnowy.nc.si;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;

/**
 * Date: 30.07.13 20:32
 */
@ContextConfiguration(locations={"classpath*:ds/nc-si-beans.xml", "classpath*:ds/nc-si-persistence.xml"})
public class GenericTest extends AbstractTransactionalTestNGSpringContextTests {
}
