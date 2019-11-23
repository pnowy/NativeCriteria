# Release notes

#### Release 3.0.7 changes

#### Release 3.0.1 changes

* Fixed `NativeNotLikeExp` and `NativeNotILikeExp` expressions

#### Release 3.0 changes

* Migrate to Java 8
* Migrate from Maven to Gradle
* Upgrade dependencies \(supported Hibernate version 5.2 and Spring Data 2.X\)
* Migrate documentation to VuePress
* Some small fixes

#### Release 2.1 changes

* Better documentation

#### Release 2.0.1 changes

* Extended CriteriaResult about blob methods \(returns byte\[\] array\)
* Test profile for MySQL within test module and additional tests

#### Release 2.0 changes

* New Spring module with Pageable support and bean property mapper
* New test module with examples on Spring Boot and H2 in memory database
* Possibility to use custom SQL query on each part of NativeCriteria
* Fixed some minor bugs

#### Release 1.4 changes

* Removed logback implementation \(client can provide own logger implementation\)
* Upgraded hibernate to 4.3.8.Final & other dependencies to newer versions
* Changed a little API \(from NativeCriteria\#getResultAsList to NativeCriteria\#criteriaResult\)
* Integration with Travis CI

#### Release 1.3.1 changes

* Fixed missing JOIN statement for FULL OUTER JOIN. The missing join does not work correctly on some databases

#### Release 1.3 changes:

* Removed apache commons dependencies
* Added OracleUnicodeDialect and SQLServerUnicodeDialect classes which supporting NVARCHAR types in older Hibernate version and newer databases \(see problem descriptions at \[here\]\([http://www.tomecode.com/2012/01/08/how-to-fix-mapping-errors-in-hibernateno-dialect-mapping-for-jdbc-type-9-found-nclob-expected-nvarchar2/\](http://www.tomecode.com/2012/01/08/how-to-fix-mapping-errors-in-hibernateno-dialect-mapping-for-jdbc-type-9-found-nclob-expected-nvarchar2/\)\) and \[here\]\([http://www.componentix.com/blog/5/improved-hibernate-dialect-for-microsoft-sql-server\)\](http://www.componentix.com/blog/5/improved-hibernate-dialect-for-microsoft-sql-server%29\)\)
* Upgraded Google Guava dependency to 17.0
* Upgraded Hibernate dependency to version 4.3.5.Final

#### Release 1.2 changes:

* Added CriteriaResultTransformer interface
* Migration to logback as logging implementation \(from log4j\)



