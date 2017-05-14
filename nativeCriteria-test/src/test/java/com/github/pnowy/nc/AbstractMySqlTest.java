package com.github.pnowy.nc;

import org.junit.experimental.categories.Category;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;

/**
 * Base class for MySQL tests.
 */
@Category(MySqlGroup.class)
@ActiveProfiles(DemoApplication.PROFILE_MYSQL)
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public abstract class AbstractMySqlTest extends AbstractDbTest {}
