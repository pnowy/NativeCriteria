package com.github.pnowy.nc;

import org.junit.experimental.categories.Category;
import org.springframework.test.context.ActiveProfiles;

/**
 * Base class for SQL Server tests.
 */
@Category(SqlServerGroup.class)
@ActiveProfiles(DemoApplication.PROFILE_SQLSERVER)
public abstract class AbstractSqlServerTest extends AbstractDbTest {}
