##### Release 2.0 changes
 * New Spring module with Pageable support and bean property mapper
 * New test module with examples on Spring Boot and H2 in memory database
 * Fixed some minor bugs
 * 
 
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
