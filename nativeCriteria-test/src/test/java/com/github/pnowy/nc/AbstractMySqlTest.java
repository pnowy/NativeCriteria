package com.github.pnowy.nc;

import org.junit.experimental.categories.Category;
import org.springframework.test.context.ActiveProfiles;

/**
 * Base class for MySQL tests.
 */
@Category(MySqlGroup.class)
@ActiveProfiles(DemoApplication.PROFILE_MYSQL)
public abstract class AbstractMySqlTest extends AbstractDbTest {}
