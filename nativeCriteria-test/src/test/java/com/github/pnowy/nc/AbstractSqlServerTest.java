package com.github.pnowy.nc;

import org.junit.experimental.categories.Category;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;

/**
 * Base class for SQL Server tests.
 */
@Category(SqlServerGroup.class)
@ActiveProfiles(AbstractSqlServerTest.PROFILE_SQLSERVER)
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public abstract class AbstractSqlServerTest extends AbstractDbTest {}
