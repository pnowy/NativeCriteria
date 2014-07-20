package com.github.pnowy.nc.core.dialect;

import org.hibernate.dialect.Oracle10gDialect;
import org.hibernate.type.StringType;

import java.sql.Types;

/**
 * Additional dialect which provide the possibility to use native hibernate queries with NVARCHAR.
 * The standard Hibernate dialect does not support NVARCHAR.
 */
@SuppressWarnings("unused")
public class OracleUnicodeDialect extends Oracle10gDialect {

	public OracleUnicodeDialect() {
		registerHibernateType(Types.NVARCHAR, StringType.INSTANCE.getName());
		registerColumnType(Types.VARCHAR, "nvarchar($1)");
		registerColumnType(Types.CLOB, "nclob");
		registerColumnType(Types.NCLOB, "nclob");
	}
}
