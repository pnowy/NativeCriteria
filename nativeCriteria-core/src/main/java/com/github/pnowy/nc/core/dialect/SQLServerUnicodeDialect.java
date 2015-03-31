package com.github.pnowy.nc.core.dialect;

import org.hibernate.dialect.SQLServerDialect;

import java.sql.Types;

/**
 * Additional dialect which provide the possibility to use native hibernate queries with NVARCHAR.
 * The standard Hibernate dialect does not support NVARCHAR.
 */
@SuppressWarnings("unused")
public class SQLServerUnicodeDialect extends SQLServerDialect {

    public static final long MAX_LENGTH = 2048;

    public SQLServerUnicodeDialect() {
        registerColumnType(Types.BIGINT, "bigint");
        registerColumnType(Types.BIT, "bit");
        registerColumnType(Types.CHAR, "nchar(1)");
        registerColumnType(Types.VARCHAR, 4000, "nvarchar($l)");
        registerColumnType(Types.VARCHAR, "nvarchar(max)");
        registerColumnType(Types.VARBINARY, 4000, "varbinary($1)");
        registerColumnType(Types.VARBINARY, "varbinary(max)");
        registerColumnType(Types.BLOB, "varbinary(max)");
        registerColumnType(Types.CLOB, "nvarchar(max)");
    }

}
