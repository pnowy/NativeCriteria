
[![Build Status](https://travis-ci.org/pnowy/NativeCriteria.svg?branch=develop)](https://travis-ci.org/pnowy/NativeCriteria)

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/cz.jirutka.rsql/rsql-parser/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.pnowy.nc/nativeCriteria-core)

### Main advantages / assumptions:
 * simple, friendly API
 * without any other generated classed based on database schema
 * possibility to build complex joins with detailed projections
 * work on all databases supported by Hibernate
 * designed for a select queries (not supported update queries now)

##### Release 1.4 changes
  * Removed logback implementation (client can provide own logger implementation)
  * Upgraded hibernate to 4.3.8.Final & other dependencies to newer versions
  * Changed a little API (from NativeCriteria#getResultAsList to NativeCriteria#criteriaResult)
  * Integration with Travis CI

##### Release 1.3.1 changes
 * Fixed missing JOIN statement for FULL OUTER JOIN ([pull request](https://github.com/pnowy/NativeCriteria/pull/1) reported by groestl).
   The missing join does not work correctly on some databases (i.e. postgresql)

##### Release 1.3 changes:
 * Removed apache commons dependencies
 * Added OracleUnicodeDialect and SQLServerUnicodeDiales classes which supporting NVARCHAR types in older Hibernate version and newer databases
 	(see problem descriptions at: http://www.tomecode.com/2012/01/08/how-to-fix-mapping-errors-in-hibernateno-dialect-mapping-for-jdbc-type-9-found-nclob-expected-nvarchar2/
 	 and http://www.componentix.com/blog/5/improved-hibernate-dialect-for-microsoft-sql-server)
 * Upgraded Google Guava dependency to 17.0
 * Upgraded Hibernate dependency to version 4.3.5.Final

##### Release 1.2 changes:
 * Added CriteriaResultTransformer interface
 * Migration to logback as logging implementation (from log4j)

### Simple select example:

```java
NativeCriteria c = new NativeCriteria(new HibernateQueryProvider(hibernateSession), "table_name", "alias");
c.setProjection(NativeExps.projection().addProjection("alias.column_name"));  
c.add(NativeExps.isNull("alias.column_name"));
c.setOrder(NativeExps.order().add("alias.column_name", OrderType.ASC));
CriteriaResult res = c.criteriaResult();
while (res.next()) {
	resp.add(res.getLong(0, null));
}

CriteriaResult res = new NativeCriteria(new JpaQueryProvider(entityManager), "table_name", "alias")
		.setProjection(NativeExps.projection().addProjection("alias.column_name"));  
		.add(NativeExps.isNull("alias.column_name"));
		.setOrder(NativeExps.order().add("alias.column_name", OrderType.ASC))
		.criteriaResult();
while (res.next()) {
	resp.add(res.getLong(0, null));
}
```
### Simple select example with inner join

```java
NativeCriteria c = new NativeCriteria(new HibernateQueryProvider(hibernateSession), "table_name", "alias");
c.addJoin(NativeExps.innerJoin("table_name_to_join", "alias2", "alias.left_column", "alias2.right_column"));
c.setProjection(NativeExps.projection().addProjection(Lists.newArrayList("alias.table_column","alias2.table_column")));
```

### Get result as object list by NativeObjectMapper

```java
NativeCriteria nc = new NativeCriteria(new HibernateQueryProvider(hibernateSession), "ADDRESS", "a");
nc.add(NativeExps.eq("a.city", "WARSAW"));
List<Address> res = nc.getResultAsList(new NativeObjectMapper<Address>() {
        @Override
	public Address mapObject(CriteriaResult cr) {
		Address a = new Address();
		a.setId(cr.getLong(0));
		a.setCity(cr.getString(1));
		a.setStreet(cr.getString(2));
		a.setBuildingNumber(cr.getString(3));
		a.setZipCode(cr.getString(4));
		return a;
	}
});

```

### Get any result by CriteriaResultTransformer

```java
NativeCriteria nc = new NativeCriteria(new HibernateQueryProvider(hibernateSession), "ADDRESS", "a");
nc.add(NativeExps.eq("a.city", "WARSAW"));
Map<String, String> result = nc.criteriaResult(new CriteriaResultTransformer<Map<String, String>>() {
			@Override
			public Map<String, String> transform(CriteriaResult criteriaResult) {
				Map<String, String> result = Maps.newHashMap();
				while (criteriaResult.next()) {
					result.put(criteriaResult.getString(1), criteriaResult.getString(4));
				}
				return result;
			}
		});

```

### Logger to log execution sql time:
```xml
<logger name="NativeCriteriaPerformance" level="DEBUG">
	<appender-ref ref="stdout" />
</logger>
```

### Library available on Maven Central Repository
```xml
<dependency>
	<groupId>com.github.pnowy.nc</groupId>
	<artifactId>nativeCriteria-core</artifactId>
	<version>1.4</version>
</dependency>
```

### Development && Participation

For development is used the GitFlow approach. If you want to participate on the development please perform the pull request to the **develop** branch.