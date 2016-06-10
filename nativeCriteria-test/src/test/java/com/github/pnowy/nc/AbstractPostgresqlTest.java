package com.github.pnowy.nc;

import org.junit.experimental.categories.Category;
import org.springframework.test.context.ActiveProfiles;

/**
 * Base class for Postgresql tests.
 */
@Category(PostgresqlGroup.class)
@ActiveProfiles(DemoApplication.PROFILE_POSTGRESQL)
public abstract class AbstractPostgresqlTest extends AbstractDbTest {}
